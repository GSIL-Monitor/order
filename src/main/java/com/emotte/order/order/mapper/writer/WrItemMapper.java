package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import com.emotte.order.order.model.Item;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;
 
  @Component("wrItemMapper")
public interface WrItemMapper extends WrBaseMapper{
	
	public void insertItem(Item item);
	
    public int updateItem(Item item);
    
    public void updateItemQuantityByOrderId(Item item);
    
    public int updateItemtype(Item item);

    }