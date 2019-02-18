package com.emotte.order.timer;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.emotte.order.order.service.RecorderService;
@Component
public class RecorderTimerTask {
	/*@Resource 
	private RecorderController recorderController;*/
	@Resource
	private RecorderService recorderService;
	/**
	 * 每隔57秒执行一次
	 * @throws Exception 
	 "0 15 10 * * ? *" 每天上午10:15触发 */
//	@Scheduled(cron = "0 0 3 * * ?") 
	public void getRecorderInfo() throws Exception{
		System.err.println("*************************************"
				+ "***************************************************"
				+ "***************************************************");
		 recorderService.queryRecorderinfo();
	}

}
