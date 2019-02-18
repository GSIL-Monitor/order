package com.emotte.order.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.order.order.model.Recorder;
import com.emotte.order.order.service.RecorderService;
import com.emotte.order.util.Constants;
import com.emotte.order.util.CookieReaderUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/recorder")
public class RecorderController extends BaseController {

	@Resource
	private RecorderService recorderService;

	@RequestMapping(value = "/loadRecorder", method = { RequestMethod.POST,
			RequestMethod.GET })
	public ModelAndView loadRecorder(HttpServletRequest request,
			HttpServletResponse response, Long id) throws Exception {
		ModelAndView mv = new ModelAndView();
		try {
			Recorder recorder = this.recorderService.loadRecorder(id);
			if (recorder == null) {
				msg = BaseConstants.NULL;

			} else {
				msg = Constants.SCUUESS;
			}
			request.setAttribute("recorder", recorder);
		} catch (Exception e) {
			log.error("loadRecorder:" + e);
			msg = Constants.FAIL;
		}
		request.setAttribute("msg", msg);
		mv.setViewName("finance/load_recorder");
		return mv;
	}

	@RequestMapping(value = "/queryRecorder", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryRecorder(HttpServletRequest request,
			HttpServletResponse response, Recorder recorder)
			throws Exception {
		List<Recorder> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.recorderService.queryRecorder(recorder);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryRecorder:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/insertRecorder", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void insertRecorder(HttpServletRequest request,
			HttpServletResponse response, Recorder recorder) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			recorder.setRecorder(CookieReaderUtil.cookieReader(request).getId());
			recorder.setSources("20500001");
			this.recorderService.insertRecorder(recorder);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("insertRecorder:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/updateRecorder", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void updateRecorder(HttpServletRequest request,
			HttpServletResponse response, Recorder recorder) throws Exception {
		JSONObject jsonObject = new JSONObject();
		try {
			recorder.setUpdateBy(CookieReaderUtil.cookieReader(request).getId());
			this.recorderService.updateRecorder(recorder);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("updateRecorder:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	@RequestMapping(value = "/queryRecorderinfo", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryRecorderinfo(HttpServletRequest request,
			HttpServletResponse response, Recorder recorder)
			throws Exception {
		List<Recorder> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			//list = this.recorderService.queryRecorderinfo(recorder);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryRecorder:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}

}
