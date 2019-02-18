package com.emotte.order.order.mapper.writer;
import org.springframework.stereotype.Component;
import com.emotte.order.order.model.OrderPushLog;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;
 
  @Component("wrOrderPushLogMapper")
public interface WrOrderPushLogMapper extends WrBaseMapper{
	
	public void insertOpushlog(OrderPushLog orderPushLog);
	
}