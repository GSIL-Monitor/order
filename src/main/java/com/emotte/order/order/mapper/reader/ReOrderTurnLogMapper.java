package com.emotte.order.order.mapper.reader;

import java.util.List;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderTurnLog;

@Component("reOrderTurnLogMapper")
public interface ReOrderTurnLogMapper extends ReBaseMapper  {
	
   List<OrderTurnLog> queryOrderTurnLog(OrderTurnLog orderTurnLog);
   
   public Order selectPrincipal(Long orderCodeId);
   
   public Order selectPrincipalTwo(Long orderCodeId);
   
   public  int selectPosition (Long loginId);
   
}
