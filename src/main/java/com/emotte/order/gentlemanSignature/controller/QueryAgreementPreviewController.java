package com.emotte.order.gentlemanSignature.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.order.gentlemanSignature.service.QueryAgreementPreviewService;
import com.emotte.order.order.model.Agreement;

import net.sf.json.JSONObject;
@Controller
@RequestMapping("queryAgreementPreview")
public class QueryAgreementPreviewController extends BaseController{

	@Resource
	private QueryAgreementPreviewService queryAgreementPreviewService;
	
	@RequestMapping(value = "/queryAgreementPreview", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryAgreementPreview(HttpServletRequest request, HttpServletResponse response,Agreement agreement){
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");
		JSONObject jsonObject = new JSONObject();
		List<Agreement> a =null;
		try{
		    a =queryAgreementPreviewService.queryAgreementPreview(agreement);
		}catch(Exception e){
			log.error("updateContractStatus:" + e);
		    msg = BaseConstants.FAIL;
		}
		 jsonObject.accumulate("data",a);
		 responseSendMsg(response, jsonObject.toString());
	}
	
}
