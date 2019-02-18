package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order_after_callback
 */
public class AfterCallBack extends BaseModel {

 	// 主键 : ID
	private Long id;
	
 	// 回访结果 1:回访成功,2:回访失败,3:需再次回访 : STATUS
	private Integer status;
	
 	// 下次回访时间 : NEXT_TIME
	private String nextTime;
	
 	// 回访失败原因 1:银行信息错误,2:电话号错误,3:回访超过三次无人接听 : REASON
	private String reason;

 	// 备注 : REMARK
	private String remark;

 	// 创建人 : CREATE_BY
	private Long createBy;

 	// 创建时间 : CREATE_TIME
	private String createTime;

 	// 更新人 : UPDATE_BY
	private Long updateBy;

 	// 更新时间 : UPDATE_TIME
	private String updateTime;
	
 	// 版本 : VERSION
	private Long version;
	
 	// 是否有效 1:有效,2:无效 : VALID
	private Integer valid;

	// 订单ID
	private Long orderId;

	// 售后单ID
	private Long afterSalesId;

	//是否历史数据
	private Integer isOld = 2;

	/***************************************拓展字段**************************************/
	//创建人中文名称
	private String userName;
	//开户人姓名
	private String bankUserName;
	//开户银行名称
	private String bankName;
	//银行卡号
	private String bankCard;
	//结算单ID
	private Long accountId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getNextTime() {
		return nextTime;
	}

	public void setNextTime(String nextTime) {
		this.nextTime = nextTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public Long getCreateBy() {
		return createBy;
	}

	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	@Override
	public String getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public Long getUpdateBy() {
		return updateBy;
	}

	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	@Override
	public String getUpdateTime() {
		return updateTime;
	}

	@Override
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	public void setVersion(Long version) {
		this.version = version;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getAfterSalesId() {
		return afterSalesId;
	}

	public void setAfterSalesId(Long afterSalesId) {
		this.afterSalesId = afterSalesId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Integer getIsOld() {
		return isOld;
	}

	public void setIsOld(Integer isOld) {
		this.isOld = isOld;
	}
}