package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order_push_log
 */
public class PushLog extends BaseModel {

	// 主键 : ID
	private Long id;

	// 订单id : ORDER_ID
	private Long orderId;

	// DISTINGUISH 为1,2时managerId 3为客户id : MANAGER_ID
	private Long managerId;

	// 推送消息 : MSG
	private String msg;

	// 推送方式1微信2短信 : TYPE
	private Integer type;

	// 创建者(创建者) : CREATE_BY
	private Long createBy;

	// 修改者(修改者) : UPDATE_BY
	private Long updateBy;

	// 有效性(有效性) : VALID
	private Integer valid;

	// 版本(版本) : VERSION
	private Long version;

	// 日志(日志) : LOG
	private String log;

	// 1订单2订单回访3单次派工单4签到 : DISTINGUISH
	private Integer distinguish;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Integer getDistinguish() {
		return distinguish;
	}

	public void setDistinguish(Integer distinguish) {
		this.distinguish = distinguish;
	}

	
}