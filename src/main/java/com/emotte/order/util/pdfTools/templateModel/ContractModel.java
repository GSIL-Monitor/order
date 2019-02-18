package com.emotte.order.util.pdfTools.templateModel;

/**
 * 三方协议的参数实体类
 */
public class ContractModel {
	private String code = "";            //合同编号
	private String pactA = "";			 //甲方
	private String cardTypeA = "";		 //证件类型
	private String cardNumA = "";		 //证件号
	private String phoneA = "";			 //联系电话
	private String addrA = "";			 //公司地址（甲方）
	private String pactB = "";			 //乙方
	private String groupName = "";		 //服务站
	private String addrB = "";			 //公司地址（乙方）
	private String pactC = "";			 //丙方
	private String serviceNo = "";		 
	private String remarkZdg = "";
	private String remarkOthers = "";
	private String addrService = "";
	private String f_y = "";			//年
	private String f_m = "";			//月
	private String f_d = "";			//日
	private String t_y = "";			//年
	private String t_m = "";			//月
	private String t_d = "";			//日
	private String salaryMoneyM = "";	//标准服务费（月）
	private String salaryMoneyD = "";
	private String serviceMoney = "";	
	private String totalMoney = "";		//总支付金额
	private String totalMoneyCn = "";	//
//	private String hrDay = "";
	private String zhifuType = "";		//预支付类型
	private String yue = "";			//月
	private String ji = "";				//季
	private String banNian = "";		//半年
	private String nian = "";			//年
	private String zhifu_y = "";		//支付年
	private String zhifu_m = "";		//支付月
	private String zhifu_d = "";		//支付日
	private String zhifuDay = "";		//几日之前交钱
	private String salaryJb = "";
	private String others = "";
	private String o1 = "";				//其他约定第一行
	private String o2 = "";				//其他约定第二行
	private String o3 = "";				//其他约定第三行
	private String fj_path = "";		//附件地址
	
	private String bz_remark = ""; //收费标准说明（2.0版本新增属性）
	private String zhifu_remark = ""; //非按月支付的支付说明（2.0版本新增属性）
	
	private String bz1="";
	private String bz2="";
	
