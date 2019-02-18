package com.emotte.order.order.mapper.reader;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.emotte.order.authmaner.model.Manager;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.Dictionary;
import com.emotte.order.order.model.ItemInterview;
import com.emotte.order.order.model.Managers;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.PojoPersonnel;

@Component("reOrderMapper")
public interface ReOrderMapper extends ReBaseMapper {

	public Order loadOrder(Long id);
	
	public Order loadOrderByOrder(Order order);

	public List<Order> queryOrder(Order order);

	public List<Order> queryOrderNoPage(Order order);

	public Integer countOrder(Order order);

	public List<Order> queryOrderBasicItem(Long id);

	public List<Order> queryAttendanceOrderlistPage(Order order);

	public Long queryOrderId();

	// 查询门店，基地
	public List<Dictionary> queryFollowDept();

	// 管家
	public List<Dictionary> queryFollowBy(Dictionary dictionary);
	
	public List<Order> queryOrderNotInItem();
	
	//是否存在服务人员服务费记录
	public Boolean existsSalary(Order order);
	
	//是否存在上户记录
	public Boolean existsInterview(Order order);

	public List<Order> queryOrderReMatch(Order order);
	//查询系统时间
	public String querySysdate();
	//查询负责人订单（高职交接转单使用）
	public List<Order> queryOrderRechargeBy(Order order);
	//查询服务人员
	public Integer countPerson(Order order);
	public List<Map<String, Object>> queryPersonlistPage(Order order);

	public Integer countOrderBirthDate(Order order);

	public List<Order> queryOrderBirthDate(Order order);
	
	public List<Order> exoutOrder(Order order);
	//根据订单ID，查询物流名称和物流号
	public Order loadParcelByOrderId(Long id);
	//根据用户ID查询订单
	public List<Order> queryOrderByUserId(Long userId);
	//查询客户所有符合条件的缴费
	public List<Map<String, Object>> queryOrderUserAllFee();
	//查询渠道订单列表
	public List<Order> queryChannelOrder(Order order);
	//查询渠道订单列表总数量
	public Integer countChannelOrder(Order order);
	//根据三方订单ID，查询订单信息
	public Order loadOrderByPlateId(String oId);
	//根据订单ID查询服务开始时间
	public String queryServiceStartTime(Long orderId);
	//查询登录用户渠道经理类型
	public Integer queryChannelManagerType(Long id);
	//查询呼叫中心所有人员
	public List<Managers> queryCallCenter();
	//查询当前订单服务人员下一订单负责人
	public List<ItemInterview> queryRechargeBy(ItemInterview itemInterview);
	//查询当前订单服务人员是否与下一个订单冲突
	public List<ItemInterview> queryByConflict(ItemInterview itemInterview);

	/**
	 * 根据订单编号和品类查询订单
	 * @param orderCode 订单编号
	 * @param category 品类名称
	 * @return
	 * 2017年9月10日
	 */
	public Order loadOrderByCodeAndCategory(Long orderCode, String category);

	public List<Map<String, Object>> orderEmpPushRminds();
	
	//查询需要转单的订单
	public List<Order> queryChangeOrder(Order order);

	public PojoPersonnel downloadEmpFileCard(Long empId);
	//根据客户电话，查询订单负责人、负责部门
	public List<Order> queryOrderByUserMobile(String userMobile);

	public Map<String, Object> querySolutionResponsiblePerson(Long orderId);

	public Map<String, String> querySolutionUserMobile(Order order);

	public Map<String, String> queryOrderUserMobile(Order order);

	/**
	 * 按登录标识查询推荐人
	 * @param oauthCode
	 * @return
	 */
	public Long queryRecommendUserId(String oauthCode);

	/**
	 * 根据订单ID查询订单对象
	 *
	 * @param orderId	订单ID
	 * @return
	 * @auther: lenovo
	 * @date: 2018/7/16 10:55
     */
	Order findOrderById(Long orderId);

	/**
	 *
	 * 功能描述: 根据ID查询管家信息
	 *
	 * @param: authManagerId	管家ID
	 * @return:
	 * @auther: lenovo
	 * @date: 2018/7/16 17:14
	 */
	Manager findManagerById(Long authManagerId);

	/**
	 * 根据订单ID查询缴费总额
	 *
	 * @param orderId
	 * @return
	 * @Author zhanghao
	 * @Date 20180927
     */
	BigDecimal checkAccountTotal(Long orderId);
	
	/**
	 * 根据订单ID，查询缴费金额是否大于订单的金额
	 * @param orderId 订单编号
	 * 操作人：周鑫
	 * 2018年10月31日下午4:04:04
	 */
	public Long queryPayCompareFeeSum(Long orderId);
	/**
	 * 查询订单的有效合同（T.Agreement_state IN(2,3) 是否与当前时间在30天之内
	 * @param orderId 订单ID
	 * 操作人：周鑫
	 * 2018年11月14日下午2:45:44
	 */
	public Long queryAgreenmentDate(Long orderId);
	/**
	 * 查询，录入订单的相关回显信息
	 * 操作人：周鑫
	 */
	public Map<String, Object> queryOrderEcho(Long orderId);
}