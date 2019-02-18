package com.emotte.order.order.model;

import java.math.BigDecimal;
import java.util.Date;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order_payfee
 * 
 * 
 * @author army
 */
public class Payfee extends BaseModel {

	// 主键 : id
	private Long id;

	// : order_id
	private Long orderId;

	// 2是pos机，3银行转帐,4在线支付,6,卡支付,7券支付,8淘宝支付,9外部卡券 : fee_post
	private Integer feePost;//(三方时：1.微信支付 2.余额)
	private String postText;
	// 缴费类型：1新增缴费 2续费 : fee_type ,或结算单类型
	private Integer feeType;

	// 银行名称
	private String postBank;
	
	// 用户卡号 : post_num
	private String postNum;
	
	// 汇款
	private String postUser;

	// 消费卡卡号，消费卷卷号 : cards_num
	private String cardsNum;

	// 缴费或者退费类型：1缴费，2退费 : is_back_type
	private Integer isBackType;

	// 缴费开始时间 : fee_statrt_period
	private String feeStatrtPeriod;

	// 缴费结束时间 : fee_end_period
	private String feeEndPeriod;

	// 缴费对应日期 : fee_to_date
	private String feeToDate;

	// 缴费金额 : fee_sum
	private BigDecimal feeSum;

	// 对账状态 1 已对账 2，未对账 : account_status
	private Integer accountStatus;

	// 创建时间 : create_time
	private String createTime;

	// 创建人 : create_by
	private Long createBy;
	
	// 修改时间 : update_time
	private String updateTime;

	// 修改人 : update_by
	private Long updateBy;
	private String createByName;
	private String updateByName;

	// 版本号 : version
	private Long version;

	// 经办人 : agent_user
	private String agentUser;
	
	private Integer valid;

	private Integer payStatus; // 结算单状态
	
	// 银行流水号
	private String bankFlowNum ;
	private String splitStatus ;
	
	// 结算单信息
	private BigDecimal accountAmount; // 结算总金额
	private String payType; //结算类型
	// 20110001,'未结算',20110002,'部分结算',20110003,'结算完成',20130001,'待审核',20130002,'退款中',20130003,'退款成功',20130004,'退款失败',20130005,'审核通过','未知'
	private String payText; //结算状态
	private String startTime; //开始时间
	private String endTime; //结束时间
	private Integer bussStatus; // 结算关联的业务处理状态
	private String remark; // 备注
	private Integer payKind; // 结算单关联种类
	private BigDecimal customerFee; //预计客户信息费
	private BigDecimal personalFee; //预计服务人员信息费
	
	// 卡信息
	private BigDecimal cardBalance; //余额
	private String cardNumb; //卡号
	private String validTime; // 有效期
	private String postTerminalNo; // 终端编号
	private Integer isManual;//是否由后台录入:1是，2否（app支付
	
	//他人代收收款单位
	private Integer collectionEntity;
	private String userMobile;
	private String message;
	private Integer cateType;
	private String cityCode;
	//是否为修改预估 1是，2否
	private String isYg;
	//平台实际金额
	private BigDecimal platformFee;
	//代收渠道
	private Long collectionChannel ;  
	//代收渠道名称
	private String collectionChannelText ;  
	//平台订单编号
	private String platformOrderId ;  
	//三方平台支付金额
	private java.math.BigDecimal plateFee;
	//三方平台实付总金额
	private BigDecimal platformAllFee;
	
	//白条ID 
	private Long iouId ;  
	//白条金额 
	private java.math.BigDecimal iouAvailable;
	//白条状态
	private int iouState;
	//是否手动创建 1 是 2否（默认为否，自动创建）
	private Integer isManualFee;
	//管家帮分期取消状态
	private String vphStatus;
	//管家帮分期退款状态
	private String vphBackStatus;
	//是否处理，1已处理 2未处理
	private Integer isHandle;
	
	
	//缴费IDS
	private String[] ids_;
	//迁单的源缴费单id
	private Long sourcePayfeeId;
	//迁单退费的支付类型
	private Long sourceFeePost;
	
