package com.emotte.dubbo.activities.service;

public interface AutoGenDubboCardService {

	/**
	 * 自动校验生成卡券
	 * @return
	 */
	public void checkCondtionByOrderId(String orderId, String contractId);
}
