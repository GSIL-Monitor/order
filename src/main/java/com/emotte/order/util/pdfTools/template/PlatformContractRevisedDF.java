package com.emotte.order.util.pdfTools.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.emotte.order.util.pdfTools.templateModel.PlatformContractRevisedDFModel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

public class PlatformContractRevisedDF extends BaseTemplate{
	private static Log log = LogFactory.getLog(PlatformContractRevisedDFModel.class);

	private PlatformContractRevisedDFModel model;
	
	//背景图片
	private Image bg_img1;
	private Image bg_img2;
	private Image bg_img3;
	private Image bg_img4;
	private Image bg_img5;
	
	public PlatformContractRevisedDF(File pdf,PlatformContractRevisedDFModel model,String filePath,Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮平台信息服务协议（代付）";
			this.model = model;
			if(preview!=null && preview.intValue()==1){
				bg_img1 = Image.getInstance(filePath+"/images/20190122/20190122dfxdb/df_yl_1.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20190122/20190122dfxdb/df_yl_2.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20190122/20190122dfxdb/df_yl_3.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20190122/20190122dfxdb/df_yl_4.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20190122/20190122dfxdb/df_yl_5.jpg");
			}else{
				bg_img1 = Image.getInstance(filePath+"/images/20190122/20190122dfxdb/df_zs_1.jpg");
				bg_img2 = Image.getInstance(filePath+"/images/20190122/20190122dfxdb/df_zs_2.jpg");
				bg_img3 = Image.getInstance(filePath+"/images/20190122/20190122dfxdb/df_zs_3.jpg");
				bg_img4 = Image.getInstance(filePath+"/images/20190122/20190122dfxdb/df_zs_4.jpg");
				bg_img5 = Image.getInstance(filePath+"/images/20190122/20190122dfxdb/df_zs_5.jpg");
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
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getPactA(), 150, 678, 0);
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
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 473, 245, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getServiceNo(), 473, 245, 0);
		}
		if (model.getRemarkZdg() == null || "".equals(model.getRemarkZdg())) {//钟点工工作时间备注
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 300, 214, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkZdg(), 258, 214, 0);
		}
		if ((model.getRemarkOthers() == null || "".equals(model.getRemarkOthers()))  || (model.getServiceNo() != null && !"8".equals(model.getServiceNo()))) { //其他服务
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 300, 199, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getRemarkOthers(), 138, 199, 0);
		}
		if (model.getAddrService() == null || "".equals(model.getAddrService())) {//服务地址
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 300, 152, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAddrService(), 138, 152, 0);
		}
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_y(), 210, 136, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_m(), 258, 136, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getF_d(), 300, 136, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_y(), 357, 136, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_m(), 405, 136, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getT_d(), 443, 136, 0);
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
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 475, 662, 0); 
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getMessFee(), 466, 662, 0); 
		}
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getYue(), 77, 457, 0);
		//TODO: 一次性预付劳务费 
		if (model.getYue() != null || "".equals(model.getYue())) {
			if (model.getZhifu_y_1() == null || "".equals(model.getZhifu_y_1())) {//年
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 249, 458, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_y_1(), 239, 458, 0);
			}
			if (model.getZhifu_m_1() == null || "".equals(model.getZhifu_m_1())) {//月
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 285, 458, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_m_1(), 283, 458, 0);
			}
			if (model.getZhifu_d_1() == null || "".equals(model.getZhifu_d_1())) {//日
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 312, 458, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_d_1(), 312, 458, 0);
			}
			if (model.getAllPay_1() == null || "".equals(model.getAllPay_1())) {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 249, 442, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 404, 442, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPay_1(), 234, 442, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPayCN_1(), 333, 442, 0);
			}
		}
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getJi(), 77, 425, 0);
		//TODO: 白条支付
		if (model.getJi() != null || "".equals(model.getJi())) {
			if (model.getZhifu_y_2() == null || "".equals(model.getZhifu_y_2())) {//年
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 482, 426, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_y_2(), 473, 426, 0);
			}
			if (model.getZhifu_m_2() == null || "".equals(model.getZhifu_m_2())) {//月
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 519, 426, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_m_2(), 518, 426, 0);
			}
			if (model.getZhifu_d_2() == null || "".equals(model.getZhifu_d_2())) {//日
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 59, 411, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_d_2(), 59, 411, 0);
			}
			if (model.getAllPay_2() == null || "".equals(model.getAllPay_2())) {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 480, 411, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 113, 395, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPay_2(), 451, 411, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPayCN_2(), 77, 395, 0);
			}
		}
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getBanNian(), 77, 378, 0);
		//TODO:银行白条支付
		if (model.getBanNian() != null || "".equals(model.getBanNian())) {
			if (model.getZhifu_y_3() == null || "".equals(model.getZhifu_y_3())) {//年
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 511, 378, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_y_3(), 503, 378, 0);
			}
			if (model.getZhifu_m_3() == null || "".equals(model.getZhifu_m_3())) {//月
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 63, 364, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_m_3(), 61, 364, 0);
			}
			if (model.getZhifu_d_3() == null || "".equals(model.getZhifu_d_3())) {//日
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 92, 364, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_d_3(), 92, 364, 0);
			}
			if (model.getAllPay_3() == null || "".equals(model.getAllPay_3())) {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 88, 347, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 200, 347, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPay_3(), 68, 347, 0);
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getAllPayCN_3(), 168, 347, 0);
			}
		}
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getNian(), 77, 330, 0);
		//TODO:其他支付方式
		if (model.getNian() != null || "".equals(model.getNian())) {
			if (model.getOtherWay() == null || "".equals(model.getOtherWay())) {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 254, 331, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getOtherWay(), 186, 331, 0);
			}
			if (model.getZhifu_y_4() == null || "".equals(model.getZhifu_y_4())) {//年
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 368, 331, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_y_4(), 350, 331, 0);
			}
			if (model.getZhifu_m_4() == null || "".equals(model.getZhifu_m_4())) {//月
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 405, 331, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_m_4(), 392, 331, 0);
			}
			if (model.getZhifu_d_4() == null || "".equals(model.getZhifu_d_4())) {//日
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 435, 331, 0);
			} else {
				cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getZhifu_d_4(), 424, 331, 0);
			}
		}
		cb.endText();
		
		document.newPage(); 
		bg_img3.scaleToFit(595, 842);
		document.add(bg_img3);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getSalaryJb(), 138, 689, 0);

		cb.endText();
		
		document.newPage(); 
		bg_img4.scaleToFit(595, 842);
		document.add(bg_img4);
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getCode(), 350, 800, 0);
		
		if ("".equals(model.getO1())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 230, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO1(), 62, 230, 0);
		}
		if ("".equals(model.getO2())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 200, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO2(), 62, 200, 0);
		}
		if ("".equals(model.getO3())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 172, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO3(), 62, 172, 0);
		}
		if ("".equals(model.getO4())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 142, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO4(), 62, 142, 0);
		}
		if ("".equals(model.getO5())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 111, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO5(), 62, 111, 0);
		}
		if ("".equals(model.getO6())) { 
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,"/", 290, 81, 0);
		}else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getO6(), 62, 81, 0);
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
