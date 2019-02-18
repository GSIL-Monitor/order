package com.emotte.order.order.model;
import java.math.BigDecimal;

import org.wildhorse.server.core.model.BaseModel;
public class OrderSlice extends BaseModel{ 
	/**
	  主键ID
	*/
	private Long id;

	/**
	  订单ID
	*/
	private Long orderId;

	/**
	  分层类型 （1，三方合作分层，2，易盟二次分账分层）
	*/
	private Integer isOther;

	/**
	  服务人员ID（三方合作的时候，没有此id，服务性订单二次分账的时候，需要记录此ID）
	*/
	private Long personId;

	/**
	  分层表ID（如果是三方合作的存放的是t_product_third_divide的id  如果是二次分账的话，存放的是t_product_divide的ID）
	*/
	private Long divideId;

	/**
	  是否是默认分层（1，默认分成，2 非默认）
	*/
	private Integer isDefault;

	/**
	  分成金额
	*/
	private BigDecimal divideMoney;

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

	public Integer getIsOther(){
		return isOther; 
	}

	public void setIsOther(Integer isOther){
		this.isOther = isOther; 
	}

	public Long getPersonId(){
		return personId; 
	}

	public void setPersonId(Long personId){
		this.personId = personId; 
	}

	public Long getDivideId(){
		return divideId; 
	}

	public void setDivideId(Long divideId){
		this.divideId = divideId; 
	}

	public Integer getIsDefault(){
		return isDefault; 
	}

	public void setIsDefault(Integer isDefault){
		this.isDefault = isDefault; 
	}

	public BigDecimal getDivideMoney(){
		return divideMoney; 
	}

	public void setDivideMoney(BigDecimal divideMoney){
		this.divideMoney = divideMoney; 
	}

}