package com.emotte.order.gentlemanSignature.mapper.reader;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.Agreement;
@Component("reJudementContractMapper")
public interface ReJudementContractMapper extends ReBaseMapper{
	
	public Agreement judementContract(Agreement agreement);
	
	public Agreement queryContract(Agreement agreement);
}
