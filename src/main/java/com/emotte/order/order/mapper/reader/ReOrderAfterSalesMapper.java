package com.emotte.order.order.mapper.reader;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderAfterSales;
import com.emotte.order.order.model.Payfee; 
@Component("reOrderAfterSalesMapper") 
public interface ReOrderAfterSalesMapper extends ReBaseMapper{ 
	
	public List<OrderAfterSales> queryOrderAfterSaleslistPage(OrderAfterSales orderaftersales);

	public OrderAfterSales loadAfterSales(Long id);

	public List<OrderAfterSales> queryAfterSalesApprovelistPage(OrderAfterSales orderAfterSales);
	/**
	 * 查询唯品会退费售后单
	 * @param orderAfterSales
	 * @param page
	 * @return
	 */
	public List<OrderAfterSales> queryVPHSaleslistPage(OrderAfterSales orderAfterSales);
	/**
	 * 查询唯品会分期缴费客户
	 * @param order
	 * @return
	 */
	public List<Order> queryVPHOrderlistPage(Order order);
	/**
	 * 查询唯品会白条最大退款金额
	 * @param orderAfterSales
	 * @return
	 * @author wn
	 */
	public String queryVPHMaxMoney(OrderAfterSales orderAfterSales);
	/**
	 * 根据订单id查询合同一次分账总金额
	 * @param orderId
	 * @return
	 */
	public String queryOrderMemberAccount(Long orderId);
	/**
	 * 根据订单id查询缴费明细扣除的信息费
	 * @param orderId
	 * @return
	 */
	public String queryMemberDetailFee(Long orderId);
	/**
	 * 根据订单id查询用缴费发的服务人员服务费明细
	 * @param orderId
	 * @return
	 */
	public String querySalaryDetail(Long orderId);
	/**
	 * 根据订单id查询用缴费发的服务人员服务费明细(售后之前退的服务人员服务费)
	 * @param orderId
	 * @return
	 */
	public String querySalaryDetailFee(Long orderId);
	/**
	 * 查询白条余额
	 * @param orderId
	 * @return
	 */
	public String queryVphSum(Long orderId);
	/**
	 * 根据订单id 查询白条结算单个数
	 * @param orderId
	 * @return
	 */
	public List<Payfee> queryVPHAccount(Long orderId);
	/**
	 * 根据结算单id，计算白条退费金额
	 * @param id
	 * @return
	 */
	public String queryVPHFee(Long accountId);
	/**
	 * 查询要迁至订单
	 * @param order
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

	
	public Long queryDepartment(Long deptId);

	public List<OrderAfterSales> queryAfterSalesByDepartmentlistPage(OrderAfterSales orderAfterSales); 
	
	/**
	 * 新查询售后列表
	 * @param orderaftersales
	 * @author zs
	 * @return
	 */
	public List<OrderAfterSales> queryAfterSalesNewlistPage(OrderAfterSales orderaftersales);
	
	/**
	 * 新查询售后审核列表
	 * @param orderAfterSales
	 * @author zs
	 * @return
	 */
	public List<OrderAfterSales> queryAfterSalesApproveNewlistPage(OrderAfterSales orderAfterSales);

	/**
	 * 根据订单ID查询有效售后记录
	 *
	 * @param id	订单ID
	 * @return
     */
	Integer checkOnlyAfterSales(Long id);
}
