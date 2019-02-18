package com.emotte.order.order.service.impl;

import javax.annotation.Resource;
import java.util.List;
import org.apache.log4j.Logger;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.emotte.order.order.model.Serial;
import  com.emotte.order.order.mapper.reader.ReSerialMapper;
import  com.emotte.order.order.mapper.writer.WrSerialMapper;
import com.emotte.order.order.service.SerialService;

    @Service("serialService")
     @Transactional
public class SerialServiceImpl implements SerialService{
         Logger log=Logger.getLogger(this.getClass()); 
		 
	  	@Resource
	private ReSerialMapper reSerialMapper;
	
	    @Resource
	private WrSerialMapper wrSerialMapper;
    
    	@Override
		@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public Serial loadSerial(Long id){
	  return this.reSerialMapper.loadSerial(id);
	}
	
		@Override
		@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<Serial> querySerial(Serial serial,Page page){
	   if(page.needQueryPading()){
			page.setTotalRecord(reSerialMapper.countSerial(serial));
		}
	   serial.setBeginRow(page.getFilterRecord().toString());
	   serial.setPageSize(page.getPageCount().toString());
	  return this.reSerialMapper.querySerial(serial);
	}
	
	@Override
	public void insertSerial(Serial serial){
	  this.wrSerialMapper.insertSerial(serial);
	}
		
    @Override
	public void updateSerial(Serial serial){
             int returnValue; 
           try{
        	   this.wrSerialMapper.updateSerial(serial);
             } catch (Exception e) {
            log.error("updateId:"+e);
			throw new BaseException(e);
			}
		}

    }