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

public class JxPlatformRevisedDFHomePage extends BaseTemplate {
	private static Log log = LogFactory.getLog(JxPlatformContractRevisedDFModel.class);

	private JxPlatformContractRevisedDFModel model;

	// 背景图片
	private Image bg_img1;

	public JxPlatformRevisedDFHomePage(File pdf, JxPlatformContractRevisedDFModel model, String filePath,
			Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮平台信息服务协议（代付）首页";
			this.model = model;
			bg_img1 = Image.getInstance(filePath + "/images/20190117/20190117jxdfxdb/jxdfxdb_zs_1.jpg");
		} catch (Exception e) {
			log.info("error", e);
		}
	}

	@Override
	public void WritePdf() throws FileNotFoundException, DocumentException {
		Document document = new Document(PageSize.A4, 0, 0, 0, 0);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdf));
		document.open();
		writer.setViewerPreferences(PdfWriter.PageLayoutTwoColumnLeft); // 双列显示，奇数列在左
		writer.setViewerPreferences(PdfWriter.FitWindow); // 自动调节文档窗口尺寸以适合显示第一页
		PdfContentByte cb = writer.getDirectContent(); // 文字绝对定位对象

		// 添加Meta信息（文档属性中的说明）
		document.addAuthor(authorName);
		document.addCreator(authorName);
		document.addTitle(title);
		document.addSubject("管家帮平台信息服务协议"); 
		document.addCreationDate();
		document.addKeywords("合同");

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
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT,model.getGroupName(), 109, 573, 0);//乙方服务站
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

		// 关闭文档
		document.close();
	}

}
