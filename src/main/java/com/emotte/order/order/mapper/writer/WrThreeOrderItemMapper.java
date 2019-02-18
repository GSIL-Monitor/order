package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.Item;
@Component("wrThreeOrderItemMapper")
public interface WrThreeOrderItemMapper extends WrBaseMapper{
	
	/**
	 * 保存订单项信息 
	 */
	public void insertThreeOrderItem(Item orderItem);
}
