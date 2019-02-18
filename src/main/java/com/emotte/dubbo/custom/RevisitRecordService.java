package com.emotte.dubbo.custom;

public interface RevisitRecordService {
	
	/**
	 * 添加客户回访记录
	 * @parameter 参数及其意义 json
	 * @return 返回值 json
	 * @date 2018年5月16日
	 */
	public String insertRecorder(String json);
	
	/**
	 * 查询记录
	 * @parameter 参数及其意义 json
	 * @return 返回值 json
	 * @date 2018年5月16日
	 */
	public String queryRecordlistPage(String json);

}
