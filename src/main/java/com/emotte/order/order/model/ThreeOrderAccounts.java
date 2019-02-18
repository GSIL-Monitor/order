package com.emotte.order.order.model;

public class ThreeOrderAccounts {

	private Long orderId;

	private Long accountsId;

	private String createTime;

	private String accountAmount;

	private Long payfeeId;

	private String postUser;

	private String feePost;

	private String feeSum;

	private String payStatus;

	private String payType;

	private String isBackType;

	private String startTime;

	private String endTime;

	private Integer valid;

	private String payKind;

	private String bussStatus;

	private String remark;

	private Long createBy;

	private Long updateBy;

	private Long version;

	private Object children;

	private String postBank;

	private String bankflowNum;

	private String accountStatus;

	// 支付类型
	private String strFeePost;

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getBankflowNum() {
		return bankflowNum;
	}

	public void setBankflowNum(String bankflowNum) {
		this.bankflowNum = bankflowNum;
	}

	public String getPostBank() {
		return postBank;
	}

	public void setPostBank(String postBank) {
		this.postBank = postBank;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getAccountsId() {
		return accountsId;
	}

	public void setAccountsId(Long accountsId) {
		this.accountsId = accountsId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(String accountAmount) {
		this.accountAmount = accountAmount;
	}

	public Long getPayfeeId() {
		return payfeeId;
	}

	public void setPayfeeId(Long payfeeId) {
		this.payfeeId = payfeeId;
	}

	public String getPostUser() {
		return postUser;
	}

	public void setPostUser(String postUser) {
		this.postUser = postUser;
	}

	public String getFeePost() {
		return feePost;
	}

	public void setFeePost(String feePost) {
		this.feePost = feePost;
	}

	public String getFeeSum() {
		return feeSum;
	}

	public void setFeeSum(String feeSum) {
		this.feeSum = feeSum;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getIsBackType() {
		return isBackType;
	}

	public void setIsBackType(String isBackType) {
		this.isBackType = isBackType;
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

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public String getPayKind() {
		return payKind;
	}

	public void setPayKind(String payKind) {
		this.payKind = payKind;
	}

	public String getBussStatus() {
		return bussStatus;
	}

	public void setBussStatus(String bussStatus) {
		this.bussStatus = bussStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Object getChildren() {
		return children;
	}

	public void setChildren(Object children) {
		this.children = children;
	}

	public String getStrFeePost() {
		return strFeePost;
	}

	public void setStrFeePost(String strFeePost) {
		this.strFeePost = strFeePost;
	}

}
