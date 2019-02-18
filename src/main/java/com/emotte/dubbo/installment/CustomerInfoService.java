package com.emotte.dubbo.installment;


public interface CustomerInfoService {
	
	/**保存新建合同,验证是否有历史未完成合同, 发送短信确认
	 * @param json
	 * @return
	 */
	String saveCustomerInfo(String json);
	/**取消海金申请
	 * @param orderId
	 * @param contractCode
	 * @return {"code":"0","msg":"取消海金申请成功"}   说明   0:成功     ,1失败
	 */
	public String cancelApply(String ParamJson);
	/**
	 * 查询是否有已经推送的海金账单
	 * @param orderCode
	 * @return
	 */
	public String validaExistBill(String orderCode);
}
