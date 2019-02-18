package com.emotte.order.order.model;

import java.math.BigDecimal;
import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order_item_detail_server
 *
 *
 * @author army
 */
public class ItemDetailServer  extends BaseModel{

		// : id
	private Long id;

			//t_order_item表id : item_id
	private Long itemId;

			//商品id : product_id
	private Long orderId;

			//商品编码 : product_code
	private String productCode;

			//商品名称 : product_name
	private String productName;

	private String productPriceType; // 商品价格体系
	private String productPriceTypeText; // 商品价格体系名称
	private String productUnit; // 商品单位
	private String productUnitText; // 商品单位名称
	private String productSpec; // 商品规格
	private String productSpecText; // 商品规格名称

			//拣货数量 : pick_quantity
	private Float pickQuantity;

			//结算价 : settle_price
	private java.math.BigDecimal settlePrice;

	//商品价格 : T_ORDER_ITEM表now_price字段
	private java.math.BigDecimal nowPrice;

			//创建人 : create_by
	private Long createBy;

	private Long deptId; // 部门Id

			//创建时间 : create_time
	private String createTime;

			//更新人 : update_by
	private Long updateBy;

			//更新时间 : update_time
	private String updateTime;

			// : version
	private Long version;

			//学历:1 本科 2，。。。。 : education
	private String education;

			//服务人员级别(1，初级2，中级3，高级) : person_level
	private Integer personLevel;

			//附加服务(1.早产儿2.双胞胎) : add_server
	private String addServer;

			//备注 : remark
	private String remark;

			//要求最小年龄 : min_age
	private Integer minAge;

			//要求最大年龄 : max_age
	private Integer maxAge;

	private String origin; // 籍贯
	private String address; // 服务地址
	private String startTime;
	private String endTime;
	private String interviewTime;
	private String birthDate;//宝宝出生日期
	private Integer valid;  // 是否有效
	private BigDecimal homeForests; // 居家面积
	private Integer familys; // 居家人口数量
	private String sex;//性别
	// 订单
	private Long userId;//用户id
	private Long receiverId ; //接收人Id,或者用户Id
	private String services; //服务类型
	private String interviewAddress; // 面试地址
	private String serverType ; // 服务类型 orderType
	private Integer cateType ; // 订单类型：1单次服务，2延续服务，3商品订单
	private Integer critical; //紧急程度
	private String province;
	private String city;
	private String country;
	private String cityCode;
	private String longitude;
	private String latitude;
	private String priceType;// 商品价格体系
	private String orderChannel;// 订单渠道
	private Integer personNumber;// 需要服务人员数量
	// 排期
	private String years;
	private String months;
	private String weeks;
	private String days;
	private String hours;
	private int linedType;
	//服务人员排期字段
	private String startDate;
	private String endDate;
	private String startLineTime;
	private String endLineTime;
	private String userName;//用户名
	private String orderFenfa;  //分发方式
	private Long rechargeBy;//负责人
	private Long rechargeDept;//负责人部门
	private Long emp_id;//服务人员id
	private String[] personId;
	private String selectTime;//所选时间段
	private String timeServer;//单人服务时长
	private String serviceHours;//服务时长
	private String timeSlot;//服务时间段
	private String forestsServer;//服务面积
	private String[] weeky;
	private String startTimeSolt;
	private String endTimeSolt;
	private Integer markType;//排期标记类型
	private String ifKunCun;//是否走库存
	private String threeOrderCode;
	private String serviceObject;//服务对象  (1  企业   2  个人)
	
	public String getServiceObject() {
		return serviceObject;
	}

	public void setServiceObject(String serviceObject) {
		this.serviceObject = serviceObject;
	}

	/**
	 * 是否投保 1：是 2：否  add20181101
	 */
	private Integer insure;

	/**
	 * 投保金额
	 */
	private BigDecimal insureAmount;

	/**
	 *  : id
	 *
	 * @return
	 */
	public Long getId () {
		return id;
	}

	/**
	 *  : id
	 *
	 * @return
	 */
	public void setId (Long id) {
		this.id = id;
	}

	/**
	 * t_order_item表id : item_id
	 *
	 * @return
	 */
	public Long getItemId () {
		return itemId;
	}

