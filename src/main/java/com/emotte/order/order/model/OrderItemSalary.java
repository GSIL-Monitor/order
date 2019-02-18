package com.emotte.order.order.model;
import org.wildhorse.server.core.model.BaseModel;
public class OrderItemSalary extends BaseModel{ 
	/**
	  日志编号
	*/
	private Long id;

	/**
	  订单id
	*/
	private Long orderId;

	/**
	  服务人员id
	*/
	private Long personId;

	/**
	  结算单id
	*/
	private Long accountId;

	/**
	  是否处理：1未处理，2已处理
	*/
	private Integer valid;

	
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

	public Long getPersonId(){
		return personId; 
	}

	public void setPersonId(Long personId){
		this.personId = personId; 
	}

	public Long getAccountId(){
		return accountId; 
	}

	public void setAccountId(Long accountId){
		this.accountId = accountId; 
	}

	public Integer getValid(){
		return valid; 
	}

	public void setValid(Integer valid){
		this.valid = valid; 
	}

	

}