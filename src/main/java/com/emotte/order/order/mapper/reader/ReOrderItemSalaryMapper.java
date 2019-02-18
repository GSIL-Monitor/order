package com.emotte.order.order.mapper.reader;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.OrderItemSalary; 
@Component("reOrderItemSalaryMapper") 
public interface ReOrderItemSalaryMapper extends ReBaseMapper{ 
	public OrderItemSalary queryOrderItemSalary(OrderItemSalary orderitemsalary); 
} 
