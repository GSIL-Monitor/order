package com.emotte.order.order.service;

import java.util.List;
import java.util.Map;

import org.wildhorse.server.core.model.Page;

import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.ThreeOrderIn;
import com.emotte.order.order.model.ThreeOrderInModel;
import com.emotte.order.order.model.ThreeOrderItemDetailServer;

public interface ThreeOrderInService {

	public Order loadOrder(Long id);

	public List<Order> queryOrder(Order order, Page page);

	
	
	public List<String> loadThreeOrderId();

	/**
	 * 
	 * checkList(这里用一句话描述这个方法的作用)
	 * 
	 * 检测数据格式是否正确，是否有重复
	 *
	 * 
	 * @Title: checkList
	 * @Description: TODO
	 * @param @param list
	 * @param @return 设定文件
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	public Map<String, Object> checkList(List<Map<String, String>> list);

	/**
	 * @param orders
	 * 
	 * saveThreeOrderIn(这里用一句话描述这个方法的作用)
	 * 
	 * TODO(这里描述这个方法适用条件 – 可选)
	 * 
	 * TODO(这里描述这个方法的执行流程 – 可选)
	 * 
	 * TODO(这里描述这个方法的使用方法 – 可选)
	 * 
	 * TODO(这里描述这个方法的注意事项 – 可选)
	 *
	 * 
	 * @Title: saveThreeOrderIn
	 * @Description: TODO
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public void saveThreeOrderIn(List<Order> orders);

	public void saveThreeOrderInItem(List<Order> orders);

	public void saveThreeOrderInServerDetail(List<Order> orders);

	public void createId(List<Order> orders);

	public void createItemId(List<Order> orders);

	public List<ThreeOrderIn> exportToExcel(Order order);
	


	public List<Map<String, Object>> queryDictionary();

	public List<Map<String, Object>> queryBaseCity();

	public List<Map<String, Object>> queryOrderType();
	
	public void insertOrder(Map<String, String> list, Long createBy, List<Map<String, Object>> dictionary, List<Map<String, Object>> addresses, List<Map<String, Object>> orderTypes) throws Exception;

	public void insertFaOrder(Map<String, String> map, Long createBy, List<Map<String, Object>> dictionary,
			List<Map<String, Object>> addresses, List<Map<String, Object>> orderTypes) throws Exception;
	
	/**
	 * 保存订单详情
	 * 
	 * @param threeOrderItemDetailServer
	 */
	public void saveOrderAndItem(ThreeOrderItemDetailServer threeOrderItemDetailServer);

	/**
	 * 保存FA订单 
	 */
	public void saveThreeOrderFaItem(ThreeOrderInModel order) throws Exception ;

	public void savaExcelRecord(String url, Long createBy,String fileName);

	public List<Order> queryOrderBasicItem(Long id);

	public List<ThreeOrderIn> queryThreeOrderRecord(String recordStartTime, String recordEndTime, Page page);

	public List<Map<String, Object>> queryChannel();
	
	public List<Map<String, Object>> queryOrderChannel();

	/**
	 * 验证用户是否有管家
	 * @param userId
	 * @return
	 */
	public Long checkUserKeeper(Long userId);
	/**
	 * 获取管理员部门ID
	 * @param id
	 * @return
	 */
	public Long queryManagerDeptId(Long id);

}
