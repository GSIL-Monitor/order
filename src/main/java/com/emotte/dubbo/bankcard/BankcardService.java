package com.emotte.dubbo.bankcard;

import org.wildhorse.server.core.exception.BaseException;

public interface BankcardService {

	/**
	* @Title: queryBankSupport 
	* @Description: 银行卡类型列表
	* @param jsonstr
	* @return
	* @throws BaseException
	* @throws
	* @author liutj
	* @date 2017年10月31日 下午7:14:43
	 */
	public String queryBankSupport() throws BaseException;
	
	/**
	* @Title: myBankcard 
	* @Description: 我的银行卡列表 
	* @param jsonstr  id : 用户id
	* @return
	* @throws BaseException
	* @throws
	* @author liutj
	* @date 2017年10月31日 下午7:14:14
	 */
	public String myBankcard(String jsonstr) throws BaseException;
	
	/**
	* @Title: loadBankcard 
	* @Description: 银行卡详情
	* @param jsonstr id:银行卡id
	* @return
	* @throws BaseException
	* @throws
	* @author liutj
	* @date 2017年10月31日 下午8:13:44
	 */
	public String loadBankcard(String jsonstr) throws BaseException;
	
	/**
	* @Title: insertBankcard 
	* @Description: 添加银行卡
	* @param jsonstr   id:用户id;bankSupportId:银行卡类型id;cardName:持卡人姓名;
	* @param jsonstr   bankCard:银行卡号;bankBranch:开户行;bankCity:开户城市;platform:平台;
	* @return
	* @throws BaseException
	* @throws
	* @author liutj
	* @date 2017年10月31日 下午7:20:33
	 */
	public String insertBankcard(String jsonstr) throws BaseException;
	
	/**
	* @Title: deleteBankcard 
	* @Description: 删除银行卡
	* @param jsonstr id:银行卡id
	* @return
	* @throws BaseException
	* @throws
	* @author liutj
	* @date 2017年10月31日 下午7:43:18
	 */
	public String deleteBankcard(String jsonstr) throws BaseException;
	/**
	* @Title: updateToDefCard 
	* @Description: 设置默认的银行卡
	* @param jsonstr id:银行卡id;custId:用户id
	* @return
	* @throws BaseException
	* @throws
	* @author liutj
	* @date 2017年10月31日 下午7:47:45
	 */
	public String updateToDefCard(String jsonstr) throws BaseException;
	
	/**
	* @Title: updateBankcard 
	* @Description: 修改银行卡
	* @param jsonstr   id:银行卡id;bankSupportId:银行卡类型id;cardName:持卡人姓名;
	* @param jsonstr   bankCard:银行卡号;bankBranch:开户行;bankCity:开户城市;custId:用户id
	* @return
	* @throws BaseException
	* @throws
	* @author liutj
	* @date 2017年10月31日 下午7:53:12
	 */
	public String updateBankcard(String jsonstr) throws BaseException;
}
