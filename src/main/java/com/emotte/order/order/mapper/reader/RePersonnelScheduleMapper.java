package com.emotte.order.order.mapper.reader;

import java.util.List;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.Personnel;
import com.emotte.order.order.model.PersonnelSchedule;

@Component("rePersonnelScheduleMapper")
public interface RePersonnelScheduleMapper extends ReBaseMapper {
	
	public List<PersonnelSchedule> querySchedule(PersonnelSchedule personnelSchedule);
	
	public List<PersonnelSchedule> queryConflictSchedule(PersonnelSchedule personnelSchedule);

	public Personnel loadPersonnel(Long id);

}
