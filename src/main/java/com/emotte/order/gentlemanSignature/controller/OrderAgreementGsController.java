package com.emotte.order.gentlemanSignature.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.order.gentlemanSignature.model.AgreementGs;
import com.emotte.order.gentlemanSignature.service.OrderAgreementGsService;
import com.emotte.order.gentlemanSignature.signature.SignatureController;
import com.emotte.order.order.model.Agreement;
import com.emotte.order.order.service.AgreementService;
import com.emotte.order.util.Constants;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("orderAgreementGs")
public class OrderAgreementGsController extends BaseController{

	//private static final BLOB 12 = null;
	@Resource
	private OrderAgreementGsService orderAgreementGsService;
	@Resource
	private SignatureController signatureController;
	@Resource
	private UpdateContractStatusController updateContractStatusController;
	
	@Resource
	private AgreementService agreementService;
	
	
	@RequestMapping(value = "/insertOrderAgreementGs", method = { RequestMethod.POST, RequestMethod.GET })
	public void insertOrderAgreementGs(HttpServletRequest request, HttpServletResponse response,AgreementGs agreementGs,String base64ImgStr){ 
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");
		JSONObject jsonObject = new JSONObject();
		List<AgreementGs> a = null;
		Integer i=0;
		try{
			byte[] bytes = base64ImgStr.getBytes();
			agreementGs.setBase64image(bytes);
			AgreementGs ag = new AgreementGs();
			ag.setContactId(agreementGs.getContactId());
			ag.setRole(agreementGs.getRole());
			ag.setValid(Long.valueOf("1"));
			a = orderAgreementGsService.queryOrderAgreementGs(ag);	
		if(a!=null && a.size()>0){
			agreementGs.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			i=this.orderAgreementGsService.updateOrderAgreementGs(agreementGs);
		}else{	
		    i=this.orderAgreementGsService.insertOrderAgreementGs(agreementGs);
		}
		
		if(i>0){
		    msg = BaseConstants.SCUUESS;
		}else{
		    msg = BaseConstants.FAIL;
		}
		}catch(Exception e) {
			e.printStackTrace();
		   log.error("insertOrderAgreementGs:" + e);
		   msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
  }
	
	@RequestMapping(value = "/updateOrderAgreementGs", method = { RequestMethod.POST, RequestMethod.GET })
	public void updateOrderAgreementGs(HttpServletRequest request, HttpServletResponse response,AgreementGs agreementGs){ 
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");

		JSONObject jsonObject = new JSONObject();
		try{
		this.orderAgreementGsService.updateOrderAgreementGs(agreementGs);
		   msg = Constants.SCUUESS;
		}catch(Exception e) {
		   log.error("updateOrderAgreementGs:" + e);
		   msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
  }
	
	@RequestMapping(value = "/queryOrderAgreementGs", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryOrderAgreementGs(HttpServletRequest request, HttpServletResponse response,AgreementGs agreementGs){ 
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");

		JSONObject jsonObject = new JSONObject();
		List<AgreementGs> a = null;
		String clientImage=null;
		String serveImage=null;
		String insuranceEntrust = "";
		try{
		    a = orderAgreementGsService.queryOrderAgreementGs(agreementGs);
		    for(AgreementGs agreement:a){
		    	if(agreement.getRole()==1){
		    		clientImage= new String(agreement.getBase64image());
		    	}else if(agreement.getRole()==2){
		    		serveImage = new String(agreement.getBase64image());
		    	}
		    }
		    
		    Map agreementMap = agreementService.queryAgreementById(agreementGs.getContactId());
		    
		    if(null!=agreementMap && null!=agreementMap.get("INSURANCEENTRUST")
		    		&& !agreementMap.containsKey("INSURANCEENTRUST")){
		    	insuranceEntrust = agreementMap.get("INSURANCEENTRUST").toString();
		    }
		   msg = Constants.SCUUESS;
		}catch(Exception e) {
		   log.error("queryOrderAgreementGs:" + e);
		   msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("data", a);
		jsonObject.accumulate("clientImage", clientImage);
		jsonObject.accumulate("serveImage", serveImage);
		jsonObject.accumulate("insuranceEntrust", insuranceEntrust);
		
		responseSendMsg(response, jsonObject.toString());
		
	}
	
	@RequestMapping(value = "/contractRemark", method = { RequestMethod.POST, RequestMethod.GET })
		public void contractRemark(HttpServletRequest request, HttpServletResponse response,Agreement agreement
				,Long role,String signatories){ 
		    log.info("OrderAgreementGsController------contractRemark开始执行");
			log.info("设置跨域开始");
			response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
			response.setHeader("Access-Control-Allow-Credentials", "true");
			JSONObject jsonObject = new JSONObject();
			Map<String,Object> data = new HashMap<String,Object>();
			try{
				data = orderAgreementGsService.contractRemark(agreement, role, signatories);
				if(data!=null && data.get("wucs").equals(true)){
					msg = Constants.SCUUESS;
				}
			}catch(Exception e) {
			   log.error("contractRemark:" + e);
			   msg = Constants.FAIL;
			}
			jsonObject.accumulate("msg", msg);
			jsonObject.accumulate("data", data);
			log.info("OrderAgreementGsController------contractRemark执行结束");
			responseSendMsg(response, jsonObject.toString());	
  }
	
	
}