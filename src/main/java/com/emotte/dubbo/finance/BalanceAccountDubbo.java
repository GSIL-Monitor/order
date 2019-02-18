package com.emotte.dubbo.finance;

/**
 * 对账接口服务
 * @author Administrator
 * 2016年7月26日
 */
public interface BalanceAccountDubbo {
	/**
	 * 直接对账<br>
	 * 支付后直接进行对账<br>
	 * @param data json格式<br>
	 * {<br>
	 * 		money: String 金额<br>
	 * 		flowNum: String 流水号<br>
	 * 		payType: String 支付类型<br>
	 * 		remark: String 备注<br>
	 * 		postNum: 账号<br>
	 * }<br>
	 * @return 
	 * {<br>
	 * 		result: success/fail 返回成功或者失败<br>
	 * }<br>
	 * 2016年7月26日
	 */
	public String directBalanceAccount(String data);
	
	/**
	 * 手动对账<br>
	 * 通过传入一个银行流水ID和一个缴费单ID进行对账
	 * @param data json格式<br>
	 * {<br>
	 * 		flowId: String 银行流水<br>
	 * 		payFeeId: String 缴费单ID<br>
	 * 		userId: String 当前登录人
	 * }<br>
	 * @return
	 * {<br>
	 * 		result: success/fail 返回成功或者失败<br>
	 * 		msg: 错误详情<br>
	 * }<br>
	 * 2016年7月27日
	 */
	public String manualBalanceAccount (String data);
	/**
	 * 手动对账<br>
	 * 通过传入一个银行流水ID和多个缴费单ID进行对账
	 * @param data json格式<br>
	 * {<br>
	 * 		flowId: String 银行流水<br>
	 * 		payFeeId: String 缴费单ID<br>
	 * 		userId: String 当前登录人
	 * }<br>
	 * @return
	 * {<br>
	 * 		result: success/fail 返回成功或者失败<br>
	 * 		msg: 错误详情<br>
	 * }<br>
	 * 2016年7月27日
	 */
	public String manualBalanceAccount2 (String data);

	/**
	 * 对账（多笔缴费，对一笔流水）
	 * 业务逻辑：通过传入一个银行流水ID、多个缴费单ID进行对账
	 * @param data json格式
     * {
	 * 		flowId: String 银行流水
	 * 		payFeeIds: [] 缴费单ID列表
	 * 		userId: String 当前登录人
     * }
     * 样例：{"flowId":"123456","userId":"liujiajia",
     *             "payFeeIds":[{"payFeeId":"123"},{"payFeeId":"321"},{"payFeeId":"456"},{"payFeeId":"654"}]}
	 * @return
	 * {
	 * 		result: success/fail 返回成功或者失败
	 * 		msg: 错误详情
	 * }
	 * 2018年5月10日
	 */
	public String manyToOneBalanceAccount (String data);
	
	/**
	 * 白条还款对账
	 * 在线支付白条还款，进行对账
	 * @param data json格式<br>
	 * {<br>
	 * billId: 白条还款ID<br>
	 * money: 金额<br>
	 * flowNum: 流水号<br>
	 * payType: 支付类型<br>
	 * remark: 备注<br>
	 * dealTime: 交易时间<br>
	 * bankName: 交款银行名称<br>
	 * mchid: 账号<br>
	 * }
	 * @return
     * {<br>
	 * 		result: success/fail 返回成功或者失败<br>
	 * }<br>
	 */
	public String iouDirectBalanceAccount(String data);
	/**
	 * 对账
	 * 录入缴费同时检查是否存在流水并对账
	 */
	public String one2OneInComeBalanceAccount(String data);

}
