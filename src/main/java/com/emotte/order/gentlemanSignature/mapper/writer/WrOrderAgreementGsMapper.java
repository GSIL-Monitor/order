package com.emotte.order.gentlemanSignature.mapper.writer;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.gentlemanSignature.model.AgreementGs;
@Component("wrOrderAgreementGsMapper")
public interface WrOrderAgreementGsMapper extends WrBaseMapper{
	
	public int insertOrderAgreementGs(AgreementGs agreementGs);
	
	public int updateOrderAgreementGs(AgreementGs agreementGs);

}
