package com.emotte.order.gentlemanSignature.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.exception.BaseException;
import com.emotte.gentlemanSignature.core.controller.LocalSignController;
import com.emotte.gentlemanSignature.core.extra.AbstractSignatory;
import com.emotte.gentlemanSignature.core.model.GSResult;
import com.emotte.gentlemanSignature.core.model.LocalSign_Signatory;
import com.emotte.gentlemanSignature.core.model.LocalSign_fileType1or2;
import com.emotte.gentlemanSignature.date.BaseDateUtil;
import com.emotte.gentlemanSignature.enums.EnumConstant.DatePatterns;
import com.emotte.gentlemanSignature.http.HttpClient;
import com.emotte.gentlemanSignature.properties.GSPropertiesUtil;
import com.emotte.kernel.third.kafka.KafkaFactory;
import com.emotte.order.gentlemanSignature.Util;
import com.emotte.order.gentlemanSignature.mapper.reader.ReJudementContractMapper;
import com.emotte.order.gentlemanSignature.mapper.reader.ReOrderAgreementGsMapper;
import com.emotte.order.gentlemanSignature.mapper.reader.ReQueryAgreementPreviewMapper;
import com.emotte.order.gentlemanSignature.mapper.writer.WrOrderAgreementGsMapper;
import com.emotte.order.gentlemanSignature.mapper.writer.WrUpdateContractStatusMapper;
import com.emotte.order.gentlemanSignature.model.AgreementGs;
import com.emotte.order.gentlemanSignature.service.OrderAgreementGsService;
import com.emotte.order.order.mapper.reader.ReAgreementMapper;
import com.emotte.order.order.mapper.writer.WrAgreementMapper;
import com.emotte.order.order.model.Agreement;
import com.emotte.order.util.JsonUtil;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONObject;
@Service("orderAgreementGsService")
@Transactional
public class OrderAgreementGsServiceImpl implements OrderAgreementGsService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private ReOrderAgreementGsMapper reOrderAgreementGsMapper;
	
	@Resource
	private WrOrderAgreementGsMapper wrOrderAgreementGsMapper;

	@Resource
	private LocalSignController LocalSignController;
	
	@Resource
	private ReAgreementMapper reAgreementMapper;
	
	@Resource
	private WrAgreementMapper wrAgreementMapper;
	
	@Resource
	private WrUpdateContractStatusMapper wrUpdateContractStatusMapper;
	
	@Resource
	private ReJudementContractMapper reJudementContractMapper;
	
	@Resource
	private ReQueryAgreementPreviewMapper reQueryAgreementPreviewMapper;
	
	@Override
	public int insertOrderAgreementGs(AgreementGs agreementGs) {
		int i = wrOrderAgreementGsMapper.insertOrderAgreementGs(agreementGs);
		return i;
	}

	@Override
	public int updateOrderAgreementGs(AgreementGs agreementGs) {
		int i = wrOrderAgreementGsMapper.updateOrderAgreementGs(agreementGs);		
		return i;
	}

	@Override
	public List<AgreementGs> queryOrderAgreementGs(AgreementGs agreementGs) {
		List<AgreementGs> a = reOrderAgreementGsMapper.queryOrderAgreementGs(agreementGs);
		return a;
	}

	@Override
	public Map<String,Object> contractRemark(Agreement agreementCR,Long role,String signatoriesCR) {
		GSResult gsResult = null;
		JSONObject result = new JSONObject();
		Map<String,Object> map = new HashMap<String,Object>();
		LocalSign_fileType1or2 localSign = new LocalSign_fileType1or2();
		int i=0;
		//删除签名
	try {
		AgreementGs gsOne = new AgreementGs();
		gsOne.setContactId(agreementCR.getId());
		gsOne.setRole(role);
		gsOne.setValid((long)2);
		i=wrOrderAgreementGsMapper.updateOrderAgreementGs(gsOne);
		//整理数据	
		if(i>0){
			localSign.setSignatories(signatoriesCR);
			localSign.setReturnType(2);
			localSign.setContractName(agreementCR.getId().toString());
			localSign.setFileType(1);
			localSign.setSignApplyHashFlag(0);
		//	signatureController.signatureProcess(localSign, request, response, role);
		}
		 //签章开始	
		if(null!=localSign.getContractName()) {

			Agreement agreement = new Agreement();
			agreement.setId(Long.valueOf(localSign.getContractName()));
			List<Agreement> agreements = reAgreementMapper.queryAgreement(agreement);
			if(null!=agreements && agreements.size()!=0) {
				String fileName = agreements.get(0).getNormalContactFile();
				localSign.setFileData(fileName);
				String signatories =localSign.getSignatories();
				if(null!=signatories || !"".equals(signatories)) {
					List<LocalSign_Signatory> signatorys_ =JsonUtil.jsonToList(signatories, LocalSign_Signatory.class);
					AbstractSignatory as = new AbstractSignatory() {
						@Override
						public List<LocalSign_Signatory> signatories(List<LocalSign_Signatory> signatorys) throws Exception {
							return signatorys;
						}
					};
					if(signatorys_.get(0).getSignImg()==null || signatorys_.get(0).getSignImg()==""){
					   //signatorys_.get(0).setSignaturePicturePath("http://erp.95081.com/img/sig/updateSig.png");
						signatorys_.get(0).setSignaturePicturePath("http://erp.95081.com/img/img/2018/07/16/4fb073d5004b4f5eafeec09f36f90982.png");
					}
					localSign.setSignatories(as.loadToJsonArray(signatorys_));
				}
				gsResult = LocalSignController.process(LocalSignController.createCommonParam(localSign));
				if (null != gsResult && gsResult.isSuccess()) {
					Object data = gsResult.getData();
					if (null != data) {
						MorphDynaBean MorphDynaBean = (MorphDynaBean) data;
						if (null != MorphDynaBean && null != MorphDynaBean.get("signData")) {
							String IPFromSig = GSPropertiesUtil.getProperties("IPFromSig");
							String filePathFromSig = GSPropertiesUtil.getProperties("filePathFromSig");

							final String tempFilePath = MorphDynaBean.get("signData").toString();
							
							String copyFileUsingFileChannel = GSPropertiesUtil.getProperties("copyFileUsingFileChannel");
							
							String destPathEnd = Util.Instance.getPath()+"/"+agreements.get(0).getAgreementCode()+".pdf";
							final String destPath =  filePathFromSig+destPathEnd;
							
							HttpClient.doPost(copyFileUsingFileChannel, new HashMap<String,Object>() {{
								put("sourcePath", tempFilePath);
								put("destPath",destPath);
							}});
							agreement.setNormalContactFile(IPFromSig+destPathEnd);
							wrAgreementMapper.updateAgreement(agreement);
							map.put("signData", IPFromSig+destPathEnd);
							map.put("signContract", true);
						}
					} else {
						throw new Exception("签章数据异常");
					}
				} else {
					throw new Exception("签章未成功");
				}
			}
		}
		 //更改状态
		int j=0;
		if(map!=null&& map.get("signContract").equals(true)){
			j=wrUpdateContractStatusMapper.updateContractStatus(agreementCR);
			if(i>0&&j>0){
				map.put("wucs",true);
			}else{
				map.put("wucs",false);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	
	@Override
	public JSONObject signature(LocalSign_fileType1or2 localSign,AgreementGs agreementGs, String base64ImgStr,
			Agreement agreement, Long role) throws Exception {
		JSONObject result = new JSONObject();
		try {
			result.accumulate("success", false);
			
			List<AgreementGs> agreementGsLists  = reOrderAgreementGsMapper.queryOrderAgreementGs(agreementGs);
				
			byte[] bytes = base64ImgStr.getBytes();
			agreementGs.setBase64image(bytes);
				
			if(agreementGsLists!=null && agreementGsLists.size()>0){
				agreementGs.setUpdateTime(BaseDateUtil.Instance.DateToStr(DatePatterns.PATTERN1,new Date()));
				wrOrderAgreementGsMapper.updateOrderAgreementGs(agreementGs);
			}else{	
				agreementGs.setCreateTime(BaseDateUtil.Instance.DateToStr(DatePatterns.PATTERN1,new Date()));
				wrOrderAgreementGsMapper.insertOrderAgreementGs(agreementGs);
			}
			//dealStatus(agreement);
			dealStatus(agreement);
			result = dealSignature(localSign,agreement,base64ImgStr);
			result.put("agreement", agreement);
			result.put("base64ImgStr", base64ImgStr);
		} catch (Exception e) {
			throw new BaseException(e);
		}
		return result;
	}

	private Agreement dealStatus1(Agreement agreement)throws BaseException {
		Agreement query = new Agreement();
		query.setId(agreement.getId());
		Agreement judement = reJudementContractMapper.queryContract(query);
		if(null != judement) {
			if(null!=agreement.getElecClientState() 
					&& agreement.getElecClientState()==2
					&& null!= judement.getElecServeState()
					&& judement.getElecServeState()==2){
				
				agreement.setElecOtherState(3);
				
			}else{
				agreement.setAgreementState(6);
			}
			
			if(null!=agreement.getElecServeState() 
					&& agreement.getElecServeState()==2
					&& null!= judement.getElecClientState()
					&& judement.getElecClientState()==2){
				
				query.setElecOtherState(3);
			}else {
				agreement.setAgreementState(6);
			}
			agreement.setNormalContactFile(judement.getNormalContactFile());
			agreement.setAgreementCode(judement.getAgreementCode());
		}
		return agreement;
	}
	
	public Agreement dealStatus(Agreement agreement) throws Exception {
		Agreement judement = reJudementContractMapper.judementContract(agreement);
		int judementServe=judement.getElecServeState();//2
		int judementClient=judement.getElecClientState();//1
		
		//根据页面传值来判断
		if(null!=agreement.getElecClientState() && agreement.getElecClientState()==1) {
			if(judementServe==1) {
				//不可能
			}else if(judementServe==2){
				//不可能
			}else if(judementServe==3){
				//不可能
			}
		}else if(null!=agreement.getElecClientState() && agreement.getElecClientState()==2) {
            if(judementServe==1) {
            	agreement.setAgreementState(6);
			}else if(judementServe==2){
				agreement.setElecOtherState(3);//2

			}else if(judementServe==3){
				//不处理
			}
		}else if(null!=agreement.getElecClientState() && agreement.getElecClientState()==3) {
            if(judementServe==1) {
				agreement.setCheckStatus(1);
				agreement.setElecOtherState(5);
				//更新接口已处理
				//agreement.setAgreementState(1);
			}else if(judementServe==2){
				agreement.setCheckStatus(1);
				agreement.setElecOtherState(5);
			}else if(judementServe==3){
				agreement.setCheckStatus(1);
				agreement.setElecOtherState(5);
			}
		}else if(null!=agreement.getElecServeState() && agreement.getElecServeState()==1) {
			 if(judementClient==1) {
				//不处理
			 }else if(judementClient==2){
				//不处理
			 }else if(judementClient==3){
				//不处理
			 }
		}else if(null!=agreement.getElecServeState() && agreement.getElecServeState()==2) {
			 if(judementClient==1) {
					agreement.setAgreementState(6);
					
			 }else if(judementClient==2){
				 agreement.setElecOtherState(3);
			 }else if(judementClient==3){
					//不处理
			 }
		}else if(null!=agreement.getElecServeState() && agreement.getElecServeState()==3) {
			 if(judementClient==1) {
					agreement.setCheckStatus(1);
					agreement.setElecOtherState(5);
			 }else if(judementClient==2){
					agreement.setCheckStatus(1);
					agreement.setElecOtherState(5);
			 }else if(judementClient==3){
				 agreement.setCheckStatus(1);
				 agreement.setElecOtherState(5);
			 }
		}else {
			//处理hash保全
		}
		agreement.setNormalContactFile(judement.getNormalContactFile());
		agreement.setAgreementCode(judement.getAgreementCode());
		return agreement;
	}
	
	public JSONObject dealSignature(LocalSign_fileType1or2 localSign,Agreement agreement, final String base64ImgStr)throws Exception {
		
		JSONObject result = new JSONObject();
		result.accumulate("success", false);
		
		if(null!=localSign.getContractName()) {
			
			if(null!=agreement) {
				String fileName = agreement.getNormalContactFile();
				localSign.setFileData(fileName);
			
				String signatories =localSign.getSignatories();
				if(null!=signatories || !"".equals(signatories)) {
					
					List<LocalSign_Signatory> signatorys_ =JsonUtil.jsonToList(signatories, LocalSign_Signatory.class);
                	AbstractSignatory as = new AbstractSignatory() {
						@Override
						public List<LocalSign_Signatory> signatories(List<LocalSign_Signatory> signatorys) throws Exception {
							if(base64ImgStr.contains("data:image/png;base64,")) {
								final String base64ImgStrTemp = base64ImgStr.substring(base64ImgStr.indexOf(",")+1,base64ImgStr.length());
								signatorys.get(0).setSignImg(base64ImgStrTemp);
							}else {
								throw new BaseException("签章图片不合格");
							}
							return signatorys;
						}
					};
					localSign.setSignatories(as.loadToJsonArray(signatorys_));
				}
				
				GSResult gsResult = LocalSignController.process(LocalSignController.createCommonParam(localSign));
				
				if (null != gsResult && gsResult.isSuccess()) {
					Object data = gsResult.getData();
					if (null != data) {
						MorphDynaBean MorphDynaBean = (MorphDynaBean) data;
						if (null != MorphDynaBean && null != MorphDynaBean.get("signData")) {
							String IPFromSig = GSPropertiesUtil.getProperties("IPFromSig");
							String filePathFromSig = GSPropertiesUtil.getProperties("filePathFromSig");

							final String tempFilePath = MorphDynaBean.get("signData").toString();
							
							String copyFileUsingFileChannel = GSPropertiesUtil.getProperties("copyFileUsingFileChannel");
							
							String destPathEnd = Util.Instance.getPath()+"/"+agreement.getAgreementCode()+".pdf";
							final String destPath =  filePathFromSig+destPathEnd;
							
							HttpClient.doPost(copyFileUsingFileChannel, new HashMap<String,Object>() {{
								put("sourcePath", tempFilePath);
								put("destPath",destPath);
							}});
							
							agreement.setNormalContactFile(IPFromSig+destPathEnd);
							log.info("--合同状态--"+agreement.getElecClientState()+"----"+agreement.getElecOtherState()+"----"+agreement.getElecServeState()+"----"+agreement.getNormalContactFile()+"-----"+agreement.getId()+"-----");
							wrAgreementMapper.updateAgreement(agreement);
							result.put("signData", IPFromSig+destPathEnd);
							result.remove("success");
							result.put("success", true);
							if(null!=agreement.getElecOtherState() && 3==agreement.getElecOtherState()) {
								log.info("KAFKA开始放合同编号，给魏恭宇用------合同编号----->"+agreement.getAgreementCode());
								KafkaFactory.getProducerHandler().produce("agreement_sign","",agreement.getAgreementCode());
							}
						}
					} else {
						throw new BaseException("签章数据异常,--->"+gsResult.getMsg());
					}
				} else {
					throw new BaseException("签章未成功");
				}
			}
		}
		return result;
	}
	
	
}
