package com.emotte.order.order.service;

import java.util.List;

import com.emotte.order.order.model.AfterFees;
import com.emotte.order.order.model.Payfee;

import org.wildhorse.server.core.model.Page;

public interface AfterFeesService {

	public AfterFees loadAfterFees(Long id);

	public List<AfterFees> queryAfterFees(AfterFees afterFees, Page page);

	public void insertAfterFees(AfterFees afterFees);

	public void updateAfterFees(AfterFees afterFees);

	public AfterFees queryPayMoney(AfterFees afterFees);

	public Payfee queryAccountPay(AfterFees afterFees);

	public List<AfterFees> queryMembershipFee(AfterFees afterFees);
	
	public List<AfterFees> queryNotIousSalaryMoney(AfterFees afterFees);

	List<AfterFees> queryInstallmentFee(AfterFees afterFees);

}