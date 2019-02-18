package com.emotte.order.order.mapper.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.ThreeOrderIn;

@Component("reThreeOrderInMapper")
public interface ReThreeOrderInMapper extends ReBaseMapper {
	/**
	 * 获得订单
	 * 
	 * @param orderUser
	 * @return List<OrderUser>
	 */
	public Order loadThreeOrder(Long id);

	public List<Order> queryOrder(Order order);

	public Integer countOrder(Order order);

	public List<Order> queryOrderBasicItem(Long id);

	public List<String> loadThreeOrderId();

	public List<Map<String, Object>> queryDictionary();
	
	public List<Map<String, Object>> queryChannel();
	
	public List<Map<String, Object>> queryOrderChannel();

	public List<Map<String, Object>> queryBaseCity();

	public Long getRandomId();
	
	public List<ThreeOrderIn> exportList(Order order);

	public List<Map<String, Object>> queryOrderType();

	public String checkProductCode(String productCode);

	public Long findUserIdByUserMobile(String userMobile);

	public List<ThreeOrderIn> queryThreeOrderRecord(@Param("beginRow") String beginRow,@Param("pageSize") String pageSize,@Param("recordStartTime") String recordStartTime,@Param("recordEndTime") String recordEndTime);

	public Integer countThreeOrderRecord(@Param("recordStartTime") String recordStartTime,@Param("recordEndTime") String recordEndTime);

}
