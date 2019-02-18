package com.emotte.order.order.model;

import java.math.BigDecimal;

import org.wildhorse.server.core.model.BaseModel;

public class OrderBaseModel extends BaseModel {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Long pid;
    private Long orderId;
    private String code;
    private String orderCode;// 订单ID
    private String ids;
    private String[] idsAry;
    private Long userId;
    private String name;
    private String realName;
    private String shortName;
    private String birth; // 出生日期
    private String sex; // 性别
    private Integer age;// 年龄
    private String solutionCode;
    private String mobile;
    private String phone;
    private String province;
    private String city;
    private String cityCode;
    private String area;
    private String country;
    private String address;
    private String startTime;
    private String endTime;
    private String userAddress; // 用户地址
    private String interviewAddress; // 面试地址
    private String interviewTime; // 面试时间
    private Integer interviewType;// 面试状态
    private String remark; // 备注
    private Integer valid; // 删除标志
    private Integer type; // 类型
    private Integer personLevel; // 等级
    private BigDecimal homeForests;// 居家面积
    private Integer familys; // 居家人口
    private String sendtimeText; // 送贷时间
    private String payText;  // 支付状态
    private String sourceText; // 来源
    private Integer orderStatus; // 订单状态
    private String statusText; // 订单状态值
    private Integer orderType; // 订单类型，服务类型
    private String typeText; // 服务类型
    private Integer critical; // 紧急程度
    private String criticalText; // 紧急程度,value
    private String personLevelText; // 服务人员等级
    private String educationText;// 学历
    private String channelText;//订单渠道
    private Integer personNumber; // 需要的服务人员数量
    private Integer minAge;//要求最小年龄 : min_age
    private Integer maxAge;//要求最大年龄 : max_age
    private Integer education; //学历
    private String nation; // 民族
    private String origin; // 籍贯
    private String originText; // 籍贯
    private String matchMethod;// 服务人员匹配方式 1 erp后台  2 家政家园抢单
    private String xqSex;//需求性别
    private String checkOrderType;
    private String managerMentStatus;
    private String zodiac; // 属相
    private String isMarry; //婚姻状况
    private String idCardNum;//身份证号
    private String workingLife; // 工作年限
    private String salary; // 金额
    private String jobContent; //期望工作内容
    private String specialty; // 特长
    private String fearPet; // 是否怕宠物
    private String staffType; // 是否员工制
    private String workType; // 服务人员工种
    private String workTypeText; // 服务人员工种
    private String workLevel; // 服务人员工种级别
    private String workLevelText; // 服务人员工种级别
    private String productCode;// 商品编码
    private String productName;// 商品名称
    private BigDecimal nowPrice;//商品价格
    private float quantity;// 商品数量
    private String productPriceType; // 商品价格体系
    private String productPriceTypeText; // 商品价格体系名称
    private String productUnit; // 商品单位
    private String productUnitText; // 商品单位名称
    private String productSpec; // 商品规格
    private String productSpecText; // 商品规格名称
    private Order order;
    private String authManagerName;
    private String authManagertPhone;
    private String productCategoryCode;//基础商品分类code
    private String productCategoryName;//基础商品分类名称
    private String originalAddress;//服务人员头像地址
    private String forestsserver;
    private String servicetime;
    private String servicehours;
    private String timeslot;
    private String startTimeSolt;
    private String endTimeSolt;
    private String week;
    private Integer linedType;
    private int condition;//条件
    private int grade;    //订单工种等级
    private String interviewTypeText;
    private String isSchedule;
    private Integer isWorkLevel;
    private String birthTimeOrder;//预产期时间
    private String gradeText;    //订单工种等级文本
    private String orderTypeText;    //订单工种文本
    private Integer minWorkAge; //要求工作最小年龄 : min_age
    private Integer maxWorkAge;//要求工作最大年龄 : max_age
    private String constellation;// 星座
    private String typeCode; //等级code码
    private String levelId; //等级id
    
    
	/**
	 * 是否投保 1：是 2：否  add20181101
	 */
	private Integer insure;

	/**
	 * 投保金额 add 20181130 zhanghao
	 */
	private BigDecimal insureAmount;
	
	/**
	 * 接收人姓名 2019-01-22  周鑫  
	 */
	private String receiverName;
	
