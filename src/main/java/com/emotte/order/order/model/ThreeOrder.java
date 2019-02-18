package com.emotte.order.order.model;

import java.math.BigDecimal;

import org.wildhorse.server.core.model.BaseModel;

public class ThreeOrder extends BaseModel {

	private static final long serialVersionUID = 6457480198293983938L;

	private Long id;// 订单id
	
	private String orderId;// 订单Id
	
	private String orderIds;
	
	private String orderCode;// 订单编号 : order_code
	private Long loginBy;// 当前登录人
	private Long loginDept;// 当前登录人部门
	private Integer loginLevel;//登录人权限级别
	private String loginAllCode;// 
	private String loginOrgCode;//当前登录人部门code
	private int loginByOrNot; //是否当前登录人操作,用于对还未分包的订单进行操作判断
	private int loginRechargeOrNot; //是否当前录入人操作，用于对已经分包的订单进行操作判断
	private int loginDeptOrnot; //是否当前登录人部门操作
	
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

	public String getLoginAllCode() {
		return loginAllCode;
	}

	public void setLoginAllCode(String loginAllCode) {
		this.loginAllCode = loginAllCode;
	}

	public String getLoginOrgCode() {
		return loginOrgCode;
	}

	public void setLoginOrgCode(String loginOrgCode) {
		this.loginOrgCode = loginOrgCode;
	}

	public int getLoginByOrNot() {
		return loginByOrNot;
	}

	public void setLoginByOrNot(int loginByOrNot) {
		this.loginByOrNot = loginByOrNot;
	}

	public int getLoginRechargeOrNot() {
		return loginRechargeOrNot;
	}

	public void setLoginRechargeOrNot(int loginRechargeOrNot) {
		this.loginRechargeOrNot = loginRechargeOrNot;
	}

	public int getLoginDeptOrnot() {
		return loginDeptOrnot;
	}

	public void setLoginDeptOrnot(int loginDeptOrnot) {
		this.loginDeptOrnot = loginDeptOrnot;
	}

	private String orderItemId;

	private String cateType;// 1:单次服务，2:延续性服务，3:商品

	private String brandName;// 品牌名称

	private Long userId;// 订单用户id(下单人)(t_user表) : user_id

	private String userName;// 客户姓名 : user_name

	private String userMobile;// 客户联系电话 : user_mobile

	private String orderType;// 订单类型(1服务类型2,商品类型，) : order_type

	private String typeText;// 订单类型值

	// 订单状态 1 新单 2已受理 3已匹配 4面试中 5已上户 6下户 7 已完成 8已取消
	// 23捡货中 24 已出库 25配送中 26已妥投 27已完成 29 审核中 29审核通过 30审核未通过
	private String orderStatus;

	private String statusText;// 订单状态值

	private String orderSourceId;	// 订单来源id(10:网上订单,20:联盟订单,30:线下订单,40:电话订单,50:移动app) :

	private String sourceText;// 来源

	private String orderChannel;// 订单渠道 : order_channel

	private String channelText;// 订单渠道值
	
	private String provinceCode;
	
	private String cityCode;

	private String city;

	private String createTime;

	private String createTimeStart;

	private String createTimeEnd;

	private String code;
	
	private Integer length;

	private String startTime;

	private String endTime;
	
	private String productCode;
	
	private String dictCode;
	
	private BigDecimal price;
	
	private Integer quantity;
	
	private String saleType;
	
	private String productName;

	private String interviewTime; // 面试时间
	
	private String address; // 地址
	
	private String interviewAddress; // 面试地址
	
	private String remark; // 备注
	
	private Integer minAge;// 要求最小年龄 : min_age
	
	private Integer maxAge;// 要求最大年龄 : max_age
	
	private String origin ; // 籍贯
	
	private BigDecimal homeForests;// 居家面积
	
	private Integer familys; // 居家人口
	
	private Integer personLevel; // 等级
	
	private String personLevelText; // 服务人员等级
	
	private Integer education; //学历
	
	private String educationText;// 学历
	
	private Long version;//版本
	
	private String birth; // 出生日期 
	
	private String critical;//紧急、不紧急
	
	private String criticalText;
	
	private String sex; // 性别
	
	private String userProvince;
	
	private String userCity;
	
