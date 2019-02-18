package com.emotte.order.order.model;

import java.io.Serializable;

public class AccountActivity implements Serializable{
	
	//表t_order_activity_account
	
	//id
	private Long id;
	
	//结算单id
	private Long accountId;
	
	//订单id
	private Long orderId;
	
	//活动类型
	private int type;
	
	//版本
	private int status;
	
	//创建时间
	private String createTime;
	
	//更新时间
	private String updateTime;
	
	//是否有效 1有效 2无效
	private int valid;
	
	//备注
	private String remake;
	
	//手动日志
	private String log;
	
	//活动id
	private Long activityId;
	

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public int getValid() {
		return valid;
	}

	public void setValid(int valid) {
		this.valid = valid;
	}

	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
