package com.emotte.order.order.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emotte.order.order.mapper.reader.ReAccountPayMapper;
import com.emotte.order.order.mapper.writer.WrAccountPayMapper;
import com.emotte.order.order.model.AccountPay;
import com.emotte.order.order.service.AccountPayService;

@Service("accountPayService")
@Transactional
public class AccountPayServiceImpl implements AccountPayService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ReAccountPayMapper reAccountPayMapper;

	@Resource
	private WrAccountPayMapper wrAccountPayMapper;
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<AccountPay> queryAccountPay(AccountPay accountPay) {
		return this.reAccountPayMapper.queryAccountPay(accountPay);
	}

	@Override
	public int updateAccountList(List<AccountPay> list){
		int result = this.wrAccountPayMapper.updateAccountList(list);
		return result;
	}

	@Override
	public List<AccountPay> queryAccountById(AccountPay accountPay) {
		return this.reAccountPayMapper.queryAccountById(accountPay);
	}

	@Override
	public Map<String, Object> queryAccountInfo(Long accountId) {
		return reAccountPayMapper.queryAccountInfo(accountId);
	}

	@Override
	public List<AccountPay> queryNoBuss() {
		return reAccountPayMapper.queryNoBuss();
	}

	@Override
	public boolean isAllSplit(Long id) {
		Map<String, Object> map = reAccountPayMapper.isAllPayFeeSplit(id);
		int i = MapUtils.getIntValue(map, "RE");
		if (i == 0) return true;
		return false;
	}

	@Override
	public boolean updateBussType(Long id, String status) {
		AccountPay pay = new AccountPay();
		pay.setId(id);
		pay.setBussStatus(status);
		int i = wrAccountPayMapper.updateAccountPay(pay);
		if (i > 0) return true;
		return false;
	}
}