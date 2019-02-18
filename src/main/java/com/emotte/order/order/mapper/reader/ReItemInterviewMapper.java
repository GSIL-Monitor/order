package com.emotte.order.order.mapper.reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.ItemInterview;

  @Component("reItemInterviewMapper")
public interface ReItemInterviewMapper extends ReBaseMapper{
    
	public ItemInterview loadItemInterview(Long id);
	
	public List<ItemInterview> queryItemInterview(ItemInterview itemInterview);
	public List<ItemInterview> queryNeedPersons(ItemInterview itemInterview);
	
	public Integer countItemInterview(ItemInterview itemInterview);
	//查询做服务人员服务人员服务费的时段月份人员等
	public List<HashMap> querySalaryCondition(HashMap mapCondition);
	public List<ItemInterview> queryItemInterviewByOrderId(ItemInterview itemInterview);
	public List<ItemInterview> queryInterviewsByOrderId(ItemInterview itemInterview);

	public List<Long> queryNotHousehold(ItemInterview itemInterview);

	public ItemInterview queryInterview(ItemInterview itemInterview);
    //查询所有已匹配的服务人员
	public int queryMatchingPersonId(ItemInterview itemInterview);
	
	public List<ItemInterview> queryInterviews(ItemInterview itemInterview);
	
	public ItemInterview checkRetuningJobsUp(ItemInterview itemInterview);

	public List<ItemInterview> selectSchedule(ItemInterview itemInterview);
	
	public ItemInterview selectConflictSchedule(ItemInterview itemInterview);
	
	public Integer validateSendpies(ItemInterview itemInterview);
	
	
	public List<Map> queryShortRecode(Map map);

	public List<ItemInterview> queryOrderStatus(Long orderId);

	public ItemInterview loadItemInterviewInfo(Long id);
	
    }