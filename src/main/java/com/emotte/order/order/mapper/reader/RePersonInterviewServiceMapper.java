package com.emotte.order.order.mapper.reader;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.ItemDetailServer;
import com.emotte.order.order.model.Order;

@Component("rePersonInterviewServiceMapper")
public interface RePersonInterviewServiceMapper extends ReBaseMapper{
	
	//查询订单
	public Order selectOrder(Long id);
	
	//查询排期
	public ItemDetailServer loadOrderServerLined(Long id);
	
	//查询订单详情
	public ItemDetailServer selectItemDetailServer(Long id);

	//修改订单状态
	public int updateOrderStatus(Order order);

}
