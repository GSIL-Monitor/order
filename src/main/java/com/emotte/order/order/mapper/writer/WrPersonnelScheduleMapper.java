package com.emotte.order.order.mapper.writer;

import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.PersonnelSchedule;

public interface WrPersonnelScheduleMapper extends WrBaseMapper{
	
	public int updatePersonnelSchedule(PersonnelSchedule personnelSchedule);

}
