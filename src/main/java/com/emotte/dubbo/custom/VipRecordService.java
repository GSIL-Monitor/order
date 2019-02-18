package com.emotte.dubbo.custom;

public interface VipRecordService {

	/**
	 *  插入购买会员记录 
	 * @param vr
	 * @return
	 */
	public String insertVipRecord(Long custId,Double price,Integer timelong);

	/**
	 * 更新支付状态
	 * @param vipRecord
	 * @return
	 */
	public int updateVipRecordFlag(String json);

}