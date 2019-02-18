package com.emotte.order.warehouse.mapper.reader;

import java.util.List;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.warehouse.model.Order;

  @Component("reOrderParcelMapper")
public interface ReOrderParcelMapper extends ReBaseMapper{
    
	public List<Order> selectOrderComplete(Order order);
	
	public List<Order> OrderCode(Order order);
	
}