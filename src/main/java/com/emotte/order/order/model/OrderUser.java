package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

public class OrderUser extends BaseModel{
	private Long id;
	private Long createBy ;
	private String createTime;
	private Long updateBy;
	private String updateTime;
	private Long orderId;
	private Long userId;
	private String realName;
	private String userProvince;
	private String userCity;
	private String userCityText;
	private String userCountry;
	private String userAddress;
	private String userMobile;
	private String userEmail;
	private String userSex;
	private String userBirth;
	private String cardType ;
	private String cardNum ;
	private String remark;

	private String contactPhone;
	private String contactName;
	private String addressDetail;
	private String province;
	private String city;
	private String country;
	private Integer valid;
	private Long version;
	private String longitude;
	private String latitude;
	private Integer isDefault;	
	private Integer usValid; // 用户和服务地址关联查询时，用户的valid
	private Integer adValid; // 用户和服务地址关联查询时，服务地址的valid。判断当前用户是否已经提交
	private String sex;
	private String birth;
	
	
	private Long unicode;
	private String unicodes;
	private String loginName;
	private String loginPwd;
	private Long loginBy;// 当前登录人
	private Long loginDept;// 当前登录人部门
	private String loginDeptName;// 当前登录人部门
	private Integer loginLevel;//登录人权限级别
	
	private String cityCode;
	private String userCityCode;
	private Integer type;
	private Long deptId;
	private Long marketBy;
	
	private String userAddressChoose; // 用户选择地址
	private String addressChoose; // 配送、服务选择地址
	
	private String priceType;
	private Integer isCommit;
	private Integer ousekeeperOrChanne;
	private Long channelId;
	private String channel; // 渠道
	private String origin;//来源
	private String phones; // 管家电话
	
	private Integer isVip;//是否是VIP 1是 2否
	private String vipStartTime; //会员开始时间
	private String vipEndTime;//会员结束时间
	
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
	 * @return the version
	 */
	@Override
	public Long getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	@Override
	public void setVersion(Long version) {
		this.version = version;
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
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	 * @return the userProvince
	 */
	public String getUserProvince() {
		return userProvince;
	}
	/**
	 * @param userProvince the userProvince to set
	 */
	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}
	/**
	 * @return the userCity
	 */
	public String getUserCity() {
		return userCity;
	}
	/**
	 * @param userCity the userCity to set
	 */
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}
	
	public String getUserCityText() {
		return userCityText;
	}
	public void setUserCityText(String userCityText) {
		this.userCityText = userCityText;
	}
	public String getUserCountry() {
		return userCountry;
	}
	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
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
	 * @return the userMobile
	 */
	public String getUserMobile() {
		return userMobile;
	}
	/**
	 * @param userMobile the userMobile to set
	 */
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	/**
	 * @return the userEmail
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * @param userEmail the userEmail to set
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * @return the contactPhone
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * @param contactPhone the contactPhone to set
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * @param contactName the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * @return the addressDetail
	 */
	public String getAddressDetail() {
		return addressDetail;
	}
	/**
	 * @param addressDetail the addressDetail to set
	 */
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
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
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
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
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * @return the isDefault
	 */
	public Integer getIsDefault() {
		return isDefault;
	}
	/**
	 * @param isDefault the isDefault to set
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * @return the usValid
	 */
	public Integer getUsValid() {
		return usValid;
	}
	/**
	 * @param usValid the usValid to set
	 */
	public void setUsValid(Integer usValid) {
		this.usValid = usValid;
	}
	/**
	 * @return the adValid
	 */
	public Integer getAdValid() {
		return adValid;
	}
	/**
	 * @param adValid the adValid to set
	 */
	public void setAdValid(Integer adValid) {
		this.adValid = adValid;
	}
	/**
	 * @return the userSex
	 */
	public String getUserSex() {
		return userSex;
	}
	/**
	 * @param userSex the userSex to set
	 */
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	/**
	 * @return the suerBirth
	 */
	public String getUserBirth() {
		return userBirth;
	}
	/**
	 * @param suerBirth the suerBirth to set
	 */
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	
	/**
	 * @return the cardType
	 */
	public String getCardType() {
		return cardType;
	}
	/**
	 * @param cardType the cardType to set
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	/**
	 * @return the cardNum
	 */
	public String getCardNum() {
		return cardNum;
	}
	/**
	 * @param cardNum the cardNum to set
	 */
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
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
	public Long getUnicode() {
		return unicode;
	}
	public void setUnicode(Long unicode) {
		this.unicode = unicode;
	}
	public String getUnicodes() {
		return unicodes;
	}
	public void setUnicodes(String unicodes) {
		this.unicodes = unicodes;
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
	
	public String getLoginDeptName() {
		return loginDeptName;
	}
	public void setLoginDeptName(String loginDeptName) {
		this.loginDeptName = loginDeptName;
	}
	public Integer getLoginLevel() {
		return loginLevel;
	}
	public void setLoginLevel(Integer loginLevel) {
		this.loginLevel = loginLevel;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getUserCityCode() {
		return userCityCode;
	}
	public void setUserCityCode(String userCityCode) {
		this.userCityCode = userCityCode;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Long getMarketBy() {
		return marketBy;
	}
	public void setMarketBy(Long marketBy) {
		this.marketBy = marketBy;
	}
	public String getUserAddressChoose() {
		return userAddressChoose;
	}
	public void setUserAddressChoose(String userAddressChoose) {
		this.userAddressChoose = userAddressChoose;
	}
	public String getAddressChoose() {
		return addressChoose;
	}
	public void setAddressChoose(String addressChoose) {
		this.addressChoose = addressChoose;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public Integer getIsCommit() {
		return isCommit;
	}
	public void setIsCommit(Integer isCommit) {
		this.isCommit = isCommit;
	}
	public Integer getOusekeeperOrChanne() {
		return ousekeeperOrChanne;
	}
	public void setOusekeeperOrChanne(Integer ousekeeperOrChanne) {
		this.ousekeeperOrChanne = ousekeeperOrChanne;
	}
	
	public Long getChannelId() {
		return channelId;
	}
	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getPhones() {
		return phones;
	}
	public void setPhones(String phones) {
		this.phones = phones;
	}
	public Integer getIsVip() {
		return isVip;
	}
	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}
	public String getVipStartTime() {
		return vipStartTime;
	}
	public void setVipStartTime(String vipStartTime) {
		this.vipStartTime = vipStartTime;
	}
	public String getVipEndTime() {
		return vipEndTime;
	}
	public void setVipEndTime(String vipEndTime) {
		this.vipEndTime = vipEndTime;
	}
	

	

}
