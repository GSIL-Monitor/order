package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : T_EMP_PERSONNEL
 */
public class Personnel extends BaseModel {

 	// id : ID
	private Long id;
	
	
 	// 姓名 : NAME
	private String name;
	
	
 	// 1 女，2 男 : SEX
	private Long sex;
	
	
 	// 年龄 : AGE
	private String age;
	
	
 	// 籍贯 : ORIGIN
	private String origin;
	
	
 	// 属相 : ZODIAC
	private String zodiac;
	
	
 	// 身份证号 : ID_CARD_NUM
	private String idCardNum;
	
	
 	// 普通话 : MANDARIN
	private String mandarin;
	
	
 	// 民族 : NATION
	private String nation;
	
	
 	// 联系方式（手机） : MOBILE
	private String mobile;
	
	
 	// 1 未婚 ， 2 已婚 : IS_MARRY
	private Long isMarry;
	
	
 	// . : USUAL_LNG
	private String usualLng;
	
	
 	// . : USUAL_LAT
	private String usualLat;
	
	
 	// 现在住址 : CURRENT_ADDRESS
	private String currentAddress;
	
	
 	// 原籍住址 : HOME_ADDRESS
	private String homeAddress;
	
	
 	// 1无学历，2 小学 ，3 初中，4高中/中专 ，5 本科/大专， 6.研究生 : EDUCATION
	private Long education;
	
	
 	// 紧急联系（电话） : EMERGENCY_CONTACT_PHONE
	private String emergencyContactPhone;
	
	
 	// 1不住家 ，2 住家 : IS_HOME
	private Long isHome;
	
	
 	// 从事家政工作年限 : WORKING_LIFE
	private String workingLife;
	
	
 	// 1 健康证，2 检验证明 ，3 已过期，4未办过 : HEALTH_CERTIFICATE
	private Long healthCertificate;
	
	
 	// 1 不怕，2 害怕 : FEAR_PET
	private Long fearPet;
	
	
 	// 期望薪资 : SALARY_EXPECTATION
	private String salaryExpectation;
	
	
 	// 工作期望内容 : JOB_EXPECTATION_CONTENT
	private String jobExpectationContent;
	
	
 	// 特长 : SPECIALTY
	private String specialty;
	
	
 	// 1 未删除 ， 2 已删除 : VALID
	private Long valid;
	
	
 	// 是否到校 1已到校 2 未到校 : IS_INSCHOOL
	private Long isInschool;
	
	
 	// 预约到校时间 : APPOINTMENT_SCHOOL_TIME
	private java.util.Date appointmentSchoolTime;
	
	
 	// 一级渠道 1 学校  2 基地  3 呼叫中心 4合伙人 : L1_CHANNEL
	private String l1Channel;
	
	
 	// 二级渠道 1 58同城  2 赶集网  3 百姓网 : L2_CHANNEL
	private Long l2Channel;
	
	
 	// 三级渠道 操作人员外键 : L3_CHANNEL
	private Long l3Channel;
	
	
 	// 创建人 : CREATE_BY
	private Long createBy;
	
	
 	// 修改人 : UPDATE_BY
	private Long updateBy;
	
	
	
	
 	// 产品管家id : PRODUCTID
	private Long productid;
	
	
 	// 隶属基地id : BASE_ID
	private Long baseId;
	
	
 	// 员工类型 1.员工制 2.非员工制 : STAFF_TYPE
	private Long staffType;
	
	
 	// 是否黑名单(1 是，2 否) : IS_BLACK
	private Long isBlack;
	
	
 	// 黑名单原因 : BLACK_SO
	private String blackSo;
	
	
 	// 期望工种 : HOPE_WORK_TYPE
	private Long hopeWorkType;
	
	
 	// 初试是否通过 : IS_PASS
	private Long isPass;
	
	
 	// 推荐人姓名 : RECOMMEND_NAME
	private String recommendName;
	
	
 	// 推荐人电话 : RECOMMEND_PHONE
	private String recommendPhone;
	
	
 	// 版本 : VERSION
	private Long version;
	
	
 	// 隶属城市 : IN_CITY
	private String inCity;
	
	private String bankName;
	
	private String bankNum;
	
	private String cardMan;

