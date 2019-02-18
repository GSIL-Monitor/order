package com.emotte.order.timer;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.emotte.order.order.mapper.reader.ReOrderMapper;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.service.OrderService;

/**
 * 订单定时任务
 * @time 2016-9-7
 *
 */
@Component
public class OrderTimerTask {
	
	
	@Resource
	private OrderService orderService;
	@Resource
	private ReOrderMapper reOrderMapper;
	/**
	 * 自营单次服务人员服务人员服务费分账定时任务
	 * 每天00:00执行
	 * @author wn
	 */
	//@Scheduled(cron="0 0 0 * * ?")
	public void orderItemSalaryTask(){
		//查询订单类型为自营单次的订单及结算单信息
		System.out.println("自营单次服务人员服务人员服务费预算定时任务开始>>>>");
		List<Order> orderList = this.reOrderMapper.queryOrderNotInItem();
		System.out.println("未处理单次服务已完成订单：["+(orderList==null ? 0:orderList.size())+"] 个。");
		if (orderList != null && !orderList.isEmpty()) { 
			this.orderService.insertOrderSlice(orderList);
			this.orderService.updateOrderSalary(orderList);
		}
		System.out.println("服务人员服务人员服务费预算已全部生成！");
		System.out.println("<<<<<<<自营单次服务人员服务人员服务费预算定时任务结束！");
	}
}
