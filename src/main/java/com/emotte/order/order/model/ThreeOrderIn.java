package com.emotte.order.order.model;

import java.math.BigDecimal;
import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order
 * 
 * 
 * @author army
 */
public class ThreeOrderIn extends BaseModel {

	private Long id;
	
	private String orderCode;

	private String threeOrderCode;
	
	private String cityCode;
	
	private String cityName;
	
	private String userName;
	
	private String userMobile;
	
	private Long receiverId;
	
	private String receiverName;
	
	private String receiverMobile;
	
	private String receiverProvince;
	
	private String receiverProvinceCode;
	
	private String receiverCity;
	
	private String receiverCityCode;
	
	private String receiverArea;
	
	private String receiverAreaCode;
	
	private String sevicerName;
	
	private String receiverAddress;
	
	private String startTime;
	
	private String endTime;
	
	private Integer cateType;
	
	private String cateTypeName;
	
	private String orderType;
	
	private String createTime;
	
	private BigDecimal totalPay;
	
	private Integer orderStatus;
	
	private String statusText;
	
	private String remark;
	
	private String serverText;
	
	private Integer orderChannel;
	
	private String orderChannelText;
	
	private Long itemId;
	
	private Long detailId;
	
	private Long userId;
	
	private String errormsg;
	
	private String productCode;
	
	private String productName;//商品名称
	
	private String postId;
	
	private Long quantity;
	
	private String receiveTime;
	
	private BigDecimal productPrice;
	
	private Integer orderSourceId;
	
	private String expressCode;
	
	private String fileName;
	
	private String filePath;
	
	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getThreeOrderCode() {
		return threeOrderCode;
	}

	public void setThreeOrderCode(String threeOrderCode) {
		this.threeOrderCode = threeOrderCode;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverProvince() {
		return receiverProvince;
	}

	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverArea() {
		return receiverArea;
	}

	public void setReceiverArea(String receiverArea) {
		this.receiverArea = receiverArea;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Override
	public String getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getCateType() {
		return cateType;
	}

	public void setCateType(Integer cateType) {
		this.cateType = cateType;
	}

	public String getServerText() {
		return serverText;
	}

	public void setServerText(String serverText) {
		this.serverText = serverText;
	}

	public BigDecimal getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(BigDecimal totalPay) {
		this.totalPay = totalPay;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOrderChannel() {
		return orderChannel;
	}

	public void setOrderChannel(Integer orderChannel) {
		this.orderChannel = orderChannel;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getOrderChannelText() {
		return orderChannelText;
	}

	public void setOrderChannelText(String orderChannelText) {
		this.orderChannelText = orderChannelText;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Integer getOrderSourceId() {
		return orderSourceId;
	}

	public void setOrderSourceId(Integer orderSourceId) {
		this.orderSourceId = orderSourceId;
	}

	public String getSevicerName() {
		return sevicerName;
	}

	public void setSevicerName(String sevicerName) {
		this.sevicerName = sevicerName;
	}

	public String getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}

	public String getCateTypeName() {
		return cateTypeName;
	}

	public void setCateTypeName(String cateTypeName) {
		this.cateTypeName = cateTypeName;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getReceiverProvinceCode() {
		return receiverProvinceCode;
	}

	public void setReceiverProvinceCode(String receiverProvinceCode) {
		this.receiverProvinceCode = receiverProvinceCode;
	}

	public String getReceiverCityCode() {
		return receiverCityCode;
	}

	public void setReceiverCityCode(String receiverCityCode) {
		this.receiverCityCode = receiverCityCode;
	}

	public String getReceiverAreaCode() {
		return receiverAreaCode;
	}

	public void setReceiverAreaCode(String receiverAreaCode) {
		this.receiverAreaCode = receiverAreaCode;
	}


}
