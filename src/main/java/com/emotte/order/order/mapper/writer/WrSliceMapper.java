package com.emotte.order.order.mapper.writer;


import com.emotte.order.order.model.Slice;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

@Component("wrSliceMapper")
public interface WrSliceMapper extends WrBaseMapper {

	public void insertSlice(Slice slice);

	public int updateSlice(Slice slice);
}