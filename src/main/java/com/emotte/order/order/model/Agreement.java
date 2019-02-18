package com.emotte.order.order.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : T_ORDER_AGREEMENT
 */
public class Agreement extends BaseModel {

 	private String downloadFlag;

	private Long id;

	/**
	  合同状态：1未确认，2已确认，3已终止，4已完成  5已删除，6签约中
	*/
	private Integer agreementState;

	/**
	  丙方证件类型 1 身份证 2 护照  3驾照
	*/
	private Integer cardTypeC;

	/**
	  员工服务人员服务费
	*/
	private java.math.BigDecimal employMoney;

	/**
	  丙方证件号码
	*/
	private String cardNumC;

	/**
	  丙方联系电话(手机)
	*/
	private String mobileC;

	/**
	  是否代收 1是，2否
	*/
	private Integer isCollection;

	/**
	  发服务人员服务费日期
	*/
	private String salaryDate;

	/**
	  支付方式：1转账 2现金
	*/
	private Integer payType;

	/**
	  服务内容
	*/
	private Integer serviceCode;

	/**
	  日服务人员服务费
	*/
	private java.math.BigDecimal onedayWages;

	/**
	  首月服务佣金
	*/
	private java.math.BigDecimal firstServiceMoney;

	/**
	  首月管理费
	*/
	private java.math.BigDecimal firstManageMoney;

	/**
	  服务人员管理费(元/月)
	*/
	private java.math.BigDecimal personManageMoney;

	/**
	  服务地址
	*/
	private String serviceAddress;

	/**
	  客户管理费(一次)
	*/
	private java.math.BigDecimal customerManageMoney;

	/**
	  证件类型 1 身份证 2 护照  3驾照
	*/
	private Integer cardType;

	/**
	  证件号码
	*/
	private String cardNum;

	/**
	  联系电话(手机)
	*/
	private String mobile;

	/**
	  丙方(服务员)
	*/
	private String partyC;

	/**
	  客户地址(甲方)
	*/
	private String customerAddress;

	/**
	  平台地址(乙方)
	*/
	private String platformAddress;

	/**
	  服务站
	*/
	private String serviceGarage;

	/**
	  服务人员劳务费(元/月)
	*/
	private java.math.BigDecimal serviceMoney;

	/**
	  是日劳务费的倍数
	*/
	private java.math.BigDecimal chargeTimes;

	/**
	  本次支付金额
	*/
	private java.math.BigDecimal payment;

	/**
	  预付方式：1月，2季，3半年，4年
	*/
	private Integer advancePeriod;

	/**
	  其他支付方式
	*/
	private String otherMethods;

	/**
	  其他约定
	*/
	private String otherMatters;

	/**
	  关联的服务人员ID
	*/
	private Long personId;

	/**
	  是否有效 1有效，2无效
	*/
	private Integer valid;

	/**
	  关联的订单ID
	*/
	private Long orderId;

	/**
	  合同标题
	*/
	private String agreementTitle;

	/**
	  甲方(消费者)
	*/
	private String partyA;

	/**
	  乙方(平台)
	*/
	private String partyB;

	/**
	  合同生效日期
	*/
	private String effectDate;

	/**
	  合同结束日期
	*/
	private String finishDate;

	/**
	  合同类型：1三方合同  2两方合同
	*/
	private Integer agreementType;

	/**
	  提前支付天数
	*/
	private Integer remindDay;

	/**
	  支付日期
	*/
	private String agreementPayDate;

	/**
	  合同编号
	*/
	private String agreementCode;

