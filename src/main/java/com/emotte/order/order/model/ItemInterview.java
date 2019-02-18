package com.emotte.order.order.model;

import java.math.BigDecimal;
import java.util.List;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order_item_interview
 * 
 * 
 * @author army
 */
public class ItemInterview  extends BaseModel{

		// : id 	
	private Long id; 
	
			//订单明细表ID : order_item_id 	
	private Long orderItemId; 
	
			//服务类型 : server_type 	
	private Integer serverType; 
	
			//服务人员id : person_id 	
	private Long personId; 
	
			//开始时间（上户） : star_time 	
	private String starTime; 
	
	public String getStartTimeSolt() {
		return startTimeSolt;
	}


	public void setStartTimeSolt(String startTimeSolt) {
		this.startTimeSolt = startTimeSolt;
	}


	public String getEndTimeSolt() {
		return endTimeSolt;
	}


	public void setEndTimeSolt(String endTimeSolt) {
		this.endTimeSolt = endTimeSolt;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	//结束时间（下户） : end_time 	
	private String endTime; 
	
			//创建人 : create_by 	
	private Long createBy; 
	
			//创建时间 : create_time 	
	private String createTime; 
	
			//更新人 : update_by 	
	private Long updateBy; 
	
			//更新时间 : update_time 	
	private String updateTime; 
	
			//版本号 : version 	
	private Long version; 
	
			//受理类型:1.未处理 2.已推送 3.已拒绝 4.已匹配 5.已调换 6.面试成功. 7.面试失败 9.上户 10.下户 11.已取消: interview_type 	
	private Integer interviewType; 
	
			//备注 : remarks 	
	private String remarks; 
	
			//面试受理时间 : interview_time 	
	private String interviewTime; 
	
			// 是否有效:valid
	private Integer valid;
			// 有订单ID
	private Long orderId ;
	private Long orderCode ;
	private String ids;
	private String personids;
	private Integer type;
	private BigDecimal customerManageMoney;
	private BigDecimal totalPay;
	private int orderStatus;
	private Float quantity;
	private Long[] inwid;
	private String[] emp_id;
	private String[] asd;
	private List<ItemInterview> list;
	
	private String startTimeSolt;
	private String endTimeSolt;
	private String msg;
	private String startDate;
	private String orderType;
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	private String name;
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


	private String endDate;
	
	private String lineStartTime;//排期开始日期
	private String lineEndTime;//排期结束日期
	private String week;//星期
	private String timeSlot;//时间段
	private String hours;//小时数
	private String pushType;//推送类型 1:推送 2:顶岗推送
	
	
	private String orgName;//辅助属性 部门名字
	private String managerName;//辅助属性 管家名字
	private String managerPhone;//辅助属性 管家电话
	private String managerId;//辅助属性 管家id
	private String interviewTypeBak;
	private String interviewTypeText;
	private String inInterviewType;//格式:8,9,11
	private String matchMethod;//服务人员匹配方式 1 erp后台  2 家政家园抢单 
	
	private String mobilePhone; //辅助字段 客户电话 20180706 add
	private String shortUrl;  //辅助字段  20180706 add
	private Long customID; //辅助字段 客户ID  201807012 add
/**
  * constitution
  *
  */
		
	/**
	 *  : id
	 * 
	 * @return 
	 */
	public Long getId () {
		return id;
	}
	

	public List<ItemInterview> getList() {
		return list;
	}


	public void setList(List<ItemInterview> list) {
		this.list = list;
	}


	public String[] getAsd() {
		return asd;
	}


	public void setAsd(String[] asd) {
		this.asd = asd;
	}


	/**
	 *  : id
	 * 
	 * @return 
	 */
	public void setId (Long id) {
		this.id = id;
	}
			
	/**
	 * 订单明细表ID : order_item_id
	 * 
	 * @return 
	 */
	public Long getOrderItemId () {
		return orderItemId;
	}
	
	/**
	 * 订单明细表ID : order_item_id
	 * 
	 * @return 
	 */
	public void setOrderItemId (Long orderItemId) {
		this.orderItemId = orderItemId;
	}
			
	/**
	 * 服务类型 : server_type
	 * 
	 * @return 
	 */
	public Integer getServerType () {
		return serverType;
	}
	
	/**
	 * 服务类型 : server_type
	 * 
	 * @return 
	 */
	public void setServerType (Integer serverType) {
		this.serverType = serverType;
	}
			
	/**
	 * 服务人员id : person_id
	 * 
	 * @return 
	 */
	public Long getPersonId () {
		return personId;
	}
	
	/**
	 * 服务人员id : person_id
	 * 
	 * @return 
	 */
	public void setPersonId (Long personId) {
		this.personId = personId;
	}
			
	/**
	 * 开始时间（上户） : star_time
	 * 
	 * @return 
	 */
	public String getStarTime () {
		return starTime;
	}
	
	/**
	 * 开始时间（上户） : star_time
	 * 
	 * @return 
	 */
	public void setStarTime (String starTime) {
		this.starTime = starTime;
	}
			
	/**
	 * 结束时间（下户） : end_time
	 * 
	 * @return 
	 */
	public String getEndTime () {
		return endTime;
	}
	
	/**
	 * 结束时间（下户） : end_time
	 * 
	 * @return 
	 */
	public void setEndTime (String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 创建人 : create_by
	 * 
	 * @return 
	 */
	@Override
	public Long getCreateBy () {
		return createBy;
	}
	
	/**
	 * 创建人 : create_by
	 * 
	 * @return 
	 */
	@Override
	public void setCreateBy (Long createBy) {
		this.createBy = createBy;
	}
			
	/**
	 * 创建时间 : create_time
	 * 
	 * @return 
	 */
	@Override
	public String getCreateTime () {
		return createTime;
	}
	
	/**
	 * 创建时间 : create_time
	 * 
	 * @return 
	 */
	@Override
	public void setCreateTime (String createTime) {
		this.createTime = createTime;
	}
			
	/**
	 * 更新人 : update_by
	 * 
	 * @return 
	 */
	@Override
	public Long getUpdateBy () {
		return updateBy;
	}
	
	/**
	 * 更新人 : update_by
	 * 
	 * @return 
	 */
	@Override
	public void setUpdateBy (Long updateBy) {
		this.updateBy = updateBy;
	}
			
	/**
	 * 更新时间 : update_time
	 * 
	 * @return 
	 */
	@Override
	public String getUpdateTime () {
		return updateTime;
	}
	
	/**
	 * 更新时间 : update_time
	 * 
	 * @return 
	 */
	@Override
	public void setUpdateTime (String updateTime) {
		this.updateTime = updateTime;
	}
			
	/**
	 * 版本号 : version
	 * 
	 * @return 
	 */
	@Override
	public Long getVersion () {
		return version;
	}
	
	/**
	 * 版本号 : version
	 * 
	 * @return 
	 */
	@Override
	public void setVersion (Long version) {
		this.version = version;
	}
			
	/**
	 * 受理类型:1预约受理 2面试中 3面试未通过 4上户 5下户   : interview_type
	 * 
	 * @return 
	 */
	public Integer getInterviewType () {
		return interviewType;
	}
	
	/**
	 * 受理类型:1预约受理 2面试中 3面试未通过 4上户 5下户   : interview_type
	 * 
	 * @return 
	 */
	public void setInterviewType (Integer interviewType) {
		this.interviewType = interviewType;
	}
			
	/**
	 * 备注 : remarks
	 * 
	 * @return 
	 */
	public String getRemarks () {
		return remarks;
	}
	
	/**
	 * 备注 : remarks
	 * 
	 * @return 
	 */
	public void setRemarks (String remarks) {
		this.remarks = remarks;
	}
			
	/**
	 * 面试受理时间 : interview_time
	 * 
	 * @return 
	 */
	public String getInterviewTime () {
		return interviewTime;
	}
	
	/**
	 * 面试受理时间 : interview_time
	 * 
	 * @return 
	 */
	public void setInterviewTime (String interviewTime) {
		this.interviewTime = interviewTime;
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
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getCustomerManageMoney() {
		return customerManageMoney;
	}

	public void setCustomerManageMoney(BigDecimal customerManageMoney) {
		this.customerManageMoney = customerManageMoney;
	}

	public BigDecimal getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(BigDecimal totalPay) {
		this.totalPay = totalPay;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public Long[] getInwid() {
		return inwid;
	}

	public void setInwid(Long[] inwid) {
		this.inwid = inwid;
	}


	public String getLineStartTime() {
		return lineStartTime;
	}


	public void setLineStartTime(String lineStartTime) {
		this.lineStartTime = lineStartTime;
	}


	public String getLineEndTime() {
		return lineEndTime;
	}


	public void setLineEndTime(String lineEndTime) {
		this.lineEndTime = lineEndTime;
	}


	public String getWeek() {
		return week;
	}


	public void setWeek(String week) {
		this.week = week;
	}


	public String getTimeSlot() {
		return timeSlot;
	}


	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}


	public String getHours() {
		return hours;
	}


	public void setHours(String hours) {
		this.hours = hours;
	}


	public String[] getEmp_id() {
		return emp_id;
	}


	public String getPersonids() {
		return personids;
	}


	public void setPersonids(String personids) {
		this.personids = personids;
	}


	public void setEmp_id(String[] emp_id) {
		this.emp_id = emp_id;
	}



	public String getPushType() {
		return pushType;
	}


	public void setPushType(String pushType) {
		this.pushType = pushType;
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


	public String getManagerPhone() {
		return managerPhone;
	}


	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}


	public String getManagerId() {
		return managerId;
	}


	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}


	public String getInterviewTypeBak() {
		return interviewTypeBak;
	}


	public void setInterviewTypeBak(String interviewTypeBak) {
		this.interviewTypeBak = interviewTypeBak;
	}


	public String getInterviewTypeText() {
		return interviewTypeText;
	}


	public void setInterviewTypeText(String interviewTypeText) {
		this.interviewTypeText = interviewTypeText;
	}


	public String getInInterviewType() {
		return inInterviewType;
	}


	public void setInInterviewType(String inInterviewType) {
		this.inInterviewType = inInterviewType;
	}


	public Long getOrderCode() {
		return orderCode;
	}


	public void setOrderCode(Long orderCode) {
		this.orderCode = orderCode;
	}


	public String getMatchMethod() {
		return matchMethod;
	}


	public void setMatchMethod(String matchMethod) {
		this.matchMethod = matchMethod;
	}


	public String getMobilePhone() {
		return mobilePhone;
	}


	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}


	public String getShortUrl() {
		return shortUrl;
	}


	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}


	public Long getCustomID() {
		return customID;
	}


	public void setCustomID(Long customID) {
		this.customID = customID;
	}


	public String getOrderType() {
		return orderType;
	}


	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	
	
}
