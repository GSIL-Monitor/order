package com.emotte.order.order.model;

import java.math.BigDecimal;
import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_solution_cust_solution
 * : t_solution_cust_solution_item
 * : t_solution_cust_solution_plan
 */
public class Solution  extends BaseModel{

	//订单ID : ID 	
	private Long id; 
	/* 获取订单的管家信息字段 */
	private Long managerId; //录入人ID(管家ID)
	private String managerName;//录入人
	private String followByName;//分包人
	private Long followBy;
	private String followDeptName;//分包部门
	private Long followDept;
	private String followTime;//分包时间
	private String rechargeByName;//负责人
	private Long rechargeBy;
	private String rechargeDeptName;//负责人部门
	private Long rechargeDept;
	private String managerPhone;//分包管家电话
	private String rechManagerPhone;//分包管家电话
	
	private Integer solutionStatus; //解决方案状态
	private String solutionCode; //解决方案编号
	private Integer payStatus; //支付状态：20110002:新单已支付、20110001:新单待支付
	private String orderSourceId; //订单来源ID
	private String feeCardNum;//代扣卡卡号 
	private String mcode; //市场渠道来源编码 
	private java.math.BigDecimal totalPay;//总金额
	private String blessing;// 祝福语（用于解决方案订单添加祝福语和备注，信息保存到排期表里） 
	private String remark;// 备注 （用于解决方案订单添加祝福语和备注，信息保存到排期表里）
	private String payerName;//客户名称
	private String payerMobile;//客户手机号
	private java.math.BigDecimal activeMoney;//可用余额
	
	private Long itemId; //套餐id
	private String solutionCust_solutionId; //方案id
	private String proudctName;	//商品名称	
	private Integer qty; //数量 
	private Integer surplusNum;// 解决方案套餐剩余数量 
	private Integer qtyOnce;//单次配送数量
	private Integer frequency; //默认频率（1 : "一周一次", 2 : "两周一次", 3 : "一月一次", 4 : "仅一次", 5 : "自定义"）
	private String startServiceTime;//商品/服务首次服务时间
	
	private BigDecimal planFeeSum;//未生成执行订单的排期总余额
	private Integer delFlag;//排期删除标识
	
	private Long agreementId; //合同id
	private Integer agreementStatus;//合同状态
	
	private Long loginBy;// 当前登录人
	private Long loginDept;// 当前登录人部门
	private Integer loginLevel;//登录人权限级别
	private String loginAllCode;// 
	private String loginOrgCode;//当前登录人部门code
	
	// 用户移动电话 : user_mobile
	private String userMobile;
	
	private Long orderId;
	
	// 创建时间 : create_time
	private String createTime;
	
	//负责人
	private String rechargeByText;
	//负责人部门
	private String rechargeDeptText;
	
	//账户余额
	private BigDecimal balance;
	
	//解决方案类型
	private Integer solutionType;
	
	//客户id
	private Long custId;
	
	private  Long orderCode;
	
