package com.emotte.order.gentlemanSignature.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emotte.order.gentlemanSignature.mapper.reader.ReJudementContractMapper;
import com.emotte.order.gentlemanSignature.mapper.writer.WrUpdateContractStatusMapper;
import com.emotte.order.gentlemanSignature.service.UpdateContractStatusService;
import com.emotte.order.order.model.Agreement;

@Service("updateContractStatusService")
@Transactional
public class UpdateContractStatusServiceImpl implements UpdateContractStatusService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private WrUpdateContractStatusMapper wrUpdateContractStatusMapper;
	
	@Resource
	private ReJudementContractMapper reJudementContractMapper;
	
	@Override
	public Agreement judementContract(Agreement agreement) {
		Agreement a = null;
		if(agreement.getId()!=null){
		//if(String.valueOf(agreement.getId())!=null){
		 a = reJudementContractMapper.judementContract(agreement);
		}
		System.out.println(a);
		return a;
	}


	@Override
	public int updateContractStatus(Agreement agreement) {
		int t = wrUpdateContractStatusMapper.updateContractStatus(agreement);
		return t;
	}

}