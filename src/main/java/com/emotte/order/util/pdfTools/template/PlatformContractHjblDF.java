package com.emotte.order.util.pdfTools.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.emotte.order.util.pdfTools.templateModel.PlatformContractRevisedDFModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class PlatformContractHjblDF extends BaseTemplate{
	private static Log log = LogFactory.getLog(PlatformContractRevisedDFModel.class);

	private PlatformContractRevisedDFModel model;
	
	//背景图片
	private Image bg_img1;
	private Image bg_img2;
	private Image bg_img3;
	private Image bg_img4;
	private Image bg_img5;
	private Image bg_img6;
	private Image bg_img7;
	
	public PlatformContractHjblDF(File pdf,PlatformContractRevisedDFModel model,String filePath,Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮平台信息服务协议（海金保理代付）";
			this.model = model;
			if(preview!=null && preview.intValue()==1){
				bg_img1 = Image.getInstance(filePath+"/images/20190122/20190122dfhj/hjbl_yl_1.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20190122/20190122dfhj/hjbl_yl_2.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20190122/20190122dfhj/hjbl_yl_3.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20190122/20190122dfhj/hjbl_yl_4.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20190122/20190122dfhj/hjbl_yl_5.jpg");
				bg_img6 = Image.getInstance(filePath+"/images/20190122/20190122dfhj/hjbl_yl_6.jpg");
				bg_img7 = Image.getInstance(filePath+"/images/20190122/20190122dfhj/hjbl_yl_7.jpg");
			}else{
				bg_img1 = Image.getInstance(filePath+"/images/20190122/20190122dfhj/hjbl_zs_1.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20190122/20190122dfhj/hjbl_zs_2.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20190122/20190122dfhj/hjbl_zs_3.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20190122/20190122dfhj/hjbl_zs_4.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20190122/20190122dfhj/hjbl_zs_5.jpg");
				bg_img6 = Image.getInstance(filePath+"/images/20190122/20190122dfhj/hjbl_zs_6.jpg");
				bg_img7 = Image.getInstance(filePath+"/images/20190122/20190122dfhj/hjbl_zs_7.jpg");
			}
		} catch (Exception e) {
			log.info("error",e);
		}
	}

	@Override
	public void WritePdf() throws FileNotFoundException, DocumentException {
		Document document = new Document(PageSize.A4,0,0,0,0);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf));
		document.open();
		writer.setViewerPreferences(PdfWriter.PageLayoutTwoColumnLeft); //双列显示，奇数列在左
		writer.setViewerPreferences(PdfWriter.FitWindow); //自动调节文档窗口尺寸以适合显示第一页
		PdfContentByte cb = writer.getDirectContent(); //文字绝对定位对象
		 
		// 添加Meta信息（文档属性中的说明）
		document.addAuthor(authorName);
		document.addCreator(authorName);
		document.addTitle(title);	//标题
		document.addSubject("管家帮平台信息服务协议");	//主题
		document.addCreationDate();
		document.addKeywords("合同"); //关键字
		
		bg_img1.scaleToFit(595, 842);//大小
		document.add(bg_img1);//842×595
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		//cb.setRGBColorFill(255,0,0);//测试使用-红字
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 370, 800, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_y(), 187, 702, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_m(), 250, 702, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_d(), 302, 702, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 222, 681, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneA(), 440, 681, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeA(), 180, 658, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumA(), 410, 658, 0);
//		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrA(), 106, 637, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 220, 639, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactB(), 270, 596, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrB(), 230, 576, 0);
		//cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getGroupName(), 298, 556, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactC(), 220, 534, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneC(), 440, 534, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeC(), 180, 512, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumC(), 410, 512, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrC(), 230, 492, 0);
		
		
		if (model.getServiceNo() == null || "".equals(model.getServiceNo())) {//服务序号为空
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 480, 229, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceNo(), 480, 229, 0);
		}
		if (model.getRemarkZdg() == null || "".equals(model.getRemarkZdg())) {//钟点工工作时间备注
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 323, 191, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkZdg(), 323, 191, 0);
		}
		
		//if ((model.getRemarkOthers() == null || "".equals(model.getRemarkOthers()))  || (model.getServiceNo() != null && !"8".equals(model.getServiceNo()))) { //其他服务
			//cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 300, 199, 0);
		//} else {
			//cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkOthers(), 138, 199, 0);
		//}
		if (model.getAddrService() == null || "".equals(model.getAddrService())) {//服务地址
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 200, 130, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 200, 130, 0);
		}
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_y(), 200, 109, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_m(), 246, 109, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_d(), 278, 109, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_y(), 330, 109, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_m(), 374, 109, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_d(), 410, 109, 0);
		cb.endText();
		
		document.newPage(); 
		bg_img2.scaleToFit(595, 842);
		document.add(bg_img2);
		cb.beginText();
		//cb.setRGBColorFill(255,0,0);//测试使用-红字
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 370, 800, 0);
		if (model.getPayA() == null || "".equals(model.getPayA())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 375, 728, 0); 
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPayA(), 375, 728, 0); 
		}
		if (model.getSalaryFee() == null || "".equals(model.getSalaryFee())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 460, 679, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryFee(), 460, 679, 0);
		}
		if (model.getMessFee() == null || "".equals(model.getMessFee())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 460, 632, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getMessFee(), 460, 632, 0);
		}
		if(null!=model.getCheckWayHjbl_1() || !"".equals(model.getCheckWayHjbl_1())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCheckWayHjbl_1(), 177, 494, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCheckWayHjbl_2(), 130, 494, 0);
		}
		//TODO: 一次性预付劳务费 
		if (model.getYue() != null || "".equals(model.getYue())) {
			if (model.getZhifu_y_1() == null || "".equals(model.getZhifu_y_1())) {//年
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 255, 385, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_y_1(), 255, 385, 0);
			}
			if (model.getZhifu_m_1() == null || "".equals(model.getZhifu_m_1())) {//月
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 295, 385, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_m_1(), 295, 385, 0);
			}
			if (model.getZhifu_d_1() == null || "".equals(model.getZhifu_d_1())) {//日
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 330, 385, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_d_1(), 330, 385, 0);
			}
			if (model.getAllPay_1() == null || "".equals(model.getAllPay_1())) {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 260, 372, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 375, 372, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPay_1(), 260, 372, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPayCN_1(), 375, 372, 0);
			}
		}
		
		//cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getJi(), 77, 425, 0);
		//TODO: 白条支付
		if (model.getJi() != null || "".equals(model.getJi())) {
			if (model.getZhifu_y_2() == null || "".equals(model.getZhifu_y_2())) {//年
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 516, 355, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_y_2(), 516, 355, 0);
			}
			if (model.getZhifu_m_2() == null || "".equals(model.getZhifu_m_2())) {//月
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 60, 343, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_m_2(), 60, 343, 0);
			}
			if (model.getZhifu_d_2() == null || "".equals(model.getZhifu_d_2())) {//日
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 88, 343, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_d_2(), 88, 343, 0);
			}
			if (model.getAllPay_2() == null || "".equals(model.getAllPay_2())) {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 516, 343, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 150, 330, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPay_2(), 516, 343, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPayCN_2(), 120, 330, 0);
			}
		}
		
		//cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getBanNian(), 77, 378, 0);
		//TODO:银行白条支付
		if (model.getBanNian() != null || "".equals(model.getBanNian())) {
			if (model.getZhifu_y_3() == null || "".equals(model.getZhifu_y_3())) {//年
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 517, 315, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_y_3(), 517, 315, 0);
			}
			if (model.getZhifu_m_3() == null || "".equals(model.getZhifu_m_3())) {//月
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 73, 300, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_m_3(), 73, 300, 0);
			}
			if (model.getZhifu_d_3() == null || "".equals(model.getZhifu_d_3())) {//日
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 105, 300, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_d_3(), 105, 300, 0);
			}
			if (model.getAllPay_3() == null || "".equals(model.getAllPay_3())) {
				//cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 130, 347, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 145, 290, 0);
			} else {
				//cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPay_3(), 130, 347, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPayCN_3(), 145, 300, 0);
			}
		}
		//cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getNian(), 77, 330, 0);
		//TODO:其他支付方式
		if (model.getNian() != null || "".equals(model.getNian())) {
			if (model.getOtherWay() == null || "".equals(model.getOtherWay())) {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 251, 274, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getOtherWay(), 200, 274, 0);
			}
			if (model.getZhifu_y_4() == null || "".equals(model.getZhifu_y_4())) {//年
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 366, 274, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_y_4(), 366, 274, 0);
			}
			if (model.getZhifu_m_4() == null || "".equals(model.getZhifu_m_4())) {//月
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 400, 274, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_m_4(), 400, 274, 0);
			}
			if (model.getZhifu_d_4() == null || "".equals(model.getZhifu_d_4())) {//日
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 423, 274, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_d_4(), 423, 274, 0);
			}
		}
		cb.endText();
		
		document.newPage(); 
		bg_img3.scaleToFit(595, 842);
		document.add(bg_img3);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		//cb.setRGBColorFill(255,0,0);//测试使用-红字
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 370, 800, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryJb(), 145, 578, 0);
		cb.endText();
		
		document.newPage(); 
		bg_img4.scaleToFit(595, 842);
		document.add(bg_img4);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		//cb.setRGBColorFill(255,0,0);//测试使用-红字
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 370, 800, 0);
		if ("".equals(model.getO1())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 127, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO1(), 55, 127, 0);
		}
		if ("".equals(model.getO2())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 104, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO2(), 55, 104, 0);
		}
		if ("".equals(model.getO3())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 83, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO3(), 55, 83, 0);
		}
		cb.endText();
		
		document.newPage(); 
		bg_img5.scaleToFit(595, 842);
		document.add(bg_img5);
		cb.beginText();
		//cb.setRGBColorFill(255,0,0);//测试使用-红字
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 370, 800, 0);
		cb.endText();
		
		/*第6页开始*/
		document.newPage(); 
		bg_img6.scaleToFit(595, 842);//大小595*842
		document.add(bg_img6);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_y(), 178, 720, 0);//签署年
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_m(), 239, 720, 0);//签署月
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_d(), 295, 720, 0);//签署日
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 209, 695, 0);//甲方姓名
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneA(),400, 695, 0);//甲方联系电话
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeA(),169, 678, 0);//甲方证件类型
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumA(),344, 678, 0);//甲方证件号码
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(),107, 658, 0);//甲方地址,取服务地址
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactB(),190, 616, 0);//乙方平台
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrB(), 106, 593, 0);//乙方地址
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 353, 800, 0);//平台服务协议合同编号1
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 240, 530, 0);//平台服务协议合同编号2
//		if (model.getLegalRepresentative() == null || "".equals(model.getLegalRepresentative())) {
//			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/",209, 604, 0);//法定代表人,当前登录人
//		} else {
//			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getLegalRepresentative(),209, 604, 0);//法定代表人,当前登录人
//		}
		if (model.getPrepaidMonths() == null || "".equals(model.getPrepaidMonths())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 153, 435, 0);//预付几个月劳务费
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPrepaidMonths(), 153, 435, 0);//预付几个月劳务费
		}
		if (model.getPrepaidMoney() == null || "".equals(model.getPrepaidMoney())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 361, 435, 0);//劳务费总金额
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPrepaidMoney(), 361, 435, 0);//劳务费总金额
		}
		if (model.getInstPrepaidMonths() == null || "".equals(model.getInstPrepaidMonths())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 142, 405, 0);//分几个月劳务费
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getInstPrepaidMonths(), 142, 405, 0);//分几个月劳务费
		}
		if (model.getInstPrepaidMoney() == null || "".equals(model.getInstPrepaidMoney())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 370, 405, 0);//分期总金额
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getInstPrepaidMoney(), 370, 405, 0);//分期总金额
		}
		if (model.getLimitDays() == null || "".equals(model.getLimitDays())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 370, 336, 0);//分期手续费几日内向乙方支付
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getLimitDays()+"日", 370, 336, 0);//分期手续费几日内向乙方支付
		}
		if (model.getAccountName() == null || "".equals(model.getAccountName())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 127, 180, 0);//甲方账户名
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAccountName(), 127, 180, 0);//甲方账户名
		}
		if (model.getAccountNum() == null || "".equals(model.getAccountNum())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 127, 165, 0);//甲方账号
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAccountNum(), 127, 165, 0);//甲方账号
		}
		if (model.getAccountBank() == null || "".equals(model.getAccountBank())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 127, 150, 0);//甲方开户行
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAccountBank(), 127, 150, 0);//甲方开户行
		}
		if (model.getPhoneA() == null || "".equals(model.getPhoneA())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 127, 135, 0);//甲方手机号
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneA(), 127, 135, 0);//甲方手机号
		}
		cb.endText();
		/*第6页结束*/
		
		/*第7页开始*/
		document.newPage();
		bg_img7.scaleToFit(595, 842);//大小595*842
		document.add(bg_img7);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 353, 800, 0);//平台服务协议合同编号3
		cb.endText();
		/*第7页结束*/
	
		//5.关闭文档
		document.close();
	}
		
}
