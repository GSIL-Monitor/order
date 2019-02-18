package com.emotte.order.order.service;

import java.util.List;
import java.util.Map;

import com.emotte.order.order.model.AccountPay;

public interface AccountPayService {
	public static final int a=0;
	int f=9;
	public List<AccountPay> queryAccountPay(AccountPay accountPay);
	
	public int updateAccountList(List<AccountPay> list);
	
	public List<AccountPay> queryAccountById(AccountPay accountPay);

	/**
	 * 查询结算单信息
	 * @param accountId
	 * @return
	 * 2017年5月17日
	 */
	public Map<String, Object> queryAccountInfo(Long accountId);

	/**
	 * 查询符合条件的结算单
	 * @return
	 * 2017年6月9日
	 */
	public List<AccountPay> queryNoBuss();

	/**
	 * 验证结算单下面的缴费是否已全部分账
	 * @param id
	 * @return
	 * 2017年6月9日
	 */
	public boolean isAllSplit(Long id);

	/**
	 * 更改业务处理状态
	 * @param id
	 * @param status
	 * @return
	 * 2017年6月9日
	 */
	public boolean updateBussType(Long id, String status);

}