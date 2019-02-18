package com.emotte.order.order.model;
import java.math.BigDecimal;

import org.wildhorse.server.core.model.BaseModel;
public class ProductThirdDivide extends BaseModel{ 
	/**
	  主键
	*/
	private Long id;

	/**
	  t_city_product_dict_price表id
	*/
	private Long productDictPriceId;

	/**
	  易盟应收(分成方式为3时，存规格参考成本价)
	*/
	private BigDecimal oneselfFee;

	/**
	  第三方
	*/
	private BigDecimal othersFee;

	/**
	  分成方式 1金额 2 比例 3按订单金额 -规格参考成本价
	*/
	private Integer divideType;

	/**
	  状态 1未启用 2启用 3归档  (启用和归档后永不可改)
	*/
	private Integer flag;

	/**
	  是否有效  1 有效  ， 2 无效
	*/
	private Integer valid;

	/**
	  易盟应收比例（1为最大值）只有在分成方式为比例时候存在
	*/
	private BigDecimal oneselfScale;

	/**
	  第三方比例 （1为最大值）只有在分成方式为比例时候存在
	*/
	private BigDecimal othersScale;

	public Long getId(){
		return id; 
	}

	public void setId(Long id){
		this.id = id; 
	}

	public Long getProductDictPriceId(){
		return productDictPriceId; 
	}

	public void setProductDictPriceId(Long productDictPriceId){
		this.productDictPriceId = productDictPriceId; 
	}


	public Integer getDivideType(){
		return divideType; 
	}

	public void setDivideType(Integer divideType){
		this.divideType = divideType; 
	}

	public Integer getFlag(){
		return flag; 
	}

	public void setFlag(Integer flag){
		this.flag = flag; 
	}


	public Integer getValid(){
		return valid; 
	}

	public void setValid(Integer valid){
		this.valid = valid; 
	}

	public BigDecimal getOneselfFee() {
		return oneselfFee;
	}

	public void setOneselfFee(BigDecimal oneselfFee) {
		this.oneselfFee = oneselfFee;
	}

	public BigDecimal getOthersFee() {
		return othersFee;
	}

	public void setOthersFee(BigDecimal othersFee) {
		this.othersFee = othersFee;
	}

	public BigDecimal getOneselfScale() {
		return oneselfScale;
	}

	public void setOneselfScale(BigDecimal oneselfScale) {
		this.oneselfScale = oneselfScale;
	}

	public BigDecimal getOthersScale() {
		return othersScale;
	}

	public void setOthersScale(BigDecimal othersScale) {
		this.othersScale = othersScale;
	}

	
}