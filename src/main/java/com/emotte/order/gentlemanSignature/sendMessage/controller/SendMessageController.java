package com.emotte.order.gentlemanSignature.sendMessage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.dubbo.sms.SMSServiceDubbo;
import com.emotte.kernel.json.JsonUtil;
import com.emotte.order.gentlemanSignature.model.VerifyCodeModel;
import com.emotte.order.gentlemanSignature.service.UpdateContractStatusService;
import com.emotte.order.order.model.Agreement;
import com.emotte.order.order.service.AgreementService;
import com.emotte.order.util.ConfigComm;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/sendMessage")
public class SendMessageController extends BaseController {
	
	@Resource
	private SMSServiceDubbo sMSServiceDubbo;
	
	@Resource
	private UpdateContractStatusService updateContractStatusService;
	
	@Resource
	private AgreementService agreementService;
	
	//发送手机验证码
	@RequestMapping(value = "/sendVerifyCode", method = { RequestMethod.POST, RequestMethod.GET })
	public void sendVerifyCode(HttpServletRequest request, HttpServletResponse response
			,VerifyCodeModel verifyObj) throws Exception {
		log.info("SendMessageController------sendVerifyCode 发送验证码  执行开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");

		JSONObject jsonObject = new JSONObject();
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();
			
			if (StringUtils.isBlank(verifyObj.getMobile())) {
				throw new Exception("手机号不能为空");
			}
			if (StringUtils.isBlank(verifyObj.getSystem())) {
				throw new Exception("系统码不能为空");
			}
			if (StringUtils.isBlank(verifyObj.getTemplet())) {
				throw new Exception("短信模板不能为空");
			}
			if (StringUtils.isBlank(verifyObj.getChannel())) {
				throw new Exception("通道码不能为空");
			}
			
			map.put("mobiles", verifyObj.getMobile());
			map.put("templet", verifyObj.getTemplet()); // 模板码
			map.put("channel", verifyObj.getChannel()); // 通道码
			map.put("rate", "1");
			map.put("system", verifyObj.getSystem());
			map.put("defined", "1");
			map.put("params", new ArrayList<String>());
			String paramStr = JSONObject.fromObject(map).toString();
			String retStr = sMSServiceDubbo.send(paramStr);
			JSONObject retObj = JSONObject.fromObject(retStr);
			String str = retObj.getString("result");
			if (str.equals("success")) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.FAIL;
			}
		
