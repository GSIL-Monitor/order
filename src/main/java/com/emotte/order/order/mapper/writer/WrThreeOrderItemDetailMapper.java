package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.ThreeOrderItemDetailServer;
@Component("wrThreeOrderItemDetailMapper")
public interface WrThreeOrderItemDetailMapper extends WrBaseMapper {
	
	/**
	 * 保存订单详情 
	 */
	public void insertThreeOrderItemDetail(ThreeOrderItemDetailServer itemDetail);
}
