package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

public class ThreeOrderUser extends BaseModel {

	private static final long serialVersionUID = -8917552486243849079L;
	
	private Long id;
	
	private String loginName;
	
	private String loginPwd;
	
	private String realName;
	
	private String userSex;
	
	private String userMobile;
	
	private String cardType;
	
	private String cardNum;
	
	private String userProvince;
	
	private String userCity;
	
	private String userDistrict;
	
	private String userAddress;
	
	private String userEmail;
	
	private String longitude;
	
	private String latitude;
	
	private String provinceCode;
	
	private String cityCode;
	
	private String districtCode;
	
	private String isVip;
	
	private String isBigCust;
	
	private Integer valid;
	
	private Long marketBy;
	
	private Integer adValid; // 用户和服务地址关联查询时，服务地址的valid。判断当前用户是否已经提交
	
	private Long loginBy;// 当前登录人
	private Long loginDept;// 当前登录人部门
	private Integer loginLevel;//登录人权限级别

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getUserProvince() {
		return userProvince;
	}

	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}

	public String getUserCity() {
		return userCity;
	}

	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	public String getUserDistrict() {
		return userDistrict;
	}

	public void setUserDistrict(String userDistrict) {
		this.userDistrict = userDistrict;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public String getIsVip() {
		return isVip;
	}

	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}

	public String getIsBigCust() {
		return isBigCust;
	}

	public void setIsBigCust(String isBigCust) {
		this.isBigCust = isBigCust;
	}

	public Long getMarketBy() {
		return marketBy;
	}

	public void setMarketBy(Long marketBy) {
		this.marketBy = marketBy;
	}

	public Long getLoginBy() {
		return loginBy;
	}

	public void setLoginBy(Long loginBy) {
		this.loginBy = loginBy;
	}

	public Long getLoginDept() {
		return loginDept;
	}

	public void setLoginDept(Long loginDept) {
		this.loginDept = loginDept;
	}

	public Integer getLoginLevel() {
		return loginLevel;
	}

	public void setLoginLevel(Integer loginLevel) {
		this.loginLevel = loginLevel;
	}

	public Integer getAdValid() {
		return adValid;
	}

	public void setAdValid(Integer adValid) {
		this.adValid = adValid;
	}
	
	

}
