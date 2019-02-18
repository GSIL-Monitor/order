package com.emotte.order.authorg.service;

import java.util.List;
import java.util.Map;

import org.wildhorse.server.core.exception.BaseException;

import com.emotte.order.authorg.model.Org;
import com.emotte.order.order.model.Order;
public interface OrgService {
	/**
	 * 加载数据
	 * @param id
	 * @return
	 * 2017年01月09日
	 */
	public Org load(Long id);
	/**
	 * 查询
	 * @param org
	 * @return
	 * 2017年01月09日
	 */
	public List<Org> query(Org org);
	/**
	 * 分页查询
	 * @param org
	 * @return
	 * 2017年01月09日
	 */
	public List<Org> queryByPage(Org org);
	/**
	 * 查询导出数据
	 * @param org
	 * @return
	 * 2017年01月09日
	 */
	public List<Map<String, Object>> queryExport(Org org);
	/**
	 * 插入数据
	 * @param org
	 * @return
	 * 2017年01月09日
	 */
	public boolean insert(Org org) throws BaseException;
	/**
	 * 更新数据
	 * @param org
	 * @return
	 * 2017年01月09日
	 */
	public boolean update(Org org) throws BaseException;
	/**
	 * 删除
	 * @param ids
	 * @return
	 * 2017年01月09日
	 */
	public boolean multiDelete(String[] ids) throws BaseException;
	/**
	 * 批量插入
	 * @param data
	 * @return
	 * 2017年01月09日
	 */
	public boolean multiInsert(List<Map<String, String>> data) throws BaseException;
	/**
	 * 查询渠道所属门店，基地
	 * @param channel
	 * @param cityCode
	 * @param list
	 * @return
	 * 2017年1月9日
	 */
	public List<Org> queryByChannel(String channel, String cityCode, List<Org> list);
	/**
	 * 查询商品所属门店，基地
	 * @param channel
	 * @param cityCode
	 * @param list
	 * @return
	 * 2017年1月9日
	 */
	public List<Org> queryByProduct(String channel, String cityCode, List<Org> list);
	/**
	 * 查询最近的门店，基地
	 * @param longitude 经度
	 * @param latitude 维度
	 * @param city
	 * @param list
	 * @return
	 * 2017年1月10日
	 */
	public Org queryByDistance(String longitude, String latitude, String city, List<Org> list);
	/**
	 * 查询呼叫中心
	 * @return
	 * 2017年1月10日
	 */
	public List<Org> queryCallCenter();
	/**
	 * 根据渠道，商品，城市筛选部门
	 * @param orderChannel
	 * @param productCode
	 * @param city
	 * @param list
	 * @return
	 * 2017年1月19日
	 */
	public List<Org> queryByChannelAndProduct(String orderChannel, String productCode, String city, List<Org> list);
	
	/**
	 * 根据商品编码，查询智能小管家，商品ID
	 * @param productCode
	 * @return
	 */
	public Long queryProduct(String productCode);
	
	/**
	 * 查询所有的门店，基地
	 * @param longitude 经度
	 * @param latitude 维度
	 * @param city
	 * @param productCode 
	 * @param orderChannel 
	 * @param list
	 * @return
	 */
	public List<Org> queryAllOrgByDistance(String longitude, String latitude, String city, List<Org> list);
	/**
	 * 查询在接单范围的门店，基地
	 * @param acceptRange 门店基地接单范围
	 * @param longitude	  精度
	 * @param latitude 纬度
	 * @param city
	 * @return
	 */
	public Org queryByAcceptRange(String acceptRange, String longitude, String latitude, String city, List<Org> list);
	
	/**
	 * 北京自动分发新流程
	 * @return
	 */
	public List<Org> distributeInPeking();
	/**
	 * 插入订单轮询负责管家记录
	 * @param org
	 * @return
	 */
	public int insertPollRecord(Org org);
	/**
	 * 查询客户最后一次下单的管家
	 */
	public Order queryLastRechargeUser(Order order);
	/**
	 * 查询所有的门店，基地
	 * @param longitude 经度
	 * @param latitude 维度
	 * @param city
	 * @param productCode 
	 * @param orderChannel 
	 * @param list
	 * @return
	 */
	public List<Org> queryAllOrgByDistance3(Order order);
	/**
	 * 自动轮单
	 */
	public Long distributeInPeking3(Integer cateType,Long deptId);
	/**
	 * 更新论单次数
	 */
	public int updateDistribute(Long managerId);
	
	/**
	 * 查询是否为管家
	 */
	public boolean queryGuanjia(Long createBy);
}