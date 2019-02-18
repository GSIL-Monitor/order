package com.emotte.order.order.service;

import java.util.List;

import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderTurnLog;

public interface OrderTurnLogService {
	
	void insertOrderTurnLog(OrderTurnLog orderTurnLog);

	 List<OrderTurnLog> queryOrderTurnLog(OrderTurnLog orderTurnLog);
	 
	 public Order selectPrincipal(Long orderCodeId);
	 
	 public Order selectPrincipalTwo(Long orderCodeId);
	 
	 public  int selectPosition (Long loginId);
}
