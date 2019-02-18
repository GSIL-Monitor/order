package com.emotte.order.util.pdfTools.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.emotte.order.util.pdfTools.templateModel.ContractModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 三方合同模板
 */
public class Contract extends BaseTemplate{
	private static Log log = LogFactory.getLog(Contract.class);
	
	private ContractModel model;
	
	//背景图片
	private Image bg_img1;
	private Image bg_img2;
	private Image bg_img3;
	private Image bg_img4;
	
	public Contract(File pdf,ContractModel model,String filePath,Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮家政服务三方协议";
			this.model = model;
			if(preview!=null && preview.intValue()==1){
				bg_img1 = Image.getInstance(filePath+"/images/contract/cpe_v2.0_1_yl.png");
				bg_img2 = Image.getInstance(filePath+"/images/contract/cpe_v2.0_2_yl.png");
				bg_img3 = Image.getInstance(filePath+"/images/contract/cpe_v2.0_3_yl.png");
				bg_img4 = Image.getInstance(filePath+"/images/contract/cpe_v2.0_4_yl.png");
			}else{
				bg_img1 = Image.getInstance(filePath+"/images/contract/cpe_v2.0_1_yl.png");
				bg_img2 = Image.getInstance(filePath+"/images/contract/cpe_v2.0_2_yl.png");
				bg_img3 = Image.getInstance(filePath+"/images/contract/cpe_v2.0_3_yl.png");
				bg_img4 = Image.getInstance(filePath+"/images/contract/cpe_v2.0_4_yl.png");
			}
		} catch (Exception e) {
			log.info("error",e);
		}
	}

	@Override
	public void WritePdf() throws FileNotFoundException, DocumentException {
		Document document = new Document(PageSize.A4,0,0,0,0);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf));
		//writer.setEncryption(USER_PASS.getBytes(), OWNER_PASS.getBytes(),PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128); //加密
//		writer.setEncryption(null, null,PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128); //设置打印权限
		document.open();
		writer.setViewerPreferences(PdfWriter.PageLayoutTwoColumnLeft); //双列显示，奇数列在左
		writer.setViewerPreferences(PdfWriter.FitWindow); //自动调节文档窗口尺寸以适合显示第一页
		PdfContentByte cb = writer.getDirectContent(); //文字绝对定位对象
		 
		// 添加Meta信息（文档属性中的说明）
		document.addAuthor(authorName);
		document.addCreator(authorName);
		document.addTitle(title);	//标题
		document.addSubject("管家帮家政服务合同");	//主题
		document.addCreationDate();
		document.addKeywords("合同"); //关键字
		
		bg_img1.scaleToFit(595, 842);//大小
		document.add(bg_img1);//842×595
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 393, 790, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 141, 708, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeA(), 141, 689, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumA(), 275, 689, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneA(), 465, 689, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrA(), 141, 670, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactB(), 141, 632, 0);
//		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getTeacharName(), 110, 655, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getGroupName(), 465, 632, 0);
//		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneB(), 440, 655, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrB(), 141, 613, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactC(), 141, 574, 0);
//		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,bean.getCardTypeC(), 110, 595, 0);
//		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,bean.getCardNumC(), 240, 595, 0);
//		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,bean.getPhoneC(), 440, 595, 0);
//		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,bean.getAddrC(), 90, 581, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceNo(), 290, 489, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkZdg(), 190, 451, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkOthers(), 420, 451, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 155, 427, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_y(), 77, 378, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_m(), 124, 378, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_d(), 153, 378, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_y(), 210, 378, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_m(), 256, 378, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_d(), 291, 378, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryMoneyM(), 220, 292, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceMoney(), 400, 254, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getBz1(), 333, 236, 0); //TODO
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getBz2(), 60, 216, 0);
		
//		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,bean.getServiceMoneyOne(), 267, 255, 0);
//		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,bean.getServiceMoneyTwo(), 403, 255, 0);
//		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,bean.getServiceMoneyThree(), 64, 240, 0);
//		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,bean.getServiceMoneyFour(), 272, 240, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getTotalMoneyCn(), 444, 197, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getTotalMoney(), 110, 178, 0);
//		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getHrDay(), 183, 151, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getYue(), 484, 130, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getJi(), 521, 130, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getBanNian(), 70, 111, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getNian(), 117, 111, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_y(), 289, 111, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_m(), 337, 111, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_d(), 366, 111, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifuDay(), 435, 111, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_remark(), 188, 92, 0);
		
		cb.endText();
		
		document.newPage(); 						//设置新合同页
		bg_img2.scaleToFit(595, 842);
		document.add(bg_img2);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 393, 790, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryJb(), 490, 481, 0);
		
		cb.endText();
		
		document.newPage(); 						//设置新合同页
		bg_img3.scaleToFit(595, 842);
		document.add(bg_img3);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 393, 790, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO1(), 60, 249, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO2(), 60, 229, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO3(), 60, 210, 0);
		
		cb.endText();
		
		document.newPage(); 						//设置新合同页
		bg_img4.scaleToFit(595, 842);
		document.add(bg_img4);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 10);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 393, 790, 0);
		
		cb.endText();
		
		/*if(!model.getFj_path().equals("")){
			document.newPage(); 
			bg_img5.scaleToFit(595, 842);
			document.add(bg_img5);
			cb.beginText();
			cb.setFontAndSize(bfChinese, 10);
			
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 393, 765, 0);
			
			cb.endText();
		}*/
		
		//5.关闭文档
		document.close();
	}
}
