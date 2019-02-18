package com.emotte.order.timer;


import java.util.HashMap;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.emotte.order.util.HttpUtil;
import com.emotte.order.util.UWuKuTools;


/**
 * 
 * @author lilang
 * @emai  lilang@95081.com
 * @time 2016-7-18 下午7:04:48
 * @订单缴费后赠送十元消费券相关任务
 */
@Component
public class UWuKuTimerTask {
	String host = "http://bestweshop.dianking.cn/egou/index.php/api/uwuku/";
	
	/**
	 * 每隔57秒执行一次
	 */
	@Scheduled(cron = "3/57 * * * * ?")  
	public void getUWuKuOrder() {
		
	}
	
	
	/**
	 * 每天11:08执行
	 */
	//@Scheduled(cron = "0 48 11 * * ?")  
	public void solutionTask(){
		
	}
	
	public static void main(String[] args) {
		UWuKuTimerTask kuTimerTask = new UWuKuTimerTask();
		String result = kuTimerTask.getOrderList();
		System.out.println(result);
	}
	
	public String getOrderList(){
		String result = "";
		HashMap<String,String> map = new HashMap<String, String>();
		
		map.put("fields", "tid,status,payment,total_fee");
		map.put("page", "1");
		map.put("page_no", "5");
		map.put("request_type", "order");
		map.put("method", "youwuku.order.get");
		
		map = UWuKuTools.getSystemParam(map);
		String sign =UWuKuTools.createSignStr( UWuKuTools.sortKeyVal(map) );
		System.out.println(sign);
		map.put("sign", sign);	
		result = HttpUtil.post(UWuKuTools.host+"order",map, null, null);
		return result;
	}
	
	
	
	
}
