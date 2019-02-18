package com.emotte.order.warehouse.model;

import java.math.BigDecimal;
import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order
 * 
 * 
 * @author army
 */
public class Order extends BaseModel {

	// 订单id : id
	private Long id;

	// 订单编号 : order_code
	private String orderCode;
	
	private Long loginBy;// 当前登录人
	private Long loginDept;// 当前登录人部门
	private Integer loginLevel;//登录人权限级别
	private String loginAllCode;// 
	private String loginOrgCode;//当前登录人部门code
	private int loginByOrNot; //是否当前登录人操作,用于对还未分包的订单进行操作判断
	private int loginRechargeOrNot; //是否当前录入人操作，用于对已经分包的订单进行操作判断
	private int loginDeptOrnot; //是否当前登录人部门操作
	
	// 三方订单编号
	private String threeOrderCode;

	// 父订单id（如果没有值和子订单号一样） : parent_id
	private Long parentId;

	// 订单用户id(下单人)(t_user表) : user_id
	private Long userId;

	// 支付人名称 : user_name
	private String userName;

	// 支付人省份 : user_province
	private String userProvince;

	// 支付人城市 : user_city
	private String userCity;

	// 支付人地区 : user_area
	private String userArea;

	// 支付人详细地址 : user_address
	private String userAddress;

	// 用户移动电话 : user_mobile
	private String userMobile;

	// 用户邮箱 : user_email
	private String userEmail;

	// 接收人ID : receiver_id
	private Long receiverId;

	// 接收人名称 : receiver_name
	private String receiverName;

	// 接收人省份 : receiver_province
	private String receiverProvince;

	// 接收人城市 : receiver_city
	private String receiverCity;

	// 接收人地区 : receiver_area
	private String receiverArea;

	// 接收人邮编 : receiver_zipcode
	private String receiverZipcode;

	// 接收人详细地址 : receiver_address
	private String receiverAddress;

	// 接收人经度 : longitude
	private String longitude;

	// 接收人纬度 : latitude
	private String latitude;

	// 移动电话 : receiver_mobile
	private String receiverMobile;

	// 邮箱 : receiver_email
	private String receiverEmail;

	// 订单类型(1服务类型2,商品类型，) : order_type
	private String orderType;

	// 订单渠道 : order_channel
	private String orderChannel;

	// 订单来源id( 10:网上订单, 20 :联盟订单 ,30:线下订单,40:电话订单,50:移动app) : order_source_id
	private String orderSourceId;

	// 订单状态 1 新单 2已受理 3已匹配 4面试中 5已上户 6下户 7 已完成 8已取消
	// 23捡货中 24 已出库 25配送中 26已妥投 27已完成 29 审核中 29审核通过 30审核未通过

	private Integer orderStatus;
	private String orderStatusOther;

	// 管家id,管理员 表t_auth_manager : auth_manager_id
	private Long authManagerId;
	
	private String authManagerName;
	
	private String authManagertPhone;

	// 创建人 : create_by
	private Long createBy;

	// 创建时间 : create_time
	private String createTime;

	private String createTimeEnd; // 开始时间结束

	// 更新人 : update_by
	private Long updateBy;

	// 更新时间 : update_time
	private String updateTime;

	// 版本号 : version
	private Long version;

	// 紧急程度：1紧急 2不紧急 : critical
	private Integer critical;

	// 备注信息 : remark
	private String remark;

	// 送货时间类型（3表示只工作日送货(双休日、假日不用送) 1表示工作日、双休日与假日均可送货 2. 只双休日、假日送货(工作日不用送)） : sendtime_type
	private Integer sendtimeType;

	// 是否开发票 2表示不开 1表示开 默认为2 : is_invoice
	private Integer isInvoice;

	// 运营商id(当订单只有一个运营商的产品是 需要设置) : vendor_id
	private Long vendorId;

	// 发票类型 : invoice_type
	private String invoiceType;

	// 发票详细，发票抬头等 : invoice_memo
	private String invoiceMemo;

	// 支付状态 1 "未支付" 4 "部分支付" 2 "已支付" 3 "退款" : pay_status
	private String payStatus;

	// 总金额 : total_pay
	private java.math.BigDecimal totalPay;
	
	private String totalPayD;
	
	private Long emp_id; 
	
