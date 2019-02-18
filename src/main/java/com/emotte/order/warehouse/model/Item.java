package com.emotte.order.warehouse.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order_item
 * 
 * 
 * @author army
 */
public class Item  extends BaseModel{

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
	private Double nowPrice; 
	
			//商品数量 : quantity 	
	private Long quantity; 
	
			//已优惠的价格 : preferential_price 	
	private java.math.BigDecimal preferentialPrice; 
	
			//优惠原因 : preferential_reason 	
	private String preferentialReason; 
	
			// 优惠券兑换码等编�? : voucher_number 	
	private String voucherNumber; 
	
			//�?售类型，1�? 售卖�?2：赠�? : sale_type 	
	private String saleType; 
	
			//活动价格 : activity_price 	
	private java.math.BigDecimal activityPrice; 
	
			//活动id : activity_id 	
	private Long activityId; 
	
			//创建�? : create_by 	
	private Long createBy; 
	
			//创建时间 : create_time 	
	private String createTime; 
	
			//更新�? : update_by 	
	private Long updateBy; 
	
			//更新时间 : update_time 	
	private String updateTime; 
	
			//版本�? : version 	
	private Long version; 
	
			//备注 : remark 	
	private String remark; 
	
	
	private Long receiverId ; //接收人Id
	private Integer services; //服务类型
	
	// 是否有效 1 有效 �? 2 无效 : valid
	private Integer valid;
	
	private Long productInventoryId;
	
	private String productSpec; // 商品规格
	
	private String productPriceType; // 商品价格体系
	private String productPriceTypeText; // 商品价格体系名称
	private String productUnit; // 商品单位
	private String productUnitText; // 商品单位名称
	private String brandName; //辅助属性品牌名字
/**
  * constitution
  *
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
	 * 商品数量 : quantity
	 * 
	 * @return 
	 */
	public Long getQuantity () {
		return quantity;
	}
	
	/**
	 * 商品数量 : quantity
	 * 
	 * @return 
	 */
	public void setQuantity (Long quantity) {
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
	 *  优惠券兑换码等编�? : voucher_number
	 * 
	 * @return 
	 */
	public String getVoucherNumber () {
		return voucherNumber;
	}
	
	/**
	 *  优惠券兑换码等编�? : voucher_number
	 * 
	 * @return 
	 */
	public void setVoucherNumber (String voucherNumber) {
		this.voucherNumber = voucherNumber;
	}
			
	/**
	 * �?售类型，1�? 售卖�?2：赠�? : sale_type
	 * 
	 * @return 
	 */
	public String getSaleType () {
		return saleType;
	}
	
	/**
	 * �?售类型，1�? 售卖�?2：赠�? : sale_type
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
	 * 创建�? : create_by
	 * 
	 * @return 
	 */
	@Override
	public Long getCreateBy () {
		return createBy;
	}
	
	/**
	 * 创建�? : create_by
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
	 * 更新�? : update_by
	 * 
	 * @return 
	 */
	@Override
	public Long getUpdateBy () {
		return updateBy;
	}
	
	/**
	 * 更新�? : update_by
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
	 * 版本�? : version
	 * 
	 * @return 
	 */
	@Override
	public Long getVersion () {
		return version;
	}
	
	/**
	 * 版本�? : version
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
			
	/**
	 * @return the receiverId
	 */
	public Long getReceiverId() {
		return receiverId;
	}

	/**
	 * @param receiverId the receiverId to set
	 */
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	/**
	 * @return the services
	 */
	public Integer getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(Integer services) {
		this.services = services;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public Long getProductInventoryId() {
		return productInventoryId;
	}

	public void setProductInventoryId(Long productInventoryId) {
		this.productInventoryId = productInventoryId;
	}

	public Double getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(Double nowPrice) {
		this.nowPrice = nowPrice;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public String getProductPriceType() {
		return productPriceType;
	}

	public void setProductPriceType(String productPriceType) {
		this.productPriceType = productPriceType;
	}

	public String getProductPriceTypeText() {
		return productPriceTypeText;
	}

	public void setProductPriceTypeText(String productPriceTypeText) {
		this.productPriceTypeText = productPriceTypeText;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public String getProductUnitText() {
		return productUnitText;
	}

	public void setProductUnitText(String productUnitText) {
		this.productUnitText = productUnitText;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	
}
