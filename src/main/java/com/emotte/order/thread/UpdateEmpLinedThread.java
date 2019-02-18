package com.emotte.order.thread;

import com.emotte.eserver.core.exception.BaseException;
import com.emotte.kernel.spring.SpringContext;
import com.emotte.order.order.model.ItemDetailServer;
import com.emotte.order.order.service.ItemInterviewService;
import com.emotte.order.util.LogHelper;

public class UpdateEmpLinedThread  extends Thread {
	
	private Long personId;
	private Long orderId;
	private ItemDetailServer itemDetailServer;
	private ItemInterviewService itemInterviewService;

	public UpdateEmpLinedThread(ItemDetailServer itemDetailServer,Long personId,Long orderId) {
		//将服务放到容器内，线程启动后，便执行
		itemInterviewService =  (ItemInterviewService) SpringContext.getApplicationContext().getBean("itemInterviewService");
		this.personId = personId;
		this.orderId = orderId;
		this.itemDetailServer = itemDetailServer;
	}
	
	@Override
	public void run() {
		send();
	} 

	public void send () {
		//占用服务人员排期
		try {
			this.itemInterviewService.lindDays(itemDetailServer,personId,4);
			LogHelper.info(getClass()+ "lindDays()","占用排期");
		} catch (BaseException e) {
			LogHelper.error(getClass(), ".lindDays()"+e.getMessage(),e);
		}
	}
}
