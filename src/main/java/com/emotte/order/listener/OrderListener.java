package com.emotte.order.listener;

import org.wildhorse.server.auth.listener.BaseListener;


public class OrderListener extends BaseListener {
	/*@Override
	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);
		LogHelper.info(getClass(), "*********************启动订单更新状态线程***********************");
		UpdateOtherPlateThread thread = new UpdateOtherPlateThread(CommonConstant.REDIS_ORDER_STATUS); // 启动订单更新状态线程
		thread.start();
		LogHelper.info(getClass(), "*********************启动售后单更新状态线程***********************");
		UpdateAfterSaleStatusThread afterSaleStatusThread = new UpdateAfterSaleStatusThread(CommonConstant.REDIS_AFTERSALE_STATUS); // 启动售后单更新状态线程
		afterSaleStatusThread.start();
	}*/

}
