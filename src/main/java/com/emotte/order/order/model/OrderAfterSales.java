package com.emotte.order.order.model;
import org.wildhorse.server.core.model.BaseModel;

import java.math.BigDecimal;
public class OrderAfterSales extends BaseModel{ 
	/**
	  售后的主键
	*/
	private Long id;

	/**
	  关联的订单ID，解决方案ID等
	*/
	private Long orderId;
	private Long loginBy;// 当前登录人
	private Long loginDept;// 当前登录人部门
	private Integer loginLevel;//登录人权限级别
	private String loginAllCode;// 
	private String loginOrgCode;//当前登录人部门code
	private int loginByOrNot; //是否当前登录人操作,用于对还未分包的订单进行操作判断
	private int loginRechargeOrNot; //是否当前录入人操作，用于对已经分包的订单进行操作判断
	private int loginDeptOrnot; //是否当前登录人部门操作
	private Long solutionType;//解决方案类型
	/**
	  关联的订单类型：1解决方案，2 订单
	*/
	private String orderType;

	/**
	  售后单类型：1退款
	*/
	private String afterSalesKind;

	/**
	  客户电话
	*/
	private String custMobile;

	/**
	  客户姓名
	*/
	private String custName;

	/**
	  审核状态：1待审核，2，已通过，3，未通过
	*/
	private String auditFlag;
	//原因
	private String reason;
	//售后图片地址
	private String afterSalesImgs;
	//下户时间
	private String serverTime;
	//创建时间-起始
	private String creStart;
	//创建时间-结束
	private String creEnd;
	//退款总额
	private java.math.BigDecimal refundTotal;
	//银行卡卡号
	private String bankCard;
	//开户行名称
	private String bankName;
	//开户人姓名
	private String bankUserName;
	//银行类型
	private Long bankSupportId;
	//开户城市
	private String bankCityCode;
	//订单
	private Order order;
	//备注
	private String remark;
	//主行名称
	private String bankMainName;
	//订单编号
	private String orderCode;
	//退款结算单id
	private Long accountPayId;
	// 是否有效 1 有效 ， 2 无效 : valid
	private Integer valid;
	//结算单审核失败原因
	private String accountRemark;
	//审批人id
	private Long approveBy;
	//审批人部门
	private Long approveDept;
	//应退信息费
	private java.math.BigDecimal refundMembershipFee;
	//应退服务人员服务费
	private java.math.BigDecimal refundSalaryFee;
	//审批人名称
	private String approveByText;
	//审批人部门名称
	private String approveDeptText;
	//录入人名称
	private String createByText;
	//录入人部门名称
	private String createDeptText;
	//录入人部门id
	private Long createDept;
	//确认有效、无效标记  1有效，2无效
	private Integer makesureFlag;
	//更新售后单标记
	private Integer handle;
	//是否手动 1是 2否
	private Integer isAT;
	//管家帮分期结算单ID（唯品会结算单ID）
	private Long vphAccountId;
	//管家帮分期金额
	private java.math.BigDecimal vphFee;
	//管家帮分期取消状态（20310001 取消中，20310002取消成功，20310003取消失败）
	private String vphCancleStatus;
	//管家帮分期退费状态
	private String vphBackStatus;
	//管家帮分期ID
	private String vphId;
	//转单订单ID
	private Long moveToOrderId;
	//转单订单业务处理状态
	private String moveOrderBusFlag;
	//是否终止 1是2否
	private Integer isStop;
	//唯品会退费对象 1 唯品会 2客户 3 海金保理
	private Integer refundObject;
	//退款原因标记
	private Integer reasonFlag;
	//迁至订单编号
	private String changeCode;
	
	//未缴售后手续费
	private BigDecimal payfeeMoneyC;
	
	//售后手续费结算单id
	private Long feeAcountId;
	
	//标记1.唯品会  2.海金保理
	private Integer flag;
	
	//分摊明细类型
	private String bType;
	
	//支付方式
	private Integer feePost;


	//缴费明细id
	private Long payFeeId;

	/*20180608新增字段*/
	private BigDecimal serviceLabourFee;//服务人员服务费金额
	private Long bathno;//服务人员服务费批次
	private String realName;//客户姓名
	private String mobile;//用户手机号
	private String salaryId;//服务人员服务费单ID
	private String vphOrderId;//唯品会订单号
	private Long billBackId;//回购单id
	private String fkCode;//关联的编号
	private Long fkId;//关联的id
	private String agreementCode;//合同编号
	
	private String cardNumb;
	private BigDecimal oldRefundMembershipFee;//原应退信息费
	private BigDecimal oldRefundSalaryFee;//原应退服务人员服务费

