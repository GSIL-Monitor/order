package com.emotte.order.order.service;
import java.util.List;
import com.emotte.order.order.model.Serial;
import org.wildhorse.server.core.model.Page;

public interface SerialService{
    
	public Serial loadSerial(Long id);
	
	public List<Serial> querySerial(Serial serial,Page page);
	
	public void insertSerial(Serial serial);
	
    public void updateSerial(Serial serial);

    }