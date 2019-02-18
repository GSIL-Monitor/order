package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import com.emotte.order.order.model.ItemDetailServer;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;
 
  @Component("wrItemDetailServerMapper")
public interface WrItemDetailServerMapper extends WrBaseMapper{
	
	public void insertItemDetailServer(ItemDetailServer itemDetailServer);
	
    public int updateItemDetailServer(ItemDetailServer itemDetailServer);
    
    public void insertOrderServerLined(ItemDetailServer itemDetailServer);
	
    public int updateOrderServerLined(ItemDetailServer itemDetailServer);

	public void updateOrderServerLinedNew(ItemDetailServer itemDetailServer);

	public void updateItemDetailServerLine(ItemDetailServer itemDetailServer);
	
	public void insertUpdateOrderDetailServer(ItemDetailServer itemDetailServer);

	public int deleteSchedule(Long orId);
    
    
 }