package com.emotte.dubbo.activities.service;

public interface SolutionDubboCardService {

	/**
	 * 添加解决方案虚拟卡
	 * @return
	 */
	public String insertSolutionCard(String json);
	/**
	 * 解决方案卡作废接口
	 * @param json
	 * @return
	 */
	public String disable(String json);
}
