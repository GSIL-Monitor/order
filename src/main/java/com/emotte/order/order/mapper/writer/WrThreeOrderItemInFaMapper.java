package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.ThreeOrderInFaItemModel;

@Component("wrThreeOrderItemInFaMapper")
public interface WrThreeOrderItemInFaMapper extends WrBaseMapper{
	
	/**
	 * 保存Fa订单项信息 
	 */
	public void insertThreeOrderItemInFa(ThreeOrderInFaItemModel item);
	
	public void updateThreeOrderItemInFa(ThreeOrderInFaItemModel item);
}
