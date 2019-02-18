package com.emotte.order.util.pdfTools.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.emotte.order.util.pdfTools.templateModel.PlatformContractDSModel;
import com.emotte.order.util.pdfTools.templateModel.PlatformContractFDSModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class PlatformContractFDS extends BaseTemplate{
	private static Log log = LogFactory.getLog(PlatformContractDSModel.class);

	private PlatformContractFDSModel model;
	
	//背景图片
	private Image bg_img1;
	private Image bg_img2;
	private Image bg_img3;
	private Image bg_img4;
	private Image bg_img5;
	
	public PlatformContractFDS(File pdf,PlatformContractFDSModel model,String filePath,Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮平台信息服务协议（非代收）";
			this.model = model;
			if(preview!=null && preview.intValue()==1){
				bg_img1 = Image.getInstance(filePath+"/images/20160728/fds_1_yl.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20160728/fds_2_yl.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20160728/fds_3_yl.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20160728/fds_4_yl.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20160728/fds_5_yl.jpg");
			}else{
				bg_img1 = Image.getInstance(filePath+"/images/20160728/fds_1_zs.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20160728/fds_2_zs.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20160728/fds_3_zs.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20160728/fds_4_zs.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20160728/fds_5_zs.jpg");
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
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 796, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_y(), 185, 732, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_m(), 243, 732, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_d(), 279, 732, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 152, 714, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneA(), 417, 713, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeA(), 115, 694, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumA(), 300, 694, 0);
		//cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrA(), 100, 676, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 100, 676, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactB(), 175, 657, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrB(), 100, 638, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getGroupName(), 105, 619, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactC(), 172, 580, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneC(), 417, 580, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeC(), 115, 562, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumC(), 300, 562, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrC(), 100, 543, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceNo(), 482, 329, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkZdg(), 218, 297, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkOthers(), 112, 280, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 138, 226, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_y(), 209, 205, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_m(), 254, 205, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_d(), 291, 205, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_y(), 360, 205, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_m(), 403, 205, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_d(), 438, 205, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPayA(), 372, 115, 0);
		cb.endText();
		
		document.newPage(); 
		bg_img2.scaleToFit(595, 842);
		document.add(bg_img2);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 796, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryFee(), 392, 765, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getMessFee(), 460, 706, 0); 
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryJb(), 140, 203, 0);
		cb.endText();
		
		document.newPage(); 
		bg_img3.scaleToFit(595, 842);
		document.add(bg_img3);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 796, 0);

		cb.endText();
		
		document.newPage(); 
		bg_img4.scaleToFit(595, 842);
		document.add(bg_img4);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 796, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO1(), 58, 345, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO2(), 58, 325, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO3(), 58, 306, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO4(), 58, 288, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO5(), 58, 270, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO6(), 58, 252, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO7(), 58, 233, 0);
		
		cb.endText();
		
		document.newPage(); 
		bg_img5.scaleToFit(595, 842);
		document.add(bg_img5);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 796, 0);
		
		cb.endText();
	
		//5.关闭文档
		document.close();
	}
		
}
