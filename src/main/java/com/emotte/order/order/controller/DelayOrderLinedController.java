package com.emotte.order.order.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emotte.order.order.service.ItemInterviewService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.eclient.EClientContext;
import com.emotte.interf.EScheduleService;
import com.emotte.order.util.CookieReaderUtil;

import net.sf.json.JSONObject;

/**
 * @Title     ：
 * @File Name ： DelayOrderLinedController.java
 * @author    ：LiYunna 
 * @date      ：2017年8月21日 上午9:59:41 
 * @version 1.0   
 * @since  
 * @return   
 */
@Controller
@RequestMapping("/delay")
public class DelayOrderLinedController extends BaseController{

	EScheduleService scheduleService = (EScheduleService) EClientContext.getBean(EScheduleService.class);

	@Resource
	private ItemInterviewService itemInterviewService;
	/**
	 * @Description: 延长订单排期
	 * @param  Long empId,Long orderId,
	 *		   String startDate,String endDate,
	 *		   String startTime,String endTime,String week
	 * @author YUNNA
	 * @date 2017年8月21日
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/delayOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public void delayOrder(HttpServletRequest request, HttpServletResponse response, Long empId,Long orderId,
			String startDate,String endDate,String startTime,String endTime,String week)throws Exception{			
		JSONObject jsonObject = new JSONObject();	
		//Long authManagerId = Long.valueOf(String.valueOf(CookieUtils.getJSONKey(request,"id")));
		Long loginBy = CookieReaderUtil.cookieReader(request).getId();
		String code = "";
		String msgs = "";
		try{
			week = week.replaceAll("[^一二三四五六天,]", "").replaceAll("[,]+", ",");
			week = week.replaceAll("一", "1").replaceAll("二", "2").replaceAll("三", "3")
			             .replaceAll("四", "4").replaceAll("五", "5").replaceAll("六", "6")
			             .replaceAll("天", "7");
			jsonObject.put("updateBy", loginBy);
			jsonObject.put("empId", empId);
			jsonObject.put("orderId", orderId);
			jsonObject.put("startDate", startDate);
			jsonObject.put("endDate", endDate);
			jsonObject.put("startTime", startTime);
			jsonObject.put("endTime", endTime);
			jsonObject.put("weekday", week);		
			System.out.println(jsonObject.toString());
			String jsonStr =this.scheduleService.extendOrderLined(jsonObject.toString());
			JSONObject json = JSONObject.fromObject(jsonStr);
			JSONObject jsondata = JSONObject.fromObject(json.get("data"));
			code = jsondata.getString("code");
			msgs = jsondata.getString("msg");
		}catch(Exception e){
			log.error("delayOrderLined:" + e);
			msgs = "-2";
		}
		jsonObject.accumulate("code", code);
		jsonObject.accumulate("msg", msgs);
		responseSendMsg(response, jsonObject.toString());
	}
			
	/**
	 * @Description: 延长服务人员排期
	 * @param  Long personId,Long orderId,
	 *		   Long createBy,Long checkBy,
	 *		   String startTime,String endTime
	 * @author YUNNA
	 * @date 2017年8月21日
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/delayPersonLined", method = { RequestMethod.POST, RequestMethod.GET })
	public void delayPersonLined(HttpServletRequest request, HttpServletResponse response, Long personId,Long orderId,
			Long checkBy,String startTime,String endTime,Long clashOrderId)throws Exception{			
		JSONObject jsonObject = new JSONObject();	
		Long loginBy = CookieReaderUtil.cookieReader(request).getId();
		String code = "";
		String data = "";
		String msgs = "";
		try{
			jsonObject.put("createBy", loginBy);
			jsonObject.put("personId", personId);
			jsonObject.put("orderId", orderId);			
			jsonObject.put("startTime", startTime);
			jsonObject.put("endTime", endTime);
			jsonObject.put("checkBy", checkBy);			
			jsonObject.put("clashOrderId", clashOrderId);	
			String jsonStr = this.scheduleService.extendApply(jsonObject.toString());
			JSONObject json = JSONObject.fromObject(jsonStr);
			JSONObject jsondata = JSONObject.fromObject(json.get("data"));
			code = jsondata.getString("code");
			//data = jsondata.getString("data");
			msgs = jsondata.getString("msg");
		}catch(Exception e){
			log.error("delayPersonLined:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msgs);
		jsonObject.accumulate("code", code);
		//jsonObject.accumulate("data", data);
		responseSendMsg(response, jsonObject.toString());
	}
	
	/**
	 * @Description: 延长排期审批或取消接口
	 * @param  Long id,Long checkBy,
	 *		   Integer checkStatus	 		   
	 * @author YUNNA
	 * @date 2017年8月22日
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/audit", method = { RequestMethod.POST, RequestMethod.GET })
	public void audit(HttpServletRequest request, HttpServletResponse response, Integer checkStatus,
			Long id)throws Exception{			
		JSONObject jsonObject = new JSONObject();	
		Long checkBy = CookieReaderUtil.cookieReader(request).getId();
		String code = "";
		String data = "";
		String msgs = "";
		try{
			jsonObject.put("checkBy", checkBy);
			jsonObject.put("checkStatus", checkStatus);
			jsonObject.put("id", id);		
			System.out.println(jsonObject.toString());
			String jsonStr = this.scheduleService.apply(jsonObject.toString());
			JSONObject json = JSONObject.fromObject(jsonStr);
			JSONObject jsondata = JSONObject.fromObject(json.get("data"));
			code = jsondata.getString("code");
			msgs = jsondata.getString("msg");
		}catch(Exception e){
			log.error("delayPersonLined:" + e);
			msgs = "-2";
		}
		jsonObject.accumulate("msg", msgs);
		jsonObject.accumulate("code", code);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 服务人员下户
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param orderId
	 * @param empId
	 * @param endDate
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/downHourse", method = { RequestMethod.POST, RequestMethod.GET })
	public void downHourse(HttpServletRequest request, HttpServletResponse response, 
			Long orderId,Long empId,String endDate,Long reason,String explain)throws Exception{			
		JSONObject jsonObject = new JSONObject();	
		String code = "";
		String data = "";
		String msgs = "";
		try{
			jsonObject.put("orderId", orderId);
			jsonObject.put("empId", empId);
			jsonObject.put("endDate", endDate);
			jsonObject.put("reason", reason);
			jsonObject.put("explain", explain);
			EScheduleService service = (EScheduleService) EClientContext.getBean(EScheduleService.class);
			System.out.println(jsonObject.toString());
			String jsonStr = service.downHourse(jsonObject.toString());
			System.out.println(jsonStr);
			JSONObject json = JSONObject.fromObject(jsonStr);
			JSONObject jsondata = JSONObject.fromObject(json.get("data"));
			code = jsondata.getString("code");
			msgs = jsondata.getString("msg");
			if("0".equals(code)){//20180614更新服务人员状态为在校待岗
				itemInterviewService.updatePersonnalStatus(10l,empId);
			}
		}catch(Exception e){
			log.error("downHourse:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msgs);
		jsonObject.accumulate("code", code);
		responseSendMsg(response, jsonObject.toString());
	}
	/**
	 * 服务人员上户
	 * @author 王世博
	 * @param request
	 * @param response
	 * @param orderId
	 * @param empId
	 * @param endDate
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/goHourse", method = { RequestMethod.POST, RequestMethod.GET })
	public void goHourse(HttpServletRequest request, HttpServletResponse response, Long orderId,Long empId,String startDate)throws Exception{			
		JSONObject jsonObject = new JSONObject();	
		String code = "";
		String data = "";
		String msgs = "";
		try{
			jsonObject.put("orderId", orderId);
			jsonObject.put("empId", empId);
			jsonObject.put("startDate", startDate);
			EScheduleService service = (EScheduleService) EClientContext.getBean(EScheduleService.class);
			System.out.println(jsonObject.toString());
			String jsonStr = service.goHourse(jsonObject.toString());
			System.out.println(jsonStr);
			JSONObject json = JSONObject.fromObject(jsonStr);
			JSONObject jsondata = JSONObject.fromObject(json.get("data"));
			code = jsondata.getString("code");
			msgs = jsondata.getString("msg");
			if("0".equals(code)){//20180614更新服务人员状态为在户服务
				itemInterviewService.updatePersonnalStatus(1l,empId);
			}
		}catch(Exception e){
			log.error("goHourse:" + e);
			msg = BaseConstants.FAIL;
		}
		jsonObject.accumulate("msg", msgs);
		jsonObject.accumulate("code", code);
		responseSendMsg(response, jsonObject.toString());
	}
}
 