	private String person;
	
	private String personmobile;
	
	private String birthTimeOrder;
	
	private String birthTimeOrderEnd;
	
	//辅助属性支付状态text
	private String payStatusText;
	
	//辅助属性订单来源状态text
	private String orderSourceText;
	
	//辅助属性订单渠道状态text
	private String orderChannelText;
	
	public String getTotalPayD() {
		if(totalPay!=null){
			totalPayD = totalPay.toString();
		}
		return totalPayD;
	}

	public void setTotalPayD(String totalPayD) {
		this.totalPayD = totalPayD;
	}

	// 订单运费 : deliver_pay
	private java.math.BigDecimal deliverPay;

	// 用户ip : ip
	private String ip;

	// 邀请码 : invite_code
	private String inviteCode;

	// '用户等级： 1普通 2银卡 3金卡 4钻石 5黑卡 : user_level
	private Integer userLevel;

	// 所属部门id : order_groupid
	private Long orderGroupid;

	private String sendtimeText; // 送贷时间
	private String payText; // 支付状态
	private String sourceText; // 来源
	private String statusText; // 状态
	private String serverText; // 服务类型
	private String cateTypeName; //订单类型名字
	private String criticalText;// 紧急程度
	private String userBirth; // 出生日期
	private String userSex; // 性别
	private String receiverBirth; // 出生日期
	private String receiverSex; // 性别
	private String channelText;//订单渠道

	// 1:单次服务，2:延续性服务，3:商品
	private Integer cateType;
	private String cateTypeNot;
	private Integer cateTypeParent;

	// 是否有效 1 有效 ， 2 无效 : valid
	private Integer valid;
	//创建人部门
	private Long createDept;
	//负责人
	private Long rechargeBy;
	//负责人部门
	private Long rechargeDept;
	//分包人
	private Long followBy;
	//分包部门
	private Long followDept;
	//分包时间
	private String followTime;
	
	private String createByText;
	private String createDeptText;
	//负责人
	private String rechargeByText;
	//负责人部门
	private String rechargeDeptText;
	//分包人
	private String followByText;
	//分包部门
	private String followDeptText;

	// 服务订单需求字段
	private String address; // 服务地址
	private String startTime;
	private String endTime;
	private String interviewTime;
	private String interviewAddress; // 面试地址
	private String userCountry;
	private String servicerName; // 三方订单服务人员
	private Long threeOrderItemId;//临时存到导入的三方订单的子订单号
	private String postId;

	//部门名称
	private String orgName;

	//部门名称
	private String managerName;
	
	private String receiverCityCode; // 接收人城市编码
	private String userProvinceCode; //用户所在省份编码
	private String userCityCode; // 用户所在城市编码
	private String receiptTime; // FA订单配送时间 
	private String receiptTimes; // FA订单配送时间 
	private String city; // 订单所在城市
	
	//订单类型名称
	private String typeText;
	private String priceType; // 商品价格体体系
	//cateType需要排除的字段, 多个可以用,号隔开
	private String isSolution;
	
	//辅助：订单是否可以填报服务人员服务人员服务费状态 1.可以填报 2.不可填报
	private Integer isReport;
	private Integer payType;
	private BigDecimal accountAmount;
	//辅助属性   
	//-王世博-
	private String realName;
	private String realNameDept;
	private String followByName;
	private String rechargeByName;
	private String rechargeDeptName;
	private String followDeptName;
	private String sex;
	//他营单次服务，是否可以取消状态
	private Integer orderThreeStartFlag;
	//辅助：服务人员姓名
	private String  personnelName;
	//辅助：服务人员电话
	private String  personnelMobile;
	//辅助：是否添加服务人员查询条件语句（ yes:是 no:否）
	private String  isQueryByPersonnelNameOrMobile;
	//辅助：是否存在服务人员服务费
	private Boolean existsSalary;
	//辅助：服务人员服务费表订单月
	private Integer salaryOrderMonth;
	//辅助：服务人员服务费表订单id
	private Long salaryOrderId;
	//辅助：是否存在上户记录
	private Boolean existsInterview;
	// 合同中的订单管理费
	private BigDecimal customerManageMoney;
	
	private Integer threeMatchLock ;
	private String productCode; // 服务类订单用，商品编码
	//特殊备注
	private String log;
	
