package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.OrderSlice; 
@Component("wrOrderSliceMapper") 
public interface WrOrderSliceMapper extends WrBaseMapper{ 

	public int insertOrderSlice(OrderSlice orderslice);

	public int updateOrderSlice(OrderSlice orderslice);

} 
