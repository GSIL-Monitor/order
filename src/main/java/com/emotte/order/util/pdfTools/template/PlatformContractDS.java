package com.emotte.order.util.pdfTools.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.emotte.order.util.pdfTools.templateModel.PlatformContractDSModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class PlatformContractDS extends BaseTemplate{
	private static Log log = LogFactory.getLog(PlatformContractDSModel.class);

	private PlatformContractDSModel model;
	
	//背景图片
	private Image bg_img1;
	private Image bg_img2;
	private Image bg_img3;
	private Image bg_img4;
	private Image bg_img5;
	
	public PlatformContractDS(File pdf,PlatformContractDSModel model,String filePath,Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮平台信息服务协议（代收）";
			this.model = model;
			if(preview!=null && preview.intValue()==1){
				bg_img1 = Image.getInstance(filePath+"/images/20170929/xds_yl_1.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20170929/xds_yl_2.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20170929/xds_yl_3.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20170929/xds_yl_4.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20170929/xds_yl_5.jpg");
			}else{
				bg_img1 = Image.getInstance(filePath+"/images/20170929/xds_zs_1.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20170929/xds_zs_2.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20170929/xds_zs_3.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20170929/xds_zs_4.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20170929/xds_zs_5.jpg");
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
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 797, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_y(), 194, 720, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_m(), 255, 720, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_d(), 295, 720, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 208, 700, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneA(), 411, 700, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeA(), 170, 681, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumA(), 363, 681, 0);
		//cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrA(), 100, 676, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 106, 661, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactB(), 184, 643, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrB(), 118, 621, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getGroupName(), 118, 605, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactC(), 218, 584, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneC(), 414, 584, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeC(), 169, 564, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumC(), 373, 564, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrC(), 118, 544, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceNo(), 475, 275, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkZdg(), 231, 236, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkOthers(), 127, 216, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 135, 157, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_y(), 208, 139, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_m(), 264, 139, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_d(), 300, 139, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_y(), 361, 139, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_m(), 416, 139, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_d(), 445, 139, 0);
		cb.endText();
		
		document.newPage(); 
		bg_img2.scaleToFit(595, 842);
		document.add(bg_img2);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 797, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPayA(), 400, 764, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryFee(), 421, 691, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getMessFee(), 471, 635, 0); 
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getYue(), 145, 395, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getJi(), 177, 395, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getBanNian(), 208, 395, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getNian(), 250, 395, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_y(), 437, 395, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_m(), 479, 395, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_d(), 505, 395, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifuDay(), 101, 377, 0);
		if (model.getZhifuDate() == "") {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifuDate(), 73, 359, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifuDate(), 73, 359, 0);
		}
		cb.endText();
		
		document.newPage(); 
		bg_img3.scaleToFit(595, 842);
		document.add(bg_img3);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 797, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryJb(), 124, 727, 0);

		cb.endText();
		
		document.newPage(); 
		bg_img4.scaleToFit(595, 842);
		document.add(bg_img4);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 797, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO1(), 58, 201, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO2(), 58, 176, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO3(), 58, 152, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO4(), 58, 129, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO5(), 58, 104, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO6(), 58, 80, 0);
		/*cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO7(), 58, 115, 0);*/
		
		cb.endText();
		
		document.newPage(); 
		bg_img5.scaleToFit(595, 842);
		document.add(bg_img5);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 797, 0);
		
		cb.endText();
	
		//5.关闭文档
		document.close();
	}
		
}
