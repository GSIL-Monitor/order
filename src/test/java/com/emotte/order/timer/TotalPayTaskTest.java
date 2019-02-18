package com.emotte.order.timer;

import javax.annotation.Resource;

import org.junit.Test;

import com.emotte.BaseTestService;

public class TotalPayTaskTest extends BaseTestService {
	@Resource
	private TotalPayTask task;
	@Test
	public void test() {
		task.doBuss();
	}

}