	/**
	 * 20180726移动端售后新增商品状态字段
	 * personnelStatus
	 * 商品订单：
	 * 	1 已收货
	 * 	2 未收货
	 * 服务订单：
	 * 	1 已服务
	 * 	2 未服务
     */
	private Integer personnelStatus;

	/**
	 * 20180726移动端售后新增标记售后来源字段  1：是   2或null：否
	 */
	private Integer isApp;

	public String getCardNumb() {
		return cardNumb;
	}

	public void setCardNumb(String cardNumb) {
		this.cardNumb = cardNumb;
	}

	public Long getPayFeeId() {
		return payFeeId;
	}

	public void setPayFeeId(Long payFeeId) {
		this.payFeeId = payFeeId;
	}

	public Integer getFeePost() {
		return feePost;
	}

	public void setFeePost(Integer feePost) {
		this.feePost = feePost;
	}

	public String getbType() {
		return bType;
	}

	public void setbType(String bType) {
		this.bType = bType;
	}

	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Long getFeeAcountId() {
		return feeAcountId;
	}

	public void setFeeAcountId(Long feeAcountId) {
		this.feeAcountId = feeAcountId;
	}

	public BigDecimal getPayfeeMoneyC() {
		return payfeeMoneyC;
	}

	public void setPayfeeMoneyC(BigDecimal payfeeMoneyC) {
		this.payfeeMoneyC = payfeeMoneyC;
	}

	public Long getId(){
		return id; 
	}

	public void setId(Long id){
		this.id = id; 
	}

	public Long getOrderId(){
		return orderId; 
	}

	public void setOrderId(Long orderId){
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

	public String getOrderType(){
		return orderType; 
	}

	public void setOrderType(String orderType){
		this.orderType = orderType; 
	}

	public String getAfterSalesKind(){
		return afterSalesKind; 
	}

	public void setAfterSalesKind(String afterSalesKind){
		this.afterSalesKind = afterSalesKind; 
	}

	public String getCustMobile(){
		return custMobile; 
	}

	public void setCustMobile(String custMobile){
		this.custMobile = custMobile; 
	}

	public String getCustName(){
		return custName; 
	}

	public void setCustName(String custName){
		this.custName = custName; 
	}

	public String getAuditFlag(){
		return auditFlag; 
	}

	public void setAuditFlag(String auditFlag){
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

	public Long getBathno() {
		return bathno;
	}

	public void setBathno(Long bathno) {
		this.bathno = bathno;
	}

	public BigDecimal getServiceLabourFee() {
		return serviceLabourFee;
	}

	public void setServiceLabourFee(BigDecimal serviceLabourFee) {
		this.serviceLabourFee = serviceLabourFee;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(String salaryId) {
		this.salaryId = salaryId;
	}

	public String getVphOrderId() {
		return vphOrderId;
	}

	public void setVphOrderId(String vphOrderId) {
		this.vphOrderId = vphOrderId;
	}

	public Long getBillBackId() {
		return billBackId;
	}

	public void setBillBackId(Long billBackId) {
		this.billBackId = billBackId;
	}

	public String getFkCode() {
		return fkCode;
	}

	public void setFkCode(String fkCode) {
		this.fkCode = fkCode;
	}

	public Long getFkId() {
		return fkId;
	}

	public void setFkId(Long fkId) {
		this.fkId = fkId;
	}


	public String getAgreementCode() {
		return agreementCode;
	}

	public void setAgreementCode(String agreementCode) {
		this.agreementCode = agreementCode;
	}

	public Long getSolutionType() {
		return solutionType;
	}

	public void setSolutionType(Long solutionType) {
		this.solutionType = solutionType;
	}

	public Integer getPersonnelStatus() {
		return personnelStatus;
	}

	public void setPersonnelStatus(Integer personnelStatus) {
		this.personnelStatus = personnelStatus;
	}

	public Integer getIsApp() {
		return isApp;
	}

	public void setIsApp(Integer isApp) {
		this.isApp = isApp;
	}

	public BigDecimal getOldRefundMembershipFee() {
		return oldRefundMembershipFee;
	}

	public void setOldRefundMembershipFee(BigDecimal oldRefundMembershipFee) {
		this.oldRefundMembershipFee = oldRefundMembershipFee;
	}

	public BigDecimal getOldRefundSalaryFee() {
		return oldRefundSalaryFee;
	}

	public void setOldRefundSalaryFee(BigDecimal oldRefundSalaryFee) {
		this.oldRefundSalaryFee = oldRefundSalaryFee;
	}
	
}