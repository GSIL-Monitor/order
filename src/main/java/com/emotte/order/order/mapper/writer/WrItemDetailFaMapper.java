package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import com.emotte.order.order.model.ItemDetailFa;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;
 
  @Component("wrItemDetailFaMapper")
public interface WrItemDetailFaMapper extends WrBaseMapper{
	
	public void insertItemDetailFa(ItemDetailFa itemDetailFa);
	
    public int updateItemDetailFa(ItemDetailFa itemDetailFa);

    }