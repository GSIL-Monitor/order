package com.emotte.order.gentlemanSignature.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emotte.order.gentlemanSignature.mapper.reader.ReQueryAgreementPreviewMapper;
import com.emotte.order.gentlemanSignature.service.QueryAgreementPreviewService;
import com.emotte.order.order.model.Agreement;
@Service("queryAgreementPreviewService")
@Transactional
public class QueryAgreementPreviewServiceImpl implements QueryAgreementPreviewService{

	@Resource
	private ReQueryAgreementPreviewMapper reQueryAgreementPreviewMapper;
	@Override
	public List<Agreement> queryAgreementPreview(Agreement agreement) {
		return reQueryAgreementPreviewMapper.queryAgreementPreview(agreement);
		
	}

}
