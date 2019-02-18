package com.emotte.order.thread;

import org.apache.commons.lang.StringUtils;

import com.emotte.dubbo.sms.SMSServiceDubbo;
import com.emotte.kernel.exception.BaseException;
import com.emotte.kernel.spring.SpringContext;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.util.LogHelper;

import net.sf.json.JSONObject;

/**
 * 新单来后给固定管家发送（解决三方订单发送短信不及时问题，以后新业务增加后，此处就会关闭）
 * 目前仅处理搬家业务
 * 需求人：刘哲 战略合作部
 * @author ChengJing
 * 2017年9月10日
 */
public class NewOrderMsgThread  extends Thread {
	
	private String orderCode;
	private SMSServiceDubbo smsDubbo;
	private OrderService orderService;

	public NewOrderMsgThread(String orderCode) {
		//将服务放到容器内，线程启动后，便执行
		smsDubbo = (SMSServiceDubbo) SpringContext.getApplicationContext().getBean("SMSServiceDubbo");
		orderService = SpringContext.getApplicationContext().getBean(OrderService.class);
		this.orderCode = orderCode;
	}
	
	@Override
	public void run() {
		send();
	} 

	public void send () {
		if (!StringUtils.isBlank(orderCode)) {
			Order order = orderService.loadOrderByCodeAndCategory(Long.parseLong(orderCode), "搬家"); // 
			if (order == null) return; // 查不到订单则返回
			try {
				// 设置短信内容
				JSONObject msg = new JSONObject();
				msg.accumulate("mobiles", "15911002323"); // 固定管家  王禹熙 15911002323
				msg.accumulate("templet", "order_142");
				msg.accumulate("channel", "sys");
				msg.accumulate("system", "order");
				msg.accumulate("rate", 1);
				//TODO:输入短信内容
				msg.accumulate("params", new String[] {order.getOrderCode(), order.getUserName(), order.getUserMobile()});
				String msgStr = msg.toString().replace("null", "\"\"");
				String returnmsg = this.smsDubbo.send(msgStr);
				LogHelper.info(getClass() + ".smsDubbo.send()", "【新单后给管家发送短信】" + returnmsg);
			} catch (BaseException e) {
				LogHelper.error(getClass(), "【新单后给管家发送短信】发送短信失败!", e);
			}
		}
	}
}