	/**
	 * 管理状态：
	 * 1.在户服务、
	 * 2.其他家政公司服务、
	 * 3.自己创业、
	 * 4.兼职、
	 * 5.自己接私单、
	 * 6.回老家、
	 * 7.请假中 、
	 * 8.黑名单、
	 * 9.在家待岗、
	 * 10.在校待岗、
	 * 11.在外地接单中、
	 * 12.不从事家政行业、
	 * 13.停机、
	 * 14.空号、
	 * 15.联系不上、
	 * 16.信息不符、
	 * 17.可匹配
	 */
	private Integer manageMentStatus;

	/**
	 * 工种
	 */
	private String workType;

	/**
	 * 星座
	 */
	private Long constellation;

	/**
	 * 血型
	 */
	private String blood;

	/**
	 * 技能证书
	 */
	private String skill;

	/**
	 * 子女数量
	 */
	private Long childrenNum;

	/**
	 * 子女是否在本市 1是 2否
	 */
	private Long childrenInCity;

	/**
	 * 子女是否就业 1是 2否
	 */
	private Long childrenHasJob;

	/**
	 * 配偶是否在本市 1是 2否
	 */
	private Long coupleHere;

	/**
	 * 配偶职业
	 */
	private String coupleProfession;

	/**
	 * 父母是否健在  1 父在 2母在 3 离世 4 健在
	 */
	private Long parentsAlive;

	/**
	 * 父母是否在本市 1是 2否
	 */
	private Long parentsHere;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 工作照图片地址
	 */
	private String orginalAddress;

	/**
	 * 产品管家
	 */
	private String managerName;

	/**
	 * 当前订单需求工种的最高等级
	 */
	private String gradeText;

	/**
	 * 头像地址
	 */
	private String head;

	/**
	 * 技能照地址
	 */
	private String skillAddress;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getCardMan() {
		return cardMan;
	}

	public void setCardMan(String cardMan) {
		this.cardMan = cardMan;
	}

	/**
	 * id : ID
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * id : ID
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 姓名 : NAME
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 姓名 : NAME
	 * 
	 * @return
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 1 女，2 男 : SEX
	 * 
	 * @return
	 */
	public Long getSex() {
		return sex;
	}

	/**
	 * 1 女，2 男 : SEX
	 * 
	 * @return
	 */
	public void setSex(Long sex) {
		this.sex = sex;
	}
	
	/**
	 * 年龄 : AGE
	 * 
	 * @return
	 */
	public String getAge() {
		return age;
	}

	/**
	 * 年龄 : AGE
	 * 
	 * @return
	 */
	public void setAge(String age) {
		this.age = age;
	}
	
	/**
	 * 籍贯 : ORIGIN
	 * 
	 * @return
	 */
	public String getOrigin() {
		return origin;
	}

	/**
	 * 籍贯 : ORIGIN
	 * 
	 * @return
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	/**
	 * 属相 : ZODIAC
	 * 
	 * @return
	 */
	public String getZodiac() {
		return zodiac;
	}

