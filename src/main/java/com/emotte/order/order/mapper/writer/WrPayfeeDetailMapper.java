package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import com.emotte.order.order.model.PayfeeDetail;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;
 
  @Component("wrPayfeeDetailMapper")
public interface WrPayfeeDetailMapper extends WrBaseMapper{
	
	public void insertPayfeeDetail(PayfeeDetail payfeeDetail);
	
    public int updatePayfeeDetail(PayfeeDetail payfeeDetail);

    }