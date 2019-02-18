package com.emotte.dubbo.manager;

import java.util.List;

import com.emotte.dubbo.manager.NewsService;

public interface OrderNewsService extends NewsService {

	/**
	 * 添加消息
	 * 
	 * @param infoText
	 *            消息内容
	 * @param managerid
	 *            消息指向的人
	 * @param createBy
	 *            创建人
	 * @param orderId
	 * @param personId
	 * @return
	 */
	public Integer addNewsAboutOrder(String infoText, List<String> managerid, Long createBy, String newType,
			Long orderId, Long personId);

	/**
	 * 添加消息
	 * 
	 * @param infoText
	 *            消息内容
	 * @param infoHref
	 *            暂无用
	 * @param managerid
	 *            消息指向的人
	 * @param createBy
	 *            创建人
	 * @param orderId
	 * @param personId
	 * @return
	 */
	public Integer addNewsAboutOrder(String infoText, String infoHref, List<String> managerid, Long createBy,
			String newType, Long orderId, Long personId);

}
