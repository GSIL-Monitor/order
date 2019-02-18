package com.emotte.order.order.service;
import java.util.List;
import com.emotte.order.order.model.PayfeeDetail;
import org.wildhorse.server.core.model.Page;

public interface PayfeeDetailService{
    
	public PayfeeDetail loadPayfeeDetail(Long id);
	
	public List<PayfeeDetail> queryPayfeeDetail(PayfeeDetail payfeeDetail,Page page);
	
	public void insertPayfeeDetail(PayfeeDetail payfeeDetail);
	
    public void updatePayfeeDetail(PayfeeDetail payfeeDetail);

    }