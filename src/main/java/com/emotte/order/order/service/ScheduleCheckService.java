package com.emotte.order.order.service;

import java.util.List;

import org.wildhorse.server.core.model.Page;

import com.emotte.order.order.model.ScheduleCheck;

public interface ScheduleCheckService {

	//查询服务人员下一个订单负责人
	public List<ScheduleCheck> queryScheduleCheck(ScheduleCheck scheduleCheck, Page page);
	//查询延长服务申请排期
	public List<ScheduleCheck>  queryDelayLined(ScheduleCheck scheduleCheck);
	//查询延长服务申请状态
	public List<ScheduleCheck>  queryDelayLinedType(ScheduleCheck scheduleCheck);
}