package com.emotte.order.order.model;

import java.math.BigDecimal;

import org.wildhorse.server.core.model.BaseModel;

public class ThreeOrderItemDetailServer extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//primary key
	private Long id; 
		//t_order_item表id : item_id 	
	private Long itemId; 
		//订单id: order_id 	
	private Long orderId; 
		//商品Id: product_id
	private Long product_id;
		//商品编码 : product_code 	
	private String productCode; 
		//商品名称 : product_name 	
	private String productName; 
		//拣货数量 : pick_quantity 	
	private Float pickQuantity; 
		//结算价 : settle_price
	private java.math.BigDecimal settlePrice; 
		//共用，创建人 : create_by 	
	private Long createBy; 
		//共用，创建时间 : create_time 	
	private String createTime; 
		//共用，更新人 : update_by 	
	private Long updateBy; 
		//共用，更新时间 : update_time 	
	private String updateTime; 
		//共用， : version 	
	private Long version; 
		//延续型订单--学历:1 本科 2，。。。。 : education 	
	private Integer education; 
		//延续型订单--服务人员级别(1，初级2，中级3，高级) : person_level 	
	private Integer personLevel; 
		//延续型订单--附加服务(1.早产儿2.双胞胎) : add_server ，暂时没有使用
	private String addServer; 
		//延续型订单--备注 : remark 	
	private String remark; 
		//延续型订单--要求最小年龄 : min_age 	
	private Integer minAge; 
		//延续型订单--要求最大年龄 : max_age 	
	private Integer maxAge; 
		//延续型订单--籍贯
	private String origin; 
		//延续型订单--服务地址
	private String address; 
		//延续型订单--开始时间
	private String startTime; 
		//延续型订单--结束时间
	private String endTime;
		//延续型订单--面试时间
	private String interviewTime;
		//延续型订单--是否有效(共用)
	private Integer valid;  
		//延续型订单--居家面积
	private BigDecimal homeForests;
		//延续型订单--居家人口数量
	private Integer familys;
		// 订单
	private Long userId;//用户id
	private Long receiverId ; //接收人Id,或者用户Id
		//服务类型--1：服务，2：商品
	private Integer services; 
		//延续型订单--面试地址
	private String interviewAddress;
		//订单类型-- 产品的三级分类以及商品固定值
	private String serverType ; 
		//订单来源渠道
	private String orderChannel;
		//订单类型：1单次服务，2延续服务，3商品订单
	private Integer cateType ; 
		//紧急程度 
	private Integer critical; 
	// 排期
	private String years;
	private String months;
	private String weeks;
	private String days;
	private String hours;
	private int linedType;
	// 总金额 : total_pay
	private java.math.BigDecimal totalPay;
	private String priceType;
	private String productSpec;
	private java.math.BigDecimal nowPrice;
	private String productUnit;
	private String cateCode;
	private String city;
	private String priceSystem;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
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
	public Float getPickQuantity() {
		return pickQuantity;
	}
	public void setPickQuantity(Float pickQuantity) {
		this.pickQuantity = pickQuantity;
	}
	public java.math.BigDecimal getSettlePrice() {
		return settlePrice;
	}
	public void setSettlePrice(java.math.BigDecimal settlePrice) {
		this.settlePrice = settlePrice;
	}
	@Override
	public Long getCreateBy() {
		return createBy;
	}
	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	@Override
	public String getCreateTime() {
		return createTime;
	}
	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public Long getUpdateBy() {
		return updateBy;
	}
	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	@Override
	public String getUpdateTime() {
		return updateTime;
	}
	@Override
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public Long getVersion() {
		return version;
	}
	@Override
	public void setVersion(Long version) {
		this.version = version;
	}
	public Integer getEducation() {
		return education;
	}
	public void setEducation(Integer education) {
		this.education = education;
	}
	public Integer getPersonLevel() {
		return personLevel;
	}
	public void setPersonLevel(Integer personLevel) {
		this.personLevel = personLevel;
	}
	public String getAddServer() {
		return addServer;
	}
	public void setAddServer(String addServer) {
		this.addServer = addServer;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getMinAge() {
		return minAge;
	}
	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}
	public Integer getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getInterviewTime() {
		return interviewTime;
	}
	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public BigDecimal getHomeForests() {
		return homeForests;
	}
	public void setHomeForests(BigDecimal homeForests) {
		this.homeForests = homeForests;
	}
	public Integer getFamilys() {
		return familys;
	}
	public void setFamilys(Integer familys) {
		this.familys = familys;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	public Integer getServices() {
		return services;
	}
	public void setServices(Integer services) {
		this.services = services;
	}
	public String getInterviewAddress() {
		return interviewAddress;
	}
	public void setInterviewAddress(String interviewAddress) {
		this.interviewAddress = interviewAddress;
	}
	public String getServerType() {
		return serverType;
	}
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	public String getOrderChannel() {
		return orderChannel;
	}
	public void setOrderChannel(String orderChannel) {
		this.orderChannel = orderChannel;
	}
	public Integer getCateType() {
		return cateType;
	}
	public void setCateType(Integer cateType) {
		this.cateType = cateType;
	}
	public Integer getCritical() {
		return critical;
	}
	public void setCritical(Integer critical) {
		this.critical = critical;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getMonths() {
		return months;
	}
	public void setMonths(String months) {
		this.months = months;
	}
	public String getWeeks() {
		return weeks;
	}
	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public int getLinedType() {
		return linedType;
	}
	public void setLinedType(int linedType) {
		this.linedType = linedType;
	}
	public java.math.BigDecimal getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(java.math.BigDecimal totalPay) {
		this.totalPay = totalPay;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public String getProductSpec() {
		return productSpec;
	}
	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}
	public java.math.BigDecimal getNowPrice() {
		return nowPrice;
	}
	public void setNowPrice(java.math.BigDecimal nowPrice) {
		this.nowPrice = nowPrice;
	}
	public String getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}
	public String getCateCode() {
		return cateCode;
	}
	public void setCateCode(String cateCode) {
		this.cateCode = cateCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPriceSystem() {
		return priceSystem;
	}
	public void setPriceSystem(String priceSystem) {
		this.priceSystem = priceSystem;
	}
	
}
