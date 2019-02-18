package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import com.emotte.order.order.model.PushLog;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

@Component("wrPushLogMapper")
public interface WrPushLogMapper extends WrBaseMapper {

	public void insertPushLog(PushLog pushLog);

	public int updatePushLog(PushLog pushLog);

}