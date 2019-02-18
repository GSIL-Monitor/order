package com.emotte.dubbo.credit;

public interface CreditService {
	/**
	 * 验证身份证
     *-1:身份证号码是错误的 
     * 0:没有查到该身份证信息 
     * 1:匹配 
     * 2:姓名和身份证信息不匹配 
     * 3:参数为空
	 * @param name：姓名
	 * @param cardNum：身份证号码
	 * @param flagId：2
	 * @return
	 */
	public String checkIdCard(String name, String cardNum, Long flagId);
}
