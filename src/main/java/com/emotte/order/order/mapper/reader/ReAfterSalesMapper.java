package com.emotte.order.order.mapper.reader;

import com.emotte.order.order.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("reAfterSalesMapper")
public interface ReAfterSalesMapper extends ReBaseMapper {

	public AfterSalesNew loadAfterSales(Long id);

	public List<AfterSalesNew> queryAfterSales(AfterSalesNew afterSalesNew);

	public Integer countAfterSales(AfterSalesNew afterSalesNew);

	/**
	 * 根据卡号查询提成及账号
	 *
	 * @param cardNumber	卡号
	 * @return
     */
	ModeServeBalancePayment findMonyAndAccountByCardNumber(@Param("cardNumber") String cardNumber);

	/**
	 * 根据id查询方案信息
	 *
	 * @param solutionId	方案ID
	 * @return
	 */
	ModeSolutionCustSolution findSolutionById(@Param("solutionId") Long solutionId);

	/**
	 * 根据方案ID查询排期订单集合
	 *
	 * @param solutionId	方案ID
	 * @return
     */
	BigDecimal findPlanBalanceBySolutionId(Long solutionId);

	/**
	 * 校验方案是否赠送
	 *
	 * @param solutionId	方案ID
	 * @return
     */
	ModeSolutionCustSolution checkSolutionIsFree(@Param("solutionId") Long solutionId);

	/**
	 * 根据方案id查询方案中的代扣卡编号
	 *
	 * @param solutionId	方案ID
	 * @return
     */
	String findCardNumberForSolution(@Param("solutionId") Long solutionId);

	/**
	 * 根据售后单ID查询退款金额
	 *
	 * @param id	售后单
	 * @return
     */
	BigDecimal findVPHById(@Param("id") Long id);

	/**
	 * 根据订单号查询缴费明细
	 *
	 * @param orderId	订单ID
	 * @return
	 */
	List<AfterSalesNew> findPayFeeDetail(@Param("orderId") Long orderId,@Param("payFeeId") Long payFeeId);
	
	/**
	 * 查询订单列表
	 * @return
	 */
	List<Order> queryOrder(Order rder);

	public AfterSalesNew queryFqafterSales(Long id);

	/**
	 * 根据订单ID查询分期手续费列表
	 *
	 * @param orderId		订单ID
	 * @param payFeeId 
	 * @return
     */
	List<AfterSalesNew> findServiceChargeList(@Param("orderId") Long orderId,@Param("payFeeId") Long payFeeId);

	/**
	 * 根据订单查询手续费退费金额
	 *
	 * @param orderId	订单ID
	 * @return
     */
	BigDecimal findServiceCharge(Long orderId);

	public List<BaseCity> queryCity();

	/**
	 * 根据售后单ID查询退款金额
	 *
	 * @param id	售后单
	 * @return
	 */
	BigDecimal findOtherAmountById(@Param("id") Long id);

	/**
	 * 根据订单id、服务人员服务费批次查询售后单信息
	 *
	 * @param orderId
	 * @param bathno
     * @return
     */
	OrderAfterSales findBuyBackAfterSales(@Param("orderId") Long orderId, @Param("bathno") Long bathno);

	public List<Solution> querySolution(Solution solution);

	public List<CustSolutionPlan> querySolutionSchedule(Long id);

	/**
	 *
	 * 功能描述: 根据电话或卡号查询卡信息列表
	 *
	 * @param: afterSalesNew
	 * @return: List<AfterSalesNew>
	 * @auther: lenovo
	 * @date: 2018/6/19 16:58
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

	/**
	 * 根据ID查询售后单
	 *
	 * @param id	售后ID
	 * @return
     */
	OrderAfterSales findAfterSales(Long id);
	/**
	 * 
	 * 操作人：周鑫
	 * 2018年12月12日下午4:36:23
	 */
	public Integer queryPremiumCheck(@Param("orderId") String orderId,@Param("afterSalesKind") String afterSalesKind);

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
	 * 查询售后单银行账户信息
	 *
	 * @param afterSalesId
	 * @return
	 * @Author zhanghao
	 * @Date 20181227
	 */
	OrderAfterSales findCustomerByAfterSalesId(Long afterSalesId);
	
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
	 * @Description: 验证工资是否有海金缴费    
	 * @param payfeeId
	 * @return      
	 * @return: Integer
	 * @author:ZhouXin
	 * @Date:2019年1月18日
	 * @throws
	 */
	public Integer queryAgreementSeaGlodPayFree(String payfeeId);
	
	
	/**
	 * 
	 * @Description: 查询指定订单的商品Code   
	 * @param orderId
	 * @return      
	 * @return: List
	 * @author:ZhouXin
	 * @Date:2019年1月18日
	 * @throws
	 */
	public List<String> queryProductByOrderId(Long orderId);
	
	/**
	 * 
	 * @Description: 根据订单ID 查询是否包含制定的三级分类商品    
	 * @param orderId
	 * @return      
	 * @return: Integer
	 * @author:ZhouXin
	 * @Date:2019年1月21日
	 * @throws
	 */
	public Integer selectProductThreeCode(Long orderId);
	
}