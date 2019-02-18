package com.emotte.order.timer;

import javax.annotation.Resource;

import org.junit.Test;

import com.emotte.BaseTestService;

public class BecomeVipTaskTest extends BaseTestService {
	@Resource
	private BecomeVipTask becomeVipTask;
	@Test
	public void test(){
		for (int i = 0; i < 2; i++) {
			new Runnable() {
				@Override
				public void run() {
					becomeVipTask.becomeVip();
				}
			}.run();
		}
	}
}
