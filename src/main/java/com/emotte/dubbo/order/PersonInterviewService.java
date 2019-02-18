package com.emotte.dubbo.order;


/**
* @ClassName: PersonInterview 
* @Description: 约他(她)面试接口
* @author lidenghui 
* @date 2018年3月29日 下午1:45:54 
*
 */
public interface PersonInterviewService {
	
	/**
	 * 
	* @Description: 约Ta面试接口
	* @author lidenghui
	* @date 2018年3月29日 下午1:49:17 
	* @param str
	* @param data={'order':'123456',personIds:'123456,789456,12312'}
	* @return String
	 */
	public String personInterview(String str);

}
