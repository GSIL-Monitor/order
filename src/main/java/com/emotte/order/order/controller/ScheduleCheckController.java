package com.emotte.order.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.model.Page;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.order.order.model.ScheduleCheck;
import com.emotte.order.order.service.ScheduleCheckService;
import com.emotte.order.util.Constants;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/scheduleCheck")
public class ScheduleCheckController extends BaseController {

	@Resource
	private ScheduleCheckService scheduleCheckService;

    /**
     * 查询服务人员下一个订单负责人
     * @param scheduleCheck
     * @date 2017年 8月22日
     *
     */
	@RequestMapping(value = "/queryScheduleCheck", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryScheduleCheck(HttpServletRequest request,
			HttpServletResponse response, ScheduleCheck scheduleCheck, Page page)
			throws Exception {
		List<ScheduleCheck> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = this.scheduleCheckService.queryScheduleCheck(scheduleCheck, page);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryScheduleCheck:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}

	 /**
     * 查询延长服务申请排期
     * @param scheduleCheck
     * @date 2017年 8月23日
     *
     */
	@RequestMapping(value = "/queryDelayLined", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void queryDelayLined(HttpServletRequest request,
			HttpServletResponse response, ScheduleCheck scheduleCheck)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<ScheduleCheck> list = null;
		try {
			list = this.scheduleCheckService.queryDelayLined(scheduleCheck);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryDelayLined:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		//jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}
	 /**
     * 查询延长服务申请状态
     * @param scheduleCheck
     * @date 2017年 8月23日
     *
     */
	@RequestMapping(value = "/queryDelayLinedType", method = { RequestMethod.POST,RequestMethod.GET })
	public void queryDelayLinedType(HttpServletRequest request,HttpServletResponse response, ScheduleCheck scheduleCheck)throws Exception {
		JSONObject jsonObject = new JSONObject();
		List<ScheduleCheck> list = null;
		try {
			list = this.scheduleCheckService.queryDelayLinedType(scheduleCheck);
			if (list.size() > 0) {
				msg = BaseConstants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryDelayLined:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		//jsonObject.accumulate("page", page);
		responseSendMsg(response, jsonObject.toString());
	}

}