	private String orderFenfa;  //分发方式
	//推荐人电话
	private String recommendUserMobile;
	private String timeServer;//单人服务时长
	private String serviceHours;//服务时长
	private String timeSolt;//服务时间段
	private String otype;
	

	//物流号
	private String logisticsNumber;
	//物流名称
	private String logisticsName;
	
	//渠道经理类型(1:合作方渠道经理;2:易盟内部渠道经理;3:不是渠道经理)
	private Integer channelManagerType;
	//易盟内部渠道经理id
	private Long managerId;
	//合作方渠道经理id
	private Long partnerManagerId;
	
	private String itemiId;
	
	/**
	 * constitution
	 *
	 */

	/**
	 * 订单id : id
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 订单id : id
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 订单编号 : order_code
	 * 
	 * @return
	 */
	public String getOrderCode() {
		return orderCode;
	}

	/**
	 * 订单编号 : order_code
	 * 
	 * @return
	 */
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
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

	/**
	 * 父订单id（如果没有值和子订单号一样） : parent_id
	 * 
	 * @return
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 父订单id（如果没有值和子订单号一样） : parent_id
	 * 
	 * @return
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 订单用户id(下单人)(t_user表) : user_id
	 * 
	 * @return
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 订单用户id(下单人)(t_user表) : user_id
	 * 
	 * @return
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 支付人名称 : user_name
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 支付人名称 : user_name
	 * 
	 * @return
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 支付人省份 : user_province
	 * 
	 * @return
	 */
	public String getUserProvince() {
		return userProvince;
	}

	/**
	 * 支付人省份 : user_province
	 * 
	 * @return
	 */
	public void setUserProvince(String userProvince) {
		this.userProvince = userProvince;
	}

	/**
	 * 支付人城市 : user_city
	 * 
	 * @return
	 */
	public String getUserCity() {
		return userCity;
	}

	/**
	 * 支付人城市 : user_city
	 * 
	 * @return
	 */
	public void setUserCity(String userCity) {
		this.userCity = userCity;
	}

	/**
	 * 支付人地区 : user_area
	 * 
	 * @return
	 */
	public String getUserArea() {
		return userArea;
	}

	/**
	 * 支付人地区 : user_area
	 * 
	 * @return
	 */
	public void setUserArea(String userArea) {
		this.userArea = userArea;
	}

	/**
	 * 支付人详细地址 : user_address
	 * 
	 * @return
	 */
	public String getUserAddress() {
		return userAddress;
	}

	/**
	 * 支付人详细地址 : user_address
	 * 
	 * @return
	 */
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	/**
	 * 用户移动电话 : user_mobile
	 * 
	 * @return
	 */
	public String getUserMobile() {
		return userMobile;
	}

	/**
	 * 用户移动电话 : user_mobile
	 * 
	 * @return
	 */
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	/**
	 * 用户邮箱 : user_email
	 * 
	 * @return
	 */
	public String getUserEmail() {
		return userEmail;
	}

	/**
	 * 用户邮箱 : user_email
	 * 
	 * @return
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/**
	 * @return the receiverId
	 */
	public Long getReceiverId() {
		return receiverId;
	}

	/**
	 * @param receiverId
	 *            the receiverId to set
	 */
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	/**
	 * 接收人名称 : receiver_name
	 * 
	 * @return
	 */
	public String getReceiverName() {
		return receiverName;
	}

	/**
	 * 接收人名称 : receiver_name
	 * 
	 * @return
	 */
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	/**
	 * 接收人省份 : receiver_province
	 * 
	 * @return
	 */
	public String getReceiverProvince() {
		return receiverProvince;
	}

