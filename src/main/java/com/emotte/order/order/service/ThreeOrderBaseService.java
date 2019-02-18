package com.emotte.order.order.service;

import java.util.List;

import org.wildhorse.server.core.model.Page;
import com.emotte.order.order.model.OrderBaseModel;
import com.emotte.order.order.model.OrderUser;

public interface ThreeOrderBaseService {
	public StringBuffer queryDictionaryMapper(Long pid,String types);
	public List<OrderUser> queryUserMapper(OrderUser orderUser,Page page);
	public List<OrderUser> queryUserAddressMapper(OrderUser orderUser);
	public List<OrderUser> queryUserAddressByMobile(OrderUser orderUser);
	public void insertUser(OrderUser orderUser);
	public void insertUserAddress(OrderUser orderUser);
	public void updateUserAddress(OrderUser orderUser);
	public List<OrderBaseModel> queryBaseCity( String code, int length);
	public List<OrderBaseModel> queryOrderBasicServer(Long id);
	public List<OrderBaseModel> queryOrderNeedServer(OrderBaseModel orderBaseModel,Page page);
	
}
