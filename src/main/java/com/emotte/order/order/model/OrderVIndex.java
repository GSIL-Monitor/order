package com.emotte.order.order.model;

public class OrderVIndex {
	//订单
	private Long id; 
	//对方订单ID
	private String oId;  
	//对方订单渠道
	private String oChannel;  
	//用户姓名
	private String uName;
	//用户电话
	private String uMobile;
	//用户性别
	private String uSex;
	//用户省份
	private String uProvince;
	//用户城市
	private String uCity;
	//用户区县
	private String uStrict;
	//用户详细地址
	private String uAddress;
	//用户经度
	private String uLongitude;
	//用户纬度
	private String uLatitude;
	//订单渠道
	private String channel;
	//订单类别(1.自营单次 2.自营延续 3.自营商品 4.他营单次)
	private Integer cateType;
	//接收人名称
	private String rName;
	//接收人电话
	private String rMobile;
	//接收人省份
	private String rProvince;
	//接收人城市
	private String rCity;
	//接收人区县
	private String rStrict;
	//接收人详细地址
	private String rAddress;
	//接收人经度
	private String rLongitude;	
	//接收人纬度
	private String rLatitude;
	//总金额（包含运费）
	private java.math.BigDecimal allFee;
	//订单运费
	private java.math.BigDecimal deliverFee;
	//商品价格体系(市场价，解决方案价)
	private Integer productPrice;
	//商品编码
	private String productCode;
	//商品名称
	private String productName;
	//商品价格
	private java.math.BigDecimal nowPrice;
	//商品数量
	private java.math.BigDecimal quantity;
	//缴费类型(1:定金，2:押金，3:预收，4:收入)
	private Integer payType;
	// 创建时间 : create_time
	private String createTime;
	//订单备注
	private String oRemark;
	//微信支付流水号
	private String weixinNum;
	//开始服务时间
	private String startTime;
	//结束服务时间
	private String endTime;
	//配送时间 
	private String receiptTime;
	//订单状态
	private String oStatus;
	//是否是售后处理 1是 2不是
	private Integer chargeback;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getoId() {
		return oId;
	}
	public void setoId(String oId) {
		this.oId = oId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuMobile() {
		return uMobile;
	}
	public void setuMobile(String uMobile) {
		this.uMobile = uMobile;
	}
	public String getuSex() {
		return uSex;
	}
	public void setuSex(String uSex) {
		this.uSex = uSex;
	}
	public String getuProvince() {
		return uProvince;
	}
	public void setuProvince(String uProvince) {
		this.uProvince = uProvince;
	}
	public String getuCity() {
		return uCity;
	}
	public void setuCity(String uCity) {
		this.uCity = uCity;
	}
	public String getuStrict() {
		return uStrict;
	}
	public void setuStrict(String uStrict) {
		this.uStrict = uStrict;
	}
	public String getuAddress() {
		return uAddress;
	}
	public void setuAddress(String uAddress) {
		this.uAddress = uAddress;
	}
	public String getuLongitude() {
		return uLongitude;
	}
	public void setuLongitude(String uLongitude) {
		this.uLongitude = uLongitude;
	}
	public String getuLatitude() {
		return uLatitude;
	}
	public void setuLatitude(String uLatitude) {
		this.uLatitude = uLatitude;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Integer getCateType() {
		return cateType;
	}
	public void setCateType(Integer cateType) {
		this.cateType = cateType;
	}
	public String getrName() {
		return rName;
	}
	public void setrName(String rName) {
		this.rName = rName;
	}
	public String getrMobile() {
		return rMobile;
	}
	public void setrMobile(String rMobile) {
		this.rMobile = rMobile;
	}
	public String getrProvince() {
		return rProvince;
	}
	public void setrProvince(String rProvince) {
		this.rProvince = rProvince;
	}
	public String getrCity() {
		return rCity;
	}
	public void setrCity(String rCity) {
		this.rCity = rCity;
	}
	public String getrStrict() {
		return rStrict;
	}
	public void setrStrict(String rStrict) {
		this.rStrict = rStrict;
	}
	public String getrAddress() {
		return rAddress;
	}
	public void setrAddress(String rAddress) {
		this.rAddress = rAddress;
	}
	public String getrLongitude() {
		return rLongitude;
	}
	public void setrLongitude(String rLongitude) {
		this.rLongitude = rLongitude;
	}
	public String getrLatitude() {
		return rLatitude;
	}
	public void setrLatitude(String rLatitude) {
		this.rLatitude = rLatitude;
	}
	public java.math.BigDecimal getAllFee() {
		return allFee;
	}
	public void setAllFee(java.math.BigDecimal allFee) {
		this.allFee = allFee;
	}
	public java.math.BigDecimal getDeliverFee() {
		return deliverFee;
	}
	public void setDeliverFee(java.math.BigDecimal deliverFee) {
		this.deliverFee = deliverFee;
	}
	public Integer getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
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
	public java.math.BigDecimal getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(java.math.BigDecimal nowPrice) {
		this.nowPrice = nowPrice;
	}
	public java.math.BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(java.math.BigDecimal quantity) {
		this.quantity = quantity;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getoRemark() {
		return oRemark;
	}
	public void setoRemark(String oRemark) {
		this.oRemark = oRemark;
	}
	public String getWeixinNum() {
		return weixinNum;
	}
	public void setWeixinNum(String weixinNum) {
		this.weixinNum = weixinNum;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getReceiptTime() {
		return receiptTime;
	}
	public void setReceiptTime(String receiptTime) {
		this.receiptTime = receiptTime;
	}
	public String getoChannel() {
		return oChannel;
	}
	public void setoChannel(String oChannel) {
		this.oChannel = oChannel;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getoStatus() {
		return oStatus;
	}
	public void setoStatus(String oStatus) {
		this.oStatus = oStatus;
	}
	public Integer getChargeback() {
		return chargeback;
	}
	public void setChargeback(Integer chargeback) {
		this.chargeback = chargeback;
	}
    
	
	
}