	private String userArea;
	
	private String userAddress;// 用户地址
	
	private String userEmail;
	
	private String userSex;
	
	private Double longitude;
	
	private Double latitude;
	
	private String isInvoice;
	
	private String payStatus;
	
	private int    Threematchlock;
	
	//总金额
	private String totalPay;
	
	private String deliverPay;
	
	private String ip;
	
	private String receiverCityCode;
	
	private String userCityCode;
	
	private String receiverProvince;//服务地址省份
	
	private String receiverCity;//服务地址城市
	
	private String receiverArea;//服务地址地区
	
	private String receiverName;
	
	private String receiverMobile;
	
	private String receiverEmail;
	
	private String receiverAddress;
	
	private String sevicerName;
	
	private String postId;//快递单号
	
	private String cname;//中文分类名
	
	private String personName;//服务人员
	
	private Long accountsId;
	
	private Long payfeeId;
	
	private String cardId;
	
	private String cardNumb;
	
	private String cardBalance;
	
	private String validTime;
	
	private String feeToDate;
	
	private String feeSumYhzz;
	
	private String postBankYhzz;
	
	private String bankFlowNumYhzz;
	
	private String postUserYhzz;
	
	private String feeSumPos;
	
	private String bankFlowNumPos;
	
	private String postNumPos;
	
	private String feeSumRhPos;
	
	private String postTerminalNoRhPos;
	
	private String bankFlowNumRhPos;
	
	private String feeSumLpk;
	
	private String cardNum;
	
	private String feeSumOther;
	
	private String collectionEntity;
	
	private String postUserOther;
	
	private String feePosts;
	
	private String valid;
	
	private String fileName;
	
	private String path;
	
	private String recordStartTime;

	private String recordEndTime;
	
	private String authManagerId;
	
	private String authManagerRealName;
	
	private String authManagerPhone;
	
	private Long orderItemServiceId;
	
	private String userAddressId;
	
	//创建人部门
	private String createDept;
	//负责人
	private Long rechargeBy;
	//负责人部门
	private String rechargeDept;
	//分包人
	private Long followBy;
	//分包部门
	private String followDept;
	//商品价格体系
	private String priceType;
	//商品最小单位
	private String productUnit;
	//item规格
	private String productSpec;
	
	//辅助属性   
		//-王世博-
		private String realName;
		private String realNameDept;
		private String followByName;
		private String rechargeByName;
		private String rechargeDeptName;
		private String followDeptName;
		private String followTime;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public String getCateType() {
		return cateType;
	}

