package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : T_order_recorder
 */
public class Recorder extends BaseModel {

	// 主键 : ID
	private Long id;

	// 订单id : ORDER_ID
	private Long orderId;

	// 回访记录人 : RECORDER
	private Long recorder;

	// 满意度 1.非常满意 2.比较满意 3.不满意 : SATISFY
	private Byte satisfy;

	// 意向程度 1.强烈意向 2.一般意向 3.远期意向 : MEANING
	private Byte meaning;

	// 下次回访时间 : NEXT_TIME
	private String nextTime;

	private String orderCode;

	// 备注信息 : REMARK
	private String remark;

	// 分类code : CLASSFY_CODE
	private String classfyCode;

	private Long isread;

	private String realname;

	private String satis;

	private String mean;

	private String oneclass;

	private String twoclass;

	private String threeclass;

	private Integer orderProgress;

	private String orderProgressInfo;

	private String orderProgressStr;

	private String unintentionalReason;

	private Long customId;

	private String sources;
	
	private String unintentionalReasonStr;
	
	private Long updateBy;

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

	public Long getRecorder() {
		return recorder;
	}

	public void setRecorder(Long recorder) {
		this.recorder = recorder;
	}

	public Byte getSatisfy() {
		return satisfy;
	}

	public void setSatisfy(Byte satisfy) {
		this.satisfy = satisfy;
	}

	public Byte getMeaning() {
		return meaning;
	}

	public void setMeaning(Byte meaning) {
		this.meaning = meaning;
	}

	public String getNextTime() {
		return nextTime;
	}

	public void setNextTime(String nextTime) {
		this.nextTime = nextTime;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getClassfyCode() {
		return classfyCode;
	}

	public void setClassfyCode(String classfyCode) {
		this.classfyCode = classfyCode;
	}

	public Long getIsread() {
		return isread;
	}

	public void setIsread(Long isread) {
		this.isread = isread;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSatis() {
		return satis;
	}

	public void setSatis(String satis) {
		this.satis = satis;
	}

	public String getMean() {
		return mean;
	}

	public void setMean(String mean) {
		this.mean = mean;
	}

	public String getOneclass() {
		return oneclass;
	}

	public void setOneclass(String oneclass) {
		this.oneclass = oneclass;
	}

	public String getTwoclass() {
		return twoclass;
	}

	public void setTwoclass(String twoclass) {
		this.twoclass = twoclass;
	}

	public String getThreeclass() {
		return threeclass;
	}

	public void setThreeclass(String threeclass) {
		this.threeclass = threeclass;
	}

	public Integer getOrderProgress() {
		return orderProgress;
	}

	public void setOrderProgress(Integer orderProgress) {
		this.orderProgress = orderProgress;
	}

	public String getOrderProgressInfo() {
		return orderProgressInfo;
	}

	public void setOrderProgressInfo(String orderProgressInfo) {
		this.orderProgressInfo = orderProgressInfo;
	}

	public String getOrderProgressStr() {
		return orderProgressStr;
	}

	public void setOrderProgressStr(String orderProgressStr) {
		this.orderProgressStr = orderProgressStr;
	}

	public String getSources() {
		return sources;
	}

	public void setSources(String sources) {
		this.sources = sources;
	}

	public String getUnintentionalReason() {
		return unintentionalReason;
	}

	public void setUnintentionalReason(String unintentionalReason) {
		this.unintentionalReason = unintentionalReason;
	}

	public Long getCustomId() {
		return customId;
	}

	public void setCustomId(Long customId) {
		this.customId = customId;
	}

	public String getUnintentionalReasonStr() {
		return unintentionalReasonStr;
	}

	public void setUnintentionalReasonStr(String unintentionalReasonStr) {
		this.unintentionalReasonStr = unintentionalReasonStr;
	}

	@Override
	public Long getUpdateBy() {
		return updateBy;
	}

	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	
	

}