package com.emotte.dubbo.custom;

public interface addSubcontractLogService {

	/**
	 *  生成分包日志
	 * @param 
	 * managerId 分包管家
	 * customId  客户id
	 * @return
	 */
	public String addSubcontractLog(Long managerId,Long customId);
}
