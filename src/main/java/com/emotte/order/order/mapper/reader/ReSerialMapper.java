package com.emotte.order.order.mapper.reader;

import java.util.List;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;
import com.emotte.order.order.model.Serial;

  @Component("reSerialMapper")
public interface ReSerialMapper extends ReBaseMapper{
    
	public Serial loadSerial(Long id);
	
	public List<Serial> querySerial(Serial serial);
	
	public Integer countSerial(Serial serial);
	
	public List<Serial> getOrderMinSerial(Long version);
	
	public List<Long> querySerialFronts();
	

}