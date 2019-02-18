package com.emotte.order.order.service.impl;

import javax.annotation.Resource;
import java.util.List;
import org.apache.log4j.Logger;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.emotte.order.order.model.PayfeeDetail;
import  com.emotte.order.order.mapper.reader.RePayfeeDetailMapper;
import  com.emotte.order.order.mapper.writer.WrPayfeeDetailMapper;
import com.emotte.order.order.service.PayfeeDetailService;

    @Service("payfeeDetailService")
     @Transactional
public class PayfeeDetailServiceImpl implements PayfeeDetailService{
         Logger log=Logger.getLogger(this.getClass()); 
		 
	  	@Resource
	private RePayfeeDetailMapper rePayfeeDetailMapper;
	
	    @Resource
	private WrPayfeeDetailMapper wrPayfeeDetailMapper;
    
    	@Override
		@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public PayfeeDetail loadPayfeeDetail(Long id){
	  return this.rePayfeeDetailMapper.loadPayfeeDetail(id);
	}
	
		@Override
		@Transactional(readOnly=true,propagation = Propagation.NOT_SUPPORTED)
	public List<PayfeeDetail> queryPayfeeDetail(PayfeeDetail payfeeDetail,Page page){
	   if(page.needQueryPading()){
			page.setTotalRecord(rePayfeeDetailMapper.countPayfeeDetail(payfeeDetail));
		}
		payfeeDetail.setBeginRow(page.getFilterRecord().toString());
		payfeeDetail.setPageSize(page.getPageCount().toString());
	  return this.rePayfeeDetailMapper.queryPayfeeDetail(payfeeDetail);
	}
	
	@Override
	public void insertPayfeeDetail(PayfeeDetail payfeeDetail){
	  this.wrPayfeeDetailMapper.insertPayfeeDetail(payfeeDetail);
	}
		
    @Override
	public void updatePayfeeDetail(PayfeeDetail payfeeDetail){
             int returnValue; 
           try{
           returnValue= this.wrPayfeeDetailMapper.updatePayfeeDetail(payfeeDetail);
             if (1 !=returnValue) {
        		   throw new BaseException("update fail!");
			      }
             } catch (Exception e) {
            log.error("updatePayfeeDetail:"+e);
			throw new BaseException(e);
			}
		}

    }