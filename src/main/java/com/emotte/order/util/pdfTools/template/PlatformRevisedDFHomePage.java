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

public class PlatformRevisedDFHomePage extends BaseTemplate{
	private static Log log = LogFactory.getLog(PlatformContractRevisedDFModel.class);

	private PlatformContractRevisedDFModel model;
	
	//背景图片
	private Image bg_img1;
	
	public PlatformRevisedDFHomePage(File pdf,PlatformContractRevisedDFModel model,String filePath,Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮平台信息服务协议（代付）首页";
			this.model = model;
			bg_img1 = Image.getInstance(filePath+"/images/20190122/20190122dfxdb/df_zs_1.jpg");
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
		
	
		//关闭文档
		document.close();
	}
		
}
