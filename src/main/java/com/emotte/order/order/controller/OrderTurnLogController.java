package com.emotte.order.order.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.core.controller.BaseController;
import org.wildhorse.server.core.utils.ContextConstants.BaseConstants;

import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderTurnLog;
import com.emotte.order.order.service.OrderTurnLogService;
import com.emotte.order.util.Constants;
import com.emotte.server.util.CookieUtils;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/orderTurnLog")
public class OrderTurnLogController extends BaseController {

	 @Resource
	private OrderTurnLogService orderTurnLogService;
    
	@RequestMapping(value = "/insertOrderTurnLog", method = { RequestMethod.POST, RequestMethod.GET })
	public void insertOrderTurnLog(HttpServletRequest request, HttpServletResponse response,OrderTurnLog orderTurnLog) {
		JSONObject jsonObject = new JSONObject();
		try {
			Long longId = Long.valueOf(CookieUtils.getJSONKey(request,"id").toString());
			Long longDeptId =Long.valueOf(CookieUtils.getJSONKey(request,"deptId").toString());
			orderTurnLog.setTurnBy(longId);
			orderTurnLog.setTurnDept(longDeptId);
			orderTurnLog.setCreateBy(longId);
			orderTurnLog.setCreateDept(longDeptId);
			this.orderTurnLogService.insertOrderTurnLog(orderTurnLog);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("insertOrderTurnLog:" + e);
 			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}

	@RequestMapping(value = "/queryOrderTurnLog", method = { RequestMethod.POST, RequestMethod.GET })
	public void queryOrderTurnLog(HttpServletRequest request, HttpServletResponse response, OrderTurnLog orderTurnLog)
			throws Exception {
		List<OrderTurnLog> list = null;
		JSONObject jsonObject = new JSONObject();
		try {
			list = orderTurnLogService.queryOrderTurnLog(orderTurnLog);
			if (list.size() > 0) {
				msg = Constants.SCUUESS;
			} else {
				msg = BaseConstants.NULL;
			}
		} catch (Exception e) {
			log.error("queryOrderTurnLog:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("list", list);
		responseSendMsg(response, jsonObject.toString());
	}
	
	@RequestMapping(value = "/selectPrincipal", method = { RequestMethod.POST, RequestMethod.GET })
	public void selectPrincipal(HttpServletRequest request, HttpServletResponse response,Long orderId)
			throws Exception {
		JSONObject jsonObject = new JSONObject();
		Order order= new Order();
		Order orderTwo= new Order();
		Long loginId = Long.valueOf(CookieUtils.getJSONKey(request, "id").toString());
		Date nowTime=new Date();
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		order = orderTurnLogService.selectPrincipal(orderId);
		orderTwo =orderTurnLogService.selectPrincipalTwo(orderId);
		int num=orderTurnLogService.selectPosition(loginId);
//		Long rechargeBy=orderTwo.getRechargeBy();
		try {
			if(order==null){
				//限制呼叫中心的所有管家 &&有没有负责人
				if(num>0){
					//是
					Date createTime=format.parse(orderTwo.getCreateTime());
					long time=(nowTime.getTime()-createTime.getTime())/1000;
					System.out.println(time+"时间");
					if (orderTwo != null && (orderTwo.getRechargeBy()==null || orderTwo.getRechargeBy().equals(""))) {
						if(time>3600){
							msg = Constants.SCUUESS;
						}else{
							msg = BaseConstants.NULL;
						}
					} else if(orderTwo != null && (orderTwo.getRechargeBy()!=null && !orderTwo.getRechargeBy().equals(""))) {
						msg = Constants.SCUUESS;
					}else{
						msg = BaseConstants.NULL;
					}
				}else{
					msg = Constants.SCUUESS;
				}
			}else{
				//不是
				msg = Constants.SCUUESS;
			}
		} catch (Exception e) {
			log.error("selectPrincipal:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		responseSendMsg(response, jsonObject.toString());
	}
	

}
