package com.emotte.order.order.mapper.reader;

import java.util.List;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.ScheduleCheck;

@Component("reScheduleCheckMapper")
public interface ReScheduleCheckMapper extends ReBaseMapper {
    
	//查询服务人员下一个订单负责人
	public List<ScheduleCheck> queryScheduleCheck(ScheduleCheck scheduleCheck);
	//查询延长服务申请排期
	public List<ScheduleCheck>  queryDelayLined(ScheduleCheck scheduleCheck);
	//查询延长服务申请状态
	public List<ScheduleCheck>  queryDelayLinedType(ScheduleCheck scheduleCheck);

}