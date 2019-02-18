package com.emotte.order.order.mapper.reader;

import java.util.List;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;
import com.emotte.order.order.model.Dictionary;

  @Component("reDictionaryMapper")
public interface ReDictionaryMapper extends ReBaseMapper{
    
	public Dictionary loadDictionary(Long id);
	
	public List<Dictionary> queryDictionary(Dictionary dictionary);
	
	public Integer countDictionary(Dictionary dictionary);

    }