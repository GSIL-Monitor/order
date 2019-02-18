package com.emotte.order.order.model;

public class ThreeOrderProduct {
	
	/**
	 * 产品code 
	 */
	private String code;
	
	/**
	 * 产品名称 
	 */
	private String name;
	
	/**
	 * 三级分类 
	 */
	private String categoryCode;
	/**
	 * 部门id
	 * 
	 */
	private String orgId;
	private String cityCode;
	
	private String startTime;
	
	private String endTime;
	
	public String getCode() {
		return code;
	}

	

	public String getOrgId() {
		return orgId;
	}



	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}



	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
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
	
}
