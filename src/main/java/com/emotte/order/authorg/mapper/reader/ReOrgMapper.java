package com.emotte.order.authorg.mapper.reader;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.authorg.model.Org;
import com.emotte.order.order.model.Order;


@Component("reOrgMapper")
public interface ReOrgMapper extends ReBaseMapper {
	/**
	 * 加载
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
	 * 查询渠道所属部门
	 * @param channelId 订单渠道
	 * @param cityCode 订单城市编码
	 * @param list
	 * @return
	 * 2017年1月9日
	 */
	public List<Org> queryByChannel(@Param("channelId")String channelId, @Param("cityCode")String cityCode, @Param("orgList")List<Org> list);
	/**
	 * 查询商品所属部门
	 * @param productCode 订单商品码
	 * @param cityCode 订单城市编码
	 * @param list
	 * @return
	 * 2017年1月9日
	 */
	public List<Org> queryByProduct(@Param("productCode")String productCode, @Param("cityCode")String cityCode, @Param("orgList")List<Org> list);
	/**
	 * 查询距离最近的门店，基地
	 * @param longitude 订单经度
	 * @param latitude 订单纬度
	 * @param cityCode 订单城市编码
	 * @param list
	 * @return
	 * 2017年1月10日
	 */
	public Org queryByDistance(@Param("longitude")String longitude, @Param("latitude")String latitude, @Param("cityCode")String cityCode, @Param("orgList")List<Org> list);
	/**
	 * 呼叫中心
	 * @return
	 * 2017年1月10日
	 */
	public List<Org> queryCallCenter();
	/**
	 * 根据城市，渠道，商品过滤
	 * @param orderChannel
	 * @param productCode
	 * @param city
	 * @param list
	 * @return
	 * 2017年1月19日
	 */
	public List<Org> queryByChannelAndProduct(@Param("channelId")String orderChannel, @Param("productCode")String productCode, @Param("cityCode")String city, @Param("orgList")List<Org> list);
	/**
	 * 根据商品编码，查询商品表ID
	 * @param productCode
	 * @return
	 */
	public Long queryProduct(String productCode);
	
	/**
	 * 查询所有的门店，基地
	 * @param longitude 经度
	 * @param latitude 维度
	 * @param cityCode
	 * @param list
	 * @return
	 */
	public List<Org> queryAllOrgByDistance(@Param("longitude")String longitude, @Param("latitude")String latitude, @Param("cityCode")String cityCode, @Param("orgList")List<Org> list);
	/**
	 * 查询在接单范围的门店，基地
	 * @param longitude 经度
	 * @param latitude 维度
	 * @param cityCode
	 * @param list
	 * @return
	 */
	public Org queryByAcceptRange(@Param("acceptRange")String acceptRange,@Param("longitude")String longitude, @Param("latitude")String latitude, @Param("cityCode")String cityCode, @Param("orgList")List<Org> list);
	/**
	 * 北京自动分发新流程
	 * @return
	 */
	public List<Org> distributeInPeking();
	/**
	 * 查询客户最后一次下单的管家
	 */
	public Order queryLastRechargeUser(Order order);
	/**
	 * 查询所有的门店，基地
	 * @param longitude 经度
	 * @param latitude 维度
	 * @param cityCode
	 * @param list
	 * @return
	 */
	public List<Org> queryAllOrgByDistance3(Order order);
	/**
	 * 自动分发新流程
	 * @return
	 */
	public Long distributeInPeking3(@Param("cateType")Integer cateType,@Param("deptId")Long deptId);
	
	public Long queryGuanjia(Long createBy);
}