	/**
	 * 服务员地址(丙方)
	 */
	private String waiterAddress;
	//订单状态
	private String orderStatus;
	//订单支付状态
	private String payStatus;
	//钟点工工作时间备注
	private String remarkZdg;
	//有效的合同状态
	private String agreementStateEffect;
	//合同ids
	private String agreementIds;
	//紧急联系人姓名
	private String linkManName;
	//紧急联系人电话
	private String linkManMobile;
	//支付时间说明
	private String zhifuRemark;
	//订单编号
	private String orderCode;
	//客户姓名
	private String userName;
	//客户电话
	private String userMobile;
	//订单负责人
	private Long rechargeBy;
	//订单负责部门
	private Long rechargeDept;
	//合同审核人
	private Long checkBy;
	//合同审核日期
	private String checkDate;
	//合同审核状态
	private Integer checkStatus;
	//合同审核说明（驳回原因）
	private String checkInstructions;
	//服务站id(部门id)
	private Long serviceGarageId;
	//合同确认日期
	private String confirmationDate;
	//版本号
	private Long version;
	//集合
	private List<Agreement> list;
	//合同审核状态文字
	private String checkStatusText;
	//服务地址json（仅用于合同修改回显使用）
	private String serviceAddressEcho;
	//甲方地址json（仅用于合同修改回显使用）
	private String customerAddressEcho;
	//理由（删除、终止合同时使用）
	private String reason;
	//下载首页标记
	private Integer homePage;
	//合同审核IDS
	private String[] ids_;
	//合同状态：1未确认，2已确认，3已终止，4已完成
	private Integer agreementStateTwo;
	//订单商品/服务类型
	private String orderType;
	//签订日期-起始
	private String creStart;
	//签订日期-结束
	private String creEnd;
	//确认id
	private Long sureId;
	//其他方式
	private String otherWay;
	//费用总计
	private String allPay;
	//费用总计大写
	private String allPayCN;
	private String agreementModel;
	/**
	 * 电子签章客户状态  1,客户待签约  2,客户已签约   3,客户已驳回
	 */
	private Integer elecClientState;
	/**
	 * 电子签章服务人员状态  1,服务人员待签约   2,服务人员已签约  3,服务人员已驳回
	 */
	private Integer elecServeState;
	/**
	 * 电子签章其他状态    1,合同待签约   2,已推送  3,三方已签约  4, 已电子认证   5,签章已驳回
	 */
	private Integer elecOtherState;
	/**
	 * 1预览0下载
	 */
	private String previewFlag;
	private String agreementModelName;
	private String previewContactFile;
	private String normalContactFile;
	private String hashApplyNo;
	private boolean editContract=false;
	//T_ORDER_AGREEMENT表创建时间
	private String createtimeinfo;
	private String createtime;
	private String updateflag;
	private String log;
	private String deptId;
	private Long createBy;
	private String  contractDate;//合同签约日期
	private String checkedOrderStatus;//辅助属性订单状态2
	/** 法定代表人 **/
	private String legalRepresentative;
	/** 甲方预付几个月劳务费（海金保理白条） **/
	private Long prepaidMonths;
	/** 甲方预付几个月劳务费的总金额（海金保理白条） **/
	private Double prepaidMoney;
	/** 甲方分期支付几个月劳务费（海金保理白条） **/
	private Long instPrepaidMonths;
	/** 甲方分期支付几个月劳务费的分期总金额（海金保理白条） **/
	private Double instPrepaidMoney;
	/** 甲方分期手续费几日内支付（海金保理白条） **/
	private Long limitDays;
	/** 甲方预留信息:账户姓名 **/
	private String accountName;
	/** 甲方预留信息:账户开户行 **/
	private String accountBank;
	/** 甲方预留信息:账户号码 **/
	private String accountNum;
	/** 甲方预留信息:预留手机号 **/
	private String accountMobile;
	/** 陪护服务合同辅助查询 **/
	private String customerserviceAddress; //甲方服务地址
	private String hospitalizationNum;     //住院号
	private String departments;           //科室
	private String roomNumber;            //病房号
	private String bedNumber_a;             //床号
	private String bedNumber_b;
	private String consumerstate;         //甲方服务对象现状
	private String consumersName;         //服务对象姓名
	private String consumersCard;         //服务对象身份证
	private Integer custconsumerRelation;  //甲方与客户关系
	private String birthPeriod;           //产妇预产期
	private Integer specialConsiderations;  //特殊注意事项
	private Integer serviceItems;         //甲方所选服务项目
	private String serviceStarttime;      //服务开始时间
	private String hostsitDay;            //临床陪护暂定天数
	private Integer serviceFormat;        //服务形式
	private Integer deliveryMode;         //分娩方式
	private String replaceNumber;         //更换次数
	private String serviceprojectStandard;  //甲方选择的服务项目收费标准
	private String partyBaccountName;
	private String partyBaccountNum;
	private String partyBaccountBank;
	private String relation_relatives;       //具体的亲属关系
	private String relation_entrust;         //具体的委托关系
	/** 陪护服务合同辅助查询结束 **/
	private String agreementBusinessType;   //合同业务类型
	/**
	 乙方联系电话(手机)
	 */
	private String mobileB;
	
	
	private String insuranceEntrust; // 是否同意保险委托

