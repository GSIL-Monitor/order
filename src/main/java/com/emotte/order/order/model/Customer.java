package com.emotte.order.order.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**数据库海金申请单表model ,带有 "*" 的字段为 新建时必填字段
 * @author lihuiming
 *
 * @date 2018年9月11日
 */
@SuppressWarnings("deprecation")
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY) 
public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3388953637673377281L;
	private Long id;
	//*真实姓名
    private String customName; 
    //*客户身份证编号
    private String customIdcard;
    //*开户银行名称
    private String bankName;
    //*银行卡号
    private String bankAccountNum;
    //*银行预留手机号
    private String bankMobile;
    //*白条总额
    private Double whiteTotalMoney;
    //*分期还款总期数
    private String stagingNumber;
    //*每期应还金额
    private Double eachPeriodMoney;
    //*管家帮订单id
    private String orderId;
    //*合同编号
    private String contractCode;
    //身份证图片url(|分隔)
    private String idcardImageUrl;
    //三方协议图片url(|分隔)
    private String contractImageUrl;
    //状态10未填完 ，15结算中心审核中 ，16结算中心审核成功，17 结算中心审核失败 20三方合同审核中，21三方合同审核失败，22三方合同审核成功，
    //30应收账款审核中，31应收账款审核失败，32应收帐款审核成功，40放款审核中，41放款审核失败，42放款审核成功,60合同结束
    private Integer state;
    //*服务人员id 对应managerId
    private Long custId;
    @JsonIgnore
    private Timestamp createTime;
    
    private String createTimeStr;
    //* 创建人
    private Long createBy;
    @JsonIgnore
    private Timestamp updateTime;
    
    private String updateTimeStr;

    private Long updateBy;

    private Integer version;
    //是否有效
    private Integer valid;
    @JsonIgnore
    private Timestamp updateInfoTime;
    
    private String updateInfoTimeStr;
    
    private String ftpIdcardImageUrl;

    private String ftpContractImageUrl;
    //提交客户合同时间
    @JsonIgnore
    private Timestamp updateContractTime;
    
    private String updateContractTimeStr;
    //批次号
    private String batchId;
    //海金保理白条订单号
    private String hjblOrderId;
    //应收帐款日期
    private String accountreceivdate;
    //三方合同回调接口信息
    private String callbackContract;
    @JsonIgnore
    //三方合同回调接口更新时间
    private Timestamp callbackContractUpdatetime;
    
    private String callbackContractUpdatetimeStr;
    //应收账款期限日期清单回调接口信息
    private String callbackPeriod;
    @JsonIgnore
    //应收账款期限日期清单回调接口信息更新时间
    private Timestamp callbackPeriodUpdatetime;
    private String callbackPeriodUpdatetimeStr;
    
    //放款回调接口信息
    private String callbackPayload;
    
    //放款回调接口信息更新时间
    @JsonIgnore
    private Timestamp callbackPayloadUpdatetime;
    
    private String callbackPayloadUpdatetimeStr;
    //联动 用户业务协议号
    private String usrBusiAgreementId;
    //联动 用户业务协议号
    private String usrPayAgreementId;
    
    private Short exception;

    private String log;
    //上户时间
    @JsonIgnore
    private Timestamp interviewStartTime;
    private String interviewStartTimeStr;
    //上户表ID
    private Long interviewId;
    //*（合同）服务人员姓名
    private String agServiceName;
    //*（合同）服务人员身份证号
    private String agServiceIdcard;
    //*（合同）合同签约日期
    @JsonIgnore
    private Timestamp agSignDate;
    
    private String agSignDateStr;
    //*（合同）合同开始日期
    @JsonIgnore
    private Timestamp agStartDate;
    
    private String agStartDateStr;
    //*（合同）合同结束日期
    @JsonIgnore
    private Timestamp agEndDate;
    
    private String agEndDateStr;
    //*（订单）服务类型
    private String oOrderType;
    
    private String zhangkuanflag;

    private String hegeflag;

    private String hegeerrmsg;

    private String zhangkuanerrmsg;

    private Short isUnbinding;
    // 申请类型:1.海金申请 2.服务人员申请
    private Short applyType;
    @JsonIgnore
    private Timestamp submittime;
    
    private String submittimeStr;
    
    
    /**
     * 用户登录唯一标识
     */
    private String oauthCode;

    private String verifyCode;
    
    private String bindNo;
	
    private String flag;
    
    
    

	public String getOauthCode() {
		return oauthCode;
	}

	public void setOauthCode(String oauthCode) {
		this.oauthCode = oauthCode;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getBindNo() {
		return bindNo;
	}

	public void setBindNo(String bindNo) {
		this.bindNo = bindNo;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getCustomIdcard() {
		return customIdcard;
	}

	public void setCustomIdcard(String customIdcard) {
		this.customIdcard = customIdcard;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccountNum() {
		return bankAccountNum;
	}

	public void setBankAccountNum(String bankAccountNum) {
		this.bankAccountNum = bankAccountNum;
	}

	public String getBankMobile() {
		return bankMobile;
	}

	public void setBankMobile(String bankMobile) {
		this.bankMobile = bankMobile;
	}

	public Double getWhiteTotalMoney() {
		return whiteTotalMoney;
	}

	public void setWhiteTotalMoney(Double whiteTotalMoney) {
		this.whiteTotalMoney = whiteTotalMoney;
	}

	public String getStagingNumber() {
		return stagingNumber;
	}

	public void setStagingNumber(String stagingNumber) {
		this.stagingNumber = stagingNumber;
	}

	public Double getEachPeriodMoney() {
		return eachPeriodMoney;
	}

	public void setEachPeriodMoney(Double eachPeriodMoney) {
		this.eachPeriodMoney = eachPeriodMoney;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getIdcardImageUrl() {
		return idcardImageUrl;
	}

	public void setIdcardImageUrl(String idcardImageUrl) {
		this.idcardImageUrl = idcardImageUrl;
	}

	public String getContractImageUrl() {
		return contractImageUrl;
	}

	public void setContractImageUrl(String contractImageUrl) {
		this.contractImageUrl = contractImageUrl;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public Timestamp getUpdateInfoTime() {
		return updateInfoTime;
	}

	public void setUpdateInfoTime(Timestamp updateInfoTime) {
		this.updateInfoTime = updateInfoTime;
	}

	public String getFtpIdcardImageUrl() {
		return ftpIdcardImageUrl;
	}

	public void setFtpIdcardImageUrl(String ftpIdcardImageUrl) {
		this.ftpIdcardImageUrl = ftpIdcardImageUrl;
	}

	public String getFtpContractImageUrl() {
		return ftpContractImageUrl;
	}

	public void setFtpContractImageUrl(String ftpContractImageUrl) {
		this.ftpContractImageUrl = ftpContractImageUrl;
	}

	public Timestamp getUpdateContractTime() {
		return updateContractTime;
	}

	public void setUpdateContractTime(Timestamp updateContractTime) {
		this.updateContractTime = updateContractTime;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getHjblOrderId() {
		return hjblOrderId;
	}

	public void setHjblOrderId(String hjblOrderId) {
		this.hjblOrderId = hjblOrderId;
	}

	public String getAccountreceivdate() {
		return accountreceivdate;
	}

	public void setAccountreceivdate(String accountreceivdate) {
		this.accountreceivdate = accountreceivdate;
	}

	public String getCallbackContract() {
		return callbackContract;
	}

	public void setCallbackContract(String callbackContract) {
		this.callbackContract = callbackContract;
	}

	public Timestamp getCallbackContractUpdatetime() {
		return callbackContractUpdatetime;
	}

	public void setCallbackContractUpdatetime(Timestamp callbackContractUpdatetime) {
		this.callbackContractUpdatetime = callbackContractUpdatetime;
	}

	public String getCallbackPeriod() {
		return callbackPeriod;
	}

	public void setCallbackPeriod(String callbackPeriod) {
		this.callbackPeriod = callbackPeriod;
	}

	public Timestamp getCallbackPeriodUpdatetime() {
		return callbackPeriodUpdatetime;
	}

	public void setCallbackPeriodUpdatetime(Timestamp callbackPeriodUpdatetime) {
		this.callbackPeriodUpdatetime = callbackPeriodUpdatetime;
	}

	public String getCallbackPayload() {
		return callbackPayload;
	}

	public void setCallbackPayload(String callbackPayload) {
		this.callbackPayload = callbackPayload;
	}

	public Timestamp getCallbackPayloadUpdatetime() {
		return callbackPayloadUpdatetime;
	}

	public void setCallbackPayloadUpdatetime(Timestamp callbackPayloadUpdatetime) {
		this.callbackPayloadUpdatetime = callbackPayloadUpdatetime;
	}

	public String getUsrBusiAgreementId() {
		return usrBusiAgreementId;
	}

	public void setUsrBusiAgreementId(String usrBusiAgreementId) {
		this.usrBusiAgreementId = usrBusiAgreementId;
	}

	public String getUsrPayAgreementId() {
		return usrPayAgreementId;
	}

	public void setUsrPayAgreementId(String usrPayAgreementId) {
		this.usrPayAgreementId = usrPayAgreementId;
	}

	public Short getException() {
		return exception;
	}

	public void setException(Short exception) {
		this.exception = exception;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Timestamp getInterviewStartTime() {
		return interviewStartTime;
	}

	public void setInterviewStartTime(Timestamp interviewStartTime) {
		this.interviewStartTime = interviewStartTime;
	}

	public Long getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Long interviewId) {
		this.interviewId = interviewId;
	}

	public String getAgServiceName() {
		return agServiceName;
	}

	public void setAgServiceName(String agServiceName) {
		this.agServiceName = agServiceName;
	}

	public String getAgServiceIdcard() {
		return agServiceIdcard;
	}

	public void setAgServiceIdcard(String agServiceIdcard) {
		this.agServiceIdcard = agServiceIdcard;
	}

	public Timestamp getAgSignDate() {
		return agSignDate;
	}

	public void setAgSignDate(Timestamp agSignDate) {
		this.agSignDate = agSignDate;
	}

	public Timestamp getAgStartDate() {
		return agStartDate;
	}

	public void setAgStartDate(Timestamp agStartDate) {
		this.agStartDate = agStartDate;
	}

	public Timestamp getAgEndDate() {
		return agEndDate;
	}

	public void setAgEndDate(Timestamp agEndDate) {
		this.agEndDate = agEndDate;
	}

	public String getoOrderType() {
		return oOrderType;
	}

	public void setoOrderType(String oOrderType) {
		this.oOrderType = oOrderType;
	}

	public String getZhangkuanflag() {
		return zhangkuanflag;
	}

	public void setZhangkuanflag(String zhangkuanflag) {
		this.zhangkuanflag = zhangkuanflag;
	}

	public String getHegeflag() {
		return hegeflag;
	}

	public void setHegeflag(String hegeflag) {
		this.hegeflag = hegeflag;
	}

	public String getHegeerrmsg() {
		return hegeerrmsg;
	}

	public void setHegeerrmsg(String hegeerrmsg) {
		this.hegeerrmsg = hegeerrmsg;
	}

	public String getZhangkuanerrmsg() {
		return zhangkuanerrmsg;
	}

	public void setZhangkuanerrmsg(String zhangkuanerrmsg) {
		this.zhangkuanerrmsg = zhangkuanerrmsg;
	}

	public Short getIsUnbinding() {
		return isUnbinding;
	}

	public void setIsUnbinding(Short isUnbinding) {
		this.isUnbinding = isUnbinding;
	}

	public Short getApplyType() {
		return applyType;
	}

	public void setApplyType(Short applyType) {
		this.applyType = applyType;
	}

	public Timestamp getSubmittime() {
		return submittime;
	}

	public void setSubmittime(Timestamp submittime) {
		this.submittime = submittime;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public String getUpdateInfoTimeStr() {
		return updateInfoTimeStr;
	}

	public void setUpdateInfoTimeStr(String updateInfoTimeStr) {
		this.updateInfoTimeStr = updateInfoTimeStr;
	}

	public String getUpdateContractTimeStr() {
		return updateContractTimeStr;
	}

	public void setUpdateContractTimeStr(String updateContractTimeStr) {
		this.updateContractTimeStr = updateContractTimeStr;
	}

	public String getCallbackContractUpdatetimeStr() {
		return callbackContractUpdatetimeStr;
	}

	public void setCallbackContractUpdatetimeStr(String callbackContractUpdatetimeStr) {
		this.callbackContractUpdatetimeStr = callbackContractUpdatetimeStr;
	}

	public String getCallbackPeriodUpdatetimeStr() {
		return callbackPeriodUpdatetimeStr;
	}

	public void setCallbackPeriodUpdatetimeStr(String callbackPeriodUpdatetimeStr) {
		this.callbackPeriodUpdatetimeStr = callbackPeriodUpdatetimeStr;
	}

	public String getCallbackPayloadUpdatetimeStr() {
		return callbackPayloadUpdatetimeStr;
	}

	public void setCallbackPayloadUpdatetimeStr(String callbackPayloadUpdatetimeStr) {
		this.callbackPayloadUpdatetimeStr = callbackPayloadUpdatetimeStr;
	}

	public String getInterviewStartTimeStr() {
		return interviewStartTimeStr;
	}

	public void setInterviewStartTimeStr(String interviewStartTimeStr) {
		this.interviewStartTimeStr = interviewStartTimeStr;
	}

	public String getAgSignDateStr() {
		return agSignDateStr;
	}

	public void setAgSignDateStr(String agSignDateStr) {
		this.agSignDateStr = agSignDateStr;
	}

	public String getAgStartDateStr() {
		return agStartDateStr;
	}

	public void setAgStartDateStr(String agStartDateStr) {
		this.agStartDateStr = agStartDateStr;
	}

	public String getAgEndDateStr() {
		return agEndDateStr;
	}

	public void setAgEndDateStr(String agEndDateStr) {
		this.agEndDateStr = agEndDateStr;
	}

	public String getSubmittimeStr() {
		return submittimeStr;
	}

	public void setSubmittimeStr(String submittimeStr) {
		this.submittimeStr = submittimeStr;
	}

    
    
    
    
}