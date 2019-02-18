package com.emotte.order.order.mapper.writer;

import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderAfterSales;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;
@Component("wrOrderAfterSalesMapper") 
public interface WrOrderAfterSalesMapper extends WrBaseMapper{ 

	public int insertOrderAfterSales(OrderAfterSales orderaftersales);

	public int updateOrderAfterSales(OrderAfterSales orderaftersales);

	public int insertVphAfterSales(OrderAfterSales orderAfterSales);
	/**
	 * 更新订单备注
	 * @param o1
	 * @return
	 */
	public int updateOrderRemark(Order o1);

	/**
	 * 根据ID删除售后单
	 *
	 * @param id
	 * @param i
	 * @return
	 */
	void changeAfterSalesValid(@Param("id") Long id, @Param("valid") int valid);

	/**
	 * 根据回访结果修改售后单审核状态
	 *
	 * @param afterSalesId
	 * @param auditFlag
	 * @param updateBy
	 * @Author zhanghao
	 * @Date 20181228
	 */
	void updateAuditFlagByCallBackStatus(@Param("afterSalesId") Long afterSalesId, @Param("auditFlag") String auditFlag, @Param("updateBy") Long updateBy);

	/**
	 * 更新订单余额
	 * @param orderId
	 * @param updateBy
	 * @param difference 差额,增加或减少
	 */
	public void updateOrderTotalPay(@Param("orderId") Long orderId,@Param("updateBy") Long updateBy,@Param("difference") BigDecimal difference);
}
