package com.emotte.order.util.pdfTools.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.emotte.order.util.pdfTools.templateModel.JxPlatformContractRevisedDFModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class JxPlatformContractHjblDF extends BaseTemplate{
	private static Log log = LogFactory.getLog(JxPlatformContractRevisedDFModel.class);

	private JxPlatformContractRevisedDFModel model;
	
	//背景图片
	private Image bg_img1;
	private Image bg_img2;
	private Image bg_img3;
	private Image bg_img4;
	private Image bg_img5;
	private Image bg_img6;
	private Image bg_img7;
	
	public JxPlatformContractHjblDF(File pdf,JxPlatformContractRevisedDFModel model,String filePath,Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮平台信息服务协议（海金保理代付）";
			this.model = model;
			if(preview!=null && preview.intValue()==1){
				bg_img1 = Image.getInstance(filePath+"/images/20190117/20190117jxdfhj/jxdfhj_yl_1.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20190117/20190117jxdfhj/jxdfhj_yl_2.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20190117/20190117jxdfhj/jxdfhj_yl_3.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20190117/20190117jxdfhj/jxdfhj_yl_4.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20190117/20190117jxdfhj/jxdfhj_yl_5.jpg");
				bg_img6 = Image.getInstance(filePath+"/images/20190117/20190117jxdfhj/jxdfhjfq_yl_1.jpg");
				bg_img7 = Image.getInstance(filePath+"/images/20190117/20190117jxdfhj/jxdfhjfq_yl_2.jpg");
			}else{
				bg_img1 = Image.getInstance(filePath+"/images/20190117/20190117jxdfhj/jxdfhj_zs_1.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20190117/20190117jxdfhj/jxdfhj_zs_2.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20190117/20190117jxdfhj/jxdfhj_zs_3.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20190117/20190117jxdfhj/jxdfhj_zs_4.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20190117/20190117jxdfhj/jxdfhj_zs_5.jpg");
				bg_img6 = Image.getInstance(filePath+"/images/20190117/20190117jxdfhj/jxdfhjfq_zs_1.jpg");
				bg_img7 = Image.getInstance(filePath+"/images/20190117/20190117jxdfhj/jxdfhjfq_zs_2.jpg");
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
		document.addTitle(title);
		document.addSubject("管家帮平台信息服务协议");
		document.addCreationDate();
		document.addKeywords("合同");
		
		bg_img1.scaleToFit(595, 842);
		document.add(bg_img1);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);//合同编号
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_y(), 163, 700, 0);//年
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_m(), 224, 700, 0);//月
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_d(), 261, 700, 0);//日
		if(model.getPactA() == null || "".equals(model.getPactA())){
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 227, 679, 0);//甲方姓名
		}else{
			if(model.getPactA().length() > 4){
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 139, 678, 0);//甲方姓名
			}else{
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 200, 678, 0);//甲方姓名
			}
		}
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneA(), 390, 678, 0);//甲方电话
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeA(), 130, 657, 0);//甲方证件类型
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumA(), 372, 657, 0);//甲方证件号码
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 106, 637, 0);//甲方地址
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactB(), 188, 595, 0);//乙方名称
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrB(), 109, 574, 0);//乙方地址
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactC(), 200, 553, 0);//丙方姓名
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneC(), 380, 553, 0);//丙方电话
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeC(), 153, 532, 0);//丙方证件类型
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumC(), 345, 532, 0);//丙方证件号码
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrC(), 100, 511, 0);//丙方地址
		if (model.getServiceNo() == null || "".equals(model.getServiceNo())) {
			//第几项服务
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 435, 286, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceNo(), 435, 286, 0);
		}
		if(model.getServiceNo() != null && "7".equals(model.getServiceNo())){
			if (model.getRemarkZdg() == null || "".equals(model.getRemarkZdg())) {
				//钟点工工作时间
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 220, 252, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkZdg(), 175, 252, 0);
			}
		}else{
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 220, 252, 0);
		}
		if(model.getServiceNo() != null && "8".equals(model.getServiceNo())){
			if ((model.getRemarkOthers() == null || "".equals(model.getRemarkOthers()))) { 
				//其它服务
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 420, 253, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkOthers(), 365, 253, 0);
			}
		}else{
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 420, 253, 0);
		}
		if (model.getAddrService() == null || "".equals(model.getAddrService())) {
			//服务场所
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 250, 214, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 138, 214, 0);
		}
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_y(), 187, 194, 0);//服务期限开始年
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_m(), 231, 194, 0);//服务期限开始月
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_d(), 258, 194, 0);//服务期限开始日
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_y(), 310, 194, 0);//服务期限结束年
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_m(), 354, 194, 0);//服务期限结束月
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_d(), 384, 194, 0);//服务期限结束日
		if (model.getPayA() == null || "".equals(model.getPayA())) {
			//向乙方支付平台信息费
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 347, 118, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPayA(), 333, 118, 0);
		}
		cb.endText();
		
		//第2页开始
		document.newPage(); 
		bg_img2.scaleToFit(595, 842);
		document.add(bg_img2);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);//合同编号
		if (model.getSalaryFee() == null || "".equals(model.getSalaryFee())) {
			//具体收费标准
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 390, 768, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryFee(), 359, 768, 0);
		}
		if (model.getMessFee() == null || "".equals(model.getMessFee())) {
			//具体支付标准为
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 421, 721, 0); 
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getMessFee(), 411, 721, 0); 
		}
		if(null!=model.getCheckWayHjbl_1() || !"".equals(model.getCheckWayHjbl_1())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCheckWayHjbl_1(), 160, 573, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCheckWayHjbl_2(), 117, 573, 0);
		}
		//一次性预付劳务费 
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 228, 466, 0);//年
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 263, 466, 0);//月
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 289, 466, 0);//日
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 180, 453, 0);//费用总计数字
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 340, 453, 0);//费用总计中文
		//唯品会白条支付
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 464, 439, 0);//年
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 494, 439, 0);//月
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 520, 439, 0);//日
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 417, 425, 0);//费用总计数字
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 166, 412, 0);//费用总计中文
		//银行白条支付
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 464, 397, 0);//年
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 494, 397, 0);//月
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 520, 397, 0);//日
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 417, 385, 0);//费用总计数字
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 166, 371, 0);//费用总计中文
		//其他支付方式
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 209, 357, 0);//支付方式
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 318, 357, 0);//年
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 347, 357, 0);//月
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 373, 357, 0);//日
		cb.endText();
		
		//第3页开始
		document.newPage(); 
		bg_img3.scaleToFit(595, 842);
		document.add(bg_img3);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 370, 800, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryJb(),488,727, 0);//日服务费标准的几倍
		cb.endText();
		
		//第4页开始
		document.newPage(); 
		bg_img4.scaleToFit(595, 842);
		document.add(bg_img4);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);//合同编号
		if ("".equals(model.getO1())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 310, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO1(), 62, 310, 0);
		}
		if ("".equals(model.getO2())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 286, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO2(), 62, 286, 0);
		}
		if ("".equals(model.getO3())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 262, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO3(), 62, 262, 0);
		}
		if ("".equals(model.getO4())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 238, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO4(), 62, 238, 0);
		}
		if ("".equals(model.getO5())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 214, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO5(), 62, 214, 0);
		}
		if ("".equals(model.getO6())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 190, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO6(), 62, 190, 0);
		}
		cb.endText();
		
		//第5页开始
		document.newPage(); 
		bg_img5.scaleToFit(595, 842);
		document.add(bg_img5);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);
		cb.endText();
		
		//第6页开始
		document.newPage(); 
		bg_img6.scaleToFit(595, 842);
		document.add(bg_img6);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 353, 800, 0);//合同编号
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_y(), 178, 698, 0);//签署年
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_m(), 245, 698, 0);//签署月
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_d(), 292, 698, 0);//签署日
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 155, 677, 0);//甲方姓名
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneA(),400, 677, 0);//甲方联系电话
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeA(),135, 656, 0);//甲方证件类型
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumA(),344, 656, 0);//甲方证件号码
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(),100, 636, 0);//甲方地址,取服务地址
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactB(),180, 594, 0);//乙方平台
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrB(), 106, 573, 0);//乙方地址
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 240, 516, 0);//平台服务协议合同
		if (model.getPrepaidMonths() == null || "".equals(model.getPrepaidMonths())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 161, 422, 0);//预付几个月劳务费
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPrepaidMonths(), 161, 422, 0);//预付几个月劳务费
		}
		if (model.getPrepaidMoney() == null || "".equals(model.getPrepaidMoney())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 364, 422, 0);//劳务费总金额
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPrepaidMoney(), 338, 422, 0);//劳务费总金额
		}
		if (model.getInstPrepaidMonths() == null || "".equals(model.getInstPrepaidMonths())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 147, 391, 0);//分几个月劳务费
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getInstPrepaidMonths(), 147, 391, 0);//分几个月劳务费
		}
		if (model.getInstPrepaidMoney() == null || "".equals(model.getInstPrepaidMoney())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 374, 391, 0);//分期总金额
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getInstPrepaidMoney(), 347, 391, 0);//分期总金额
		}
		if (model.getLimitDays() == null || "".equals(model.getLimitDays())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 372, 323, 0);//分期手续费几日内向乙方支付
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getLimitDays()+"日", 372, 323, 0);//分期手续费几日内向乙方支付
		}
		if (model.getAccountName() == null || "".equals(model.getAccountName())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 127, 168, 0);//甲方账户名
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAccountName(), 127, 168, 0);//甲方账户名
		}
		if (model.getAccountNum() == null || "".equals(model.getAccountNum())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 127, 153, 0);//甲方账号
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAccountNum(), 127, 153, 0);//甲方账号
		}
		if (model.getAccountBank() == null || "".equals(model.getAccountBank())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 127, 137, 0);//甲方开户行
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAccountBank(), 127, 137, 0);//甲方开户行
		}
		if (model.getPhoneA() == null || "".equals(model.getPhoneA())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 127, 122, 0);//甲方手机号
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneA(), 127, 122, 0);//甲方手机号
		}
		cb.endText();
		
		//第7页开始
		document.newPage();
		bg_img7.scaleToFit(595, 842);
		document.add(bg_img7);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 353, 800, 0);//平台服务协议合同编号
		cb.endText();
	
		//5.关闭文档
		document.close();
	}
		
}
