package com.emotte.order.util.pdfTools.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.emotte.order.util.pdfTools.templateModel.PlatformContractZFModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class PlatformContractZF extends BaseTemplate{
	private static Log log = LogFactory.getLog(PlatformContractZFModel.class);

	private PlatformContractZFModel model;
	
	//背景图片
	private Image bg_img1;
	private Image bg_img2;
	private Image bg_img3;
	private Image bg_img4;
	private Image bg_img5;
	
	public PlatformContractZF(File pdf,PlatformContractZFModel model,String filePath,Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮平台信息服务协议（直付）";
			this.model = model;
			if(preview!=null && preview.intValue()==1){
				bg_img1 = Image.getInstance(filePath+"/images/20190122/20190122zf/zf_yl_1.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20190122/20190122zf/zf_yl_2.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20190122/20190122zf/zf_yl_3.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20190122/20190122zf/zf_yl_4.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20190122/20190122zf/zf_yl_5.jpg");
			}else{
				bg_img1 = Image.getInstance(filePath+"/images/20190122/20190122zf/zf_zs_1.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20190122/20190122zf/zf_zs_2.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20190122/20190122zf/zf_zs_3.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20190122/20190122zf/zf_zs_4.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20190122/20190122zf/zf_zs_5.jpg");
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
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_y(), 185, 699, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_m(), 255, 699, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_d(), 303, 699, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 227, 679, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneA(), 423, 679, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeA(), 178, 657, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumA(), 362, 657, 0);
//		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrA(), 107, 635, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 107, 636, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactB(), 183, 615, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrB(), 107, 594, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getGroupName(), 107, 574, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactC(), 235, 552, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneC(), 427, 552, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeC(), 161, 531, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumC(), 344, 531, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrC(), 107, 510, 0);
		
		if (model.getServiceNo() == null || "".equals(model.getServiceNo())) {//服务序号为空
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 481, 276, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceNo(), 481, 276, 0);
		}
		
		if (model.getRemarkZdg() == null || "".equals(model.getRemarkZdg())) {//钟点工工作时间备注
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 300, 241, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkZdg(), 257, 241, 0);
		}
		
		if ((model.getRemarkOthers() == null || "".equals(model.getRemarkOthers())) || (model.getServiceNo() != null && !"8".equals(model.getServiceNo()))) { //其他服务
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 300, 218, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkOthers(), 132, 218, 0);
		}
		
		if (model.getAddrService() == null || "".equals(model.getAddrService())) {//服务地址
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 300, 159, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 136, 159, 0);
		}
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_y(), 209, 137, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_m(), 258, 137, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_d(), 300, 137, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_y(), 357, 137, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_m(), 406, 137, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_d(), 441, 137, 0);
		cb.endText();
		
		document.newPage(); 
		bg_img2.scaleToFit(595, 842);
		document.add(bg_img2);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);
		
		if (model.getPayA() == null || "".equals(model.getPayA())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 393, 766, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPayA(), 393, 766, 0);
		}
		
		if (model.getSalaryFee() == null || "".equals(model.getSalaryFee())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 429, 713, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryFee(), 429, 713, 0);
		}
		
		if (model.getMessFee() == null || "".equals(model.getMessFee())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 480, 662, 0); 
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getMessFee(), 468, 662, 0); 
		}
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryJb(), 138, 276, 0);
		cb.endText();
		
		document.newPage(); 
		bg_img3.scaleToFit(595, 842);
		document.add(bg_img3);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);

		cb.endText();
		
		document.newPage(); 
		bg_img4.scaleToFit(595, 842);
		document.add(bg_img4);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);
		if ("".equals(model.getO1())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 467, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO1(), 60, 467, 0);
		}
		if ("".equals(model.getO2())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 437, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO2(), 60, 437, 0);
		}
		if ("".equals(model.getO3())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 408, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO3(), 60, 408, 0);
		}
		if ("".equals(model.getO4())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 379, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO4(), 60, 379, 0);
		}
		
		if ("".equals(model.getO5())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 349, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO5(), 60, 349, 0);
		}
		if ("".equals(model.getO6())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 319, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO6(), 60, 319, 0);
		}
		//cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO7(), 60, 289, 0);
		
		cb.endText();
		
		document.newPage(); 
		bg_img5.scaleToFit(595, 842);
		document.add(bg_img5);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);
		
		cb.endText();
	
		//5.关闭文档
		document.close();
	}
		
}
