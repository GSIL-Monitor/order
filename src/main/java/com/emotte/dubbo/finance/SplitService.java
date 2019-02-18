package com.emotte.dubbo.finance;

public interface SplitService {
	
	/**
	 * 单次服务分账
	 * @param data json串
	 * <p>{<br>
	 * 	orderId: String 订单ID<br>
	 * 	totalMoney: String 结算金额（待分账金额）<br>
	 * 	servicePersonId: String 服务人员ID<br>
	 * }</p>
	 * @return json串
	 * <p>{ result: success/fail 返回成功或者失败 }</p>
	 * 2016年8月31日
	 */
	public String singleServerSplit (String data);
}
