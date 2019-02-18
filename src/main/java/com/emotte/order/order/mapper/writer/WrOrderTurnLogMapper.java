package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.OrderTurnLog;

@Component("wrOrderTurnLogMapper") 
public interface WrOrderTurnLogMapper  extends WrBaseMapper{
	
	void insertOrderTurnLog(OrderTurnLog orderTurnLog);
}
