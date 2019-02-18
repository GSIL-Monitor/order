package com.emotte.order.order.service.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emotte.order.order.mapper.writer.WrCommondLogMapper;
import com.emotte.order.order.model.CommondLog;
import com.emotte.order.order.service.CommondLogService;

@Service("commondLogService")
@Transactional
public class CommondLogServiceImpl implements CommondLogService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private WrCommondLogMapper wrCommondLogMapper;


	@Override
	public boolean insertCommondLog(String key, String type, String typeValue) {
		boolean result = false;
		CommondLog commondLog = new CommondLog();
		commondLog.setKeyConstent(key);
		commondLog.setTypeConstent(type);
		commondLog.setTypeValue(typeValue);
		int i = wrCommondLogMapper.insertCommondLog(commondLog);
		if (i == 1) {
			result = true;
		} 
		return result;
	}
	
	
}
