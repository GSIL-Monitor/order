package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.OrderUser;

@Component("wrOrderBaseMapper")
public interface WrOrderBaseMapper extends WrBaseMapper{
	
	public void insertOrderUser(OrderUser orderUser);
	public void insertOrderUserAccount(OrderUser orderUser);
	public void updateOrderUser(OrderUser orderUser);
	public void insertOrderUserAddress(OrderUser orderUser);
	public void updateOrderUserAddress(OrderUser orderUser);
	public void insertOrderCustomerAnalysis(OrderUser orderUser);
}
