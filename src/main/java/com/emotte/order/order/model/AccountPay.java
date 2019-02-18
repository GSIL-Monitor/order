package com.emotte.order.order.model;
import java.math.BigDecimal;

import org.wildhorse.server.core.model.BaseModel;
public class AccountPay extends BaseModel{ 
	/**
	  是否退款结算单：1是退款结算单 2 不是退款结算单
	*/
	private Long isBackType;

	/**
	  开始时间
	*/
	private String startTime;

	/**
	  结束时间
	*/
	private String endTime;

	/**
	  是否有效：1有效，2无效。结算单可以删除
	*/
	private Long valid;

	/**
	  备注
	*/
	private String remark;

	/**
	  (退款或者换货生成的结算单)对应的源付款结算单ID
	*/
	private Long backFromAccounId;

	/**
	  t_account_pay
	*/
	private Long id;

	/**
	  订单id
	*/
	private Long orderId;

	/**
	  结算总额
	*/
	private BigDecimal accountAmount;

	/**
	  创建时间
	*/
	private String createTime;

	/**
	  创建人
	*/
	private Long createBy;

	/**
	  更新人
	*/
	private Long updateBy;

	/**
	  更新时间
	*/
	private String updateTime;

	/**
	  版本号
	*/
	private Long version;

	/**
	  支付状态：结算单（20110001未支付，20110002已支付，20110003支付完成） 退款结算单（30001001,待审核,30001002,退款中,30001003,退款成功,30001004,退款失败）
	*/
	private String payStatus;
	private String orderStatus;

	/**
	  结算类型：1:定金、2:押金、3:预收、4:实收、5:补缴
	*/
	private String payType;

	/**
	  结算单关联种类：1.账户充值，2.订单，3.解决方案 ，4 卡
	*/
	private String payKind;

	/**
	  结算关联的业务处理状态：1未处理业务 2，业务处理完成
	*/
	private String bussStatus;

	private String cateType;
	private BigDecimal totalPay; // 订单总金额
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	private int isManual;
	private String isOldData;
	
	//服务人员服务人员服务费
	private double money;
	
	public Long getIsBackType(){
		return isBackType; 
	}

	public void setIsBackType(Long isBackType){
		this.isBackType = isBackType; 
	}

	public String getStartTime(){
		return startTime; 
	}

	public void setStartTime(String startTime){
		this.startTime = startTime; 
	}

	public String getEndTime(){
		return endTime; 
	}

	public void setEndTime(String endTime){
		this.endTime = endTime; 
	}

	public Long getValid(){
		return valid; 
	}

	public void setValid(Long valid){
		this.valid = valid; 
	}

	public String getRemark(){
		return remark; 
	}

	public void setRemark(String remark){
		this.remark = remark; 
	}

	public Long getBackFromAccounId(){
		return backFromAccounId; 
	}

	public void setBackFromAccounId(Long backFromAccounId){
		this.backFromAccounId = backFromAccounId; 
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

	public BigDecimal getAccountAmount(){
		return accountAmount; 
	}

	public void setAccountAmount(BigDecimal accountAmount){
		this.accountAmount = accountAmount; 
	}

	@Override
	public String getCreateTime(){
		return createTime; 
	}

	@Override
	public void setCreateTime(String createTime){
		this.createTime = createTime; 
	}

	@Override
	public Long getCreateBy(){
		return createBy; 
	}

	@Override
	public void setCreateBy(Long createBy){
		this.createBy = createBy; 
	}

	@Override
	public Long getUpdateBy(){
		return updateBy; 
	}

	@Override
	public void setUpdateBy(Long updateBy){
		this.updateBy = updateBy; 
	}

	@Override
	public String getUpdateTime(){
		return updateTime; 
	}

	@Override
	public void setUpdateTime(String updateTime){
		this.updateTime = updateTime; 
	}

	@Override
	public Long getVersion(){
		return version; 
	}

	@Override
	public void setVersion(Long version){
		this.version = version; 
	}

	public String getPayStatus(){
		return payStatus; 
	}

	public void setPayStatus(String payStatus){
		this.payStatus = payStatus; 
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getPayType(){
		return payType; 
	}

	public void setPayType(String payType){
		this.payType = payType; 
	}

	public String getPayKind(){
		return payKind; 
	}

	public void setPayKind(String payKind){
		this.payKind = payKind; 
	}

	public String getBussStatus(){
		return bussStatus; 
	}

	public void setBussStatus(String bussStatus){
		this.bussStatus = bussStatus; 
	}

	public String getCateType() {
		return cateType;
	}

	public void setCateType(String cateType) {
		this.cateType = cateType;
	}

	public BigDecimal getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(BigDecimal totalPay) {
		this.totalPay = totalPay;
	}

	public int getIsManual() {
		return isManual;
	}

	public void setIsManual(int isManual) {
		this.isManual = isManual;
	}

	
	public String getIsOldData() {
		return isOldData;
	}

	public void setIsOldData(String isOldData) {
		this.isOldData = isOldData;
	}

	@Override
	public String toString() {
		return "AccountPay [isBackType=" + isBackType + ", startTime="
				+ startTime + ", endTime=" + endTime + ", valid=" + valid
				+ ", remark=" + remark + ", backFromAccounId="
				+ backFromAccounId + ", id=" + id + ", orderId=" + orderId
				+ ", accountAmount=" + accountAmount + ", createTime="
				+ createTime + ", createBy=" + createBy + ", updateBy="
				+ updateBy + ", updateTime=" + updateTime + ", version="
				+ version + ", payStatus=" + payStatus + ", orderStatus="
				+ orderStatus + ", payType=" + payType + ", payKind=" + payKind
				+ ", bussStatus=" + bussStatus + ", cateType=" + cateType
				+ ", totalPay=" + totalPay + ", isManual=" + isManual + "]";
	}

}