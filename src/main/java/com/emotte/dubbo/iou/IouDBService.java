package com.emotte.dubbo.iou;

import java.util.Map;

/**
 * 白条支付系统向Dubbo暴露的接口
 */
public interface IouDBService {
	/**
	 * 
	 * @Function :  iouService
	 * @Desc     :  白条服务入口
	 * @Desc     :  白条接口目前只提供一个入口,根据参数来区别 所执行的业务！
	 * @param param op > checkBill
	 * @return
	 */
	public String iouService(Map<String,String> param);
	
	/**
	 * 
	 * @Function :  refund
	 * @Desc     :  退费操作
	 * @param param > type：退费类型
	 * @param param > money：退费金额
	 * @param param > order_id：订单ID
	 * @param param > user_id：客户ID
	 * @return
	 */
	public String refund(Map<String,String> param);
	
}
