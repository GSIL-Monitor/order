package com.emotte;

import org.junit.Test;

import com.emotte.order.thread.NewOrderMsgThread;

public class NewOrderMsgThreadTest extends BaseTestService {

	@Test
	public void test() {
		NewOrderMsgThread thread = new NewOrderMsgThread("867385426219413");
		thread.run();
	}

}
