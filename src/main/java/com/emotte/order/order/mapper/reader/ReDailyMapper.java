package com.emotte.order.order.mapper.reader;

import java.util.List;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;
import com.emotte.order.order.model.Daily;

  @Component("reDailyMapper")
public interface ReDailyMapper extends ReBaseMapper{
    
	public Daily loadDaily(Long id);
	
	public List<Daily> queryDaily(Daily log);
	
	public Integer countDaily(Daily log);

    }