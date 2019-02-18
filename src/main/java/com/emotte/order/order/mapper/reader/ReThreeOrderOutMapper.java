package com.emotte.order.order.mapper.reader;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.ThreeOrder;
import com.emotte.order.order.model.ThreeOrderAccounts;
import com.emotte.order.order.model.ThreeOrderAddress;
import com.emotte.order.order.model.ThreeOrderCard;
import com.emotte.order.order.model.ThreeOrderCategory;
import com.emotte.order.order.model.ThreeOrderDictionary;
import com.emotte.order.order.model.ThreeOrderPrice;
import com.emotte.order.order.model.ThreeOrderProduct;
import com.emotte.order.order.model.ThreeOrderUser;

@Component("reThreeOrderOutMapper")
public interface ReThreeOrderOutMapper extends ReBaseMapper{
	
	public List<ThreeOrderCategory> queryOrderThirdType(ThreeOrder order);
	
	public List<ThreeOrderDictionary> queryBaseDictionary(ThreeOrder order);
	
	public List<ThreeOrderDictionary> queryBaseCity(ThreeOrder order);

	public Integer countOrder(ThreeOrder order);
	
	public List<ThreeOrder> queryOrder(ThreeOrder order);
	
	public List<ThreeOrder> queryOrderOneDetail(ThreeOrder order);
	
	public List<ThreeOrderAccounts> queryOrderOneAccounts(ThreeOrderAccounts order);
	
	public List<ThreeOrderAccounts> queryOrderOnePayfee(ThreeOrderAccounts order);

	public Integer countOrderUser(ThreeOrderUser order);

	public List<ThreeOrderUser> queryOrderUser(ThreeOrderUser order);
	
	public int queryOrderUserByMobile(ThreeOrderUser orderUser);

	public List<ThreeOrderCategory> queryCategory(ThreeOrderCategory category);

	public List<ThreeOrderProduct> queryProduct(ThreeOrderProduct product);
	
	public List<ThreeOrderAddress> queryAddress(ThreeOrder order);
	
	public ThreeOrderUser queryUserDetail(ThreeOrder order);
	
	public ThreeOrderPrice queryProductPrice(ThreeOrder order);
	
	public ThreeOrderAddress queryUserAddressDetail(ThreeOrder order);
	
	public int checkOrderStatus(Map<Object, Object> map);

	public ThreeOrderProduct queryProductDetail(ThreeOrder order);
	
	public List<ThreeOrderCard> queryCards(ThreeOrder order);

	public List<ThreeOrder> queryOrderList(ThreeOrder order);

	public List<Map<String, Object>> queryDictionary();

	public List<Map<String, Object>> queryOrderType();

	public List<Map<String, Object>> queryOrderCodeList();
	
	public Map<String, String> queryDeptName(Map<String, String> map);
	
	public List<String> queryThreeOrderCodeList();
	
	public List<Map<String, Object>> loadBaseCity();

	public Integer countThreeOrderOutRecord(ThreeOrder order);

	public List<ThreeOrder> queryThreeOrderOutRecord(ThreeOrder order);
	
	public Integer countOrderImportRecord(ThreeOrder order);
	
	public List<ThreeOrder> queryOrderImportRecord(ThreeOrder order);

	public Long queryIdByOrderCode(String orderCode);
	
	// 根据管家ID查询管家 
	public ThreeOrderUser queryUserDetailByUserId(Long userId);
	
	//查询管理员部门ID
	public Long queryManagerDeptId(Long id);
 
}
