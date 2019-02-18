package com.emotte.order.order.service;

import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderAfterSales;
import org.wildhorse.server.core.model.Page;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderAfterSalesService {

	public OrderAfterSales loadOrderAfterSales(Long id);

	public List<OrderAfterSales> queryOrderAfterSales(OrderAfterSales orderAfterSales, Page page);

	public String insertOrderAfterSales(OrderAfterSales orderAfterSales) throws Exception;

	public void updateOrderAfterSales(OrderAfterSales orderAfterSales);

	public List<OrderAfterSales> queryAfterSalesApprove(OrderAfterSales orderAfterSales, Page page);
	/**
	 * 查询唯品会退费售后单
	 * @param orderAfterSales
	 * @param page
	 * @return
	 */
	public List<OrderAfterSales> queryVPHSales(OrderAfterSales orderAfterSales, Page page);
	/**
	 * 查询使用唯品会分期用户
	 * @param order
	 * @param page
	 * @return
	 */
	public List<Order> queryVPHOrder(Order order, Page page);
	/**
	 * 新增唯品会分期退费售后单
	 * @param orderAfterSales
	 * @author wn
	 * @throws Exception 
	 */
	public void insertVphAfterSales(OrderAfterSales orderAfterSales);
	/**
	 * 修改唯品会分期退费售后单
	 * @param orderAfterSales
	 * @author wn
	 */
	public void updateVphAfterSales(OrderAfterSales orderAfterSales);
	/**
	 * 查询唯品会白条最大退款金额
	 * @param orderAfterSales
	 * @return
	 * @author wn
	 */
	public String queryVPHMaxMoney(OrderAfterSales orderAfterSales);
	/**
	 * 查询信息费最大退款金额
	 * @param orderId
	 * @return
	 */
	public String queryMemberMoney(Long orderId);
	/**
	 * 查询应退服务人员服务费最大退款金额
	 * @param orderId
	 * @return
	 */
	public String querySalaryMoney(Long orderId);
	/**
	 * 插入售后单
	 * @param orderAfterSales
	 */
	public void insertSaleServer(OrderAfterSales orderAfterSales) throws Exception;
	/**
	 * 查询白条余额
	 * @param orderId
	 * @return
	 */
	public String queryVphSum(Long orderId);
	/**
	 * 新建迁单
	 * @param orderAfterSales
	 * @return 
	 */
	public String insertChangeOrder(OrderAfterSales orderAfterSales);
	/**
	 * 查询要迁至订单
	 * @param order
	 * @param page
	 * @return
	 */
	public List<Order> queryOrderA(Order order);

	public List<Map<String, String>> isOrderSale(Long orderId);

	/**
	 * 查询是否是非月嫂和延续订单
	 * @author lidenghui  
	 * @date 2018年4月27日 下午8:26:19
	 */
	public Order queryOrder(Long orderId);
	
	/**
	 * 更新白条订单余额
	 */
	public void updateTotalPayById(OrderAfterSales orderAfterSales);

	
	//判断是不是结算中心得账号
	public Long queryDepartment(Long deptId);
	
	/**
	 * 查询售后跟据部门(结算中心)
	 * @param queryAfterSalesByDepartment
	 * @param page
	 * @return
	 */
	public List<OrderAfterSales> queryAfterSalesByDepartment(OrderAfterSales orderAfterSales, Page page);

	/**
	 * 根据ID删除售后单
	 *
	 * @param id
	 * @param i
	 * @return
     */
	void changeAfterSalesValid(Long id, int i);

	/**
	 * 移动端售后终止合同释放排期
	 *
	 * @param orderId
	 * @param createBy
     */
	void updateAgreementAndEmpScheduleForApp(Long orderId,Long createBy);

	/**
	 * 普通售后,按缴费方式汇总缴费明细
	 * @param orderId
	 * @return
	 */
	public List<HashMap<String, Object>> queryPayGroupBy(Long orderId);

	/**
	 * 普通售后根据订单ID查询退款金额
	 *
	 * @param orderId
	 * @return
	 * @Author zhanghao
	 * @Date 20181214
     */
	Map<String,BigDecimal> queryAfterSalesAmountByOrderId(Long orderId);
	/**
	 * 
	 * @Description: 查询商品编码  
	 * @param orderId
	 * @return      
	 * @return: List<String>
	 * @author:ZhouXin
	 * @Date:2019年1月18日
	 * @throws
	 */
	List<String> queryProductByOrderId(Long orderId);
	/**
	 * 
	 * @Description: 根据订单ID 查询是否包含制定的三级分类商品   
	 * @param orderId
	 * @return      
	 * @return: List<String>
	 * @author:ZhouXin
	 * @Date:2019年1月21日
	 * @throws
	 */
	Integer selectProductThreeCode(Long orderId);
}