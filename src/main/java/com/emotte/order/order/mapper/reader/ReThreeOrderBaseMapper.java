package com.emotte.order.order.mapper.reader;

import java.util.List;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;
import com.emotte.order.order.model.Dictionary;
import com.emotte.order.order.model.OrderBaseModel;
import com.emotte.order.order.model.OrderUser;

  @Component("reThreeOrderBaseMapper")
public interface ReThreeOrderBaseMapper extends ReBaseMapper{
	  
	public List<Dictionary> queryDictionaryMapper(Dictionary dictionary); // 查字典
	public Integer countUserMapper(OrderUser orderUser); // 用户
	public List<OrderUser> queryUserMapper(OrderUser orderUser); // 用户
	public List<OrderUser> queryUserAddressMapper(OrderUser orderUser); // 用户
	public List<OrderUser> queryUserAddressOrder(OrderUser orderUser);
	public List<OrderUser> queryUserAddressByMobile(OrderUser orderUser); // 查用户地址
	public List<OrderBaseModel> queryBaseCity(OrderBaseModel baseModel); // 查城市
	public List<OrderBaseModel> queryOrderBasicServer(Long id); // 查服务订单基本信息
	public Integer countOrderNeedServer(OrderBaseModel orderBaseModel); // 查服务订单基本信息
	public List<OrderBaseModel> queryOrderNeedServer(OrderBaseModel orderBaseModel); // 查服务订单基本信息
	public List<OrderBaseModel> queryOrderNeeds(OrderBaseModel orderBaseModel); // 匹配人员信息
	public OrderUser querLoadUser(OrderUser user);
	public OrderUser queryAddress(OrderUser user);
	
	
}