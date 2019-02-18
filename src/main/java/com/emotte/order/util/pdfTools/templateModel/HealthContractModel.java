package com.emotte.order.util.pdfTools.templateModel;
/**
 * 管家帮医疗陪护信息服务协议实体bean
 * @author 
 */
public class HealthContractModel {
	private String code = "";            //合同编号
	private String qs_y = "";		 	 //签署 年
	private String qs_m = "";		 	 //签署 月
	private String qs_d = "";		     //签署  日
	
	private String pactA = "";			 //甲方
	private String cardTypeA = "";		 //甲方证件类型
	private String cardNumA = "";		 //甲方证件号
	private String phoneA = "";			 //甲方联系电话
	private String addrA = "";			 //家庭地址（甲方）
	private String serviceAddrA = "";	 //服务地址（甲方）
	
	
	private String pactB = "";			 //乙方
	private String groupName = "";		 //服务站
	private String addrB = "";			 //公司地址（乙方）
	private String phoneB="";            //乙方联系电话
	
	private String pactC = "";			 //丙方
	private String phoneC = "";			 //丙方联系电话
	private String addrC = "";			 //丙方地址
	private String cardTypeC = "";		 //丙方证件类型
	private String cardNumC = "";		 //丙方证件号
	
	
	private String relation_a = "";			 //甲方本人
	private String relation_b = "";			 //服务对象与甲方为亲属关系
	private String relation_c = "";			 //甲方非亲属关系的委托人
	private String relation_relatives;       //具体的亲属关系
	private String relation_entrust;         //具体的委托关系
	
	private String childbirth_y = "";			 //产妇预产期_年
	private String childbirth_m = "";			 //产妇预产期_月
	private String childbirth_d = "";			 //产妇预产期_日
	
	private String specialConsiderations_yes = "";	     //特别注意事项（有）
	private String specialConsiderations_no = "";	     //特别注意事项（无）
	
	private String serviceStart_y = "";			 //服务起始日期_年
	private String serviceStart_m = "";			 //服务起始日期_月
	private String serviceStart_r = "";			 //服务起始日期_日
	
	private String serviceFormat_day= "";            //服务形式_白天
	private String serviceFormat_24= "";             //服务形式_24小时
	
	private String deliveryMode_sc= "";            //分娩方式_顺产
	private String deliveryMode_gc= "";            //分娩方式_剖腹产
	
