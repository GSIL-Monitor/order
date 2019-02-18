package com.emotte.dubbo.service;

public interface PersonnelInterfaceService {
	/**
	 * 根据条件搜索合适的服务人员
	 * @param queryJson SearchBean转化的json字符串
	 * @param offset 查询结果偏移量
	 * @param size   查询结果条数
	 * @return 查询结果json格式 total,offset,size,data
	 */
	public String search(String queryJson,int offset,int size);
	
	/**
	 * 根据条件搜索合适的服务人员
	 * @param queryJson SearchBean转化的json字符串
	 * @param sort  排序顺序 (field1 desc,field2 asc)
	 * @param offset 查询结果偏移量
	 * @param size   查询结果条数
	 * @return 查询结果json格式 total,offset,size,data
	 */
	public String query(String queryJson,String sort,int offset,int size);
	
	/**
	 * 查询服务人员排期
	 * @param id 人员ID
	 * @param startDate 开始日期
	 * @return
	 */
	public String queryScheduleByMonth(Long id,Long startDate);
	
	/**
	 * 查询服务人员排期
	 * @param id 人员ID
	 * @param startDate 开始日期
	 * @return
	 */
	public String queryScheduleByDay(Long id,Long startDate);
	
	/**
	 * 查询服务人员排期
	 * @param id 人员ID
	 * @param startDate 开始日期
	 * @return
	 */
	public String queryScheduleByHour(Long id,Long startDate);
	
	
	/**
	 * 修改排期
	 * @param json
	 */
	public boolean updateSchedule(String json);
}
