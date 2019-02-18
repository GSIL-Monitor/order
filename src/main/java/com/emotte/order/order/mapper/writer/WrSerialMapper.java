package com.emotte.order.order.mapper.writer;

import java.util.List;

import org.springframework.stereotype.Component;
import com.emotte.order.order.model.Serial;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;
 
  @Component("wrSerialMapper")
public interface WrSerialMapper extends WrBaseMapper{
	
	public void insertSerial(Serial serial);
	
    public void updateSerial(Serial serial);
    
    public int updateSerialVersion(Serial serial);
    
    public void insertSerialList(List<Serial> list);
    
    public void deleteSerialVersion(Long version);

}