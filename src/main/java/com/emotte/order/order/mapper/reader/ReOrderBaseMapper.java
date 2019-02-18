package com.emotte.order.order.mapper.reader;

import java.util.List;
import java.util.Map;

import com.emotte.order.order.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.Category;
import com.emotte.order.order.model.Dictionary;
import com.emotte.order.order.model.ItemInterview;
import com.emotte.order.order.model.Managers;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderBaseModel;
import com.emotte.order.order.model.OrderUser;

@Component("reOrderBaseMapper")
public interface ReOrderBaseMapper extends ReBaseMapper{
	public List<Dictionary> queryBaseDictionary(Dictionary dictionary); // 查 t_base_dictionary
	public List<Dictionary> queryServerType(Dictionary dictionary); // 查 t_product_category
	public List<Dictionary> queryDictionaryMapper(Dictionary dictionary); // 查 t_order_dictionary
	public List<Dictionary> queryOriginDictionary(Dictionary dictionary); // 查籍贯
	public Integer countUserMapper(OrderUser orderUser); // 用户
	public List<OrderUser> queryUserMapper(OrderUser orderUser); // 用户
	public List<OrderUser> queryUserAddressMapper(OrderUser orderUser); // 用户
	public List<OrderUser> queryUserAddressOrder(OrderUser orderUser);
	public List<OrderUser> queryUserAddressByMobile(OrderUser orderUser); // 查用户地址
	public List<OrderBaseModel> queryBaseCity(OrderBaseModel baseModel); // 查城市
	public List<OrderBaseModel> queryOrderBasicServer(Long id); // 查服务订单基本信息
	public Integer countOrderNeedServer(OrderBaseModel orderBaseModel); // 查服务订单基本信息
	public Long queryOrderWorkTypeId(Long orderId); // 通过订单ID查订单对应的工种
	public List<OrderBaseModel> queryOrderNeedServer(OrderBaseModel orderBaseModel); //查询服务人员信息
	public List<OrderBaseModel> queryOrderNeedServerByPersonId(Long id);//查询单个明细
	public List<OrderBaseModel> queryOrderNeeds(OrderBaseModel orderBaseModel); // 匹配人员信息
	public OrderUser querLoadUser(OrderUser user);
	public List<OrderUser> queryOrderUserFollow(OrderUser user);// 查询当前录入人及部门
	public OrderUser queryOrderUserFollowByOrg(Order order);// 得到最近门店或门店基地
	public OrderUser queryOrderUserManagerById(Long id);// 查询当前录入人及部门
	public List<Dictionary> queryServerChannel(Dictionary dictionary);//查询订单渠道
	public List<Dictionary> queryServerChannelNew(Dictionary dictionary);//查询订单渠道
	public List<Dictionary> querydeptname(Dictionary dictionary);  //查询门店基地
	public List<Managers> queryguanjia(Managers managers);    //查询管家
	public List<OrderBaseModel> queryOrderBasicServerType(Long id);//修改延续性类型
	public OrderUser queryUserByMobile(OrderUser userMobile);//根据电话查询用户信息
	public List<OrderUser> queryCityCodeByArea(OrderUser orderUser);//根据区县名称，查询城市cityCode
	public List<Dictionary> querydeptnameWithDept(Dictionary dictionary);
	//查询所有用户
	public List<OrderUser> queryUserWithVip();
	public List<Dictionary> queryOrderChannel(Dictionary dictionary);//渠道经理查询渠道
	public int selectStock(Dictionary dictionary);//查询城市 商品库存
	public int checkNumStock(Dictionary dictionary);//查询库存数量
	public List<ItemInterview> queryOrderItemInterview(ItemInterview itemInterview);//查询订单是否有下户人员
	public OrderBaseModel queryOrdertypeLevelWorkTypeId(Long orderId); // 通过订单ID查订单对应的工种  工种等级
	public OrderBaseModel matchingPersonnel(OrderBaseModel orderBaseModel);//匹配服务人员
	public List<Category> queryCategoryCity(Category category);//查询城市关联分类
	public Long queryDeptIdById(Long id);//通过负责人id，查询负责人部门
	public Dictionary queryCategoryType(Dictionary dictionary);//通过订单类型code查询订单类型名称
	public List<Dictionary> queryChannels(Dictionary dictionary);

	/**
	 * 根据ids查询服务人员信息
	 *
	 * @param personnelIds
	 * @return
	 * @Author zhanghao
	 * @Date 20180927
	 */
	  List<Personnel> checkPersonnelText(List<Long> personnelIds);

	/**
	 * 根据服务人员ID查询管理状态
	 *
	 * @param orderBaseModel
	 * @return
	 * @Author zhanghao
	 * @Date 20180928
	 */
	List<Personnel> checkOrderTrue(OrderBaseModel orderBaseModel);

	public Map queryShowMsg(PersonnelSchedule personnelSchedule);

	/**
	 * 推送页面查询服务人员信息卡方法
	 *
	 * @param orderId		订单ID
	 * @param personnelId	服务人员ID
	 * @return
	 * @Author zhanghao
	 * @Date 20181011
	 */
	Personnel findPersonnelDetailCard(@Param("orderId") Long orderId, @Param("personnelId") Long personnelId);
}