package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import com.emotte.order.order.model.ItemInterview;
import com.emotte.order.order.model.ServerLinedRecord;

import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;
 
  @Component("wrItemInterviewMapper")
public interface WrItemInterviewMapper extends WrBaseMapper{
	
	public void insertItemInterview(ItemInterview itemInterview);
	
    public int updateItemInterview(ItemInterview itemInterview);
    
    public int updateItemInterviewByOrderId(ItemInterview itemInterview);
    
    public int updatePersonStartOneByOrderId(ItemInterview itemInterview);

	public void insertOrderLineRecord(ServerLinedRecord serverLinedRecord);
    }