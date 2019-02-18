package com.emotte.dubbo.service;

public interface PushInterfaceService {
	/**
	 * 
	 * @param userId 人员id
	 * @param appId  appId，服务人员app为1
	 * @param title  推送标题
	 * @param description 推送描述
	 * @param msg         推送消息内容
	 * @return Integer    推送结果 0 失败 大于0推送成功
	 */
	public Integer push(Long userId,Long appId,String title,String description,String msg);
	
	/**
	 * 给服务人员APP推送订单消息
	 * @param userId  服务人员id
	 * @param orderId 订单id
	 * @return
	 */
	public Integer pushOrder(Long userId, Long orderId);
}
