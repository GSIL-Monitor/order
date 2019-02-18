package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order_after_fees
 */
public class AfterFees extends BaseModel {

 	// 主键 : ID
	private Long id;
	
	
 	// 类型 : TYPE
	private Integer type;
	
	
 	// 缴费id : PAYFEE_ID
	private Long payfeeId;
	
	
 	// 应扣金额 : FEE_MONEY
	private Double feeMoney;
	
	
 	// 创建人 : CREATE_BY
	private Long createBy;
	
	
 	// 更新人 : UPDATE_BY
	private Long updateBy;
	
	
 	// 是否有效(1:是 2:否) : VALID
	private Integer valid;
	
	
 	// 版本 : VERSION
	private Long version;
	
	
 	// 手动日志 : LOG
	private String log;
	
	//订单id
	private Long orderId; 
	
	// 应扣总金额  
    private Double payfeeMoney;
    
    // 是否已生成售后手续费记录(1:未生成,2:已生成) : STATUS
 	private Integer status;
 	
 	//总金额
 	private Double feeSumA;
 	
 	//已使用金额
 	private Double feeSumB;
 	
 	//剩余金额
 	private Double feeSumC;

	public Double getFeeSumA() {
		return feeSumA;
	}


	public void setFeeSumA(Double feeSumA) {
		this.feeSumA = feeSumA;
	}


	public Double getFeeSumB() {
		return feeSumB;
	}


	public void setFeeSumB(Double feeSumB) {
		this.feeSumB = feeSumB;
	}


	public Double getFeeSumC() {
		return feeSumC;
	}


	public void setFeeSumC(Double feeSumC) {
		this.feeSumC = feeSumC;
	}


	public Double getPayfeeMoney() {
		return payfeeMoney;
	}


	public void setPayfeeMoney(Double payfeeMoney) {
		this.payfeeMoney = payfeeMoney;
	}


	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Long getPayfeeId() {
		return payfeeId;
	}


	public void setPayfeeId(Long payfeeId) {
		this.payfeeId = payfeeId;
	}


	public Double getFeeMoney() {
		return feeMoney;
	}


	public void setFeeMoney(Double feeMoney) {
		this.feeMoney = feeMoney;
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
	public Long getUpdateBy() {
		return updateBy;
	}


	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}


	public Integer getValid() {
		return valid;
	}


	public void setValid(Integer valid) {
		this.valid = valid;
	}


	@Override
	public Long getVersion() {
		return version;
	}


	@Override
	public void setVersion(Long version) {
		this.version = version;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getLog() {
		return log;
	}


	public void setLog(String log) {
		this.log = log;
	}
	
	

	
	
}