package com.emotte.order.timer;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.emotte.kernel.exception.BaseException;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.order.model.AccountPay;
import com.emotte.order.order.service.AccountPayService;
import com.emotte.order.order.service.OrderService;

/**
 * 订单可用余额问题处理
 * @author ChengJing
 * 2017年6月9日
 */
@Component
public class TotalPayTask {
	@Resource
	private AccountPayService accountPayService;
	@Resource
	private OrderService orderService;
	/**
	 * 计算订单余额
	 * 2017年6月9日
	 */
	synchronized public void doBuss () {
		LogHelper.debug(getClass(), "【定时器】开始计算订单余额");
		// 查询满足条件的结算单
		List<AccountPay> list = accountPayService.queryNoBuss();
		// 循环遍历每一个结算单，计算订单可用余额
		for (AccountPay accountPay : list) {
			try {
				boolean f = orderService.updateOrderTotalPay(accountPay);
				if (!f) throw new BaseException(String.format("更新失败"));
			} catch (BaseException e) {
				LogHelper.error(getClass(), "【计算可用余额失败】【"+accountPay.getId()+"】" + e.getMessage());
			} catch (Exception e) {
				LogHelper.error(getClass(), "【计算可用余额失败】【"+accountPay.getId()+"】", e);
			}
		}
	}
}
