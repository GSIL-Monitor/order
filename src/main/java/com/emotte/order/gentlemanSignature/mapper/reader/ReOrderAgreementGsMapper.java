package com.emotte.order.gentlemanSignature.mapper.reader;

import java.util.List;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.gentlemanSignature.model.AgreementGs;

@Component("reOrderAgreementGsMapper") 
public interface ReOrderAgreementGsMapper extends ReBaseMapper{

	public List<AgreementGs> queryOrderAgreementGs(AgreementGs agreementGs);
	
}
