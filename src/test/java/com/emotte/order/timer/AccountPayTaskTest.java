package com.emotte.order.timer;

import javax.annotation.Resource;

import org.junit.Test;

import com.emotte.BaseTestService;
import com.emotte.order.order.service.AccountPayService;

public class AccountPayTaskTest extends BaseTestService {
	@Resource
	private AccountPayTask service;
	@Resource
	private AccountPayService accountPayService;
	@Test
	public void test() {
//		AccountPay accountPay = new AccountPay();
//		accountPay.setId(775785197336389l);
//		AccountPay account = accountPayService.queryAccountPay(accountPay).get(0);
		for (int i = 0; i < 3; i++) {
			new Runnable() {
				@Override
				public void run() {
					service.doBuss();
				}
			}.run();
		}
	}

}
