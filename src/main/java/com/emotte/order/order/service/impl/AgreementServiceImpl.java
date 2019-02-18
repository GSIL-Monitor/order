package com.emotte.order.order.service.impl;

import com.emotte.dubbo.installment.CustomerInfoService;
import com.emotte.dubbo.installment.InstallmentService;
import com.emotte.eserver.core.helper.LogHelper;
import com.emotte.gentlemanSignature.core.controller.LocalSignController;
import com.emotte.gentlemanSignature.core.controller.OrganizationStatusController;
import com.emotte.gentlemanSignature.core.extra.AbstractSignatory;
import com.emotte.gentlemanSignature.core.model.GSResult;
import com.emotte.gentlemanSignature.core.model.LocalSign_Signatory;
import com.emotte.gentlemanSignature.core.model.LocalSign_fileType0;
import com.emotte.gentlemanSignature.core.model.OrganizationCreate;
import com.emotte.gentlemanSignature.http.HttpClient;
import com.emotte.order.authorg.mapper.reader.ReOrgMapper;
import com.emotte.order.authorg.model.Org;
import com.emotte.order.constant.CommonConstant;
import com.emotte.order.gentlemanSignature.Util;
import com.emotte.order.order.mapper.reader.ReAgreementMapper;
import com.emotte.order.order.mapper.writer.WrAgreementFileMapper;
import com.emotte.order.order.mapper.writer.WrAgreementMapper;
import com.emotte.order.order.mapper.writer.WrOrderMapper;
import com.emotte.order.order.model.*;
import com.emotte.order.order.service.AgreementService;
import com.emotte.order.order.service.OrderBaseService;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.util.Constants;
import com.emotte.order.util.JsonUtil;
import com.emotte.order.util.Tools;
import com.emotte.order.util.pdfTools.template.BaseTemplate;
import com.emotte.server.util.CookieUtils;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.dataUtils.DateUtil;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("agreementService")
@Transactional
public class AgreementServiceImpl implements AgreementService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ReAgreementMapper reAgreementMapper;

	@Resource
	private WrAgreementMapper wrAgreementMapper;
	
	@Resource
	private WrOrderMapper wrOrderMapper;
	@Resource
	private OrderService orderService;
	@Resource
	private OrderBaseService orderBaseService;
	
	@Resource
	private LocalSignController LocalSignController;
	
	@Resource
	OrganizationStatusController organizationStatusController;
	
	@Resource
	private ReOrgMapper reOrgMapper;
	
	@Resource
	private CustomerInfoService customerInfoService;
	
	@Resource
	private InstallmentService installmentService;

	@Resource
	private WrAgreementFileMapper wrAgreementFileMapper;
	
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Agreement loadAgreement(Long id) {
		return this.reAgreementMapper.loadAgreement(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Map queryAgreementById(Long id) {
		return this.reAgreementMapper.queryAgreementById(id);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Agreement> queryAgreement(Agreement agreement, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(reAgreementMapper.countAgreement(agreement));
		}
		agreement.setBeginRow(page.getFilterRecord().toString());
		agreement.setPageSize(page.getPageCount().toString());
		return this.reAgreementMapper.queryAgreement(agreement);
	}


	@Override
	public void insertAgreement(Agreement agreement,LocalSign_fileType0 ls,HttpServletRequest request,HttpServletResponse response) throws Exception{
	    agreement.setAgreementState(1);			//合同状态
	    agreement.setValid(1);					//有效
	    agreement.setVersion((long) 1);
	    agreement.setCreateTime(DateUtil.getCurrDateTime());
	    String msg=null;
	   try {
		   if(null == agreement.getContractDate() || "".equals(agreement.getContractDate())){
				//签约日期为空,默认为合同生效日期
				agreement.setContractDate(agreement.getEffectDate());
			}
		    //服务类型序号设置
		    if (agreement.getOrderType() != null && !"".equals(agreement.getOrderType())) {
				if (CommonConstant.ORDER_TYPE_JIAWU_1.equals(agreement.getOrderType())
					 || CommonConstant.ORDER_TYPE_JIAWU_2.equals(agreement.getOrderType())) {//一般家务
					agreement.setServiceCode(1);
				}else if (CommonConstant.ORDER_TYPE_YUESAO_1.equals(agreement.getOrderType())
						|| CommonConstant.ORDER_TYPE_YUESAO_2.equals(agreement.getOrderType())) {//月嫂
					agreement.setServiceCode(2);
				}else if (CommonConstant.ORDER_TYPE_YUYINGSHI_1.equals(agreement.getOrderType())
						|| CommonConstant.ORDER_TYPE_YUYINGSHI_2.equals(agreement.getOrderType())) {//育婴师
					agreement.setServiceCode(3);
				}else if (CommonConstant.ORDER_TYPE_PEIHU_1.equals(agreement.getOrderType())) {//陪护
					agreement.setServiceCode(4);
				}else if (CommonConstant.ORDER_TYPE_ZHONGDIANGONG_1.equals(agreement.getOrderType())) {//钟点工
					agreement.setServiceCode(7);
				}else {//其它
					agreement.setServiceCode(null);
				}
			} else {
				agreement.setServiceCode(null);
			}
		   
		    if("20520001".equals(agreement.getAgreementModel())) {
		    	agreement.setPreviewFlag("0");
		    	agreement.setElecClientState(1);        //客户待签约
			    agreement.setElecServeState(1);         //服务人员待签约
			    agreement.setElecOtherState(1);         //电子合同待签约
			    ls.setContractName(agreement.getAgreementCode());
			    try {
			    	createContact(agreement,request,response,ls);
				} catch (Exception e) {
					throw e;
				} 
		    }
		    this.checkIsPeContract(agreement);//绩效合同配置
		    this.wrAgreementMapper.insertAgreement(agreement); 
		    if(StringUtils.equals("2",agreement.getAgreementBusinessType()) || agreement.getAgreementBusinessType()=="2") {
		    	AgreementAssistant agreementasstant=new AgreementAssistant();
		    	agreementasstant.setOrderId(agreement.getOrderId());
		    	agreementasstant.setAgreementId(agreement.getId());
		    	agreementasstant.setCustomerserviceAddress(agreement.getCustomerserviceAddress());
		    	agreementasstant.setHospitalizationNum(agreement.getHospitalizationNum());
		    	agreementasstant.setDepartments(agreement.getDepartments());
		    	agreementasstant.setRoomNumber(agreement.getRoomNumber());
		    	agreementasstant.setBedNumber_a(agreement.getBedNumber_a());
		    	agreementasstant.setBedNumber_b(agreement.getBedNumber_b());
		    	agreementasstant.setConsumerstate(agreement.getConsumerstate());
		    	agreementasstant.setConsumersName(agreement.getConsumersName());
		    	agreementasstant.setConsumersCard(agreement.getConsumersCard());
		    	agreementasstant.setCustconsumerRelation(agreement.getCustconsumerRelation());
		    	agreementasstant.setBirthPeriod(agreement.getBirthPeriod());
		    	agreementasstant.setSpecialConsiderations(agreement.getSpecialConsiderations());
		    	agreementasstant.setServiceItems(agreement.getServiceItems());
		    	agreementasstant.setServiceStarttime(agreement.getServiceStarttime());
		    	agreementasstant.setHostsitDay(agreement.getHostsitDay());
		    	agreementasstant.setServiceFormat(agreement.getServiceFormat());
		    	agreementasstant.setDeliveryMode(agreement.getDeliveryMode());
		    	agreementasstant.setReplaceNumber(agreement.getReplaceNumber());
		    	agreementasstant.setRelation_relatives(agreement.getRelation_relatives());
		    	agreementasstant.setRelation_entrust(agreement.getRelation_entrust());
		    	this.wrAgreementMapper.insertAgreementAssistant(agreementasstant);
		    }
		    if(agreement.getAdvancePeriod() != null && agreement.getAdvancePeriod() == 5){
		    	/**如果是海金保理合同往海金传数据*/
		    	Long orderCode=reAgreementMapper.getOrderCode(agreement.getOrderId());
		    	agreement.setOrderId(orderCode);
		    	String result=saveAgreementinfo(agreement);
		    	if (null != result) {
					JSONObject jsonResult=JSONObject.fromObject(result);
					String code=(String) jsonResult.get("code");
					 msg=(String) jsonResult.get("message");
					if("0".equals(code)){
						code = Constants.SCUUESS;
					}else{
						throw new Exception(msg);
					}
				} 
		    }
	} catch (Exception e) {
		log.error("insertAgreement:" + e);
		throw new BaseException(msg);
	}
	}
	
	
	@Override
	@Transactional
	public void insertAgreementNew(Agreement agreement,LocalSign_fileType0 ls,HttpServletRequest request,HttpServletResponse response){
 		agreement.setAgreementState(1);			//合同状态
		agreement.setValid(1);					//有效
		agreement.setVersion((long) 1);
		agreement.setCreateTime(DateUtil.getCurrDateTime());
		String msg=null;
		try {
			if(null == agreement.getContractDate() || "".equals(agreement.getContractDate())){
				//签约日期为空,默认为合同生效日期
				agreement.setContractDate(agreement.getEffectDate());
			}
			//服务类型序号设置
			if (agreement.getOrderType() != null && !"".equals(agreement.getOrderType())) {
				if (CommonConstant.ORDER_TYPE_JIAWU_1.equals(agreement.getOrderType())
						|| CommonConstant.ORDER_TYPE_JIAWU_2.equals(agreement.getOrderType())) {//一般家务
					agreement.setServiceCode(1);
				}else if (CommonConstant.ORDER_TYPE_YUESAO_1.equals(agreement.getOrderType())
						|| CommonConstant.ORDER_TYPE_YUESAO_2.equals(agreement.getOrderType())) {//月嫂
					agreement.setServiceCode(2);
				}else if (CommonConstant.ORDER_TYPE_YUYINGSHI_1.equals(agreement.getOrderType())
						|| CommonConstant.ORDER_TYPE_YUYINGSHI_2.equals(agreement.getOrderType())) {//育婴师
					agreement.setServiceCode(3);
				}else if (CommonConstant.ORDER_TYPE_PEIHU_1.equals(agreement.getOrderType())) {//陪护
					agreement.setServiceCode(4);
				}else if (CommonConstant.ORDER_TYPE_ZHONGDIANGONG_1.equals(agreement.getOrderType())) {//钟点工
					agreement.setServiceCode(7);
				}else {//其它
					agreement.setServiceCode(null);
				}
			} else {
				agreement.setServiceCode(null);
			}
			Long deptId=reAgreementMapper.getDeptId(agreement.getCreateBy());
			agreement.setDeptId(deptId.toString());
			if("20520001".equals(agreement.getAgreementModel())) {
				agreement.setPreviewFlag("0");
				agreement.setElecClientState(1);        //客户待签约
				agreement.setElecServeState(1);         //服务人员待签约
				agreement.setElecOtherState(1);         //电子合同待签约
				ls.setContractName(agreement.getAgreementCode());
				try {
					createContactNew(agreement,request,response,ls);
				} catch (Exception e) {
					throw e;
				}
			}
			this.checkIsPeContract(agreement);//绩效合同配置
			this.wrAgreementMapper.insertAgreement(agreement);
			if(StringUtils.equals("2",agreement.getAgreementBusinessType())) {
		    	AgreementAssistant agreementasstant=new AgreementAssistant();
		    	BeanUtils.copyProperties(agreementasstant, agreement);
		    	/*agreementasstant.setOrderId(agreement.getOrderId());
		    	agreementasstant.setAgreementId(agreement.getId());
		    	agreementasstant.setCustomerserviceAddress(agreement.getCustomerserviceAddress());
		    	agreementasstant.setHospitalizationNum(agreement.getHospitalizationNum());
		    	agreementasstant.setDepartments(agreement.getDepartments());
		    	agreementasstant.setRoomNumber(agreement.getRoomNumber());
		    	agreementasstant.setBedNumber_a(agreement.getBedNumber_a());
		    	agreementasstant.setBedNumber_b(agreement.getBedNumber_b());
		    	agreementasstant.setConsumerstate(agreement.getConsumerstate());
		    	agreementasstant.setConsumersName(agreement.getConsumersName());
		    	agreementasstant.setConsumersCard(agreement.getConsumersCard());
		    	agreementasstant.setCustconsumerRelation(agreement.getCustconsumerRelation());
		    	agreementasstant.setBirthPeriod(agreement.getBirthPeriod());
		    	agreementasstant.setSpecialConsiderations(agreement.getSpecialConsiderations());
		    	agreementasstant.setServiceItems(agreement.getServiceItems());
		    	agreementasstant.setServiceStarttime(agreement.getServiceStarttime());
		    	agreementasstant.setHostsitDay(agreement.getHostsitDay());
		    	agreementasstant.setServiceFormat(agreement.getServiceFormat());
		    	agreementasstant.setDeliveryMode(agreement.getDeliveryMode());
		    	agreementasstant.setReplaceNumber(agreement.getReplaceNumber());
		    	agreementasstant.setRelation_relatives(agreement.getRelation_relatives());
		    	agreementasstant.setRelation_entrust(agreement.getRelation_entrust());*/
		    	
		    	this.wrAgreementMapper.insertAgreementAssistant(agreementasstant);
		    }
			if(agreement.getAdvancePeriod() == 5 && agreement.getAdvancePeriod() != null){
				Long orderCode=reAgreementMapper.getOrderCode(agreement.getOrderId());
		    	agreement.setOrderId(orderCode);
		    	String result=saveAgreementinfo(agreement);
		    	if (null != result) {
					JSONObject jsonResult=JSONObject.fromObject(result);
					String code=(String) jsonResult.get("code");
					 msg=(String) jsonResult.get("message");
					if("0".equals(code)){
						code = Constants.SCUUESS;
					}else{
						throw new Exception(msg);
					}
				} 
		    }
		} catch (Exception e) {
			log.error("新增合同接口服务层错误" + ",orderId:" + agreement.getOrderId() + ",msg:" + msg + ",错误信息insertAgreementNew:" + ExceptionUtils.getStackTrace(e));
			throw new BaseException(msg);
		}
	}
	
	@Override
	public void updateAgreement(Agreement agreement,LocalSign_fileType0 ls,HttpServletRequest request,HttpServletResponse response) {
		String msg=null;
		try {
			if (agreement.getAdvancePeriod() != null && agreement.getAdvancePeriod() != 1) {
				agreement.setRemindDay(null);//提前几日支付
			}else if (agreement.getAdvancePeriod() != null && agreement.getAdvancePeriod() == 1) {
				agreement.setZhifuRemark("");
			}
			if (agreement.getAgreementIds() != null ) {
				  String[] agreementInfos = agreement.getAgreementIds().split(",");
				  for (int i = 0; i < agreementInfos.length; i++) {
					  agreement.setId(Long.parseLong(agreementInfos[i]));
					  List<Agreement> queryAgreementList = this.reAgreementMapper.queryAgreement(agreement);
					  if (queryAgreementList != null && !queryAgreementList.isEmpty() ) {
						  if (queryAgreementList.get(0).getAgreementState() == 1) {
							  agreement.setAgreementState(5);
						  } else if (queryAgreementList.get(0).getAgreementState() == 2) {
							  agreement.setAgreementState(3);
						  }
						  
						  this.wrAgreementMapper.updateAgreement(agreement);
						  
						  if(StringUtils.equals("2",agreement.getAgreementBusinessType()) || agreement.getAgreementBusinessType()=="2") {
							  AgreementAssistant agreementasstant=new AgreementAssistant();
						    	agreementasstant.setOrderId(agreement.getOrderId());
						    	agreementasstant.setAgreementId(agreement.getId());
						    	agreementasstant.setCustomerserviceAddress(agreement.getCustomerserviceAddress());
						    	agreementasstant.setHospitalizationNum(agreement.getHospitalizationNum());
						    	agreementasstant.setDepartments(agreement.getDepartments());
						    	agreementasstant.setRoomNumber(agreement.getRoomNumber());
						    	agreementasstant.setBedNumber_a(agreement.getBedNumber_a());
						    	agreementasstant.setBedNumber_b(agreement.getBedNumber_b());
						    	agreementasstant.setConsumerstate(agreement.getConsumerstate());
						    	agreementasstant.setConsumersName(agreement.getConsumersName());
						    	agreementasstant.setConsumersCard(agreement.getConsumersCard());
						    	agreementasstant.setCustconsumerRelation(agreement.getCustconsumerRelation());
						    	agreementasstant.setBirthPeriod(agreement.getBirthPeriod());
						    	agreementasstant.setSpecialConsiderations(agreement.getSpecialConsiderations());
						    	agreementasstant.setServiceItems(agreement.getServiceItems());
						    	agreementasstant.setServiceStarttime(agreement.getServiceStarttime());
						    	agreementasstant.setHostsitDay(agreement.getHostsitDay());
						    	agreementasstant.setServiceFormat(agreement.getServiceFormat());
						    	agreementasstant.setDeliveryMode(agreement.getDeliveryMode());
						    	agreementasstant.setReplaceNumber(agreement.getReplaceNumber());
						    	agreementasstant.setRelation_relatives(agreement.getRelation_relatives());
						    	agreementasstant.setRelation_entrust(agreement.getRelation_entrust());
							  this.wrAgreementMapper.updateAgreementAssistant(agreementasstant);
						  }
						  
						  
					  }
				  }
			}else {

				 if("20520001".equals(agreement.getAgreementModel())) {
					 agreement.setPreviewFlag("0");
				     try {
				       ls.setContractName(agreement.getAgreementCode());
					   createContact(agreement,request,response,ls);
					 } catch (Exception e) {
						throw e;
					 }
				  }
				 
				 
				this.wrAgreementMapper.updateAgreement(agreement);
				if(agreement.getCheckStatus() != null && agreement.getCheckStatus() == 3){
					agreement.setCheckStatus(1);
					agreement.setUpdateBy(agreement.getCreateBy());
					this.wrAgreementMapper.updateAgreementState(agreement);
				}
				if(StringUtils.equals("2",agreement.getAgreementBusinessType()) || agreement.getAgreementBusinessType()=="2") {
					  AgreementAssistant agreementasstant=new AgreementAssistant();
				    	agreementasstant.setOrderId(agreement.getOrderId());
				    	agreementasstant.setAgreementId(agreement.getId());
				    	agreementasstant.setCustomerserviceAddress(agreement.getCustomerserviceAddress());
				    	agreementasstant.setHospitalizationNum(agreement.getHospitalizationNum());
				    	agreementasstant.setDepartments(agreement.getDepartments());
				    	agreementasstant.setRoomNumber(agreement.getRoomNumber());
				    	agreementasstant.setBedNumber_a(agreement.getBedNumber_a());
				    	agreementasstant.setBedNumber_b(agreement.getBedNumber_b());
				    	agreementasstant.setConsumerstate(agreement.getConsumerstate());
				    	agreementasstant.setConsumersName(agreement.getConsumersName());
				    	agreementasstant.setConsumersCard(agreement.getConsumersCard());
				    	agreementasstant.setCustconsumerRelation(agreement.getCustconsumerRelation());
				    	agreementasstant.setBirthPeriod(agreement.getBirthPeriod());
				    	agreementasstant.setSpecialConsiderations(agreement.getSpecialConsiderations());
				    	agreementasstant.setServiceItems(agreement.getServiceItems());
				    	agreementasstant.setServiceStarttime(agreement.getServiceStarttime());
				    	agreementasstant.setHostsitDay(agreement.getHostsitDay());
				    	agreementasstant.setServiceFormat(agreement.getServiceFormat());
				    	agreementasstant.setDeliveryMode(agreement.getDeliveryMode());
				    	agreementasstant.setReplaceNumber(agreement.getReplaceNumber());
				    	agreementasstant.setRelation_relatives(agreement.getRelation_relatives());
				    	agreementasstant.setRelation_entrust(agreement.getRelation_entrust());
					  this.wrAgreementMapper.updateAgreementAssistant(agreementasstant);
				  }
			}
			// 只有订单状态在面试成功时，才修改订单状态为已签约
			if ("6".equals(agreement.getOrderStatus())&&agreement.getAgreementState() != null && agreement.getAgreementState() == 2) {//确认
				if(!"8".equals(agreement.getCheckedOrderStatus())){
					Order order = new Order();
					order.setId(agreement.getOrderId());
					order.setOrderStatus(7);//已签约
					orderService.updateOrder2(order);
				}
			}
			//向海金传入数据
			if(agreement.getAdvancePeriod() != null && agreement.getAdvancePeriod() == 5){
				Long orderCode=reAgreementMapper.getOrderCode(agreement.getOrderId());
		    	agreement.setOrderId(orderCode);
		    	String result=saveAgreementinfo(agreement);
		    	if (null != result) {
					JSONObject jsonResult=JSONObject.fromObject(result);
					String code=(String) jsonResult.get("code");
					msg=(String) jsonResult.get("message");
					if("0".equals(code)){
						code = Constants.SCUUESS;
					}else{
						throw new Exception(msg);
					}
				} 
		    }
		} catch (Exception e) {
			log.error("updateAgreement:" + e);
			throw new BaseException(msg);
		}
	}
	@Override
	public void updateAgreementNew(Agreement agreement,LocalSign_fileType0 ls,HttpServletRequest request,HttpServletResponse response) {
		String msg=null;
		try {
			if (agreement.getAdvancePeriod() != null && agreement.getAdvancePeriod() != 1) {
				agreement.setRemindDay(null);//提前几日支付
			}else if (agreement.getAdvancePeriod() != null && agreement.getAdvancePeriod() == 1) {
				agreement.setZhifuRemark("");
			}
			if (agreement.getAgreementIds() != null ) {
				String[] agreementInfos = agreement.getAgreementIds().split(",");
				for (int i = 0; i < agreementInfos.length; i++) {
					agreement.setId(Long.parseLong(agreementInfos[i]));
					List<Agreement> queryAgreementList = this.reAgreementMapper.queryAgreement(agreement);
					if (queryAgreementList != null && !queryAgreementList.isEmpty() ) {
						if (queryAgreementList.get(0).getAgreementState() == 1) {
							agreement.setAgreementState(5);
						} else if (queryAgreementList.get(0).getAgreementState() == 2) {
							agreement.setAgreementState(3);
						}
					    
						this.wrAgreementMapper.updateAgreement(agreement);
						
						if(StringUtils.equals("2",agreement.getAgreementBusinessType())) {
							    AgreementAssistant agreementasstant=new AgreementAssistant();
							    BeanUtils.copyProperties(agreementasstant, agreement); 
						    	/*agreementasstant.setOrderId(agreement.getOrderId());
						    	agreementasstant.setAgreementId(agreement.getId());
						    	agreementasstant.setCustomerserviceAddress(agreement.getCustomerserviceAddress());
						    	agreementasstant.setHospitalizationNum(agreement.getHospitalizationNum());
						    	agreementasstant.setDepartments(agreement.getDepartments());
						    	agreementasstant.setRoomNumber(agreement.getRoomNumber());
						    	agreementasstant.setBedNumber_a(agreement.getBedNumber_a());
						    	agreementasstant.setBedNumber_b(agreement.getBedNumber_b());
						    	agreementasstant.setConsumerstate(agreement.getConsumerstate());
						    	agreementasstant.setConsumersName(agreement.getConsumersName());
						    	agreementasstant.setConsumersCard(agreement.getConsumersCard());
						    	agreementasstant.setCustconsumerRelation(agreement.getCustconsumerRelation());
						    	agreementasstant.setBirthPeriod(agreement.getBirthPeriod());
						    	agreementasstant.setSpecialConsiderations(agreement.getSpecialConsiderations());
						    	agreementasstant.setServiceItems(agreement.getServiceItems());
						    	agreementasstant.setServiceStarttime(agreement.getServiceStarttime());
						    	agreementasstant.setHostsitDay(agreement.getHostsitDay());
						    	agreementasstant.setServiceFormat(agreement.getServiceFormat());
						    	agreementasstant.setDeliveryMode(agreement.getDeliveryMode());
						    	agreementasstant.setReplaceNumber(agreement.getReplaceNumber());
						    	agreementasstant.setRelation_relatives(agreement.getRelation_relatives());
						    	agreementasstant.setRelation_entrust(agreement.getRelation_entrust());*/
							    this.wrAgreementMapper.updateAgreementAssistant(agreementasstant);
						  }
						
					}
				}
			}else {
				Long deptId=reAgreementMapper.getDeptId(agreement.getCreateBy());
				agreement.setDeptId(deptId.toString());
				if("20520001".equals(agreement.getAgreementModel())) {
					agreement.setPreviewFlag("0");
					try {
						ls.setContractName(agreement.getAgreementCode());
						createContactNew(agreement,request,response,ls);
					} catch (Exception e) {
						throw e;
					}
				}
				
			    
				this.wrAgreementMapper.updateAgreement(agreement);
				if(agreement.getCheckStatus() != null && agreement.getCheckStatus() == 3){
					agreement.setCheckStatus(1);
					agreement.setUpdateBy(agreement.getCreateBy());
					this.wrAgreementMapper.updateAgreementState(agreement);
				}
				
				if(StringUtils.equals("2",agreement.getAgreementBusinessType())) {
					  AgreementAssistant agreementasstant=new AgreementAssistant();
					  BeanUtils.copyProperties(agreementasstant, agreement);
				    	/*agreementasstant.setOrderId(agreement.getOrderId());
				    	agreementasstant.setAgreementId(agreement.getId());
				    	agreementasstant.setCustomerserviceAddress(agreement.getCustomerserviceAddress());
				    	agreementasstant.setHospitalizationNum(agreement.getHospitalizationNum());
				    	agreementasstant.setDepartments(agreement.getDepartments());
				    	agreementasstant.setRoomNumber(agreement.getRoomNumber());
				    	agreementasstant.setBedNumber_a(agreement.getBedNumber_a());
				    	agreementasstant.setBedNumber_b(agreement.getBedNumber_b());
				    	agreementasstant.setConsumerstate(agreement.getConsumerstate());
				    	agreementasstant.setConsumersName(agreement.getConsumersName());
				    	agreementasstant.setConsumersCard(agreement.getConsumersCard());
				    	agreementasstant.setCustconsumerRelation(agreement.getCustconsumerRelation());
				    	agreementasstant.setBirthPeriod(agreement.getBirthPeriod());
				    	agreementasstant.setSpecialConsiderations(agreement.getSpecialConsiderations());
				    	agreementasstant.setServiceItems(agreement.getServiceItems());
				    	agreementasstant.setServiceStarttime(agreement.getServiceStarttime());
				    	agreementasstant.setHostsitDay(agreement.getHostsitDay());
				    	agreementasstant.setServiceFormat(agreement.getServiceFormat());
				    	agreementasstant.setDeliveryMode(agreement.getDeliveryMode());
				    	agreementasstant.setReplaceNumber(agreement.getReplaceNumber());
				    	agreementasstant.setRelation_relatives(agreement.getRelation_relatives());
				    	agreementasstant.setRelation_entrust(agreement.getRelation_entrust());*/
					  this.wrAgreementMapper.updateAgreementAssistant(agreementasstant);
				  }
				
			}
			// 只有订单状态在面试成功时，才修改订单状态为已签约
			if ("6".equals(agreement.getOrderStatus())&&agreement.getAgreementState() != null && agreement.getAgreementState() == 2) {//确认
				if(!"8".equals(agreement.getCheckedOrderStatus())){
					Order order = new Order();
					order.setId(agreement.getOrderId());
					order.setOrderStatus(7);//已签约
					orderService.updateOrder2(order);
				}
			}
			
			//向海金传入数据
			if(agreement.getAdvancePeriod() != null && agreement.getAdvancePeriod() == 5  ){
				Long orderCode=reAgreementMapper.getOrderCode(agreement.getOrderId());
		    	agreement.setOrderId(orderCode);
		    	String result=saveAgreementinfo(agreement);
		    	if (null != result) {
					JSONObject jsonResult=JSONObject.fromObject(result);
					String code=(String) jsonResult.get("code");
					 msg=(String) jsonResult.get("message");
					if("0".equals(code)){
						code = Constants.SCUUESS;
					}else{
						throw new Exception(msg);
					}
				} 
		    }
		} catch (Exception e) {
			log.error("updateAgreement:" + msg);
			throw new BaseException(msg);
		}
	}
	/**
	 * 预览、下载合同
	 * @param request
	 * @param response
	 * @param previewFlag 预览、下载标记 ,0为下载
	 * @param contractId  合同ID
	 * @param homePage  下载合同首页标记  ,1为下载
	 */
	@Override
	public String queryContractPDF(HttpServletRequest request,HttpServletResponse response,
			String previewFlag, String contractId,String homePage,LocalSign_fileType0 ls,String agreementModel) {
		Agreement agr = new Agreement();
		agr.setPreviewFlag(previewFlag);
		agr.setHomePage(homePage!=null?Integer.valueOf(homePage):null);
		agr.setId(Long.valueOf(contractId));
		agr.setAgreementModel(agreementModel);
		String fileName = null;
		try {
			fileName = createContact(agr,request,response,ls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}
	
	// 获取一个毫秒数作为一个名称（本地任务）
	public String getSS(){
		Date d = new Date();
		String s = d.getTime()+"";
		s = s.substring(s.length()-4, s.length());
		return s;
	}
	//获得一个时间标识保存路径的一部分（yyyy/mm/dd/）
	public String getPath(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
		return sdf.format(new Date());
	}
	// 下载文件  要被下载的文件的绝对路径
	public void downFile(String realPath,String fileName,HttpServletResponse response,boolean dzht,String contractCode) throws Exception{
		response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename="+contractCode+".pdf");
        String allPath = null;
        ServletOutputStream out = response.getOutputStream();
        
        try {
        	if(dzht) {
        		
	        	byte[] file_byte = HttpClient.readBytes(fileName);
	        	out.write(file_byte,0,file_byte.length);
	        	
    		}else {
    			
    		   if(null!=realPath) {
    	        	realPath = realPath.replaceAll("\\\\", "/"); 
    	        	allPath = realPath + fileName;
    	        }
    			File f = new File(allPath);
    			FileInputStream  inputStream  = new FileInputStream(f);

    			byte[] buffer = new byte[2048];
    			int iLength = 0;
    			while ((iLength = inputStream.read(buffer)) != -1) {
    				out.write(buffer,0,iLength);
    			}
    			inputStream.close();
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			out.flush();
			out.close();
		}
        
      
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<HashMap<String, Object>> queryCheckAgreement_listPage(Agreement agreement, Page page) {
		agreement.setPage(page);
		return this.reAgreementMapper.queryCheckAgreement_listPage(agreement);
	}

	@Override
	public void checkAgreement(Agreement agreement) {
		try {
			this.wrAgreementMapper.checkAgreement(agreement);
		} catch (Exception e) {
			log.error("checkAgreement:" + e);
			throw new BaseException(e);
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<HashMap<String, Object>> queryServiceStation(Agreement agreement) {
		return this.reAgreementMapper.queryServiceStation(agreement);
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<HashMap<String, Object>> queryPersonnel(Long orderId) {
		return this.reAgreementMapper.queryPersonnel(orderId);
	}

	@Override
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED)
	public List<BaseCity> queryCitys(BaseCity baseCity) {
		return this.reAgreementMapper.queryCitys(baseCity);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Agreement> queryCanCopyAgreement(Agreement agreement) {
		return this.reAgreementMapper.queryCanCopyAgreement(agreement);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Map<String, String> queryAgreementHeader(Long deptId) {
		return this.reAgreementMapper.queryAgreementHeader(deptId);
	}

	@Override
	public List<Map<String, Object>> queryExportCpeList(Agreement agreement) {
		return this.reAgreementMapper.queryExportCpeList(agreement);
	}

	@Override
	public void updateAgreementTwo(Agreement agreement) {
		//终止
		Agreement agreement2 =new Agreement();
		agreement2.setAgreementState(agreement.getAgreementStateTwo());
		agreement2.setId(agreement.getSureId());
		agreement2.setReason(agreement.getReason());
		agreement2.setUpdateBy(agreement.getUpdateBy());
		//新增
		Agreement agreement3 =new Agreement();
		agreement3.setAgreementState(agreement.getAgreementState());
		agreement3.setOrderId(agreement.getOrderId());
		agreement3.setId(agreement.getId());
		agreement3.setCheckedOrderStatus(agreement.getCheckedOrderStatus());
		agreement3.setOrderStatus(agreement.getOrderStatus());
		agreement3.setUpdateBy(agreement.getUpdateBy());
		try {
			//agreement.setUpdateTime(DateUtil.getCurrDateTime());
			if (agreement.getAdvancePeriod() != null && agreement.getAdvancePeriod() != 1) {
				agreement.setRemindDay(null);//提前几日支付
			}else if (agreement.getAdvancePeriod() != null && agreement.getAdvancePeriod() == 1) {
				agreement.setZhifuRemark("");
			}
			if (agreement.getAgreementIds() != null ) {
				  String[] agreementInfos = agreement.getAgreementIds().split(",");
				  for (int i = 0; i < agreementInfos.length; i++) {
					  agreement.setId(Long.parseLong(agreementInfos[i]));
					  List<Agreement> queryAgreementList = this.reAgreementMapper.queryAgreement(agreement);
					  if (queryAgreementList != null && !queryAgreementList.isEmpty() ) {
						  if (queryAgreementList.get(0).getAgreementState() == 1) {
							  agreement.setAgreementState(5);
						  } else if (queryAgreementList.get(0).getAgreementState() == 2) {
							  agreement.setAgreementState(3);
						  }
						  this.wrAgreementMapper.updateAgreement(agreement);
					  }
				  }
			}else {
				this.wrAgreementMapper.updateAgreement(agreement2);
				this.wrAgreementMapper.updateAgreement(agreement3);
			}
			// 只有订单状态在面试成功时，才修改订单状态为已签约
			if ("6".equals(agreement3.getOrderStatus())&&agreement3.getAgreementState() != null && agreement3.getAgreementState() == 2) {//确认
				if(!"8".equals(agreement3.getCheckedOrderStatus())){
					Order order = new Order();
					order.setId(agreement3.getOrderId());
					order.setOrderStatus(7);//已签约
					orderService.updateOrder2(order);
				}
			}
		} catch (Exception e) {
			log.error("updateAgreement:" + e);
			throw new BaseException(e);
		}
		
	}

	@Override
	public String checkCardName(Long orderId) {
		return reAgreementMapper.checkCardName(orderId);
	}

	@Override
	public List<Map<String, String>> showCustomerManager(Long userId) {
		return this.reAgreementMapper.showCustomerManager(userId);
	}

	@Override
	public List<DataDictionaryModel> getDictionaryInfo(DataDictionaryModel dictObj,HttpServletRequest request) {
		List<DataDictionaryModel> data= reAgreementMapper.getDictionaryInfo(dictObj);
		String deptId =CookieUtils.getJSONKey(request, "deptId").toString();
		Org queryOrg = new Org();
		queryOrg.setId(Long.valueOf(deptId));
		List<Org> orgs =reOrgMapper.query(queryOrg);
		OrganizationCreate organizationCreate = new OrganizationCreate();
		organizationCreate.setEmailOrMobile(orgs.get(0).getEmail());
		try {
			GSResult GSResult = organizationStatusController.process(organizationStatusController.createCommonParam(organizationCreate));
		
			if(null!=GSResult && (GSResult.isSuccess() || GSResult.getCode() == 0)) {
				data.remove(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return data;
	}

	/**
	 * 创建、预览合同
	 * @param agreement
	 * @param request
	 * @param response
	 * @param previewFlag
	 * @return
	 */
	private String createContact(Agreement agreement,HttpServletRequest request,HttpServletResponse response,LocalSign_fileType0 ls)throws Exception{
		/*Managers managers = new Managers();
		managers.setId(CookieReaderUtil.cookieReader(request).getId());
		List<Managers> managerList = this.orderBaseService.queryguanjia(managers);//法定代表人*/
		String realPath = request.getSession().getServletContext().getRealPath("/");// 项目根路径
		String fileName = "";
		String pdfName = Tools.getGeneralTime() + getSS() + "_cpe.pdf";
		
		if (Integer.valueOf(agreement.getPreviewFlag()) == 1) { // 空白合同或者预览则不将路径保存到数据库
			fileName = "dataFile/pdf/temp/" + pdfName;
		} else {
			fileName = "dataFile/itext/cpe_pact/" + getPath() + pdfName;
		}
		
		String filePath = realPath + fileName;
		
		int flag = 1;
		Agreement agr = null;
		
		if (null != agreement.getId() && !agreement.isEditContract()) {
			agr = new Agreement();
			agr.setId(agreement.getId());// 合同ID
			
			List<Agreement> agrList = this.reAgreementMapper.queryAgreement(agr);
			agr = agrList.get(0);
			if (agr.getIsCollection() != null && agr.getIsCollection() == 1) {
				flag = 2;
			} else if (agr.getIsCollection() != null && agr.getIsCollection() == 5) {// 平台代付
				flag = 5;
			} else if (agr.getIsCollection() != null && agr.getIsCollection() == 6) {// 客户直付
				flag = 6;
			} else if (agr.getIsCollection() != null && agr.getIsCollection() == 7) {// 平台代付（修订版）
				flag = 7;
			}
			// 下载合同首页标记
			if (null != agreement.getHomePage()) {
				Integer firstPage = Integer.valueOf(agreement.getHomePage());
				agr.setHomePage(firstPage);
			}
		}else{
			if (agreement.getIsCollection() != null && agreement.getIsCollection() == 1) {
				flag = 2;
			} else if (agreement.getIsCollection() != null && agreement.getIsCollection() == 5) {// 平台代付
				flag = 5;
			} else if (agreement.getIsCollection() != null && agreement.getIsCollection() == 6) {// 客户直付
				flag = 6;
			} else if (agreement.getIsCollection() != null && agreement.getIsCollection() == 7) {// 平台代付（修订版）
				flag = 7;
			}
		}
	
		File pdf = new File(filePath);
		if (!pdf.getParentFile().exists()) {
			pdf.getParentFile().mkdirs();
		}
		
		TemplateFactory factory = new TemplateFactory();
		BaseTemplate bt = null;
		// 电子合同
		if ("20520001".equals(agreement.getAgreementModel())) {
	
			//编辑合同、新增或更新
			if(agreement.isEditContract()) {
				
				final Org o = getOrg(request);
				
				List<LocalSign_Signatory> signatorys_ =
						JsonUtil.jsonToList(URLDecoder.decode(ls.getSignatories(), "utf-8"),
								LocalSign_Signatory.class);
				
				AbstractSignatory as = new AbstractSignatory() {
					@Override
					public List<LocalSign_Signatory> signatories(List<LocalSign_Signatory> signatorys) throws Exception {
						signatorys.get(0).setSignaturePicturePath(o.getSignaturePicture());
						signatorys.get(0).setIdentityCard(o.getOrganizationNumber());
						signatorys.get(0).setEmail(o.getEmail());
						signatorys.get(0).setFullName(o.getContractHeader());
						return signatorys;
					}
				};
				LogHelper.info(getClass(), "signatorys_--->" + signatorys_ + System.currentTimeMillis());
				ls.setSignatories(as.loadToJsonArray(signatorys_));
				//法定代表人
				if(agreement.getOrderId() != null){
					String legalRepresentative = this.reAgreementMapper.queryLegalRepresentative(agreement.getOrderId());
					agreement.setLegalRepresentative(legalRepresentative);
				}
				this.checkIsPeContract(agreement);//绩效合同配置
				bt = factory.getTemplate(pdf, flag, realPath, Integer.valueOf(agreement.getPreviewFlag()),agreement);
				bt.WritePdf();
				
				// 存起来           
				fileName=Util.Instance.gsignature(request.getServletContext(),filePath,fileName,agreement,ls);
				agreement.setNormalContactFile(fileName);
				agreement.setElecOtherState(1);
				request.setAttribute("path", fileName);
				return fileName;
				
			 }else {
				 //不编辑
				 System.out.println(agreement.getPreviewFlag());
				 System.out.println(Integer.valueOf(agreement.getPreviewFlag()));
				 System.out.println(Integer.valueOf(agreement.getPreviewFlag()) == 1);
				 if (Integer.valueOf(agreement.getPreviewFlag()) == 1) {
					    this.checkIsPeContract(agr);//绩效合同配置
						bt = factory.getTemplate(pdf, flag, realPath, Integer.valueOf(agreement.getPreviewFlag()),agr);
						bt.WritePdf();
						agr.setPreviewContactFile(fileName);
						wrAgreementMapper.updateAgreement(agr);
						request.setAttribute("path", fileName);
						return fileName;
					} else {
						fileName =  agr.getNormalContactFile();
						this.downFile(null, agr.getNormalContactFile(), response,true,agr.getAgreementCode());
					}
			 }
		} else {
			// 非电子合同
			this.checkIsPeContract(agr);//绩效合同配置
			bt = factory.getTemplate(pdf, flag, realPath, Integer.valueOf(agreement.getPreviewFlag()),agr);
			bt.WritePdf();
			if (Integer.valueOf(agreement.getPreviewFlag()) == 0) {
				this.downFile(realPath, fileName, response,false,agr.getAgreementCode());
			} else {
				request.setAttribute("path", fileName);
				return fileName;
			}
		}

		return fileName;
	}	
	
	private String createContactNew(Agreement agreement,HttpServletRequest request,HttpServletResponse response,LocalSign_fileType0 ls)throws Exception{
		/*Managers managers = new Managers();
		managers.setId(agreement.getCreateBy());
		List<Managers> managerList = this.orderBaseService.queryguanjia(managers);//法定代表人*/
		
		String realPath = request.getSession().getServletContext().getRealPath("/");// 项目根路径
		String fileName = "";
		String pdfName = Tools.getGeneralTime() + getSS() + "_cpe.pdf";
		
		if (Integer.valueOf(agreement.getPreviewFlag()) == 1) { // 空白合同或者预览则不将路径保存到数据库
			fileName = "dataFile/pdf/temp/" + pdfName;
		} else {
			fileName = "dataFile/itext/cpe_pact/" + getPath() + pdfName;
		}
		
		String filePath = realPath + fileName;
		
		int flag = 1;
		Agreement agr = null;
		if (null != agreement.getId() && !agreement.isEditContract()) {
			agr = new Agreement();
			agr.setId(agreement.getId());// 合同ID
			List<Agreement> agrList = this.reAgreementMapper.queryAgreement(agr);
			agr = agrList.get(0);
			if (agr.getIsCollection() != null && agr.getIsCollection() == 1) {
				flag = 2;
			} else if (agr.getIsCollection() != null && agr.getIsCollection() == 5) {// 平台代付
				flag = 5;
			} else if (agr.getIsCollection() != null && agr.getIsCollection() == 6) {// 客户直付
				flag = 6;
			} else if (agr.getIsCollection() != null && agr.getIsCollection() == 7) {// 平台代付（修订版）
				flag = 7;
			}
			// 下载合同首页标记
			if (null != agreement.getHomePage()) {
				Integer firstPage = Integer.valueOf(agreement.getHomePage());
				agr.setHomePage(firstPage);
			}
		}else{
			if (agreement.getIsCollection() != null && agreement.getIsCollection() == 1) {
				flag = 2;
			} else if (agreement.getIsCollection() != null && agreement.getIsCollection() == 5) {// 平台代付
				flag = 5;
			} else if (agreement.getIsCollection() != null && agreement.getIsCollection() == 6) {// 客户直付
				flag = 6;
			} else if (agreement.getIsCollection() != null && agreement.getIsCollection() == 7) {// 平台代付（修订版）
				flag = 7;
			}
		}
		
		File pdf = new File(filePath);
		if (!pdf.getParentFile().exists()) {
			pdf.getParentFile().mkdirs();
		}
		
		TemplateFactory factory = new TemplateFactory();
		BaseTemplate bt = null;
		// 电子合同
		if ("20520001".equals(agreement.getAgreementModel())) {
			
			//编辑合同、新增或更新
			if(agreement.isEditContract()) {
				
				final Org o = getOrgNew(request,agreement);
				
				List<LocalSign_Signatory> signatorys_ =
						JsonUtil.jsonToList(URLDecoder.decode(ls.getSignatories(), "utf-8"),
								LocalSign_Signatory.class);
				
				AbstractSignatory as = new AbstractSignatory() {
					@Override
					public List<LocalSign_Signatory> signatories(List<LocalSign_Signatory> signatorys) throws Exception {
						signatorys.get(0).setSignaturePicturePath(o.getSignaturePicture());
						signatorys.get(0).setIdentityCard(o.getOrganizationNumber());
						signatorys.get(0).setEmail(o.getEmail());
						signatorys.get(0).setFullName(o.getContractHeader());
						return signatorys;
					}
				};
				LogHelper.info(getClass(), "signatorys_--->" + signatorys_ + System.currentTimeMillis());
				ls.setSignatories(as.loadToJsonArray(signatorys_));
				//法定代表人
				if(agreement.getOrderId() != null){
					String legalRepresentative = this.reAgreementMapper.queryLegalRepresentative(agreement.getOrderId());
					agreement.setLegalRepresentative(legalRepresentative);
				}
				this.checkIsPeContract(agreement);//绩效合同配置
				bt = factory.getTemplate(pdf, flag, realPath, Integer.valueOf(agreement.getPreviewFlag()),agreement);
				bt.WritePdf();
				
				// 存起来           
				fileName=Util.Instance.gsignature(request.getServletContext(),filePath,fileName,agreement,ls);
				agreement.setNormalContactFile(fileName);
				agreement.setElecOtherState(1);
				request.setAttribute("path", fileName);
				return fileName;
				
			}else {
				//不编辑
				if (Integer.valueOf(agreement.getPreviewFlag()) == 1) {
					this.checkIsPeContract(agr);//绩效合同配置
					bt = factory.getTemplate(pdf, flag, realPath, Integer.valueOf(agreement.getPreviewFlag()),agr);
					bt.WritePdf();
					agr.setPreviewContactFile(fileName);
					wrAgreementMapper.updateAgreement(agr);
					request.setAttribute("path", fileName);
					return fileName;
				} else {
					fileName =  agr.getNormalContactFile();
					this.downFile(null, agr.getNormalContactFile(), response,true,agr.getAgreementCode());
				}
			}
		} else {
			// 非电子合同
			this.checkIsPeContract(agr);//绩效合同配置
			bt = factory.getTemplate(pdf, flag, realPath, Integer.valueOf(agreement.getPreviewFlag()),agr);
			bt.WritePdf();
			if (Integer.valueOf(agreement.getPreviewFlag()) == 0) {
				this.downFile(realPath, fileName, response,false,agr.getAgreementCode());
			} else {
				request.setAttribute("path", fileName);
				return fileName;
			}
		}
		
		return fileName;
	}	
	
	/**
	 * weixd 20180711
	 * 合同编号中 【 o O i I 0 1】分别替换为【a A b B 8 9】
	 * @param agr
	 */
	private static Agreement changeString(Agreement agr) {
		if(agr != null && StringUtils.isNotBlank(agr.getAgreementCode())) {
			String agreementCode = agr.getAgreementCode();
			if(agreementCode.contains("o") || agreementCode.contains("O") || agreementCode.contains("i") || 
					agreementCode.contains("I") || agreementCode.contains("0") || agreementCode.contains("1")) {
				agreementCode = agreementCode.replaceAll("o", "a");
				agreementCode = agreementCode.replaceAll("O", "A");
				agreementCode = agreementCode.replaceAll("i", "b");
				agreementCode = agreementCode.replaceAll("I", "B");
				agreementCode = agreementCode.replaceAll("0", "8");
				agreementCode = agreementCode.replaceAll("1", "9");
			}
			agr.setAgreementCode(agreementCode);
		}
		return agr;
	}

	private Org getOrg(HttpServletRequest request) {
		String deptId =CookieUtils.getJSONKey(request, "deptId").toString();
		Org org_database = null;
		if(null!=deptId && !"".equals(deptId)) {
			Org queryOrg = new Org();
			queryOrg.setId(Long.valueOf(deptId));
			List<Org> orgs =reOrgMapper.query(queryOrg);
			org_database=orgs.get(0);
		}else {
			//默认是总部
    		org_database=new Org();
    		String tempSig = "images/sig/sig.jpg";
    		org_database.setSignaturePicture(request.getSession().getServletContext().getRealPath("/")+tempSig);
    		org_database.setName("北京易盟天地信息技术股份有限公司");
    		org_database.setOrganizationNumber("91110107764338538E");
    		org_database.setEmail("623161512@qq.com");
		}
		return org_database;
	}

	
	private Org getOrgNew(HttpServletRequest request, Agreement agreement) {
		//String deptId1 =CookieUtils.getJSONKey(request, "deptId").toString();
		String deptId = agreement.getDeptId();
		Org org_database = null;
		if(null!=deptId && !"".equals(deptId)) {
			Org queryOrg = new Org();
			queryOrg.setId(Long.valueOf(deptId));
			List<Org> orgs =reOrgMapper.query(queryOrg);
			org_database=orgs.get(0);
		}else {
			//默认是总部
			org_database=new Org();
			String tempSig = "images/sig/sig.jpg";
			org_database.setSignaturePicture(request.getSession().getServletContext().getRealPath("/")+tempSig);
			org_database.setName("北京易盟天地信息技术股份有限公司");
			org_database.setOrganizationNumber("91110107764338538E");
			org_database.setEmail("623161512@qq.com");
		}
		return org_database;
	}
	
	
	
	   
	/**
	 * 海金更新签约时间方法——20180630添加 
	 */
	@Override
	public Agreement queryAgreementcreatime(String id) {
		// TODO Auto-generated method stub
		return this.reAgreementMapper.queryAgreementcreatime(id);
	}

	@Override
	public Agreement queryAgreementcreatime1(String id) {
		// TODO Auto-generated method stub
		return this.reAgreementMapper.queryAgreementcreatime1(id);
	}

	@Override
	public Agreement loadAgreementcreatime(Long id) {
		// TODO Auto-generated method stub
		return this.reAgreementMapper.loadAgreementcreatime(id);
	}

	@Override
	public void updateAgreementCreatime(Agreement agreement) {
		// TODO Auto-generated method stub
		this.wrAgreementMapper.updateAgreementTime(agreement);
	}
	
	@Override
	public void updateAgreementCreatimeNew(Agreement agreement) {
		// TODO Auto-generated method stub
		this.wrAgreementMapper.updateAgreementTimeNew(agreement);
	}

	/**
	 *
	 * 功能描述: 根据合同ID查询合同信息
	 *
	 * @param: orderId	订单ID
	 * @return:
	 * @auther: lenovo
	 * @date: 2018/7/19 20:12
	 */
	@Override
	public int findContractById(Long contractId) {
		int count = reAgreementMapper.findContractById(contractId);
		return count;
	}

	/**
	 *
	 * 功能描述: 根据合同ID查询合同编号
	 *
	 * @param: contractId 合同ID
	 * @return:
	 * @auther: lenovo
	 * @date: 2018/7/20 16:23
	 */
	@Override
	public String findAgreementCodeById(Long contractId) {
		String agreementCode = reAgreementMapper.findAgreementCodeById(contractId);
		return agreementCode;
	}
	
	public String saveAgreementinfo(Agreement agreement){
		/*String json="{'customName':'"+agreement.getAccountName()+"','customIdcard':'"+agreement.getCardNum()+"','bankName':'"+agreement.getAccountBank()+"','bankAccountNum':'"+agreement.getAccountNum()+"',"
						+ "'whiteTotalMoney':'"+agreement.getInstPrepaidMoney()+"','stagingNumber':'"+agreement.getInstPrepaidMonths().toString()+"','bankMobile':'"+agreement.getAccountMobile()+"','orderId':'"+agreement.getOrderId().toString()+"',"
						+ "'contractCode':'"+agreement.getAgreementCode()+"','custId':'"+agreement.getPersonId()+"','agServiceName':'"+agreement.getPartyC()+"','agServiceIdcard':'"+agreement.getCardNumC()+"','agSignDateStr':'"+agreement.getContractDate()+"','agStartDateStr':'"+agreement.getEffectDate()+"',"
						+ "'agEndDateStr':'"+agreement.getFinishDate()+"','oOrderType':'"+agreement.getOrderType()+"','eachPeriodMoney':'"+agreement.getServiceMoney()+"','createBy':'"+agreement.getCreateBy()+"'}";*/
		Customer customer =new Customer();
		Agreement oldAgreement = reAgreementMapper.findOne(agreement.getId());
		customer.setCustomName(agreement.getAccountName());
		customer.setCustomIdcard(agreement.getCardNum());
		customer.setBankName(oldAgreement.getAccountBank());
		customer.setBankAccountNum(agreement.getAccountNum());
		customer.setWhiteTotalMoney(agreement.getInstPrepaidMoney());
		customer.setStagingNumber(agreement.getInstPrepaidMonths().toString());
		customer.setBankMobile(agreement.getAccountMobile());
		customer.setOrderId(agreement.getOrderId().toString());
		customer.setContractCode(agreement.getAgreementCode());
		customer.setCustId(agreement.getPersonId());
		customer.setAgServiceIdcard(agreement.getCardNumC());
		customer.setAgServiceName(agreement.getPartyC());
		customer.setAgSignDateStr(agreement.getContractDate());
		customer.setAgEndDateStr(agreement.getFinishDate());
		customer.setAgStartDateStr(agreement.getEffectDate());
		customer.setoOrderType(agreement.getOrderType());
		customer.setEachPeriodMoney(agreement.getServiceMoney().doubleValue());
		customer.setCreateBy(agreement.getCreateBy());
		JSONObject customerJson = JSONObject.fromObject(customer);
		String json1 = customerJson.toString();
		log.info("saveAgreementinfo传入的参数:"+json1);
		System.err.println(json1);
		String result=customerInfoService.saveCustomerInfo(json1);
		log.info("saveAgreementinfo返回的参数 :"+json1);
		System.err.println("返回的参数 "+result);
		return result;
	}
	@Override
	public Agreement selectAgreement(Agreement agreement) {
		return reAgreementMapper.selectAgreement(agreement);
	}

	/**
	 * 根据订单ID或者合同ID查询合同是否存在
	 *
	 * @param culomnName    查询列名称
	 * @param id            查询参数
	 * @return
	 * @Author zhanghao
	 * @Date 20181114
	 */
	@Override
	public List<Agreement> findAgreementByOrderIdOrAgreementId(String culomnName, Long id) {
		List<Agreement> agreements = reAgreementMapper.findAgreementByOrderIdOrAgreementId(culomnName,id);
		return agreements;
	}
	
	
	/**
	 * 查询是否是海金合同
	 * 操作人：周鑫
	 * 2018年11月15日下午1:40:24
	 */
	@Override
	public int findAgreementByOrderIdGold(Long orderId) {
		int count = reAgreementMapper.findAgreementByOrderIdGold(orderId);
		return count;
	}
	/**
	 * 查询合同code和订单code
	 * @param orderId 订单ID
	 * @param id 合同IDs
	 * 操作人：周鑫
	 * 2018年11月15日下午1:40:24
	 */
	@Override
	public Map<String,String> queryOrderIdAndId(Long orderId, Long id) {
		Map<String, String> map=new HashMap<>();
		//获取订单号 
		Long orderCode=reAgreementMapper.getOrderCode(orderId);
		//获取合同Code
		String agreementCode = reAgreementMapper.findAgreementCodeById(id);
		map.put("orderId", orderCode.toString());
		map.put("contractCode", agreementCode);
		return map;
	}
	

	/**
	 * 根据订单ID查询最后一个订单的结束时间
	 *
	 * @param orderId
	 * @return
	 * @Author zhanghao
	 * @Date 20181115
	 */
	@Override
	public Agreement findEndTimeByOrderId(Long orderId) {
		Agreement agreement = reAgreementMapper.findEndTimeByOrderId(orderId);
		return agreement;
	}

	/**
	 * 根据订单ID查询合同列表
	 *
	 * @param orderId
	 * @return
	 * @Author zhanghao
	 * @Date 20181116
	 */
	@Override
	public List<Agreement> findAgreementListByOrderId(Agreement agreement) {
		List<Agreement> agreements = reAgreementMapper.findAgreementListByOrderId(agreement);
		return agreements;
	}

	@Override
	public JSONObject updateInsurance(Agreement agreement) {
		JSONObject msg = new JSONObject();
		int updateFlag = wrAgreementMapper.updateAgreement(agreement);
		if(updateFlag >0){
			msg.accumulate("msg", Constants.SCUUESS);
			msg.accumulate("msgInfo", "操作成功");
		}else{
			msg.accumulate("msg", Constants.FAIL);
			msg.accumulate("msgInfo", "操作失败");
		}
		return msg;
	}
	
	/**
	 * 修改合同的审核状态
	 */
	@Override
	public JSONObject updateAgreementCheckStatus(Long checkAgreementId,Long loginId) {
		JSONObject msg = new JSONObject();
		Agreement agreement=new Agreement();
		agreement.setId(checkAgreementId);
		agreement.setCheckStatusText("未处理");
		agreement.setCheckStatus(1);
		agreement.setUpdateBy(loginId);
		int updateFlag = wrAgreementMapper.updateAgreementType(agreement);
		if(updateFlag >0){
			msg.accumulate("msg", Constants.SCUUESS);
			msg.accumulate("msgInfo", "操作成功");
		}else{
			msg.accumulate("msg", Constants.FAIL);
			msg.accumulate("msgInfo", "操作失败");
		}
		return msg;
	}

	/**
	 * 单次服务订单查询三方协议详情
	 *
	 * @param orderId
	 * @return
	 * @Author zhanghao
	 * @Date 20190111
	 */
	@Override
	public Agreement queryAgreementServerOneDetail(Long orderId) {
		Agreement agreement = reAgreementMapper.queryAgreementServerOneDetail(orderId);
		return agreement;
	}

	/**
	 * 保存单次服务合同
	 *
	 * @param agreement
	 * @Author zhanghao
	 * @Date 20190111
	 */
	@Override
	public void addOneceAgreement(Agreement agreement) {
		wrAgreementMapper.insertOnceAgreement(agreement);
	}

	/**
	 * 根据合同ID查询对象信息
	 *
	 * @param id
	 * @return
	 * @Author zhanghao
	 * @Date 20190114
	 */
	@Override
	public Agreement findAgreementById(Long id) {
		Agreement agreement = reAgreementMapper.findAgreementById(id);
		return agreement;
	}

	/**
	 * 更新单次合同
	 *
	 * @param agreement
	 * @Author zhanghao
	 * @Date 20190114
	 */
	@Override
	public void updateOneceAgreement(Agreement agreement) {
		wrAgreementMapper.updateAgreement(agreement);
	}

	/**
	 * 删除单次合同
	 *
	 * @Author zhanghao
	 * @Date 20190114
	 * @param agreement
	 */
	@Override
	public void deleteOnceAgreement(Agreement agreement) {
		//修改单次合同valid字段
		wrAgreementMapper.updateAgreement(agreement);
		//修改PDF文件valid字段
		wrAgreementFileMapper.deleteOldOnceServerAgreementCopy(agreement);
	}

	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Boolean checkIsPeContractOld(Agreement agreement) {
		Boolean result = false;//默认
		agreement.setIsPeContract(2);//默认
		agreement.setPeContractBasic(null);//默认
		agreement.setPeContractPerformance(null);//默认
		try {
			/*****************************绩效合同配置开始*****************************/
			Boolean isPerformanceContract = true;//关于是否执行绩效合同配置
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date peContractStartDate = df.parse("2019-01-01 00:00");//执行开始日期
			Date peContractEndDate = df.parse("3000-01-01 00:00");//执行结束日期
			String[] peContractCity = new String[]{"101001001"};//适用城市,cityCode
			BigDecimal peContractBasic = new BigDecimal(0.90).setScale(1,BigDecimal.ROUND_DOWN);//基本工资占比
			BigDecimal peContractPerformance = new BigDecimal(0.10).setScale(1,BigDecimal.ROUND_DOWN);//绩效工资占比
			/*****************************绩效合同配置结束*****************************/
			if(null == agreement.getId()){
				//新增操作
				if(isPerformanceContract){
					Date today = new Date();//当前日期
					if(today.getTime() >= peContractStartDate.getTime() && today.getTime() <= peContractEndDate.getTime()){
						//在执行时间段内
						if(null != agreement.getOrderId()){
							Order order = this.reAgreementMapper.loadOrder(agreement.getOrderId());
							if(null != order && null != order.getCity()){
								boolean isHave = ArrayUtils.contains(peContractCity, order.getCity());
								if(isHave){
									//符合适用城市
									agreement.setIsPeContract(1);//是否为绩效合同 1:是,2或空:否
									agreement.setPeContractBasic(peContractBasic);//基本工资占比
									agreement.setPeContractPerformance(peContractPerformance);//绩效工资占比
									result = true;
								}
							}
						}
					}
				}
			}else{
				Agreement agr = this.reAgreementMapper.queryIsPeContract(agreement.getId());
				if(null != agr && null != agr.getIsPeContract() && 1 == agr.getIsPeContract()){
					agreement.setIsPeContract(agr.getIsPeContract());
			    	agreement.setPeContractBasic(agr.getPeContractBasic());
					agreement.setPeContractPerformance(agr.getPeContractPerformance());
					result = true;
				}
			}
			LogHelper.info(getClass()+".checkIsPeContract()","绩效合同结果："+agreement.getIsPeContract()+"/"+agreement.getPeContractBasic()+"/"+agreement.getPeContractPerformance());
		} catch (Exception e) {
			LogHelper.error(getClass(), "获取绩效合同配置异常"+e.getMessage());
		}
		return result;
	}
	
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Boolean checkIsPeContract(Agreement agreement) {
		Boolean result = false;//默认
		agreement.setIsPeContract(2);//默认
		agreement.setPeContractBasic(null);//默认
		agreement.setPeContractPerformance(null);//默认
		try {
			Boolean isPerformanceContract = true;//关于是否执行绩效合同配置
			if(isPerformanceContract){
				if(null == agreement.getId()){
					//新增操作
						if(null != agreement.getOrderId()){
							Order order = this.reAgreementMapper.loadOrder(agreement.getOrderId());
							if(null != order && null != order.getCity()){
								AgreementPerformance apf = this.reAgreementMapper.queryAgreementPerformance(order.getCity());
								if(apf != null){
									//符合适用城市
									agreement.setIsPeContract(1);//是否为绩效合同 1:是,2或空:否
									agreement.setPeContractBasic(apf.getPeContractBasic());//基本工资占比
									agreement.setPeContractPerformance(apf.getPeContractPerformance());//绩效工资占比
									LogHelper.info(getClass()+".checkIsPeContract()","绩效合同配置："+order.getCity()+"/"+apf.getPeContractBasic()+"/"+apf.getPeContractPerformance()+"/"+apf.getStartTime()+"/"+apf.getEndTime());
									result = true;
								}else{
									LogHelper.info(getClass()+".checkIsPeContract()","绩效合同结果：未获取城市配置,城市"+order.getCity());
								}
							}else{
								LogHelper.info(getClass()+".checkIsPeContract()","绩效合同结果：未获取对应订单或订单城市为空");
							}
						}else{
							LogHelper.info(getClass()+".checkIsPeContract()","绩效合同结果：合同订单id为空");
						}
				}else{
					//修改操作
					Agreement agr = this.reAgreementMapper.queryIsPeContract(agreement.getId());
					if(null != agr && null != agr.getIsPeContract() && 1 == agr.getIsPeContract()){
						agreement.setIsPeContract(agr.getIsPeContract());
				    	agreement.setPeContractBasic(agr.getPeContractBasic());
						agreement.setPeContractPerformance(agr.getPeContractPerformance());
						result = true;
					}
				}
			}
			LogHelper.info(getClass()+".checkIsPeContract()","绩效合同结果："+agreement.getIsPeContract()+"/"+agreement.getPeContractBasic()+"/"+agreement.getPeContractPerformance());
		} catch (Exception e) {
			LogHelper.error(getClass(), "获取绩效合同配置异常"+e.getMessage());
		}
		return result;
	}

	/**
	 * 根据订单ID，修改单次合同审核状态
	 *
	 * @param agreement
	 * @Author zhanghao
	 * @Date 20190212
	 */
	@Override
	public void changeOnceAgreementAuditStatus(Agreement agreement) {
		wrAgreementMapper.changeOnceAgreementAuditStatus(agreement);
	}

	/**
	 * 驳回单次合同
	 *
	 * @param agreement
	 * @Author zhanghao
	 * @Date 20190212
	 */
	@Override
	public void turnDownOneceAgreement(Agreement agreement) {
		wrAgreementMapper.changeOnceAgreementAuditStatus(agreement);
	}

	/**
	 * 单次合同审核通过
	 *
	 * @param agreement
	 * @Author zhanghao
	 * @Date 20190212
	 */
	@Override
	public void auditPassOnceAgreement(Agreement agreement) {
		wrAgreementMapper.changeOnceAgreementAuditStatus(agreement);
	}

}
