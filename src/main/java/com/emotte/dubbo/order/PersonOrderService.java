package com.emotte.dubbo.order;

public interface PersonOrderService {

	/**
	 * 库存查询接口
	 * @param str 
	 * cityCode 城市code; 
	 * cateCode 分类code;
	 * startDate 时间  格式yyyyMMdd;
	 * @return
	 */
	public String onceInventory(String str);
	
	/**
	 * 时间段查询接口
	 * @param str 
	 * cityCode 城市code; 
	 * cateCode 分类code;
	 * startDate 时间  格式yyyyMMdd;
	 * type 类型
	 * @return
	 */
	public String onceQuantums(String str);

}


