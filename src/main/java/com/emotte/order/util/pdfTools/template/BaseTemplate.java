package com.emotte.order.util.pdfTools.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

/**
 * 模板基类
 * 
 */
public abstract class BaseTemplate {
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 要写入的文件
	 */
	protected File pdf;
	
	// 一些用于不同内容的字体
	protected Font headfont;
	protected Font keyfont;
	protected Font textfont;
	protected Font footfont;
	protected BaseFont bfChinese;
	
//	protected String USER_PASS = "UsEr_26986";
//	protected String OWNER_PASS = "OwnER_27175";
	
	protected String title = "模板标题";
	protected String createTime = "";
	protected String authorName = "";

	public BaseTemplate(File pdf) {
		this.pdf = pdf;
		try {
			bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H",
					BaseFont.NOT_EMBEDDED); //中文字体
			headfont = new Font(bfChinese, 12, Font.BOLD);
			keyfont = new Font(bfChinese, 8, Font.BOLD);
			textfont = new Font(bfChinese, 8, Font.NORMAL);
			footfont = new Font(bfChinese, 6, Font.NORMAL);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 向模板中写入内容
	 * @throws FileNotFoundException
	 * @throws DocumentException
	 */
	public abstract void WritePdf() throws FileNotFoundException, DocumentException;
}
