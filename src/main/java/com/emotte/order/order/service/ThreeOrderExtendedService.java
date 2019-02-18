package com.emotte.order.order.service;

import com.emotte.order.order.model.Item;
import com.emotte.order.order.model.ThreeOrderInModel;
import com.emotte.order.order.model.ThreeOrderItemDetailServer;

public interface ThreeOrderExtendedService {
	/**
	 * 保存订单 
	 */
	public Long insertThreeOrder(ThreeOrderInModel order);
	
	/**
	 * 订单项 
	 */
	public Long insertThreeOrderItem(Item item);
	
	/**
	 * 订单详情
	 */
	public void insertItemDetailServer(ThreeOrderItemDetailServer threeOrderItemDetailServer);
	
}
