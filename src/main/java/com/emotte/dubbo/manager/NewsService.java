package com.emotte.dubbo.manager;

import java.util.List;

public interface NewsService {
	
	
	/**
	 * 添加消息
	 * @param infoText 消息内容
	 * @param managerid 消息指向的人
	 * @param createBy 创建人
	 * @return
	 */
	public Integer addNews(String infoText,List<String> managerid,Long createBy);

	/**
	 * 添加消息
	 * @param infoText 消息内容
	 * @param infoHref 暂无用
	 * @param managerid 消息指向的人
	 * @param createBy 创建人
	 * @return
	 */
	public Integer addNews(String infoText,String infoHref,List<String> managerid,Long createBy);
}
