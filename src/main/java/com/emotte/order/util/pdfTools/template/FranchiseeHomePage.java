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

public class FranchiseeHomePage extends BaseTemplate{
	private static Log log = LogFactory.getLog(FranchiseeContractModel.class);

	private FranchiseeContractModel model;
	
	//背景图片
	private Image bg_img1;
	
	public FranchiseeHomePage(File pdf,FranchiseeContractModel model,String filePath,Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮加盟商信息服务协议首页";
			this.model = model;
			bg_img1 = Image.getInstance(filePath+"/images/20170619/jm_1_zs.jpg");
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
		document.addSubject("管家帮加盟商信息服务协议首页");	//主题
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
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceNo(), 387, 340, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkZdg(), 186, 304, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkOthers(), 112, 286, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 127, 250, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_y(), 192, 231, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_m(), 224, 231, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_d(), 246, 231, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_y(), 290, 231, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_m(), 322, 231, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_d(), 343, 231, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPayA(), 355, 150, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryFee(), 330, 95, 0);
		cb.endText();
		
	
		//关闭文档
		document.close();
	}
		
}
