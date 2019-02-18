package com.emotte.order.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emotte.order.order.mapper.reader.ReOrderTurnLogMapper;
import com.emotte.order.order.mapper.writer.WrOrderTurnLogMapper;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderTurnLog;
import com.emotte.order.order.service.OrderTurnLogService;

@Service("orderTurnLogService")
@Transactional
public class OrderTurnLogServiceImpl implements OrderTurnLogService {

	@Resource
	private WrOrderTurnLogMapper wrOrderTurnLogMapper;

	@Resource
	private ReOrderTurnLogMapper reOrderTurnLogMapper;

	@Override
	public void insertOrderTurnLog(OrderTurnLog orderTurnLog) {
		try {
			this.wrOrderTurnLogMapper.insertOrderTurnLog(orderTurnLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<OrderTurnLog> queryOrderTurnLog(OrderTurnLog orderTurnLog) {
		List<OrderTurnLog> list = null;
		try {
			list = this.reOrderTurnLogMapper.queryOrderTurnLog(orderTurnLog);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Order selectPrincipal(Long orderCodeId) {
		Order order=null;
		try {
			order=reOrderTurnLogMapper.selectPrincipal(orderCodeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public int selectPosition(Long loginId) {
		int num=reOrderTurnLogMapper.selectPosition(loginId);
		System.out.println(num);
		return num;
	}

	@Override
	public Order selectPrincipalTwo(Long orderCodeId) {
		Order order=null;
		try {
			order=reOrderTurnLogMapper.selectPrincipalTwo(orderCodeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}
}