	/**
	 * 接收人省份 : receiver_province
	 * 
	 * @return
	 */
	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}

	/**
	 * 接收人城市 : receiver_city
	 * 
	 * @return
	 */
	public String getReceiverCity() {
		return receiverCity;
	}

	/**
	 * 接收人城市 : receiver_city
	 * 
	 * @return
	 */
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	/**
	 * 接收人地区 : receiver_area
	 * 
	 * @return
	 */
	public String getReceiverArea() {
		return receiverArea;
	}

	/**
	 * 接收人地区 : receiver_area
	 * 
	 * @return
	 */
	public void setReceiverArea(String receiverArea) {
		this.receiverArea = receiverArea;
	}

	/**
	 * 接收人邮编 : receiver_zipcode
	 * 
	 * @return
	 */
	public String getReceiverZipcode() {
		return receiverZipcode;
	}

	/**
	 * 接收人邮编 : receiver_zipcode
	 * 
	 * @return
	 */
	public void setReceiverZipcode(String receiverZipcode) {
		this.receiverZipcode = receiverZipcode;
	}

	/**
	 * 接收人详细地址 : receiver_address
	 * 
	 * @return
	 */
	public String getReceiverAddress() {
		return receiverAddress;
	}

	/**
	 * 接收人详细地址 : receiver_address
	 * 
	 * @return
	 */
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
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
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	/**
	 * 移动电话 : receiver_mobile
	 * 
	 * @return
	 */
	public String getReceiverMobile() {
		return receiverMobile;
	}

	/**
	 * 移动电话 : receiver_mobile
	 * 
	 * @return
	 */
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	/**
	 * 邮箱 : receiver_email
	 * 
	 * @return
	 */
	public String getReceiverEmail() {
		return receiverEmail;
	}

	/**
	 * 邮箱 : receiver_email
	 * 
	 * @return
	 */
	public void setReceiverEmail(String receiverEmail) {
		this.receiverEmail = receiverEmail;
	}


	/**
	 * 订单渠道 : order_channel
	 * 
	 * @return
	 */
	public String getOrderChannel() {
		return orderChannel;
	}

	/**
	 * 订单渠道 : order_channel
	 * 
	 * @return
	 */
	public void setOrderChannel(String orderChannel) {
		this.orderChannel = orderChannel;
	}

	/**
	 * 订单来源id( 10:网上订单, 20 :联盟订单 ,30:线下订单,40:电话订单,50:移动app) : order_source_id
	 * 
	 * @return
	 */
	public String getOrderSourceId() {
		return orderSourceId;
	}

	/**
	 * 订单来源id( 10:网上订单, 20 :联盟订单 ,30:线下订单,40:电话订单,50:移动app) : order_source_id
	 * 
	 * @return
	 */
	public void setOrderSourceId(String orderSourceId) {
		this.orderSourceId = orderSourceId;
	}

	/**
	 * 订单状态
	 * 1 新单 2已受理 3已匹配 4面试中 5已上户 6下户 7 已完成 8已取消
	 * 23捡货中 24 已出库 25配送中 26已妥投 27已完成 29 审核中 29审核通过 30审核未通过
	 * 
	 * : order_status
	 * 
	 * @return
	 */
	public Integer getOrderStatus() {
		return orderStatus;
	}

	/**
	 * 订单状态
	 * 1 新单 2已受理 3已匹配 4面试中 5已上户 6下户 7 已完成 8已取消
	 * 23捡货中 24 已出库 25配送中 26已妥投 27已完成 29 审核中 29审核通过 30审核未通过
	 * 
	 * : order_status
	 * 
	 * @return
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * 管家id,管理员 表t_auth_manager : auth_manager_id
	 * 
	 * @return
	 */
	public Long getAuthManagerId() {
		return authManagerId;
	}

	/**
	 * 管家id,管理员 表t_auth_manager : auth_manager_id
	 * 
	 * @return
	 */
	public void setAuthManagerId(Long authManagerId) {
		this.authManagerId = authManagerId;
	}

	/**
	 * 创建人 : create_by
	 * 
	 * @return
	 */
	@Override
	public Long getCreateBy() {
		return createBy;
	}

	/**
	 * 创建人 : create_by
	 * 
	 * @return
	 */
	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	/**
	 * 创建时间 : create_time
	 * 
	 * @return
	 */
	@Override
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间 : create_time
	 * 
	 * @return
	 */
	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the createTimeEnd
	 */
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	/**
	 * @param createTimeEnd
	 *            the createTimeEnd to set
	 */
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	/**
	 * 更新人 : update_by
	 * 
	 * @return
	 */
	@Override
	public Long getUpdateBy() {
		return updateBy;
	}

	/**
	 * 更新人 : update_by
	 * 
	 * @return
	 */
	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * 更新时间 : update_time
	 * 
	 * @return
	 */
	@Override
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间 : update_time
	 * 
	 * @return
	 */
	@Override
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 版本号 : version
	 * 
	 * @return
	 */
	@Override
	public Long getVersion() {
		return version;
	}

	/**
	 * 版本号 : version
	 * 
	 * @return
	 */
	@Override
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * 紧急程度：1紧急 2不紧急 : critical
	 * 
	 * @return
	 */
	public Integer getCritical() {
		return critical;
	}

	/**
	 * 紧急程度：1紧急 2不紧急 : critical
	 * 
	 * @return
	 */
	public void setCritical(Integer critical) {
		this.critical = critical;
	}

	/**
	 * 备注信息 : remark
	 * 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注信息 : remark
	 * 
	 * @return
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * 送货时间类型（3表示只工作日送货(双休日、假日不用送) 1表示工作日、双休日与假日均可送货 2. 只双休日、假日送货(工作日不用送)） : sendtime_type
	 * 
	 * @return
	 */
	public Integer getSendtimeType() {
		return sendtimeType;
	}

	/**
	 * 送货时间类型（3表示只工作日送货(双休日、假日不用送) 1表示工作日、双休日与假日均可送货 2. 只双休日、假日送货(工作日不用送)） : sendtime_type
	 * 
	 * @return
	 */
	public void setSendtimeType(Integer sendtimeType) {
		this.sendtimeType = sendtimeType;
	}

	/**
	 * 是否开发票 2表示不开 1表示开 默认为2 : is_invoice
	 * 
	 * @return
	 */
	public Integer getIsInvoice() {
		return isInvoice;
	}

	/**
	 * 是否开发票 2表示不开 1表示开 默认为2 : is_invoice
	 * 
	 * @return
	 */
	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
	}

	/**
	 * 运营商id(当订单只有一个运营商的产品是 需要设置) : vendor_id
	 * 
	 * @return
	 */
	public Long getVendorId() {
		return vendorId;
	}

	/**
	 * 运营商id(当订单只有一个运营商的产品是 需要设置) : vendor_id
	 * 
	 * @return
	 */
	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	/**
	 * 发票类型 : invoice_type
	 * 
	 * @return
	 */
	public String getInvoiceType() {
		return invoiceType;
	}

	/**
	 * 发票类型 : invoice_type
	 * 
	 * @return
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * 发票详细，发票抬头等 : invoice_memo
	 * 
	 * @return
	 */
	public String getInvoiceMemo() {
		return invoiceMemo;
	}

	/**
	 * 发票详细，发票抬头等 : invoice_memo
	 * 
	 * @return
	 */
	public void setInvoiceMemo(String invoiceMemo) {
		this.invoiceMemo = invoiceMemo;
	}

	/**
	 * 支付状态 1 "未支付" 4 "部分支付" 2 "已支付" 3 "退款" : pay_status
	 * 
	 * @return
	 */
	public String getPayStatus() {
		return payStatus;
	}

	/**
	 * 支付状态 1 "未支付" 4 "部分支付" 2 "已支付" 3 "退款" : pay_status
	 * 
	 * @return
	 */
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	/**
	 * 总金额 : total_pay
	 * 
	 * @return
	 */
	public java.math.BigDecimal getTotalPay() {
		return totalPay;
	}

	/**
	 * 总金额 : total_pay
	 * 
	 * @return
	 */
	public void setTotalPay(java.math.BigDecimal totalPay) {
		this.totalPay = totalPay;
	}

	/**
	 * 订单运费 : deliver_pay
	 * 
	 * @return
	 */
	public java.math.BigDecimal getDeliverPay() {
		return deliverPay;
	}

	/**
	 * 订单运费 : deliver_pay
	 * 
	 * @return
	 */
	public void setDeliverPay(java.math.BigDecimal deliverPay) {
		this.deliverPay = deliverPay;
	}

	/**
	 * 用户ip : ip
	 * 
	 * @return
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * 用户ip : ip
	 * 
	 * @return
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 邀请码 : invite_code
	 * 
	 * @return
	 */
	public String getInviteCode() {
		return inviteCode;
	}

	/**
	 * 邀请码 : invite_code
	 * 
	 * @return
	 */
	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	/**
	 * '用户等级： 1普通 2银卡 3金卡 4钻石 5黑卡 : user_level
	 * 
	 * @return
	 */
	public Integer getUserLevel() {
		return userLevel;
	}

	/**
	 * '用户等级： 1普通 2银卡 3金卡 4钻石 5黑卡 : user_level
	 * 
	 * @return
	 */
	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	/**
	 * 所属部门id : order_groupid
	 * 
	 * @return
	 */
	public Long getOrderGroupid() {
		return orderGroupid;
	}

	/**
	 * 所属部门id : order_groupid
	 * 
	 * @return
	 */
	public void setOrderGroupid(Long orderGroupid) {
		this.orderGroupid = orderGroupid;
	}

	/**
	 * @return the sendtimeText
	 */
	public String getSendtimeText() {
		return sendtimeText;
	}

	/**
	 * @param sendtimeText
	 *            the sendtimeText to set
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
	 * @param payText
	 *            the payText to set
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
	 * @param sourceText
	 *            the sourceText to set
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
	 * @param statusText
	 *            the statusText to set
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	/**
	 * @return the serverText
	 */
	public String getServerText() {
		return serverText;
	}

	/**
	 * @param serverText
	 *            the serverText to set
	 */
	public void setServerText(String serverText) {
		this.serverText = serverText;
	}

	/**
	 * @return the criticalText
	 */
	public String getCriticalText() {
		return criticalText;
	}

	/**
	 * @param criticalText
	 *            the criticalText to set
	 */
	public void setCriticalText(String criticalText) {
		this.criticalText = criticalText;
	}

	/**
	 * @return the userBirth
	 */
	public String getUserBirth() {
		return userBirth;
	}

	/**
	 * @param userBirth
	 *            the userBirth to set
	 */
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	/**
	 * @return the userSex
	 */
	public String getUserSex() {
		return userSex;
	}

	/**
	 * @param userSex
	 *            the userSex to set
	 */
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	/**
	 * @return the receiverBirth
	 */
	public String getReceiverBirth() {
		return receiverBirth;
	}

	/**
	 * @param receiverBirth
	 *            the receiverBirth to set
	 */
	public void setReceiverBirth(String receiverBirth) {
		this.receiverBirth = receiverBirth;
	}

	/**
	 * @return the receiverSex
	 */
	public String getReceiverSex() {
		return receiverSex;
	}

	/**
	 * @param receiverSex
	 *            the receiverSex to set
	 */
	public void setReceiverSex(String receiverSex) {
		this.receiverSex = receiverSex;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	/**
	 * @return the cateType
	 */
	public Integer getCateType() {
		return cateType;
	}

	/**
	 * @param cateType
	 *            the cateType to set
	 */
	public void setCateType(Integer cateType) {
		this.cateType = cateType;
	}

	public String getCateTypeNot() {
		return cateTypeNot;
	}

	public void setCateTypeNot(String cateTypeNot) {
		this.cateTypeNot = cateTypeNot;
	}

	public Integer getCateTypeParent() {
		return cateTypeParent;
	}

	public void setCateTypeParent(Integer cateTypeParent) {
		this.cateTypeParent = cateTypeParent;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getInterviewTime() {
		return interviewTime;
	}

	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}

	public String getInterviewAddress() {
		return interviewAddress;
	}

	public void setInterviewAddress(String interviewAddress) {
		this.interviewAddress = interviewAddress;
	}

	public String getUserCountry() {
		return userCountry;
	}

	public void setUserCountry(String userCountry) {
		this.userCountry = userCountry;
	}

	public String getThreeOrderCode() {
		return threeOrderCode;
	}

	public void setThreeOrderCode(String threeOrderCode) {
		this.threeOrderCode = threeOrderCode;
	}

	public String getServicerName() {
		return servicerName;
	}

	public void setServicerName(String servicerName) {
		this.servicerName = servicerName;
	}

	public Long getThreeOrderItemId() {
		return threeOrderItemId;
	}

	public void setThreeOrderItemId(Long threeOrderItemId) {
		this.threeOrderItemId = threeOrderItemId;
	}


	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getCateTypeName() {
		return cateTypeName;
	}

	public void setCateTypeName(String cateTypeName) {
		this.cateTypeName = cateTypeName;
	}

	public String getChannelText() {
		return channelText;
	}

	public void setChannelText(String channelText) {
		this.channelText = channelText;
	}

	public String getTypeText() {
		return typeText;
	}

	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}

	public String getReceiverCityCode() {
		return receiverCityCode;
	}

	public void setReceiverCityCode(String receiverCityCode) {
		this.receiverCityCode = receiverCityCode;
	}

	public String getUserCityCode() {
		return userCityCode;
	}

	public void setUserCityCode(String userCityCode) {
		this.userCityCode = userCityCode;
	}

	public String getReceiptTime() {
		return receiptTime;
	}

	public void setReceiptTime(String receiptTime) {
		this.receiptTime = receiptTime;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getUserProvinceCode() {
		return userProvinceCode;
	}

	public void setUserProvinceCode(String userProvinceCode) {
		this.userProvinceCode = userProvinceCode;
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

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getIsSolution() {
		return isSolution;
	}

	public void setIsSolution(String isSolution) {
		this.isSolution = isSolution;
	}

	public Long getCreateDept() {
		return createDept;
	}

	public void setCreateDept(Long createDept) {
		this.createDept = createDept;
	}

	public Long getRechargeBy() {
		return rechargeBy;
	}

	public void setRechargeBy(Long rechargeBy) {
		this.rechargeBy = rechargeBy;
	}

	public Long getRechargeDept() {
		return rechargeDept;
	}

	public void setRechargeDept(Long rechargeDept) {
		this.rechargeDept = rechargeDept;
	}

	public Long getFollowBy() {
		return followBy;
	}

	public void setFollowBy(Long followBy) {
		this.followBy = followBy;
	}

	public Long getFollowDept() {
		return followDept;
	}

	public void setFollowDept(Long followDept) {
		this.followDept = followDept;
	}

	public String getFollowTime() {
		return followTime;
	}

	public void setFollowTime(String followTime) {
		this.followTime = followTime;
	}

	public String getCreateByText() {
		return createByText;
	}

	public void setCreateByText(String createByText) {
		this.createByText = createByText;
	}

	public String getCreateDeptText() {
		return createDeptText;
	}

	public void setCreateDeptText(String createDeptText) {
		this.createDeptText = createDeptText;
	}

	public String getRechargeByText() {
		return rechargeByText;
	}

	public void setRechargeByText(String rechargeByText) {
		this.rechargeByText = rechargeByText;
	}

	public String getRechargeDeptText() {
		return rechargeDeptText;
	}

	public void setRechargeDeptText(String rechargeDeptText) {
		this.rechargeDeptText = rechargeDeptText;
	}

	public String getFollowByText() {
		return followByText;
	}

	public void setFollowByText(String followByText) {
		this.followByText = followByText;
	}

	public String getFollowDeptText() {
		return followDeptText;
	}

	public void setFollowDeptText(String followDeptText) {
		this.followDeptText = followDeptText;
	}

	public Integer getIsReport() {
		return isReport;
	}

	public void setIsReport(Integer isReport) {
		this.isReport = isReport;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public BigDecimal getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(BigDecimal accountAmount) {
		this.accountAmount = accountAmount;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOrderStatusOther() {
		return orderStatusOther;
	}

	public void setOrderStatusOther(String orderStatusOther) {
		this.orderStatusOther = orderStatusOther;
	}

	public Integer getOrderThreeStartFlag() {
		return orderThreeStartFlag;
	}

	public void setOrderThreeStartFlag(Integer orderThreeStartFlag) {
		this.orderThreeStartFlag = orderThreeStartFlag;
	}

	public String getPersonnelName() {
		return personnelName;
	}

	public void setPersonnelName(String personnelName) {
		this.personnelName = personnelName;
	}

	public String getPersonnelMobile() {
		return personnelMobile;
	}

	public void setPersonnelMobile(String personnelMobile) {
		this.personnelMobile = personnelMobile;
	}

	public String getIsQueryByPersonnelNameOrMobile() {
		return isQueryByPersonnelNameOrMobile;
	}

	public void setIsQueryByPersonnelNameOrMobile(String isQueryByPersonnelNameOrMobile) {
		this.isQueryByPersonnelNameOrMobile = isQueryByPersonnelNameOrMobile;
	}

	public Boolean getExistsSalary() {
		return existsSalary;
	}

	public void setExistsSalary(Boolean existsSalary) {
		this.existsSalary = existsSalary;
	}

	public Integer getSalaryOrderMonth() {
		return salaryOrderMonth;
	}

	public void setSalaryOrderMonth(Integer salaryOrderMonth) {
		this.salaryOrderMonth = salaryOrderMonth;
	}

	public Long getSalaryOrderId() {
		return salaryOrderId;
	}

	public void setSalaryOrderId(Long salaryOrderId) {
		this.salaryOrderId = salaryOrderId;
	}

	public Boolean getExistsInterview() {
		return existsInterview;
	}

	public void setExistsInterview(Boolean existsInterview) {
		this.existsInterview = existsInterview;
	}

	public BigDecimal getCustomerManageMoney() {
		return customerManageMoney;
	}

	public void setCustomerManageMoney(BigDecimal customerManageMoney) {
		this.customerManageMoney = customerManageMoney;
	}

	public Integer getThreeMatchLock() {
		return threeMatchLock;
	}

	public void setThreeMatchLock(Integer threeMatchLock) {
		this.threeMatchLock = threeMatchLock;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getOrderFenfa() {
		return orderFenfa;
	}

	public void setOrderFenfa(String orderFenfa) {
		this.orderFenfa = orderFenfa;
	}

	public Long getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Long emp_id) {
		this.emp_id = emp_id;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getPersonmobile() {
		return personmobile;
	}

	public void setPersonmobile(String personmobile) {
		this.personmobile = personmobile;
	}

	public String getRecommendUserMobile() {
		return recommendUserMobile;
	}

	public void setRecommendUserMobile(String recommendUserMobile) {
		this.recommendUserMobile = recommendUserMobile;
	}

	public String getTimeServer() {
		return timeServer;
	}

	public void setTimeServer(String timeServer) {
		this.timeServer = timeServer;
	}

	public String getServiceHours() {
		return serviceHours;
	}

	public void setServiceHours(String serviceHours) {
		this.serviceHours = serviceHours;
	}

	public String getTimeSolt() {
		return timeSolt;
	}

	public void setTimeSolt(String timeSolt) {
		this.timeSolt = timeSolt;
	}

	public String getBirthTimeOrder() {
		return birthTimeOrder;
	}

	public void setBirthTimeOrder(String birthTimeOrder) {
		this.birthTimeOrder = birthTimeOrder;
	}

	public String getBirthTimeOrderEnd() {
		return birthTimeOrderEnd;
	}

	public void setBirthTimeOrderEnd(String birthTimeOrderEnd) {
		this.birthTimeOrderEnd = birthTimeOrderEnd;
	}

	public String getOtype() {
		return otype;
	}

	public void setOtype(String otype) {
		this.otype = otype;
	}

	public String getLogisticsNumber() {
		return logisticsNumber;
	}

	public void setLogisticsNumber(String logisticsNumber) {
		this.logisticsNumber = logisticsNumber;
	}

	public String getLogisticsName() {
		return logisticsName;
	}

	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}

	public Integer getChannelManagerType() {
		return channelManagerType;
	}

	public void setChannelManagerType(Integer channelManagerType) {
		this.channelManagerType = channelManagerType;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public Long getPartnerManagerId() {
		return partnerManagerId;
	}

	public void setPartnerManagerId(Long partnerManagerId) {
		this.partnerManagerId = partnerManagerId;
	}

	public String getReceiptTimes() {
		return receiptTimes;
	}

	public void setReceiptTimes(String receiptTimes) {
		this.receiptTimes = receiptTimes;
	}

	public String getPayStatusText() {
		return payStatusText;
	}

	public void setPayStatusText(String payStatusText) {
		this.payStatusText = payStatusText;
	}

	public String getOrderSourceText() {
		return orderSourceText;
	}

	public void setOrderSourceText(String orderSourceText) {
		this.orderSourceText = orderSourceText;
	}

	public String getOrderChannelText() {
		return orderChannelText;
	}

	public void setOrderChannelText(String orderChannelText) {
		this.orderChannelText = orderChannelText;
	}

	public String getItemiId() {
		return itemiId;
	}

	public void setItemiId(String itemiId) {
		this.itemiId = itemiId;
	}

	
	
}
