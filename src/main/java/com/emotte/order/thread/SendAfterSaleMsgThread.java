package com.emotte.order.thread;

import com.emotte.dubbo.sms.SMSServiceDubbo;
import com.emotte.kernel.exception.BaseException;
import com.emotte.kernel.spring.SpringContext;
import com.emotte.order.util.LogHelper;

import net.sf.json.JSONObject;

public class SendAfterSaleMsgThread  extends Thread {
	
	private String realName;
	private String orderCode;
	private String afterSaleId;
	private SMSServiceDubbo smsDubbo;

	public SendAfterSaleMsgThread(String realName,String orderCode,String afterSaleId) {
		//将服务放到容器内，线程启动后，便执行
		smsDubbo = (SMSServiceDubbo) SpringContext.getApplicationContext().getBean("SMSServiceDubbo");
		this.realName = realName;
		this.orderCode = orderCode;
		this.afterSaleId = afterSaleId;
	}
	
	@Override
	public void run() {
		send();
	} 

	public void send () {
		try {
			// 设置短信内容
			JSONObject msg = new JSONObject();
			msg.accumulate("mobiles",realName); 
			msg.accumulate("templet", "order_282");
			msg.accumulate("channel", "sys");
			msg.accumulate("system", "act");
			msg.accumulate("rate", 1);
			//TODO:输入短信内容
			msg.accumulate("params", new String[] {realName,orderCode,afterSaleId});
			String msgStr = msg.toString().replace("null", "\"\"");
			String returnmsg = this.smsDubbo.send(msgStr);
			LogHelper.info(getClass() + ".smsDubbo.send()", "售后单提交短信发送给管家结果:" + returnmsg);
		} catch (BaseException e) {
			LogHelper.error(getClass(), "发送短信失败!", e);
		}
	}
}
