package com.emotte.order.order.service.impl;

import javax.annotation.Resource;
import java.util.List;
import org.apache.log4j.Logger;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.emotte.order.order.model.Daily;
import  com.emotte.order.order.mapper.reader.ReDailyMapper;
import  com.emotte.order.order.mapper.writer.WrDailyMapper;
import com.emotte.order.order.service.DailyService;

    @Service("dailyService")
     @Transactional
public class DailyServiceImpl implements DailyService{
         Logger log=Logger.getLogger(this.getClass()); 
		 
	  	@Resource
	private ReDailyMapper reDailyMapper;
	
	    @Resource
	private WrDailyMapper wrDailyMapper;
    
    	@Override
		@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public Daily loadDaily(Long id){
	  return this.reDailyMapper.loadDaily(id);
	}
	
		@Override
		@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<Daily> queryDaily(Daily daily,Page page){
	   if(page.needQueryPading()){
			page.setTotalRecord(reDailyMapper.countDaily(daily));
		}
	   daily.setBeginRow(page.getFilterRecord().toString());
	   daily.setPageSize(page.getPageCount().toString());
	  return this.reDailyMapper.queryDaily(daily);
	}
	
	@Override
	public void insertDaily(Daily daily){
	  this.wrDailyMapper.insertDaily(daily);
	}
		
    @Override
	public void updateDaily(Daily daily){
             int returnValue; 
           try{
           returnValue= this.wrDailyMapper.updateDaily(daily);
             if (1 !=returnValue) {
        		   throw new BaseException("update fail!");
			      }
             } catch (Exception e) {
            	 log.error("updateDaily:"+e);
			throw new BaseException(e);
			}
		}

    }