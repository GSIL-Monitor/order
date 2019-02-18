package com.emotte.order.order.file.rw.impl.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.emotte.order.order.file.model.Position;


/**
 * TODO the base excel service
 * @author ChengJing
 * 2014年7月10日
 */
public class BaseSSService {
	/**
	 * 获得工作薄
	 * @param excelPath
	 * @return HSSFWorkbook
	 * 2014年7月10日
	 */
	public static Workbook getWorkBook(String filePath) {
		int index = filePath.lastIndexOf(".");
		String suffix = filePath.substring(index+1);
		Workbook wb = null;
		try {
			if ("xls".equals(suffix)) {
				wb = new HSSFWorkbook(new FileInputStream(filePath));
			} else if ("xlsx".equals(suffix)) {
				wb = new XSSFWorkbook(new FileInputStream(filePath));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return wb;
	}
	
	/**
	 * 根据File创建工作簿
	 * @param file
	 * @return
	 * 2016年7月13日
	 */
	public static Workbook getWorkBook(File file) {
		String filePath = file.getPath();
		int index = filePath.lastIndexOf(".");
		String suffix = filePath.substring(index+1);
		Workbook wb = null;
		try {
			if ("xls".equals(suffix)) {
				wb = new HSSFWorkbook(new FileInputStream(file));
			} else if ("xlsx".equals(suffix)) {
				wb = new XSSFWorkbook(new FileInputStream(file));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return wb;
	}
	
	/**
	 * 根据输入流和文件类型创建工作簿
	 * @param is
	 * @param fileName
	 * @return
	 * 2016年7月13日
	 */
	public static Workbook getWorkBook(InputStream is, String fileName) {
		int index = fileName.lastIndexOf(".");
		String suffix = fileName.substring(index+1);
		Workbook wb = null;
		try {
			if ("xls".equals(suffix)) {
				wb = new HSSFWorkbook(is);
			} else if ("xlsx".equals(suffix)) {
				wb = new XSSFWorkbook(is);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return wb;
	}
	
	
	
	/**
	 * 创建工作薄
	 * @param filePath
	 * @return
	 * @author ChengJing
	 * @date 2014年8月21日
	 */
	public static Workbook createWorkbook (String filePath) {
		int index = filePath.lastIndexOf(".");
		String suffix = filePath.substring(index+1);
		Workbook wb = null;
		if ("xls".equals(suffix)) {
			wb = new HSSFWorkbook();
		}
		if ("xlsx".equals(suffix)) {
			wb = new XSSFWorkbook();
		}
		return wb;
	}
	
	/**
	 * 创建Excel
	 * @param hssfWb
	 * @param toExcelPath
	 * @author ChengJing
	 * @date 2014年7月21日
	 */
	public static void write2File(Workbook hssfWb, String excelPath) {
		FileOutputStream fos = null;
		try {
			File file = new File(excelPath);
			File parentFile = file.getParentFile();
			if(!parentFile.exists()){
				parentFile.mkdirs();
			}
			fos = new FileOutputStream(file);
			hssfWb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != fos) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * get sheet by sheetName
	 * @param hsb
	 * @param sheetName
	 * @return HSSFSheet
	 * @author ChengJing
	 * @date 2014年7月10日
	 */
	public static Sheet getSheet(Workbook hsb, String sheetName) {
		Sheet sheet = null;
		sheet = hsb.getSheet(sheetName);
		return sheet;
	}
	
	/**
	 * get sheet by index of sheet
	 * @param hsb
	 * @param sheetIndex
	 * @return HSSFSheet
	 * @author ChengJing
	 * @date 2014年7月10日
	 */
	public static Sheet getSheet(Workbook hsb, int sheetIndex) {
		Sheet sheet = null;
		sheet = hsb.getSheetAt(sheetIndex);
		return sheet;
	}
	
	/**
	 * 获得单元格内容
	 * @param sheet
	 * @param position
	 * @return
	 * 2016年7月14日
	 */
	public static String getCellValue(Sheet sheet, Position position) {
		String content = null;
		Row row = sheet.getRow(position.getOrdinate()); // 行对应的是纵坐标
		Cell cell = row.getCell(position.getAbscissa()); // 列对应的是横坐标
		if(null != cell) {
			int cellType = cell.getCellType();
			switch(cellType) {
			case Cell.CELL_TYPE_BOOLEAN: content = cell.getBooleanCellValue()?"是":"否"; break;
			case Cell.CELL_TYPE_NUMERIC:{
				if (DateUtil.isCellDateFormatted(cell)) { // 判断是否为日期类型
					Date date = cell.getDateCellValue();
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss");
					content = format.format(date);
				} else {
//					content = cell.getNumericCellValue() + "";
					DecimalFormat df = new DecimalFormat("#.##");  
				    content = df.format(cell.getNumericCellValue()); 
				}
				break;
			}
			case Cell.CELL_TYPE_STRING: content = cell.getStringCellValue(); break;
			case Cell.CELL_TYPE_ERROR: {
				content = null;
				System.out.println("数据异常：" + position);
				break;
			}
			default: content = cell.getStringCellValue();
			}
		}
		return content;
	}
	
	/**
	 * 单元格赋值
	 * @param cell
	 * @param object
	 * @return
	 * 2016年7月14日
	 */
	public static boolean setCellValue(Cell cell, Object object, CellStyle cellStyle) {
		if(null != cell) {
			int cellType = change2CellType(object);
			cell.setCellType(cellType); // 设置单元格类型
			cell.setCellStyle(cellStyle); // 设置单元格样式
			switch(cellType) {
			case Cell.CELL_TYPE_STRING: {
				cell.setCellValue((String)object); 
				break;
			}
			case Cell.CELL_TYPE_BOOLEAN: {
				cell.setCellValue((Boolean)object); 
				break;
			}
			case Cell.CELL_TYPE_NUMERIC:{ // 处理数值类型和日期类型
				if (object instanceof Date) { // 判断是否为日期类型
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
					cell.setCellValue(sdf.format(object));
				} else {
					cell.setCellValue(object.toString()); //
				}
				break;
			}
			case Cell.CELL_TYPE_BLANK: break; // 空不作处理
			case Cell.CELL_TYPE_ERROR: break; // 错误不做处理
			default: cell.setCellValue(object.toString());
			}
		}
		return Boolean.TRUE;
	}
	
	/**
	 * 将对象转换为CellType
	 * @param obj
	 * @return
	 * 2016年7月14日
	 */
	private static int change2CellType (Object obj) {
		if(null == obj) return Cell.CELL_TYPE_BLANK;
		if (obj instanceof String) {
			return Cell.CELL_TYPE_STRING;
		} else if (obj instanceof Boolean) {
			return Cell.CELL_TYPE_BOOLEAN;
		} else if (obj instanceof Integer || obj instanceof Double || obj instanceof Float || obj instanceof Long || obj instanceof BigDecimal || obj instanceof Date) {
			return Cell.CELL_TYPE_NUMERIC;
		} else {
			return Cell.CELL_TYPE_ERROR;
		}
	}
}
