package com.emotte.order.order.service;

import java.util.List;
import java.util.Map;

import com.emotte.order.order.model.*;
import org.wildhorse.server.core.model.Page;

public interface OrderBaseService {
	public StringBuffer queryBaseDictionary(String dictCode,int dictLength);
	public List<Dictionary> queryServerType(String dictCode,int dictLength);
	public StringBuffer queryDictionaryMapper(Long pid,String types);
	public List<Dictionary> queryServerChannel(String dkey);
	public StringBuffer queryServerChannelBak(String dkey);
	public StringBuffer queryOriginDictionary(String dkey);
	public List<OrderUser> queryUserMapper(OrderUser orderUser,Page page);
	public List<OrderUser> queryUserAddressMapper(OrderUser orderUser);
	public List<OrderUser> queryUserAddressByMobile(OrderUser orderUser);
	public void insertUser(OrderUser orderUser);
	public void insertUserAddress(OrderUser orderUser);
	public void updateUserAddress(OrderUser orderUser);
	public List<OrderBaseModel> queryBaseCity( String code, int length);
	public List<OrderBaseModel> queryOrderBasicServer(Long id);
	public List<OrderBaseModel> queryOrderNeedServer(OrderBaseModel orderBaseModel,Page page) throws Exception;
	public int checkedUserOrNot(String mobile);
	public OrderUser orderUserFollow(Order order);
	public List<Dictionary> querydeptname(Dictionary  dictionary);
	public List<Managers> queryguanjia(Managers managers);
	public List<OrderBaseModel> queryOrderBasicServertype(Long id);
	/**
	 * 通过用户电话，查询客户信息
	 * @param orderUser
	 * @return
	 */
	public OrderUser queryUserByMobile( OrderUser orderUser);
	/**
	 * 根据区县名称，查询城市cityCode
	 * @param orderUser
	 * @return
	 */
	public List<OrderUser> queryCityCodeByArea(OrderUser orderUser);
	/**
	 * 查询门店基地 带部门
	 * @param dictionary
	 * @return
	 */
	public List<Dictionary> querydeptnameWithDept(Dictionary dictionary);
	/**
	 * 查询所有用户
	 * @param userMobile
	 * @return
	 */
	public List<OrderUser> queryUserWithVip();
	/**
	 * 更新客户信息
	 * @param orderUser
	 * @return
	 */
	public void updateOrderUser(OrderUser orderUser);
	
	/**
	 * 渠道经理
	 * 查询渠道
	 * @param loginBy
	 * @return
	 */
	public List<Dictionary> queryOrderChannel(Dictionary dictionary);
	public int selectStock(Dictionary dictionary);
	public int checkNumStock(Dictionary dictionary);
	/**
	 * 查询城市关联分类
	 * @param category
	 * @return
	 */
	public List<Category> queryCategoryCity(Category category);
	/**
	 * 通过负责人id，查询负责人部门
	 * @param id
	 * @return
	 */
	public Long queryDeptIdById(Long id);
	/**
	 * 通过订单类型code查询订单类型名称
	 * @param dictionary
	 * @return 
	 */
	public Dictionary queryCategoryType(Dictionary dictionary);
	
	/**
	 * 传入管家是否看到
	* @Description: TODO 
	* @author lidenghui
	* @date 2018年7月25日 上午11:20:48 
	* @version
	* @param dkey
	* @param housekeeperAvailable
	* @return List<Dictionary>
	 */
	public List<Dictionary> queryServerChannel(String dkey, Integer housekeeperAvailable);
	
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

	public Map<String, String> queryShowMsg(PersonnelSchedule personnelSchedule);

	/**
	 * 推送页面查询服务人员信息卡方法
	 *
	 * @param orderId        订单ID
	 * @param personnelId    服务人员ID
     * @return
	 * @Author zhanghao
	 * @Date 20181011
     */
	Personnel findPersonnelDetailCard(Long orderId, Long personnelId);
}
