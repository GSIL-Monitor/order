package com.emotte.order.order.controller;

import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.wildhorse.server.auth.annotation.UserAnnotation;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.alibaba.fastjson.JSON;
import com.emotte.dubbo.activities.service.AutoGenDubboCardService;
import com.emotte.dubbo.credit.CreditService;
import com.emotte.dubbo.installment.CustomerInfoService;
import com.emotte.dubbo.installment.InstallmentService;
import com.emotte.dubbo.installment.ReissueAgreementService;
import com.emotte.gentlemanSignature.core.model.LocalSign_fileType0;
import com.emotte.kernel.file.rw.IFileRWService;
import com.emotte.kernel.file.rw.impl.excel.ExcelServiceImpl;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.order.model.Agreement;
import com.emotte.order.order.model.BaseCity;
import com.emotte.order.order.model.DataDictionaryModel;
import com.emotte.order.order.service.AgreementService;
import com.emotte.order.util.Constants;
import com.emotte.order.util.CookieReaderUtil;
import com.emotte.order.util.LoginCookieInfo;
import com.emotte.order.util.PropertiesHelper;
import com.emotte.server.util.CookieUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/agreement")
public class AgreementController extends BaseController {

	@Resource
	private AgreementService agreementService;
	
	@Resource
	private CreditService creditService;
	
	@Resource
	private InstallmentService installmentService;

	@Resource
	private CustomerInfoService customerInfoService;

	@Resource
	private AutoGenDubboCardService autoGenDubboCardService;
	
	@Resource
	private ReissueAgreementService reissueAgreementService;
	

