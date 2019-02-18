package com.emotte.dubbo.activities.service;

public interface GJBAPPCardInterfaceService {
	public String paySuccess(String json);
	/**
	 * 微指数券激活为已使用
	 * @param json （userId,ticketNum）
	 * @return
	 */
	public String setTicketStatusToBeUsed(String json);

}
