package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

public class OrderThreeAddrModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	private Integer usValid;
	private Integer adValid;
	private String sex;
	private String birth;
	
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
	public Integer getUsValid() {
		return usValid;
	}
	public void setUsValid(Integer usValid) {
		this.usValid = usValid;
	}
	public Integer getAdValid() {
		return adValid;
	}
	public void setAdValid(Integer adValid) {
		this.adValid = adValid;
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
}
