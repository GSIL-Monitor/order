package com.emotte.dubbo.activities.service;

public interface CardPayDubboService {

	/**
	 * 根据商品code，手机号查询可支付的卡  code =0  成功 -2 失败
	 * @param json
	 * @returnMobile
	 */
	public String erpCardPay (String json);

}
