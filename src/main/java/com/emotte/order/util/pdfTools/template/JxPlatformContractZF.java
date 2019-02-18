package com.emotte.order.util.pdfTools.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.emotte.order.util.pdfTools.templateModel.JxPlatformContractZFModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class JxPlatformContractZF extends BaseTemplate{
	private static Log log = LogFactory.getLog(JxPlatformContractZFModel.class);

	private JxPlatformContractZFModel model;
	
	//背景图片
	private Image bg_img1;
	private Image bg_img2;
	private Image bg_img3;
	private Image bg_img4;
	private Image bg_img5;
	
	public JxPlatformContractZF(File pdf,JxPlatformContractZFModel model,String filePath,Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮平台信息服务协议（直付）";
			this.model = model;
			if(preview!=null && preview.intValue()==1){
				bg_img1 = Image.getInstance(filePath+"/images/20190117/20190117jxzf/jxzf_yl_1.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20190117/20190117jxzf/jxzf_yl_2.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20190117/20190117jxzf/jxzf_yl_3.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20190117/20190117jxzf/jxzf_yl_4.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20190117/20190117jxzf/jxzf_yl_5.jpg");
			}else{
				bg_img1 = Image.getInstance(filePath+"/images/20190117/20190117jxzf/jxzf_zs_1.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20190117/20190117jxzf/jxzf_zs_2.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20190117/20190117jxzf/jxzf_zs_3.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20190117/20190117jxzf/jxzf_zs_4.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20190117/20190117jxzf/jxzf_zs_5.jpg");
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
		
		//第1页开始
		bg_img1.scaleToFit(595, 842);
		document.add(bg_img1);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);//合同编号
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_y(), 185, 699, 0);//年
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_m(), 255, 699, 0);//月
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_d(), 292, 699, 0);//日
		if(model.getPactA() == null || "".equals(model.getPactA())){
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 227, 679, 0);//甲方姓名
		}else{
			if(model.getPactA().length() > 4){
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 153, 679, 0);//甲方姓名
			}else{
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 227, 679, 0);//甲方姓名
			}
		}
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneA(), 423, 679, 0);//甲方电话
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeA(), 178, 657, 0);//甲方证件类型
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumA(), 362, 657, 0);//甲方证件号码
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 107, 636, 0);//甲方服务地址
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactB(), 183, 615, 0);//乙方名称
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrB(), 107, 594, 0);//乙方地址
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactC(), 235, 552, 0);//丙方姓名
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneC(), 427, 552, 0);//丙方电话
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeC(), 161, 531, 0);//丙方证件类型
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumC(), 344, 531, 0);//丙方证件号码
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrC(), 107, 510, 0);//丙方地址
		if (model.getServiceNo() == null || "".equals(model.getServiceNo())) {
			//服务序号
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 481, 245, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceNo(), 481, 245, 0);
		}
		if (model.getRemarkZdg() == null || "".equals(model.getRemarkZdg())) {
			//钟点工工作时间
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 295, 208, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkZdg(), 248, 208, 0);
		}
		if(model.getServiceNo() != null && "8".equals(model.getServiceNo())){
			if ((model.getRemarkOthers() == null || "".equals(model.getRemarkOthers()))) { 
				//其他服务
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 477, 208, 0);
			}else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkOthers(), 435, 208, 0);
			}
		}
		if (model.getAddrService() == null || "".equals(model.getAddrService())) {
			//服务场所
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 300, 149, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 136, 149, 0);
		}
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_y(), 209, 128, 0);//服务期限开始年
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_m(), 251, 128, 0);//服务期限开始月
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_d(), 283, 128, 0);//服务期限开始日
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_y(), 338, 128, 0);//服务期限结束年
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_m(), 386, 128, 0);//服务期限结束月
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_d(), 421, 128, 0);//服务期限结束日
		cb.endText();
		
		//第2页开始
		document.newPage(); 
		bg_img2.scaleToFit(595, 842);
		document.add(bg_img2);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);
		if (model.getPayA() == null || "".equals(model.getPayA())) {
			//平台信息费
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 384, 744, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPayA(), 369, 744, 0);
		}
		if (model.getSalaryFee() == null || "".equals(model.getSalaryFee())) {
			//具体收费标准
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 450, 692, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryFee(), 400, 692, 0);
		}
		if (model.getMessFee() == null || "".equals(model.getMessFee())) {
			//具体支付标准为
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 470, 639, 0); 
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getMessFee(), 455, 639, 0); 
		}
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryJb(), 135, 244, 0);
		cb.endText();
		
		//第3页开始
		document.newPage(); 
		bg_img3.scaleToFit(595, 842);
		document.add(bg_img3);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);
		cb.endText();
		
		//第4页开始
		document.newPage(); 
		bg_img4.scaleToFit(595, 842);
		document.add(bg_img4);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);
		if ("".equals(model.getO1())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 388, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO1(), 62, 388, 0);
		}
		if ("".equals(model.getO2())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 364, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO2(), 62, 364, 0);
		}
		if ("".equals(model.getO3())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 340, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO3(), 62, 340, 0);
		}
		if ("".equals(model.getO4())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 316, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO4(), 62, 316, 0);
		}
		if ("".equals(model.getO5())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 292, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO5(), 62, 292, 0);
		}
		if ("".equals(model.getO6())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 268, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO6(), 62, 268, 0);
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
	
		//关闭文档
		document.close();
	}
		
}
