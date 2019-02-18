package com.emotte.order.thread;

import java.util.List;

import org.wildhorse.server.core.dao.redis.client.RedisClient;
import org.wildhorse.server.core.dao.redis.exception.RedisAccessException;

import com.emotte.dubbo.order.OrderInterfaceService;
import com.emotte.kernel.exception.BaseException;
import com.emotte.kernel.spring.SpringContext;
import com.emotte.order.order.service.CommondLogService;
import com.emotte.order.util.LogHelper;


public class UpdateOtherPlateThread extends Thread  {
	private RedisClient redisClient;
	private String key;
	private OrderInterfaceService orderInterfaceService;
	private CommondLogService commondLogService;
	
	public UpdateOtherPlateThread(String key) {
		//将服务放到容器内，线程启动后，便执行
		redisClient = (RedisClient) SpringContext.getApplicationContext().getBean("redisClient");
		orderInterfaceService = (OrderInterfaceService) SpringContext.getApplicationContext().getBean("orderInterfaceService");
		commondLogService = (CommondLogService) SpringContext.getApplicationContext().getBean("commondLogService");
		this.key = key;
	}
	
	@Override
	public void run() {
		send();
	}

	public void send () {
		while(true) {
			try {
				List<String> data = redisClient.blpop(key);
				if (null != data) {
					LogHelper.info(getClass(), "更改订单状态接收redis的值:"+data.get(1));
					try {
						String result = this.orderInterfaceService.updatePlateOrder(data.get(1));
						if("success".equals(result)) {
							LogHelper.info(getClass(), "更新订单状态成功：" + data);
						} else if ("fail".equals(result)) {
							LogHelper.info(getClass(), "更新订单状态失败：" + result+ ":" + data );
							Boolean commondFlag = this.commondLogService.insertCommondLog(data.get(0), "redisClient", data.get(1));
							LogHelper.info(getClass(), "插入指令日志表订单状态失败信息是否成功：" + commondFlag);
						}
					} catch (BaseException e) {
						LogHelper.error(getClass(), e.getMessage() + data, e);
					}
				} else {
					LogHelper.info(getClass(), "无可更新订单：" + data );
				}
			} catch (RedisAccessException e) {
				LogHelper.error(getClass(), "更新订单状态失败", e);
			}
		}
	}
	
}
