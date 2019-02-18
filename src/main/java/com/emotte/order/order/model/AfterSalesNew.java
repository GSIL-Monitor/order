package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

import java.math.BigDecimal;

/**
 * : t_order_after_sales
 */
public class AfterSalesNew extends BaseModel {

	/**
	 * 售后的主键
	 */
	private Long id;

	/**
	 * 关联的订单ID，解决方案ID等
	 */
	private Long orderId;
	private Long loginBy;// 当前登录人
	private Long loginDept;// 当前登录人部门
	private Integer loginLevel;// 登录人权限级别
	private String loginAllCode;//
	private String loginOrgCode;// 当前登录人部门code
	private int loginByOrNot; // 是否当前登录人操作,用于对还未分包的订单进行操作判断
	private int loginRechargeOrNot; // 是否当前录入人操作，用于对已经分包的订单进行操作判断
	private int loginDeptOrnot; // 是否当前登录人部门操作
	private Long payMentId;//收支ID页面判断是否有提成信息
	public Integer getSalesType() {
		return salesType;
	}

	public void setSalesType(Integer salesType) {
		this.salesType = salesType;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	/**
	 * 关联的订单类型：1解决方案，2 订单
	 */
	private String orderType;

	/**
	 * 售后单类型：
	 * 	1:FA订单取消
	 * 	2:FA订单退货/退款
	 * 	3:FA订单换货重发
	 * 	4:单次服务订单取消
	 * 	5:延续性服务订单取消
	 * 	6:解决方案订单取消
	 * 	7:延续性服务订单换人
	 * 	8:延续性服务订单终止
	 * 	9:解决方案订单退费
	 * 	10:延续性订单退费
	 * 	11:延续性服务订单分期退费
	 * 	12:迁单
	 * 	13:唯品会白条分期服务费退费(原:分期手续费退款)】
	 * 	14:海金保理白条分期服务费退费
	 * 	15:招行分期分期服务费退费
	 */
	private String afterSalesKind;

	/**
	 * 客户电话
	 */
	private String custMobile;

	/**
	 * 客户姓名
	 */
	private String custName;

	/**
	 * 审核状态：1待审核，2，已通过，3，未通过
	 */
	private String auditFlag;
	// 原因
	private String reason;
	// 售后图片地址
	private String afterSalesImgs;
	// 下户时间
	private String serverTime;
	// 创建时间-起始
	private String creStart;
	// 创建时间-结束
	private String creEnd;
	// 退款总额
	private java.math.BigDecimal refundTotal;
	// 银行卡卡号
	private String bankCard;
	// 开户行名称
	private String bankName;
	// 开户人姓名
	private String bankUserName;
	// 银行类型
	private Long bankSupportId;
	// 开户城市
	private String bankCityCode;
	// 订单
	private Order order;
	// 备注
	private String remark;
	// 主行名称
	private String bankMainName;
	// 订单编号
	private String orderCode;
	// 退款结算单id
	private Long accountPayId;
	// 是否有效 1 有效 ， 2 无效 : valid
	private Integer valid;
	// 结算单审核失败原因
	private String accountRemark;
	// 审批人id
	private Long approveBy;
	// 审批人部门
	private Long approveDept;
	// 应退信息费
	private java.math.BigDecimal refundMembershipFee;
	// 应退服务人员服务费
	private java.math.BigDecimal refundSalaryFee;
	// 审批人名称
	private String approveByText;
	// 审批人部门名称
	private String approveDeptText;
	// 录入人名称
	private String createByText;
	// 录入人部门名称
	private String createDeptText;
	// 录入人部门id
	private Long createDept;
	// 确认有效、无效标记 1有效，2无效
	private Integer makesureFlag;
	// 更新售后单标记
	private Integer handle;
	// 是否手动 1是 2否
	private Integer isAT;
	// 管家帮分期结算单ID（唯品会结算单ID）
	private Long vphAccountId;
	// 管家帮分期金额
	private java.math.BigDecimal vphFee;
	// 管家帮分期取消状态（20310001 取消中，20310002取消成功，20310003取消失败）
	private String vphCancleStatus;
	// 管家帮分期退费状态
	private String vphBackStatus;
	// 管家帮分期ID
	private String vphId;
	// 转单订单ID
	private Long moveToOrderId;
	// 转单订单业务处理状态
	private String moveOrderBusFlag;
	// 是否终止 1是2否
	private Integer isStop;
	// 唯品会退费对象 1 唯品会 2客户
	private Integer refundObject;
	// 退款原因标记
	private Integer reasonFlag;
	// 迁至订单编号
	private String changeCode;

	// 未缴售后手续费
	private BigDecimal payfeeMoneyC;

	// 售后手续费结算单id
	private Long feeAcountId;

	/*20180523退卡售后流程增加字段*/
	private String cardNumber;	//卡片编号
	private Long solutionId;	//方案ID

	/*20180525日白条退费售后流程增加字段*/
	private BigDecimal baiTiaoAmount;//白条退费金额

	/*20180529白条售后页面缴费明细增加字段*/
	private Long feePost;				//分期类型（手续费类型）
	private Long payFeeId;				//缴费单ID
	private Long sourceFeePost;			//支持退款方式
	private BigDecimal payFeeAmount;	//缴费金额
	private BigDecimal payFeeDetail;	//缴费明细金额
	private BigDecimal returnAmount;	//可退款金额（可退手续费金额）

	/*20180601分期手续费退费新增字段*/
	private BigDecimal serviceChargeAmount;//手续费金额
	private String payType;
	
	private Long bType;

	/*20180619日卡售后新增字段*/
	private String userName;
	private String recommendName;//推卡人
	private String recommendInst;//推卡部门
	private Long cardStatus;//卡状态
	private String openCardDate;//开卡日期
	private String validDate;//卡有效期
	private String channelName;//渠道来源
	private BigDecimal totalMoney;//卡面额
	private BigDecimal cardbalance;//卡余额
	private Long isGive;//是否赠卡标识
	
	private String cardNumb;
	
	private BigDecimal feeSum;
	public BigDecimal getFeeSum() {
		return feeSum;
	}

	public void setFeeSum(BigDecimal feeSum) {
		this.feeSum = feeSum;
	}

	public String getCardNumb() {
		return cardNumb;
	}

	public void setCardNumb(String cardNumb) {
		this.cardNumb = cardNumb;
	}

	public String getOpenCardDate() {
		return openCardDate;
	}

	public void setOpenCardDate(String openCardDate) {
		this.openCardDate = openCardDate;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	private Integer isGJTK;//是否管家推卡
	private Integer isPushMoney;//是否有提成
	private String personnelName;//管家姓名
	private BigDecimal gjMoney;//管家提成
	private BigDecimal gjBalance;//扣除提成后管家账户余额
	private Long cardSellRecordId;
	private Integer salesType;
	private Long custId;
	private Long cardId;
	
	private BigDecimal allOncePrirce; //单价总和
	
	private BigDecimal otherMoney;//他人代收
	
	private BigDecimal solutionExpend; //支出
	
	private Integer solutionType; //解决方案状态


	public Integer getSolutionType() {
		return solutionType;
	}

	public void setSolutionType(Integer solutionType) {
		this.solutionType = solutionType;
	}

	public BigDecimal getAllOncePrirce() {
		return allOncePrirce;
	}

	public void setAllOncePrirce(BigDecimal allOncePrirce) {
		this.allOncePrirce = allOncePrirce;
	}

	public BigDecimal getOtherMoney() {
		return otherMoney;
	}

	public void setOtherMoney(BigDecimal otherMoney) {
		this.otherMoney = otherMoney;
	}

	public BigDecimal getSolutionExpend() {
		return solutionExpend;
	}

	public void setSolutionExpend(BigDecimal solutionExpend) {
		this.solutionExpend = solutionExpend;
	}

	public Long getbType() {
		return bType;
	}

	public void setbType(Long bType) {
		this.bType = bType;
	}

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

	public int getLoginByOrNot() {
		return loginByOrNot;
	}

	public void setLoginByOrNot(int loginByOrNot) {
		this.loginByOrNot = loginByOrNot;
	}

	public int getLoginRechargeOrNot() {
		return loginRechargeOrNot;
	}

	public void setLoginRechargeOrNot(int loginRechargeOrNot) {
		this.loginRechargeOrNot = loginRechargeOrNot;
	}

	public int getLoginDeptOrnot() {
		return loginDeptOrnot;
	}

	public void setLoginDeptOrnot(int loginDeptOrnot) {
		this.loginDeptOrnot = loginDeptOrnot;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getAfterSalesKind() {
		return afterSalesKind;
	}

	public void setAfterSalesKind(String afterSalesKind) {
		this.afterSalesKind = afterSalesKind;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAuditFlag() {
		return auditFlag;
	}

	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAfterSalesImgs() {
		return afterSalesImgs;
	}

	public void setAfterSalesImgs(String afterSalesImgs) {
		this.afterSalesImgs = afterSalesImgs;
	}

	public String getServerTime() {
		return serverTime;
	}

	public void setServerTime(String serverTime) {
		this.serverTime = serverTime;
	}

	public String getCreStart() {
		return creStart;
	}

	public void setCreStart(String creStart) {
		this.creStart = creStart;
	}

	public String getCreEnd() {
		return creEnd;
	}

	public void setCreEnd(String creEnd) {
		this.creEnd = creEnd;
	}

	public java.math.BigDecimal getRefundTotal() {
		return refundTotal;
	}

	public void setRefundTotal(java.math.BigDecimal refundTotal) {
		this.refundTotal = refundTotal;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public Long getBankSupportId() {
		return bankSupportId;
	}

	public void setBankSupportId(Long bankSupportId) {
		this.bankSupportId = bankSupportId;
	}

	public String getBankCityCode() {
		return bankCityCode;
	}

	public void setBankCityCode(String bankCityCode) {
		this.bankCityCode = bankCityCode;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBankMainName() {
		return bankMainName;
	}

	public void setBankMainName(String bankMainName) {
		this.bankMainName = bankMainName;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public Long getAccountPayId() {
		return accountPayId;
	}

	public void setAccountPayId(Long accountPayId) {
		this.accountPayId = accountPayId;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public String getAccountRemark() {
		return accountRemark;
	}

	public void setAccountRemark(String accountRemark) {
		this.accountRemark = accountRemark;
	}

	public Long getApproveBy() {
		return approveBy;
	}

	public void setApproveBy(Long approveBy) {
		this.approveBy = approveBy;
	}

	public Long getApproveDept() {
		return approveDept;
	}

	public void setApproveDept(Long approveDept) {
		this.approveDept = approveDept;
	}

	public java.math.BigDecimal getRefundMembershipFee() {
		return refundMembershipFee;
	}

	public void setRefundMembershipFee(java.math.BigDecimal refundMembershipFee) {
		this.refundMembershipFee = refundMembershipFee;
	}

	public java.math.BigDecimal getRefundSalaryFee() {
		return refundSalaryFee;
	}

	public void setRefundSalaryFee(java.math.BigDecimal refundSalaryFee) {
		this.refundSalaryFee = refundSalaryFee;
	}

	public String getApproveByText() {
		return approveByText;
	}

	public void setApproveByText(String approveByText) {
		this.approveByText = approveByText;
	}

	public String getApproveDeptText() {
		return approveDeptText;
	}

	public void setApproveDeptText(String approveDeptText) {
		this.approveDeptText = approveDeptText;
	}

	public String getCreateByText() {
		return createByText;
	}

	public void setCreateByText(String createByText) {
		this.createByText = createByText;
	}

	public String getCreateDeptText() {
		return createDeptText;
	}

	public void setCreateDeptText(String createDeptText) {
		this.createDeptText = createDeptText;
	}

	public Long getCreateDept() {
		return createDept;
	}

	public void setCreateDept(Long createDept) {
		this.createDept = createDept;
	}

	public Integer getMakesureFlag() {
		return makesureFlag;
	}

	public void setMakesureFlag(Integer makesureFlag) {
		this.makesureFlag = makesureFlag;
	}

	public Integer getHandle() {
		return handle;
	}

	public void setHandle(Integer handle) {
		this.handle = handle;
	}

	public Integer getIsAT() {
		return isAT;
	}

	public void setIsAT(Integer isAT) {
		this.isAT = isAT;
	}

	public Long getVphAccountId() {
		return vphAccountId;
	}

	public void setVphAccountId(Long vphAccountId) {
		this.vphAccountId = vphAccountId;
	}

	public java.math.BigDecimal getVphFee() {
		return vphFee;
	}

	public void setVphFee(java.math.BigDecimal vphFee) {
		this.vphFee = vphFee;
	}

	public String getVphCancleStatus() {
		return vphCancleStatus;
	}

	public void setVphCancleStatus(String vphCancleStatus) {
		this.vphCancleStatus = vphCancleStatus;
	}

	public String getVphBackStatus() {
		return vphBackStatus;
	}

	public void setVphBackStatus(String vphBackStatus) {
		this.vphBackStatus = vphBackStatus;
	}

	public String getVphId() {
		return vphId;
	}

	public void setVphId(String vphId) {
		this.vphId = vphId;
	}

	public Long getMoveToOrderId() {
		return moveToOrderId;
	}

	public void setMoveToOrderId(Long moveToOrderId) {
		this.moveToOrderId = moveToOrderId;
	}

	public String getMoveOrderBusFlag() {
		return moveOrderBusFlag;
	}

	public void setMoveOrderBusFlag(String moveOrderBusFlag) {
		this.moveOrderBusFlag = moveOrderBusFlag;
	}

	public Integer getIsStop() {
		return isStop;
	}

	public void setIsStop(Integer isStop) {
		this.isStop = isStop;
	}

	public Integer getRefundObject() {
		return refundObject;
	}

	public void setRefundObject(Integer refundObject) {
		this.refundObject = refundObject;
	}

	public Integer getReasonFlag() {
		return reasonFlag;
	}

	public void setReasonFlag(Integer reasonFlag) {
		this.reasonFlag = reasonFlag;
	}

	public String getChangeCode() {
		return changeCode;
	}

	public void setChangeCode(String changeCode) {
		this.changeCode = changeCode;
	}

	public BigDecimal getPayfeeMoneyC() {
		return payfeeMoneyC;
	}

	public void setPayfeeMoneyC(BigDecimal payfeeMoneyC) {
		this.payfeeMoneyC = payfeeMoneyC;
	}

	public Long getFeeAcountId() {
		return feeAcountId;
	}

	public void setFeeAcountId(Long feeAcountId) {
		this.feeAcountId = feeAcountId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Long getSolutionId() {
		return solutionId;
	}

	public void setSolutionId(Long solutionId) {
		this.solutionId = solutionId;
	}

	public BigDecimal getBaiTiaoAmount() {
		return baiTiaoAmount;
	}

	public void setBaiTiaoAmount(BigDecimal baiTiaoAmount) {
		this.baiTiaoAmount = baiTiaoAmount;
	}

	public Long getFeePost() {
		return feePost;
	}

	public void setFeePost(Long feePost) {
		this.feePost = feePost;
	}

	public Long getPayFeeId() {
		return payFeeId;
	}

	public void setPayFeeId(Long payFeeId) {
		this.payFeeId = payFeeId;
	}

	public Long getSourceFeePost() {
		return sourceFeePost;
	}

	public void setSourceFeePost(Long sourceFeePost) {
		this.sourceFeePost = sourceFeePost;
	}

	public BigDecimal getPayFeeAmount() {
		return payFeeAmount;
	}

	public void setPayFeeAmount(BigDecimal payFeeAmount) {
		this.payFeeAmount = payFeeAmount;
	}

	public BigDecimal getPayFeeDetail() {
		return payFeeDetail;
	}

	public void setPayFeeDetail(BigDecimal payFeeDetail) {
		this.payFeeDetail = payFeeDetail;
	}

	public BigDecimal getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(BigDecimal returnAmount) {
		this.returnAmount = returnAmount;
	}

	public BigDecimal getServiceChargeAmount() {
		return serviceChargeAmount;
	}

	public void setServiceChargeAmount(BigDecimal serviceChargeAmount) {
		this.serviceChargeAmount = serviceChargeAmount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRecommendName() {
		return recommendName;
	}

	public void setRecommendName(String recommendName) {
		this.recommendName = recommendName;
	}

	public String getRecommendInst() {
		return recommendInst;
	}

	public void setRecommendInst(String recommendInst) {
		this.recommendInst = recommendInst;
	}

	public Long getCardStatus() {
		return cardStatus;
	}

	public void setCardStatus(Long cardStatus) {
		this.cardStatus = cardStatus;
	}


	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public BigDecimal getCardbalance() {
		return cardbalance;
	}

	public void setCardbalance(BigDecimal cardbalance) {
		this.cardbalance = cardbalance;
	}

	public Long getIsGive() {
		return isGive;
	}

	public void setIsGive(Long isGive) {
		this.isGive = isGive;
	}

	public Integer getIsGJTK() {
		return isGJTK;
	}

	public void setIsGJTK(Integer isGJTK) {
		this.isGJTK = isGJTK;
	}

	public Integer getIsPushMoney() {
		return isPushMoney;
	}

	public void setIsPushMoney(Integer isPushMoney) {
		this.isPushMoney = isPushMoney;
	}

	public String getPersonnelName() {
		return personnelName;
	}

	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}

	public BigDecimal getGjMoney() {
		return gjMoney;
	}

	public void setGjMoney(BigDecimal gjMoney) {
		this.gjMoney = gjMoney;
	}

	public BigDecimal getGjBalance() {
		return gjBalance;
	}

	public void setGjBalance(BigDecimal gjBalance) {
		this.gjBalance = gjBalance;
	}

	public Long getCardSellRecordId() {
		return cardSellRecordId;
	}

	public void setCardSellRecordId(Long cardSellRecordId) {
		this.cardSellRecordId = cardSellRecordId;
	}

	public Long getPayMentId() {
		return payMentId;
	}

	public void setPayMentId(Long payMentId) {
		this.payMentId = payMentId;
	}
}
