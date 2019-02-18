package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order_server_lined_record
 */
public class ServerLinedRecord extends BaseModel {

 	// id : ID
	private Long id;
	
	
 	// 订单id : ORDER_ID
	private Long orderId;
	
	
 	// 排期开始时间 : START_TIME
	private String startTime;
	
	
 	// 排期结束时间 : END_TIME
	private String endTime;
	
	
 	// 创建人 : CREATE_BY
	private Long createBy;
	
	
 	// 日志 : LOG
	private String log;
	
	

	/**
	 * id : ID
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * id : ID
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 订单id : ORDER_ID
	 * 
	 * @return
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * 订单id : ORDER_ID
	 * 
	 * @return
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * 排期开始时间 : START_TIME
	 * 
	 * @return
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * 排期开始时间 : START_TIME
	 * 
	 * @return
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * 排期结束时间 : END_TIME
	 * 
	 * @return
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * 排期结束时间 : END_TIME
	 * 
	 * @return
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * 创建人 : CREATE_BY
	 * 
	 * @return
	 */
	@Override
	public Long getCreateBy() {
		return createBy;
	}

	/**
	 * 创建人 : CREATE_BY
	 * 
	 * @return
	 */
	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	
	/**
	 * 日志 : LOG
	 * 
	 * @return
	 */
	public String getLog() {
		return log;
	}

	/**
	 * 日志 : LOG
	 * 
	 * @return
	 */
	public void setLog(String log) {
		this.log = log;
	}
	
	
}