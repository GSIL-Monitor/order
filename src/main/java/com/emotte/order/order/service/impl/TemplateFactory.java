package com.emotte.order.order.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.emotte.order.order.model.Agreement;
import com.emotte.order.util.NumberToCN;
import com.emotte.order.util.pdfTools.template.BaseTemplate;
import com.emotte.order.util.pdfTools.template.Contract;
import com.emotte.order.util.pdfTools.template.FranchiseeContract;
import com.emotte.order.util.pdfTools.template.FranchiseeHomePage;
import com.emotte.order.util.pdfTools.template.HealthContract;
import com.emotte.order.util.pdfTools.template.HealthHomePage;
import com.emotte.order.util.pdfTools.template.JxPlatformContractHjblDF;
import com.emotte.order.util.pdfTools.template.JxPlatformContractHjblDfHonePage;
import com.emotte.order.util.pdfTools.template.JxPlatformContractRevisedDF;
import com.emotte.order.util.pdfTools.template.JxPlatformContractZF;
import com.emotte.order.util.pdfTools.template.JxPlatformRevisedDFHomePage;
import com.emotte.order.util.pdfTools.template.JxPlatformZFHomePage;
import com.emotte.order.util.pdfTools.template.PlatformContractDF;
import com.emotte.order.util.pdfTools.template.PlatformContractDS;
import com.emotte.order.util.pdfTools.template.PlatformContractHjblDF;
import com.emotte.order.util.pdfTools.template.PlatformContractHjblDfHonePage;
import com.emotte.order.util.pdfTools.template.PlatformContractRevisedDF;
import com.emotte.order.util.pdfTools.template.PlatformContractZF;
import com.emotte.order.util.pdfTools.template.PlatformDFHomePage;
import com.emotte.order.util.pdfTools.template.PlatformHomePage;
import com.emotte.order.util.pdfTools.template.PlatformRevisedDFHomePage;
import com.emotte.order.util.pdfTools.template.PlatformZFHomePage;
import com.emotte.order.util.pdfTools.templateModel.ContractModel;
import com.emotte.order.util.pdfTools.templateModel.FranchiseeContractModel;
import com.emotte.order.util.pdfTools.templateModel.HealthContractModel;
import com.emotte.order.util.pdfTools.templateModel.JxPlatformContractRevisedDFModel;
import com.emotte.order.util.pdfTools.templateModel.JxPlatformContractZFModel;
import com.emotte.order.util.pdfTools.templateModel.PlatformContractDFModel;
import com.emotte.order.util.pdfTools.templateModel.PlatformContractDSModel;
import com.emotte.order.util.pdfTools.templateModel.PlatformContractFDSModel;
import com.emotte.order.util.pdfTools.templateModel.PlatformContractRevisedDFModel;
import com.emotte.order.util.pdfTools.templateModel.PlatformContractZFModel;

/**
 * 模板工厂，用于产生模板
 */