	public ContractModel() {
		super();
	}
	public String getCode() {
		return code==null?"":code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPactA() {
		return pactA==null?"":pactA;
	}
	public void setPactA(String pactA) {
		this.pactA = pactA;
	}
	public String getCardTypeA() {
		return cardTypeA==null?"":cardTypeA;
	}
	public void setCardTypeA(String cardTypeA) {
		this.cardTypeA = cardTypeA;
	}
	public String getCardNumA() {
		return cardNumA==null?"":cardNumA;
	}
	public void setCardNumA(String cardNumA) {
		this.cardNumA = cardNumA;
	}
	public String getPhoneA() {
		return phoneA==null?"":phoneA;
	}
	public void setPhoneA(String phoneA) {
		this.phoneA = phoneA;
	}
	public String getAddrA() {
		return addrA==null?"":addrA;
	}
	public void setAddrA(String addrA) {
		this.addrA = addrA;
	}
	public String getPactB() {
		return pactB==null?"":pactB;
	}
	public void setPactB(String pactB) {
		this.pactB = pactB;
	}

	public String getGroupName() {
		return groupName==null?"":groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getAddrB() {
		return addrB==null?"":addrB;
	}
	public void setAddrB(String addrB) {
		this.addrB = addrB;
	}
	public String getPactC() {
		return pactC==null?"":pactC;
	}
	public void setPactC(String pactC) {
		this.pactC = pactC;
	}

	public String getServiceNo() {
		return serviceNo==null?"":serviceNo;
	}
	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}
	public String getRemarkZdg() {
		return remarkZdg==null?"":remarkZdg;
	}
	public void setRemarkZdg(String remarkZdg) {
		this.remarkZdg = remarkZdg;
	}
	public String getRemarkOthers() {
		return remarkOthers==null?"":remarkOthers;
	}
	public void setRemarkOthers(String remarkOthers) {
		this.remarkOthers = remarkOthers;
	}
	public String getAddrService() {
		return addrService==null?"":addrService;
	}
	public void setAddrService(String addrService) {
		this.addrService = addrService;
	}
	public String getF_y() {
		return f_y==null?"":f_y;
	}
	public void setF_y(String fY) {
		f_y = fY;
	}
	public String getF_m() {
		return f_m==null?"":f_m;
	}
	public void setF_m(String fM) {
		f_m = fM;
	}
	public String getF_d() {
		return f_d==null?"":f_d;
	}
	public void setF_d(String fD) {
		f_d = fD;
	}
	public String getT_y() {
		return t_y==null?"":t_y;
	}
	public void setT_y(String tY) {
		t_y = tY;
	}
	public String getT_m() {
		return t_m==null?"":t_m;
	}
	public void setT_m(String tM) {
		t_m = tM;
	}
	public String getT_d() {
		return t_d==null?"":t_d;
	}
	public void setT_d(String tD) {
		t_d = tD;
	}
	public String getSalaryMoneyM() {
		return salaryMoneyM==null?"":salaryMoneyM;
	}
	public void setSalaryMoneyM(String salaryMoneyM) {
		this.salaryMoneyM = salaryMoneyM;
	}
	public String getSalaryMoneyD() {
		return salaryMoneyD==null?"":salaryMoneyD;
	}
	public void setSalaryMoneyD(String salaryMoneyD) {
		this.salaryMoneyD = salaryMoneyD;
	}
	public String getServiceMoney() {
		return serviceMoney==null?"":serviceMoney;
	}
	public void setServiceMoney(String serviceMoney) {
		this.serviceMoney = serviceMoney;
	}
	public String getTotalMoney() {
		return totalMoney==null?"":totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getTotalMoneyCn() {
		return totalMoneyCn==null?"":totalMoneyCn;
	}
	public void setTotalMoneyCn(String totalMoneyCn) {
		this.totalMoneyCn = totalMoneyCn;
	}
//	public String getHrDay() {
//		return hrDay==null?"":hrDay;
//	}
//	public void setHrDay(String hrDay) {
//		this.hrDay = hrDay;
//	}
	public String getZhifuType() {
		return zhifuType==null?"":zhifuType;
	}
	public void setZhifuType(String zhifuType) {
		//甲方向丙方的支付方式（1月、2季、3半年、4年）
		this.zhifuType = zhifuType;
		if(zhifuType.equals("1")){
			this.yue = "√";
		}else if(zhifuType.equals("2")){
			this.ji = "√";
		}else if(zhifuType.equals("3")){
			this.banNian = "√";
		}else if(zhifuType.equals("4")){
			this.nian = "√";
		}
	}
	public String getYue() {
		return yue==null?"":yue;
	}
	public String getJi() {
		return ji==null?"":ji;
	}
	public String getBanNian() {
		return banNian==null?"":banNian;
	}
	public String getNian() {
		return nian==null?"":nian;
	}
	public String getZhifu_y() {
		return zhifu_y==null?"":zhifu_y;
	}
	public void setZhifu_y(String zhifuY) {
		zhifu_y = zhifuY;
	}
	public String getZhifu_m() {
		return zhifu_m==null?"":zhifu_m;
	}
	public void setZhifu_m(String zhifuM) {
		zhifu_m = zhifuM;
	}
	public String getZhifu_d() {
		return zhifu_d==null?"":zhifu_d;
	}
	public void setZhifu_d(String zhifuD) {
		zhifu_d = zhifuD;
	}
	public String getZhifuDay() {
		return zhifuDay==null?"":zhifuDay;
	}
	public void setZhifuDay(String zhifuDay) {
		this.zhifuDay = zhifuDay;
	}
	public String getSalaryJb() {
		return salaryJb==null?"":salaryJb;
	}
	public void setSalaryJb(String salaryJb) {
		this.salaryJb = salaryJb;
	}
	public String getOthers() {
		return others==null?"":others;
	}
	public void setOthers(String others) {
		this.others = others;
		if(others!=null){
			if(others.length()>92){
				o1 = others.substring(0,46);
				o2 = others.substring(46, 92);
				o3 = others.substring(92);
			}else if(others.length()>46){
				o1 = others.substring(0,46);
				o2 = others.substring(46);
			}else{
				o1 = others;
			}
		}
	}
	public String getO1() { //46
		return o1==null?"":o1;
	}
	public String getO2() {
		return o2==null?"":o2;
	}
	public String getO3() {
		return o3==null?"":o3;
	}
	public String getFj_path() {
		return fj_path==null?"":fj_path;
	}
	public void setFj_path(String fjPath) {
		this.fj_path = fjPath;
	}
	public String getBz_remark() {
		return bz_remark==null?"":bz_remark;
	}
	public void setBz_remark(String bzRemark) {
		this.bz_remark = bzRemark;
		if(bzRemark!=null){
			if(bzRemark.length()>21){
				this.bz1=bzRemark.substring(0,21);
				this.bz2=bzRemark.substring(21);
			}else{
				this.bz1=bzRemark;
			}
		}
	}
	public String getZhifu_remark() {
		return zhifu_remark==null?"":zhifu_remark;
	}
	public void setZhifu_remark(String zhifuRemark) {
		this.zhifu_remark = zhifuRemark;
	}
	public String getBz1() {
		return bz1;
	}
	public String getBz2() {
		return bz2;
	}
}
