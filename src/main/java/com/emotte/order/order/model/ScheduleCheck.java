package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : T_EMP_SCHEDULE_CHECK
 */
public class ScheduleCheck extends BaseModel {

 	// 主键id : ID
	private Long id;
	
	
 	// 创建人 : CREATE_BY
	private Long createBy;
	
	
 	// 审批时间 : CHECK_TIME
	private String checkTime;
	
	
 	// 审批人 : CHECK_BY
	private Long checkBy;
	
	
 	// 审批内容 : CHECK_DETAIL
	private String checkDetail;
	
	
 	// 审批详情ID（t_emp_schedule_check_detail 的ID） : CHECK_ID
	private Long checkId;
	
	
 	// 审批状态：1审核中,2通过，3不通过 4取消 : CHECK_STATUS
	private Integer checkStatus;
	
	
 	// 审批类型：1 排期 2其它 : CHECK_TYPE
	private Integer checkType;
	
	
 	// 版本号 : VERSION
	private Long version;
	
	
 	// 是否有效 1有效 2无效 : VALID
	private Integer valid;
	
	
 	// 当前订单ID : ORDER_ID
	private Long orderId;
	
	
 	// 服务人员ID : PERSON_ID
	private Long personId;
	
	
 	// 更新人 : UPDATE_BY
	private Long updateBy;
	
	
 	// 冲突订单ID : CLASH_ORDER_ID
	private Long clashOrderId;
	//辅助属性
	private String detailId;//详细表id
	private String detailStartTime;//申请延长 开始
	private String detailEndTime;//申请延长 结束
	private String orderCode;//订单code
	private String managerName;//管家名字
	private String managerPhone;//管家电话
	private String orgName;//部门名字
	private String startTime;//订单排期 开始
	private String endTime;//订单排期 结束
	private String checkStatusText;//状态

	/**
	 * 主键id : ID
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 主键id : ID
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * 审批时间 : CHECK_TIME
	 * 
	 * @return
	 */
	public String getCheckTime() {
		return checkTime;
	}

	/**
	 * 审批时间 : CHECK_TIME
	 * 
	 * @return
	 */
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	
	/**
	 * 审批人 : CHECK_BY
	 * 
	 * @return
	 */
	public Long getCheckBy() {
		return checkBy;
	}

	/**
	 * 审批人 : CHECK_BY
	 * 
	 * @return
	 */
	public void setCheckBy(Long checkBy) {
		this.checkBy = checkBy;
	}
	
	/**
	 * 审批内容 : CHECK_DETAIL
	 * 
	 * @return
	 */
	public String getCheckDetail() {
		return checkDetail;
	}

	/**
	 * 审批内容 : CHECK_DETAIL
	 * 
	 * @return
	 */
	public void setCheckDetail(String checkDetail) {
		this.checkDetail = checkDetail;
	}
	
	/**
	 * 审批详情ID（t_emp_schedule_check_detail 的ID） : CHECK_ID
	 * 
	 * @return
	 */
	public Long getCheckId() {
		return checkId;
	}

	/**
	 * 审批详情ID（t_emp_schedule_check_detail 的ID） : CHECK_ID
	 * 
	 * @return
	 */
	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}
	
	/**
	 * 审批状态：1审核中,2通过，3不通过 4取消 : CHECK_STATUS
	 * 
	 * @return
	 */
	public Integer getCheckStatus() {
		return checkStatus;
	}

	/**
	 * 审批状态：1审核中,2通过，3不通过 4取消 : CHECK_STATUS
	 * 
	 * @return
	 */
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	/**
	 * 审批类型：1 排期 2其它 : CHECK_TYPE
	 * 
	 * @return
	 */
	public Integer getCheckType() {
		return checkType;
	}

	/**
	 * 审批类型：1 排期 2其它 : CHECK_TYPE
	 * 
	 * @return
	 */
	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}
	
	/**
	 * 版本号 : VERSION
	 * 
	 * @return
	 */
	@Override
	public Long getVersion() {
		return version;
	}

	/**
	 * 版本号 : VERSION
	 * 
	 * @return
	 */
	@Override
	public void setVersion(Long version) {
		this.version = version;
	}
	
	/**
	 * 是否有效 1有效 2无效 : VALID
	 * 
	 * @return
	 */
	public Integer getValid() {
		return valid;
	}

	/**
	 * 是否有效 1有效 2无效 : VALID
	 * 
	 * @return
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	
	/**
	 * 当前订单ID : ORDER_ID
	 * 
	 * @return
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * 当前订单ID : ORDER_ID
	 * 
	 * @return
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * 服务人员ID : PERSON_ID
	 * 
	 * @return
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * 服务人员ID : PERSON_ID
	 * 
	 * @return
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	
	/**
	 * 更新人 : UPDATE_BY
	 * 
	 * @return
	 */
	@Override
	public Long getUpdateBy() {
		return updateBy;
	}

	/**
	 * 更新人 : UPDATE_BY
	 * 
	 * @return
	 */
	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	
	
	/**
	 * 冲突订单ID : CLASH_ORDER_ID
	 * 
	 * @return
	 */
	public Long getClashOrderId() {
		return clashOrderId;
	}

	/**
	 * 冲突订单ID : CLASH_ORDER_ID
	 * 
	 * @return
	 */
	public void setClashOrderId(Long clashOrderId) {
		this.clashOrderId = clashOrderId;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getDetailStartTime() {
		return detailStartTime;
	}

	public void setDetailStartTime(String detailStartTime) {
		this.detailStartTime = detailStartTime;
	}

	public String getDetailEndTime() {
		return detailEndTime;
	}

	public void setDetailEndTime(String detailEndTime) {
		this.detailEndTime = detailEndTime;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCheckStatusText() {
		return checkStatusText;
	}

	public void setCheckStatusText(String checkStatusText) {
		this.checkStatusText = checkStatusText;
	}
	
	
}