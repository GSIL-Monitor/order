package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.CommondLog;

@Component("wrCommondLogMapper")
public interface WrCommondLogMapper extends WrBaseMapper{

	public int insertCommondLog(CommondLog commondLog);


}
