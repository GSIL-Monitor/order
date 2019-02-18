package com.emotte.order.util.pdfTools.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.emotte.order.util.NumberToCN;
import com.emotte.order.util.pdfTools.templateModel.HealthContractModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class HealthContract extends BaseTemplate{
	private static Log log = LogFactory.getLog(HealthContractModel.class);

	private HealthContractModel model;
	
	//背景图片
	private Image bg_img1;
	private Image bg_img2;
	private Image bg_img3;
	private Image bg_img4;
	
	public HealthContract(File pdf,HealthContractModel model,String filePath,Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮陪护服务协议书";
			this.model = model;
			if(preview!=null && preview.intValue()==1){
				bg_img1 = Image.getInstance(filePath+"/images/20180716/hl_1_yl.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20180716/hl_2_yl.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20180716/hl_3_yl.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20180716/hl_4_yl.jpg");
				
			}else{
				bg_img1 = Image.getInstance(filePath+"/images/20180716/hl_1_zs.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20180716/hl_2_zs.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20180716/hl_3_zs.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20180716/hl_4_zs.jpg");
				
			}
		} catch (Exception e) {
			log.info("error",e);
		}
	}

	@Override
	public void WritePdf() throws FileNotFoundException, DocumentException {
		NumberToCN t = new NumberToCN();
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
		document.addSubject("管家帮陪护服务协议书");	//主题
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
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSpecialConsiderations_no(), 93,159,0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"",93,159,0);
		}
		
		cb.endText();
		
		document.newPage(); 
		bg_img2.scaleToFit(595, 842);
		document.add(bg_img2);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 368, 801, 0);  //合同编号
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceItems(), 194, 746, 0);              //甲方选择服务项目
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceAddrA(), 159, 700, 0);             //服务地址
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceStart_y(), 194, 680, 0);            //服务起始年
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceStart_m(), 234, 680, 0);            //服务起始月
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceStart_r(), 266, 680, 0);            //服务起始日
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getHostsitDay(), 464,679, 0);                 //暂定天数
		
		if(null!=model.getServiceFormat_24()||!"".equals(model.getServiceFormat_24())||!"null".equals(model.getServiceFormat_24())) {                  //服务形式
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceFormat_24(), 270,647,0);                 
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"", 270,647,0);   
		}
		
		
		
		if(null!=model.getServiceFormat_day()||!"".equals(model.getServiceFormat_day())||!"null".equals(model.getServiceFormat_day())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceFormat_day(), 217,646,0);   
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"", 217,646,0);  
		}
		
		
	
		if(null!=model.getDeliveryMode_sc()||!"".equals(model.getDeliveryMode_sc())||!"null".equals(model.getDeliveryMode_sc())) {                  //分娩方式
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getDeliveryMode_sc(), 192,625, 0);                 
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"",192,625,0);  
		}
		
		
		
		if(null!=model.getDeliveryMode_gc()||!"".equals(model.getDeliveryMode_gc())||!"null".equals(model.getDeliveryMode_gc())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getDeliveryMode_gc(), 259,625,0);   
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"", 259,625,0); 
		}
		
		
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getReplaceNumber(), 194, 316, 0);            //更换次数
		
		cb.endText();
		
		document.newPage(); 
		bg_img3.scaleToFit(595, 842);
		document.add(bg_img3);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 368, 801, 0);
		//服务收费标准
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceprojectStandard(), 307,635, 0);
		//甲方向乙方支付费用
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPayment(), 433,597,0);
		//甲方向乙方支付费用  大写
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,t.getCn(model.getPayment()), 136,575, 0);
		// 开户名称        
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPartyBaccountName(), 138,532, 0);
		// 开户名称        
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPartyBaccountNum(), 138,511, 0);
		// 开户名称        
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPartyBaccountBank(), 138,488, 0);
		
		cb.endText();
		
		document.newPage(); 
		bg_img4.scaleToFit(595,842);
		document.add(bg_img4);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 368, 801, 0);
		
		cb.endText();
		
		//5.关闭文档
		document.close();
	}
		
}
