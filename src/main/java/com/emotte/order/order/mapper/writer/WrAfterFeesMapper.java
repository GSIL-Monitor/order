package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import com.emotte.order.order.model.AfterFees;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

@Component("wrAfterFeesMapper")
public interface WrAfterFeesMapper extends WrBaseMapper {

	public void insertAfterFees(AfterFees afterFees);

	public int updateAfterFees(AfterFees afterFees);

}