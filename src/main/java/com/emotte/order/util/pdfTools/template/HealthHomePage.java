package com.emotte.order.util.pdfTools.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.emotte.order.util.pdfTools.templateModel.HealthContractModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class HealthHomePage extends BaseTemplate{
	private static Log log = LogFactory.getLog(HealthContractModel.class);

	private HealthContractModel model;
	
	//背景图片
	private Image bg_img1;
	
	public HealthHomePage(File pdf,HealthContractModel model,String filePath,Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮陪护服务协议首页";
			this.model = model;
			bg_img1 = Image.getInstance(filePath+"/images/20180716/hl_1_zs.jpg");
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
		document.addSubject("管家帮陪护服务协议首页");	//主题
		document.addCreationDate();
		document.addKeywords("合同"); //关键字
		
		bg_img1.scaleToFit(595, 842);//大小
		document.add(bg_img1);//842×595
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 368, 801, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 142, 680, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneA(), 365, 659, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumA(), 130, 659, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrA(), 118, 638, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceAddrA(), 118, 616, 0);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getHospitalizationNum(), 106, 596, 0);  //住院号
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getDepartments(), 225, 596, 0);         //科室
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRoomNumber(), 353, 596, 0);          //病房号
		if(null!=model.getBedNumber_a()||!"".equals(model.getBedNumber_a())||!"null".equals(model.getBedNumber_a())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getBedNumber_a(), 450, 596, 0);           //床号
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"", 450,596,0);        
		}
		if(null!=model.getBedNumber_b()||!"".equals(model.getBedNumber_b())||!"null".equals(model.getBedNumber_b())) {
		    cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getBedNumber_b(),491,595,0); 
		}else {
			 cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"",491,595,0);
		}  
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getConsumerstate(), 60, 559, 0);       //服务对象现状
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactB(), 190, 538, 0);              //乙方 
		if(null!=model.getPhoneB() || !"".equals(model.getPhoneB())||!"null".equals(model.getPhoneB())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneB(),372, 517, 0);   //乙方电话
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"",372,517,0);    //乙方电话
		}
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrB(), 94, 517, 0);               //乙方地址
		
		//丙方信息
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactC(), 185, 496, 0);             //丙方
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneC(), 376, 474, 0);            //丙方联系电话
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumC(), 135, 474, 0);          //丙方证件号
		
		//服务对象基本情况信息
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getConsumersName(), 142, 301, 0);     
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getConsumersCard(), 338, 301, 0);
		//cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCustconsumerRelation(), 338, 301, 0);
		if(null!=model.getRelation_a() || !"".equals(model.getRelation_a())||!"null".equals(model.getRelation_a())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRelation_a(),78,262,0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"",78,262,0);
		}
		
		if(null!=model.getRelation_b()||!"".equals(model.getRelation_b())||!"null".equals(model.getRelation_b())){
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRelation_b(),78,239,0);
			if(null!=model.getRelation_relatives()||!"".equals(model.getRelation_relatives())||!"null".equals(model.getRelation_relatives())){
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRelation_relatives(), 199,236, 0);  //具体亲属关系
			}else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"", 199,236,0);  //具体亲属关系
			}
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"",78,239,0);
		}
		
		
		if(null!=model.getRelation_c()||!"".equals(model.getRelation_c())||!"null".equals(model.getRelation_c())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRelation_c(), 78,220,0);
			if(null!=model.getRelation_relatives()||!"".equals(model.getRelation_relatives())||!"null".equals(model.getRelation_relatives())){
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRelation_entrust(), 234,214, 0);    //具体委托关系
			}else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"", 234,214, 0);    //具体委托关系
			}
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"",78,220,0);
		}
		
		//产妇预产期
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getChildbirth_y(), 231, 192, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getChildbirth_m(), 310, 192, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getChildbirth_d(), 360, 192, 0);
		//特殊注意事项
		if(null!=model.getSpecialConsiderations_yes() || !"".equals(model.getSpecialConsiderations_yes())||!"null".equals(model.getSpecialConsiderations_yes())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSpecialConsiderations_yes(),139,159,0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"",139,159,0);
		}
		
		if(null!=model.getSpecialConsiderations_no()|| !"".equals(model.getSpecialConsiderations_no())||!"null".equals(model.getSpecialConsiderations_no())){
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSpecialConsiderations_no(),93,159,0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"",93,159,0);
		}
	    
		
        cb.endText();
		
		//5.关闭文档
		document.close();
	}
		
}
