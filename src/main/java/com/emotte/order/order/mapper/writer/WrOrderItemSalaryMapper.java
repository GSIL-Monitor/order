package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.OrderItemSalary; 
@Component("wrOrderItemSalaryMapper") 
public interface WrOrderItemSalaryMapper extends WrBaseMapper{ 

	public void insertOrderItemSalary(OrderItemSalary orderitemsalary);

	public int updateOrderItemSalary(OrderItemSalary orderitemsalary);

} 
