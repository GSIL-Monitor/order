package com.emotte.order.order.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.wildhorse.server.core.model.Page;

import com.emotte.order.order.model.ThreeOrderBaseCity;
import com.emotte.order.order.model.ThreeOrderCustomerAddrModel;
import com.emotte.order.order.model.ThreeOrderCustomerModel;
import com.emotte.order.order.model.ThreeOrderInProductModel;

public interface ThreeOrderService {
	/**
	 * 获得客户信息 
	 */
	public List<ThreeOrderCustomerModel> getCustomMapper(ThreeOrderCustomerModel customer,Page page);
	
	/**
	 * 保存客户信息
	 */
	public void saveOrderUser(ThreeOrderCustomerModel customer);
	
	/**
	 * 获得客户信息 （根据客服phone）
	 */
	public boolean isExsitCustomerByMoblie(ThreeOrderCustomerModel customer);
	
	/**
	 * 获得地址信息 
	 */
	public List<ThreeOrderCustomerAddrModel> getOrderThreeCustomerAddrList(ThreeOrderCustomerAddrModel customer);
	
	/**
	 * 收货地址是否存在 
	 */
	public Boolean isExistCustomerAddr(ThreeOrderCustomerAddrModel customer);
	
	/**
	 * 保存地址信息 
	 */
	public void saveOrderThreeCustomerAddr(ThreeOrderCustomerAddrModel customer);
	
	/**
	 * 修改地址信息 
	 */
	public void editOrderThreeCustomerAddr(ThreeOrderCustomerAddrModel customer);
	
	/**
	 * 获得地址信息 
	 */
	public ThreeOrderCustomerAddrModel getOrderThreeCustomerAddrById(ThreeOrderCustomerAddrModel customer);
	
	/**
	 * 根据地址的名称获得code 
	 */
	public ThreeOrderBaseCity getAddrCode(ThreeOrderBaseCity addrBaseCity);
	
	
	/**
	 * 获得三级分类下的商品信息 
	 */
	public List<ThreeOrderInProductModel> getThreeOrderInProductList(
			ThreeOrderInProductModel threeOrderInProductModel);
	
	/**
	 * 重新计算订单价格（Fa）
	 */
	public List<ThreeOrderInProductModel> reCalculateThreeOrderInPrice(
			ThreeOrderInProductModel threeOrderInProductModel,Map<String,BigDecimal>amount_map);
}
