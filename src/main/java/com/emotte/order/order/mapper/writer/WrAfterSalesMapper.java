package com.emotte.order.order.mapper.writer;

import com.emotte.order.order.model.ModeServeBalancePayment;
import com.emotte.order.order.model.Payfee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.emotte.order.order.model.AfterSalesNew;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import java.math.BigDecimal;

@Component("wrAfterSalesMapper")
public interface WrAfterSalesMapper extends WrBaseMapper {

	public void insertAfterSales(AfterSalesNew afterSalesNew);

	public int updateAfterSales(AfterSalesNew afterSalesNew);

	/**
	 * 根据卡编号修改卡状态为废弃
	 *
	 * @param cardNumber    卡编号
	 * @param updateBy
	 */
	void discard(@Param("cardNumber") String cardNumber, @Param("updateBy") Long updateBy);

	/**
	 * 根据 提成 账户id 扣除管家提成
	 *
	 * @param afterSalesNew	参数封装对象
     */
	void updateBanlance(ModeServeBalancePayment afterSalesNew);

	/**
	 * 收支表添加支出记录
	 *
	 * @param modeServeBanlancePayment	收支表对象信息
     */
	void addBanlancePayment(ModeServeBalancePayment modeServeBanlancePayment);

	/**
	 * 修改方案status为取消
	 *
	 * @param solutionId	方案ID
	 */
	void updateSolutionStatus(@Param("solutionId") Long solutionId, @Param("updateBy") Long updateBy);

	/**
	 * 修改排期订单删除标记为4
	 *
	 * @param solutionId	方案ID
     */
	void updatePlanStatus(@Param("solutionId") Long solutionId, @Param("updateBy") Long updateBy);

	/**
	 * 根据代扣卡号，修改余额为0
	 *
	 * @param cardNumber	代扣卡编号
     */
	void removeCardBalance(@Param("cardNumber") String cardNumber);

	/**
	 * 排期订单余额回冲代扣卡
	 *
	 * @param cardNumber		代扣卡编号
	 * @param totalBalance		排期订单余额
     */
	void updateCardBalance(@Param("cardNumber") String cardNumber, @Param("totalBalance") BigDecimal totalBalance);

	/**
	 * 根据订单id扣除订单余额字段
	 *
	 * @param orderId			订单ID
	 * @param baiTiaoAmount		退费金额
	 */
	int subtractionOrderBalanceForBaiTiao(@Param("orderId") Long orderId, @Param("baiTiaoAmount") BigDecimal baiTiaoAmount,@Param("updateBy") Long updateBy);

	/**
	 * 根据订单id增加订单余额字段
	 *  @param orderId            订单ID
	 * @param baiTiaoAmount        退费金额
	 * @param updateBy
	 */
	int addOrderBalanceForBaiTiao(@Param("orderId") Long orderId, @Param("baiTiaoAmount") BigDecimal baiTiaoAmount, @Param("updateBy") Long updateBy);

	/**
	 * 保存结算单
	 *
	 * @param payfee	结算单对象
     */
	int saveAccountPay(Payfee payfee);

	/**
	 * 保存缴费单
	 *
	 * @param payfee	缴费单对象
     */
	void savePayFee(Payfee payfee);

	/**
	 * 排期余额回冲解决方案
	 *
	 * @param totalBalance        排期订单占用金额
	 * @param solutionId
	 */
	void chargeActiveMonry(@Param("totalBalance") BigDecimal totalBalance, @Param("solutionId") Long solutionId, @Param("updateBy") Long updateBy);
}