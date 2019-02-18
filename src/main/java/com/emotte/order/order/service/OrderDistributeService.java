package com.emotte.order.order.service;

import com.emotte.order.order.model.Order;

/**
 * 订单分发服务类
 * @author ChengJing
 * 2017年1月9日
 */
public interface OrderDistributeService {
	/**
	 * 分发
	 * @param order 订单信息 
	 * @return true:分发成功<br>false:分发失败<br>
	 * 2017年1月9日
	 */
	public boolean distribute (Order order);
}
