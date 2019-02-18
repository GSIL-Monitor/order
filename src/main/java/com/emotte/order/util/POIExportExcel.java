package com.emotte.order.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

public class POIExportExcel {

	/**
	 * @author Luocc
	 * @param title
	 * @param rowName
	 * @param fileName
	 * @param dataList
	 * @param response
	 * @throws IOException
	 */
	public void exportExcel(String title, String[] rowName, String fileName, 
			List<Object[]> dataList,HttpServletResponse response) throws IOException {
		
		fileName = fileName + ".xls";
		HSSFWorkbook workbook = new HSSFWorkbook(); // 创建工作簿对象
		HSSFSheet sheet = workbook.createSheet(title); // 创建工作表

		// 产生表格标题行
		HSSFRow rowm = sheet.createRow(0);
		HSSFCell cellTiltle = rowm.createCell(0);

		HSSFCellStyle columnTopStyle = this.getColumnTopStyle(workbook);// 获取列头样式对象
		HSSFCellStyle style = this.getStyle(workbook); // 单元格样式对象

		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, (rowName.length - 1)));
		cellTiltle.setCellStyle(columnTopStyle);
		cellTiltle.setCellValue(title);

		// 定义所需列数
		int columnNum = rowName.length;
		HSSFRow rowRowName = sheet.createRow(2); // 在索引2的位置创建行(最顶端的行开始的第二行)

		// 将列头设置到sheet的单元格中
		for (int n = 0; n < columnNum; n++) {
			HSSFCell cellRowName = rowRowName.createCell(n); // 创建列头对应个数的单元格
			cellRowName.setCellType(Cell.CELL_TYPE_STRING); // 设置列头单元格的数据类型
			HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
			cellRowName.setCellValue(text); // 设置列头单元格的值
			cellRowName.setCellStyle(columnTopStyle); // 设置列头单元格样式
		}

		// 将查询出的数据设置到sheet对应的单元格中
		for (int i = 0; i < dataList.size(); i++) {
			Object[] obj = dataList.get(i);// 遍历每个对象
			HSSFRow row = sheet.createRow(i + 3);// 创建所需的行数
			for (int j = 0; j < obj.length; j++) {
				HSSFCell cell = null; // 设置单元格的数据类型
				if (j == 0) {
					cell = row.createCell(j, Cell.CELL_TYPE_NUMERIC);
					cell.setCellValue(i + 1);
				} else {
					cell = row.createCell(j, Cell.CELL_TYPE_STRING);
					if (!"".equals(obj[j]) && obj[j] != null) {
						cell.setCellValue(obj[j].toString()); // 设置单元格的值
					} else {
						cell.setCellValue(""); // 设置单元格的值
					}
				}
				cell.setCellStyle(style); // 设置单元格样式
			}
		}
		// 让列宽随着导出的列长自动适应
		for (int colNum = 0; colNum < columnNum; colNum++) {
			int columnWidth = sheet.getColumnWidth(colNum) / 256;
			for (int rowNum = 0; rowNum < sheet.getLastRowNum() + 1; rowNum++) {
				HSSFRow currentRow;
				// 当前行未被使用过
				if (sheet.getRow(rowNum) == null) {
					currentRow = sheet.createRow(rowNum);
				} else {
					currentRow = sheet.getRow(rowNum);
				}
				if (currentRow.getCell(colNum) != null) {
					HSSFCell currentCell = currentRow.getCell(colNum);
					if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
						int length = currentCell.getStringCellValue().getBytes().length;
						if (columnWidth < length) {
							columnWidth = length;
						}
					}
				}
			}
			if (colNum == 0) {
				sheet.setColumnWidth(colNum, (columnWidth - 2) * 256);
			} else {
				sheet.setColumnWidth(colNum, (columnWidth + 4) * 256);
			}
		}

		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(), "iso-8859-1"));

//		OutputStream output = response.getOutputStream();
//		workbook.write(out);
		FileOutputStream out = new FileOutputStream("D:/"+ fileName);
		workbook.write(out);
		out.close();
	}

	/*
	 * 列头单元格样式
	 */
	public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {

		// 设置字体
		HSSFFont font = workbook.createFont();
		// 设置字体大小
		font.setFontHeightInPoints((short) 11);
		// 字体加粗
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		// 设置字体名字
		font.setFontName("Courier New");
		// 设置样式;
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(CellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(CellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(CellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(CellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(CellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		return style;

	}

	/*
	 * 列数据信息单元格样式
	 */
	public HSSFCellStyle getStyle(HSSFWorkbook workbook) {
		// 设置字体
		HSSFFont font = workbook.createFont();
		// 设置字体大小
		// font.setFontHeightInPoints((short)10);
		// 字体加粗
		// font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 设置字体名字
		font.setFontName("Courier New");
		// 设置样式;
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置底边框;
		style.setBorderBottom(CellStyle.BORDER_THIN);
		// 设置底边框颜色;
		style.setBottomBorderColor(HSSFColor.BLACK.index);
		// 设置左边框;
		style.setBorderLeft(CellStyle.BORDER_THIN);
		// 设置左边框颜色;
		style.setLeftBorderColor(HSSFColor.BLACK.index);
		// 设置右边框;
		style.setBorderRight(CellStyle.BORDER_THIN);
		// 设置右边框颜色;
		style.setRightBorderColor(HSSFColor.BLACK.index);
		// 设置顶边框;
		style.setBorderTop(CellStyle.BORDER_THIN);
		// 设置顶边框颜色;
		style.setTopBorderColor(HSSFColor.BLACK.index);
		// 在样式用应用设置的字体;
		style.setFont(font);
		// 设置自动换行;
		style.setWrapText(false);
		// 设置水平对齐的样式为居中对齐;
		style.setAlignment(CellStyle.ALIGN_CENTER);
		// 设置垂直对齐的样式为居中对齐;
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

		return style;

	}
}