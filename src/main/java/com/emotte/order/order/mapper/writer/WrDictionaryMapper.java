package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import com.emotte.order.order.model.Dictionary;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;
 
  @Component("wrDictionaryMapper")
public interface WrDictionaryMapper extends WrBaseMapper{
	
	public void insertDictionary(Dictionary dictionary);
	
    public int updateDictionary(Dictionary dictionary);

    }