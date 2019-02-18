package com.emotte.order.order.controller;

import java.net.URLEncoder;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.core.controller.BaseController;

import com.emotte.dubbo.sms.SMSServiceDubbo;
import com.emotte.order.order.model.ItemInterview;
import com.emotte.order.order.model.PushLog;
import com.emotte.order.order.service.ItemInterviewService;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.order.service.PushLogService;
import com.emotte.order.util.Constants;
import com.emotte.order.util.SendUtils;
import com.emotte.server.util.CookieUtils;

import net.sf.json.JSONObject;
import com.emotte.kernel.helper.LogHelper;

/**
 * 下户发送短信
 * 
 * @author ZhouXin 2018年10月30日下午2:26:29
 *
 */
@Controller
@RequestMapping("/outHouseSendSmss")
public class OutHouseSendSmss extends BaseController {

	@Resource
	private SMSServiceDubbo smsServiceDubbo;
	@Resource
	private OrderService orderService;
	@Resource
	private ItemInterviewService itemInterviewService;
	@Resource
	private PushLogService pushLogService;

	/**
	 * 服务人员下户发送短信 操作人：周鑫 2018年10月30日下午4:39:28
	 * 
	 * @param ids:上下户ID
	 */
	@RequestMapping(value = "/sendSmss", method = { RequestMethod.POST, RequestMethod.GET })
	public void sendSmss(HttpServletRequest request, HttpServletResponse response, Long ids) throws Exception {
		// 保存返回信息
		JSONObject jsonObject = new JSONObject();
		String msg = "";
		String msgText = "";
		try {
			LogHelper.info(getClass(), "服务人员签退参数:" + ids);
			// 根据上下户ID获取相关信息
			ItemInterview loadItemInterviewInfo = itemInterviewService.loadItemInterviewInfo(ids);
			// 必须是查询到结果和时间必须是 2018-10-31 以后的
			if (null != loadItemInterviewInfo&&null!=loadItemInterviewInfo.getStarTime()&&Long.parseLong(loadItemInterviewInfo.getStarTime())>20181031) {
				/**
				 * 长连接转短链接
				 */
				String shortUrl = null;
				String linkConvertUrl = ResourceBundle.getBundle("config").getString("link_convert_url");
				String clockBillUrl = ResourceBundle.getBundle("config").getString("clockRecord_url");
				/**
				 * 参数：  personId  服务人员ID
				 * 	    orderId   订单ID
				 * 	    orderStatus:订单状态
				 * 		queryCategory:标识
				 */
				String param = URLEncoder.encode(clockBillUrl + 
						"?personId=" + loadItemInterviewInfo.getPersonId() +
						"&orderId=" + loadItemInterviewInfo.getOrderId() +
						"&orderStatus=" + loadItemInterviewInfo.getOrderStatus() +
						"&queryCategory=2", "UTF-8");
				String jsonStr = SendUtils.sendGet(linkConvertUrl, param);
				if (null != jsonStr && !"".equals(jsonStr)) {
					JSONObject jsonObject1 = JSONObject.fromObject(jsonStr);
					if (jsonObject1.get("msg").equals("00")) {
						shortUrl = jsonObject1.get("shortUrl") != null ? jsonObject1.getString("shortUrl") : "";
						LogHelper.info(getClass(), "服务人员签退短链接:ids=" +ids +",shortUrl="+ shortUrl);
					}
				}
				/**
				 * 发送短信功能
				 */
				if (null != shortUrl && shortUrl != "") {
					JSONObject p_sendParam = new JSONObject();
					p_sendParam.put("mobiles", loadItemInterviewInfo.getMobilePhone()); // 接收人手机号(必填)
					p_sendParam.put("params",new String[] { 
							                loadItemInterviewInfo.getName(),
											loadItemInterviewInfo.getOrderType(),
											loadItemInterviewInfo.getOrderCode().toString(),
											shortUrl });// 模板内容(选填)
					p_sendParam.put("templet", "crm2_1383"); // 模板(必填)
					p_sendParam.put("channel", "sys"); // 通道字典码(yzm:验证码,sys:系统消息,ts:营销消息)(必填)
					p_sendParam.put("type", "2"); // 短信类型字典码(1:续费,2:反馈回复,3:余额变动)(选填)
					p_sendParam.put("rate", "1"); // 发送频率(1:即时发送,2:定时发送)(必填)
					p_sendParam.put("system", "crm"); // 系统码(必填)
					p_sendParam.put("source", "order|orderPersonStartCont.jsp"); // 来源(格式:系统名|页面|按钮)(选填)
					LogHelper.info(getClass(), "给服务人员发服务人员服务费通知短信参数：" + p_sendParam);
					String p_dubboResult = this.smsServiceDubbo.send(p_sendParam.toString());
					LogHelper.info(getClass(), "给服务人员发服务人员服务费通知短信返回：" + p_dubboResult);
					msgText = "短信发送成功";
					msg = Constants.SCUUESS;
					
					// 新增发送记录
					PushLog pushLog = new PushLog();
					pushLog.setOrderId(loadItemInterviewInfo.getOrderId());
					pushLog.setManagerId(loadItemInterviewInfo.getPersonId());
					pushLog.setType(2);
					pushLog.setCreateBy(CookieUtils.getJSON(request).getLong("id"));
					pushLog.setDistinguish(4);
					pushLog.setLog("Order:下户发送短信");
				    pushLogService.insertPushLog(pushLog);
				}else{
					msgText = "短信发送失败";
					msg = Constants.RET_ERROR;
				}
			}else{
				msgText = "短信发送失败";
				msg = Constants.RET_ERROR;
			}
		} catch (Exception e) {
			LogHelper.info(getClass(), "服务人员签退异常" + e.toString());
			msgText = "短信发送失败";
			msg = Constants.FAIL;
		}
		// 返回信息
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("msgText",msgText);
		responseSendMsg(response, jsonObject.toString());
	}

}
