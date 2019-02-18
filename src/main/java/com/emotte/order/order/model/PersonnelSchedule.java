package com.emotte.order.order.model;

import java.util.List;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_emp_personnel_schedule
 */
public class PersonnelSchedule extends BaseModel {

	// 主键 : ID
	private String id;

	// 人员id : EMP_ID
	private Long empId;

	// 开始日期 包含 : START_DATE
	private Long startDate;

	// 结束日期 包含 : END_DATE
	private Long endDate;

	// 开始时间 包含 : START_TIME
	private Long startTime;

	// 结束时间 不包含 : END_TIME
	private Long endTime;

	// 工作日 0 为全部 1~7分别代表周一至周日 : WEEKDAY
	private String weekday;

	// 状态 1 空闲 2预留 3 休假 4 忙碌 : STATUS
	private Long status;

	// 手动日志 : LOG
	private String log;

	// 星期（钟点工） : WEEK
	private String week;

	// 时间段 : TIMESLOT
	private String timeslot;

	// 订单id : ORDER_ID
	private Long orderId;

	// 是否启用(1:未启用 2:启用) : IS_ENABLE
	private Integer isEnable;
	
	private String msg;
	
	private String rechargeDeptName;
	private String rechargeByName;
	private String rechargeBy;
	private String rechargeDept;
	
	private String orderCode;
	
	private String realName;
	
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	private String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public Long getStartDate() {
		return startDate;
	}

	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}

	public Long getEndDate() {
		return endDate;
	}

	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRechargeDeptName() {
		return rechargeDeptName;
	}

	public void setRechargeDeptName(String rechargeDeptName) {
		this.rechargeDeptName = rechargeDeptName;
	}

	public String getRechargeByName() {
		return rechargeByName;
	}

	public void setRechargeByName(String rechargeByName) {
		this.rechargeByName = rechargeByName;
	}

	public String getRechargeBy() {
		return rechargeBy;
	}

	public void setRechargeBy(String rechargeBy) {
		this.rechargeBy = rechargeBy;
	}

	public String getRechargeDept() {
		return rechargeDept;
	}

	public void setRechargeDept(String rechargeDept) {
		this.rechargeDept = rechargeDept;
	}

}