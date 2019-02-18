package com.emotte.order.order.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.exception.BaseException;

import com.emotte.order.order.mapper.writer.WrOrderMapper;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.service.TestService;

@Service("testService")
@Transactional
public class TestServiceImpl implements TestService {

	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private WrOrderMapper wrOrderMapper;
	
	@Resource
	private TestService testService;

	@Override
	public void update1(Order order) {
		try {
			this.wrOrderMapper.updateOrder(order);
		} catch (Exception e) {
			log.error("update1" + e);
			throw new BaseException(e);
		}
	}

	@Override
	public void update2(Order order) {
		try {
			Order order1 = new Order();
			order1.setId(229433928928534L);
			order1.setUpdateBy(1111111111L);
			this.testService.update1(order1);
			this.wrOrderMapper.updateOrder(order);
			throw new BaseException("123");
		} catch (Exception e) {
			log.error("update2" + e);
			throw new BaseException(e);
		}
	}
}
