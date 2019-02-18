package com.emotte.order.gentlemanSignature.mapper.reader;

import java.util.List;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.Agreement;


@Component("reQueryAgreementPreviewMapper")
public interface ReQueryAgreementPreviewMapper extends ReBaseMapper{
    
	public List<Agreement> queryAgreementPreview(Agreement agreement);
	
}
