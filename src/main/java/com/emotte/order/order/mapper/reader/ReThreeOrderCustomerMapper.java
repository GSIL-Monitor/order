package com.emotte.order.order.mapper.reader;

import java.util.List;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.ThreeOrderCustomerAddrModel;
import com.emotte.order.order.model.ThreeOrderCustomerModel;
import com.emotte.order.order.model.ThreeOrderInProductModel;
@Component("reThreeOrderCustomerMapper")
public interface ReThreeOrderCustomerMapper extends ReBaseMapper {
	/**
	 * 获得客户信息
	 * @param orderUser
	 * @return List<OrderUser>
	 */
	public List<ThreeOrderCustomerModel> getOrderThreeCustomer(ThreeOrderCustomerModel orderUser);
	
	/**
	 * 获得客户数量
	 * @param orderUser
	 * @return Integer
	 */
	public Integer countOrderThreeCustomer(ThreeOrderCustomerModel orderUser);
	
	
	/**
	 * 根据手机号码获得客户信息 
	 * @param orderUser
	 * @return OrderUser
	 */
	public Integer isExsitCustomerByMoblie(ThreeOrderCustomerModel orderUser);
	
	/**
	 * 获得客户信息ById
	 */
	public ThreeOrderCustomerModel getCustomer(ThreeOrderCustomerModel orderUser);
	
	/**
	 * 获得用户级别：是否vip,是否大客户
	 */
	public ThreeOrderInProductModel gerCustomerIs_Vip_BigCustById(ThreeOrderInProductModel threeOrderInProductModel);
	
	/**
	 * 获得此用户的收货地址
	 */
	public List<ThreeOrderCustomerAddrModel> queryOrderThreeAddrByUser(ThreeOrderCustomerAddrModel customer);
	
	/**
	 * 验证是否存在 
	 */
	public Integer isExistCustomerAddrById(ThreeOrderCustomerAddrModel customer);
	
}
