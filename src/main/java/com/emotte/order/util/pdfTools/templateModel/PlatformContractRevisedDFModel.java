package com.emotte.order.util.pdfTools.templateModel;
/**
 * 管家帮平台信息服务协议实体bean（代付）
 * @author wn
 * @time 2018-03-07
 */
public class PlatformContractRevisedDFModel {
	private String code = "";            //合同编号
	private String qs_y = "";		 	 //签署 年
	private String qs_m = "";		 	 //签署 月
	private String qs_d = "";		     //签署  日
	
	private String pactA = "";			 //甲方
	private String cardTypeA = "";		 //甲方证件类型
	private String cardNumA = "";		 //甲方证件号
	private String phoneA = "";			 //甲方联系电话
	private String addrA = "";			 //公司地址（甲方）
	
	private String pactB = "";			 //乙方
	private String groupName = "";		 //服务站
	private String addrB = "";			 //公司地址（乙方）
	
	private String pactC = "";			 //丙方
	private String phoneC = "";			 //丙方联系电话
	private String addrC = "";			 //丙方地址
	private String cardTypeC = "";		 //丙方证件类型
	private String cardNumC = "";		 //丙方证件号
	
	private String serviceNo = "";		 //第几项服务
	private String remarkZdg = "";		 //钟点工工作时间
	private String remarkOthers = "";	 //其他服务内容
	private String addrService = "";	 //服务地址
	private String f_y = "";			 //年
	private String f_m = "";			 //月
	private String f_d = "";			 //日
	private String t_y = "";			 //年
	private String t_m = "";			 //月
	private String t_d = "";			 //日
	
	private String messFee = "";	     //平台信息费（甲向乙支付）
	private String salaryFee = "";		 //收费标准（丙向乙支付）
	private String payA = "";			 //支付标准（甲向丙支付）
	
	private String zhifuType = "";		//预支付类型
	private String yue = "";			//月
	private String ji = "";				//季
	private String banNian = "";		//半年
	private String nian = "";			//年
/*	private String zhifu_y = "";		//支付年
	private String zhifu_m = "";		//支付月
	private String zhifu_d = "";		//支付日
	private String zhifuDay = "";		//几日之前交钱
	private String zhifuDate = "";		//支付时间
*/	
	private String salaryJb = "";		//几倍
	
	private String others = "";			//其他约定全行
	private String o1 = "";				//其他约定第一行
	private String o2 = "";				//其他约定第二行
	private String o3 = "";				//其他约定第三行
	private String o4 = "";				//其他约定第四行
	private String o5 = "";				//其他约定第五行
	private String o6 = "";				//其他约定第六行
	private String o7 = "";				//其他约定第七行
	
	
	private String zhifu_y_1 = "";		//支付年
	private String zhifu_m_1 = "";		//支付月
	private String zhifu_d_1 = "";		//支付日
	private String allPay_1= "";		//费用总计
	private String allPayCN_1 = "";		//费用总计汉字
	
	private String zhifu_y_2 = "";		//支付年
	private String zhifu_m_2 = "";		//支付月
	private String zhifu_d_2 = "";		//支付日
	private String allPay_2= "";		//费用总计
	private String allPayCN_2 = "";		//费用总计汉字
	
	private String zhifu_y_3 = "";		//支付年
	private String zhifu_m_3 = "";		//支付月
	private String zhifu_d_3 = "";		//支付日
	private String allPay_3= "";		//费用总计
	private String allPayCN_3 = "";		//费用总计汉字
	
	private String otherWay = "";		//其它方式
	private String zhifu_y_4 = "";		//支付年
	private String zhifu_m_4 = "";		//支付月
	private String zhifu_d_4 = "";		//支付日
		/**
	 * 新增 海金保理 选择方式
	 * @return
	 */
	private String checkWayHjbl_1;
	private String checkWayHjbl_2;
	
	/** 法定代表人 **/
	private String legalRepresentative; 
	/** 甲方预付几个月劳务费（海金保理白条） **/
	private String prepaidMonths;
	/** 甲方预付几个月劳务费的总金额（海金保理白条） **/
	private String prepaidMoney;
	/** 甲方分期支付几个月劳务费（海金保理白条） **/
	private String instPrepaidMonths;
	/** 甲方分期支付几个月劳务费的分期总金额（海金保理白条） **/
	private String instPrepaidMoney;
	/** 甲方分期手续费几日内支付（海金保理白条） **/
	private String limitDays;
	/** 甲方预留信息:账户姓名 **/
	private String accountName;
	/** 甲方预留信息:账户开户行 **/
	private String accountBank;
	/** 甲方预留信息:账户号码 **/
	private String accountNum;

