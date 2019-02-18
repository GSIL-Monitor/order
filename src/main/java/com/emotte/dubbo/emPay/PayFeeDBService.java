package com.emotte.dubbo.emPay;

public interface PayFeeDBService {
	/**
	 * 通过dubbo接口 来实现扣除公司本身支付方式金额的逻辑
	 * 该方法是实现，扣除对应卡/券/账户  支付的金额的
	 * @param params  json格式参数
	 * 	 	params 具体参数
	 * 			orderId    			结算单ID
	 * @return 返回是否扣除成功  success 扣除成功，其余均为扣除失败
	 */
	public String payCost(String params);
}
