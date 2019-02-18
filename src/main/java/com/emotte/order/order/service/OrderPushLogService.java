package com.emotte.order.order.service;

import com.emotte.order.order.model.OrderPushLog;

public interface OrderPushLogService{
    

	/**
	 * 单次派工单发送完短信增加记录
	 * @param orderPushLog
	 * @return
	 */
	public Long insertOpushlog(OrderPushLog orderPushLog);
	
    
}

