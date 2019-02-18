package com.emotte.order.order.service;

import com.emotte.order.order.model.*;
import org.wildhorse.server.core.dao.redis.exception.RedisAccessException;
import org.wildhorse.server.core.model.Page;

import java.util.HashMap;
import java.util.List;

public interface AfterSalesService {

	public AfterSalesNew loadAfterSales(Long id);

	public List<AfterSalesNew> queryAfterSales(AfterSalesNew afterSalesNew, Page page);

	public void insertAfterSales(AfterSalesNew afterSalesNew);

	public void updateAfterSales(AfterSalesNew afterSalesNew);

	/**
	 * 根据用户手机号查询关联方案订单表
	 *
	 * @param phoneNumber
	 * @return
     */
	List<Solution> findSolutionByPhone(String phoneNumber);
	
	/**
	 * 添加分期售后
	 * @author lidenghui  
	 * @date 2018年5月28日 下午4:40:04
	 */
	public void insertOrUpdateInstallmentAfterSales(AfterSalesNew afterSalesNew);

	public List<Order> queryOrder(Order order);
	
	public List<AfterSalesNew> findPayFeeDetail(Long orderId,Long payFeeId);

	public AfterSalesNew queryFqafterSales(Long id);

	/**
	 * 根据订单ID查询手续费退费明细列表
	 *
	 * @param afterSalesNew		查询条件对象
	 * @return
     */
	List<AfterSalesNew> findServiceChargeList(AfterSalesNew afterSalesNew);

	public List<BaseCity> queryCity();

	void buyBackAfterSales(String afterSalesNew) throws RedisAccessException;

	/**
	 * 根据订单id、服务人员服务费批次查询售后单信息
	 *
	 * @param orderId
	 * @param bathno
     * @return
     */
	OrderAfterSales findBuyBackAfterSales(Long orderId, Long bathno);

	public List<Solution> querySolution(Solution solution);

	public List<CustSolutionPlan> querySolutionSchedule(Long id);

	/**
	 *
	 * 功能描述:
	 *
	 * @param: [afterSalesNew]									查询参数封装对象
	 * @return: com.emotte.order.order.model.AfterSalesNew		卡售后信息封装对象
	 * @auther: lenovo
	 * @date: 2018/6/19 16:34
	 */
	List<AfterSalesNew> findCardDetailForCardAfterSales(AfterSalesNew afterSalesNew);

	/**
	 * 根据卡ID查询卡售后详情
	 *
	 * @param cardId	卡ID
	 * @return
	 * @auther: lenovo
	 * @date: 2018/6/21 16:09
     */
	AfterSalesNew findCardAfterSalesDetail(Long cardId);

	public AfterSalesNew querySolutionMoney(Long id);

	public Integer selectPremiumCheck(String orderId, String afterSalesKind);
	
	/**
	 * 查询订单可退工资总金额(不含:券,他人代收,白条支付,管家帮分期/海金保理分期,培训分期,唯品会白条,招行分期,唯品会白条支付,消费金额分期还款,汇嘉分期)
	 * @param orderAfterSales
	 * @return
	 */
	HashMap<String, Object> queryAfterReturnableSalary(AfterSalesNew afterSalesNew);
	
	/**
	 * 查询订单可退客户信息费总金额
	 * @param orderAfterSales
	 * @return
	 */
	HashMap<String, Object> queryAfterReturnableMessageFee(AfterSalesNew afterSalesNew);
	/**
	 * 
	 * @Description:验证工资是否有海金缴费 
	 * @param payfeeId
	 * @return      
	 * @return: Integer
	 * @author:ZhouXin
	 * @Date:2019年1月18日
	 * @throws
	 */
	Integer selectAgreementSeaGlodPayFree(String payfeeId);

}