package com.emotte.order.util.pdfTools.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.emotte.order.util.pdfTools.templateModel.FranchiseeContractModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class FranchiseeContract extends BaseTemplate{
	private static Log log = LogFactory.getLog(FranchiseeContractModel.class);

	private FranchiseeContractModel model;
	
	//背景图片
	private Image bg_img1;
	private Image bg_img2;
	private Image bg_img3;
	private Image bg_img4;
	private Image bg_img5;
	
	public FranchiseeContract(File pdf,FranchiseeContractModel model,String filePath,Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮加盟商信息服务协议";
			this.model = model;
			if(preview!=null && preview.intValue()==1){
				bg_img1 = Image.getInstance(filePath+"/images/20170619/jm_1_yl.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20170619/jm_2_yl.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20170619/jm_3_yl.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20170619/jm_4_yl.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20170619/jm_5_yl.jpg");
			}else{
				bg_img1 = Image.getInstance(filePath+"/images/20170619/jm_1_zs.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20170619/jm_2_zs.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20170619/jm_3_zs.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20170619/jm_4_zs.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20170619/jm_5_zs.jpg");
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
		document.addSubject("管家帮加盟商信息服务协议");	//主题
		document.addCreationDate();
		document.addKeywords("合同"); //关键字
		
		bg_img1.scaleToFit(595, 842);//大小
		document.add(bg_img1);//842×595
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 798, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_y(), 172, 720, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_m(), 240, 720, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_d(), 311, 720, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 152, 702, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneA(), 417, 702, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeA(), 133, 683, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumA(), 300, 683, 0);
		//cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrA(), 100, 665, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 100, 665, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactB(), 139, 629, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrB(), 96, 610, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactC(), 172, 574, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneC(), 305, 574, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeC(), 133, 556, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumC(), 300, 556, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrC(), 100, 537, 0);
		
		if (model.getServiceNo() == null || "".equals(model.getServiceNo())) {//服务序号为空
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 387, 340, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceNo(), 387, 340, 0);
		}
		
		if (model.getRemarkZdg() == null || "".equals(model.getRemarkZdg())) {//钟点工工作时间备注
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 270, 304, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkZdg(), 186, 304, 0);
		}
		
		if ((model.getRemarkOthers() == null || "".equals(model.getRemarkOthers())) || (model.getServiceNo() != null && !"8".equals(model.getServiceNo()))) { //其他服务
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 270, 286, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkOthers(), 112, 286, 0);
		}
		
		if (model.getAddrService() == null || "".equals(model.getAddrService())) {//服务地址
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 270, 250, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 127, 250, 0);
		}
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_y(), 192, 231, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_m(), 224, 231, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_d(), 246, 231, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_y(), 290, 231, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_m(), 322, 231, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_d(), 343, 231, 0);
		
		if (model.getPayA() == null || "".equals(model.getPayA())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 355, 150, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPayA(), 355, 150, 0);
		}
		
		if (model.getSalaryFee() == null || "".equals(model.getSalaryFee())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 330, 95, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryFee(), 330, 95, 0);
		}
		
		cb.endText();
		
		document.newPage(); 
		bg_img2.scaleToFit(595, 842);
		document.add(bg_img2);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 798, 0);
		
		if (model.getMessFee() == null || "".equals(model.getMessFee())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 428, 703, 0); 
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getMessFee(), 428, 703, 0); 
		}
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getYue(), 134, 519, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getJi(), 161, 519, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getBanNian(), 188, 519, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getNian(), 226, 519, 0);
		
		if (model.getZhifu_y() == null || "".equals(model.getZhifu_y())) {//年
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 402, 519, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_y(), 394, 519, 0);
		}
		
		if (model.getZhifu_m() == null || "".equals(model.getZhifu_m())) {//月
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 438, 519, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_m(), 437, 519, 0);
		}
		
		if (model.getZhifu_d() == null || "".equals(model.getZhifu_d())) {//日
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 475, 519, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_d(), 470, 519, 0);
		}
		
		if (model.getZhifuDay() == null || "".equals(model.getZhifuDay())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 69, 502, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifuDay(), 65, 502, 0);
		}
		
		if (model.getZhifuDate() == "") {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 450, 502, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifuDate(), 426, 502, 0);
		}
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryJb(), 70, 118, 0);
		cb.endText();
		
		document.newPage(); 
		bg_img3.scaleToFit(595, 842);
		document.add(bg_img3);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 798, 0);

		cb.endText();
		
		document.newPage(); 
		bg_img4.scaleToFit(595, 842);
		document.add(bg_img4);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 798, 0);
		
		cb.endText();
		
		document.newPage(); 
		bg_img5.scaleToFit(595, 842);
		document.add(bg_img5);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 798, 0);
		if ("".equals(model.getO1())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 701, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO1(), 60, 701, 0);
		}
		if ("".equals(model.getO2())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 680, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO2(), 60, 680, 0);
		}
		if ("".equals(model.getO3())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 660, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO3(), 60, 660, 0);
		}
		if ("".equals(model.getO4())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 640, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO4(), 60, 640, 0);
		}
		
		if ("".equals(model.getO5())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 620, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO5(), 60, 620, 0);
		}
		
		if ("".equals(model.getO6())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 600, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO6(), 60, 600, 0);
		}
		
		if ("".equals(model.getO7())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 580, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO7(), 60, 580, 0);
		}
		
		
		cb.endText();
	
		//5.关闭文档
		document.close();
	}
		
}
