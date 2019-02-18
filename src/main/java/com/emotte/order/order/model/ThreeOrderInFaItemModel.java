package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

public class ThreeOrderInFaItemModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//主键 : id 	
	private Long id; 
	
	//订单id : order_id 	
	private Long orderId; 
	
	//商品分类code
	private String categoryCode;
	
	//商品编码 : product_code 	
	private String productCode; 
	
	//商品名称 : product_name 	
	private String productName; 
	
	//商品价格 : now_price 	
	private java.math.BigDecimal nowPrice; 
	
	//商品数量 : quantity 	
	private Integer quantity; 
	
	//已优惠的价格 : preferential_price 	
	private java.math.BigDecimal preferentialPrice; 
	
	//优惠原因 : preferential_reason 	
	private String preferentialReason; 
	
	// 优惠券兑换码等编号 : voucher_number 	
	private String voucherNumber; 
	
	//销售类型，1： 售卖，2：赠送 : sale_type 	
	private String saleType; 
	
	//活动价格 : activity_price 	
	private java.math.BigDecimal activityPrice; 
	
	//活动id : activity_id 	
	private Long activityId; 
	
	//创建人 : create_by 	
	private Long createBy; 
	
	//创建时间 : create_time 	
	private String createTime; 
	
	//更新人 : update_by 	
	private Long updateBy; 
	
	//更新时间 : update_time 	
	private String updateTime; 
	
	//版本号 : version 	
	private Long version; 
	
	//备注 : remark 	
	private String remark; 
	
	// 是否有效 1 有效 ， 2 无效 : valid
	private Integer valid;
	
	//商品图片
	private String product_img;
	
	private String productPriceType;
	
	private String productSpec;
	
	private String productUnit;
	/**
	 * constitution
	 */
	/**
	 * 主键 : id
	 * 
	 * @return 
	 */
	public Long getId () {
		return id;
	}
	
	/**
	 * 主键 : id
	 * 
	 * @return 
	 */
	public void setId (Long id) {
		this.id = id;
	}
			
	/**
	 * 订单id : order_id
	 * 
	 * @return 
	 */
	public Long getOrderId () {
		return orderId;
	}
	
	/**
	 * 订单id : order_id
	 * 
	 * @return 
	 */
	public void setOrderId (Long orderId) {
		this.orderId = orderId;
	}
			
	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/**
	 * 商品编码 : product_code
	 * 
	 * @return 
	 */
	public String getProductCode () {
		return productCode;
	}
	
	/**
	 * 商品编码 : product_code
	 * 
	 * @return 
	 */
	public void setProductCode (String productCode) {
		this.productCode = productCode;
	}
			
	/**
	 * 商品名称 : product_name
	 * 
	 * @return 
	 */
	public String getProductName () {
		return productName;
	}
	
	/**
	 * 商品名称 : product_name
	 * 
	 * @return 
	 */
	public void setProductName (String productName) {
		this.productName = productName;
	}
			
	/**
	 * 商品价格 : now_price
	 * 
	 * @return 
	 */
	public java.math.BigDecimal getNowPrice () {
		return nowPrice;
	}
	
	/**
	 * 商品价格 : now_price
	 * 
	 * @return 
	 */
	public void setNowPrice (java.math.BigDecimal nowPrice) {
		this.nowPrice = nowPrice;
	}
			
	/**
	 * 商品数量 : quantity
	 * 
	 * @return 
	 */
	public Integer getQuantity () {
		return quantity;
	}
	
	/**
	 * 商品数量 : quantity
	 * 
	 * @return 
	 */
	public void setQuantity (Integer quantity) {
		this.quantity = quantity;
	}
			
	/**
	 * 已优惠的价格 : preferential_price
	 * 
	 * @return 
	 */
	public java.math.BigDecimal getPreferentialPrice () {
		return preferentialPrice;
	}
	
	/**
	 * 已优惠的价格 : preferential_price
	 * 
	 * @return 
	 */
	public void setPreferentialPrice (java.math.BigDecimal preferentialPrice) {
		this.preferentialPrice = preferentialPrice;
	}
			
	/**
	 * 优惠原因 : preferential_reason
	 * 
	 * @return 
	 */
	public String getPreferentialReason () {
		return preferentialReason;
	}
	
	/**
	 * 优惠原因 : preferential_reason
	 * 
	 * @return 
	 */
	public void setPreferentialReason (String preferentialReason) {
		this.preferentialReason = preferentialReason;
	}
			
	/**
	 *  优惠券兑换码等编号 : voucher_number
	 * 
	 * @return 
	 */
	public String getVoucherNumber () {
		return voucherNumber;
	}
	
	/**
	 *  优惠券兑换码等编号 : voucher_number
	 * 
	 * @return 
	 */
	public void setVoucherNumber (String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
			
	/**
	 * 销售类型，1： 售卖，2：赠送 : sale_type
	 * 
	 * @return 
	 */
	public String getSaleType () {
		return saleType;
	}
	
	/**
	 * 销售类型，1： 售卖，2：赠送 : sale_type
	 * 
	 * @return 
	 */
	public void setSaleType (String saleType) {
		this.saleType = saleType;
	}
			
	/**
	 * 活动价格 : activity_price
	 * 
	 * @return 
	 */
	public java.math.BigDecimal getActivityPrice () {
		return activityPrice;
	}
	
	/**
	 * 活动价格 : activity_price
	 * 
	 * @return 
	 */
	public void setActivityPrice (java.math.BigDecimal activityPrice) {
		this.activityPrice = activityPrice;
	}
			
	/**
	 * 活动id : activity_id
	 * 
	 * @return 
	 */
	public Long getActivityId () {
		return activityId;
	}
	
	/**
	 * 活动id : activity_id
	 * 
	 * @return 
	 */
	public void setActivityId (Long activityId) {
		this.activityId = activityId;
	}
			
	/**
	 * 创建人 : create_by
	 * 
	 * @return 
	 */
	@Override
	public Long getCreateBy () {
		return createBy;
	}
	
	/**
	 * 创建人 : create_by
	 * 
	 * @return 
	 */
	@Override
	public void setCreateBy (Long createBy) {
		this.createBy = createBy;
	}
			
	/**
	 * 创建时间 : create_time
	 * 
	 * @return 
	 */
	@Override
	public String getCreateTime () {
		return createTime;
	}
	
	/**
	 * 创建时间 : create_time
	 * 
	 * @return 
	 */
	@Override
	public void setCreateTime (String createTime) {
		this.createTime = createTime;
	}
			
	/**
	 * 更新人 : update_by
	 * 
	 * @return 
	 */
	@Override
	public Long getUpdateBy () {
		return updateBy;
	}
	
	/**
	 * 更新人 : update_by
	 * 
	 * @return 
	 */
	@Override
	public void setUpdateBy (Long updateBy) {
		this.updateBy = updateBy;
	}
			
	/**
	 * 更新时间 : update_time
	 * 
	 * @return 
	 */
	@Override
	public String getUpdateTime () {
		return updateTime;
	}
	
	/**
	 * 更新时间 : update_time
	 * 
	 * @return 
	 */
	@Override
	public void setUpdateTime (String updateTime) {
		this.updateTime = updateTime;
	}
			
	/**
	 * 版本号 : version
	 * 
	 * @return 
	 */
	@Override
	public Long getVersion () {
		return version;
	}
	
	/**
	 * 版本号 : version
	 * 
	 * @return 
	 */
	@Override
	public void setVersion (Long version) {
		this.version = version;
	}
	/**
	 * 备注 : remark
	 * 
	 * @return 
	 */
	public String getRemark () {
		return remark;
	}
	
	/**
	 * 备注 : remark
	 * 
	 * @return 
	 */
	public void setRemark (String remark) {
		this.remark = remark;
	}
			
	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public String getProduct_img() {
		return product_img;
	}

	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}

	public String getProductPriceType() {
		return productPriceType;
	}

	public void setProductPriceType(String productPriceType) {
		this.productPriceType = productPriceType;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	
	
}
