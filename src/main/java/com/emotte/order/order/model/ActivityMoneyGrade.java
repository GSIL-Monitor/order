package com.emotte.order.order.model;

import java.math.BigDecimal;

import org.wildhorse.server.core.model.BaseModel;

public class ActivityMoneyGrade extends BaseModel {
	
	//账户充值档位表id
	private Long id;
	
	//金额档位  （大于等于）
	private String moneyAndMonth;
	
	//满减金额
	private BigDecimal balance;
	
	
	private String productCode;
	

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMoneyAndMonth() {
		return moneyAndMonth;
	}

	public void setMoneyAndMonth(String moneyAndMonth) {
		this.moneyAndMonth = moneyAndMonth;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
