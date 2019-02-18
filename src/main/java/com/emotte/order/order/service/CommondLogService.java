package com.emotte.order.order.service;

public interface CommondLogService {
	//添加指令日志信息
	public boolean insertCommondLog(String key ,String type, String typeValue);
}