	/***钉钉审批单号 add 20190111*/
	private String dingTalkAuditCode;
	/***绩效合同：适用城市*/
	private String[] peContractCity;
	/***绩效合同：使用开始日期*/
	private String peContractStartDate;
	/***绩效合同：使用结束日期*/
	private String peContractEndDate;
	/***绩效合同：基本工资占比,小数0.90*/
	private BigDecimal peContractBasic;
	/***绩效合同：绩效工资占比,小数0.10*/
	private BigDecimal peContractPerformance;
	/***绩效合同：是否为绩效合同 1:是,2或空:否*/
	private Integer isPeContract;
	/***绩效合同：当前订单城市*/
	private String peContractOrderCity;
	
	

	/*扩展字段*/
	/**
	 * 是否上传了合同附件 add 20190111
	 */
	private String havePDF;

	@Override
	public Long getCreateBy() {
		return createBy;
	}

	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	public void setVersion(Long version) {
		this.version = version;
	}

    public String getDownloadFlag() {
        return downloadFlag;
    }

    public void setDownloadFlag(String downloadFlag) {
        this.downloadFlag = downloadFlag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAgreementState() {
        return agreementState;
    }

    public void setAgreementState(Integer agreementState) {
        this.agreementState = agreementState;
    }

    public Integer getCardTypeC() {
        return cardTypeC;
    }

    public void setCardTypeC(Integer cardTypeC) {
        this.cardTypeC = cardTypeC;
    }

    public BigDecimal getEmployMoney() {
        return employMoney;
    }

    public void setEmployMoney(BigDecimal employMoney) {
        this.employMoney = employMoney;
    }

    public String getCardNumC() {
        return cardNumC;
    }

    public void setCardNumC(String cardNumC) {
        this.cardNumC = cardNumC;
    }

    public String getMobileC() {
        return mobileC;
    }

    public void setMobileC(String mobileC) {
        this.mobileC = mobileC;
    }

    public Integer getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(Integer isCollection) {
        this.isCollection = isCollection;
    }

    public String getSalaryDate() {
        return salaryDate;
    }

    public void setSalaryDate(String salaryDate) {
        this.salaryDate = salaryDate;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Integer getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(Integer serviceCode) {
        this.serviceCode = serviceCode;
    }

    public BigDecimal getOnedayWages() {
        return onedayWages;
    }

    public void setOnedayWages(BigDecimal onedayWages) {
        this.onedayWages = onedayWages;
    }

    public BigDecimal getFirstServiceMoney() {
        return firstServiceMoney;
    }

    public void setFirstServiceMoney(BigDecimal firstServiceMoney) {
        this.firstServiceMoney = firstServiceMoney;
    }

    public BigDecimal getFirstManageMoney() {
        return firstManageMoney;
    }

    public void setFirstManageMoney(BigDecimal firstManageMoney) {
        this.firstManageMoney = firstManageMoney;
    }

    public BigDecimal getPersonManageMoney() {
        return personManageMoney;
    }

    public void setPersonManageMoney(BigDecimal personManageMoney) {
        this.personManageMoney = personManageMoney;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public BigDecimal getCustomerManageMoney() {
        return customerManageMoney;
    }

    public void setCustomerManageMoney(BigDecimal customerManageMoney) {
        this.customerManageMoney = customerManageMoney;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPartyC() {
        return partyC;
    }

    public void setPartyC(String partyC) {
        this.partyC = partyC;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getPlatformAddress() {
        return platformAddress;
    }

    public void setPlatformAddress(String platformAddress) {
        this.platformAddress = platformAddress;
    }

    public String getServiceGarage() {
        return serviceGarage;
    }

    public void setServiceGarage(String serviceGarage) {
        this.serviceGarage = serviceGarage;
    }

    public BigDecimal getServiceMoney() {
        return serviceMoney;
    }

    public void setServiceMoney(BigDecimal serviceMoney) {
        this.serviceMoney = serviceMoney;
    }

    public BigDecimal getChargeTimes() {
        return chargeTimes;
    }

    public void setChargeTimes(BigDecimal chargeTimes) {
        this.chargeTimes = chargeTimes;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public Integer getAdvancePeriod() {
        return advancePeriod;
    }

    public void setAdvancePeriod(Integer advancePeriod) {
        this.advancePeriod = advancePeriod;
    }

    public String getOtherMethods() {
        return otherMethods;
    }

    public void setOtherMethods(String otherMethods) {
        this.otherMethods = otherMethods;
    }

    public String getOtherMatters() {
        return otherMatters;
    }

    public void setOtherMatters(String otherMatters) {
        this.otherMatters = otherMatters;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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

    public String getAgreementTitle() {
        return agreementTitle;
    }

    public void setAgreementTitle(String agreementTitle) {
        this.agreementTitle = agreementTitle;
    }

    public String getPartyA() {
        return partyA;
    }

    public void setPartyA(String partyA) {
        this.partyA = partyA;
    }

    public String getPartyB() {
        return partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB;
    }

    public String getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(String effectDate) {
        this.effectDate = effectDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public Integer getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(Integer agreementType) {
        this.agreementType = agreementType;
    }

    public Integer getRemindDay() {
        return remindDay;
    }

    public void setRemindDay(Integer remindDay) {
        this.remindDay = remindDay;
    }

    public String getAgreementPayDate() {
        return agreementPayDate;
    }

    public void setAgreementPayDate(String agreementPayDate) {
        this.agreementPayDate = agreementPayDate;
    }

    public String getAgreementCode() {
        return agreementCode;
    }

    public void setAgreementCode(String agreementCode) {
        this.agreementCode = agreementCode;
    }

    public String getWaiterAddress() {
        return waiterAddress;
    }

    public void setWaiterAddress(String waiterAddress) {
        this.waiterAddress = waiterAddress;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getRemarkZdg() {
        return remarkZdg;
    }

    public void setRemarkZdg(String remarkZdg) {
        this.remarkZdg = remarkZdg;
    }

    public String getAgreementStateEffect() {
        return agreementStateEffect;
    }

    public void setAgreementStateEffect(String agreementStateEffect) {
        this.agreementStateEffect = agreementStateEffect;
    }

    public String getAgreementIds() {
        return agreementIds;
    }

    public void setAgreementIds(String agreementIds) {
        this.agreementIds = agreementIds;
    }

    public String getLinkManName() {
        return linkManName;
    }

    public void setLinkManName(String linkManName) {
        this.linkManName = linkManName;
    }

    public String getLinkManMobile() {
        return linkManMobile;
    }

    public void setLinkManMobile(String linkManMobile) {
        this.linkManMobile = linkManMobile;
    }

    public String getZhifuRemark() {
        return zhifuRemark;
    }

    public void setZhifuRemark(String zhifuRemark) {
        this.zhifuRemark = zhifuRemark;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
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

    public Long getCheckBy() {
        return checkBy;
    }

    public void setCheckBy(Long checkBy) {
        this.checkBy = checkBy;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckInstructions() {
        return checkInstructions;
    }

    public void setCheckInstructions(String checkInstructions) {
        this.checkInstructions = checkInstructions;
    }

    public Long getServiceGarageId() {
        return serviceGarageId;
    }

    public void setServiceGarageId(Long serviceGarageId) {
        this.serviceGarageId = serviceGarageId;
    }

    public String getConfirmationDate() {
        return confirmationDate;
    }

    public void setConfirmationDate(String confirmationDate) {
        this.confirmationDate = confirmationDate;
    }

    public List<Agreement> getList() {
        return list;
    }

    public void setList(List<Agreement> list) {
        this.list = list;
    }

    public String getCheckStatusText() {
        return checkStatusText;
    }

    public void setCheckStatusText(String checkStatusText) {
        this.checkStatusText = checkStatusText;
    }

    public String getServiceAddressEcho() {
        return serviceAddressEcho;
    }

    public void setServiceAddressEcho(String serviceAddressEcho) {
        this.serviceAddressEcho = serviceAddressEcho;
    }

    public String getCustomerAddressEcho() {
        return customerAddressEcho;
    }

    public void setCustomerAddressEcho(String customerAddressEcho) {
        this.customerAddressEcho = customerAddressEcho;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getHomePage() {
        return homePage;
    }

    public void setHomePage(Integer homePage) {
        this.homePage = homePage;
    }

    public String[] getIds_() {
        return ids_;
    }

    public void setIds_(String[] ids_) {
        this.ids_ = ids_;
    }

    public Integer getAgreementStateTwo() {
        return agreementStateTwo;
    }

    public void setAgreementStateTwo(Integer agreementStateTwo) {
        this.agreementStateTwo = agreementStateTwo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
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

    public Long getSureId() {
        return sureId;
    }

    public void setSureId(Long sureId) {
        this.sureId = sureId;
    }

    public String getOtherWay() {
        return otherWay;
    }

    public void setOtherWay(String otherWay) {
        this.otherWay = otherWay;
    }

    public String getAllPay() {
        return allPay;
    }

    public void setAllPay(String allPay) {
        this.allPay = allPay;
    }

    public String getAllPayCN() {
        return allPayCN;
    }

    public void setAllPayCN(String allPayCN) {
        this.allPayCN = allPayCN;
    }

    public String getAgreementModel() {
        return agreementModel;
    }

    public void setAgreementModel(String agreementModel) {
        this.agreementModel = agreementModel;
    }

    public Integer getElecClientState() {
        return elecClientState;
    }

    public void setElecClientState(Integer elecClientState) {
        this.elecClientState = elecClientState;
    }

    public Integer getElecServeState() {
        return elecServeState;
    }

    public void setElecServeState(Integer elecServeState) {
        this.elecServeState = elecServeState;
    }

    public Integer getElecOtherState() {
        return elecOtherState;
    }

    public void setElecOtherState(Integer elecOtherState) {
        this.elecOtherState = elecOtherState;
    }

    public String getPreviewFlag() {
        return previewFlag;
    }

    public void setPreviewFlag(String previewFlag) {
        this.previewFlag = previewFlag;
    }

    public String getAgreementModelName() {
        return agreementModelName;
    }

    public void setAgreementModelName(String agreementModelName) {
        this.agreementModelName = agreementModelName;
    }

    public String getPreviewContactFile() {
        return previewContactFile;
    }

    public void setPreviewContactFile(String previewContactFile) {
        this.previewContactFile = previewContactFile;
    }

    public String getNormalContactFile() {
        return normalContactFile;
    }

    public void setNormalContactFile(String normalContactFile) {
        this.normalContactFile = normalContactFile;
    }

    public String getHashApplyNo() {
        return hashApplyNo;
    }

    public void setHashApplyNo(String hashApplyNo) {
        this.hashApplyNo = hashApplyNo;
    }

    public boolean isEditContract() {
        return editContract;
    }

    public void setEditContract(boolean editContract) {
        this.editContract = editContract;
    }

    public String getCreatetimeinfo() {
        return createtimeinfo;
    }

    public void setCreatetimeinfo(String createtimeinfo) {
        this.createtimeinfo = createtimeinfo;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdateflag() {
        return updateflag;
    }

    public void setUpdateflag(String updateflag) {
        this.updateflag = updateflag;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getCheckedOrderStatus() {
        return checkedOrderStatus;
    }

    public void setCheckedOrderStatus(String checkedOrderStatus) {
        this.checkedOrderStatus = checkedOrderStatus;
    }

    public String getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(String legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public Long getPrepaidMonths() {
        return prepaidMonths;
    }

    public void setPrepaidMonths(Long prepaidMonths) {
        this.prepaidMonths = prepaidMonths;
    }

    public Double getPrepaidMoney() {
        return prepaidMoney;
    }

    public void setPrepaidMoney(Double prepaidMoney) {
        this.prepaidMoney = prepaidMoney;
    }

    public Long getInstPrepaidMonths() {
        return instPrepaidMonths;
    }

    public void setInstPrepaidMonths(Long instPrepaidMonths) {
        this.instPrepaidMonths = instPrepaidMonths;
    }

    public Double getInstPrepaidMoney() {
        return instPrepaidMoney;
    }

    public void setInstPrepaidMoney(Double instPrepaidMoney) {
        this.instPrepaidMoney = instPrepaidMoney;
    }

    public Long getLimitDays() {
        return limitDays;
    }

    public void setLimitDays(Long limitDays) {
        this.limitDays = limitDays;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(String accountBank) {
        this.accountBank = accountBank;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountMobile() {
        return accountMobile;
    }

    public void setAccountMobile(String accountMobile) {
        this.accountMobile = accountMobile;
    }

    public String getCustomerserviceAddress() {
        return customerserviceAddress;
    }

    public void setCustomerserviceAddress(String customerserviceAddress) {
        this.customerserviceAddress = customerserviceAddress;
    }

    public String getHospitalizationNum() {
        return hospitalizationNum;
    }

    public void setHospitalizationNum(String hospitalizationNum) {
        this.hospitalizationNum = hospitalizationNum;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBedNumber_a() {
        return bedNumber_a;
    }

    public void setBedNumber_a(String bedNumber_a) {
        this.bedNumber_a = bedNumber_a;
    }

    public String getBedNumber_b() {
        return bedNumber_b;
    }

    public void setBedNumber_b(String bedNumber_b) {
        this.bedNumber_b = bedNumber_b;
    }

    public String getConsumerstate() {
        return consumerstate;
    }

    public void setConsumerstate(String consumerstate) {
        this.consumerstate = consumerstate;
    }

    public String getConsumersName() {
        return consumersName;
    }

    public void setConsumersName(String consumersName) {
        this.consumersName = consumersName;
    }

    public String getConsumersCard() {
        return consumersCard;
    }

    public void setConsumersCard(String consumersCard) {
        this.consumersCard = consumersCard;
    }

    public Integer getCustconsumerRelation() {
        return custconsumerRelation;
    }

    public void setCustconsumerRelation(Integer custconsumerRelation) {
        this.custconsumerRelation = custconsumerRelation;
    }

    public String getBirthPeriod() {
        return birthPeriod;
    }

    public void setBirthPeriod(String birthPeriod) {
        this.birthPeriod = birthPeriod;
    }

    public Integer getSpecialConsiderations() {
        return specialConsiderations;
    }

    public void setSpecialConsiderations(Integer specialConsiderations) {
        this.specialConsiderations = specialConsiderations;
    }

    public Integer getServiceItems() {
        return serviceItems;
    }

    public void setServiceItems(Integer serviceItems) {
        this.serviceItems = serviceItems;
    }

    public String getServiceStarttime() {
        return serviceStarttime;
    }

    public void setServiceStarttime(String serviceStarttime) {
        this.serviceStarttime = serviceStarttime;
    }

    public String getHostsitDay() {
        return hostsitDay;
    }

    public void setHostsitDay(String hostsitDay) {
        this.hostsitDay = hostsitDay;
    }

    public Integer getServiceFormat() {
        return serviceFormat;
    }

    public void setServiceFormat(Integer serviceFormat) {
        this.serviceFormat = serviceFormat;
    }

    public Integer getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(Integer deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public String getReplaceNumber() {
        return replaceNumber;
    }

    public void setReplaceNumber(String replaceNumber) {
        this.replaceNumber = replaceNumber;
    }

    public String getServiceprojectStandard() {
        return serviceprojectStandard;
    }

    public void setServiceprojectStandard(String serviceprojectStandard) {
        this.serviceprojectStandard = serviceprojectStandard;
    }

    public String getPartyBaccountName() {
        return partyBaccountName;
    }

    public void setPartyBaccountName(String partyBaccountName) {
        this.partyBaccountName = partyBaccountName;
    }

    public String getPartyBaccountNum() {
        return partyBaccountNum;
    }

    public void setPartyBaccountNum(String partyBaccountNum) {
        this.partyBaccountNum = partyBaccountNum;
    }

    public String getPartyBaccountBank() {
        return partyBaccountBank;
    }

    public void setPartyBaccountBank(String partyBaccountBank) {
        this.partyBaccountBank = partyBaccountBank;
    }

    public String getRelation_relatives() {
        return relation_relatives;
    }

    public void setRelation_relatives(String relation_relatives) {
        this.relation_relatives = relation_relatives;
    }

    public String getRelation_entrust() {
        return relation_entrust;
    }

    public void setRelation_entrust(String relation_entrust) {
        this.relation_entrust = relation_entrust;
    }

    public String getAgreementBusinessType() {
        return agreementBusinessType;
    }

    public void setAgreementBusinessType(String agreementBusinessType) {
        this.agreementBusinessType = agreementBusinessType;
    }

    public String getMobileB() {
        return mobileB;
    }

    public void setMobileB(String mobileB) {
        this.mobileB = mobileB;
    }

	public String getInsuranceEntrust() {
		return insuranceEntrust;
	}

	public void setInsuranceEntrust(String insuranceEntrust) {
		this.insuranceEntrust = insuranceEntrust;
	}

	public String getDingTalkAuditCode() {
		return dingTalkAuditCode;
	}

	public void setDingTalkAuditCode(String dingTalkAuditCode) {
		this.dingTalkAuditCode = dingTalkAuditCode;
	}

	public String getHavePDF() {
		return havePDF;
	}

	public void setHavePDF(String havePDF) {
		this.havePDF = havePDF;
	}

	public String[] getPeContractCity() {
		return peContractCity;
	}

	public void setPeContractCity(String[] peContractCity) {
		this.peContractCity = peContractCity;
	}

	public String getPeContractStartDate() {
		return peContractStartDate;
	}

	public void setPeContractStartDate(String peContractStartDate) {
		this.peContractStartDate = peContractStartDate;
	}

	public String getPeContractEndDate() {
		return peContractEndDate;
	}

	public void setPeContractEndDate(String peContractEndDate) {
		this.peContractEndDate = peContractEndDate;
	}

	public BigDecimal getPeContractBasic() {
		return peContractBasic;
	}

	public void setPeContractBasic(BigDecimal peContractBasic) {
		this.peContractBasic = peContractBasic;
	}

	public BigDecimal getPeContractPerformance() {
		return peContractPerformance;
	}

	public void setPeContractPerformance(BigDecimal peContractPerformance) {
		this.peContractPerformance = peContractPerformance;
	}

	public Integer getIsPeContract() {
		return isPeContract;
	}

	public void setIsPeContract(Integer isPeContract) {
		this.isPeContract = isPeContract;
	}

	public String getPeContractOrderCity() {
		return peContractOrderCity;
	}

	public void setPeContractOrderCity(String peContractOrderCity) {
		this.peContractOrderCity = peContractOrderCity;
	}

	
}