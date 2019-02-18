package com.emotte.order.order.model;

public class OrderServerNeeds {
	
	private String startDate;
	// 结束日期 yyyyMMdd格式 包含 : END_DATE
	private String endDate;
	// 开始时间 HHmmss格式 包含 : START_TIME
	private String startTime;
	// 结束时间 HHmmss格式 不包含 : END_TIME
	private String endTime;
	// 工作日，0为所有，1~7分别为周一到周日，逗号隔开 : WEEKDAY
	private String weekday;
	// 状态 0空闲1工作2预占3请假4其他 -1 删除 : STATUS
	private String status;
	// id : ID
	private String id;
	// 姓名 : NAME
	private String name;
	// 1 女，2 男 : SEX
	private String sex;
	// 年龄 : AGE
	private String age;
	// 籍贯 : ORIGIN
	private String origin;
	// 属相 : ZODIAC
	private String zodiac;
	// 普通话 : MANDARIN
	private String mandarin;
	// 民族 : NATION
	private String nation;
	// 1 未婚 ， 2 已婚 : IS_MARRY
	private String isMarry;
	// 1无学历，2 小学 ，3 初中，4高中/中专 ，5 本科/大专， 6.研究生 : EDUCATION
	private String education;
	// 紧急联系（电话） : EMERGENCY_CONTACT_PHONE
	private String emergencyContactPhone;
	// 从事家政工作年限 : WORKING_LIFE
	private String workingLife;
	// 1 不怕，2 害怕 : FEAR_PET
	private String fearPet;
	// : USUAL_LNG
	private String usualLng;
	// : USUAL_LAT
	private String usualLat;
	private String productCode;
	
	
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
	public String getWeekday() {
		return weekday;
	}
	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getZodiac() {
		return zodiac;
	}
	public void setZodiac(String zodiac) {
		this.zodiac = zodiac;
	}
	public String getMandarin() {
		return mandarin;
	}
	public void setMandarin(String mandarin) {
		this.mandarin = mandarin;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getIsMarry() {
		return isMarry;
	}
	public void setIsMarry(String isMarry) {
		this.isMarry = isMarry;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getEmergencyContactPhone() {
		return emergencyContactPhone;
	}
	public void setEmergencyContactPhone(String emergencyContactPhone) {
		this.emergencyContactPhone = emergencyContactPhone;
	}
	public String getWorkingLife() {
		return workingLife;
	}
	public void setWorkingLife(String workingLife) {
		this.workingLife = workingLife;
	}
	public String getFearPet() {
		return fearPet;
	}
	public void setFearPet(String fearPet) {
		this.fearPet = fearPet;
	}
	public String getUsualLng() {
		return usualLng;
	}
	public void setUsualLng(String usualLng) {
		this.usualLng = usualLng;
	}
	public String getUsualLat() {
		return usualLat;
	}
	public void setUsualLat(String usualLat) {
		this.usualLat = usualLat;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	
}
