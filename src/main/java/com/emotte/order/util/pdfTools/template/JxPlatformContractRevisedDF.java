package com.emotte.order.util.pdfTools.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.emotte.order.util.pdfTools.templateModel.JxPlatformContractRevisedDFModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class JxPlatformContractRevisedDF extends BaseTemplate{
	private static Log log = LogFactory.getLog(JxPlatformContractRevisedDFModel.class);

	private JxPlatformContractRevisedDFModel model;
	
	//背景图片
	private Image bg_img1;
	private Image bg_img2;
	private Image bg_img3;
	private Image bg_img4;
	private Image bg_img5;
	
	public JxPlatformContractRevisedDF(File pdf,JxPlatformContractRevisedDFModel model,String filePath,Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮平台信息服务协议（代付）";
			this.model = model;
			if(preview!=null && preview.intValue()==1){
				bg_img1 = Image.getInstance(filePath+"/images/20190117/20190117jxdfxdb/jxdfxdb_yl_1.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20190117/20190117jxdfxdb/jxdfxdb_yl_2.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20190117/20190117jxdfxdb/jxdfxdb_yl_3.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20190117/20190117jxdfxdb/jxdfxdb_yl_4.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20190117/20190117jxdfxdb/jxdfxdb_yl_5.jpg");
			}else{
				bg_img1 = Image.getInstance(filePath+"/images/20190117/20190117jxdfxdb/jxdfxdb_zs_1.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20190117/20190117jxdfxdb/jxdfxdb_zs_2.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20190117/20190117jxdfxdb/jxdfxdb_zs_3.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20190117/20190117jxdfxdb/jxdfxdb_zs_4.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20190117/20190117jxdfxdb/jxdfxdb_zs_5.jpg");
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
		document.addTitle(title);//标题
		document.addSubject("管家帮平台信息服务协议");//主题
		document.addCreationDate();
		document.addKeywords("合同");//关键字
		
		//第1页开始
		bg_img1.scaleToFit(595, 842);
		document.add(bg_img1);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);//合同编号
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_y(), 163, 700, 0);//年
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_m(), 224, 700, 0);//月
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getQs_d(), 261, 700, 0);//日
		if(model.getPactA() == null || "".equals(model.getPactA())){
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 227, 679, 0);//甲方姓名
		}else{
			if(model.getPactA().length() > 4){
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 139, 678, 0);//甲方姓名
			}else{
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 200, 678, 0);//甲方姓名
			}
		}
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneA(), 390, 678, 0);//甲方电话
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeA(), 130, 657, 0);//甲方证件类型
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumA(), 372, 657, 0);//甲方证件号码
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 106, 637, 0);//甲方地址
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactB(), 188, 595, 0);//乙方名称
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrB(), 109, 574, 0);//乙方地址
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactC(), 200, 553, 0);//丙方姓名
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPhoneC(), 380, 553, 0);//丙方电话
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardTypeC(), 153, 532, 0);//丙方证件类型
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCardNumC(), 345, 532, 0);//丙方证件号码
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrC(), 100, 511, 0);//丙方地址
		if (model.getServiceNo() == null || "".equals(model.getServiceNo())) {
			//第几项服务
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 435, 286, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceNo(), 435, 286, 0);
		}
		if(model.getServiceNo() != null && "7".equals(model.getServiceNo())){
			if (model.getRemarkZdg() == null || "".equals(model.getRemarkZdg())) {
				//钟点工工作时间
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 220, 252, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkZdg(), 175, 252, 0);
			}
		}else{
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 220, 252, 0);
		}
		if(model.getServiceNo() != null && "8".equals(model.getServiceNo())){
			if ((model.getRemarkOthers() == null || "".equals(model.getRemarkOthers()))) { 
				//其它服务
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 420, 253, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkOthers(), 365, 253, 0);
			}
		}else{
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 420, 253, 0);
		}
		if (model.getAddrService() == null || "".equals(model.getAddrService())) {
			//服务场所
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 250, 214, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 138, 214, 0);
		}
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_y(), 187, 194, 0);//服务期限开始年
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_m(), 231, 194, 0);//服务期限开始月
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_d(), 258, 194, 0);//服务期限开始日
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_y(), 310, 194, 0);//服务期限结束年
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_m(), 354, 194, 0);//服务期限结束月
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_d(), 384, 194, 0);//服务期限结束日
		if (model.getPayA() == null || "".equals(model.getPayA())) {
			//向乙方支付平台信息费
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 347, 118, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPayA(), 333, 118, 0);
		}
		cb.endText();
		
		
		//第2页开始
		document.newPage(); 
		bg_img2.scaleToFit(595, 842);
		document.add(bg_img2);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);//合同编号
		if (model.getSalaryFee() == null || "".equals(model.getSalaryFee())) {
			//具体收费标准
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 390, 768, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryFee(), 359, 768, 0);
		}
		if (model.getMessFee() == null || "".equals(model.getMessFee())) {
			//具体支付标准为
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 421, 721, 0); 
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getMessFee(), 411, 721, 0); 
		}
		//一次性预付劳务费 
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getYue(),78,469, 0);
		if (model.getYue() != null && !"".equals(model.getYue())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"√",118,576, 0);
			if (model.getZhifu_y_1() == null || "".equals(model.getZhifu_y_1())) {
				//年
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 228, 469, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_y_1(), 217, 469, 0);
			}
			if (model.getZhifu_m_1() == null || "".equals(model.getZhifu_m_1())) {
				//月
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 263, 469, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_m_1(), 263, 469, 0);
			}
			if (model.getZhifu_d_1() == null || "".equals(model.getZhifu_d_1())) {
				//日
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 289, 469, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_d_1(), 289, 469, 0);
			}
			if (model.getAllPay_1() == null || "".equals(model.getAllPay_1())) {
				//费用总计
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 180, 456, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 340, 456, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPay_1(),150, 456, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPayCN_1(),268, 456, 0);
			}
		}else{
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 228, 469, 0);//年
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 263, 469, 0);//月
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 289, 469, 0);//日
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 180, 456, 0);//费用总计数字
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 340, 456, 0);//费用总计中文
		}
		//唯品会白条支付
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getJi(), 78, 442, 0);
		if (model.getJi() != null && !"".equals(model.getJi())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"√",118,576, 0);
			if (model.getZhifu_y_2() == null || "".equals(model.getZhifu_y_2())) {
				//年
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 464, 442, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_y_2(), 459, 442, 0);
			}
			if (model.getZhifu_m_2() == null || "".equals(model.getZhifu_m_2())) {
				//月
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 494, 442, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_m_2(), 494, 442, 0);
			}
			if (model.getZhifu_d_2() == null || "".equals(model.getZhifu_d_2())) {
				//日
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 520, 442, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_d_2(), 520, 442, 0);
			}
			if (model.getAllPay_2() == null || "".equals(model.getAllPay_2())) {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 417, 428, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 166, 415, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPay_2(), 383, 428, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPayCN_2(), 101, 415, 0);
			}
		}else{
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 464, 442, 0);//年
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 494, 442, 0);//月
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 520, 442, 0);//日
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 417, 428, 0);//费用总计数字
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 166, 415, 0);//费用总计中文
		}
		//银行白条支付
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getBanNian(), 78, 400, 0);
		if (model.getBanNian() != null && !"".equals(model.getBanNian())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"√",118,576, 0);
			if (model.getZhifu_y_3() == null || "".equals(model.getZhifu_y_3())) {
				//年
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 456, 400, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_y_3(), 448, 400, 0);
			}
			if (model.getZhifu_m_3() == null || "".equals(model.getZhifu_m_3())) {
				//月
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 484, 400, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_m_3(), 480, 400, 0);
			}
			if (model.getZhifu_d_3() == null || "".equals(model.getZhifu_d_3())) {
				//日
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 511, 400, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_d_3(), 506, 400, 0);
			}
			if (model.getAllPay_3() == null || "".equals(model.getAllPay_3())) {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 414, 388, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 165, 374, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPay_3(), 372, 388, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPayCN_3(), 101, 374, 0);
			}
		}else{
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 456, 400, 0);//年
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 484, 400, 0);//月
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 511, 400, 0);//日
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 414, 388, 0);//费用总计数字
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 165, 374, 0);//费用总计中文
		}
		//其他支付方式
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getNian(), 80,360, 0);
		if (model.getNian() != null && !"".equals(model.getNian())) {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"√",118,576, 0);
			if (model.getOtherWay() == null || "".equals(model.getOtherWay())) {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 209, 360, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getOtherWay(), 162,360, 0);
			}
			if (model.getZhifu_y_4() == null || "".equals(model.getZhifu_y_4())) {
				//年
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 318, 360, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_y_4(), 311, 360, 0);
			}
			if (model.getZhifu_m_4() == null || "".equals(model.getZhifu_m_4())) {
				//月
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 347, 360, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_m_4(), 344, 360, 0);
			}
			if (model.getZhifu_d_4() == null || "".equals(model.getZhifu_d_4())) {
				//日
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 373, 360, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_d_4(), 371, 360, 0);
			}
		}else{
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 209, 360, 0);//支付方式
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 318, 360, 0);//年
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 347, 360, 0);//月
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 373, 360, 0);//日
		}
		cb.endText();
		
		//第3页开始
		document.newPage(); 
		bg_img3.scaleToFit(595, 842);
		document.add(bg_img3);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);//合同编号
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryJb(),488,740, 0);//日服务费标准的几倍
		cb.endText();
		
		//第4页开始
		document.newPage(); 
		bg_img4.scaleToFit(595, 842);
		document.add(bg_img4);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);//合同编号
		if ("".equals(model.getO1())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 323, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO1(), 62, 323, 0);
		}
		if ("".equals(model.getO2())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 299, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO2(), 62, 299, 0);
		}
		if ("".equals(model.getO3())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 275, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO3(), 62, 275, 0);
		}
		if ("".equals(model.getO4())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 251, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO4(), 62, 251, 0);
		}
		if ("".equals(model.getO5())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 227, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO5(), 62, 227, 0);
		}
		if ("".equals(model.getO6())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 293, 203, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO6(), 62, 203, 0);
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
		
		//5.关闭文档
		document.close();
	}
		
}