	public String getLegalRepresentative() {
		return legalRepresentative==null?"":legalRepresentative;
	}
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}
	public String getPrepaidMonths() {
		return  prepaidMonths==null?"":prepaidMonths;
	}
	public void setPrepaidMonths(String prepaidMonths) {
		this.prepaidMonths = prepaidMonths;
	}
	public String getPrepaidMoney() {
		return  prepaidMoney==null?"":prepaidMoney;
	}
	public void setPrepaidMoney(String prepaidMoney) {
		this.prepaidMoney = prepaidMoney;
	}
	public String getInstPrepaidMonths() {
		return  instPrepaidMonths==null?"":instPrepaidMonths;
	}
	public void setInstPrepaidMonths(String instPrepaidMonths) {
		this.instPrepaidMonths = instPrepaidMonths;
	}
	public String getInstPrepaidMoney() {
		return  instPrepaidMoney==null?"":instPrepaidMoney;
	}
	public void setInstPrepaidMoney(String instPrepaidMoney) {
		this.instPrepaidMoney = instPrepaidMoney;
	}
	public String getLimitDays() {
		return  limitDays==null?"":limitDays;
	}
	public void setLimitDays(String limitDays) {
		this.limitDays = limitDays;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getCode() {
		return code==null?"":code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getQs_y() {
		return qs_y ==null?"":qs_y;
	}
	public void setQs_y(String qs_y) {
		this.qs_y = qs_y;
	}
	public String getQs_m() {
		return qs_m==null?"":qs_m;
	}
	public void setQs_m(String qs_m) {
		this.qs_m = qs_m;
	}
	public String getQs_d() {
		return qs_d==null?"":qs_d;
	}
	public void setQs_d(String qs_d) {
		this.qs_d = qs_d;
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
	public String getPhoneC() {
		return phoneC==null?"":phoneC;
	}
	public void setPhoneC(String phoneC) {
		this.phoneC = phoneC;
	}
	public String getAddrC() {
		return addrC==null?"":addrC;
	}
	public void setAddrC(String addrC) {
		this.addrC = addrC;
	}
	public String getCardTypeC() {
		return cardTypeC==null?"":cardTypeC;
	}
	public void setCardTypeC(String cardTypeC) {
		this.cardTypeC = cardTypeC;
	}
	public String getCardNumC() {
		return cardNumC==null?"":cardNumC;
	}
	public void setCardNumC(String cardNumC) {
		this.cardNumC = cardNumC;
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
	public void setF_y(String f_y) {
		this.f_y = f_y;
	}
	public String getF_m() {
		return f_m==null?"":f_m;
	}
	public void setF_m(String f_m) {
		this.f_m = f_m;
	}
	public String getF_d() {
		return f_d==null?"":f_d;
	}
	public void setF_d(String f_d) {
		this.f_d = f_d;
	}
	public String getT_y() {
		return t_y==null?"":t_y;
	}
	public void setT_y(String t_y) {
		this.t_y = t_y;
	}
	public String getT_m() {
		return t_m==null?"":t_m;
	}
	public void setT_m(String t_m) {
		this.t_m = t_m;
	}
	public String getT_d() {
		return t_d==null?"":t_d;
	}
	public void setT_d(String t_d) {
		this.t_d = t_d;
	}
	public String getMessFee() {
		return messFee==null?"":messFee;
	}
	public void setMessFee(String messFee) {
		this.messFee = messFee;
	}
	public String getSalaryFee() {
		return salaryFee==null?"":salaryFee;
	}
	public void setSalaryFee(String salaryFee) {
		this.salaryFee = salaryFee;
	}
	public String getPayA() {
		return payA==null?"":payA;
	}
	public void setPayA(String payA) {
		this.payA = payA;
	}
	public String getYue() {
		return yue==null?"":yue;
	}
	public void setYue(String yue) {
		this.yue = yue;
	}
	public String getJi() {
		return ji==null?"":ji;
	}
	public void setJi(String ji) {
		this.ji = ji;
	}
	public String getBanNian() {
		return banNian==null?"":banNian;
	}
	public void setBanNian(String banNian) {
		this.banNian = banNian;
	}
	public String getNian() {
		return nian==null?"":nian;
	}
	public void setNian(String nian) {
		this.nian = nian;
	}
	/*public String getZhifu_y() {
		return zhifu_y==null?"":zhifu_y;
	}
	public void setZhifu_y(String zhifu_y) {
		this.zhifu_y = zhifu_y;
	}
	public String getZhifu_m() {
		return zhifu_m==null?"":zhifu_m;
	}
	public void setZhifu_m(String zhifu_m) {
		this.zhifu_m = zhifu_m;
	}
	public String getZhifu_d() {
		return zhifu_d==null?"":zhifu_d;
	}
	public void setZhifu_d(String zhifu_d) {
		this.zhifu_d = zhifu_d;
	}
	public String getZhifuDay() {
		return zhifuDay==null?"":zhifuDay;
	}
	public void setZhifuDay(String zhifuDay) {
		this.zhifuDay = zhifuDay;
	}*/
	public String getSalaryJb() {
		return salaryJb==null?"":salaryJb;
	}
	public void setSalaryJb(String salaryJb) {
		this.salaryJb = salaryJb;
	}
	public String getO1() {
		return o1==null?"":o1;
	}
	public void setO1(String o1) {
		this.o1 = o1;
	}
	public String getO2() {
		return o2==null?"":o2;
	}
	public void setO2(String o2) {
		this.o2 = o2;
	}
	public String getO3() {
		return o3==null?"":o3;
	}
	public void setO3(String o3) {
		this.o3 = o3;
	}
	public String getO4() {
		return o4==null?"":o4;
	}
	public void setO4(String o4) {
		this.o4 = o4;
	}
	public String getO5() {
		return o5==null?"":o5;
	}
	public void setO5(String o5) {
		this.o5 = o5;
	}
	public String getO6() {
		return o6==null?"":o6;
	}
	public void setO6(String o6) {
		this.o6 = o6;
	}
	public String getO7() {
		return o7==null?"":o7;
	}
	public void setO7(String o7) {
		this.o7 = o7;
	}
	public String getZhifuType() {
		return zhifuType==null?"":zhifuType;
	}
	public void setZhifuType(String zhifuType) {
		//甲方向丙方的支付方式（1月、2季、3半年、4年）
		//新类型（1 一次性预付劳务费 ，2 白条支付，3银行白条支付，4其他支付方式）
		this.zhifuType = zhifuType;
		if(zhifuType.equals("1")){
			this.yue = "√";
		}else if(zhifuType.equals("2")){
			this.ji = "√";
		}else if(zhifuType.equals("3")){
			this.banNian = "√";
		}else if(zhifuType.equals("4")){
			this.nian = "√";
		}else if(zhifuType.equals("5")){
			this.checkWayHjbl_1 = "√";
		}else if(!"".equals(zhifuType) && zhifuType.equals("5")) {
			this.checkWayHjbl_2 = "√";
		}
	}
	
	/**
	 * 设置其他约定，处理模板格式的换行
	 * @param others 其他约定
	 */
	public void setOthers(String others){
		if(others!=null){
			int len = others.length();
			if(len>44*6){
				this.setO1(others.substring(0, 44));
				this.setO2(others.substring(44, 44*2));
				this.setO3(others.substring(44*2, 44*3));
				this.setO4(others.substring(44*3, 44*4));
				this.setO5(others.substring(44*4, 44*5));
				this.setO6(others.substring(44*5, 44*6));
				this.setO7(others.substring(44*6, len));
			}else if(len>44*5){
				this.setO1(others.substring(0, 44));
				this.setO2(others.substring(44, 44*2));
				this.setO3(others.substring(44*2, 44*3));
				this.setO4(others.substring(44*3, 44*4));
				this.setO5(others.substring(44*4, 44*5));
				this.setO6(others.substring(44*5, len));
			}else if(len>44*4){
				this.setO1(others.substring(0, 44));
				this.setO2(others.substring(44, 44*2));
				this.setO3(others.substring(44*2, 44*3));
				this.setO4(others.substring(44*3, 44*4));
				this.setO5(others.substring(44*4, len));
			}else if(len>44*3){
				this.setO1(others.substring(0, 44));
				this.setO2(others.substring(44, 44*2));
				this.setO3(others.substring(44*2, 44*3));
				this.setO4(others.substring(44*3, len));
			}else if(len>44*2){
				this.setO1(others.substring(0, 44));
				this.setO2(others.substring(44, 44*2));
				this.setO3(others.substring(44*2, len));
			}else if(len>44){
				this.setO1(others.substring(0, 44));
				this.setO2(others.substring(44, len));
			}else{
				this.setO1(others);
			}
		}else {
			this.setO1("");
			this.setO2("");
			this.setO3("");
			this.setO4("");
			this.setO5("");
			this.setO6("");
			this.setO7("");
		}
	}
	public String getOthers() {
		return others;
	}
	/*public String getZhifuDate() {
		return zhifuDate==null?"":zhifuDate;
	}
	public void setZhifuDate(String zhifuDate) {
		this.zhifuDate = zhifuDate;
	}*/
	
	
	public String getZhifu_y_1() {
		return zhifu_y_1==null?"":zhifu_y_1;
	}
	public void setZhifu_y_1(String zhifu_y_1) {
		this.zhifu_y_1 = zhifu_y_1;
	}
	public String getZhifu_m_1() {
		return zhifu_m_1==null?"":zhifu_m_1;
	}
	public void setZhifu_m_1(String zhifu_m_1) {
		this.zhifu_m_1 = zhifu_m_1;
	}
	public String getZhifu_d_1() {
		return zhifu_d_1==null?"":zhifu_d_1;
	}
	public void setZhifu_d_1(String zhifu_d_1) {
		this.zhifu_d_1 = zhifu_d_1;
	}
	public String getAllPay_1() {
		return allPay_1==null?"":allPay_1;
	}
	public void setAllPay_1(String allPay_1) {
		this.allPay_1 = allPay_1;
	}
	public String getAllPayCN_1() {
		return allPayCN_1==null?"":allPayCN_1;
	}
	public void setAllPayCN_1(String allPayCN_1) {
		this.allPayCN_1 = allPayCN_1;
	}
	public String getZhifu_y_2() {
		return zhifu_y_2==null?"":zhifu_y_2;
	}
	public void setZhifu_y_2(String zhifu_y_2) {
		this.zhifu_y_2 = zhifu_y_2;
	}
	public String getZhifu_m_2() {
		return zhifu_m_2==null?"":zhifu_m_2;
	}
	public void setZhifu_m_2(String zhifu_m_2) {
		this.zhifu_m_2 = zhifu_m_2;
	}
	public String getZhifu_d_2() {
		return zhifu_d_2==null?"":zhifu_d_2;
	}
	public void setZhifu_d_2(String zhifu_d_2) {
		this.zhifu_d_2 = zhifu_d_2;
	}
	public String getAllPay_2() {
		return allPay_2==null?"":allPay_2;
	}
	public void setAllPay_2(String allPay_2) {
		this.allPay_2 = allPay_2;
	}
	public String getAllPayCN_2() {
		return allPayCN_2==null?"":allPayCN_2;
	}
	public void setAllPayCN_2(String allPayCN_2) {
		this.allPayCN_2 = allPayCN_2;
	}
	public String getZhifu_y_3() {
		return zhifu_y_3==null?"":zhifu_y_3;
	}
	public void setZhifu_y_3(String zhifu_y_3) {
		this.zhifu_y_3 = zhifu_y_3;
	}
	public String getZhifu_m_3() {
		return zhifu_m_3==null?"":zhifu_m_3;
	}
	public void setZhifu_m_3(String zhifu_m_3) {
		this.zhifu_m_3 = zhifu_m_3;
	}
	public String getZhifu_d_3() {
		return zhifu_d_3==null?"":zhifu_d_3;
	}
	public void setZhifu_d_3(String zhifu_d_3) {
		this.zhifu_d_3 = zhifu_d_3;
	}
	public String getAllPay_3() {
		return allPay_3==null?"":allPay_3;
	}
	public void setAllPay_3(String allPay_3) {
		this.allPay_3 = allPay_3;
	}
	public String getAllPayCN_3() {
		return allPayCN_3==null?"":allPayCN_3;
	}
	public void setAllPayCN_3(String allPayCN_3) {
		this.allPayCN_3 = allPayCN_3;
	}
	public String getOtherWay() {
		return otherWay==null?"":otherWay;
	}
	public void setOtherWay(String otherWay) {
		this.otherWay = otherWay;
	}
	public String getZhifu_y_4() {
		return zhifu_y_4==null?"":zhifu_y_4;
	}
	public void setZhifu_y_4(String zhifu_y_4) {
		this.zhifu_y_4 = zhifu_y_4;
	}
	public String getZhifu_m_4() {
		return zhifu_m_4==null?"":zhifu_m_4;
	}
	public void setZhifu_m_4(String zhifu_m_4) {
		this.zhifu_m_4 = zhifu_m_4;
	}
	public String getZhifu_d_4() {
		return zhifu_d_4==null?"":zhifu_d_4;
	}
	public void setZhifu_d_4(String zhifu_d_4) {
		this.zhifu_d_4 = zhifu_d_4;
	}
	
	public String getCheckWayHjbl_1() {
		return checkWayHjbl_1;
	}
	public void setCheckWayHjbl_1(String checkWayHjbl_1) {
		this.checkWayHjbl_1 = checkWayHjbl_1;
	}
	public String getCheckWayHjbl_2() {
		return checkWayHjbl_2;
	}
	public void setCheckWayHjbl_2(String checkWayHjbl_2) {
		this.checkWayHjbl_2 = checkWayHjbl_2;
	}
	
}
