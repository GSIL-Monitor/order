package com.emotte.order.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.order.order.model.AgreementFile;
import com.emotte.order.order.service.AgreementFileService;
import com.emotte.order.util.Constants;
import com.emotte.server.util.CookieUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/agreementFile")
public class AgreementFileController extends BaseController {

	@Resource
	private AgreementFileService agreementFileService;

	@RequestMapping(value = "/loadAgreementFile", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView loadAgreementFile(HttpServletRequest request, HttpServletResponse response, Long id)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			AgreementFile agreementFile = this.agreementFileService.loadAgreementFile(id);
			if (agreementFile == null) {
				msg = BaseConstants.NULL;

			} else {
				msg = Constants.SCUUESS;
			}
			request.setAttribute("agreementFile", agreementFile);
		} catch (Exception e) {
			log.error("loadAgreementFile:" + e);
			msg = Constants.FAIL;
		}
		request.setAttribute("msg", msg);
		mv.setViewName("finance/load_agreementFile");
		return mv;
	}

	@RequestMapping(value = "/queryAgreementFile", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryAgreementFile(HttpServletRequest request, HttpServletResponse response,
			AgreementFile agreementFile) throws Exception {
		log.info("设置跨域开始");
		response.setHeader("Access-Control-Allow-Origin", "*"); // 允许跨域请求
		response.setHeader("Access-Control-Allow-Credentials", "true");
		List<AgreementFile> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.agreementFileService.queryAgreementFile(agreementFile);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryAgreementFile:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/insertAgreementFile", method = { RequestMethod.POST, RequestMethod.GET })
	public void insertAgreementFile(HttpServletRequest request, HttpServletResponse response,
			AgreementFile agreementFile) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			Long createBy = Long.valueOf(CookieUtils.getJSONKey(request, "id").toString());
			agreementFile.setCreateBy(createBy);
			this.agreementFileService.insertAgreementFile(agreementFile);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("insertAgreementFile:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/updateAgreementFile", method = { RequestMethod.POST, RequestMethod.GET })
	public void updateAgreementFile(HttpServletRequest request, HttpServletResponse response,
			AgreementFile agreementFile) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			this.agreementFileService.updateAgreementFile(agreementFile);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("updateAgreementFile:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

	/**
	 * 单次服务订单上传合同扫描件
	 *
	 * @param request
	 * @param response
	 * @param agreementFile
	 * @Author zhanghao
	 * @Date 20190115
     */
	@RequestMapping("/saveOnceServerAgreementCopy")
	public void saveOnceServerAgreementCopy(HttpServletRequest request, HttpServletResponse response, AgreementFile agreementFile){
		JSONObject jsonObject = new JSONObject();
		Long loginId = Long.valueOf(CookieUtils.getJSONKey(request, "id").toString());
		agreementFile.setCreateBy(loginId);
		agreementFile.setValid(1);
		agreementFile.setVersion(1l);
		try {
			agreementFileService.saveOnceServerAgreementCopy(agreementFile);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			msg = Constants.FAIL;
			log.error("saveOnceServerAgreementCopy方法执行失败,参数信息:{agreementId:" + agreementFile.getAgreementId() + "},错误信息:" + ExceptionUtils.getStackTrace(e));
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

}
