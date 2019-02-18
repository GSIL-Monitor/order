package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.ThreeOrderInModel;

@Component("wrThreeOrderMapper")
public interface WrThreeOrderMapper extends WrBaseMapper{
	
	/**
	 * 保存订单信息 
	 */
	public void insertThreeOrder(ThreeOrderInModel order);
}
