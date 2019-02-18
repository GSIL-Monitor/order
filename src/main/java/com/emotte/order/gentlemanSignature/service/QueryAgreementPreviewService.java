package com.emotte.order.gentlemanSignature.service;

import java.util.List;

import com.emotte.order.order.model.Agreement;

public interface QueryAgreementPreviewService {
  
	public List<Agreement> queryAgreementPreview(Agreement agreement);
}