	private String hospitalizationNum= "";         
	private String departments="";
	private String roomNumber="";
	private String bedNumber_a="";
	private String bedNumber_b="";
	private String consumerstate="";
	private String consumersName="";
	private String consumersCard="";
	private String custconsumerRelation="";
	private String birthPeriod="";
	private String specialConsiderations="";
	private String serviceItems="";
	private String serviceStarttime="";
	private String hostsitDay="";
	private String serviceFormat="";
	private String deliveryMode="";
	private String replaceNumber="";
	private String serviceprojectStandard= "";
	private String payment="";
	private String paymentUpper="";
	private String partyBaccountName= "";
	private String partyBaccountNum="";
	private String partyBaccountBank="";
	
	
	private String others = "";			//其他约定全行
	private String o1 = "";				//其他约定第一行
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getQs_y() {
		return qs_y;
	}
	public void setQs_y(String qs_y) {
		this.qs_y = qs_y;
	}
	public String getQs_m() {
		return qs_m;
	}
	public void setQs_m(String qs_m) {
		this.qs_m = qs_m;
	}
	public String getQs_d() {
		return qs_d;
	}
	public void setQs_d(String qs_d) {
		this.qs_d = qs_d;
	}
	public String getPactA() {
		return pactA;
	}
	public void setPactA(String pactA) {
		this.pactA = pactA;
	}
	public String getCardTypeA() {
		return cardTypeA;
	}
	public void setCardTypeA(String cardTypeA) {
		this.cardTypeA = cardTypeA;
	}
	public String getCardNumA() {
		return cardNumA;
	}
	public void setCardNumA(String cardNumA) {
		this.cardNumA = cardNumA;
	}
	public String getPhoneA() {
		return phoneA;
	}
	public void setPhoneA(String phoneA) {
		this.phoneA = phoneA;
	}
	public String getAddrA() {
		return addrA;
	}
	public void setAddrA(String addrA) {
		this.addrA = addrA;
	}
	public String getServiceAddrA() {
		return serviceAddrA;
	}
	public void setServiceAddrA(String serviceAddrA) {
		this.serviceAddrA = serviceAddrA;
	}
	public String getPactB() {
		return pactB;
	}
	public void setPactB(String pactB) {
		this.pactB = pactB;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getAddrB() {
		return addrB;
	}
	public void setAddrB(String addrB) {
		this.addrB = addrB;
	}
	public String getPactC() {
		return pactC;
	}
	public void setPactC(String pactC) {
		this.pactC = pactC;
	}
	public String getPhoneC() {
		return phoneC;
	}
	public void setPhoneC(String phoneC) {
		this.phoneC = phoneC;
	}
	public String getAddrC() {
		return addrC;
	}
	public void setAddrC(String addrC) {
		this.addrC = addrC;
	}
	public String getCardTypeC() {
		return cardTypeC;
	}
	public void setCardTypeC(String cardTypeC) {
		this.cardTypeC = cardTypeC;
	}
	public String getCardNumC() {
		return cardNumC;
	}
	public void setCardNumC(String cardNumC) {
		this.cardNumC = cardNumC;
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
	public String getCustconsumerRelation() {
		return custconsumerRelation;
	}
	public void setCustconsumerRelation(String custconsumerRelation) {
		this.custconsumerRelation = custconsumerRelation;
		if(custconsumerRelation.equals("1")){
			this.relation_a = "√";
		}else if(custconsumerRelation.equals("2")){
			this.relation_b = "√";
		}else if(custconsumerRelation.equals("3")){
			this.relation_c = "√";
		}
	}
	
	public String getSpecialConsiderations() {
		return specialConsiderations;
	}
	public void setSpecialConsiderations(String specialConsiderations) {
		this.specialConsiderations = specialConsiderations;
		if(specialConsiderations.equals("1")){
			this.specialConsiderations_yes = "√";
		}else if(specialConsiderations.equals("2")){
			this.specialConsiderations_no = "√";
		}
		
	}
	public String getServiceItems() {
		return serviceItems;
	}
	public void setServiceItems(String serviceItems) {
		this.serviceItems = serviceItems;
		if(serviceItems.equals("1")){
			this.serviceItems="(1)";
		}else if(serviceItems.equals("2")) {
			this.serviceItems="(2)";
		}else if(serviceItems.equals("3")) {
			this.serviceItems="(3)";
		}else if(serviceItems.equals("4")) {
			this.serviceItems="(4)";
		}
		
		
	}
	
	public String getHostsitDay() {
		return hostsitDay;
	}
	public void setHostsitDay(String hostsitDay) {
		this.hostsitDay = hostsitDay;
	}
	public String getServiceFormat() {
		return serviceFormat;
	}
	public void setServiceFormat(String serviceFormat) {
		this.serviceFormat = serviceFormat;
		if(serviceFormat.equals("1")){
			this.serviceFormat_day = "√";
		}else if(serviceFormat.equals("2")){
			this.serviceFormat_24 = "√";
		}
		
	}
	public String getDeliveryMode() {
		return deliveryMode;
	}
	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
		if(deliveryMode.equals("1")){
			this.deliveryMode_sc = "√";
		}else if(deliveryMode.equals("2")){
			this.deliveryMode_gc = "√";
		}
		
	}
	public String getReplaceNumber() {
		return replaceNumber;
	}
	public void setReplaceNumber(String replaceNumber) {
		this.replaceNumber = replaceNumber;
	}
	public String getServiceprojectStandard() {
		return serviceprojectStandard;
	}
	public void setServiceprojectStandard(String serviceprojectStandard) {
		this.serviceprojectStandard = serviceprojectStandard;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getPaymentUpper() {
		return paymentUpper;
	}
	public void setPaymentUpper(String paymentUpper) {
		this.paymentUpper = paymentUpper;
	}
	public String getPartyBaccountName() {
		return partyBaccountName;
	}
	public void setPartyBaccountName(String partyBaccountName) {
		this.partyBaccountName = partyBaccountName;
	}
	public String getPartyBaccountNum() {
		return partyBaccountNum;
	}
	public void setPartyBaccountNum(String partyBaccountNum) {
		this.partyBaccountNum = partyBaccountNum;
	}
	public String getPartyBaccountBank() {
		return partyBaccountBank;
	}
	public void setPartyBaccountBank(String partyBaccountBank) {
		this.partyBaccountBank = partyBaccountBank;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getO1() {
		return o1;
	}
	public void setO1(String o1) {
		this.o1 = o1;
	}
	public String getRelation_a() {
		return relation_a;
	}
	public void setRelation_a(String relation_a) {
		this.relation_a = relation_a;
	}
	public String getRelation_b() {
		return relation_b;
	}
	public void setRelation_b(String relation_b) {
		this.relation_b = relation_b;
	}
	public String getRelation_c() {
		return relation_c;
	}
	public void setRelation_c(String relation_c) {
		this.relation_c = relation_c;
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
	public String getChildbirth_y() {
		return childbirth_y;
	}
	public void setChildbirth_y(String childbirth_y) {
		this.childbirth_y = childbirth_y;
	}
	public String getChildbirth_m() {
		return childbirth_m;
	}
	public void setChildbirth_m(String childbirth_m) {
		this.childbirth_m = childbirth_m;
	}
	public String getChildbirth_d() {
		return childbirth_d;
	}
	public void setChildbirth_d(String childbirth_d) {
		this.childbirth_d = childbirth_d;
	}
	public String getSpecialConsiderations_yes() {
		return specialConsiderations_yes;
	}
	public void setSpecialConsiderations_yes(String specialConsiderations_yes) {
		this.specialConsiderations_yes = specialConsiderations_yes;
	}
	public String getSpecialConsiderations_no() {
		return specialConsiderations_no;
	}
	public void setSpecialConsiderations_no(String specialConsiderations_no) {
		this.specialConsiderations_no = specialConsiderations_no;
	}
	public String getServiceStart_y() {
		return serviceStart_y;
	}
	public void setServiceStart_y(String serviceStart_y) {
		this.serviceStart_y = serviceStart_y;
	}
	public String getServiceStart_m() {
		return serviceStart_m;
	}
	public void setServiceStart_m(String serviceStart_m) {
		this.serviceStart_m = serviceStart_m;
	}
	public String getServiceStart_r() {
		return serviceStart_r;
	}
	public void setServiceStart_r(String serviceStart_r) {
		this.serviceStart_r = serviceStart_r;
	}
	public String getServiceFormat_day() {
		return serviceFormat_day;
	}
	public void setServiceFormat_day(String serviceFormat_day) {
		this.serviceFormat_day = serviceFormat_day;
	}
	public String getServiceFormat_24() {
		return serviceFormat_24;
	}
	public void setServiceFormat_24(String serviceFormat_24) {
		this.serviceFormat_24 = serviceFormat_24;
	}
	public String getDeliveryMode_sc() {
		return deliveryMode_sc;
	}
	public void setDeliveryMode_sc(String deliveryMode_sc) {
		this.deliveryMode_sc = deliveryMode_sc;
	}
	public String getDeliveryMode_gc() {
		return deliveryMode_gc;
	}
	public void setDeliveryMode_gc(String deliveryMode_gc) {
		this.deliveryMode_gc = deliveryMode_gc;
	}
	public String getBirthPeriod() {
		return birthPeriod;
	}
	public void setBirthPeriod(String birthPeriod) {
		this.birthPeriod = birthPeriod;
	}
	public String getServiceStarttime() {
		return serviceStarttime;
	}
	public void setServiceStarttime(String serviceStarttime) {
		this.serviceStarttime = serviceStarttime;
	}
	public String getPhoneB() {
		return phoneB;
	}
	public void setPhoneB(String phoneB) {
		this.phoneB = phoneB;
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

	
}
