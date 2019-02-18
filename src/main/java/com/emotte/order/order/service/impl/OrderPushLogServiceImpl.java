package com.emotte.order.order.service.impl;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.emotte.order.order.mapper.writer.WrOrderPushLogMapper;
import com.emotte.order.order.model.OrderPushLog;
import com.emotte.order.order.service.OrderPushLogService;


@Service("orderPushLogService")
@Transactional
public class OrderPushLogServiceImpl implements OrderPushLogService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private WrOrderPushLogMapper wrOrderPushLogMapper;
	

	@Override
	public Long insertOpushlog(OrderPushLog orderPushLog) {
		this.wrOrderPushLogMapper.insertOpushlog(orderPushLog);
		return orderPushLog.getId();
	}

	

}