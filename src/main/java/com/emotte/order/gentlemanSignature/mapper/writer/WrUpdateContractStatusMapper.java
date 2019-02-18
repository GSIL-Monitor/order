package com.emotte.order.gentlemanSignature.mapper.writer;


import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.Agreement;
@Component("wrUpdateContractStatusMapper")
public interface WrUpdateContractStatusMapper extends WrBaseMapper{

	public int updateContractStatus(Agreement agreement);
	
}
