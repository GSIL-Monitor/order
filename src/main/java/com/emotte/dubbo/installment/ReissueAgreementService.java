package com.emotte.dubbo.installment;

public interface ReissueAgreementService {

	/**
	 * 海金合同给客户发送代扣协议
	 * 2018-10-16
	 */
	String reissueAgreementByOrderId(String orderId);

}