	@RequestMapping(value = "/loadAgreement", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView loadAgreement(HttpServletRequest request,
			HttpServletResponse response, Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			Agreement agreement = this.agreementService.loadAgreement(id);
			if (agreement == null) {
				msg = BaseConstants.NULL; 

			} else {
				msg = Constants.SCUUESS;
			}
			request.setAttribute("agreement", agreement);
		} catch (Exception e) {
			log.error("loadAgreement:" + e);
			msg = Constants.FAIL;
		}
		request.setAttribute("msg", msg);
		mv.setViewName("finance/load_agreement");
		return mv;
	}

	
	@RequestMapping(value = "/queryAgreement", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryAgreement(HttpServletRequest request,
			HttpServletResponse response, Agreement agreement, Page page)
			throws Exception {
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");

		List<Agreement> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.agreementService.queryAgreement(agreement, page);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryAgreement:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/insertAgreement", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void insertAgreement(HttpServletRequest request,
			HttpServletResponse response, Agreement agreement,LocalSign_fileType0 ls) throws Exception {
		JSONObject jsonObject = new JSONObject();
		String msgText=null;
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");
		try {
			LoginCookieInfo cookie = CookieReaderUtil.cookieReader(request);
			agreement.setCreateBy(cookie.getId());
			//获取乙方公司名称
			if (agreement.getPartyB()!= null && !"".equals(agreement.getPartyB())) {
				String partyB = URLDecoder.decode(agreement.getPartyB(),"UTF-8");
				agreement.setPartyB(partyB);
			}
			//获取服务场所
			if (agreement.getServiceAddress()!= null && !"".equals(agreement.getServiceAddress())) {
				String serviceAddress = URLDecoder.decode(agreement.getServiceAddress(),"UTF-8");
				agreement.setServiceAddress(serviceAddress);
			}
			//获取服务场所回显字段
			if (agreement.getServiceAddressEcho()!= null && !"".equals(agreement.getServiceAddressEcho())) {
				String serviceAddressEcho = URLDecoder.decode(agreement.getServiceAddressEcho(),"UTF-8");
				agreement.setServiceAddressEcho(serviceAddressEcho);
			}
			//获取甲方地址
			if (agreement.getCustomerAddress()!= null && !"".equals(agreement.getCustomerAddress())) {
				String customerAddress = URLDecoder.decode(agreement.getCustomerAddress(),"UTF-8");
				agreement.setCustomerAddress(customerAddress);
			}
			//获取甲方地址回显字段
			if (agreement.getCustomerAddressEcho()!= null && !"".equals(agreement.getCustomerAddressEcho())) {
				String customerAddressEcho = URLDecoder.decode(agreement.getCustomerAddressEcho(),"UTF-8");
				agreement.setCustomerAddressEcho(customerAddressEcho);
			}
			agreement.setAgreementCode(agreement.getMobile() + getRodomFourChar());
			this.agreementService.insertAgreement(agreement,ls,request,response);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("insertAgreement:" + e);
			msg = Constants.FAIL;
			msgText = e.getMessage();
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("msgText", msgText);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * weixd
	 * @param request
	 * @param response
	 * @param json
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertAgreementNew", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void insertAgreementNew(HttpServletRequest request, HttpServletResponse response, String data) throws Exception {
		LogHelper.info(getClass(), "新增合同接口开始执行,参数信息data:" + data);
		String signatories1 = 
				 "[{'identityType':'11','fullName':'管家帮','email':'guanjiabang@www.bccto.me','identityCard':'916400007508102806',"+
					"'pageChapteJson':[{'page':'4','chaptes':[{'offsetX':'0.25','offsetY':'0.59'}]},{'page':'-1','chaptes':[{'offsetX':'0.25','offsetY':'0.92'}]}]}]";
		String signatories2 = 
				  "[{'identityType':'11','fullName':'管家帮','email':'guanjiabang@www.bccto.me','identityCard':'916400007508102806',"+
					"'pageChapteJson':[{'page':'-1','chaptes':[{'offsetX':'0.25','offsetY':'0.67'}]}]}]";
		Agreement agreement = null;
		LocalSign_fileType0 ls = null;
		String msgText=null;
		JSONObject fromObject = JSONObject.fromObject(data);
		JSONObject agreementJson = fromObject.getJSONObject("agreement");
		JSONObject signJson = fromObject.getJSONObject("sign");
		JSONObject customerAddressEchoData = agreementJson.getJSONObject("customerAddressEcho");
		JSONObject serviceAddressEchoData = agreementJson.getJSONObject("serviceAddressEcho");
		
		agreement = (Agreement) JSONObject.toBean(agreementJson, Agreement.class);
		agreement.setCustomerAddressEcho(customerAddressEchoData.toString());
		agreement.setServiceAddressEcho(serviceAddressEchoData.toString());
		ls = (LocalSign_fileType0) JSONObject.toBean(signJson, LocalSign_fileType0.class);
		Integer isCollection = agreement.getIsCollection();//平台代付1,客户直付2
		Integer advancePeriod = agreement.getAdvancePeriod();//1:月,2:季,3:半年,4:年);(平台代付：1:一次性预付劳务费,2:唯品会白条,3:招行分期,4:其他支付方式,5:海金保理白条
		if(advancePeriod == 5 && isCollection == 7) {
			ls.setSignatories(signatories1);
		}else {
			ls.setSignatories(signatories2);
		}
		JSONObject jsonObject = new JSONObject();
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");
		try {
			//获取乙方公司名称 
			if (agreement.getPartyB()!= null && !"".equals(agreement.getPartyB())) {
				String partyB = URLDecoder.decode(agreement.getPartyB(),"UTF-8");
				agreement.setPartyB(partyB);
			}
			//获取服务场所
			if (agreement.getServiceAddress()!= null && !"".equals(agreement.getServiceAddress())) {
				String serviceAddress = URLDecoder.decode(agreement.getServiceAddress(),"UTF-8");
				agreement.setServiceAddress(serviceAddress);
			}
			//获取服务场所回显字段
			if (agreement.getServiceAddressEcho()!= null && !"".equals(agreement.getServiceAddressEcho())) {
				String serviceAddressEcho = URLDecoder.decode(agreement.getServiceAddressEcho(),"UTF-8");
				agreement.setServiceAddressEcho(serviceAddressEcho);
			}
			//获取甲方地址
			if (agreement.getCustomerAddress()!= null && !"".equals(agreement.getCustomerAddress())) {
				String customerAddress = URLDecoder.decode(agreement.getCustomerAddress(),"UTF-8");
				agreement.setCustomerAddress(customerAddress);
			}
			//获取甲方地址回显字段
			if (agreement.getCustomerAddressEcho()!= null && !"".equals(agreement.getCustomerAddressEcho())) {
				String customerAddressEcho = URLDecoder.decode(agreement.getCustomerAddressEcho(),"UTF-8");
				agreement.setCustomerAddressEcho(customerAddressEcho);
			}
			agreement.setAgreementCode(agreement.getMobile() + getRodomFourChar());
			JSONObject reaulst = checkAgreementStartOrEndDate(agreement);
			String checkStr = reaulst.getString("msg");
			if("00".equals(checkStr)){
				this.agreementService.insertAgreementNew(agreement,ls,request,response);
				this.msg = Constants.SCUUESS;
			}else{
				this.msg = Constants.FAIL;
				msgText = reaulst.getString("msgText");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msg = Constants.FAIL;
			msgText = e.getMessage();
			log.error("新增合同接口,insertAgreementNew方法错误" + ",orderId:" + agreement.getOrderId() + ",msgText:" + msgText + "msg:" + msg + ",错误信息:" + ExceptionUtils.getStackTrace(e));
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("msgText", msgText);
		responseSendMsg(response, jsonObject.toString());
	}

	/**
	 * weixd
	 * @param request
	 * @param response
	 * @param json
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryAgreementById", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryAgreementById(HttpServletRequest request,
			HttpServletResponse response, String json) throws Exception {
		Agreement agreement = null;
		LocalSign_fileType0 ls = null;
		JSONObject fromObject = JSONObject.fromObject(json);
		JSONObject agreementJson = fromObject.getJSONObject("agreement");
		agreement = (Agreement) JSONObject.toBean(agreementJson, Agreement.class);
		JSONObject jsonObject = new JSONObject();
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");
		Long id;
		String url = null;
		try {
			id = agreement.getId();
			Map map = this.agreementService.queryAgreementById(id);
			url = (String) map.get("NORMALCONTACTFILE");
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("insertAgreement:" + e);
			e.printStackTrace();
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("url", url);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/updateAgreement", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void updateAgreement(HttpServletRequest request,
			HttpServletResponse response, Agreement agreement,LocalSign_fileType0 ls) throws Exception {
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");
		JSONObject jsonObject = new JSONObject();
		String msgText=null;
		try {
			LoginCookieInfo cookie = CookieReaderUtil.cookieReader(request);
			agreement.setUpdateBy(cookie.getId());
			agreement.setCreateBy(cookie.getId());
			//获取乙方公司名称
			if (agreement.getPartyB()!= null && !"".equals(agreement.getPartyB())) {
				String partyB = URLDecoder.decode(agreement.getPartyB(),"UTF-8");
				agreement.setPartyB(partyB);
			}
			//获取服务场所
			if (agreement.getServiceAddress()!= null && !"".equals(agreement.getServiceAddress())) {
				String serviceAddress = URLDecoder.decode(agreement.getServiceAddress(),"UTF-8");
				agreement.setServiceAddress(serviceAddress);
			}
			//获取服务场所回显字段
			if (agreement.getServiceAddressEcho()!= null && !"".equals(agreement.getServiceAddressEcho())) {
				String serviceAddressEcho = URLDecoder.decode(agreement.getServiceAddressEcho(),"UTF-8");
				agreement.setServiceAddressEcho(serviceAddressEcho);
			}
			//获取甲方地址
			if (agreement.getCustomerAddress()!= null && !"".equals(agreement.getCustomerAddress())) {
				String customerAddress = URLDecoder.decode(agreement.getCustomerAddress(),"UTF-8");
				agreement.setCustomerAddress(customerAddress);
			}
			//获取甲方地址回显字段
			if (agreement.getCustomerAddressEcho()!= null && !"".equals(agreement.getCustomerAddressEcho())) {
				String customerAddressEcho = URLDecoder.decode(agreement.getCustomerAddressEcho(),"UTF-8");
				agreement.setCustomerAddressEcho(customerAddressEcho);
			}
			this.agreementService.updateAgreement(agreement,ls,request,response);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("updateAgreement:" + e);
			msg = Constants.FAIL;
			msgText = e.getMessage();
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("msgText", msgText);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * weixd
	 * @param request
	 * @param response
	 * @param json
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateAgreementNew", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void updateAgreementNew(HttpServletRequest request,
			HttpServletResponse response, String data) throws Exception {
		LogHelper.info(getClass(), "updateAgreementNew修改合同接口开始执行,参数信息data:" + data);
		String signatories1 = 
				 "[{'identityType':'11','fullName':'管家帮','email':'guanjiabang@www.bccto.me','identityCard':'916400007508102806',"+
					"'pageChapteJson':[{'page':'4','chaptes':[{'offsetX':'0.25','offsetY':'0.59'}]},{'page':'-1','chaptes':[{'offsetX':'0.25','offsetY':'0.92'}]}]}]";
		String signatories2 = 
				  "[{'identityType':'11','fullName':'管家帮','email':'guanjiabang@www.bccto.me','identityCard':'916400007508102806',"+
					"'pageChapteJson':[{'page':'-1','chaptes':[{'offsetX':'0.25','offsetY':'0.67'}]}]}]";
		Agreement agreement = null;
		LocalSign_fileType0 ls = null;
		String msgText=null;
		JSONObject fromObject = JSONObject.fromObject(data);
		JSONObject agreementJson = fromObject.getJSONObject("agreement");
		JSONObject signJson = fromObject.getJSONObject("sign");
		JSONObject customerAddressEchoData = agreementJson.getJSONObject("customerAddressEcho");
		JSONObject serviceAddressEchoData = agreementJson.getJSONObject("serviceAddressEcho");
		
		agreement = (Agreement) JSONObject.toBean(agreementJson, Agreement.class);
		agreement.setCustomerAddressEcho(customerAddressEchoData.toString());
		agreement.setServiceAddressEcho(serviceAddressEchoData.toString());
		ls = (LocalSign_fileType0) JSONObject.toBean(signJson, LocalSign_fileType0.class);
		Integer isCollection = agreement.getIsCollection();//平台代付1,客户直付2
		Integer advancePeriod = agreement.getAdvancePeriod();//1:月,2:季,3:半年,4:年);(平台代付：1:一次性预付劳务费,2:唯品会白条,3:招行分期,4:其他支付方式,5:海金保理白条
		
		if(advancePeriod == 5 && isCollection == 7) {
			ls.setSignatories(signatories1);
		}else {
			ls.setSignatories(signatories2);
		}
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");
		JSONObject jsonObject = new JSONObject();
		try {
			//获取乙方公司名称
			if (agreement.getPartyB()!= null && !"".equals(agreement.getPartyB())) {
				String partyB = URLDecoder.decode(agreement.getPartyB(),"UTF-8");
				agreement.setPartyB(partyB);
			}
			//获取服务场所
			if (agreement.getServiceAddress()!= null && !"".equals(agreement.getServiceAddress())) {
				String serviceAddress = URLDecoder.decode(agreement.getServiceAddress(),"UTF-8");
				agreement.setServiceAddress(serviceAddress);
			}
			//获取服务场所回显字段
			if (agreement.getServiceAddressEcho()!= null && !"".equals(agreement.getServiceAddressEcho())) {
				String serviceAddressEcho = URLDecoder.decode(agreement.getServiceAddressEcho(),"UTF-8");
				agreement.setServiceAddressEcho(serviceAddressEcho);
			}
			//获取甲方地址
			if (agreement.getCustomerAddress()!= null && !"".equals(agreement.getCustomerAddress())) {
				String customerAddress = URLDecoder.decode(agreement.getCustomerAddress(),"UTF-8");
				agreement.setCustomerAddress(customerAddress);
			}
			//获取甲方地址回显字段
			if (agreement.getCustomerAddressEcho()!= null && !"".equals(agreement.getCustomerAddressEcho())) {
				String customerAddressEcho = URLDecoder.decode(agreement.getCustomerAddressEcho(),"UTF-8");
				agreement.setCustomerAddressEcho(customerAddressEcho);
			}
			JSONObject reaulst = checkAgreementStartOrEndDate(agreement);
			String checkStr = reaulst.getString("msg");
			if("00".equals(checkStr)){
				this.agreementService.updateAgreementNew(agreement,ls,request,response);
				msg = Constants.SCUUESS;
			}else{
				this.msg = Constants.FAIL;
				msgText = reaulst.getString("msgText");
			}
		} catch (Exception e) {
			msg = Constants.FAIL;
			msgText = e.getMessage();
			log.error("updateAgreementNew修改合同接口开始执行,返回值:{msg:" + msg + ",msgText:" + e.getMessage() + "},异常信息" + ExceptionUtils.getStackTrace(e));
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("msgText", msgText);
		responseSendMsg(response, jsonObject.toString());
	}
	@RequestMapping(value = "/queryContract", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryContractPDF(HttpServletRequest request,HttpServletResponse response,String agreementModel,String previewFlag,String contractId,String homePage,LocalSign_fileType0 ls)
			throws Exception {
		String fileName = "";
		JSONObject jsonObject = new JSONObject();
		try {
			fileName = this.agreementService.queryContractPDF(request,response,previewFlag, contractId,homePage,ls,agreementModel);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("queryAgreement:" + e);
			msg = Constants.FAIL;
			e.printStackTrace();
		}
		if(fileName != null){
			jsonObject.accumulate("msg", msg);
			jsonObject.accumulate("fileName", fileName);
			responseSendMsg(response, jsonObject.toString());
		}
	}

	
	@RequestMapping(value = "/loadAgreementCookie", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void loadAgreementCookie(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		LoginCookieInfo cookie = new LoginCookieInfo();
		String contractHeaderStr = "";
		String orgAddrStr = "";
		try {
			cookie = CookieReaderUtil.cookieReader(request);
			if (!"".equals(cookie.getContractHeader()) && !"null".equals(cookie.getContractHeader()) && cookie.getContractHeader() != null) {
				contractHeaderStr = cookie.getContractHeader();
			} 
			if (!"".equals(cookie.getOrgAddr()) && !"null".equals(cookie.getOrgAddr()) && cookie.getOrgAddr() != null) {
				orgAddrStr = cookie.getOrgAddr();
			} 
		} catch (Exception e) {
			log.error("loadAgreementCookie:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("contractHeader", contractHeaderStr);
		jsonObject.accumulate("orgAddr", orgAddrStr);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 合同审核查询列表
	 * @author zhangshun
	 * @param request
	 * @param response
	 * @param agreement
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryCheckAgreement_listPage", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryCheckAgreement_listPage(HttpServletRequest request,HttpServletResponse response,Agreement agreement,Page page)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			list = this.agreementService.queryCheckAgreement_listPage(agreement, page);
			if(list.size() > 0 ){
				msg = Constants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryCheckAgreement_listPage:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 审核合同
	 * @author zhangshun
	 * @param request
	 * @param response
	 * @param agreement
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkAgreement", method = { RequestMethod.POST,RequestMethod.GET })
	public void checkAgreement(HttpServletRequest request,HttpServletResponse response,Agreement agreement)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Long loginId = Long.valueOf(CookieUtils.getJSONKey(request, "id").toString());
			agreement.setUpdateBy(loginId);
			agreement.setCheckBy(loginId);
			this.agreementService.checkAgreement(agreement);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("checkAgreement:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 新增合同页查询服务站
	 * @author zhangshun
	 * @param request
	 * @param response
	 * @param agreement
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryServiceStation", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryServiceStation(HttpServletRequest request,HttpServletResponse response,Agreement agreement)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			list = this.agreementService.queryServiceStation(agreement);
			if(list.size() > 0 ){
				msg = Constants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryServiceStation:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 按订单id查询己面试成功的服务人员信息
	 * @param request
	 * @param response
	 * @param orderId
	 */
	@RequestMapping(value="/queryPersonnel",method={RequestMethod.POST,RequestMethod.GET})
	public void queryPersonnel(HttpServletRequest request,HttpServletResponse response,Long orderId){
		JSONObject jsonObject = new JSONObject();
		List<HashMap<String, Object>> list =  new ArrayList<HashMap<String, Object>>();
		try {
			list = this.agreementService.queryPersonnel(orderId);
			if(list.size() > 0){
				msg = Constants.SCUUESS;
			}else{
				msg = Constants.FAIL;
			}
		} catch (Exception e) {
			log.error("queryPersonnel"+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 验证身份证
	 * @param request
	 * @param response
	 * @param name
	 * @param cardNum
	 */
	@RequestMapping(value="/checkIdCard",method={RequestMethod.POST,RequestMethod.GET})
	public void checkIdCard(HttpServletRequest request,HttpServletResponse response,String name, String cardNum){
		final Long flagId = 2L;
		JSONObject jsonObject = new JSONObject();
		String result = "";
		try {
			result = this.creditService.checkIdCard(name, cardNum, flagId);
			if(!result.equals("") && result != null){
				msg = Constants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("checkIdCard"+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("result", result);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * @author zhangshun
	 * @param request
	 * @param response
	 * @param baseCity
	 */
	@RequestMapping(value="/queryCitys",method={RequestMethod.POST,RequestMethod.GET})
	public void queryCitys(HttpServletRequest request,HttpServletResponse response,BaseCity baseCity ){
		JSONObject jsonObject = new JSONObject();
		List<BaseCity> list = new ArrayList<BaseCity>();
		try {
			list = this.agreementService.queryCitys(baseCity);
			if(list !=null && list.size() > 0){
				msg = Constants.SCUUESS;
			}else{
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryCitys"+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 新增查询当前订单可复制合同
	 * @author zhangshun
	 * @param request
	 * @param response
	 * @param agreement
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryCanCopyAgreement", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryCanCopyAgreement(HttpServletRequest request,HttpServletResponse response, Agreement agreement)
			throws Exception {
		List<Agreement> list = new ArrayList<Agreement>();
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.agreementService.queryCanCopyAgreement(agreement);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryCanCopyAgreement:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * 新查询乙方合同头和地址
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryAgreementHeader", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryAgreementHeader(HttpServletRequest request,HttpServletResponse response)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		Long deptId =  Long.valueOf(CookieUtils.getJSONKey(request, "orgId").toString());
		Map<String, String> result  = new HashMap<String, String>();
		try {
			result = this.agreementService.queryAgreementHeader(deptId);
			if (result.size() > 0 && result != null) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryAgreementHeader:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("result", result);
		responseSendMsg(response, jsonObject.toString());
	}

	
	/**
	 * 导出符合条件审核成功的合同
	 * @param request 请求
	 * @param response 响应
	 * @param agreement 合同
	 * @throws Exception
	 */
	@UserAnnotation(methodName="导出符合条件审核成功的合同")
	@RequestMapping(value = "/exportContract", method = { RequestMethod.POST,RequestMethod.GET })
	public void exportContract(HttpServletRequest request,HttpServletResponse response, Agreement agreement)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		String path=request.getSession().getServletContext().getRealPath("/");
		String name="dataFile/contract/"+request.getSession().getId() + "_" +System.currentTimeMillis()+"_cpe.xls";
		String filePath=path+name;
		try{
			// 设置标题
			String titles = PropertiesHelper.getValue("props/export.properties", "passContract");
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) JSON.parse(titles);
			String ids = request.getParameter("ids");
			if (!StringUtils.isBlank(ids)) agreement.setIds_(ids.split(",")); // 存储前台传递过来的ids
			List<Map<String, Object>> data = this.agreementService.queryExportCpeList(agreement);
			if (null != data && !data.isEmpty()) {
				data.add(0, map); // 存入标题
				IFileRWService<List<Map<String, String>>, List<Map<String, Object>>> fileService = new ExcelServiceImpl();
				fileService.write2File(data, filePath); // 将内容存入excel
				jsonObject.accumulate("url", name); // 将url返回到页面，以便于下载使用
				msg = Constants.SCUUESS;
			} else {
				msg = "只能导出审核通过的合同！";
			}
		}catch(Exception e){
			log.error("AgreementExport:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	@RequestMapping(value = "/updateAgreementTwo", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void updateAgreementTwo(HttpServletRequest request,
			HttpServletResponse response, Agreement agreement) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			LoginCookieInfo cookie = CookieReaderUtil.cookieReader(request);
			agreement.setUpdateBy(cookie.getId());
			//获取服务场所
			if (agreement.getServiceAddress()!= null && !"".equals(agreement.getServiceAddress())) {
				String serviceAddress = URLDecoder.decode(agreement.getServiceAddress(),"UTF-8");
				agreement.setServiceAddress(serviceAddress);
			}
			//获取服务场所回显字段
			if (agreement.getServiceAddressEcho()!= null && !"".equals(agreement.getServiceAddressEcho())) {
				String serviceAddressEcho = URLDecoder.decode(agreement.getServiceAddressEcho(),"UTF-8");
				agreement.setServiceAddressEcho(serviceAddressEcho);
			}
			//获取甲方地址
			if (agreement.getCustomerAddress()!= null && !"".equals(agreement.getCustomerAddress())) {
				String customerAddress = URLDecoder.decode(agreement.getCustomerAddress(),"UTF-8");
				agreement.setCustomerAddress(customerAddress);
			}
			//获取甲方地址回显字段
			if (agreement.getCustomerAddressEcho()!= null && !"".equals(agreement.getCustomerAddressEcho())) {
				String customerAddressEcho = URLDecoder.decode(agreement.getCustomerAddressEcho(),"UTF-8");
				agreement.setCustomerAddressEcho(customerAddressEcho);
			}
			this.agreementService.updateAgreementTwo(agreement);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("updateAgreementTwo:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	

	@RequestMapping(value = "/getDictionaryInfo", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void getDictionaryInfo(HttpServletRequest request,
			HttpServletResponse response, DataDictionaryModel dictObj) throws Exception {
		
		JSONObject jsonObject = new JSONObject();
		try {
			List<DataDictionaryModel> list = new ArrayList<DataDictionaryModel>();
			/*LoginCookieInfo cookie = CookieReaderUtil.cookieReader(request);
			dictObj.setUpdateBy(cookie.getId());*/
			
			list = agreementService.getDictionaryInfo(dictObj,request);
			jsonObject.put("data", list);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("getDicByFCode:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	/*public String getRodomFourChar(){
		ArrayList list = new ArrayList();
        for (char c = 'a'; c <= 'z'; c++) {
            list.add(c);
        }
        String str = "";
        for (int i = 0; i < 4; i++) {
            int num = (int) (Math.random() * 26);
            str = str + list.get(num);
        }
        System.out.println(str);
        return str.toUpperCase();
	}*/
	
	/**
	 * 2018-07-11
	 * 
	 */
	public String getRodomFourChar(){
		ArrayList list = new ArrayList();
        for (char c = 'a'; c <= 'z'; c++) {
        	if(c != 'o' && c != 'i'){
        		list.add(c);
        	}
        }
        String str = "";
        for (int i = 0; i < 4; i++) {
            int num = (int) (Math.random() * list.size());
            str = str + list.get(num);
        }
        System.out.println(str.toUpperCase());
        return str.toUpperCase();
	}
	
	
	@RequestMapping(value = "/showCustomerManager", method = { RequestMethod.POST, RequestMethod.GET })
	public void showCustomerManager(HttpServletRequest request, HttpServletResponse response,Long userId) throws Exception {
		List<Map<String, String>> list=null;
		JSONObject jsonObject = new JSONObject();
		try {
			 list = this.agreementService.showCustomerManager(userId);
			if (null != list) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("deleteBankcard:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	
	public static void main(String[] args) throws ParseException {
		//AgreementController agreementController = new AgreementController();
		
		//System.out.println(agreementController.getRodomFourChar());
		AgreementController ac = new AgreementController();
		ac.getRodomFourChar();
	}
	
	@RequestMapping(value = "/checkCardName", method = { RequestMethod.POST, RequestMethod.GET })
	public void checkCardName(HttpServletRequest request, HttpServletResponse response,Long orderId) throws Exception {
		String cardName = "";
		JSONObject jsonObject = new JSONObject();
		try {
			cardName = this.agreementService.checkCardName(orderId);
			if (null != cardName && !"".equals(cardName)) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("deleteBankcard:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("cardName", cardName);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	
	
	
	/**
	 * 海金更新签约时间-信息查询
	 * @param request
	 * @param response
	 * @param agreement
	 * @param page
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/queryAgreementcreateDate", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryAgreementcreateDate(HttpServletRequest request,HttpServletResponse response, Agreement agreement)
			throws Exception {
		
		Agreement agrt=null; Agreement agrt1 = null;
		
		Agreement agrt3=this.agreementService.loadAgreementcreatime(agreement.getId());
		
		JSONObject jsonObject = new JSONObject();
		try {
			agrt = this.agreementService.queryAgreementcreatime(agrt3.getAgreementCode());
			agrt1= this.agreementService.queryAgreementcreatime1(agrt3.getAgreementCode());
			if (agrt!=null) {
				msg = Constants.SCUUESS;
				jsonObject.accumulate("msg", msg);
				jsonObject.accumulate("data", agrt);
				jsonObject.accumulate("updateflag", 1);
			}else if(agrt1!=null){
				
				msg = Constants.SCUUESS;
				jsonObject.accumulate("msg", msg);
				jsonObject.accumulate("data", agrt1);
				jsonObject.accumulate("updateflag", 2);
			}else {
				msg = Constants.FAIL;
				jsonObject.accumulate("msg", msg);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("queryAgreementcreateDate:" + e);
			msg = Constants.FAIL;
		}
		
		responseSendMsg(response, jsonObject.toString());
	}*/
	@RequestMapping(value = "/queryAgreementcreateDate", method = { RequestMethod.POST, 
			RequestMethod.GET }) 
	public void queryAgreementcreateDate(HttpServletRequest request,HttpServletResponse response, Agreement agreement)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Agreement agrt=this.agreementService.loadAgreementcreatime(agreement.getId());
			
			if (agrt!=null) {
				msg = Constants.SCUUESS;
				jsonObject.accumulate("msg", msg);
				jsonObject.accumulate("data", agrt);
				jsonObject.accumulate("updateflag", 1);
			}else {
				msg = Constants.FAIL;
				jsonObject.accumulate("msg", msg);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("queryAgreementcreateDate:" + e);
			msg = Constants.FAIL;
		}
		
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	
	
	
	/**
	 * 海金更新签约时间
	 * @param request
	 * @param response
	 * @param agreement
	 * @param page
	 * @throws Exception
	 */
	/*@RequestMapping(value = "/updateAgreementcreatedate", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void updateAgreementcreatedate(HttpServletRequest request,
			HttpServletResponse response, Agreement agreement) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			LoginCookieInfo cookie = CookieReaderUtil.cookieReader(request);
			 
			
			agreement.setUpdateBy(cookie.getId());
			agreement.setLog("当前操作人【" + cookie.getId() + "】旧的时间为【" + agreement.getCreatetime() + "】新的时间为" + agreement.getCreatetimeinfo());
			if(agreement.getUpdateflag().equals("1")) {
				this.agreementService.updateAgreementCreatime(agreement);
			}else {
				this.agreementService.updateAgreementCreatimeNew(agreement);
			}
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("updateAgreementcreatedate:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}*/
	@RequestMapping(value = "/updateAgreementcreatedate", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void updateAgreementcreatedate(HttpServletRequest request,
			HttpServletResponse response, Agreement agreement) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			LoginCookieInfo cookie = CookieReaderUtil.cookieReader(request);
			 
			
			agreement.setUpdateBy(cookie.getId());
			agreement.setLog("当前操作人【" + cookie.getId() + "】旧的时间为【" + agreement.getCreatetime() + "】新的时间为" + agreement.getCreatetimeinfo());
			if(agreement.getUpdateflag().equals("1")) {
				this.agreementService.updateAgreementCreatime(agreement);
			}
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("updateAgreementcreatedate:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	@RequestMapping(value = "/submitAuth", method = { RequestMethod.POST, RequestMethod.GET })
	public void submitAuth(HttpServletRequest request, HttpServletResponse response,Long userMoble,String customName,String bankName,String bankAccountNum,String customIdCard,Long applyType) throws Exception {
		LogHelper.info(getClass(),"联动四要素"+userMoble+","+customName+","+bankAccountNum+","+customIdCard+","+applyType);
		JSONObject jsonObject = new JSONObject();
 		String json="{'bankMobile':'"+userMoble+"','customName':'"+customName+"','bankAccountNum':'"+bankAccountNum+"','customIdCard':'"+customIdCard+"','applyType':'"+applyType+"'}";
		String code=null;
		String message=null;
		try {
			String result = installmentService.validateHaiJinBaoLi(json);
			if (null != result) {
				JSONObject jsonResult=JSONObject.fromObject(result);
				code=(String) jsonResult.get("code");
				message=(String) jsonResult.get("message");
				if("0".equals(code)){
					code = Constants.SCUUESS;
				}else{
					code = BaseConstants.FAIL;
				}
			} else {
				code = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("submitAuth:" + e);
			code = Constants.FAIL;
		}
		jsonObject.accumulate("code", code);
		jsonObject.accumulate("msg", message);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	
	
	
	/**
	 * 合同状态的修改完成后，调用自动校验生成卡券。
	 * @param orderId 订单编号
	 * 操作人：周鑫
	 * 2018年9月12日下午5:19:35
	 */
	@RequestMapping(value = "/updateAgreementType", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void updateAgreementType(HttpServletRequest request,
			HttpServletResponse response,Long orderId,Long contractId) throws Exception {
		try {
			//System.out.println(orderId);
			//调用自动校验生成卡券方法
			autoGenDubboCardService.checkCondtionByOrderId(orderId.toString(),contractId.toString());
		} catch (Exception e) {
			log.error("updateAgreementType:" + e);
		}
	}
	
	/**
	 * 海金合同给客户发送代扣协议
	 * @param request
	 * @param response
	 * @param orderId
	 * @throws Exception
	 */
	@RequestMapping(value = "/reissueAgreementByOrderId", method = { RequestMethod.POST, RequestMethod.GET })
	public void reissueAgreementByOrderId(HttpServletRequest request, HttpServletResponse response,Long orderId) throws Exception {
		LogHelper.info(getClass(),"海金合同给客户发送代扣协议,参数订单id"+orderId);
		JSONObject jsonObject = new JSONObject();
		String code = null;
		String result = null;
		try {
			result = this.reissueAgreementService.reissueAgreementByOrderId(orderId+"");
			if (null != result && !"".equals(result)) {
				code = Constants.SCUUESS;
				LogHelper.info(getClass(),"海金合同给客户发送代扣协议,参数订单id"+orderId+",返回"+result);
			} else {
				code = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("reissueAgreementByOrderId:" + e);
			code = Constants.FAIL;
		}
		jsonObject.accumulate("code", code);
		jsonObject.accumulate("result", result);
		responseSendMsg(response, jsonObject.toString());
	}

	/**
	 * 根据订单ID查询合同是否存在
	 *
	 * @param response		响应对象
	 * @param request		请求对象
	 * @param orderId		订单ID
	 * @Author zhanghao
	 * @Date 20181114
     */
	@RequestMapping("/findAgreementByOrderId")
	public void findAgreementByOrderId(HttpServletResponse response,HttpServletRequest request,Long orderId){
		JSONObject jsonObject = new JSONObject();
		String agreementCode = "";
		try {
			List<Agreement> agreements = agreementService.findAgreementByOrderIdOrAgreementId("ORDER_ID",orderId);
			if(CollectionUtils.isNotEmpty(agreements)){
				for(Agreement agreement : agreements){
					if(agreement.getAgreementState() != 3 || agreement.getAgreementState() != 5){
						msg = Constants.NULL;
						agreementCode = agreement.getAgreementCode();
						break;
					}else{
						msg = Constants.SCUUESS;
					}
				}
			}else{
				msg = Constants.SCUUESS;
			}
		} catch (Exception e) {
			log.error("findAgreementByOrderId:"+e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("agreementCode",agreementCode);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 校验合同是否存在
	 *
	 * @param request
	 * @param response
	 * @param orderId
	 * @Author zhanghao
	 * @Date 20181115
     */
	@RequestMapping("/checkAgreementHas")
	public void checkAgreementHas(HttpServletRequest request,HttpServletResponse response,Long orderId,String agreementCode){
		//返回json对象
		JSONObject jsonObject = new JSONObject();
		//返回消息
		msg = "00";
		Agreement agreement = new Agreement();
		agreement.setOrderId(orderId);
		agreement.setAgreementCode(agreementCode);
		//根据订单ID查询合同信息
		List<Agreement> agreements = agreementService.findAgreementListByOrderId(agreement);
		/**
		 * 新增合同校验规则：
		 * 1、不存在有效合同，不存在有效海金合同：返回值"00"
		 * 		合同类型无校验：
		 * 	        海金：合同期限间隔不得小于3个自然月
		 * 2、存在有效合同，不存在有效海金合同：返回值"01"
		 * 		允许新增合同类型：
		 * 			非海金：新增合同起始时间为:（当前日期前7日） 至（第一份有效合同结束时间）；结束时间为:第一份有效合同的结束时间
		 * 			海金：新增合同起始时间为:（当前日期前7日） 至（第一份有效合同结束时间前3个月）；结束时间为:第一份有效合同的结束时间
		 * 3、存在有效合同，存在有效海金合同：返回值"02"
		 * 		允许新增合同类型：
		 * 			非海金：新增合同起始时间为:（当前日期-7日） 至（第一份有效合同结束时间）；结束时间为:第一份有效合同的结束时间
		 * 4、海金合同服务时间不得小于三个月
		 */
		if(CollectionUtils.isNotEmpty(agreements)){
			for(Agreement textAgreement : agreements){
				if(textAgreement.getAdvancePeriod() != 5){//存在有效合同，不存在有效海金合同
					msg = "01";
				}else if(textAgreement.getAdvancePeriod() == 5){//存在有效合同，存在有效海金合同
					msg = "02";
					break;
				}
			}
		}
		if("01".equals(msg) || "02".equals(msg)){
			agreement = agreementService.findEndTimeByOrderId(orderId);
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("agreement",agreement);
		LogHelper.info(getClass(),"checkAgreementHas方法执行结束,orderId:"+orderId+",agreementCode:"+agreementCode+",msg:"+msg);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 *
	 * 操作人：周鑫
	 * 2018年11月15日下午1:35:12
	 */
	@RequestMapping(value = "/deleteIsGold", method = { RequestMethod.POST, RequestMethod.GET })
	public void deleteIsGold(HttpServletRequest request,
			HttpServletResponse response, Agreement agreement) throws Exception {
		LogHelper.info(getClass(),"合同id"+agreement.getId());
		JSONObject jsonObject = new JSONObject();
		String code = null;
		String result = null;
		try {
			int  count= this.agreementService.findAgreementByOrderIdGold(agreement.getId());
			//是海金合同
			if (count==1) {
				//查询出code
				Map<String, String> map = this.agreementService.queryOrderIdAndId(agreement.getOrderId(), agreement.getId());
				//调用接口判断是否清除海金申请
				String cancelApply = customerInfoService.cancelApply(map.toString());
				JSONObject fromObject = jsonObject.fromObject(cancelApply);
				Object object = fromObject.get("code");
				Object msg = fromObject.get("msg");
				//清除申请成功
				if (object!=null&&(object.toString()).equals("0")) {
					code="0";
					result="清除海金合同成功";
				}else{
					//清除申请失败
					code="1";
					result=msg.toString();
				}
			}else {
				code = Constants.SCUUESS;
				result="不是海金合同";
			}
		} catch (Exception e) {
			LogHelper.error(getClass(), e.toString());
			code = Constants.FAIL;
		}
		jsonObject.accumulate("code", code);
		jsonObject.accumulate("result", result);
		responseSendMsg(response, jsonObject.toString());
	}

	/**
	 * 新增合同校验规则：
	 * 1、不存在有效合同，不存在有效海金合同：
	 * 		合同类型无校验：
	 * 	        海金：合同期限间隔不得小于3个自然月
	 * 2、存在有效合同，不存在有效海金合同：
	 * 		允许新增合同类型：
	 * 			非海金：新增合同起始时间为:（当前日期前7日） 至（第一份有效合同结束时间）；结束时间为:第一份有效合同的结束时间
	 * 			海金：新增合同起始时间为:（当前日期前7日） 至（第一份有效合同结束时间前3个月）；结束时间为:第一份有效合同的结束时间
	 * 3、存在有效合同，存在有效海金合同：
	 * 		允许新增合同类型：
	 * 			非海金：新增合同起始时间为:（当前日期-7日） 至（第一份有效合同结束时间）；结束时间为:第一份有效合同的结束时间
	 * 4、海金合同服务时间不得小于三个月
	 * @Author zhanghao
	 * @Date 20181210
	 */
	private JSONObject checkAgreementStartOrEndDate(Agreement agreement) {
		LogHelper.info(getClass(),"checkAgreementStartOrEndDate校验方法开始,订单ID:"+agreement.getOrderId()+",开始时间:"+agreement.getEffectDate()+",结束时间:"+agreement.getFinishDate());
		//创建返回值对象
		JSONObject jsonObject = new JSONObject();
		String msg = "00";
		String msgText = "";

		//创建合同结束时间封装对象
		Agreement agreementEndDate = new Agreement();

		//获取订单信息
		Long orderId = agreement.getOrderId();//获取订单ID
		Integer advancePeriod = agreement.getAdvancePeriod();//1:月,2:季,3:半年,4:年);(平台代付：1:一次性预付劳务费,2:唯品会白条,3:招行分期,4:其他支付方式,5:海金保理白条
		Integer isCollection = agreement.getIsCollection();//平台代付1,客户直付2

		//根据订单ID查询有无历史合同数据
		List<Agreement> agreements = agreementService.findAgreementListByOrderId(agreement);

		//根据历史合同类型获取结果
		String result = "00";
		if(CollectionUtils.isNotEmpty(agreements)){
			for(Agreement textAgreement : agreements){
				if(textAgreement.getAdvancePeriod() != 5){//存在有效合同，不存在有效海金合同
					result = "01";
				}else if(textAgreement.getAdvancePeriod() == 5){//存在有效合同，存在有效海金合同
					result = "02";
					break;
				}
			}
		}

		//合同信息校验
		try {
			//获取时间信息
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String currentDateStr = format.format(new Date());
			Date currentDate = format.parse(currentDateStr);
			//当前时间前7d
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(Calendar.DAY_OF_MONTH,-7);
			long sevenDayBeforCurrentTime = calendar.getTimeInMillis();//当前时间前7d毫秒值
			String sevenDayBeforCurrentDate = format.format(sevenDayBeforCurrentTime);//当前时间前7d"yyyy-MM-dd"
			//新增合同日期信息
			String effectDate = agreement.getEffectDate().split(" ")[0];//"yyyy-MM-dd"
			String finishDate = agreement.getFinishDate().split(" ")[0];//"yyyy-MM-dd"
			long startTime = format.parse(effectDate).getTime();//新增合同生效时间毫秒值
			long endTime = format.parse(finishDate).getTime();//新增合同结束时间毫秒值
			//获取上一份合同结束时间
			String lastDate = "";
			long lastDateTime = 0;//该订单前一份合同结束时间毫秒值
			long threeMonthBeforeLastTime = 0;//该订单前一份合同结束前3个月时间毫秒值
			if("01".equals(result) || "02".equals(result)){
				agreementEndDate = agreementService.findEndTimeByOrderId(orderId);
				lastDate = agreementEndDate.getEffectDate().split(" ")[0];
				lastDateTime = format.parse(lastDate).getTime();
				calendar.clear();
				calendar.setTime(new Date(lastDateTime));
				calendar.add(Calendar.MONTH,-3);
				threeMonthBeforeLastTime = calendar.getTimeInMillis();
			}

			//校验规则
			if("01".equals(result)){//存在有效合同，不存在有效海金合同
				if(isCollection == 7 && advancePeriod == 5){//新增海金合同
					if(startTime < sevenDayBeforCurrentTime){//起始时间早于当前时间7d前
						msg = "01";
						msgText = "合同起始时间不得早于" + sevenDayBeforCurrentDate;
					}else if(startTime > threeMonthBeforeLastTime){//起始时间晚于该订单前一份合同结束前3个月时间
						String threeMonthBeforeLastDate = format.format(new Date(threeMonthBeforeLastTime));
						msg = "01";
						msgText = "合同起始时间不得晚于" + threeMonthBeforeLastDate;
					}else if(endTime != lastDateTime){//结束时间不等于该订单前一份合同结束时间
						msg = "01";
						msgText = "合同结束时间只能选择" + lastDate;
					}else{
						msg = "00";
					}
				}else{
					if(startTime < sevenDayBeforCurrentTime){//起始时间早于当前时间7d前
						msg = "01";
						msgText = "合同起始时间不得早于" + sevenDayBeforCurrentDate;
					}else if(startTime > lastDateTime){//起始时间晚于该订单前一份合同结束时间
						msg = "01";
						msgText = "合同起始时间不得晚于" + lastDate;
					}else if(endTime != lastDateTime){//结束时间不等于该订单前一份合同结束时间
						msg = "01";
						msgText = "合同结束时间只能选择" + lastDate;
					}else{
						msg = "00";
					}
				}
			}else if("02".equals(result)){//存在有效合同，存在有效海金合同
				if(isCollection == 7 && advancePeriod == 5){//新增海金合同
					msg = "01";
					msgText = "订单已存在有效的海金合同,只能新增非海金合同!";
				}else{
					if(startTime < sevenDayBeforCurrentTime){//起始时间早于当前时间7d前
						msg = "01";
						msgText = "合同起始时间不得早于" + sevenDayBeforCurrentDate;
					}else if(startTime > lastDateTime){//起始时间晚于该订单前一份合同结束时间
						msg = "01";
						msgText = "合同起始时间不得晚于" + lastDate;
					}else if(endTime != lastDateTime){//结束时间不等于该订单前一份合同结束时间
						msg = "01";
						msgText = "合同结束时间只能选择" + lastDate;
					}else{
						msg = "00";
					}
				}
			}else if("00".equals(result)){//新增合同
				msg = "00";
				if(isCollection == 7 && advancePeriod == 5) {//新增海金合同
					calendar.clear();
					calendar.setTime(new Date(endTime));
					calendar.add(Calendar.MONTH,-3);
					long threeMonthBeforEndTime = calendar.getTimeInMillis();
					if(startTime > threeMonthBeforEndTime){
						msg = "01";
						msgText = "海金合同期限间隔不得小于3个月!";
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			msg = "01";
			msgText = "校验程序错误!";
		}
		LogHelper.info(getClass(),"checkAgreementStartOrEndDate校验方法结束,orderId:" + agreement.getOrderId() + ",msg:" + msg + ",msgText:" + msgText);
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("msgText",msgText);
		return jsonObject;
	}
	
	
	/**
	 * 
	 * @Title: updateInsurance
	 * @Description: 点击签署同意保险委托书
	 * @author dengyouqian@95081.com
	 * @date 2018年12月25日
	 * @param request
	 * @param response
	 * @param agreement
	 * @throws Exception
	 * @return void
	 * @throws
	 */
	@RequestMapping(value = "/updateInsurance", method = { RequestMethod.POST, RequestMethod.GET })
	public void updateInsurance(HttpServletRequest request,
			HttpServletResponse response, Agreement agreement) throws Exception {
		LogHelper.info(getClass(),"参数是-"+agreement.toString());
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");
		JSONObject msg = new JSONObject();
		try {
			if(null!=agreement && null!=agreement.getId() && !"".equals(agreement.getId())
					&& null!=agreement.getInsuranceEntrust() && !"".equals(agreement.getInsuranceEntrust())){
				msg = agreementService.updateInsurance(agreement);
				
			}else{
				msg.accumulate("msg", Constants.FAIL);
				msg.accumulate("msgInfo", "缺少参数");
			}
		} catch (Exception e) {
			LogHelper.error(getClass(), e.toString());
			msg.accumulate("msg", Constants.FAIL);
			msg.accumulate("msgInfo", "操作失败");
		}
		responseSendMsg(response, msg.toString());
	}
	
	
	/**
	 * 修改海金合同的审核状态
	 * 操作人：周鑫
	 * 2019年1月2日下午1:33:43
	 */
	@RequestMapping(value = "/updateAgreementCheckStatus", method = { RequestMethod.POST, RequestMethod.GET })
	public void updateAgreementCheckStatus(HttpServletRequest request,
			HttpServletResponse response, Long checkAgreementId) throws Exception {
		LogHelper.info(getClass(),"Id:"+checkAgreementId);
		Long loginId = Long.valueOf(CookieUtils.getJSONKey(request, "id").toString());
		JSONObject jsonObject = new JSONObject();
		String code = null;
		String result = null;
		try {
			//int  count= this.agreementService.findAgreementByOrderIdGold(checkAgreementId);
			//是海金合同
			//if (count==1) {
				agreementService.updateAgreementCheckStatus(checkAgreementId,loginId);
				code = Constants.SCUUESS;
			//}
		} catch (Exception e) {
			LogHelper.error(getClass(), e.toString());
			code = Constants.FAIL;
		}
		jsonObject.accumulate("code", code);
		jsonObject.accumulate("result", result);
		responseSendMsg(response, jsonObject.toString());
	}

	/**
	 * 单次服务订单查询三方协议详情
	 *
	 * @param request
	 * @param response
	 * @param orderId
	 * @Author zhanghao
	 * @Date 20190111
     */
	@RequestMapping("/queryAgreementServerOneDetail")
	public void queryAgreementServerOneDetail(HttpServletRequest request,HttpServletResponse response,Long orderId){
		JSONObject jsonObject = new JSONObject();
		Agreement agreement = new Agreement();
		try {
			agreement = agreementService.queryAgreementServerOneDetail(orderId);
			if(agreement != null){
				msg = Constants.SCUUESS;
			}else{
				msg = Constants.NULL;
			}
		} catch (Exception e) {
			msg = Constants.FAIL;
			log.error("queryAgreementServerOneDetail方法执行失败,参数信息:{orderId:" + orderId + ",msg:" + msg + "},错误信息:" + ExceptionUtils.getStackTrace(e));
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("agreement",agreement);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 保存单次服务合同
	 *
	 * @param request
	 * @param response
	 * @param agreement
	 * @Author zhanghao
	 * @Date 20190111
     */
	@RequestMapping("/addOneceAgreement")
	public void addOneceAgreement(HttpServletRequest request,HttpServletResponse response,Agreement agreement){
		JSONObject jsonObject = new JSONObject();
		Long loginId = Long.valueOf(CookieUtils.getJSONKey(request, "id").toString());
		agreement.setCreateBy(loginId);
		agreement.setUpdateBy(loginId);
		agreement.setValid(1);
		agreement.setCardType(4);
		try {
			agreementService.addOneceAgreement(agreement);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			msg = Constants.FAIL;
			log.error("addOneceAgreement方法执行失败,参数信息:{orderId:" + agreement.getOrderId() + "},错误信息:" + ExceptionUtils.getStackTrace(e));
		}
		jsonObject.accumulate("msg",msg);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 修改单次合同回显
	 *
	 * @param request
	 * @param response
	 * @param id
	 * @Author zhanghao
	 * @Date 20190114
     */
	@RequestMapping("/findAgreementById")
	public void findAgreementById(HttpServletRequest request,HttpServletResponse response,Long id){
		JSONObject jsonObject = new JSONObject();
		Agreement agreement = new Agreement();
		try {
			agreement = agreementService.findAgreementById(id);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			msg = Constants.FAIL;
			log.error("findAgreementById方法执行失败,参数信息:{agreementId:" + id + "},错误信息:" + ExceptionUtils.getStackTrace(e));
		}
		jsonObject.accumulate("msg",msg);
		jsonObject.accumulate("agreement",agreement);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 更新单次合同
	 *
	 * @param request
	 * @param response
	 * @param agreement
	 * @Author zhanghao
	 * @Date 20190114
     */
	@RequestMapping("/updateOneceAgreement")
	public void updateOneceAgreement(HttpServletRequest request,HttpServletResponse response,Agreement agreement){
		JSONObject jsonObject = new JSONObject();
		Long loginId = Long.valueOf(CookieUtils.getJSONKey(request, "id").toString());
		agreement.setUpdateBy(loginId);
		try {
			agreementService.updateOneceAgreement(agreement);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			msg = Constants.FAIL;
			log.error("findAgreementById方法执行失败,参数信息:{agreementId:" + agreement.getId() + "},错误信息:" + ExceptionUtils.getStackTrace(e));
		}
		jsonObject.accumulate("msg",msg);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 删除单次合同
	 *
	 * @param request
	 * @param response
     * @param id
	 * @Author zhanghao
	 * @Date 20190114
     */
	@RequestMapping("/deleteOnceAgreement")
	public void deleteOnceAgreement(HttpServletRequest request,HttpServletResponse response,Long id){
		JSONObject jsonObject = new JSONObject();
		Agreement agreement = new Agreement();
		Long loginId = Long.valueOf(CookieUtils.getJSONKey(request, "id").toString());
		agreement.setUpdateBy(loginId);
		agreement.setId(id);
		agreement.setValid(2);
		try {
			agreementService.deleteOnceAgreement(agreement);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			msg = Constants.FAIL;
			log.error("deleteOnceAgreement方法执行失败,参数信息:{agreementId:" + agreement.getId() + "},错误信息:" + ExceptionUtils.getStackTrace(e));
		}
		jsonObject.accumulate("msg",msg);
		responseSendMsg(response,jsonObject.toString());
	}



	/**
	 *
	 * @Description:  查询是否是海金合同
	 * @param request
	 * @param response
	 * @param checkAgreementId
	 * @throws Exception
	 * @return: void
	 * @author:ZhouXin
	 * @Date:2019年2月12日
	 * @throws
	 */
	@RequestMapping(value = "/queryKingAgreement", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryKingAgreement(HttpServletRequest request,
			HttpServletResponse response, Long checkAgreementId) throws Exception {
		LogHelper.info(getClass(),"Id:"+checkAgreementId);
		JSONObject jsonObject = new JSONObject();
		String code = null;
		int count = -1;
		try {
			count= this.agreementService.findAgreementByOrderIdGold(checkAgreementId);
			//是海金合同
			code = Constants.SCUUESS;
		} catch (Exception e) {
			LogHelper.error(getClass(), e.toString());
			code = Constants.FAIL;
		}
		jsonObject.accumulate("code", code);
		jsonObject.accumulate("count", count);
		responseSendMsg(response, jsonObject.toString());
	}

	/**
	 * 上传合同扫描件后，修改单次合同审核状态
	 *
	 * @param request
	 * @param response
	 * @param orderId
	 * @Author zhanghao
	 * @Date 20190212
     */
	@RequestMapping("/changeOnceAgreementAuditStatus")
	public void changeOnceAgreementAuditStatus(HttpServletRequest request,HttpServletResponse response,Long orderId){
		JSONObject jsonObject = new JSONObject();
		Agreement agreement = new Agreement();
		Long loginId = Long.valueOf(CookieUtils.getJSONKey(request,"id").toString());
		agreement.setUpdateBy(loginId);
		agreement.setOrderId(orderId);
		agreement.setCheckStatus(1);
		try {
			agreementService.changeOnceAgreementAuditStatus(agreement);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			msg = Constants.FAIL;
			log.error("changeOnceAgreementAuditStatus方法执行失败,参数信息:{orderId:" + orderId + "},错误信息:" + ExceptionUtils.getStackTrace(e));
		}
		jsonObject.accumulate("msg",msg);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 单次合同驳回
	 *
	 * @param request
	 * @param response
	 * @param agreement
	 * @Author zhanghao
	 * @Date 20190212
     */
	@RequestMapping("/turnDownOneceAgreement")
	public void turnDownOneceAgreement(HttpServletRequest request,HttpServletResponse response,Agreement agreement){
		JSONObject jsonObject = new JSONObject();
		Long loginId = Long.valueOf(CookieUtils.getJSONKey(request,"id").toString());
		agreement.setUpdateBy(loginId);
		agreement.setCheckStatus(3);
		try {
			agreementService.turnDownOneceAgreement(agreement);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			msg = Constants.FAIL;
			log.error("turnDownOneceAgreement方法执行失败,参数信息:{agreementId:" + agreement.getId() + "},错误信息:" + ExceptionUtils.getStackTrace(e));
		}
		jsonObject.accumulate("msg",msg);
		responseSendMsg(response,jsonObject.toString());
	}

	/**
	 * 单次合同审核通过
	 *
	 * @param request
	 * @param response
	 * @param agreement
	 * @Author zhanghao
	 * @Date 20190212
     */
	@RequestMapping("/auditPassOnceAgreement")
	public void auditPassOnceAgreement(HttpServletRequest request,HttpServletResponse response,Agreement agreement){
		JSONObject jsonObject = new JSONObject();
		Long loginId = Long.valueOf(CookieUtils.getJSONKey(request,"id").toString());
		agreement.setUpdateBy(loginId);
		agreement.setCheckStatus(2);
		try {
			agreementService.auditPassOnceAgreement(agreement);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			msg = Constants.FAIL;
			log.error("turnDownOneceAgreement方法执行失败,参数信息:{agreementId:" + agreement.getId() + "},错误信息:" + ExceptionUtils.getStackTrace(e));
		}
		jsonObject.accumulate("msg",msg);
		responseSendMsg(response,jsonObject.toString());
	}
}
