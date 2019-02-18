package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

public class ThreeOrderCustomerAddrModel extends BaseModel{
	//收货地址--t_user_address
	private static final long serialVersionUID = 1L;
	//用户Id
	private Long id;
	//用户Id
	private Long userId;
	//收货地址联系人电话
	private String contactPhone;
	//收货地址联系人姓名
	private String contactName;
	//收货地址详细地址
	private String addressDetail;
	//收货地址-省份
	private String province;
	//收货地址-城市
	private String city;
	//收货地址-区域
	private String country;
	//收货地址--是否可用
	private Integer valid;
	//收货地址--版本
	private Long version;
	//用户和收货地址共用--经度
	private String longitude;
	//用户和收货地址共用--纬度
	private String latitude;
	//收货地址--是否默认地址
	private Integer isDefault;
	//收货地址 --联系人 性别
	private String sex;
	//收货地址 --联系人 生日
	private String birth;
	//收货地址 --城市code
	private String cityCode;
	//是否是前后台 2 表示后台
	private Integer isFront ;
	//使用可用
	private String isDisabled;
	//选择地址
	private String addressChooseAddress;
	
	
	public String getAddressChooseAddress() {
		return addressChooseAddress;
	}
	public void setAddressChooseAddress(String addressChooseAddress) {
		this.addressChooseAddress = addressChooseAddress;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
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
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	@Override
	public Long getVersion() {
		return version;
	}
	@Override
	public void setVersion(Long version) {
		this.version = version;
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
	public Integer getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public Integer getIsFront() {
		return isFront;
	}
	public void setIsFront(Integer isFront) {
		this.isFront = isFront;
	}
	public String getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(String isDisabled) {
		this.isDisabled = isDisabled;
	}

}
