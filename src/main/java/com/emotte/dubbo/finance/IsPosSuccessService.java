package com.emotte.dubbo.finance;

public interface IsPosSuccessService {
	/**
     * 
     * @Description: 查询这条融汇天下的pos流水的未对账金额
     * 				通过流水号，终端号，查询这条流水剩余的对账金额是否大于等于这笔缴费的金额
     * @author 周天冬
     * @date 2016年6月28日 下午4:23:32
     * @param data 需要调用数据的json参数
	 * 	data{
	 * 			jiaofei 缴费金额
	 * 			terminalNo 终端号
	 * 			referenceNo 流水号
	 * 		}
     * @return String ( ok:成功，error:失败)
     */
	public String isSuccess(String data);
}
