package com.emotte.order.order.alimapper.writer;

import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.OrderUser;

public interface WrOrderAliMapper extends WrBaseMapper {
	public void insertOrderCustomerAnalysis(OrderUser orderUser);
}
