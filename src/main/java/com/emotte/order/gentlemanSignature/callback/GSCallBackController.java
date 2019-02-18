package com.emotte.order.gentlemanSignature.callback;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.data.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wildhorse.server.core.controller.BaseController;

import com.emotte.gentlemanSignature.core.controller.OrganizationAuditStatusController;
import com.emotte.gentlemanSignature.core.controller.ServCertiStatusController;
import com.emotte.gentlemanSignature.core.model.GSResult;
import com.emotte.gentlemanSignature.core.model.OrganizationAuditStatus;
import com.emotte.gentlemanSignature.core.model.ServCertiStatus;
import com.emotte.order.util.JsonUtil;

@Controller
@RequestMapping("GSCallBack")
public class GSCallBackController extends BaseController{
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Reference
	private OrganizationAuditStatusController organizationAuditStatusController;
	
	@Reference
	private ServCertiStatusController servCertiStatusController;

	@RequestMapping("organization.audit.status")
	public void organizationAuditStatus(HttpServletRequest request, HttpServletResponse response,OrganizationAuditStatus organizationAuditStatus) {
		log.info("君子签回调函数GSCallBack------organization.audit.status开始执行");
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		GSResult GSResult = null;
		try {
			GSResult = organizationAuditStatusController.process(organizationAuditStatus);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("君子签回调函数GSCallBack------organization.audit.status执行结束");
		responseSendMsg(response, JsonUtil.toJson(GSResult));
	}
	
	  
	@RequestMapping("serv.certi.status")
	public void servCertiStatus(ServCertiStatus servCertiStatus) {
       log.info("君子签回调函数GSCallBack------serv.certi.status开始执行");
       log.info("设置跨域开始");
	   response.setHeader("Access-Control-Allow-Origin", "*");
	   response.setHeader("Access-Control-Allow-Credentials", "true");
       GSResult GSResult = null;
       try {
    	   GSResult =servCertiStatusController.process(servCertiStatus);
	} catch (Exception e) {
		e.printStackTrace();
	}
	   log.info("君子签回调函数GSCallBack------serv.certi.status执行结束");
	   responseSendMsg(response, JsonUtil.toJson(GSResult));
	}
}