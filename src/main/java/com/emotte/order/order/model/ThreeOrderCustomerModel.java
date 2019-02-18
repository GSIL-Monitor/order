package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

public class ThreeOrderCustomerModel extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	//用户信息--t_customer
		//用户的创建者
	private Long createBy ;
		//用户的创建时间
	private String createTime;
		//用户的操作者
	private Long updateBy;
		//用户的更新时间
	private String updateTime;
		//订单Id()
	private Long orderId;
		//用户Id
	private Long userId;
		//用户真实姓名
	private String realName;
		//用户地址的省
	private String userProvince;
		//用户地址的市
	private String userCity;
		//用户地址的区
	private String userDistrict;
		//用户地址详细地址
	private String userAddress;
		//用户联系方式
	private String userMobile;
		//用户emial
	private String userEmail;
		//用户性别
	private String userSex;
		//用户生日
	private String userBirth;
		//证件类型
	private String cardType ;
		//证件信息
	private String cardNum ;
		//备注
	private String remark;
		//用户关联查询出地址
	private String userCityCode;
		//用户是否可用
	private Integer valid;
		//是否VIP 1是 
	private Integer is_vip;
		//是否大客户 1是 
	private Integer is_big_cust;
		//用户和收货地址共用--经度
	private String longitude;
		//用户和收货地址共用--纬度
	private String latitude;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
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
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getIs_vip() {
		return is_vip;
	}
	public void setIs_vip(Integer is_vip) {
		this.is_vip = is_vip;
	}
	public Integer getIs_big_cust() {
		return is_big_cust;
	}
	public void setIs_big_cust(Integer is_big_cust) {
		this.is_big_cust = is_big_cust;
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
	
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getUserCityCode() {
		return userCityCode;
	}
	public void setUserCityCode(String userCityCode) {
		this.userCityCode = userCityCode;
	}
	
}
