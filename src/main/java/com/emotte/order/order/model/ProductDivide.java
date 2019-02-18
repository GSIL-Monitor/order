package com.emotte.order.order.model;

import java.math.BigDecimal;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : T_PRODUCT_DIVIDE
 */
public class ProductDivide extends BaseModel {

 	// 主键 : ID
	private Long id;
 	// 分成方式 1金额 2 比例 
	private Integer divideType;
 	// 客户信息费 
	private BigDecimal customerInfoFee;
 	// 服务人员信息费
	private BigDecimal serviceInfoFee;
 	// 服务人员劳务费 
	private BigDecimal serviceLabourFee;
 	// 1 员工制  2 非员工制 
	private Integer isEmployee;
 	// 分成周期 1年 2月 3日 4次 
	private Integer cycle;
 	// 服务月份 
	private Integer month;
 	// 是否默认  1默认 2非默认 
	private Integer isDefault;
 	// 状态 1未启用 2启用 3归档  (启用和归档后永不可改) 
	private Integer flag;
 	// 是否有效  1 有效  ， 2 无效 
	private Integer valid;
	//t_city_product_dict_price表id
	private Long productDictPriceId;
	//客户信息费比例（1为最大值）只有在分成方式为比例时候存在 
	private BigDecimal serviceInfoScale;
	//客户劳务费比例 （1为最大值）只有在分成方式为比例时候存在
	private BigDecimal serviceLabourScale;
	
	
	/**
	 * 主键 : ID
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 主键 : ID
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDivideType() {
		return divideType;
	}

	public void setDivideType(Integer divideType) {
		this.divideType = divideType;
	}

	public BigDecimal getCustomerInfoFee() {
		return customerInfoFee;
	}

	public void setCustomerInfoFee(BigDecimal customerInfoFee) {
		this.customerInfoFee = customerInfoFee;
	}

	public BigDecimal getServiceInfoFee() {
		return serviceInfoFee;
	}

	public void setServiceInfoFee(BigDecimal serviceInfoFee) {
		this.serviceInfoFee = serviceInfoFee;
	}

	public BigDecimal getServiceLabourFee() {
		return serviceLabourFee;
	}

	public void setServiceLabourFee(BigDecimal serviceLabourFee) {
		this.serviceLabourFee = serviceLabourFee;
	}

	public Integer getIsEmployee() {
		return isEmployee;
	}

	public void setIsEmployee(Integer isEmployee) {
		this.isEmployee = isEmployee;
	}

	public Integer getCycle() {
		return cycle;
	}

	public void setCycle(Integer cycle) {
		this.cycle = cycle;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public BigDecimal getServiceInfoScale() {
		return serviceInfoScale;
	}

	public void setServiceInfoScale(BigDecimal serviceInfoScale) {
		this.serviceInfoScale = serviceInfoScale;
	}

	public BigDecimal getServiceLabourScale() {
		return serviceLabourScale;
	}

	public void setServiceLabourScale(BigDecimal serviceLabourScale) {
		this.serviceLabourScale = serviceLabourScale;
	}
	
	public Long getProductDictPriceId() {
		return productDictPriceId;
	}

	public void setProductDictPriceId(Long productDictPriceId) {
		this.productDictPriceId = productDictPriceId;
	}

	
}