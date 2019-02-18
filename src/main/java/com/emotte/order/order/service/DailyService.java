package com.emotte.order.order.service;
import java.util.List;
import com.emotte.order.order.model.Daily;
import org.wildhorse.server.core.model.Page;

public interface DailyService{
    
	public Daily loadDaily(Long id);
	
	public List<Daily> queryDaily(Daily log,Page page);
	
	public void insertDaily(Daily log);
	
    public void updateDaily(Daily log);

    }