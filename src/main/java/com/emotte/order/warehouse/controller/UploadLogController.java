package com.emotte.order.warehouse.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.order.util.Constants;
import com.emotte.order.warehouse.model.UploadLog;
import com.emotte.order.warehouse.service.UploadLogService;
import com.emotte.server.util.CookieUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/uploadLog")
public class UploadLogController extends BaseController {

	@Resource
	private UploadLogService uploadLogService;

	@RequestMapping(value = "/loadUploadLog", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView loadUploadLog(HttpServletRequest request,
			HttpServletResponse response, Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			UploadLog uploadLog = this.uploadLogService.loadUploadLog(id);
			if (uploadLog == null) {
				msg = BaseConstants.NULL;

			} else {
				msg = Constants.SCUUESS;
			}
			request.setAttribute("uploadLog", uploadLog);
		} catch (Exception e) {
			log.error("loadUploadLog:" + e);
			msg = Constants.FAIL;
		}
		request.setAttribute("msg", msg);
		mv.setViewName("finance/load_uploadLog");
		return mv;
	}
	/**
	 * 查询批量发货记录
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param uploadLog
	 * @param page
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryUploadLog", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryUploadLog(HttpServletRequest request,
			HttpServletResponse response, UploadLog uploadLog, Page page)
			throws Exception {
		List<UploadLog> list = null;
		JSONObject jsonObject = new JSONObject();
		String orgocde = String.valueOf(CookieUtils.getJSONKey(request,"orgCode"));
		try {
			uploadLog.setCitycode(orgocde);
			uploadLog.setFlagPage(1);
			uploadLog.setPage(page);
			list = this.uploadLogService.queryUploadLog(uploadLog, page);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryUploadLog:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/insertUploadLog", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void insertUploadLog(HttpServletRequest request,
			HttpServletResponse response, UploadLog uploadLog) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			this.uploadLogService.insertUploadLog(uploadLog);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("insertUploadLog:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/updateUploadLog", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void updateUploadLog(HttpServletRequest request,
			HttpServletResponse response, UploadLog uploadLog) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			this.uploadLogService.updateUploadLog(uploadLog);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("updateUploadLog:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

}
