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

public class PlatformContractHjblDfHonePage extends BaseTemplate {
	private static Log log = LogFactory.getLog(PlatformContractRevisedDFModel.class);

	private PlatformContractRevisedDFModel model;

	// 背景图片
	private Image bg_img1;

	public PlatformContractHjblDfHonePage(File pdf, PlatformContractRevisedDFModel model, String filePath,
			Integer preview) {
		super(pdf);
		try {
			super.title = "管家帮平台信息服务协议（海金保理代付）";
			this.model = model;
			bg_img1 = Image.getInstance(filePath + "/images/20190122/20190122dfhj/hjbl_zs_1.jpg");
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
		document.addTitle(title); // 标题
		document.addSubject("管家帮平台信息服务协议"); // 主题
		document.addCreationDate();
		document.addKeywords("合同"); // 关键字

		bg_img1.scaleToFit(595, 842);// 大小
		document.add(bg_img1);// 842×595
		cb.beginText();
		cb.setFontAndSize(bfChinese, 11);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getCode(), 370, 800, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getQs_y(), 187, 702, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getQs_m(), 250, 702, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getQs_d(), 302, 702, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getPactA(), 222, 681, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getPhoneA(), 440, 681, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getCardTypeA(), 180, 658, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getCardNumA(), 410, 658, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getAddrService(), 220, 639, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getPactB(), 270, 596, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getAddrB(), 230, 576, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getPactC(), 220, 534, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getPhoneC(), 440, 534, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getCardTypeC(), 180, 512, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getCardNumC(), 410, 512, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getAddrC(), 230, 492, 0);
		if (model.getServiceNo() == null || "".equals(model.getServiceNo())) {// 服务序号为空
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "/", 480, 229, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getServiceNo(), 480, 229, 0);
		}
		if (model.getRemarkZdg() == null || "".equals(model.getRemarkZdg())) {// 钟点工工作时间备注
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "/", 323, 191, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getRemarkZdg(), 323, 191, 0);
		}
		if (model.getAddrService() == null || "".equals(model.getAddrService())) {// 服务地址
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "/", 200, 130, 0);
		} else {
			cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getAddrService(), 200, 130, 0);
		}
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getF_y(), 200, 109, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getF_m(), 246, 109, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getF_d(), 278, 109, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getT_y(), 330, 109, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getT_m(), 374, 109, 0);
		cb.showTextAligned(PdfContentByte.ALIGN_LEFT, model.getT_d(), 410, 109, 0);
		cb.endText();
		// 5.关闭文档
		document.close();
	}

}
