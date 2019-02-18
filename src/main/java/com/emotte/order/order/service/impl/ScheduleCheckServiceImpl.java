package com.emotte.order.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wildhorse.server.core.model.Page;

import com.emotte.order.order.mapper.reader.ReScheduleCheckMapper;
import com.emotte.order.order.model.ScheduleCheck;
import com.emotte.order.order.service.ScheduleCheckService;

@Service("scheduleCheckService")
@Transactional
public class ScheduleCheckServiceImpl implements ScheduleCheckService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ReScheduleCheckMapper reScheduleCheckMapper;
	 /**
     * 查询服务人员下一个订单负责人
     * @param scheduleCheck
     * @date 2017年 8月22日
     *
     */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<ScheduleCheck> queryScheduleCheck(ScheduleCheck scheduleCheck, Page page) {
		return this.reScheduleCheckMapper.queryScheduleCheck(scheduleCheck);
	}
	
	/**
     * 查询延长服务申请排期
     * @param scheduleCheck
     * @date 2017年 8月23日
     *
     */
	@Override
	public List<ScheduleCheck> queryDelayLined(ScheduleCheck scheduleCheck) {		
		return this.reScheduleCheckMapper.queryDelayLined(scheduleCheck);
	}
	/**
	 * 查询延长服务申请状态
	 * @param scheduleCheck
	 * @date 2017年 8月23日
	 *
	 */
	@Override
	public List<ScheduleCheck> queryDelayLinedType(ScheduleCheck scheduleCheck) {		
		return this.reScheduleCheckMapper.queryDelayLinedType(scheduleCheck);
	}


}