	public Long getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(Long orderCode) {
		this.orderCode = orderCode;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Integer getSolutionType() {
		return solutionType;
	}

	public void setSolutionType(Integer solutionType) {
		this.solutionType = solutionType;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getRechargeByText() {
		return rechargeByText;
	}

	public void setRechargeByText(String rechargeByText) {
		this.rechargeByText = rechargeByText;
	}

	public String getRechargeDeptText() {
		return rechargeDeptText;
	}

	public void setRechargeDeptText(String rechargeDeptText) {
		this.rechargeDeptText = rechargeDeptText;
	}

	@Override
	public String getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getUserMobile() {
			return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

/**
  * constitution
  *
  */

	public Long getLoginBy() {
		return loginBy;
	}

	public void setLoginBy(Long loginBy) {
		this.loginBy = loginBy;
	}

	public Long getLoginDept() {
		return loginDept;
	}

	public void setLoginDept(Long loginDept) {
		this.loginDept = loginDept;
	}

	public Integer getLoginLevel() {
		return loginLevel;
	}

	public void setLoginLevel(Integer loginLevel) {
		this.loginLevel = loginLevel;
	}

	public String getLoginAllCode() {
		return loginAllCode;
	}

	public void setLoginAllCode(String loginAllCode) {
		this.loginAllCode = loginAllCode;
	}

	public String getLoginOrgCode() {
		return loginOrgCode;
	}

	public void setLoginOrgCode(String loginOrgCode) {
		this.loginOrgCode = loginOrgCode;
	}

	/**
	 * 订单ID : ID
	 * 
	 * @return 
	 */
	public Long getId () {
		return id;
	}
	
	/**
	 * 订单ID : ID
	 * 
	 * @return 
	 */
	public void setId (Long id) {
		this.id = id;
	}
			
			
	/**
	 * 订单来源ID( 10:网上订单, 20 :联盟订单 ,30:线下订单,40:电话订单,50:移动APP) : ORDER_SOURCE_ID
	 * 
	 * @return 
	 */
	public String getOrderSourceId () {
		return orderSourceId;
	}
	
	/**
	 * 订单来源ID( 10:网上订单, 20 :联盟订单 ,30:线下订单,40:电话订单,50:移动APP) : ORDER_SOURCE_ID
	 * 
	 * @return 
	 */
	public void setOrderSourceId (String orderSourceId) {
		this.orderSourceId = orderSourceId;
	}
			
			
	/**
	 * 管家ID : MANAGER_ID
	 * 
	 * @return 
	 */
	public Long getManagerId () {
		return managerId;
	}
	
	/**
	 * 管家ID : MANAGER_ID
	 * 
	 * @return 
	 */
	public void setManagerId (Long managerId) {
		this.managerId = managerId;
	}
			
	
			
	/**
	 * 解决方案状态:1 : SOLUTION_STATUS
	 * 
	 * @return 
	 */
	public Integer getSolutionStatus () {
		return solutionStatus;
	}
	
	/**
	 * 解决方案状态:1 : SOLUTION_STATUS
	 * 
	 * @return 
	 */
	public void setSolutionStatus (Integer solutionStatus) {
		this.solutionStatus = solutionStatus;
	}
			
			
	/**
	 * 总金额 : TOTAL_PAY
	 * 
	 * @return 
	 */
	public java.math.BigDecimal getTotalPay () {
		return totalPay;
	}
	
	/**
	 * 总金额 : TOTAL_PAY
	 * 
	 * @return 
	 */
	public void setTotalPay (java.math.BigDecimal totalPay) {
		this.totalPay = totalPay;
	}
			
	/**
	 * 市场渠道来源编码 : MCODE
	 * 
	 * @return 
	 */
	public String getMcode () {
		return mcode;
	}
	
	/**
	 * 市场渠道来源编码 : MCODE
	 * 
	 * @return 
	 */
	public void setMcode (String mcode) {
		this.mcode = mcode;
	}

	public String getBlessing() {
		return blessing;
	}

	public void setBlessing(String blessing) {
		this.blessing = blessing;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFeeCardNum() {
		return feeCardNum;
	}

	public void setFeeCardNum(String feeCardNum) {
		this.feeCardNum = feeCardNum;
	}

	
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getFollowByName() {
		return followByName;
	}

	public void setFollowByName(String followByName) {
		this.followByName = followByName;
	}

	public String getFollowDeptName() {
		return followDeptName;
	}

	public void setFollowDeptName(String followDeptName) {
		this.followDeptName = followDeptName;
	}

	public String getFollowTime() {
		return followTime;
	}

	public void setFollowTime(String followTime) {
		this.followTime = followTime;
	}

	public String getRechargeByName() {
		return rechargeByName;
	}

	public void setRechargeByName(String rechargeByName) {
		this.rechargeByName = rechargeByName;
	}

	public String getRechargeDeptName() {
		return rechargeDeptName;
	}

	public void setRechargeDeptName(String rechargeDeptName) {
		this.rechargeDeptName = rechargeDeptName;
	}

	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	public Long getFollowBy() {
		return followBy;
	}

	public void setFollowBy(Long followBy) {
		this.followBy = followBy;
	}

	public Long getFollowDept() {
		return followDept;
	}

	public void setFollowDept(Long followDept) {
		this.followDept = followDept;
	}

	public Long getRechargeBy() {
		return rechargeBy;
	}

	public void setRechargeBy(Long rechargeBy) {
		this.rechargeBy = rechargeBy;
	}

	public Long getRechargeDept() {
		return rechargeDept;
	}

	public void setRechargeDept(Long rechargeDept) {
		this.rechargeDept = rechargeDept;
	}

	public String getRechManagerPhone() {
		return rechManagerPhone;
	}

	public void setRechManagerPhone(String rechManagerPhone) {
		this.rechManagerPhone = rechManagerPhone;
	}

	public String getProudctName() {
		return proudctName;
	}

	public void setProudctName(String proudctName) {
		this.proudctName = proudctName;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	public Integer getSurplusNum() {
		return surplusNum;
	}

	public void setSurplusNum(Integer surplusNum) {
		this.surplusNum = surplusNum;
	}

	public Integer getQtyOnce() {
		return qtyOnce;
	}

	public void setQtyOnce(Integer qtyOnce) {
		this.qtyOnce = qtyOnce;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public String getStartServiceTime() {
		return startServiceTime;
	}

	public void setStartServiceTime(String startServiceTime) {
		this.startServiceTime = startServiceTime;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayerMobile() {
		return payerMobile;
	}

	public void setPayerMobile(String payerMobile) {
		this.payerMobile = payerMobile;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getSolutionCust_solutionId() {
		return solutionCust_solutionId;
	}

	public void setSolutionCust_solutionId(String solutionCust_solutionId) {
		this.solutionCust_solutionId = solutionCust_solutionId;
	}

	public BigDecimal getPlanFeeSum() {
		return planFeeSum;
	}

	public void setPlanFeeSum(BigDecimal planFeeSum) {
		this.planFeeSum = planFeeSum;
	}

	public java.math.BigDecimal getActiveMoney() {
		return activeMoney;
	}

	public void setActiveMoney(java.math.BigDecimal activeMoney) {
		this.activeMoney = activeMoney;
	}

	public Integer getAgreementStatus() {
		return agreementStatus;
	}

	public void setAgreementStatus(Integer agreementStatus) {
		this.agreementStatus = agreementStatus;
	}

	public Long getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Long agreementId) {
		this.agreementId = agreementId;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public String getSolutionCode() {
		return solutionCode;
	}

	public void setSolutionCode(String solutionCode) {
		this.solutionCode = solutionCode;
	}

	
	}