	/**
	 * t_order_item表id : item_id
	 *
	 * @return
	 */
	public void setItemId (Long itemId) {
		this.itemId = itemId;
	}

	/**
	 * 商品id : order_id
	 *
	 * @return
	 */
	public Long getOrderId () {
		return orderId;
	}

	/**
	 * 商品id : order_id
	 *
	 * @return
	 */
	public void setOrderId (Long orderId) {
		this.orderId = orderId;
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

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public String getProductSpecText() {
		return productSpecText;
	}

	public void setProductSpecText(String productSpecText) {
		this.productSpecText = productSpecText;
	}

	/**
	 * 拣货数量 : pick_quantity
	 *
	 * @return
	 */
	public Float getPickQuantity () {
		return pickQuantity;
	}

	/**
	 * 拣货数量 : pick_quantity
	 *
	 * @return
	 */
	public void setPickQuantity (Float pickQuantity) {
		this.pickQuantity = pickQuantity;
	}


	/**
	 *  : version
	 *
	 * @return
	 */
	@Override
	public Long getVersion () {
		return version;
	}

	/**
	 *  : version
	 *
	 * @return
	 */
	@Override
	public void setVersion (Long version) {
		this.version = version;
	}

	/**
	 * 结算价 : settle_price
	 *
	 * @return
	 */
	public java.math.BigDecimal getSettlePrice () {
		return settlePrice;
	}

	/**
	 * 结算价 : settle_price
	 *
	 * @return
	 */
	public void setSettlePrice (java.math.BigDecimal settlePrice) {
		this.settlePrice = settlePrice;
	}

	/**
	 * @return the createBy
	 */
	@Override
	public Long getCreateBy() {
		return createBy;
	}

	/**
	 * @param createBy the createBy to set
	 */
	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the createTime
	 */
	@Override
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateBy
	 */
	@Override
	public Long getUpdateBy() {
		return updateBy;
	}

	/**
	 * @param updateBy the updateBy to set
	 */
	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * @return the updateTime
	 */
	@Override
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	@Override
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * @param education the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * @return the personLevel
	 */
	public Integer getPersonLevel() {
		return personLevel;
	}

	/**
	 * @param personLevel the personLevel to set
	 */
	public void setPersonLevel(Integer personLevel) {
		this.personLevel = personLevel;
	}

	/**
	 * @return the addServer
	 */
	public String getAddServer() {
		return addServer;
	}

	/**
	 * @param addServer the addServer to set
	 */
	public void setAddServer(String addServer) {
		this.addServer = addServer;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the minAge
	 */
	public Integer getMinAge() {
		return minAge;
	}

	/**
	 * @param minAge the minAge to set
	 */
	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	/**
	 * @return the maxAge
	 */
	public Integer getMaxAge() {
		return maxAge;
	}

	/**
	 * @param maxAge the maxAge to set
	 */
	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	/**
	 * @return the origin
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * @param origin the origin to set
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the interviewTime
	 */
	public String getInterviewTime() {
		return interviewTime;
	}

	/**
	 * @param interviewTime the interviewTime to set
	 */
	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}

	/**
	 * @return the valid
	 */
	public Integer getValid() {
		return valid;
	}

	/**
	 * @param valid the valid to set
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
	}

	/**
	 * @return the homeForests
	 */
	public BigDecimal getHomeForests() {
		return homeForests;
	}

	/**
	 * @param homeForests the homeForests to set
	 */
	public void setHomeForests(BigDecimal homeForests) {
		this.homeForests = homeForests;
	}

	/**
	 * @return the familys
	 */
	public Integer getFamilys() {
		return familys;
	}

	/**
	 * @param familys the familys to set
	 */
	public void setFamilys(Integer familys) {
		this.familys = familys;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public String getServices() {
		return services;
	}

	/**
	 * @param services the services to set
	 */
	public void setServices(String services) {
		this.services = services;
	}

	/**
	 * @return the interviewAddress
	 */
	public String getInterviewAddress() {
		return interviewAddress;
	}

	/**
	 * @param interviewAddress the interviewAddress to set
	 */
	public void setInterviewAddress(String interviewAddress) {
		this.interviewAddress = interviewAddress;
	}


	/**
	 * @return the cateType
	 */
	public Integer getCateType() {
		return cateType;
	}

	/**
	 * @param cateType the cateType to set
	 */
	public void setCateType(Integer cateType) {
		this.cateType = cateType;
	}

	/**
	 * @return the critical
	 */
	public Integer getCritical() {
		return critical;
	}

	/**
	 * @param critical the critical to set
	 */
	public void setCritical(Integer critical) {
		this.critical = critical;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getOrderChannel() {
		return orderChannel;
	}

	public void setOrderChannel(String orderChannel) {
		this.orderChannel = orderChannel;
	}

	public Integer getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(Integer personNumber) {
		this.personNumber = personNumber;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the years
	 */
	public String getYears() {
		return years;
	}

	/**
	 * @param years the years to set
	 */
	public void setYears(String years) {
		this.years = years;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return the months
	 */
	public String getMonths() {
		return months;
	}

	/**
	 * @param months the months to set
	 */
	public void setMonths(String months) {
		this.months = months;
	}

	/**
	 * @return the weeks
	 */
	public String getWeeks() {
		return weeks;
	}

	/**
	 * @param weeks the weeks to set
	 */
	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	/**
	 * @return the days
	 */
	public String getDays() {
		return days;
	}

	/**
	 * @param days the days to set
	 */
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

	public String getServerType() {
		return serverType;
	}

	public void setServerType(String serverType) {
		this.serverType = serverType;
	}

	public java.math.BigDecimal getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(java.math.BigDecimal nowPrice) {
		this.nowPrice = nowPrice;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrderFenfa() {
		return orderFenfa;
	}

	public void setOrderFenfa(String orderFenfa) {
		this.orderFenfa = orderFenfa;
	}

	public Long getRechargeBy() {
		return rechargeBy;
	}

	public void setRechargeBy(Long rechargeBy) {
		this.rechargeBy = rechargeBy;
	}

	public Long getRechargeDept() {
		return rechargeDept;
	}

	public void setRechargeDept(Long rechargeDept) {
		this.rechargeDept = rechargeDept;
	}

	public Long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getTimeServer() {
		return timeServer;
	}

	public void setTimeServer(String timeServer) {
		this.timeServer = timeServer;
	}

	public String getServiceHours() {
		return serviceHours;
	}

	public void setServiceHours(String serviceHours) {
		this.serviceHours = serviceHours;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public String getSelectTime() {
		return selectTime;
	}

	public void setSelectTime(String selectTime) {
		this.selectTime = selectTime;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStartLineTime() {
		return startLineTime;
	}

	public void setStartLineTime(String startLineTime) {
		this.startLineTime = startLineTime;
	}

	public String getEndLineTime() {
		return endLineTime;
	}

	public void setEndLineTime(String endLineTime) {
		this.endLineTime = endLineTime;
	}

	public String[] getPersonId() {
		return personId;
	}

	public void setPersonId(String[] personId) {
		this.personId = personId;
	}

	public String[] getWeeky() {
		return weeky;
	}

	public void setWeeky(String[] weeky) {
		this.weeky = weeky;
	}

	public String getStartTimeSolt() {
		return startTimeSolt;
	}

	public void setStartTimeSolt(String startTimeSolt) {
		this.startTimeSolt = startTimeSolt;
	}

	public String getEndTimeSolt() {
		return endTimeSolt;
	}

	public void setEndTimeSolt(String endTimeSolt) {
		this.endTimeSolt = endTimeSolt;
	}

	public String getForestsServer() {
		return forestsServer;
	}

	public void setForestsServer(String forestsServer) {
		this.forestsServer = forestsServer;
	}

	public Integer getMarkType() {
		return markType;
	}

	public void setMarkType(Integer markType) {
		this.markType = markType;
	}

	public String getIfKunCun() {
		return ifKunCun;
	}

	public void setIfKunCun(String ifKunCun) {
		this.ifKunCun = ifKunCun;
	}

	public String getThreeOrderCode() {
		return threeOrderCode;
	}

	public void setThreeOrderCode(String threeOrderCode) {
		this.threeOrderCode = threeOrderCode;
	}

	public Integer getInsure() {
		return insure;
	}

	public void setInsure(Integer insure) {
		this.insure = insure;
	}

	public BigDecimal getInsureAmount() {
		return insureAmount;
	}

	public void setInsureAmount(BigDecimal insureAmount) {
		this.insureAmount = insureAmount;
	}
}
