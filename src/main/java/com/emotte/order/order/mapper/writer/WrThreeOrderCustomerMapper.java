package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.ThreeOrderCustomerAddrModel;
import com.emotte.order.order.model.ThreeOrderCustomerModel;
@Component("wrThreeOrderCustomerMapper")
public interface WrThreeOrderCustomerMapper extends WrBaseMapper {
	/**
	 * 新增用户 
	 */
	public void insertThreeOrderCustomer(ThreeOrderCustomerModel orderUser);
	
	/**
	 * 添加客户的收货地址 
	 */
	public void insertThreeOrderFaAddr(ThreeOrderCustomerAddrModel customer);
	
	/**
	 * 修改客户的收货地址 
	 */
	public void updateThreeOrderFaAddr(ThreeOrderCustomerAddrModel customer);
}
