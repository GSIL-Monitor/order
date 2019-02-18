package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

public class ItemFaQuery  extends BaseModel{
	private String orderCode;  //订单编号
	private String receiverName;//收货人姓名
	private String receiverMobile;//收货人电话
	private String userMobile;//下单电话
	private String userAddress;//收货地址
	private String createTime;//下单日期
	private String createTimeend;
	private String recetptTime;//配送日期
	private String recetptTimeend;
	private String productName;//商品名称
	private String quantity;//数量
	private String productSpec;//规格
	private String orderChannel;//订单渠道
	private String name;//下单部门
	private String orderSourceId;//订单来源
	private String remark;//备注
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverMobile() {
		return receiverMobile;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	@Override
	public String getCreateTime() {
		return createTime;
	}
	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRecetptTime() {
		return recetptTime;
	}
	public void setRecetptTime(String recetptTime) {
		this.recetptTime = recetptTime;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getProductSpec() {
		return productSpec;
	}
	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}
	public String getOrderChannel() {
		return orderChannel;
	}
	public void setOrderChannel(String orderChannel) {
		this.orderChannel = orderChannel;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrderSourceId() {
		return orderSourceId;
	}
	public void setOrderSourceId(String orderSourceId) {
		this.orderSourceId = orderSourceId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreateTimeend() {
		return createTimeend;
	}
	public void setCreateTimeend(String createTimeend) {
		this.createTimeend = createTimeend;
	}
	public String getRecetptTimeend() {
		return recetptTimeend;
	}
	public void setRecetptTimeend(String recetptTimeend) {
		this.recetptTimeend = recetptTimeend;
	}
	
}
