package com.emotte.order.order.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emotte.order.order.model.ItemDetailServer;
import com.emotte.order.order.model.ItemInterview;

import org.wildhorse.server.core.model.Page;

public interface ItemInterviewService{
    
	public ItemInterview loadItemInterview(Long id);
	
	
	public List<ItemInterview> queryItemInterview(ItemInterview itemInterview,Page page);
	public List<ItemInterview> queryNeedPersons(ItemInterview itemInterview);
	
	public void insertItemInterview(ItemInterview itemInterview);
	
    public void updateItemInterview(ItemInterview itemInterview);
    
    //查询做服务人员服务人员服务费的时段月份人员等
    public List<HashMap> querySalaryCondition(HashMap mapCondition);

	public void contradictionPai(ItemDetailServer itemDetailServer);
	//操作服务人员排期
	public void lindDays(ItemDetailServer itd,Long personId,int status);
    //查询已匹配的服务人员
	public int queryMatchingPersonId(ItemInterview itemInterview);

	public List<ItemInterview> queryInterviews(ItemInterview itemInterview);
	
	public ItemInterview checkRetuningJobsUp(ItemInterview itemInterview);
	
	public ItemInterview selectConflictSchedule(ItemInterview itemInterview);

	/**
	 * 修改服务人员状态
	 *
	 * @param status		人员状态
	 * @param personId		人员ID
     */
	void updatePersonnalStatus(long status, Long personId);
	
	
	
	/**
	 * 点击发送派工单按钮 验证是否订单已发送过派工单
	 * @author xyp
	 * 20180706
	 */
	
	public Integer validateSendpies(ItemInterview itemInterview);
	
	public List<Map> queryShortRecode(Map map);
	public boolean checkOrderId(Map map);


	public List<ItemInterview> queryOrderStatus(Long orderId);
	
	public ItemInterview loadItemInterviewInfo(Long id);
	
}