	/**
	 * 属相 : ZODIAC
	 * 
	 * @return
	 */
	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}
	
	/**
	 * 身份证号 : ID_CARD_NUM
	 * 
	 * @return
	 */
	public String getIdCardNum() {
		return idCardNum;
	}

	/**
	 * 身份证号 : ID_CARD_NUM
	 * 
	 * @return
	 */
	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}
	
	/**
	 * 普通话 : MANDARIN
	 * 
	 * @return
	 */
	public String getMandarin() {
		return mandarin;
	}

	/**
	 * 普通话 : MANDARIN
	 * 
	 * @return
	 */
	public void setMandarin(String mandarin) {
		this.mandarin = mandarin;
	}
	
	/**
	 * 民族 : NATION
	 * 
	 * @return
	 */
	public String getNation() {
		return nation;
	}

	/**
	 * 民族 : NATION
	 * 
	 * @return
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}
	
	/**
	 * 联系方式（手机） : MOBILE
	 * 
	 * @return
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 联系方式（手机） : MOBILE
	 * 
	 * @return
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 1 未婚 ， 2 已婚 : IS_MARRY
	 * 
	 * @return
	 */
	public Long getIsMarry() {
		return isMarry;
	}

	/**
	 * 1 未婚 ， 2 已婚 : IS_MARRY
	 * 
	 * @return
	 */
	public void setIsMarry(Long isMarry) {
		this.isMarry = isMarry;
	}
	
	/**
	 * . : USUAL_LNG
	 * 
	 * @return
	 */
	public String getUsualLng() {
		return usualLng;
	}

	/**
	 * . : USUAL_LNG
	 * 
	 * @return
	 */
	public void setUsualLng(String usualLng) {
		this.usualLng = usualLng;
	}
	
	/**
	 * . : USUAL_LAT
	 * 
	 * @return
	 */
	public String getUsualLat() {
		return usualLat;
	}

	/**
	 * . : USUAL_LAT
	 * 
	 * @return
	 */
	public void setUsualLat(String usualLat) {
		this.usualLat = usualLat;
	}
	
	/**
	 * 现在住址 : CURRENT_ADDRESS
	 * 
	 * @return
	 */
	public String getCurrentAddress() {
		return currentAddress;
	}

	/**
	 * 现在住址 : CURRENT_ADDRESS
	 * 
	 * @return
	 */
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	
	/**
	 * 原籍住址 : HOME_ADDRESS
	 * 
	 * @return
	 */
	public String getHomeAddress() {
		return homeAddress;
	}

	/**
	 * 原籍住址 : HOME_ADDRESS
	 * 
	 * @return
	 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	
	/**
	 * 1无学历，2 小学 ，3 初中，4高中/中专 ，5 本科/大专， 6.研究生 : EDUCATION
	 * 
	 * @return
	 */
	public Long getEducation() {
		return education;
	}

	/**
	 * 1无学历，2 小学 ，3 初中，4高中/中专 ，5 本科/大专， 6.研究生 : EDUCATION
	 * 
	 * @return
	 */
	public void setEducation(Long education) {
		this.education = education;
	}
	
	/**
	 * 紧急联系（电话） : EMERGENCY_CONTACT_PHONE
	 * 
	 * @return
	 */
	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}

	/**
	 * 紧急联系（电话） : EMERGENCY_CONTACT_PHONE
	 * 
	 * @return
	 */
	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}
	
	/**
	 * 1不住家 ，2 住家 : IS_HOME
	 * 
	 * @return
	 */
	public Long getIsHome() {
		return isHome;
	}

	/**
	 * 1不住家 ，2 住家 : IS_HOME
	 * 
	 * @return
	 */
	public void setIsHome(Long isHome) {
		this.isHome = isHome;
	}
	
	/**
	 * 从事家政工作年限 : WORKING_LIFE
	 * 
	 * @return
	 */
	public String getWorkingLife() {
		return workingLife;
	}

	/**
	 * 从事家政工作年限 : WORKING_LIFE
	 * 
	 * @return
	 */
	public void setWorkingLife(String workingLife) {
		this.workingLife = workingLife;
	}
	
	/**
	 * 1 健康证，2 检验证明 ，3 已过期，4未办过 : HEALTH_CERTIFICATE
	 * 
	 * @return
	 */
	public Long getHealthCertificate() {
		return healthCertificate;
	}

	/**
	 * 1 健康证，2 检验证明 ，3 已过期，4未办过 : HEALTH_CERTIFICATE
	 * 
	 * @return
	 */
	public void setHealthCertificate(Long healthCertificate) {
		this.healthCertificate = healthCertificate;
	}
	
	/**
	 * 1 不怕，2 害怕 : FEAR_PET
	 * 
	 * @return
	 */
	public Long getFearPet() {
		return fearPet;
	}

	/**
	 * 1 不怕，2 害怕 : FEAR_PET
	 * 
	 * @return
	 */
	public void setFearPet(Long fearPet) {
		this.fearPet = fearPet;
	}
	
	/**
	 * 期望薪资 : SALARY_EXPECTATION
	 * 
	 * @return
	 */
	public String getSalaryExpectation() {
		return salaryExpectation;
	}

	/**
	 * 期望薪资 : SALARY_EXPECTATION
	 * 
	 * @return
	 */
	public void setSalaryExpectation(String salaryExpectation) {
		this.salaryExpectation = salaryExpectation;
	}
	
	/**
	 * 工作期望内容 : JOB_EXPECTATION_CONTENT
	 * 
	 * @return
	 */
	public String getJobExpectationContent() {
		return jobExpectationContent;
	}

	/**
	 * 工作期望内容 : JOB_EXPECTATION_CONTENT
	 * 
	 * @return
	 */
	public void setJobExpectationContent(String jobExpectationContent) {
		this.jobExpectationContent = jobExpectationContent;
	}
	
	/**
	 * 特长 : SPECIALTY
	 * 
	 * @return
	 */
	public String getSpecialty() {
		return specialty;
	}

	/**
	 * 特长 : SPECIALTY
	 * 
	 * @return
	 */
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	
	/**
	 * 1 未删除 ， 2 已删除 : VALID
	 * 
	 * @return
	 */
	public Long getValid() {
		return valid;
	}

	/**
	 * 1 未删除 ， 2 已删除 : VALID
	 * 
	 * @return
	 */
	public void setValid(Long valid) {
		this.valid = valid;
	}
	
	/**
	 * 是否到校 1已到校 2 未到校 : IS_INSCHOOL
	 * 
	 * @return
	 */
	public Long getIsInschool() {
		return isInschool;
	}

	/**
	 * 是否到校 1已到校 2 未到校 : IS_INSCHOOL
	 * 
	 * @return
	 */
	public void setIsInschool(Long isInschool) {
		this.isInschool = isInschool;
	}
	
	/**
	 * 预约到校时间 : APPOINTMENT_SCHOOL_TIME
	 * 
	 * @return
	 */
	public java.util.Date getAppointmentSchoolTime() {
		return appointmentSchoolTime;
	}

	/**
	 * 预约到校时间 : APPOINTMENT_SCHOOL_TIME
	 * 
	 * @return
	 */
	public void setAppointmentSchoolTime(java.util.Date appointmentSchoolTime) {
		this.appointmentSchoolTime = appointmentSchoolTime;
	}
	
	/**
	 * 一级渠道 1 学校  2 基地  3 呼叫中心 4合伙人 : L1_CHANNEL
	 * 
	 * @return
	 */
	public String getL1Channel() {
		return l1Channel;
	}

	/**
	 * 一级渠道 1 学校  2 基地  3 呼叫中心 4合伙人 : L1_CHANNEL
	 * 
	 * @return
	 */
	public void setL1Channel(String l1Channel) {
		this.l1Channel = l1Channel;
	}
	
	/**
	 * 二级渠道 1 58同城  2 赶集网  3 百姓网 : L2_CHANNEL
	 * 
	 * @return
	 */
	public Long getL2Channel() {
		return l2Channel;
	}

	/**
	 * 二级渠道 1 58同城  2 赶集网  3 百姓网 : L2_CHANNEL
	 * 
	 * @return
	 */
	public void setL2Channel(Long l2Channel) {
		this.l2Channel = l2Channel;
	}
	
	/**
	 * 三级渠道 操作人员外键 : L3_CHANNEL
	 * 
	 * @return
	 */
	public Long getL3Channel() {
		return l3Channel;
	}

	/**
	 * 三级渠道 操作人员外键 : L3_CHANNEL
	 * 
	 * @return
	 */
	public void setL3Channel(Long l3Channel) {
		this.l3Channel = l3Channel;
	}
	
	/**
	 * 创建人 : CREATE_BY
	 * 
	 * @return
	 */
	@Override
	public Long getCreateBy() {
		return createBy;
	}

	/**
	 * 创建人 : CREATE_BY
	 * 
	 * @return
	 */
	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	
	/**
	 * 修改人 : UPDATE_BY
	 * 
	 * @return
	 */
	@Override
	public Long getUpdateBy() {
		return updateBy;
	}

	/**
	 * 修改人 : UPDATE_BY
	 * 
	 * @return
	 */
	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	
	/**
	 * 产品管家id : PRODUCTID
	 * 
	 * @return
	 */
	public Long getProductid() {
		return productid;
	}

	/**
	 * 产品管家id : PRODUCTID
	 * 
	 * @return
	 */
	public void setProductid(Long productid) {
		this.productid = productid;
	}
	
	/**
	 * 隶属基地id : BASE_ID
	 * 
	 * @return
	 */
	public Long getBaseId() {
		return baseId;
	}

	/**
	 * 隶属基地id : BASE_ID
	 * 
	 * @return
	 */
	public void setBaseId(Long baseId) {
		this.baseId = baseId;
	}
	
	/**
	 * 员工类型 1.员工制 2.非员工制 : STAFF_TYPE
	 * 
	 * @return
	 */
	public Long getStaffType() {
		return staffType;
	}

	/**
	 * 员工类型 1.员工制 2.非员工制 : STAFF_TYPE
	 * 
	 * @return
	 */
	public void setStaffType(Long staffType) {
		this.staffType = staffType;
	}
	
	/**
	 * 是否黑名单(1 是，2 否) : IS_BLACK
	 * 
	 * @return
	 */
	public Long getIsBlack() {
		return isBlack;
	}

	/**
	 * 是否黑名单(1 是，2 否) : IS_BLACK
	 * 
	 * @return
	 */
	public void setIsBlack(Long isBlack) {
		this.isBlack = isBlack;
	}
	
	/**
	 * 黑名单原因 : BLACK_SO
	 * 
	 * @return
	 */
	public String getBlackSo() {
		return blackSo;
	}

	/**
	 * 黑名单原因 : BLACK_SO
	 * 
	 * @return
	 */
	public void setBlackSo(String blackSo) {
		this.blackSo = blackSo;
	}
	
	/**
	 * 期望工种 : HOPE_WORK_TYPE
	 * 
	 * @return
	 */
	public Long getHopeWorkType() {
		return hopeWorkType;
	}

	/**
	 * 期望工种 : HOPE_WORK_TYPE
	 * 
	 * @return
	 */
	public void setHopeWorkType(Long hopeWorkType) {
		this.hopeWorkType = hopeWorkType;
	}
	
	/**
	 * 初试是否通过 : IS_PASS
	 * 
	 * @return
	 */
	public Long getIsPass() {
		return isPass;
	}

	/**
	 * 初试是否通过 : IS_PASS
	 * 
	 * @return
	 */
	public void setIsPass(Long isPass) {
		this.isPass = isPass;
	}
	
	/**
	 * 推荐人姓名 : RECOMMEND_NAME
	 * 
	 * @return
	 */
	public String getRecommendName() {
		return recommendName;
	}

	/**
	 * 推荐人姓名 : RECOMMEND_NAME
	 * 
	 * @return
	 */
	public void setRecommendName(String recommendName) {
		this.recommendName = recommendName;
	}
	
	/**
	 * 推荐人电话 : RECOMMEND_PHONE
	 * 
	 * @return
	 */
	public String getRecommendPhone() {
		return recommendPhone;
	}

	/**
	 * 推荐人电话 : RECOMMEND_PHONE
	 * 
	 * @return
	 */
	public void setRecommendPhone(String recommendPhone) {
		this.recommendPhone = recommendPhone;
	}
	
	/**
	 * 版本 : VERSION
	 * 
	 * @return
	 */
	@Override
	public Long getVersion() {
		return version;
	}

	/**
	 * 版本 : VERSION
	 * 
	 * @return
	 */
	@Override
	public void setVersion(Long version) {
		this.version = version;
	}
	
	/**
	 * 隶属城市 : IN_CITY
	 * 
	 * @return
	 */
	public String getInCity() {
		return inCity;
	}

	/**
	 * 隶属城市 : IN_CITY
	 * 
	 * @return
	 */
	public void setInCity(String inCity) {
		this.inCity = inCity;
	}

	public Integer getManageMentStatus() {
		return manageMentStatus;
	}

	public void setManageMentStatus(Integer manageMentStatus) {
		this.manageMentStatus = manageMentStatus;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public Long getConstellation() {
		return constellation;
	}

	public void setConstellation(Long constellation) {
		this.constellation = constellation;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Long getChildrenNum() {
		return childrenNum;
	}

	public void setChildrenNum(Long childrenNum) {
		this.childrenNum = childrenNum;
	}

	public Long getChildrenInCity() {
		return childrenInCity;
	}

	public void setChildrenInCity(Long childrenInCity) {
		this.childrenInCity = childrenInCity;
	}

	public Long getChildrenHasJob() {
		return childrenHasJob;
	}

	public void setChildrenHasJob(Long childrenHasJob) {
		this.childrenHasJob = childrenHasJob;
	}

	public Long getCoupleHere() {
		return coupleHere;
	}

	public void setCoupleHere(Long coupleHere) {
		this.coupleHere = coupleHere;
	}

	public String getCoupleProfession() {
		return coupleProfession;
	}

	public void setCoupleProfession(String coupleProfession) {
		this.coupleProfession = coupleProfession;
	}

	public Long getParentsAlive() {
		return parentsAlive;
	}

	public void setParentsAlive(Long parentsAlive) {
		this.parentsAlive = parentsAlive;
	}

	public Long getParentsHere() {
		return parentsHere;
	}

	public void setParentsHere(Long parentsHere) {
		this.parentsHere = parentsHere;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrginalAddress() {
		return orginalAddress;
	}

	public void setOrginalAddress(String orginalAddress) {
		this.orginalAddress = orginalAddress;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getGradeText() {
		return gradeText;
	}

	public void setGradeText(String gradeText) {
		this.gradeText = gradeText;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getSkillAddress() {
		return skillAddress;
	}

	public void setSkillAddress(String skillAddress) {
		this.skillAddress = skillAddress;
	}
}