package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import com.emotte.order.order.model.Daily;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;
 
  @Component("wrDailyMapper")
public interface WrDailyMapper extends WrBaseMapper{
	
	public void insertDaily(Daily log);
	
    public int updateDaily(Daily log);

    }