	/**
	 * 接受人电话  2019-01-22  周鑫  
	 */
	private String receiverMobile;
	
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}

	public String getGradeText() {
		return gradeText;
	}

	public void setGradeText(String gradeText) {
		this.gradeText = gradeText;
	}

	public String getOrderTypeText() {
		return orderTypeText;
	}

	public void setOrderTypeText(String orderTypeText) {
		this.orderTypeText = orderTypeText;
	}

	public String getInterviewTypeText() {
		return interviewTypeText;
	}

	public void setInterviewTypeText(String interviewTypeText) {
		this.interviewTypeText = interviewTypeText;
	}

	public String getIsSchedule() {
		return isSchedule;
	}

	public void setIsSchedule(String isSchedule) {
		this.isSchedule = isSchedule;
	}

	public Integer getIsWorkLevel() {
		return isWorkLevel;
	}

	public void setIsWorkLevel(Integer isWorkLevel) {
		this.isWorkLevel = isWorkLevel;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getCondition() {
		return condition;
	}

	public void setCondition(int condition) {
		this.condition = condition;
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

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the pid
	 */
	public Long getPid() {
		return pid;
	}

	/**
	 * @param pid the pid to set
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the orderCode
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * @param orderCode the orderCode to set
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * @return the idsAry
	 */
	public String[] getIdsAry() {
		return idsAry;
	}

	/**
	 * @param idsAry the idsAry to set
	 */
	public void setIdsAry(String[] idsAry) {
		this.idsAry = idsAry;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
	 * @return the userAddress
	 */
	public String getUserAddress() {
		return userAddress;
	}

	/**
	 * @param userAddress the userAddress to set
	 */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
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

	public Integer getInterviewType() {
		return interviewType;
	}

	public void setInterviewType(Integer interviewType) {
		this.interviewType = interviewType;
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
	 * @return the valid
	 */
	public Integer getValid() {
		return valid;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
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

	/**
	 * @return the orderStatus
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}

	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
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

	/**
	 * @return the sendtimeText
	 */
	public String getSendtimeText() {
		return sendtimeText;
	}

	/**
	 * @param sendtimeText the sendtimeText to set
	 */
	public void setSendtimeText(String sendtimeText) {
		this.sendtimeText = sendtimeText;
	}

	/**
	 * @return the payText
	 */
	public String getPayText() {
		return payText;
	}

	/**
	 * @param payText the payText to set
	 */
	public void setPayText(String payText) {
		this.payText = payText;
	}

	/**
	 * @return the sourceText
	 */
	public String getSourceText() {
		return sourceText;
	}

	/**
	 * @param sourceText the sourceText to set
	 */
	public void setSourceText(String sourceText) {
		this.sourceText = sourceText;
	}

	/**
	 * @return the statusText
	 */
	public String getStatusText() {
		return statusText;
	}

	/**
	 * @param statusText the statusText to set
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	/**
	 * @return the orderType
	 */

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getTypeText() {
		return typeText;
	}

	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}

	/**
	 * @return the criticalText
	 */
	public String getCriticalText() {
		return criticalText;
	}


	/**
	 * @param criticalText the criticalText to set
	 */
	public void setCriticalText(String criticalText) {
		this.criticalText = criticalText;
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
	 * @return the personLevelText
	 */
	public String getPersonLevelText() {
		return personLevelText;
	}

	/**
	 * @param personLevelText the personLevelText to set
	 */
	public void setPersonLevelText(String personLevelText) {
		this.personLevelText = personLevelText;
	}

	/**
	 * @return the educationText
	 */
	public String getEducationText() {
		return educationText;
	}

	/**
	 * @param educationText the educationText to set
	 */
	public void setEducationText(String educationText) {
		this.educationText = educationText;
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
	 * @return the birth
	 */
	public String getBirth() {
		return birth;
	}

	/**
	 * @param birth the birth to set
	 */
	public void setBirth(String birth) {
		this.birth = birth;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}


	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * @return the education
	 */
	public Integer getEducation() {
		return education;
	}

	/**
	 * @param education the education to set
	 */
	public void setEducation(Integer education) {
		this.education = education;
	}

	/**
	 * @return the nation
	 */
	public String getNation() {
		return nation;
	}

	/**
	 * @param nation the nation to set
	 */
	public void setNation(String nation) {
		this.nation = nation;
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

	public String getOriginText() {
		return originText;
	}

	public void setOriginText(String originText) {
		this.originText = originText;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the zodiac
	 */
	public String getZodiac() {
		return zodiac;
	}

	/**
	 * @param zodiac the zodiac to set
	 */
	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}

	/**
	 * @return the isMarry
	 */
	public String getIsMarry() {
		return isMarry;
	}

	/**
	 * @param isMarry the isMarry to set
	 */
	public void setIsMarry(String isMarry) {
		this.isMarry = isMarry;
	}

	/**
	 * @return the idCardNum
	 */
	public String getIdCardNum() {
		return idCardNum;
	}

	/**
	 * @param idCardNum the idCardNum to set
	 */
	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	/**
	 * @return the workingLife
	 */
	public String getWorkingLife() {
		return workingLife;
	}

	/**
	 * @param workingLife the workingLife to set
	 */
	public void setWorkingLife(String workingLife) {
		this.workingLife = workingLife;
	}

	/**
	 * @return the salary
	 */
	public String getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(String salary) {
		this.salary = salary;
	}

	/**
	 * @return the jobContent
	 */
	public String getJobContent() {
		return jobContent;
	}

	/**
	 * @param jobContent the jobContent to set
	 */
	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}

	/**
	 * @return the specialty
	 */
	public String getSpecialty() {
		return specialty;
	}

	/**
	 * @param specialty the specialty to set
	 */
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	/**
	 * @return the fearPet
	 */
	public String getFearPet() {
		return fearPet;
	}

	/**
	 * @param fearPet the fearPet to set
	 */
	public void setFearPet(String fearPet) {
		this.fearPet = fearPet;
	}

	/**
	 * @return the staffType
	 */
	public String getStaffType() {
		return staffType;
	}

	/**
	 * @param staffType the staffType to set
	 */
	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getWorkTypeText() {
		return workTypeText;
	}

	public void setWorkTypeText(String workTypeText) {
		this.workTypeText = workTypeText;
	}

	public String getWorkLevel() {
		return workLevel;
	}

	public void setWorkLevel(String workLevel) {
		this.workLevel = workLevel;
	}

	public String getWorkLevelText() {
		return workLevelText;
	}

	public void setWorkLevelText(String workLevelText) {
		this.workLevelText = workLevelText;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getChannelText() {
		return channelText;
	}

	public void setChannelText(String channelText) {
		this.channelText = channelText;
	}

	public Integer getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(Integer personNumber) {
		this.personNumber = personNumber;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(BigDecimal nowPrice) {
		this.nowPrice = nowPrice;
	}

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getAuthManagerName() {
		return authManagerName;
	}

	public void setAuthManagerName(String authManagerName) {
		this.authManagerName = authManagerName;
	}

	public String getAuthManagertPhone() {
		return authManagertPhone;
	}

	public void setAuthManagertPhone(String authManagertPhone) {
		this.authManagertPhone = authManagertPhone;
	}

	public String getProductCategoryCode() {
		return productCategoryCode;
	}

	public void setProductCategoryCode(String productCategoryCode) {
		this.productCategoryCode = productCategoryCode;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public String getOriginalAddress() {
		return originalAddress;
	}

	public void setOriginalAddress(String originalAddress) {
		this.originalAddress = originalAddress;
	}

	public String getServicetime() {
		return servicetime;
	}

	public void setServicetime(String servicetime) {
		this.servicetime = servicetime;
	}

	public String getServicehours() {
		return servicehours;
	}

	public void setServicehours(String servicehours) {
		this.servicehours = servicehours;
	}

	public String getTimeslot() {
		return timeslot;
	}

	public void setTimeslot(String timeslot) {
		this.timeslot = timeslot;
	}

	public String getForestsserver() {
		return forestsserver;
	}

	public void setForestsserver(String forestsserver) {
		this.forestsserver = forestsserver;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public Integer getLinedType() {
		return linedType;
	}

	public void setLinedType(Integer linedType) {
		this.linedType = linedType;
	}

	public String getBirthTimeOrder() {
		return birthTimeOrder;
	}

	public void setBirthTimeOrder(String birthTimeOrder) {
		this.birthTimeOrder = birthTimeOrder;
	}

	public String getMatchMethod() {
		return matchMethod;
	}

	public void setMatchMethod(String matchMethod) {
		this.matchMethod = matchMethod;
	}

	public String getCheckOrderType() {
		return checkOrderType;
	}

	public void setCheckOrderType(String checkOrderType) {
		this.checkOrderType = checkOrderType;
	}

	public String getManagerMentStatus() {
		return managerMentStatus;
	}

	public void setManagerMentStatus(String managerMentStatus) {
		this.managerMentStatus = managerMentStatus;
	}

	public String getSolutionCode() {
		return solutionCode;
	}

	public void setSolutionCode(String solutionCode) {
		this.solutionCode = solutionCode;
	}

	public Integer getInsure() {
		return insure;
	}

	public void setInsure(Integer insure) {
		this.insure = insure;
	}

	public String getXqSex() {
		return xqSex;
	}

	public void setXqSex(String xqSex) {
		this.xqSex = xqSex;
	}

	public Integer getMinWorkAge() {
		return minWorkAge;
	}

	public void setMinWorkAge(Integer minWorkAge) {
		this.minWorkAge = minWorkAge;
	}

	public Integer getMaxWorkAge() {
		return maxWorkAge;
	}

	public void setMaxWorkAge(Integer maxWorkAge) {
		this.maxWorkAge = maxWorkAge;
	}

	public String getLevelId() {
		return levelId;
	}

	public BigDecimal getInsureAmount() {
		return insureAmount;
	}

	public void setInsureAmount(BigDecimal insureAmount) {
		this.insureAmount = insureAmount;
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
	
}
