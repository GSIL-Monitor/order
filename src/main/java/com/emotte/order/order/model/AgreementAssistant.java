package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;
/**
 * : T_ORDER_AGREEMENT_Assistant
 */
public class AgreementAssistant extends BaseModel {

 	
	private Long id;
	private Long orderId;
	private Long agreementId;
	/** 陪护服务合同辅助查询 **/ 
	private String customerserviceAddress; //甲方服务地址
	private String hospitalizationNum;     //住院号
	private String departments;           //科室
	private String roomNumber;            //病房号
	private String bedNumber_a;             //床号
	private String bedNumber_b;            
	private String consumerstate;         //甲方服务对象现状
	private String consumersName;         //服务对象姓名
	private String consumersCard;         //服务对象身份证
	private Integer custconsumerRelation;  //甲方与客户关系
	private String birthPeriod;           //产妇预产期
	private Integer specialConsiderations;  //特殊注意事项
	private Integer serviceItems;         //甲方所选服务项目
	private String serviceStarttime;      //服务开始时间
	private String hostsitDay;            //临床陪护暂定天数
	private Integer serviceFormat;        //服务形式
	private Integer deliveryMode;         //分娩方式
	private String replaceNumber;         //更换次数
	private String relation_relatives;       //具体的亲属关系
	private String relation_entrust;         //具体的委托关系
	
	/** 陪护服务合同辅助查询结束 **/ 


	/**
	 *  ID
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 *  ID
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Long agreementId) {
		this.agreementId = agreementId;
	}

	public String getCustomerserviceAddress() {
		return customerserviceAddress;
	}

	public void setCustomerserviceAddress(String customerserviceAddress) {
		this.customerserviceAddress = customerserviceAddress;
	}

	public String getHospitalizationNum() {
		return hospitalizationNum;
	}

	public void setHospitalizationNum(String hospitalizationNum) {
		this.hospitalizationNum = hospitalizationNum;
	}

	public String getDepartments() {
		return departments;
	}

	public void setDepartments(String departments) {
		this.departments = departments;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getBedNumber_a() {
		return bedNumber_a;
	}

	public void setBedNumber_a(String bedNumber_a) {
		this.bedNumber_a = bedNumber_a;
	}

	public String getBedNumber_b() {
		return bedNumber_b;
	}

	public void setBedNumber_b(String bedNumber_b) {
		this.bedNumber_b = bedNumber_b;
	}

	public String getConsumerstate() {
		return consumerstate;
	}

	public void setConsumerstate(String consumerstate) {
		this.consumerstate = consumerstate;
	}

	public String getConsumersName() {
		return consumersName;
	}

	public void setConsumersName(String consumersName) {
		this.consumersName = consumersName;
	}

	public String getConsumersCard() {
		return consumersCard;
	}

	public void setConsumersCard(String consumersCard) {
		this.consumersCard = consumersCard;
	}

	public Integer getCustconsumerRelation() {
		return custconsumerRelation;
	}

	public void setCustconsumerRelation(Integer custconsumerRelation) {
		this.custconsumerRelation = custconsumerRelation;
	}

	public String getBirthPeriod() {
		return birthPeriod;
	}

	public void setBirthPeriod(String birthPeriod) {
		this.birthPeriod = birthPeriod;
	}

	public Integer getSpecialConsiderations() {
		return specialConsiderations;
	}

	public void setSpecialConsiderations(Integer specialConsiderations) {
		this.specialConsiderations = specialConsiderations;
	}

	public Integer getServiceItems() {
		return serviceItems;
	}

	public void setServiceItems(Integer serviceItems) {
		this.serviceItems = serviceItems;
	}

	public String getServiceStarttime() {
		return serviceStarttime;
	}

	public void setServiceStarttime(String serviceStarttime) {
		this.serviceStarttime = serviceStarttime;
	}

	public String getHostsitDay() {
		return hostsitDay;
	}

	public void setHostsitDay(String hostsitDay) {
		this.hostsitDay = hostsitDay;
	}

	public Integer getServiceFormat() {
		return serviceFormat;
	}

	public void setServiceFormat(Integer serviceFormat) {
		this.serviceFormat = serviceFormat;
	}

	public Integer getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(Integer deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public String getReplaceNumber() {
		return replaceNumber;
	}

	public void setReplaceNumber(String replaceNumber) {
		this.replaceNumber = replaceNumber;
	}

	public String getRelation_relatives() {
		return relation_relatives;
	}

	public void setRelation_relatives(String relation_relatives) {
		this.relation_relatives = relation_relatives;
	}

	public String getRelation_entrust() {
		return relation_entrust;
	}

	public void setRelation_entrust(String relation_entrust) {
		this.relation_entrust = relation_entrust;
	}

	
	
	
	
}