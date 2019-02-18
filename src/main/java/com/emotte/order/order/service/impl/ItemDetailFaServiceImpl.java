package com.emotte.order.order.service.impl;

import javax.annotation.Resource;

import java.util.Calendar;
import java.util.List;
import org.apache.log4j.Logger;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.emotte.order.order.model.ItemDetailFa;
import com.emotte.order.order.model.ItemFaQuery;
import  com.emotte.order.order.mapper.reader.ReItemDetailFaMapper;
import  com.emotte.order.order.mapper.writer.WrItemDetailFaMapper;
import com.emotte.order.order.service.ItemDetailFaService;

    @Service("itemDetailFaService")
     @Transactional
public  class ItemDetailFaServiceImpl implements ItemDetailFaService{
         Logger log=Logger.getLogger(this.getClass()); 
		 
	  	@Resource
	private ReItemDetailFaMapper reItemDetailFaMapper;
	
	    @Resource
	private WrItemDetailFaMapper wrItemDetailFaMapper;
    
    	@Override
		@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public ItemDetailFa loadItemDetailFa(Long id){
	  return this.reItemDetailFaMapper.loadItemDetailFa(id);
	}
	
		@Override
		@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<ItemDetailFa> queryItemDetailFa(ItemDetailFa itemDetailFa,Page page){
	   if(page.needQueryPading()){
			page.setTotalRecord(reItemDetailFaMapper.countItemDetailFa(itemDetailFa));
		}
		itemDetailFa.setBeginRow(page.getFilterRecord().toString());
		itemDetailFa.setPageSize(page.getPageCount().toString());
	  return this.reItemDetailFaMapper.queryItemDetailFa(itemDetailFa);
	}
	
	@Override
	public void insertItemDetailFa(ItemDetailFa itemDetailFa){
	  this.wrItemDetailFaMapper.insertItemDetailFa(itemDetailFa);
	}
		
    @Override
	public void updateItemDetailFa(ItemDetailFa itemDetailFa){
             int returnValue; 
           try{
           returnValue= this.wrItemDetailFaMapper.updateItemDetailFa(itemDetailFa);
             if (1 !=returnValue) {
        		   throw new BaseException("update fail!");
			      }
             } catch (Exception e) {
            log.error("updateItemDetailFa:"+e);
			throw new BaseException(e);
			}
		}
/*查询fa订单列表*/
    @Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ItemFaQuery> queryItemQuery(ItemFaQuery ItemFaQuery, Page page) {
		long start = Calendar.getInstance().getTimeInMillis();
		if (page.needQueryPading()) {
			page.setTotalRecord(reItemDetailFaMapper.countOrder(ItemFaQuery));
		}
		long end = Calendar.getInstance().getTimeInMillis();
		System.out.println("countOrder:" +(end-start));
		ItemFaQuery.setBeginRow(page.getFilterRecord().toString());
		ItemFaQuery.setPageSize(page.getPageCount().toString());
		List<ItemFaQuery> list = this.reItemDetailFaMapper.queryItemQuery(ItemFaQuery);
		System.out.println("queryOrder:" +(Calendar.getInstance().getTimeInMillis()-end));
		return list;
	}
    /*查询fa订单列表2*/
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ItemFaQuery> queryItemQuerylist(ItemFaQuery itemFaQuery) {
		return reItemDetailFaMapper.queryOrderList(itemFaQuery);
	}

    }