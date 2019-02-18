package com.emotte.dubbo.order.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emotte.dubbo.order.PersonInterviewService;
import com.emotte.dubbo.order.PersonnelScheduleService;
import com.emotte.kernel.exception.BaseException;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.order.mapper.reader.ReItemDetailServerMapper;
import com.emotte.order.order.mapper.reader.RePersonInterviewServiceMapper;
import com.emotte.order.order.mapper.writer.WrItemInterviewMapper;
import com.emotte.order.order.model.ItemDetailServer;
import com.emotte.order.order.model.ItemInterview;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.service.impl.ItemInterviewServiceImpl;
import com.emotte.order.util.Tools;

import net.sf.json.JSONObject;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class PersonInterviewServiceImpl implements PersonInterviewService {
	
	@Resource
	private RePersonInterviewServiceMapper rePersonInterviewServiceMapper;
	
	@Resource
	private PersonnelScheduleService personnelScheduleService;
	
	@Resource
	private ReItemDetailServerMapper reItemDetailServerMapper;
	
	@Resource
	private WrItemInterviewMapper wrItemInterviewMapper;
	
	@Resource
	private ItemInterviewServiceImpl itemInterviewServiceImpl;
	
	
	/**
	 * 服务人员约Ta面试 支持钟点工和延续性订单
	 */
	@Override
	public String personInterview(String json) {
		LogHelper.info(getClass() + ".personInterview()", "接收的数据:" + json);
		String result = "{'code':'1','msg':'请求操作失败'}";
		JSONObject jsonObject=JSONObject.fromObject(json);
		String startTime="";
		String endTime="";
		try {
			if(null!=jsonObject){
				Long orderId=Long.valueOf(jsonObject.get("orderId").toString());
				String personIds=(String) jsonObject.get("personIds");
				String [] personId=personIds.split(",");
				ItemDetailServer itemDetailServer=rePersonInterviewServiceMapper.loadOrderServerLined(orderId);
				ItemDetailServer itemDetail=rePersonInterviewServiceMapper.selectItemDetailServer(orderId);
				Order order =rePersonInterviewServiceMapper.selectOrder(orderId);
				if(null!=itemDetailServer){
					ItemInterview item = new ItemInterview();
					item.setOrderId(orderId);
					item.setOrderItemId(itemDetail.getId());
					item.setCreateBy(order.getUserId());
					item.setValid(1);
					item.setVersion(0L);
					item.setInterviewType(1);
					item.setMatchMethod("4");
					String hours=itemDetailServer.getHours();
					String newHours=Tools.transitionTimes(hours);
					if(!"".equals(newHours)){
						String[] data=newHours.split(",");
						 startTime=data[0];
						 endTime=data[1];
					}
					String startDate =itemDetailServer.getStartTime();
					String endDate=itemDetailServer.getEndTime();
					String weeks=itemDetailServer.getWeeks();
					String newWeeks=Tools.transitionWeeks(weeks);
					ItemDetailServer itd = this.reItemDetailServerMapper.loadOrderServerLined(orderId);
					Order order2=new Order();
					order2.setId(orderId);
					order2.setUpdateBy(order.getUserId());
					for (int i = 0; i < personId.length; i++) {
						item.setPersonId(Long.valueOf(personId[i]));
						String datas="{'personnelSchedule':{'empId':'"+personId[i]+"','startDate':'"+startDate+"','endDate':'"+endDate+"','startTime':'"+startTime+"','endTime':'"+endTime+"','weekday':'"+newWeeks+"'}}";
						//查询排期冲突
						String result2=personnelScheduleService.queryConflictSchedule(datas);
						JSONObject jsonObject1=JSONObject.fromObject(result2);
						String code=(String) jsonObject1.get("code");
						if(!code.equals("2")){
							throw new BaseException(jsonObject1.getString(result2));
						}
						if("".equals(newWeeks)){
							itd.setLinedType(3);
						}else{
							itd.setLinedType(2);
						}
						//添加服务人员排期
						this.itemInterviewServiceImpl.lindDays(itd,Long.valueOf(personId[i]),4);
						//添加上户
						this.wrItemInterviewMapper.insertItemInterview(item);
					}
					//修改订单状态
					this.rePersonInterviewServiceMapper.updateOrderStatus(order2);
					result = "{'code':'0','msg':'请求成功'}";
				}
			}
			
		} catch (Exception e) {
			LogHelper.info(getClass() + ".personInterview()", e.getMessage());
			
		}
		LogHelper.info(getClass() + ".personInterview()返回去的数据",result);
		return result;
	}

}
