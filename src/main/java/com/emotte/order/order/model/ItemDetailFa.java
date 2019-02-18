package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order_item_detail_fa
 * 
 * 
 * @author army
 */
public class ItemDetailFa extends BaseModel {

	// : id
	private Long id;

	// 订单项目ID : order_item_id
	private Long orderItemId;

	// 拣货数量 : pick_quantity
	private Long pickQuantity;

	// 快递单号 : express_code
	private String expressCode;

	// 送货人电话 : delivery_phone
	private String deliveryPhone;

	// 送货人 : delivery_name
	private String deliveryName;

	// 发货时间 : delivery_time
	private java.util.Date deliveryTime;

	// 签收时间 : recive_time
	private java.util.Date reciveTime;

	// 创建人 : create_by
	private Long createBy;

	// 创建时间 : create_time
	private String createTime;

	// 更新人 : update_by
	private Long updateBy;

	// 更新时间 : update_time
	private String updateTime;

	// 版本号 : version
	private Long version;

	// 备注 : remarks
	private String remarks;

	// 商品仓库id : product_inventory_id
	private Long productInventoryId;

	/**
	 * constitution
	 *
	 */

	/**
	 * : id
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * : id
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 订单项目ID : order_item_id
	 * 
	 * @return
	 */
	public Long getOrderItemId() {
		return orderItemId;
	}

	/**
	 * 订单项目ID : order_item_id
	 * 
	 * @return
	 */
	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	/**
	 * 拣货数量 : pick_quantity
	 * 
	 * @return
	 */
	public Long getPickQuantity() {
		return pickQuantity;
	}

	/**
	 * 拣货数量 : pick_quantity
	 * 
	 * @return
	 */
	public void setPickQuantity(Long pickQuantity) {
		this.pickQuantity = pickQuantity;
	}

	/**
	 * 快递单号 : express_code
	 * 
	 * @return
	 */
	public String getExpressCode() {
		return expressCode;
	}

	/**
	 * 快递单号 : express_code
	 * 
	 * @return
	 */
	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}

	/**
	 * 送货人电话 : delivery_phone
	 * 
	 * @return
	 */
	public String getDeliveryPhone() {
		return deliveryPhone;
	}

	/**
	 * 送货人电话 : delivery_phone
	 * 
	 * @return
	 */
	public void setDeliveryPhone(String deliveryPhone) {
		this.deliveryPhone = deliveryPhone;
	}

	/**
	 * 送货人 : delivery_name
	 * 
	 * @return
	 */
	public String getDeliveryName() {
		return deliveryName;
	}

	/**
	 * 送货人 : delivery_name
	 * 
	 * @return
	 */
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	/**
	 * 发货时间 : delivery_time
	 * 
	 * @return
	 */
	public java.util.Date getDeliveryTime() {
		return deliveryTime;
	}

	/**
	 * 发货时间 : delivery_time
	 * 
	 * @return
	 */
	public void setDeliveryTime(java.util.Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	/**
	 * 签收时间 : recive_time
	 * 
	 * @return
	 */
	public java.util.Date getReciveTime() {
		return reciveTime;
	}

	/**
	 * 签收时间 : recive_time
	 * 
	 * @return
	 */
	public void setReciveTime(java.util.Date reciveTime) {
		this.reciveTime = reciveTime;
	}

	/**
	 * 创建人 : create_by
	 * 
	 * @return
	 */
	@Override
	public Long getCreateBy() {
		return createBy;
	}

	/**
	 * 创建人 : create_by
	 * 
	 * @return
	 */
	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	/**
	 * 创建时间 : create_time
	 * 
	 * @return
	 */
	@Override
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间 : create_time
	 * 
	 * @return
	 */
	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新人 : update_by
	 * 
	 * @return
	 */
	@Override
	public Long getUpdateBy() {
		return updateBy;
	}

	/**
	 * 更新人 : update_by
	 * 
	 * @return
	 */
	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * 更新时间 : update_time
	 * 
	 * @return
	 */
	@Override
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间 : update_time
	 * 
	 * @return
	 */
	@Override
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 版本号 : version
	 * 
	 * @return
	 */
	@Override
	public Long getVersion() {
		return version;
	}

	/**
	 * 版本号 : version
	 * 
	 * @return
	 */
	@Override
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * 备注 : remarks
	 * 
	 * @return
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 备注 : remarks
	 * 
	 * @return
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 商品仓库id : product_inventory_id
	 * 
	 * @return
	 */
	public Long getProductInventoryId() {
		return productInventoryId;
	}

	/**
	 * 商品仓库id : product_inventory_id
	 * 
	 * @return
	 */
	public void setProductInventoryId(Long productInventoryId) {
		this.productInventoryId = productInventoryId;
	}
}
