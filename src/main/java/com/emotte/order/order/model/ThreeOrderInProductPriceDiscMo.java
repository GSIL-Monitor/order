package com.emotte.order.order.model;

import java.math.BigDecimal;

import org.wildhorse.server.core.model.BaseModel;

public class ThreeOrderInProductPriceDiscMo extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String product_code;
	private String dict_code;
	private BigDecimal price;
	
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getDict_code() {
		return dict_code;
	}
	public void setDict_code(String dict_code) {
		this.dict_code = dict_code;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
