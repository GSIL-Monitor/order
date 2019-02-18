package com.emotte.order.order.mapper.reader;

import java.util.List;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;
import com.emotte.order.order.model.PayfeeDetail;

  @Component("rePayfeeDetailMapper")
public interface RePayfeeDetailMapper extends ReBaseMapper{
    
	public PayfeeDetail loadPayfeeDetail(Long id);
	
	public List<PayfeeDetail> queryPayfeeDetail(PayfeeDetail payfeeDetail);
	
	public Integer countPayfeeDetail(PayfeeDetail payfeeDetail);

    }