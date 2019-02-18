package com.emotte.order.order.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.dubbo.bankcard.BankcardService;
import com.emotte.order.order.model.Bankcard;
import com.emotte.order.util.Constants;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/bankcard")
public class BankcardController extends BaseController {

	@Resource
	private BankcardService bankcardService;

	@RequestMapping(value = "/queryBankSupport", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryBankSupport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String result = "";
		JSONObject jsonObject = new JSONObject();
		try {
			result = this.bankcardService.queryBankSupport();
			if (null != result && !"".equals(result)) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryBankSupport:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("result", result);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/myBankcard", method = { RequestMethod.POST, RequestMethod.GET })
	public void myBankcard(HttpServletRequest request, HttpServletResponse response, Long custId) throws Exception {
		String result = "";
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject json = new JSONObject();
			json.accumulate("id", custId);
			result = this.bankcardService.myBankcard(json.toString());
			if (null != result && !"".equals(result)) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("myBankcard:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("result", result);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/insertBankcard", method = { RequestMethod.POST, RequestMethod.GET })
	public void insertBankcard(HttpServletRequest request, HttpServletResponse response,Bankcard bankcard) throws Exception {
		String result = "";
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject json = JSONObject.fromObject(bankcard);
			result = this.bankcardService.insertBankcard(json.toString());
			if (null != result && !"".equals(result)) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("insertBankcard:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("result", result);
		responseSendMsg(response, jsonObject.toString());
	}
	
	
	@RequestMapping(value = "/deleteBankcard", method = { RequestMethod.POST, RequestMethod.GET })
	public void deleteBankcard(HttpServletRequest request, HttpServletResponse response,Bankcard bankcard) throws Exception {
		String result = "";
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject json = JSONObject.fromObject(bankcard);
			result = this.bankcardService.deleteBankcard(json.toString());
			if (null != result && !"".equals(result)) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("deleteBankcard:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("result", result);
		responseSendMsg(response, jsonObject.toString());
	}
	

}