	public void setCateType(String cateType) {
		this.cateType = cateType;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getTypeText() {
		return typeText;
	}

	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getStatusText() {
		return statusText;
	}

	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	public String getOrderSourceId() {
		return orderSourceId;
	}

	public void setOrderSourceId(String orderSourceId) {
		this.orderSourceId = orderSourceId;
	}

	public String getSourceText() {
		return sourceText;
	}

	public void setSourceText(String sourceText) {
		this.sourceText = sourceText;
	}

	public String getOrderChannel() {
		return orderChannel;
	}

	public void setOrderChannel(String orderChannel) {
		this.orderChannel = orderChannel;
	}

	public String getChannelText() {
		return channelText;
	}

	public void setChannelText(String channelText) {
		this.channelText = channelText;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInterviewAddress() {
		return interviewAddress;
	}

	public void setInterviewAddress(String interviewAddress) {
		this.interviewAddress = interviewAddress;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getMinAge() {
		return minAge;
	}

	public void setMinAge(Integer minAge) {
		this.minAge = minAge;
	}

	public Integer getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(Integer maxAge) {
		this.maxAge = maxAge;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public BigDecimal getHomeForests() {
		return homeForests;
	}

	public void setHomeForests(BigDecimal homeForests) {
		this.homeForests = homeForests;
	}

	public Integer getFamilys() {
		return familys;
	}

	public void setFamilys(Integer familys) {
		this.familys = familys;
	}

	public Integer getPersonLevel() {
		return personLevel;
	}

	public void setPersonLevel(Integer personLevel) {
		this.personLevel = personLevel;
	}

	public String getPersonLevelText() {
		return personLevelText;
	}

	public void setPersonLevelText(String personLevelText) {
		this.personLevelText = personLevelText;
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer education) {
		this.education = education;
	}

	public String getEducationText() {
		return educationText;
	}

	public void setEducationText(String educationText) {
		this.educationText = educationText;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	public void setVersion(Long version) {
		this.version = version;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getCritical() {
		return critical;
	}

	public void setCritical(String critical) {
		this.critical = critical;
	}

	public String getCriticalText() {
		return criticalText;
	}

	public void setCriticalText(String criticalText) {
		this.criticalText = criticalText;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public String getUserArea() {
		return userArea;
	}

	public void setUserArea(String userArea) {
		this.userArea = userArea;
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

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(String isInvoice) {
		this.isInvoice = isInvoice;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(String totalPay) {
		this.totalPay = totalPay;
	}

	public String getDeliverPay() {
		return deliverPay;
	}

	public void setDeliverPay(String deliverPay) {
		this.deliverPay = deliverPay;
	}

	public String getReceiverCityCode() {
		return receiverCityCode;
	}

	public void setReceiverCityCode(String receiverCityCode) {
		this.receiverCityCode = receiverCityCode;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserCityCode() {
		return userCityCode;
	}

	public void setUserCityCode(String userCityCode) {
		this.userCityCode = userCityCode;
	}

	public String getReceiverProvince() {
		return receiverProvince;
	}

	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverArea() {
		return receiverArea;
	}

	public void setReceiverArea(String receiverArea) {
		this.receiverArea = receiverArea;
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

	public String getReceiverEmail() {
		return receiverEmail;
	}

	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getSevicerName() {
		return sevicerName;
	}

	public void setSevicerName(String sevicerName) {
		this.sevicerName = sevicerName;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Long getAccountsId() {
		return accountsId;
	}

	public void setAccountsId(Long accountsId) {
		this.accountsId = accountsId;
	}

	public Long getPayfeeId() {
		return payfeeId;
	}

	public void setPayfeeId(Long payfeeId) {
		this.payfeeId = payfeeId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardNumb() {
		return cardNumb;
	}

	public void setCardNumb(String cardNumb) {
		this.cardNumb = cardNumb;
	}

	public String getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(String cardBalance) {
		this.cardBalance = cardBalance;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public String getFeeToDate() {
		return feeToDate;
	}

	public void setFeeToDate(String feeToDate) {
		this.feeToDate = feeToDate;
	}

	public String getFeeSumYhzz() {
		return feeSumYhzz;
	}

	public void setFeeSumYhzz(String feeSumYhzz) {
		this.feeSumYhzz = feeSumYhzz;
	}

	public String getPostBankYhzz() {
		return postBankYhzz;
	}

	public void setPostBankYhzz(String postBankYhzz) {
		this.postBankYhzz = postBankYhzz;
	}

	public String getBankFlowNumYhzz() {
		return bankFlowNumYhzz;
	}

	public void setBankFlowNumYhzz(String bankFlowNumYhzz) {
		this.bankFlowNumYhzz = bankFlowNumYhzz;
	}

	public String getPostUserYhzz() {
		return postUserYhzz;
	}

	public void setPostUserYhzz(String postUserYhzz) {
		this.postUserYhzz = postUserYhzz;
	}

	public String getFeeSumPos() {
		return feeSumPos;
	}

	public void setFeeSumPos(String feeSumPos) {
		this.feeSumPos = feeSumPos;
	}

	public String getBankFlowNumPos() {
		return bankFlowNumPos;
	}

	public void setBankFlowNumPos(String bankFlowNumPos) {
		this.bankFlowNumPos = bankFlowNumPos;
	}

	public String getPostNumPos() {
		return postNumPos;
	}

	public void setPostNumPos(String postNumPos) {
		this.postNumPos = postNumPos;
	}

	public String getFeeSumRhPos() {
		return feeSumRhPos;
	}

	public void setFeeSumRhPos(String feeSumRhPos) {
		this.feeSumRhPos = feeSumRhPos;
	}

	public String getPostTerminalNoRhPos() {
		return postTerminalNoRhPos;
	}

	public void setPostTerminalNoRhPos(String postTerminalNoRhPos) {
		this.postTerminalNoRhPos = postTerminalNoRhPos;
	}

	public String getBankFlowNumRhPos() {
		return bankFlowNumRhPos;
	}

	public void setBankFlowNumRhPos(String bankFlowNumRhPos) {
		this.bankFlowNumRhPos = bankFlowNumRhPos;
	}

	public String getFeeSumLpk() {
		return feeSumLpk;
	}

	public void setFeeSumLpk(String feeSumLpk) {
		this.feeSumLpk = feeSumLpk;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getFeeSumOther() {
		return feeSumOther;
	}

	public void setFeeSumOther(String feeSumOther) {
		this.feeSumOther = feeSumOther;
	}

	public String getCollectionEntity() {
		return collectionEntity;
	}

	public void setCollectionEntity(String collectionEntity) {
		this.collectionEntity = collectionEntity;
	}

	public String getPostUserOther() {
		return postUserOther;
	}

	public void setPostUserOther(String postUserOther) {
		this.postUserOther = postUserOther;
	}

	public String getFeePosts() {
		return feePosts;
	}

	public void setFeePosts(String feePosts) {
		this.feePosts = feePosts;
	}

	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getRecordStartTime() {
		return recordStartTime;
	}

	public void setRecordStartTime(String recordStartTime) {
		this.recordStartTime = recordStartTime;
	}

	public String getRecordEndTime() {
		return recordEndTime;
	}

	public void setRecordEndTime(String recordEndTime) {
		this.recordEndTime = recordEndTime;
	}

	public String getAuthManagerId() {
		return authManagerId;
	}

	public void setAuthManagerId(String authManagerId) {
		this.authManagerId = authManagerId;
	}

	public String getAuthManagerRealName() {
		return authManagerRealName;
	}

	public void setAuthManagerRealName(String authManagerRealName) {
		this.authManagerRealName = authManagerRealName;
	}

	public String getAuthManagerPhone() {
		return authManagerPhone;
	}

	public void setAuthManagerPhone(String authManagerPhone) {
		this.authManagerPhone = authManagerPhone;
	}

	public Long getOrderItemServiceId() {
		return orderItemServiceId;
	}

	public void setOrderItemServiceId(Long orderItemServiceId) {
		this.orderItemServiceId = orderItemServiceId;
	}

	public String getUserAddressId() {
		return userAddressId;
	}

	public void setUserAddressId(String userAddressId) {
		this.userAddressId = userAddressId;
	}

	public String getCreateDept() {
		return createDept;
	}

	public void setCreateDept(String createDept) {
		this.createDept = createDept;
	}

	public Long getRechargeBy() {
		return rechargeBy;
	}

	public void setRechargeBy(Long rechargeBy) {
		this.rechargeBy = rechargeBy;
	}

	public String getRechargeDept() {
		return rechargeDept;
	}

	public void setRechargeDept(String rechargeDept) {
		this.rechargeDept = rechargeDept;
	}

	public Long getFollowBy() {
		return followBy;
	}

	public void setFollowBy(Long followBy) {
		this.followBy = followBy;
	}

	public String getFollowDept() {
		return followDept;
	}

	public void setFollowDept(String followDept) {
		this.followDept = followDept;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRealNameDept() {
		return realNameDept;
	}

	public void setRealNameDept(String realNameDept) {
		this.realNameDept = realNameDept;
	}

	public String getFollowByName() {
		return followByName;
	}

	public void setFollowByName(String followByName) {
		this.followByName = followByName;
	}

	public String getRechargeByName() {
		return rechargeByName;
	}

	public void setRechargeByName(String rechargeByName) {
		this.rechargeByName = rechargeByName;
	}

	public String getRechargeDeptName() {
		return rechargeDeptName;
	}

	public void setRechargeDeptName(String rechargeDeptName) {
		this.rechargeDeptName = rechargeDeptName;
	}

	public String getFollowDeptName() {
		return followDeptName;
	}

	public void setFollowDeptName(String followDeptName) {
		this.followDeptName = followDeptName;
	}

	public String getFollowTime() {
		return followTime;
	}

	public void setFollowTime(String followTime) {
		this.followTime = followTime;
	}
	
	public int getThreematchlock() {
		return Threematchlock;
	}

	public void setThreematchlock(int threematchlock) {
		Threematchlock = threematchlock;
	}


}
