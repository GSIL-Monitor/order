package com.emotte.dubbo.activities;

/**
 * lishfe
 */
public interface ActivitiesEmottePayService {

  /**
   * 订单取消时，删除缴费，回执卡券状态
   */
	String reBackCardInfo(Long orderId);

}