public class TemplateFactory {
	Logger log = Logger.getLogger(this.getClass());
	/**
	 * 得到一个模板参数实体对象
	 * @param Id 相关实体的id
	 * @param pdf pdf文件
	 * @param flag 
	 * @param filePath 项目的 WebRoot 路径
	 * @return
	 */
	public BaseTemplate getTemplate(File pdf,int flag,String filePath,Integer preview,Agreement agr){
		BaseTemplate bt = null;
	    Integer	isPeContract = agr.getIsPeContract();//是否为绩效合同 1:是,2或空:否
	    BigDecimal peContractBasic =  agr.getPeContractBasic();//基本工资占比
	    BigDecimal peContractPerformance =  agr.getPeContractPerformance();//绩效工资占比
		try {
			switch (flag) {
			case 1:	//三方合同
				ContractModel contractModel = this.loadSFHT(agr);
			  	bt = new Contract(pdf, contractModel, filePath, preview);
			  	break;
			case 2:	//平台服务合同（代收）
				if (agr.getHomePage() != null && agr.getHomePage() == 1) {
					//首页
					PlatformContractDSModel platformContractDSModel = this.loadDSHT(agr);
					bt = new PlatformHomePage(pdf, platformContractDSModel, filePath, preview);
				} else {
					//全页
					PlatformContractDSModel platformContractDSModel = this.loadDSHT(agr);
					bt = new PlatformContractDS(pdf, platformContractDSModel, filePath, preview);
				}
				break;
//			case 3:		//平台服务合同（非代收）（下线）
//				PlatformContractFDSModel platformContractFDSModel = this.loadFDSHT(agr);
//				bt = new PlatformContractFDS(pdf, platformContractFDSModel, filePath, preview);
//				break;
			case 4:	//加盟商合同
				if (agr.getHomePage() != null && agr.getHomePage() == 1) {
					//首页
					FranchiseeContractModel franchiseeContractFDSModel = this.loadFranchisee(agr);
					bt = new FranchiseeHomePage(pdf, franchiseeContractFDSModel, filePath, preview);
				} else {
					//全页
					FranchiseeContractModel franchiseeContractFDSModel = this.loadFranchisee(agr);
					bt = new FranchiseeContract(pdf, franchiseeContractFDSModel, filePath, preview);
				}
				break;
			case 5:	//代付合同
				if (agr.getHomePage() != null && agr.getHomePage() == 1) {
					//首页
					PlatformContractDFModel platformContractDFModel = this.loadDFHT(agr);
					bt = new PlatformDFHomePage(pdf, platformContractDFModel, filePath, preview);
				} else {
					//全页
					PlatformContractDFModel platformContractDFModel = this.loadDFHT(agr);
					bt = new PlatformContractDF(pdf, platformContractDFModel, filePath, preview);
				}
				break;	
			case 6:	//直付合同
				if(isPeContract != null && isPeContract == 1){
					//绩效合同
					if (agr.getHomePage() != null && agr.getHomePage() == 1) {
						//首页
						JxPlatformContractZFModel jxPlatformContractZFModel = this.loadJxZFHT(agr);
						bt = new JxPlatformZFHomePage(pdf, jxPlatformContractZFModel, filePath, preview);
					}else{
						//全页
						JxPlatformContractZFModel jxPlatformContractZFModel = this.loadJxZFHT(agr);
						bt = new JxPlatformContractZF(pdf, jxPlatformContractZFModel, filePath, preview);
					}
				}else{
					//非绩效合同
					if (agr.getHomePage() != null && agr.getHomePage() == 1) {
						//首页
						PlatformContractZFModel platformContractZFModel = this.loadZFHT(agr);
						bt = new PlatformZFHomePage(pdf, platformContractZFModel, filePath, preview);
					} else {
						//全页
						PlatformContractZFModel platformContractZFModel = this.loadZFHT(agr);
						bt = new PlatformContractZF(pdf, platformContractZFModel, filePath, preview);
					}
				}
				break;
			case 7:	//代付合同
					if(null!=agr.getAdvancePeriod() && 5==agr.getAdvancePeriod()) {
						//代付-海金保理合同 
						if(isPeContract != null && isPeContract == 1){
							//绩效合同
							if (agr.getHomePage() != null && agr.getHomePage() == 1) {
								//首页
								JxPlatformContractRevisedDFModel jxPlatformContractRevisedDFModel = this.loadJxRevisedDF(agr);
								bt = new JxPlatformContractHjblDfHonePage(pdf, jxPlatformContractRevisedDFModel, filePath, preview);
							} else {
								//全页
								JxPlatformContractRevisedDFModel jxPlatformContractRevisedDFModel = this.loadJxRevisedDF(agr);
								bt = new JxPlatformContractHjblDF(pdf, jxPlatformContractRevisedDFModel, filePath, preview);
							}
						}else{
							//非绩效合同
							if (agr.getHomePage() != null && agr.getHomePage() == 1) {
								//首页
								PlatformContractRevisedDFModel platformContractRevisedDFModel = this.loadRevisedDF(agr);
								bt = new PlatformContractHjblDfHonePage(pdf, platformContractRevisedDFModel, filePath, preview);
							} else {
								//全页
								PlatformContractRevisedDFModel platformContractRevisedDFModel = this.loadRevisedDF(agr);
								bt = new PlatformContractHjblDF(pdf, platformContractRevisedDFModel, filePath, preview);
							}
						}
					}else if(null!=agr.getAgreementBusinessType()&&agr.getAgreementBusinessType().equals("2")) {
						//医院陪护合同
						if (agr.getHomePage() != null && agr.getHomePage() == 1) {
							//首页
							HealthContractModel healthContractModel = this.loadHealthsee(agr);
							bt = new HealthHomePage(pdf, healthContractModel, filePath, preview);
						} else {
							//全页
							HealthContractModel healthContractModel2 = this.loadHealthsee(agr);
							bt = new HealthContract(pdf, healthContractModel2, filePath, preview);
						}
					}else {
						//代付合同-修订版
						if(isPeContract != null && isPeContract == 1){
							//绩效合同
							if (agr.getHomePage() != null && agr.getHomePage() == 1) {
								//首页
								JxPlatformContractRevisedDFModel jxPlatformContractRevisedDFModel = this.loadJxRevisedDF(agr);
								bt = new JxPlatformRevisedDFHomePage(pdf, jxPlatformContractRevisedDFModel, filePath, preview);
							} else {
								//全页
								JxPlatformContractRevisedDFModel jxPlatformContractRevisedDFModel = this.loadJxRevisedDF(agr);
								bt = new JxPlatformContractRevisedDF(pdf, jxPlatformContractRevisedDFModel, filePath, preview);
							}
						}else{
							//非绩效合同
							if (agr.getHomePage() != null && agr.getHomePage() == 1) {
								//首页
								PlatformContractRevisedDFModel platformContractRevisedDFModel = this.loadRevisedDF(agr);
								bt = new PlatformRevisedDFHomePage(pdf, platformContractRevisedDFModel, filePath, preview);
							} else {
								//全页
								PlatformContractRevisedDFModel platformContractRevisedDFModel = this.loadRevisedDF(agr);
								bt = new PlatformContractRevisedDF(pdf, platformContractRevisedDFModel, filePath, preview);
							}
						}
					}
				break;
			default:
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(getClass()+".getTemplate()->获取合同异常："+e);
		}
		return bt;
	}
	

	public ContractModel loadSFHT(Agreement agr) throws Exception{
		ContractModel model = new ContractModel();
			model.setCode(agr.getAgreementCode());			//合同编号
			model.setPactA(agr.getPartyA());			    //甲方
		if(agr.getCardType()!=null)						    //证件类型
			model.setCardTypeA(this.getCardTypeCn(agr.getCardType().intValue()));
			model.setCardNumA(agr.getCardNum());			//证件编号
			model.setPhoneA(agr.getMobile());				//联系电话
			model.setAddrA(agr.getCustomerAddress());		//甲方地址
			model.setPactB(agr.getPartyB());				//乙方
			model.setGroupName(agr.getServiceGarage());		//乙方服务站
			model.setAddrB(agr.getPlatformAddress());		//乙方地址
			model.setPactC(agr.getPartyC());				//丙方

		Calendar c = Calendar.getInstance();					
		if(agr.getEffectDate()!=null){						//起始年月日
			Date effectDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getEffectDate());
			c.setTime(effectDate);
			model.setF_y(c.get(Calendar.YEAR)+"");
			model.setF_m((c.get(Calendar.MONTH)+1)+"");
			model.setF_d(c.get(Calendar.DAY_OF_MONTH)+"");
		}
		if(agr.getFinishDate()!=null){					    //截止年月日
			Date finishDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getFinishDate());
			c.setTime(finishDate);
			model.setT_y(c.get(Calendar.YEAR)+"");
			model.setT_m((c.get(Calendar.MONTH)+1)+"");
			model.setT_d(c.get(Calendar.DAY_OF_MONTH)+"");
		}
		if(agr.getPersonManageMoney()!=null)			//服务人员管理费(元/月)
			model.setSalaryMoneyM(agr.getPersonManageMoney().toString());
		if(agr.getCustomerManageMoney()!=null)			//客户管理费(一次)
			model.setSalaryMoneyD(agr.getCustomerManageMoney().toString());
		if(agr.getServiceMoney()!=null)					//服务人员劳务费(元/月)
			model.setServiceMoney(agr.getServiceMoney().toString());
		if(agr.getPayment()!=null)						//本次支付金额
			model.setTotalMoney(agr.getPayment().toString());
		NumberToCN t = new NumberToCN();
		if(model.getTotalMoney()!=null)	
			model.setTotalMoneyCn(t.getCn(model.getTotalMoney()));
		if(agr.getAdvancePeriod()!=null)				//预支付类型
			model.setZhifuType(agr.getAdvancePeriod().toString());
		if(agr.getAgreementPayDate()!=null){			//支付日期
			Date payDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getAgreementPayDate());
			c.setTime(payDate);
			model.setZhifu_y(c.get(Calendar.YEAR)+"");
			model.setZhifu_m((c.get(Calendar.MONTH)+1)+"");
			model.setZhifu_d(c.get(Calendar.DAY_OF_MONTH)+"");
		}
		if(agr.getRemindDay()!=null)
			model.setZhifuDay(agr.getRemindDay().toString());
		
		return model;
		
	}
	//平台服务合同（代收）
	private PlatformContractDSModel loadDSHT(Agreement agr) throws Exception{
			PlatformContractDSModel model = new PlatformContractDSModel();
			Calendar c = Calendar.getInstance();					
			model.setCode(agr.getAgreementCode());			//合同编号
			if(agr.getEffectDate() != null){
				Date createDate  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getContractDate());
				c.setTime(createDate);
				model.setQs_y(c.get(Calendar.YEAR)+"");			//签署年
				model.setQs_m((c.get(Calendar.MONTH)+1)+"");	//签署月
				model.setQs_d(c.get(Calendar.DAY_OF_MONTH)+"");	//签署日
			}
			
			model.setPactA(agr.getPartyA());			    //甲方
			model.setCardTypeA(this.getCardTypeCn(agr.getCardType().intValue()));//甲方证件类型
			model.setCardNumA(agr.getCardNum());			//证件编号
			model.setPhoneA(agr.getMobile());				//联系电话
			model.setAddrA(agr.getCustomerAddress());		//甲方地址
			
			model.setPactB(agr.getPartyB());				//乙方
			model.setGroupName(agr.getServiceGarage());		//乙方服务站
			model.setAddrB(agr.getPlatformAddress());		//乙方地址
			
			model.setPactC(agr.getPartyC());				//丙方
			model.setCardTypeC(this.getCardTypeCn(agr.getCardTypeC().intValue()));//丙方证件类型
			model.setCardNumC(agr.getCardNumC());			//丙方证件编号
			model.setPhoneC(agr.getMobileC());				//丙方联系电话
			model.setAddrC(agr.getWaiterAddress());		    //丙方地址
		if(agr.getServiceCode()!=null){
			model.setServiceNo(agr.getServiceCode().toString());//服务项目序号
		}
		if(agr.getRemarkZdg()!=null){
			model.setRemarkZdg(agr.getRemarkZdg().toString());//钟点工工作时间备注
		}
		if(agr.getEffectDate()!=null){						//起始年月日
			Date effectDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getEffectDate());
			c.setTime(effectDate);
			model.setF_y(c.get(Calendar.YEAR)+"");
			model.setF_m((c.get(Calendar.MONTH)+1)+"");
			model.setF_d(c.get(Calendar.DAY_OF_MONTH)+"");
		}
		if(agr.getFinishDate()!=null){					    //截止年月日
			Date finishDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getFinishDate());
			c.setTime(finishDate);
			model.setT_y(c.get(Calendar.YEAR)+"");
			model.setT_m((c.get(Calendar.MONTH)+1)+"");
			model.setT_d(c.get(Calendar.DAY_OF_MONTH)+"");
		}
		if(agr.getPersonManageMoney()!=null)			//服务人员管理费(元/月)  收费标准（丙向乙支付）
			model.setSalaryFee(agr.getPersonManageMoney().toString());
		if(agr.getCustomerManageMoney()!=null)			//客户管理费(一次)      支付标准（甲向丙支付）
			model.setPayA(agr.getCustomerManageMoney().toString());
		if(agr.getServiceMoney()!=null)					//服务人员劳务费(元/月)  平台信息费（甲向乙支付）
			model.setMessFee(agr.getServiceMoney().toString());
		NumberToCN t = new NumberToCN();
		if(agr.getAdvancePeriod()!=null)				//预支付类型
			model.setZhifuType(agr.getAdvancePeriod().toString());
		if(agr.getAgreementPayDate()!=null && agr.getAdvancePeriod()!=null && agr.getAdvancePeriod() == 1){			//支付日期
			Date payDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getAgreementPayDate());
			c.setTime(payDate);
			model.setZhifu_y(c.get(Calendar.YEAR)+"");
			model.setZhifu_m((c.get(Calendar.MONTH)+1)+"");
			model.setZhifu_d(c.get(Calendar.DAY_OF_MONTH)+"");
		}else {
			model.setZhifuDate(agr.getZhifuRemark());
		}
		if(agr.getRemindDay()!=null){					//几日之前支付
			model.setZhifuDay(agr.getRemindDay().toString());
		}
		if(agr.getChargeTimes()!=null){					//几倍
			model.setSalaryJb(agr.getChargeTimes().toString());
		}
		model.setOthers(agr.getOtherMatters()); 		//其他约定全行
		model.setRemarkOthers(agr.getOtherMethods());   //其他服务
		model.setAddrService(agr.getServiceAddress());  //服务地址
		return model;
	
	}

	//平台服务合同（非代收）
	private PlatformContractFDSModel loadFDSHT(Agreement agr) throws Exception{
			PlatformContractFDSModel model = new PlatformContractFDSModel();
			Calendar c = Calendar.getInstance();					
			model.setCode(agr.getAgreementCode());			//合同编号
			if(agr.getEffectDate() != null){
				Date createDate  =   new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getEffectDate());
				c.setTime(createDate);
				model.setQs_y(c.get(Calendar.YEAR)+"");			//签署年
				model.setQs_m((c.get(Calendar.MONTH)+1)+"");	//签署月
				model.setQs_d(c.get(Calendar.DAY_OF_MONTH)+"");	//签署日
			}
			
			model.setPactA(agr.getPartyA());			    //甲方
			model.setCardTypeA(this.getCardTypeCn(agr.getCardType().intValue()));//甲方证件类型
			model.setCardNumA(agr.getCardNum());			//证件编号
			model.setPhoneA(agr.getMobile());				//联系电话
			model.setAddrA(agr.getCustomerAddress());		//甲方地址
			
			model.setPactB(agr.getPartyB());				//乙方
			model.setGroupName(agr.getServiceGarage());		//乙方服务站
			model.setAddrB(agr.getPlatformAddress());		//乙方地址
			
			model.setPactC(agr.getPartyC());				//丙方
			model.setCardTypeC(this.getCardTypeCn(agr.getCardTypeC().intValue()));//丙方证件类型
			model.setCardNumC(agr.getCardNumC());			//丙方证件编号
			model.setPhoneC(agr.getMobileC());				//丙方联系电话
			model.setAddrC(agr.getWaiterAddress());		    //丙方地址
		if(agr.getServiceCode()!=null){
			model.setServiceNo(agr.getServiceCode().toString());//服务项目序号
		}	
		if(agr.getRemarkZdg()!=null){
			model.setRemarkZdg(agr.getRemarkZdg().toString());//钟点工工作时间备注
		}
		if(agr.getEffectDate()!=null){						//起始年月日
			Date effectDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getEffectDate());
			c.setTime(effectDate);
			model.setF_y(c.get(Calendar.YEAR)+"");
			model.setF_m((c.get(Calendar.MONTH)+1)+"");
			model.setF_d(c.get(Calendar.DAY_OF_MONTH)+"");
		}
		if(agr.getFinishDate()!=null){					    //截止年月日
			Date finishDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getFinishDate());
			c.setTime(finishDate);
			model.setT_y(c.get(Calendar.YEAR)+"");
			model.setT_m((c.get(Calendar.MONTH)+1)+"");
			model.setT_d(c.get(Calendar.DAY_OF_MONTH)+"");
		}
		if(agr.getPersonManageMoney()!=null)			//服务人员管理费(元/月)  收费标准（丙向乙支付）
			model.setSalaryFee(agr.getPersonManageMoney().toString());
		if(agr.getCustomerManageMoney()!=null)			//客户管理费(一次)      支付标准（甲向丙支付）
			model.setPayA(agr.getCustomerManageMoney().toString());
		if(agr.getServiceMoney()!=null)					//服务人员劳务费(元/月)  平台信息费（甲向乙支付）
			model.setMessFee(agr.getServiceMoney().toString());
		
		if(agr.getChargeTimes()!=null){					//几倍
			model.setSalaryJb(agr.getChargeTimes().toString());
		}
		model.setOthers(agr.getOtherMatters()); 		//其他约定全行
		model.setRemarkOthers(agr.getOtherMethods());   //其他服务
		model.setAddrService(agr.getServiceAddress());  //服务地址
		return model;
	}
	
	/**
	 * 根据证件类型号码返回中文类型名称
	 * @param type
	 * @return
	 */
	private String getCardTypeCn(int type){//证件类别（1身份证 2护照 3驾照 ）
		if(type==1)
			return "身份证";
		else if(type==2)
			return "护照";
		else if(type==3)
			return "驾照";
		else if(type==4)
			return "营业执照";
		else
			return "";
	}
	
	/**
	 * 加盟商合同
	 * @param agr
	 * @return
	 */
	private FranchiseeContractModel loadFranchisee(Agreement agr) throws Exception{
			FranchiseeContractModel model = new FranchiseeContractModel();
			Calendar c = Calendar.getInstance();					
			model.setCode(agr.getAgreementCode());			//合同编号
			if(agr.getEffectDate() != null){
				Date createDate  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getContractDate());
				c.setTime(createDate);
				model.setQs_y(c.get(Calendar.YEAR)+"");			//签署年
				model.setQs_m((c.get(Calendar.MONTH)+1)+"");	//签署月
				model.setQs_d(c.get(Calendar.DAY_OF_MONTH)+"");	//签署日
			}
			
			model.setPactA(agr.getPartyA());			    //甲方
			model.setCardTypeA(this.getCardTypeCn(agr.getCardType().intValue()));//甲方证件类型
			model.setCardNumA(agr.getCardNum());			//证件编号
			model.setPhoneA(agr.getMobile());				//联系电话
			model.setAddrA(agr.getCustomerAddress());		//甲方地址
			
			model.setPactB(agr.getPartyB());				//乙方
			model.setGroupName(agr.getServiceGarage());		//乙方服务站
			model.setAddrB(agr.getPlatformAddress());		//乙方地址
			
			model.setPactC(agr.getPartyC());				//丙方
			model.setCardTypeC(this.getCardTypeCn(agr.getCardTypeC().intValue()));//丙方证件类型
			model.setCardNumC(agr.getCardNumC());			//丙方证件编号
			model.setPhoneC(agr.getMobileC());				//丙方联系电话
			model.setAddrC(agr.getWaiterAddress());		    //丙方地址
		if(agr.getServiceCode()!=null){
			model.setServiceNo(agr.getServiceCode().toString());//服务项目序号
		}
		if(agr.getRemarkZdg()!=null){
			model.setRemarkZdg(agr.getRemarkZdg().toString());//钟点工工作时间备注
		}
		if(agr.getEffectDate()!=null){						//起始年月日
			Date effectDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getEffectDate());
			c.setTime(effectDate);
			model.setF_y(c.get(Calendar.YEAR)+"");
			model.setF_m((c.get(Calendar.MONTH)+1)+"");
			model.setF_d(c.get(Calendar.DAY_OF_MONTH)+"");
		}
		if(agr.getFinishDate()!=null){					    //截止年月日
			Date finishDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getFinishDate());
			c.setTime(finishDate);
			model.setT_y(c.get(Calendar.YEAR)+"");
			model.setT_m((c.get(Calendar.MONTH)+1)+"");
			model.setT_d(c.get(Calendar.DAY_OF_MONTH)+"");
		}
		if(agr.getPersonManageMoney()!=null)			//服务人员管理费(元/月)  收费标准（丙向乙支付）
			model.setSalaryFee(agr.getPersonManageMoney().toString());
		if(agr.getCustomerManageMoney()!=null)			//客户管理费(一次)      支付标准（甲向丙支付）
			model.setPayA(agr.getCustomerManageMoney().toString());
		if(agr.getServiceMoney()!=null)					//服务人员劳务费(元/月)  平台信息费（甲向乙支付）
			model.setMessFee(agr.getServiceMoney().toString());
		NumberToCN t = new NumberToCN();
		if(agr.getAdvancePeriod()!=null)				//预支付类型
			model.setZhifuType(agr.getAdvancePeriod().toString());
		if(agr.getAgreementPayDate()!=null && agr.getAdvancePeriod()!=null && agr.getAdvancePeriod() == 1){			//支付日期
			Date payDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getAgreementPayDate());
			c.setTime(payDate);
			model.setZhifu_y(c.get(Calendar.YEAR)+"");
			model.setZhifu_m((c.get(Calendar.MONTH)+1)+"");
			model.setZhifu_d(c.get(Calendar.DAY_OF_MONTH)+"");
		}else {
			model.setZhifuDate(agr.getZhifuRemark());
		}
		if(agr.getRemindDay()!=null){					//几日之前支付
			model.setZhifuDay(agr.getRemindDay().toString());
		}
		if(agr.getChargeTimes()!=null){					//几倍
			model.setSalaryJb(agr.getChargeTimes().toString());
		}
		model.setOthers(agr.getOtherMatters()); 		//其他约定全行
		model.setRemarkOthers(agr.getOtherMethods());   //其他服务
		model.setAddrService(agr.getServiceAddress());  //服务地址
		return model;

	}

	
	//平台服务代付合同
	private PlatformContractDFModel loadDFHT(Agreement agr) throws Exception{
		PlatformContractDFModel model = new PlatformContractDFModel();
			Calendar c = Calendar.getInstance();					
			model.setCode(agr.getAgreementCode());			//合同编号
			if(agr.getEffectDate() != null){
				Date createDate  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getContractDate());
				c.setTime(createDate);
				model.setQs_y(c.get(Calendar.YEAR)+"");			//签署年
				model.setQs_m((c.get(Calendar.MONTH)+1)+"");	//签署月
				model.setQs_d(c.get(Calendar.DAY_OF_MONTH)+"");	//签署日
			}
			
			model.setPactA(agr.getPartyA());			    //甲方
			model.setCardTypeA(this.getCardTypeCn(agr.getCardType().intValue()));//甲方证件类型
			model.setCardNumA(agr.getCardNum());			//证件编号
			model.setPhoneA(agr.getMobile());				//联系电话
			model.setAddrA(agr.getCustomerAddress());		//甲方地址
			
			model.setPactB(agr.getPartyB());				//乙方
			model.setGroupName(agr.getServiceGarage());		//乙方服务站
			model.setAddrB(agr.getPlatformAddress());		//乙方地址
			
			model.setPactC(agr.getPartyC());				//丙方
			model.setCardTypeC(this.getCardTypeCn(agr.getCardTypeC().intValue()));//丙方证件类型
			model.setCardNumC(agr.getCardNumC());			//丙方证件编号
			model.setPhoneC(agr.getMobileC());				//丙方联系电话
			model.setAddrC(agr.getWaiterAddress());		    //丙方地址
		if(agr.getServiceCode()!=null){
			model.setServiceNo(agr.getServiceCode().toString());//服务项目序号
		}
		if(agr.getRemarkZdg()!=null){
			model.setRemarkZdg(agr.getRemarkZdg().toString());//钟点工工作时间备注
		}
		if(agr.getEffectDate()!=null){						//起始年月日
			Date effectDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getEffectDate());
			c.setTime(effectDate);
			model.setF_y(c.get(Calendar.YEAR)+"");
			model.setF_m((c.get(Calendar.MONTH)+1)+"");
			model.setF_d(c.get(Calendar.DAY_OF_MONTH)+"");
		}
		if(agr.getFinishDate()!=null){					    //截止年月日
			Date finishDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getFinishDate());
			c.setTime(finishDate);
			model.setT_y(c.get(Calendar.YEAR)+"");
			model.setT_m((c.get(Calendar.MONTH)+1)+"");
			model.setT_d(c.get(Calendar.DAY_OF_MONTH)+"");
		}
		if(agr.getPersonManageMoney()!=null)			//服务人员管理费(元/月)  收费标准（丙向乙支付）
			model.setSalaryFee(agr.getPersonManageMoney().toString());
		if(agr.getCustomerManageMoney()!=null)			//客户管理费(一次)      支付标准（甲向丙支付）
			model.setPayA(agr.getCustomerManageMoney().toString());
		if(agr.getServiceMoney()!=null)					//服务人员劳务费(元/月)  平台信息费（甲向乙支付）
			model.setMessFee(agr.getServiceMoney().toString());
		NumberToCN t = new NumberToCN();
		if(agr.getAdvancePeriod()!=null)				//预支付类型
			model.setZhifuType(agr.getAdvancePeriod().toString());
		if(agr.getAgreementPayDate()!=null && agr.getAdvancePeriod()!=null && agr.getAdvancePeriod() == 1){			//支付日期
			Date payDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getAgreementPayDate());
			c.setTime(payDate);
			model.setZhifu_y(c.get(Calendar.YEAR)+"");
			model.setZhifu_m((c.get(Calendar.MONTH)+1)+"");
			model.setZhifu_d(c.get(Calendar.DAY_OF_MONTH)+"");
		}else {
			model.setZhifuDate(agr.getZhifuRemark());
		}
		if(agr.getRemindDay()!=null){					//几日之前支付
			model.setZhifuDay(agr.getRemindDay().toString());
		}
		if(agr.getChargeTimes()!=null){					//几倍
			model.setSalaryJb(agr.getChargeTimes().toString());
		}
		model.setOthers(agr.getOtherMatters()); 		//其他约定全行
		model.setRemarkOthers(agr.getOtherMethods());   //其他服务
		model.setAddrService(agr.getServiceAddress());  //服务地址
		return model;
	
	}
	
		//平台服务直付合同
		private PlatformContractZFModel loadZFHT(Agreement agr) throws Exception{
			PlatformContractZFModel model = new PlatformContractZFModel();
				Calendar c = Calendar.getInstance();					
				model.setCode(agr.getAgreementCode());			//合同编号
				if(agr.getEffectDate() != null){
					Date createDate  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getContractDate());
					c.setTime(createDate);
					model.setQs_y(c.get(Calendar.YEAR)+"");			//签署年
					model.setQs_m((c.get(Calendar.MONTH)+1)+"");	//签署月
					model.setQs_d(c.get(Calendar.DAY_OF_MONTH)+"");	//签署日
				}
				
				model.setPactA(agr.getPartyA());			    //甲方
				model.setCardTypeA(this.getCardTypeCn(agr.getCardType().intValue()));//甲方证件类型
				model.setCardNumA(agr.getCardNum());			//证件编号
				model.setPhoneA(agr.getMobile());				//联系电话
				model.setAddrA(agr.getCustomerAddress());		//甲方地址
				
				model.setPactB(agr.getPartyB());				//乙方
				model.setGroupName(agr.getServiceGarage());		//乙方服务站
				model.setAddrB(agr.getPlatformAddress());		//乙方地址
				
				model.setPactC(agr.getPartyC());				//丙方
				model.setCardTypeC(this.getCardTypeCn(agr.getCardTypeC().intValue()));//丙方证件类型
				model.setCardNumC(agr.getCardNumC());			//丙方证件编号
				model.setPhoneC(agr.getMobileC());				//丙方联系电话
				model.setAddrC(agr.getWaiterAddress());		    //丙方地址
			if(agr.getServiceCode()!=null){
				model.setServiceNo(agr.getServiceCode().toString());//服务项目序号
			}
			if(agr.getRemarkZdg()!=null){
				model.setRemarkZdg(agr.getRemarkZdg().toString());//钟点工工作时间备注
			}
			if(agr.getEffectDate()!=null){						//起始年月日
				Date effectDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getEffectDate());
				c.setTime(effectDate);
				model.setF_y(c.get(Calendar.YEAR)+"");
				model.setF_m((c.get(Calendar.MONTH)+1)+"");
				model.setF_d(c.get(Calendar.DAY_OF_MONTH)+"");
			}
			if(agr.getFinishDate()!=null){					    //截止年月日
				Date finishDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getFinishDate());
				c.setTime(finishDate);
				model.setT_y(c.get(Calendar.YEAR)+"");
				model.setT_m((c.get(Calendar.MONTH)+1)+"");
				model.setT_d(c.get(Calendar.DAY_OF_MONTH)+"");
			}
			if(agr.getPersonManageMoney()!=null)			//服务人员管理费(元/月)  收费标准（丙向乙支付）
				model.setSalaryFee(agr.getPersonManageMoney().toString());
			if(agr.getCustomerManageMoney()!=null)			//客户管理费(一次)      支付标准（甲向丙支付）
				model.setPayA(agr.getCustomerManageMoney().toString());
			if(agr.getServiceMoney()!=null)					//服务人员劳务费(元/月)  平台信息费（甲向乙支付）
				model.setMessFee(agr.getServiceMoney().toString());
			NumberToCN t = new NumberToCN();
			/*if(agr.getAdvancePeriod()!=null)				//预支付类型
				model.setZhifuType(agr.getAdvancePeriod().toString());
			if(agr.getAgreementPayDate()!=null && agr.getAdvancePeriod()!=null && agr.getAdvancePeriod() == 1){			//支付日期
				Date payDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getAgreementPayDate());
				c.setTime(payDate);
				model.setZhifu_y(c.get(Calendar.YEAR)+"");
				model.setZhifu_m((c.get(Calendar.MONTH)+1)+"");
				model.setZhifu_d(c.get(Calendar.DAY_OF_MONTH)+"");
			}else {
				model.setZhifuDate(agr.getZhifuRemark());
			}
			if(agr.getRemindDay()!=null){					//几日之前支付
				model.setZhifuDay(agr.getRemindDay().toString());
			}*/
			if(agr.getChargeTimes()!=null){					//几倍
				model.setSalaryJb(agr.getChargeTimes().toString());
			}
			model.setOthers(agr.getOtherMatters()); 		//其他约定全行
			model.setRemarkOthers(agr.getOtherMethods());   //其他服务
			model.setAddrService(agr.getServiceAddress());  //服务地址
			return model;
		
		}
		
		//平台服务代付合同(修订版)
		private PlatformContractRevisedDFModel loadRevisedDF(Agreement agr) throws Exception{
			PlatformContractRevisedDFModel model = new PlatformContractRevisedDFModel();
				Calendar c = Calendar.getInstance();					
				model.setCode(agr.getAgreementCode());			//合同编号
				if(agr.getEffectDate() != null){
					Date createDate  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getContractDate());
					c.setTime(createDate);
					model.setQs_y(c.get(Calendar.YEAR)+"");			//签署年
					model.setQs_m((c.get(Calendar.MONTH)+1)+"");	//签署月
					model.setQs_d(c.get(Calendar.DAY_OF_MONTH)+"");	//签署日
				}
				
				model.setPactA(agr.getPartyA());			    //甲方
				model.setCardTypeA(this.getCardTypeCn(agr.getCardType().intValue()));//甲方证件类型
				model.setCardNumA(agr.getCardNum());			//证件编号
				model.setPhoneA(agr.getMobile());				//联系电话
				model.setAddrA(agr.getCustomerAddress());		//甲方地址
				
				model.setPactB(agr.getPartyB());				//乙方
				model.setGroupName(agr.getServiceGarage());		//乙方服务站
				model.setAddrB(agr.getPlatformAddress());		//乙方地址
				
				model.setPactC(agr.getPartyC());				//丙方
				model.setCardTypeC(this.getCardTypeCn(agr.getCardTypeC().intValue()));//丙方证件类型
				model.setCardNumC(agr.getCardNumC());			//丙方证件编号
				model.setPhoneC(agr.getMobileC());				//丙方联系电话
				model.setAddrC(agr.getWaiterAddress());		    //丙方地址
			if(agr.getServiceCode()!=null){
				model.setServiceNo(agr.getServiceCode().toString());//服务项目序号
			}
			if(agr.getRemarkZdg()!=null){
				model.setRemarkZdg(agr.getRemarkZdg().toString());//钟点工工作时间备注
			}
			if(agr.getEffectDate()!=null){						//起始年月日
				Date effectDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getEffectDate());
				c.setTime(effectDate);
				model.setF_y(c.get(Calendar.YEAR)+"");
				model.setF_m((c.get(Calendar.MONTH)+1)+"");
				model.setF_d(c.get(Calendar.DAY_OF_MONTH)+"");
			}
			if(agr.getFinishDate()!=null){					    //截止年月日
				Date finishDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getFinishDate());
				c.setTime(finishDate);
				model.setT_y(c.get(Calendar.YEAR)+"");
				model.setT_m((c.get(Calendar.MONTH)+1)+"");
				model.setT_d(c.get(Calendar.DAY_OF_MONTH)+"");
			}
			if(agr.getPersonManageMoney()!=null)			//服务人员管理费(元/月)  收费标准（丙向乙支付）
				model.setSalaryFee(agr.getPersonManageMoney().toString());
			if(agr.getCustomerManageMoney()!=null)			//客户管理费(一次)      支付标准（甲向丙支付）
				model.setPayA(agr.getCustomerManageMoney().toString());
			if(agr.getServiceMoney()!=null)					//服务人员劳务费(元/月)  平台信息费（甲向乙支付）
				model.setMessFee(agr.getServiceMoney().toString());
			if(agr.getAdvancePeriod()!=null)				//预支付类型
				model.setZhifuType(agr.getAdvancePeriod().toString());
			if (agr.getAgreementPayDate()!=null && agr.getAdvancePeriod()!=null && agr.getAdvancePeriod() == 1) {
				NumberToCN t1 = new NumberToCN();
				Date payDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getAgreementPayDate());
				c.setTime(payDate);
				model.setZhifu_y_1(c.get(Calendar.YEAR)+"");
				model.setZhifu_m_1((c.get(Calendar.MONTH)+1)+"");
				model.setZhifu_d_1(c.get(Calendar.DAY_OF_MONTH)+"");
				model.setAllPay_1(agr.getAllPay().toString());
				model.setAllPayCN_1(t1.getCn(agr.getAllPay().toString()));
			} else if (agr.getAgreementPayDate()!=null && agr.getAdvancePeriod()!=null && agr.getAdvancePeriod() == 2){
				NumberToCN t2 = new NumberToCN();
				Date payDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getAgreementPayDate());
				c.setTime(payDate);
				model.setZhifu_y_2(c.get(Calendar.YEAR)+"");
				model.setZhifu_m_2((c.get(Calendar.MONTH)+1)+"");
				model.setZhifu_d_2(c.get(Calendar.DAY_OF_MONTH)+"");
				model.setAllPay_2(agr.getAllPay().toString());
				model.setAllPayCN_2(t2.getCn(agr.getAllPay().toString()));
			}else if (agr.getAgreementPayDate()!=null && agr.getAdvancePeriod()!=null && agr.getAdvancePeriod() == 3){
				NumberToCN t3 = new NumberToCN();
				Date payDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getAgreementPayDate());
				c.setTime(payDate);
				model.setZhifu_y_3(c.get(Calendar.YEAR)+"");
				model.setZhifu_m_3((c.get(Calendar.MONTH)+1)+"");
				model.setZhifu_d_3(c.get(Calendar.DAY_OF_MONTH)+"");
				model.setAllPay_3(agr.getAllPay().toString());
				model.setAllPayCN_3(t3.getCn(agr.getAllPay().toString()));
			}else if (agr.getAgreementPayDate()!=null && agr.getAdvancePeriod()!=null && agr.getAdvancePeriod() == 4){
				Date payDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getAgreementPayDate());
				c.setTime(payDate);
				model.setZhifu_y_4(c.get(Calendar.YEAR)+"");
				model.setZhifu_m_4((c.get(Calendar.MONTH)+1)+"");
				model.setZhifu_d_4(c.get(Calendar.DAY_OF_MONTH)+"");
			}
			/*if(agr.getAgreementPayDate()!=null && agr.getAdvancePeriod()!=null && agr.getAdvancePeriod() == 1){			//支付日期
				Date payDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getAgreementPayDate());
				c.setTime(payDate);
				model.setZhifu_y(c.get(Calendar.YEAR)+"");
				model.setZhifu_m((c.get(Calendar.MONTH)+1)+"");
				model.setZhifu_d(c.get(Calendar.DAY_OF_MONTH)+"");
			}else {
				model.setZhifuDate(agr.getZhifuRemark());
			}
			
			if (agr.getAllPay() != null) {
				model.setAllPay(agr.getAllPay().toString());
				model.setAllPayCN(t.getCn(agr.getAllPay().toString()));
			}
			if(agr.getRemindDay()!=null){					//几日之前支付
				model.setZhifuDay(agr.getRemindDay().toString());
			}*/
			if (agr.getOtherWay() != null) {
				model.setOtherWay(agr.getOtherWay());
			}
			if(agr.getChargeTimes()!=null){					//几倍
				model.setSalaryJb(agr.getChargeTimes().toString());
			}
			model.setOthers(agr.getOtherMatters()); 		//其他约定全行
			model.setRemarkOthers(agr.getOtherMethods());   //其他服务
			model.setAddrService(agr.getServiceAddress());  //服务地址
			if(agr.getLegalRepresentative()!=null){
				model.setLegalRepresentative(agr.getLegalRepresentative());;//法定代表人
			}
			if(agr.getPrepaidMonths()!=null){
				model.setPrepaidMonths(agr.getPrepaidMonths().toString());
			}
			if(agr.getPrepaidMoney()!=null){
				model.setPrepaidMoney(agr.getPrepaidMoney().toString());
			}
			if(agr.getInstPrepaidMonths()!=null){
				model.setInstPrepaidMonths(agr.getInstPrepaidMonths().toString());
			}
			if(agr.getInstPrepaidMoney()!=null){
				model.setInstPrepaidMoney(agr.getInstPrepaidMoney().toString());
			}
			if(agr.getLimitDays()!=null){
				model.setLimitDays(agr.getLimitDays().toString());
			}
			if(agr.getAccountName()!=null){
				model.setAccountName(agr.getAccountName());
			}
			if(agr.getAccountNum()!=null){
				model.setAccountNum(agr.getAccountNum());
			}
			if(agr.getAccountBank()!=null){
				model.setAccountBank(agr.getAccountBank());
			}
			return model;
		
		}
		
		
		
		/**
		 * 医疗陪护合同
		 * @param agr
		 * @return
		 */
		private HealthContractModel loadHealthsee(Agreement agr) throws Exception{
			HealthContractModel model = new HealthContractModel();
				Calendar c = Calendar.getInstance();					
				model.setCode(agr.getAgreementCode());			//合同编号
				if(agr.getEffectDate() != null){
					Date createDate  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getContractDate());
					c.setTime(createDate);
					model.setQs_y(c.get(Calendar.YEAR)+"");			//签署年
					model.setQs_m((c.get(Calendar.MONTH)+1)+"");	//签署月
					model.setQs_d(c.get(Calendar.DAY_OF_MONTH)+"");	//签署日
				}
				
				model.setPactA(agr.getPartyA());			    //甲方
				model.setCardTypeA(this.getCardTypeCn(agr.getCardType().intValue()));//甲方证件类型
				model.setCardNumA(agr.getCardNum());			//证件编号
				model.setPhoneA(agr.getMobile());				//联系电话
				model.setAddrA(agr.getCustomerAddress());		//甲方家庭地址
				model.setServiceAddrA(agr.getCustomerserviceAddress());   //甲方家庭服务地址
				model.setHospitalizationNum(agr.getHospitalizationNum());  //住院号
				model.setDepartments(agr.getDepartments());                //科室
				model.setRoomNumber(agr.getRoomNumber());                  //病房号
				model.setBedNumber_a(agr.getBedNumber_a());                   //床号
				model.setBedNumber_b(agr.getBedNumber_b()); 
				model.setConsumerstate(agr.getConsumerstate());           //服务对象现状
				
				model.setPactB(agr.getPartyB());				//乙方
				model.setGroupName(agr.getServiceGarage());		//乙方服务站
				model.setAddrB(agr.getPlatformAddress());		//乙方地址
				model.setPhoneB(agr.getMobileB());
				
				model.setPactC(agr.getPartyC());				//丙方
				model.setCardTypeC(this.getCardTypeCn(agr.getCardTypeC().intValue()));//丙方证件类型
				model.setCardNumC(agr.getCardNumC());			//丙方证件编号
				model.setPhoneC(agr.getMobileC());				//丙方联系电话
				model.setAddrC(agr.getWaiterAddress());		    //丙方地址
				
				model.setConsumersName(agr.getConsumersName()); //服务对象姓名
				model.setConsumersCard(agr.getConsumersCard());  //服务对象身份证
				
				model.setCustconsumerRelation(agr.getCustconsumerRelation().toString());  //与服务对象的关系
				
				
				if(StringUtils.equals("", agr.getRelation_relatives()) || agr.getRelation_relatives() == null) {
					model.setRelation_relatives("");
				}else {
					model.setRelation_relatives(agr.getRelation_relatives().toString());  //具体亲属关系
				}
				
				if(StringUtils.equals("", agr.getRelation_entrust()) || agr.getRelation_entrust() == null) {
					model.setRelation_entrust("");
				}else {
					model.setRelation_entrust(agr.getRelation_entrust().toString());  //具体委托关系
				}
				
				
				if(agr.getBirthPeriod()!= null){
					Date birthPeriodDate  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getBirthPeriod());
					c.setTime(birthPeriodDate);
					model.setChildbirth_y(c.get(Calendar.YEAR)+"");			//预产 年
					model.setChildbirth_m((c.get(Calendar.MONTH)+1)+"");	//预产 月
					model.setChildbirth_d(c.get(Calendar.DAY_OF_MONTH)+"");	//预产 日
				}
				
				model.setSpecialConsiderations(agr.getSpecialConsiderations().toString());  //特殊注意事项
				
				
				model.setServiceItems(agr.getServiceItems().toString());  //甲方选择服务项目
				
				
				if(agr.getServiceStarttime()!= null){
					Date birthPeriodDate  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getServiceStarttime());
					c.setTime(birthPeriodDate);
					model.setServiceStart_y(c.get(Calendar.YEAR)+"");			//服务开始 年
					model.setServiceStart_m((c.get(Calendar.MONTH)+1)+"");	//服务开始 月
					model.setServiceStart_r(c.get(Calendar.DAY_OF_MONTH)+"");	//服务开始 日
				}
				
				
				//临床陪护暂定天数
				model.setHostsitDay(agr.getHostsitDay().toString()); 
				//服务形式
				model.setServiceFormat(agr.getServiceFormat().toString());
				//分娩方式
				model.setDeliveryMode(agr.getDeliveryMode().toString());
				//更换次数
				model.setReplaceNumber(agr.getReplaceNumber().toString());
				
				//收费标准
				model.setServiceprojectStandard(agr.getServiceprojectStandard().toString());
				
				//向乙方支付费用
				model.setPayment(agr.getAllPay().toString());
				//乙方收款账户
				model.setPartyBaccountName(agr.getPartyBaccountName().toString());
				model.setPartyBaccountNum(agr.getPartyBaccountNum().toString());
				model.setPartyBaccountBank(agr.getPartyBaccountBank().toString());
			
			return model;

		}
		
		//绩效-平台服务直付合同
		private JxPlatformContractZFModel loadJxZFHT(Agreement agr) throws Exception{
			JxPlatformContractZFModel model = new JxPlatformContractZFModel();
				Calendar c = Calendar.getInstance();					
				model.setCode(agr.getAgreementCode());			//合同编号
				if(agr.getEffectDate() != null){
					Date createDate  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getContractDate());
					c.setTime(createDate);
					model.setQs_y(c.get(Calendar.YEAR)+"");			//签署年
					model.setQs_m((c.get(Calendar.MONTH)+1)+"");	//签署月
					model.setQs_d(c.get(Calendar.DAY_OF_MONTH)+"");	//签署日
				}
				model.setPactA(agr.getPartyA());			    //甲方
				model.setCardTypeA(this.getCardTypeCn(agr.getCardType().intValue()));//甲方证件类型
				model.setCardNumA(agr.getCardNum());			//证件编号
				model.setPhoneA(agr.getMobile());				//联系电话
				model.setAddrA(agr.getCustomerAddress());		//甲方地址
				model.setPactB(agr.getPartyB());				//乙方
				model.setGroupName(agr.getServiceGarage());		//乙方服务站
				model.setAddrB(agr.getPlatformAddress());		//乙方地址
				model.setPactC(agr.getPartyC());				//丙方
				model.setCardTypeC(this.getCardTypeCn(agr.getCardTypeC().intValue()));//丙方证件类型
				model.setCardNumC(agr.getCardNumC());			//丙方证件编号
				model.setPhoneC(agr.getMobileC());				//丙方联系电话
				model.setAddrC(agr.getWaiterAddress());		    //丙方地址
			if(agr.getServiceCode()!=null){
				model.setServiceNo(agr.getServiceCode().toString());//服务项目序号
			}
			if(agr.getRemarkZdg()!=null){
				model.setRemarkZdg(agr.getRemarkZdg().toString());//钟点工工作时间备注
			}
			if(agr.getEffectDate()!=null){						//起始年月日
				Date effectDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getEffectDate());
				c.setTime(effectDate);
				model.setF_y(c.get(Calendar.YEAR)+"");
				model.setF_m((c.get(Calendar.MONTH)+1)+"");
				model.setF_d(c.get(Calendar.DAY_OF_MONTH)+"");
			}
			if(agr.getFinishDate()!=null){					    //截止年月日
				Date finishDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getFinishDate());
				c.setTime(finishDate);
				model.setT_y(c.get(Calendar.YEAR)+"");
				model.setT_m((c.get(Calendar.MONTH)+1)+"");
				model.setT_d(c.get(Calendar.DAY_OF_MONTH)+"");
			}
			if(agr.getPersonManageMoney()!=null)			//服务人员管理费(元/月)  收费标准（丙向乙支付）
				model.setSalaryFee(agr.getPersonManageMoney().toString());
			if(agr.getCustomerManageMoney()!=null)			//客户管理费(一次)      支付标准（甲向丙支付）
				model.setPayA(agr.getCustomerManageMoney().toString());
			if(agr.getServiceMoney()!=null)					//服务人员劳务费(元/月)  平台信息费（甲向乙支付）
				model.setMessFee(agr.getServiceMoney().toString());
			if(agr.getChargeTimes()!=null){					//几倍
				model.setSalaryJb(agr.getChargeTimes().toString());
			}
			model.setOthers(agr.getOtherMatters()); 		//其他约定全行
			model.setRemarkOthers(agr.getOtherMethods());   //其他服务
			model.setAddrService(agr.getServiceAddress());  //服务地址
			return model;
		}
		
		//绩效-平台服务代付合同(修订版)
				private JxPlatformContractRevisedDFModel loadJxRevisedDF(Agreement agr) throws Exception{
					DecimalFormat df = new DecimalFormat("#######0.##");
					JxPlatformContractRevisedDFModel model = new JxPlatformContractRevisedDFModel();
						Calendar c = Calendar.getInstance();					
						model.setCode(agr.getAgreementCode());			//合同编号
						if(agr.getEffectDate() != null){
							Date createDate  =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getContractDate());
							c.setTime(createDate);
							model.setQs_y(c.get(Calendar.YEAR)+"");			//签署年
							model.setQs_m((c.get(Calendar.MONTH)+1)+"");	//签署月
							model.setQs_d(c.get(Calendar.DAY_OF_MONTH)+"");	//签署日
						}
						
						model.setPactA(agr.getPartyA());			    //甲方
						model.setCardTypeA(this.getCardTypeCn(agr.getCardType().intValue()));//甲方证件类型
						model.setCardNumA(agr.getCardNum());			//证件编号
						model.setPhoneA(agr.getMobile());				//联系电话
						model.setAddrA(agr.getCustomerAddress());		//甲方地址
						
						model.setPactB(agr.getPartyB());				//乙方
						model.setGroupName(agr.getServiceGarage());		//乙方服务站
						model.setAddrB(agr.getPlatformAddress());		//乙方地址
						
						model.setPactC(agr.getPartyC());				//丙方
						model.setCardTypeC(this.getCardTypeCn(agr.getCardTypeC().intValue()));//丙方证件类型
						model.setCardNumC(agr.getCardNumC());			//丙方证件编号
						model.setPhoneC(agr.getMobileC());				//丙方联系电话
						model.setAddrC(agr.getWaiterAddress());		    //丙方地址
					if(agr.getServiceCode()!=null){
						model.setServiceNo(agr.getServiceCode().toString());//服务项目序号
					}
					if(agr.getRemarkZdg()!=null){
						model.setRemarkZdg(agr.getRemarkZdg().toString());//钟点工工作时间备注
					}
					if(agr.getEffectDate()!=null){						//起始年月日
						Date effectDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getEffectDate());
						c.setTime(effectDate);
						model.setF_y(c.get(Calendar.YEAR)+"");
						model.setF_m((c.get(Calendar.MONTH)+1)+"");
						model.setF_d(c.get(Calendar.DAY_OF_MONTH)+"");
					}
					if(agr.getFinishDate()!=null){					    //截止年月日
						Date finishDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getFinishDate());
						c.setTime(finishDate);
						model.setT_y(c.get(Calendar.YEAR)+"");
						model.setT_m((c.get(Calendar.MONTH)+1)+"");
						model.setT_d(c.get(Calendar.DAY_OF_MONTH)+"");
					}
					if(agr.getPersonManageMoney()!=null)			//服务人员管理费(元/月)  收费标准（丙向乙支付）
						model.setSalaryFee(agr.getPersonManageMoney().toString());
					if(agr.getCustomerManageMoney()!=null)			//客户管理费(一次)      支付标准（甲向丙支付）
						model.setPayA(agr.getCustomerManageMoney().toString());
					if(agr.getServiceMoney()!=null)					//服务人员劳务费(元/月)  平台信息费（甲向乙支付）
						model.setMessFee(agr.getServiceMoney().toString());
					if(agr.getAdvancePeriod()!=null)				//预支付类型
						model.setZhifuType(agr.getAdvancePeriod().toString());
					if (agr.getAgreementPayDate()!=null && agr.getAdvancePeriod()!=null && agr.getAdvancePeriod() == 1) {
						NumberToCN t1 = new NumberToCN();
						Date payDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getAgreementPayDate());
						c.setTime(payDate);
						model.setZhifu_y_1(c.get(Calendar.YEAR)+"");
						model.setZhifu_m_1((c.get(Calendar.MONTH)+1)+"");
						model.setZhifu_d_1(c.get(Calendar.DAY_OF_MONTH)+"");
						model.setAllPay_1(agr.getAllPay().toString());
						model.setAllPayCN_1(t1.getCn(agr.getAllPay().toString()));
					} else if (agr.getAgreementPayDate()!=null && agr.getAdvancePeriod()!=null && agr.getAdvancePeriod() == 2){
						NumberToCN t2 = new NumberToCN();
						Date payDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getAgreementPayDate());
						c.setTime(payDate);
						model.setZhifu_y_2(c.get(Calendar.YEAR)+"");
						model.setZhifu_m_2((c.get(Calendar.MONTH)+1)+"");
						model.setZhifu_d_2(c.get(Calendar.DAY_OF_MONTH)+"");
						model.setAllPay_2(agr.getAllPay().toString());
						model.setAllPayCN_2(t2.getCn(agr.getAllPay().toString()));
					}else if (agr.getAgreementPayDate()!=null && agr.getAdvancePeriod()!=null && agr.getAdvancePeriod() == 3){
						NumberToCN t3 = new NumberToCN();
						Date payDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getAgreementPayDate());
						c.setTime(payDate);
						model.setZhifu_y_3(c.get(Calendar.YEAR)+"");
						model.setZhifu_m_3((c.get(Calendar.MONTH)+1)+"");
						model.setZhifu_d_3(c.get(Calendar.DAY_OF_MONTH)+"");
						model.setAllPay_3(agr.getAllPay().toString());
						model.setAllPayCN_3(t3.getCn(agr.getAllPay().toString()));
					}else if (agr.getAgreementPayDate()!=null && agr.getAdvancePeriod()!=null && agr.getAdvancePeriod() == 4){
						Date payDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(agr.getAgreementPayDate());
						c.setTime(payDate);
						model.setZhifu_y_4(c.get(Calendar.YEAR)+"");
						model.setZhifu_m_4((c.get(Calendar.MONTH)+1)+"");
						model.setZhifu_d_4(c.get(Calendar.DAY_OF_MONTH)+"");
					}
					if (agr.getOtherWay() != null) {
						model.setOtherWay(agr.getOtherWay());
					}
					if(agr.getChargeTimes()!=null){					//几倍
						model.setSalaryJb(agr.getChargeTimes().toString());
					}
					model.setOthers(agr.getOtherMatters()); 		//其他约定全行
					model.setRemarkOthers(agr.getOtherMethods());   //其他服务
					model.setAddrService(agr.getServiceAddress());  //服务地址
					if(agr.getLegalRepresentative()!=null){
						model.setLegalRepresentative(agr.getLegalRepresentative());;//法定代表人
					}
					if(agr.getPrepaidMonths()!=null){
						model.setPrepaidMonths(agr.getPrepaidMonths().toString());
					}
					if(agr.getPrepaidMoney()!=null){
						model.setPrepaidMoney(df.format(agr.getPrepaidMoney()));
					}
					if(agr.getInstPrepaidMonths()!=null){
						model.setInstPrepaidMonths(agr.getInstPrepaidMonths().toString());
					}
					if(agr.getInstPrepaidMoney()!=null){
						model.setInstPrepaidMoney(df.format(agr.getInstPrepaidMoney()));
					}
					if(agr.getLimitDays()!=null){
						model.setLimitDays(agr.getLimitDays().toString());
					}
					if(agr.getAccountName()!=null){
						model.setAccountName(agr.getAccountName());
					}
					if(agr.getAccountNum()!=null){
						model.setAccountNum(agr.getAccountNum());
					}
					if(agr.getAccountBank()!=null){
						model.setAccountBank(agr.getAccountBank());
					}
					if(agr.getIsPeContract() != null && agr.getIsPeContract() == 1){
						
					}
					return model;
				
				}
}