	//唯品会白条签约人姓名
	private String vipShopName;
	/**
	 * pos凭条查询 
	 */
	//pos凭条图片地址
	private String posImgUrl;
	//pos类型
	private Integer posType;
	//POS审批人id
	private Long posCheckBy ;  
	//POS审批人部门ID
	private Long posCheckDept ;  
	//POS审批日期
	private String posCheckDate ;  
	//POS审批驳回原因
	private String posFailReason ;  
	//POS审核状态 1待审核，2通过，3驳回
	private Integer posCheckStatus;
	//提交人
	private String createByString;
	//提交人部门
	private String createDeptString;
	//审核人
	private String checkByString;
	//审核人部门
	private String checkDeptString;
	//提交结束时间
	private String createEndTime;
	//pos凭条查询 订单类型
	private String typeText;
	//pos凭条查询 订单编号
	private String orderCode;
	//pos凭条查询 交易日期
	private String payDate;
	//pos凭条查询 交易人，户名
	private String payUser;
	//pos凭条查询 订单城市
	private String orderCity;
	//pos凭条查询 是否对账
	private String accountStatusText;
	//pos凭条查询 凭条来源
	private String feeTypeText;
	//pos凭条查询 凭条对账时间
	private String confirmTime;
	//pos凭条查询 凭条对账结束时间
	private String confirmEndTime;
	//pos凭条查询 流水金额
	private String dealMoney;
	//pos凭条查询 省份
	private String checkProvince;
	//pos凭条查询 城市
	private String checkCity;
	//pos凭条查询 城市分类
	private String categoryCode;
	private Long loginBy;//pos凭条查询  当前登录人
	private Long loginDept;//pos凭条查询  当前登录人部门
	private Integer loginLevel;//pos凭条查询 登录人权限级别
	//唯品会退费对象 1 唯品会 2客户
    private Integer refundObject;
    //唯品会申请姓名
    private String wphName;
    //是否是退费和分期
    private String isType;
    private String hjblName;//海金白条
    private String hjblBank;//海金白条
    private String hjblCard;//海金白条
    private String hjblCardBank;//海金白条
    
    //生成类型（1:客户主动缴纳 2:售后扣除缴纳）（用于生成售后手续费时使用）
    private Integer createType;

	/*20180608日完善字段*/
	private String isOldData;//是否老数据 0：不是 1：是
    private BigDecimal memberFee;//老数据割接存信息费临时用
	private Date finishRefundTime;//退款成功时间
	private String oaNumber;//OA单号
	private String payBanknum;//打款银行账号
	private String remark2;//财务审核退费导入需要一个备注
	private String log;//日志
	private String persontAndDate;//操作人和日期
	private Long oldAccountId;//老结算ID
	private Long backFromAccounId;//退款或者换货生成的结算单对应的源付款结算单ID
	private Long tmpVaild;
	private Integer submitStatus;
	private Long tmpValid;
	private Long oldOrderId;
	private BigDecimal poundage;
	private Integer payResult;
	private BigDecimal withrawNum;
	private Integer term;
	private String vipshopName;
	private Long withdrawNum;
	private Double salaryFee;
	
	private String ticketName;

	/**
	 * 订单商品市场价汇总
	 */
	private BigDecimal marketPrice;

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public Integer getCreateType() {
		return createType;
	}

	public void setCreateType(Integer createType) {
		this.createType = createType;
	}

	/**
	 * constitution
	 *
	 */

	public String getHjblName() {
		return hjblName;
	}

	public void setHjblName(String hjblName) {
		this.hjblName = hjblName;
	}

	public String getHjblBank() {
		return hjblBank;
	}

	public void setHjblBank(String hjblBank) {
		this.hjblBank = hjblBank;
	}

	public String getHjblCard() {
		return hjblCard;
	}

	public void setHjblCard(String hjblCard) {
		this.hjblCard = hjblCard;
	}

	public String getHjblCardBank() {
		return hjblCardBank;
	}

	public void setHjblCardBank(String hjblCardBank) {
		this.hjblCardBank = hjblCardBank;
	}

	/**
	 * 主键 : id
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	public String getPosImgUrl() {
		return posImgUrl;
	}

	public void setPosImgUrl(String posImgUrl) {
		this.posImgUrl = posImgUrl;
	}

	/**
	 * 主键 : id
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * : order_id
	 * 
	 * @return
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * : order_id
	 * 
	 * @return
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * 2是pos机，3银行转帐,4在线支付,6,卡支付,7券支付,8淘宝支付,9外部卡券 : fee_post
	 * 
	 * @return
	 */
	public Integer getFeePost() {
		return feePost;
	}