			jsonObject.accumulate("msg", msg);
		} catch (Exception e) {
			log.error("sendVerifyCode:" + e);
			msg = BaseConstants.FAIL;
			jsonObject.accumulate("msg", msg);
		}
		log.info("SendMessageController------sendVerifyCode 发送验证码  执行结束");
		responseSendMsg(response, jsonObject.toString());
	}
	
	//较验 验证码
	@RequestMapping(value = "/checkVerifyCode", method = { RequestMethod.POST, RequestMethod.GET })
	public void checkVerifyCode(HttpServletRequest request, HttpServletResponse response
			,VerifyCodeModel verifyObj) throws Exception {
		log.info("SendMessageController------checkVerifyCode 校验 验证码  执行开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");

		JSONObject jsonObject = new JSONObject();
		try {
			
			Map<String, Object> map = new HashMap<String, Object>();
			if (StringUtils.isBlank(verifyObj.getMobile())) {
				throw new Exception("手机号不能为空");
			}
			if (StringUtils.isBlank(verifyObj.getSystem())) {
				throw new Exception("系统码不能为空");
			}
			if (StringUtils.isBlank(verifyObj.getTemplet())) {
				throw new Exception("短信模板不能为空");
			}
			if (StringUtils.isBlank(verifyObj.getChannel())) {
				throw new Exception("通道码不能为空");
			}
			if (StringUtils.isBlank(verifyObj.getVerifyCode())) {
				throw new Exception("验证码不能为空");
			}
			
			map.put("mobile", verifyObj.getMobile());
			map.put("code", verifyObj.getVerifyCode());
			map.put("system", verifyObj.getSystem());
			map.put("templet", verifyObj.getTemplet());
			map.put("defined", "1");
			map.put("channel", verifyObj.getChannel()); // 通道码
			map.put("rate", "1");
			JSONObject paramObj = JSONObject.fromObject(map);
			String retStr = sMSServiceDubbo.verify(paramObj.toString());
			JSONObject retObj = JSONObject.fromObject(retStr);
			String str = retObj.getString("result");
			if (str.equals("success")) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.FAIL;
			}
			jsonObject.accumulate("msg", msg);
		} catch (Exception e) {
			log.error("sendCompactMessage:" + e);
			msg = BaseConstants.FAIL;
			jsonObject.accumulate("msg", msg);
		}
		log.info("SendMessageController------checkVerifyCode 校验 验证码  执行结束");
		responseSendMsg(response, jsonObject.toString());
	}
	
	//发送短信链接   url为短链接
	@RequestMapping(value = "/sendPushMessage", method = { RequestMethod.POST, RequestMethod.GET })
	public void sendPushMessage(HttpServletRequest request, HttpServletResponse response
			,String jsonStr) throws Exception {
		log.info("SendMessageController------sendPushMessage 发送推送短信开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");
		log.info("参数是"+jsonStr);
		JSONObject jsonObject = new JSONObject();
		String info="";
		try {
			boolean sendFlag=false;
			Map<String, Object> temMap = JsonUtil.mapFromJson(jsonStr);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list = (List<Map<String, Object>>) temMap.get("baseList");
			
			for (Map<String, Object> map : list) {
				
				JSONObject messageParams = new JSONObject();
				String mobile = String.valueOf(map.get("mobile"));
				String messageUrl = String.valueOf(map.get("shortUrl"));
				String[] params = new String[] {messageUrl} ;
				
				messageParams.accumulate("channel", "sys");
				messageParams.accumulate("mobiles", mobile);
				messageParams.accumulate("rate", "1");
				messageParams.accumulate("templet", map.get("templet"));
				messageParams.accumulate("system", map.get("system"));
				messageParams.accumulate("params", params);
				String res = this.sMSServiceDubbo.send(messageParams.toString());
				
				JSONObject fromObject = JSONObject.fromObject(res);
				String str = fromObject.get("result").toString();
				if(null!=fromObject && !fromObject.isEmpty() && str.equals("success")){
					sendFlag = true;
					msg = BaseConstants.SCUUESS;
					info += "发送"+mobile+"成功";
				}else{
					info += "发送"+mobile+"失败";
				}
			}
		
			//有一条短信发送成功就将 合同 的电子签章其他状态   改为已推送
			if(sendFlag){
				Agreement agreement = new Agreement();
				agreement.setId(Long.valueOf(String.valueOf(temMap.get("compactId"))));
				List<Agreement> queryAgreement = agreementService.queryAgreement(agreement, page);
				if(queryAgreement !=null && queryAgreement.size()>0){
					Integer elecOtherState = queryAgreement.get(0).getElecOtherState();
					if(elecOtherState !=null && 3!=elecOtherState && 4!=elecOtherState){
						agreement.setElecOtherState(2);
						if(5==elecOtherState && queryAgreement.get(0).getElecClientState()==3){
							agreement.setElecClientState(1);
						}else if(5==elecOtherState && queryAgreement.get(0).getElecServeState()==3) {
							agreement.setElecServeState(1);
						}
						updateContractStatusService.updateContractStatus(agreement);
						msg = BaseConstants.SCUUESS;
					}
				}
			}else{
				msg = BaseConstants.FAIL;
			}
			jsonObject.accumulate("msg", msg);
			jsonObject.accumulate("info",info);
			
			log.info("SendMessageController------sendPushMessage 发送推送短信结束");
		} catch (Exception e) {
			log.error("sendPushMessage:" + e);
			msg = BaseConstants.FAIL;
			jsonObject.accumulate("info",e.getMessage());
			jsonObject.accumulate("msg", msg);
		}
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * weixd
	 * @param request
	 * @param response
	 * @param jsonStr
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendPushMessageNew", method = { RequestMethod.POST, RequestMethod.GET })
	public void sendPushMessageNew(HttpServletRequest request, HttpServletResponse response
			,String jsonStr) throws Exception {
		jsonStr = "{\"baseList\":[{\"mobile\":\"18301019183\",\"shortUrl\":\"http://t.erp.95081.com/sms/su/reyqqy\",\"system\":\"other\",\"templet\":\"other_744\"},{\"mobile\":\"19910380834\",\"shortUrl\":\"http://t.erp.95081.com/sms/su/reyqqy\",\"system\":\"other\",\"templet\":\"other_744\"},{\"mobile\":\"18301019183\",\"shortUrl\":\"http://t.erp.95081.com/sms/su/reyqqy\",\"system\":\"other\",\"templet\":\"other_744\"}],\"compactId\":\"493927658809832\"}";
		log.info("SendMessageController------sendPushMessage 发送推送短信开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");
		log.info("参数是"+jsonStr);
		JSONObject jsonObject = new JSONObject();
		String info="";
		try {
			boolean sendFlag=false;
			Map<String, Object> temMap = JsonUtil.mapFromJson(jsonStr);
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			list = (List<Map<String, Object>>) temMap.get("baseList");
			
			for (Map<String, Object> map : list) {
				
				JSONObject messageParams = new JSONObject();
				String mobile = String.valueOf(map.get("mobile"));
				String messageUrl = String.valueOf(map.get("shortUrl"));
				String[] params = new String[] {messageUrl} ;
				
				messageParams.accumulate("channel", "sys");
				messageParams.accumulate("mobiles", mobile);
				messageParams.accumulate("rate", "1");
				messageParams.accumulate("templet", map.get("templet"));
				messageParams.accumulate("system", map.get("system"));
				messageParams.accumulate("params", params);
				System.out.println("发送短信参数"+messageParams.toString());
				String res = this.sMSServiceDubbo.send(messageParams.toString());
				
				JSONObject fromObject = JSONObject.fromObject(res);
				String str = fromObject.get("result").toString();
				if(null!=fromObject && !fromObject.isEmpty() && str.equals("success")){
					sendFlag = true;
					msg = BaseConstants.SCUUESS;
					info += "发送"+mobile+"成功";
				}else{
					info += "发送"+mobile+"失败";
				}
			}
			
			//有一条短信发送成功就将 合同 的电子签章其他状态   改为已推送
			if(sendFlag){
				Agreement agreement = new Agreement();
				agreement.setId(Long.valueOf(String.valueOf(temMap.get("compactId"))));
				List<Agreement> queryAgreement = agreementService.queryAgreement(agreement, page);
				if(queryAgreement !=null && queryAgreement.size()>0){
					Integer elecOtherState = queryAgreement.get(0).getElecOtherState();
					if(elecOtherState !=null && 3!=elecOtherState && 4!=elecOtherState ){
						agreement.setElecOtherState(2);
						int updateContractStatus = updateContractStatusService.updateContractStatus(agreement);
						msg = BaseConstants.SCUUESS;
					}
				}
			}else{
				msg = BaseConstants.FAIL;
			}
			jsonObject.accumulate("msg", msg);
			jsonObject.accumulate("info",info);
			
			log.info("SendMessageController------sendPushMessage 发送推送短信结束");
		} catch (Exception e) {
			log.error("sendPushMessage:" + e);
			msg = BaseConstants.FAIL;
			jsonObject.accumulate("info",e.getMessage());
			jsonObject.accumulate("msg", msg);
		}
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	//拼接要发送的短信链接(长的)   
	@RequestMapping(value = "/getAgreementUrl", method = { RequestMethod.POST, RequestMethod.GET })
	public void getAgreementUrl(HttpServletRequest request, HttpServletResponse response
			,String jsonStr) throws Exception {
		log.info("SendMessageController------getAgreementUrl 拼接电子合同短信推送的url");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");
		log.info("参数是"+jsonStr);
		
		JSONObject jsonObject = new JSONObject();
		try {
			Map<String, Object> temMap = JsonUtil.mapFromJson(jsonStr);
			List<Map<String, Object>> list =(List<Map<String, Object>>) temMap.get("baseList");
			ArrayList<Map<String, Object>> retList = new ArrayList<Map<String, Object>>();
			String  compactId = String.valueOf(temMap.get("compactId"));
			if(compactId ==null && "".equals(compactId)){
				throw new Exception("合同id不能为空");
			}
			
			String emotteUrl = ConfigComm.getInstance("config.properties").get("emotteUrl").toString();
			for (Map<String, Object> map : list) {
				String htmlUrl = emotteUrl+"/contract/index.html?";
				htmlUrl += "compactId=" + compactId ;
				String mobile = String.valueOf(map.get("mobile"));
				String identity = String.valueOf(map.get("identity"));
				if(identity.equals("reissueService")){
					htmlUrl = emotteUrl+"/contract/protocol.html?";
					String argumentId = String.valueOf(map.get("argumentId"));
					htmlUrl += "&argumentId="+ argumentId;
				}
				if(mobile ==null && "".equals(mobile)){
					throw new Exception("手机号不能为空");
				}
				if(identity ==null && "".equals(identity)){
					throw new Exception("接收短信者身份不能为空");
				}
				
				htmlUrl += "&mobile="+ mobile;
				htmlUrl += "&identity="+ identity;
				Map<String, Object> hashMap = new HashMap<String,Object>();
				hashMap.put("mobile", mobile);
				hashMap.put("messageUrl", htmlUrl);
				retList.add(hashMap);
				htmlUrl ="";
			}
			jsonObject.accumulate("msg",BaseConstants.SCUUESS);
			jsonObject.accumulate("data",JsonUtil.toJson(retList));
			jsonObject.accumulate("emotteUrl",emotteUrl);
			
			System.out.println(jsonObject.toString());
			log.info("SendMessageController------getAgreementUrl 拼接电子合同短信推送的url 结束");
		} catch (Exception e) {
			log.error("getAgreementUrl:" + e);
			msg = BaseConstants.FAIL;
			jsonObject.accumulate("msg", msg);
		}
		responseSendMsg(response, jsonObject.toString());
	}
	
	
}
