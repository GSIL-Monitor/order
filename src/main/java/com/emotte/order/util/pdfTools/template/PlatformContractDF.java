package com.emotte.order.util.pdfTools.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.emotte.order.util.pdfTools.templateModel.PlatformContractDFModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class PlatformContractDF extends BaseTemplate{
	private static Log log = LogFactory.getLog(PlatformContractDFModel.class);

	private PlatformContractDFModel model;
	
	//背景图片
	private Image bg_img1;
	private Image bg_img2;
	private Image bg_img3;
	private Image bg_img4;
	private Image bg_img5;
	
	public PlatformContractDF(File pdf,PlatformContractDFModel model,String filePath,Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮平台信息服务协议（代付）";
			this.model = model;
			if(preview!=null && preview.intValue()==1){
				bg_img1 = Image.getInstance(filePath+"/images/20171221/df_yl_1.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20171221/df_yl_2.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20171221/df_yl_3.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20171221/df_yl_4.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20171221/df_yl_5.jpg");
			}else{
				bg_img1 = Image.getInstance(filePath+"/images/20171221/df_zs_1.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20171221/df_zs_2.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20171221/df_zs_3.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20171221/df_zs_4.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20171221/df_zs_5.jpg");
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
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_y(), 185, 700, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_m(), 254, 700, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_d(), 304, 700, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 212, 678, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneA(), 415, 678, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeA(), 171, 657, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumA(), 372, 657, 0);
//		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrA(), 106, 637, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 106, 637, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactB(), 188, 616, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrB(), 109, 594, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getGroupName(), 109, 573, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactC(), 236, 552, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneC(), 428, 552, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeC(), 158, 531, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumC(), 345, 531, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrC(), 100, 511, 0);
		
		if (model.getServiceNo() == null || "".equals(model.getServiceNo())) {//服务序号为空
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 481, 276, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceNo(), 481, 276, 0);
		}
		if (model.getRemarkZdg() == null || "".equals(model.getRemarkZdg())) {//钟点工工作时间备注
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 300, 239, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkZdg(), 258, 239, 0);
		}
		if ((model.getRemarkOthers() == null || "".equals(model.getRemarkOthers()))  || (model.getServiceNo() != null && !"8".equals(model.getServiceNo()))) { //其他服务
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 300, 218, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkOthers(), 138, 218, 0);
		}
		if (model.getAddrService() == null || "".equals(model.getAddrService())) {//服务地址
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 300, 159, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 138, 159, 0);
		}
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_y(), 210, 138, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_m(), 258, 138, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_d(), 300, 138, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_y(), 357, 138, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_m(), 405, 138, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_d(), 443, 138, 0);
		cb.endText();
		
		document.newPage(); 
		bg_img2.scaleToFit(595, 842);
		document.add(bg_img2);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);
		if (model.getPayA() == null || "".equals(model.getPayA())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 390, 767, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPayA(), 390, 767, 0);
		}
		if (model.getSalaryFee() == null || "".equals(model.getSalaryFee())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 422, 715, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryFee(), 422, 715, 0);
		}
		if (model.getMessFee() == null || "".equals(model.getMessFee())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 485, 662, 0); 
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getMessFee(), 474, 662, 0); 
		}
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getYue(), 142, 474, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getJi(), 172, 474, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getBanNian(), 202, 474, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getNian(), 244, 474, 0);
		
		if (model.getZhifu_y() == null || "".equals(model.getZhifu_y())) {//年
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 441, 474, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_y(), 431, 474, 0);
		}
		if (model.getZhifu_m() == null || "".equals(model.getZhifu_m())) {//月
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 477, 474, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_m(), 475, 474, 0);
		}
		if (model.getZhifu_d() == null || "".equals(model.getZhifu_d())) {//日
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 506, 474, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_d(), 505, 474, 0);
		}
		if (model.getZhifuDay() == null || "".equals(model.getZhifuDay())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 96, 458, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifuDay(), 96, 458, 0);
		}
		if (model.getZhifuDate() == "") {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 120, 442, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifuDate(), 71, 442, 0);
		}
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryJb(), 138, 90, 0);
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
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 298, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO1(), 62, 298, 0);
		}
		if ("".equals(model.getO2())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 268, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO2(), 62, 268, 0);
		}
		if ("".equals(model.getO3())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 238, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO3(), 62, 238, 0);
		}
		if ("".equals(model.getO4())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 209, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO4(), 62, 209, 0);
		}
		if ("".equals(model.getO5())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 179, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO5(), 62, 179, 0);
		}
		if ("".equals(model.getO6())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 150, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO6(), 62, 150, 0);
		}
		/*cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO7(), 58, 115, 0);*/
		
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