	/**
	 * 2是pos机，3银行转帐,4在线支付,6,卡支付,7券支付,8淘宝支付,9外部卡券 : fee_post
	 * 
	 * @return
	 */
	public void setFeePost(Integer feePost) {
		this.feePost = feePost;
	}

	public String getPostText() {
		return postText;
	}

	public void setPostText(String postText) {
		this.postText = postText;
	}

	/**
	 * 缴费类型：1新增缴费 2续费 : fee_type
	 * 
	 * @return
	 */
	public Integer getFeeType() {
		return feeType;
	}

	/**
	 * 缴费类型：1新增缴费 2续费 : fee_type
	 * 
	 * @return
	 */
	public void setFeeType(Integer feeType) {
		this.feeType = feeType;
	}

	/**
	 * 用户卡号 : post_num
	 * 
	 * @return
	 */
	public String getPostNum() {
		return postNum;
	}

	/**
	 * 用户卡号 : post_num
	 * 
	 * @return
	 */
	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}

	/**
	 * 消费卡卡号，消费卷卷号 : cards_num
	 * 
	 * @return
	 */
	public String getCardsNum() {
		return cardsNum;
	}

	/**
	 * 消费卡卡号，消费卷卷号 : cards_num
	 * 
	 * @return
	 */
	public void setCardsNum(String cardsNum) {
		this.cardsNum = cardsNum;
	}

	/**
	 * 缴费或者退费类型：1缴费，2退费 : in_back_type
	 * 
	 * @return
	 */
	public Integer getIsBackType() {
		return isBackType;
	}

	/**
	 * 缴费或者退费类型：1缴费，2退费 : in_back_type
	 * 
	 * @return
	 */
	public void setIsBackType(Integer isBackType) {
		this.isBackType = isBackType;
	}

	/**
	 * 缴费开始时间 : fee_statrt_period
	 * 
	 * @return
	 */
	public String getFeeStatrtPeriod() {
		return feeStatrtPeriod;
	}

	/**
	 * 缴费开始时间 : fee_statrt_period
	 * 
	 * @return
	 */
	public void setFeeStatrtPeriod(String feeStatrtPeriod) {
		this.feeStatrtPeriod = feeStatrtPeriod;
	}

	/**
	 * 缴费结束时间 : fee_end_period
	 * 
	 * @return
	 */
	public String getFeeEndPeriod() {
		return feeEndPeriod;
	}

	/**
	 * 缴费结束时间 : fee_end_period
	 * 
	 * @return
	 */
	public void setFeeEndPeriod(String feeEndPeriod) {
		this.feeEndPeriod = feeEndPeriod;
	}

	/**
	 * 缴费对应日期 : fee_to_date
	 * 
	 * @return
	 */
	public String getFeeToDate() {
		return feeToDate;
	}

	/**
	 * 缴费对应日期 : fee_to_date
	 * 
	 * @return
	 */
	public void setFeeToDate(String feeToDate) {
		this.feeToDate = feeToDate;
	}

	/**
	 * 缴费金额 : fee_sum
	 * 
	 * @return
	 */
	public java.math.BigDecimal getFeeSum() {
		return feeSum;
	}

	/**
	 * 缴费金额 : fee_sum
	 * 
	 * @return
	 */
	public void setFeeSum(java.math.BigDecimal feeSum) {
		this.feeSum = feeSum;
	}

	/**
	 * 对账状态 1 已对账 2，未对账 : account_status
	 * 
	 * @return
	 */
	public Integer getAccountStatus() {
		return accountStatus;
	}

	/**
	 * 对账状态 1 已对账 2，未对账 : account_status
	 * 
	 * @return
	 */
	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}

	/**
	 * 创建人 : create_by
	 * 
	 * @return
	 */
	@Override
	public Long getCreateBy() {
		return createBy;
	}

	/**
	 * 创建人 : create_by
	 * 
	 * @return
	 */
	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	/**
	 * 创建时间 : create_time
	 * 
	 * @return
	 */
	@Override
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间 : create_time
	 * 
	 * @return
	 */
	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新人 : update_by
	 * 
	 * @return
	 */
	@Override
	public Long getUpdateBy() {
		return updateBy;
	}

	/**
	 * 更新人 : update_by
	 * 
	 * @return
	 */
	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public String getUpdateByName() {
		return updateByName;
	}

	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}

	/**
	 * 更新时间 : update_time
	 * 
	 * @return
	 */
	@Override
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间 : update_time
	 * 
	 * @return
	 */
	@Override
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 版本号 : version
	 * 
	 * @return
	 */
	@Override
	public Long getVersion() {
		return version;
	}

	/**
	 * 版本号 : version
	 * 
	 * @return
	 */
	@Override
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * 经办人 : agent_user
	 * 
	 * @return
	 */
	public String getAgentUser() {
		return agentUser;
	}

	/**
	 * 经办人 : agent_user
	 * 
	 * @return
	 */
	public void setAgentUser(String agentUser) {
		this.agentUser = agentUser;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public String getPostBank() {
		return postBank;
	}

	public void setPostBank(String postBank) {
		this.postBank = postBank;
	}

	public String getBankFlowNum() {
		return bankFlowNum;
	}

	public void setBankFlowNum(String bankFlowNum) {
		this.bankFlowNum = bankFlowNum;
	}

	public String getSplitStatus() {
		return splitStatus;
	}

	public void setSplitStatus(String splitStatus) {
		this.splitStatus = splitStatus;
	}

	public String getPostUser() {
		return postUser;
	}

	public void setPostUser(String postUser) {
		this.postUser = postUser;
	}

	public BigDecimal getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(BigDecimal accountAmount) {
		this.accountAmount = accountAmount;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayText() {
		return payText;
	}

	public void setPayText(String payText) {
		this.payText = payText;
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

	public Integer getBussStatus() {
		return bussStatus;
	}

	public void setBussStatus(Integer bussStatus) {
		this.bussStatus = bussStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPayKind() {
		return payKind;
	}

	public void setPayKind(Integer payKind) {
		this.payKind = payKind;
	}

	public BigDecimal getCustomerFee() {
		return customerFee;
	}

	public void setCustomerFee(BigDecimal customerFee) {
		this.customerFee = customerFee;
	}

	public BigDecimal getPersonalFee() {
		return personalFee;
	}

	public void setPersonalFee(BigDecimal personalFee) {
		this.personalFee = personalFee;
	}

	public BigDecimal getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(BigDecimal cardBalance) {
		this.cardBalance = cardBalance;
	}

	public String getCardNumb() {
		return cardNumb;
	}

	public void setCardNumb(String cardNumb) {
		this.cardNumb = cardNumb;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public String getPostTerminalNo() {
		return postTerminalNo;
	}

	public void setPostTerminalNo(String postTerminalNo) {
		this.postTerminalNo = postTerminalNo;
	}

	public Integer getIsManual() {
		return isManual;
	}

	public void setIsManual(Integer isManual) {
		this.isManual = isManual;
	}

	public Integer getCollectionEntity() {
		return collectionEntity;
	}

	public void setCollectionEntity(Integer collectionEntity) {
		this.collectionEntity = collectionEntity;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCateType() {
		return cateType;
	}

	public void setCateType(Integer cateType) {
		this.cateType = cateType;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getIsYg() {
		return isYg;
	}

	public void setIsYg(String isYg) {
		this.isYg = isYg;
	}

	public BigDecimal getPlatformFee() {
		return platformFee;
	}

	public void setPlatformFee(BigDecimal platformFee) {
		this.platformFee = platformFee;
	}

	public Long getCollectionChannel() {
		return collectionChannel;
	}

	public void setCollectionChannel(Long collectionChannel) {
		this.collectionChannel = collectionChannel;
	}

	public String getPlatformOrderId() {
		return platformOrderId;
	}

	public void setPlatformOrderId(String platformOrderId) {
		this.platformOrderId = platformOrderId;
	}

	public String getCollectionChannelText() {
		return collectionChannelText;
	}

	public void setCollectionChannelText(String collectionChannelText) {
		this.collectionChannelText = collectionChannelText;
	}

	public java.math.BigDecimal getPlateFee() {
		return plateFee;
	}

	public void setPlateFee(java.math.BigDecimal plateFee) {
		this.plateFee = plateFee;
	}

	public BigDecimal getPlatformAllFee() {
		return platformAllFee;
	}

	public void setPlatformAllFee(BigDecimal platformAllFee) {
		this.platformAllFee = platformAllFee;
	}

	public Long getIouId() {
		return iouId;
	}

	public void setIouId(Long iouId) {
		this.iouId = iouId;
	}

	public java.math.BigDecimal getIouAvailable() {
		return iouAvailable;
	}

	public void setIouAvailable(java.math.BigDecimal iouAvailable) {
		this.iouAvailable = iouAvailable;
	}

	public int getIouState() {
		return iouState;
	}

	public void setIouState(int iouState) {
		this.iouState = iouState;
	}

	public Integer getIsManualFee() {
		return isManualFee;
	}

	public void setIsManualFee(Integer isManualFee) {
		this.isManualFee = isManualFee;
	}

	public String getVphStatus() {
		return vphStatus;
	}

	public void setVphStatus(String vphStatus) {
		this.vphStatus = vphStatus;
	}

	public String getVphBackStatus() {
		return vphBackStatus;
	}

	public void setVphBackStatus(String vphBackStatus) {
		this.vphBackStatus = vphBackStatus;
	}

	public Integer getIsHandle() {
		return isHandle;
	}

	public void setIsHandle(Integer isHandle) {
		this.isHandle = isHandle;
	}

	public Integer getPosType() {
		return posType;
	}

	public void setPosType(Integer posType) {
		this.posType = posType;
	}

	public Long getPosCheckBy() {
		return posCheckBy;
	}

	public void setPosCheckBy(Long posCheckBy) {
		this.posCheckBy = posCheckBy;
	}

	public Long getPosCheckDept() {
		return posCheckDept;
	}

	public void setPosCheckDept(Long posCheckDept) {
		this.posCheckDept = posCheckDept;
	}

	public String getPosCheckDate() {
		return posCheckDate;
	}

	public void setPosCheckDate(String posCheckDate) {
		this.posCheckDate = posCheckDate;
	}

	public String getPosFailReason() {
		return posFailReason;
	}

	public void setPosFailReason(String posFailReason) {
		this.posFailReason = posFailReason;
	}

	public Integer getPosCheckStatus() {
		return posCheckStatus;
	}

	public void setPosCheckStatus(Integer posCheckStatus) {
		this.posCheckStatus = posCheckStatus;
	}

	public String getCreateByString() {
		return createByString;
	}

	public void setCreateByString(String createByString) {
		this.createByString = createByString;
	}

	public String getCreateDeptString() {
		return createDeptString;
	}

	public void setCreateDeptString(String createDeptString) {
		this.createDeptString = createDeptString;
	}

	public String getCheckByString() {
		return checkByString;
	}

	public void setCheckByString(String checkByString) {
		this.checkByString = checkByString;
	}

	public String getCheckDeptString() {
		return checkDeptString;
	}

	public void setCheckDeptString(String checkDeptString) {
		this.checkDeptString = checkDeptString;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}

	public String[] getIds_() {
		return ids_;
	}

	public void setIds_(String[] ids_) {
		this.ids_ = ids_;
	}

	public Long getSourcePayfeeId() {
		return sourcePayfeeId;
	}

	public void setSourcePayfeeId(Long sourcePayfeeId) {
		this.sourcePayfeeId = sourcePayfeeId;
	}

	public Long getSourceFeePost() {
		return sourceFeePost;
	}

	public void setSourceFeePost(Long sourceFeePost) {
		this.sourceFeePost = sourceFeePost;
	}

	public String getTypeText() {
		return typeText;
	}

	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public String getPayUser() {
		return payUser;
	}

	public void setPayUser(String payUser) {
		this.payUser = payUser;
	}

	public String getOrderCity() {
		return orderCity;
	}

	public void setOrderCity(String orderCity) {
		this.orderCity = orderCity;
	}

	public String getAccountStatusText() {
		return accountStatusText;
	}

	public void setAccountStatusText(String accountStatusText) {
		this.accountStatusText = accountStatusText;
	}

	public String getFeeTypeText() {
		return feeTypeText;
	}

	public void setFeeTypeText(String feeTypeText) {
		this.feeTypeText = feeTypeText;
	}

	public String getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(String confirmTime) {
		this.confirmTime = confirmTime;
	}

	public String getDealMoney() {
		return dealMoney;
	}

	public void setDealMoney(String dealMoney) {
		this.dealMoney = dealMoney;
	}

	public String getCheckProvince() {
		return checkProvince;
	}

	public void setCheckProvince(String checkProvince) {
		this.checkProvince = checkProvince;
	}

	public String getCheckCity() {
		return checkCity;
	}

	public void setCheckCity(String checkCity) {
		this.checkCity = checkCity;
	}

	public String getConfirmEndTime() {
		return confirmEndTime;
	}

	public void setConfirmEndTime(String confirmEndTime) {
		this.confirmEndTime = confirmEndTime;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getVipShopName() {
		return vipShopName;
	}

	public void setVipShopName(String vipShopName) {
		this.vipShopName = vipShopName;
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

	public Integer getRefundObject() {
		return refundObject;
	}

	public void setRefundObject(Integer refundObject) {
		this.refundObject = refundObject;
	}

	public String getWphName() {
		return wphName;
	}

	public void setWphName(String wphName) {
		this.wphName = wphName;
	}

	public String getIsType() {
		return isType;
	}

	public void setIsType(String isType) {
		this.isType = isType;
	}

	public String getIsOldData() {
		return isOldData;
	}

	public void setIsOldData(String isOldData) {
		this.isOldData = isOldData;
	}

	public BigDecimal getMemberFee() {
		return memberFee;
	}

	public void setMemberFee(BigDecimal memberFee) {
		this.memberFee = memberFee;
	}

	public Date getFinishRefundTime() {
		return finishRefundTime;
	}

	public void setFinishRefundTime(Date finishRefundTime) {
		this.finishRefundTime = finishRefundTime;
	}

	public String getOaNumber() {
		return oaNumber;
	}

	public void setOaNumber(String oaNumber) {
		this.oaNumber = oaNumber;
	}

	public String getPayBanknum() {
		return payBanknum;
	}

	public void setPayBanknum(String payBanknum) {
		this.payBanknum = payBanknum;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getPersontAndDate() {
		return persontAndDate;
	}

	public void setPersontAndDate(String persontAndDate) {
		this.persontAndDate = persontAndDate;
	}

	public Long getOldAccountId() {
		return oldAccountId;
	}

	public void setOldAccountId(Long oldAccountId) {
		this.oldAccountId = oldAccountId;
	}

	public Long getBackFromAccounId() {
		return backFromAccounId;
	}

	public void setBackFromAccounId(Long backFromAccounId) {
		this.backFromAccounId = backFromAccounId;
	}

	public Long getTmpVaild() {
		return tmpVaild;
	}

	public void setTmpVaild(Long tmpVaild) {
		this.tmpVaild = tmpVaild;
	}

	public Integer getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(Integer submitStatus) {
		this.submitStatus = submitStatus;
	}

	public Long getTmpValid() {
		return tmpValid;
	}

	public void setTmpValid(Long tmpValid) {
		this.tmpValid = tmpValid;
	}

	public Long getOldOrderId() {
		return oldOrderId;
	}

	public void setOldOrderId(Long oldOrderId) {
		this.oldOrderId = oldOrderId;
	}

	public BigDecimal getPoundage() {
		return poundage;
	}

	public void setPoundage(BigDecimal poundage) {
		this.poundage = poundage;
	}

	public Integer getPayResult() {
		return payResult;
	}

	public void setPayResult(Integer payResult) {
		this.payResult = payResult;
	}

	public BigDecimal getWithrawNum() {
		return withrawNum;
	}

	public void setWithrawNum(BigDecimal withrawNum) {
		this.withrawNum = withrawNum;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public String getVipshopName() {
		return vipshopName;
	}

	public void setVipshopName(String vipshopName) {
		this.vipshopName = vipshopName;
	}

	public Long getWithdrawNum() {
		return withdrawNum;
	}

	public void setWithdrawNum(Long withdrawNum) {
		this.withdrawNum = withdrawNum;
	}

	public Double getSalaryFee() {
		return salaryFee;
	}

	public void setSalaryFee(Double salaryFee) {
		this.salaryFee = salaryFee;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
}
