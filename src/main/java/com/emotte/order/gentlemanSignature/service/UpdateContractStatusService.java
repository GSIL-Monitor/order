package com.emotte.order.gentlemanSignature.service;

import com.emotte.order.order.model.Agreement;

public interface UpdateContractStatusService {

	public Agreement judementContract(Agreement agreement);
	
	public int updateContractStatus(Agreement agreement);
}
