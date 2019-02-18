package com.emotte.order.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import com.alibaba.fastjson.JSON;


/**
 * 基于POI的javaee导出Excel工具类
 * 
 * @author luocc
 * @see POI
 */
public class ExportExcelUtil {

	/**
	 * @param response
	 *            请求
	 * @param fileName
	 *            文件名 如："订单给出表"
	 * @param excelHeader
	 *            excel表头数组，存放"姓名#name"格式字符串，"姓名"为excel标题行， "name"为对象字段名
	 * @param dataList
	 *            数据集合，需与表头数组中的字段名一致，并且符合javabean规范
	 * @return 返回一个HSSFWorkbook
	 * @throws Exception
	 */
	public static <T> HSSFWorkbook export(HttpServletResponse response, String fileName, String[] excelHeader,
			List<T> dataList) throws Exception {
		// 设置请求
		//response.setContentType("application/vnd.ms-excel");
		
		
		response.setContentType("application/x-download;charset=UTF-8");
		//response.setContentType("application/octet-stream");
		
		response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis()+".zip");
		// 创建一个Workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 设置标题样式
		HSSFCellStyle titleStyle = wb.createCellStyle();
		// 设置单元格边框样式
		titleStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框 细边线
		titleStyle.setBorderBottom(CellStyle.BORDER_THIN);// 下边框 细边线
		titleStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框 细边线
		titleStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框 细边线
		// 设置单元格对齐方式
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
		titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
		// 设置字体样式
		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 15); // 字体高度
		titleFont.setFontName("黑体"); // 字体样式
		titleStyle.setFont(titleFont);
		// 在Workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(fileName);
		// 标题数组
		String[] titleArray = new String[excelHeader.length];
		// 字段名数组
		String[] fieldArray = new String[excelHeader.length];
		for (int i = 0; i < excelHeader.length; i++) {
			String[] tempArray = excelHeader[i].split("#");// 临时数组 分割#
			titleArray[i] = tempArray[0];
			fieldArray[i] = tempArray[1];
		}
		// 在sheet中添加标题行
		HSSFRow row = sheet.createRow(0);// 行数从0开始
		HSSFCell sequenceCell = row.createCell(0);// cell列 从0开始 第一列添加序号
		sequenceCell.setCellValue("序号");
		sequenceCell.setCellStyle(titleStyle);
		sheet.autoSizeColumn(0);// 自动设置宽度
		// 为标题行赋值
		for (int i = 0; i < titleArray.length; i++) {
			HSSFCell titleCell = row.createCell(i + 1);// 0号位被序号占用，所以需+1
			titleCell.setCellValue(titleArray[i]);
			titleCell.setCellStyle(titleStyle);
			sheet.autoSizeColumn(i + 1);// 0号位被序号占用，所以需+1
		}
		// 数据样式 因为标题和数据样式不同 需要分开设置 不然会覆盖
		HSSFCellStyle dataStyle = wb.createCellStyle();
		// 设置数据边框
		dataStyle.setBorderBottom(CellStyle.BORDER_THIN);
		dataStyle.setBorderTop(CellStyle.BORDER_THIN);
		dataStyle.setBorderLeft(CellStyle.BORDER_THIN);
		dataStyle.setBorderRight(CellStyle.BORDER_THIN);
		// 设置居中样式
		dataStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
		dataStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
		// 设置数据字体
		Font dataFont = wb.createFont();
		dataFont.setFontHeightInPoints((short) 12); // 字体高度
		dataFont.setFontName("宋体"); // 字体
		dataStyle.setFont(dataFont);
		// 遍历集合数据，产生数据行
		Iterator<T> it = dataList.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;// 0号位被占用 所以+1
			row = sheet.createRow(index);
			// 为序号赋值
			HSSFCell sequenceCellValue = row.createCell(0);// 序号值永远是第0列
			sequenceCellValue.setCellValue(index);
			sequenceCellValue.setCellStyle(dataStyle);
			sheet.autoSizeColumn(0);
			T t = it.next();
			// 利用反射，根据传过来的字段名数组，动态调用对应的getXxx()方法得到属性值
			for (int i = 0; i < fieldArray.length; i++) {
				HSSFCell dataCell = row.createCell(i + 1);
				dataCell.setCellStyle(dataStyle);
				sheet.autoSizeColumn(i + 1);
				String fieldName = fieldArray[i];
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);// 取得对应getXxx()方法
				Class<? extends Object> tCls = t.getClass();// 泛型为Object以及所有Object的子类
				Method getMethod = tCls.getMethod(getMethodName, new Class[] {});// 通过方法名得到对应的方法
				Object value = getMethod.invoke(t, new Object[] {});// 动态调用方,得到属性值
				if (value != null) {
					dataCell.setCellValue(value.toString());// 为当前列赋值
				}
			}
		}
		OutputStream outputStream = response.getOutputStream();// 打开流
		
		ZipOutputStream zos = new ZipOutputStream(outputStream); 
		//BufferedOutputStream bos = new BufferedOutputStream(zos) ;
		zos.putNextEntry(new ZipEntry ( "1.xls") ) ;
		zos.setComment( "by liangruihua test!" ) ;
		
		wb.write(zos);
		    
	    zos.close ( ) ;
		
		//wb.write(outputStream);// HSSFWorkbook写入流
		// wb.close();// HSSFWorkbook关闭
		outputStream.flush();// 刷新流
		outputStream.close();// 关闭流
		return wb;
	}

	
	
	public static <T> HSSFWorkbook export(HttpServletResponse response, String fileName, String[] excelHeader,
			List<T> dataList,String[] faExcelHeader,List<T> faDataList) throws Exception {
		// 设置请求
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename=" + System.currentTimeMillis() + ".xls");
		// 创建一个Workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 设置标题样式
		HSSFCellStyle titleStyle = wb.createCellStyle();
		// 设置单元格边框样式
		titleStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框 细边线
		titleStyle.setBorderBottom(CellStyle.BORDER_THIN);// 下边框 细边线
		titleStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框 细边线
		titleStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框 细边线
		// 设置单元格对齐方式
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
		titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
		// 设置字体样式
		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 15); // 字体高度
		titleFont.setFontName("黑体"); // 字体样式
		titleStyle.setFont(titleFont);
		// 在Workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("服务订单");
		// 标题数组
		String[] titleArray = new String[excelHeader.length];
		// 字段名数组
		String[] fieldArray = new String[excelHeader.length];
		for (int i = 0; i < excelHeader.length; i++) {
			String[] tempArray = excelHeader[i].split("#");// 临时数组 分割#
			titleArray[i] = tempArray[0];
			fieldArray[i] = tempArray[1];
		}
		// 在sheet中添加标题行
		HSSFRow row = sheet.createRow(0);// 行数从0开始
		HSSFCell sequenceCell = row.createCell(0);// cell列 从0开始 第一列添加序号
		sequenceCell.setCellValue("序号");
		sequenceCell.setCellStyle(titleStyle);
		sheet.autoSizeColumn(0);// 自动设置宽度
		// 为标题行赋值
		for (int i = 0; i < titleArray.length; i++) {
			HSSFCell titleCell = row.createCell(i + 1);// 0号位被序号占用，所以需+1
			titleCell.setCellValue(titleArray[i]);
			titleCell.setCellStyle(titleStyle);
			sheet.autoSizeColumn(i + 1);// 0号位被序号占用，所以需+1
		}
		// 数据样式 因为标题和数据样式不同 需要分开设置 不然会覆盖
		HSSFCellStyle dataStyle = wb.createCellStyle();
		// 设置数据边框
		dataStyle.setBorderBottom(CellStyle.BORDER_THIN);
		dataStyle.setBorderTop(CellStyle.BORDER_THIN);
		dataStyle.setBorderLeft(CellStyle.BORDER_THIN);
		dataStyle.setBorderRight(CellStyle.BORDER_THIN);
		// 设置居中样式
		dataStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
		dataStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
		// 设置数据字体
		Font dataFont = wb.createFont();
		dataFont.setFontHeightInPoints((short) 12); // 字体高度
		dataFont.setFontName("宋体"); // 字体
		dataStyle.setFont(dataFont);
		// 遍历集合数据，产生数据行
		Iterator<T> it = dataList.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;// 0号位被占用 所以+1
			row = sheet.createRow(index);
			// 为序号赋值
			HSSFCell sequenceCellValue = row.createCell(0);// 序号值永远是第0列
			sequenceCellValue.setCellValue(index);
			sequenceCellValue.setCellStyle(dataStyle);
			sheet.autoSizeColumn(0);
			T t = it.next();
			// 利用反射，根据传过来的字段名数组，动态调用对应的getXxx()方法得到属性值
			for (int i = 0; i < fieldArray.length; i++) {
				HSSFCell dataCell = row.createCell(i + 1);
				dataCell.setCellStyle(dataStyle);
				sheet.autoSizeColumn(i + 1);
				String fieldName = fieldArray[i];
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);// 取得对应getXxx()方法
				Class<? extends Object> tCls = t.getClass();// 泛型为Object以及所有Object的子类
				Method getMethod = tCls.getMethod(getMethodName, new Class[] {});// 通过方法名得到对应的方法
				Object value = getMethod.invoke(t, new Object[] {});// 动态调用方,得到属性值
				if (value != null) {
					dataCell.setCellValue(value.toString());// 为当前列赋值
				}
			}
		}
		HSSFSheet faSheet = wb.createSheet("商品订单");
		// 标题数组
		String[] faTitleArray = new String[faExcelHeader.length];
		// 字段名数组
		String[] faFieldArray = new String[faExcelHeader.length];
		for (int i = 0; i < faExcelHeader.length; i++) {
			String[] tempArray = faExcelHeader[i].split("#");// 临时数组 分割#
			faTitleArray[i] = tempArray[0];
			faFieldArray[i] = tempArray[1];
		}
		// 在sheet中添加标题行
		HSSFRow faRow = faSheet.createRow(0);// 行数从0开始
		HSSFCell faSequenceCell = faRow.createCell(0);// cell列 从0开始 第一列添加序号
		faSequenceCell.setCellValue("序号");
		faSequenceCell.setCellStyle(titleStyle);
		faSheet.autoSizeColumn(0);// 自动设置宽度
		// 为标题行赋值
		for (int i = 0; i < faTitleArray.length; i++) {
			HSSFCell titleCell = faRow.createCell(i + 1);// 0号位被序号占用，所以需+1
			titleCell.setCellValue(faTitleArray[i]);
			titleCell.setCellStyle(titleStyle);
			faSheet.autoSizeColumn(i + 1);// 0号位被序号占用，所以需+1
		}
		it = faDataList.iterator();
		index = 0;
		while (it.hasNext()) {
			index++;// 0号位被占用 所以+1
			faRow = faSheet.createRow(index);
			// 为序号赋值
			HSSFCell sequenceCellValue = faRow.createCell(0);// 序号值永远是第0列
			sequenceCellValue.setCellValue(index);
			sequenceCellValue.setCellStyle(dataStyle);
			faSheet.autoSizeColumn(0);
			T t = it.next();
			// 利用反射，根据传过来的字段名数组，动态调用对应的getXxx()方法得到属性值
			for (int i = 0; i < faFieldArray.length; i++) {
				HSSFCell dataCell = faRow.createCell(i + 1);
				dataCell.setCellStyle(dataStyle);
				faSheet.autoSizeColumn(i + 1);
				String fieldName = faFieldArray[i];
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);// 取得对应getXxx()方法
				Class<? extends Object> tCls = t.getClass();// 泛型为Object以及所有Object的子类
				Method getMethod = tCls.getMethod(getMethodName, new Class[] {});// 通过方法名得到对应的方法
				Object value = getMethod.invoke(t, new Object[] {});// 动态调用方,得到属性值
				if (value != null) {
					dataCell.setCellValue(value.toString());// 为当前列赋值
				}
			}
		}
		OutputStream outputStream = response.getOutputStream();// 打开流
		wb.write(outputStream);// HSSFWorkbook写入流
		// wb.close();// HSSFWorkbook关闭
		outputStream.flush();// 刷新流
		outputStream.close();// 关闭流
		return wb;
	}
	
	public static String exportList(HttpServletResponse response, String fileName, String[] excelHeader,
			List<Map<String, String>> dataList,String[] faExcelHeader,List<Map<String, String>> faDataList,HttpServletRequest request) throws Exception {
		// 设置请求
		// 创建一个Workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 设置标题样式
		HSSFCellStyle titleStyle = wb.createCellStyle();
		// 设置单元格边框样式
		titleStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框 细边线
		titleStyle.setBorderBottom(CellStyle.BORDER_THIN);// 下边框 细边线
		titleStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框 细边线
		titleStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框 细边线
		// 设置单元格对齐方式
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
		titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
		// 设置字体样式
		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 15); // 字体高度
		titleFont.setFontName("黑体"); // 字体样式
		titleStyle.setFont(titleFont);
		// 在Workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("服务订单");
		// 在sheet中添加标题行
		HSSFRow row = sheet.createRow(0);// 行数从0开始
		HSSFCell sequenceCell = row.createCell(0);// cell列 从0开始 第一列添加序号
		sequenceCell.setCellValue("序号");
		sequenceCell.setCellStyle(titleStyle);
		sheet.autoSizeColumn(0);// 自动设置宽度
		// 标题数组
		String[] titleArray = new String[excelHeader.length];
		// 字段名数组
		String[] fieldArray = new String[excelHeader.length];
		for (int i = 0; i < excelHeader.length; i++) {
			String[] tempArray = excelHeader[i].split("#");// 临时数组 分割#
			titleArray[i] = tempArray[0];
			fieldArray[i] = tempArray[1];
		}
		// 为标题行赋值
		for (int i = 0; i < titleArray.length; i++) {
			HSSFCell titleCell = row.createCell(i + 1);// 0号位被序号占用，所以需+1
			titleCell.setCellValue(titleArray[i]);
			titleCell.setCellStyle(titleStyle);
			sheet.autoSizeColumn(i + 1);// 0号位被序号占用，所以需+1
		}
		// 数据样式 因为标题和数据样式不同 需要分开设置 不然会覆盖
		HSSFCellStyle dataStyle = wb.createCellStyle();
		// 设置数据边框
		dataStyle.setBorderBottom(CellStyle.BORDER_THIN);
		dataStyle.setBorderTop(CellStyle.BORDER_THIN);
		dataStyle.setBorderLeft(CellStyle.BORDER_THIN);
		dataStyle.setBorderRight(CellStyle.BORDER_THIN);
		// 设置居中样式
		dataStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
		dataStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
		// 设置数据字体
		Font dataFont = wb.createFont();
		dataFont.setFontHeightInPoints((short) 12); // 字体高度
		dataFont.setFontName("宋体"); // 字体
		dataStyle.setFont(dataFont);
		// 遍历集合数据，产生数据行
		int index = 0;
		if(!dataList.isEmpty()){
			for (Map<String,String> list : dataList) {
				index++;
				row = sheet.createRow(index);
				HSSFCell sequenceCellValue = row.createCell(0);// 序号值永远是第0列
				sequenceCellValue.setCellValue(index);
				sequenceCellValue.setCellStyle(dataStyle);
				sheet.autoSizeColumn(0);
				for (int i = 0;i < fieldArray.length; i++) {
					HSSFCell dataCell = row.createCell(i + 1);
					dataCell.setCellStyle(dataStyle);
					sheet.autoSizeColumn(i + 1);
					String value = list.get(fieldArray[i]);
					if (value != null) {
						dataCell.setCellValue(value.toString());// 为当前列赋值
					}
				}
				
			}
		}
		HSSFSheet faSheet = wb.createSheet("商品订单");
		// 在sheet中添加标题行
		HSSFRow faRow = faSheet.createRow(0);// 行数从0开始
		HSSFCell faSequenceCell = faRow.createCell(0);// cell列 从0开始 第一列添加序号
		faSequenceCell.setCellValue("序号");
		faSequenceCell.setCellStyle(titleStyle);
		faSheet.autoSizeColumn(0);// 自动设置宽度
		// 标题数组
		String[] faTitleArray = new String[faExcelHeader.length];
		// 字段名数组
		String[] faFieldArray = new String[faExcelHeader.length];
		for (int i = 0; i < faExcelHeader.length; i++) {
			String[] tempArray = faExcelHeader[i].split("#");// 临时数组 分割#
			faTitleArray[i] = tempArray[0];
			faFieldArray[i] = tempArray[1];
		}
				// 为标题行赋值
		for (int i = 0; i < faTitleArray.length; i++) {
			HSSFCell titleCell = faRow.createCell(i + 1);// 0号位被序号占用，所以需+1
			titleCell.setCellValue(faTitleArray[i]);
			titleCell.setCellStyle(titleStyle);
			sheet.autoSizeColumn(i + 1);// 0号位被序号占用，所以需+1
		}
		index = 0;
		if(!faDataList.isEmpty()){
			for (Map<String,String> list : faDataList) {
				index++;
				faRow = faSheet.createRow(index);
				HSSFCell sequenceCellValue = faRow.createCell(0);// 序号值永远是第0列
				sequenceCellValue.setCellValue(index);
				sequenceCellValue.setCellStyle(dataStyle);
				faSheet.autoSizeColumn(0);
				for (int i = 0;i < faFieldArray.length; i++) {
					HSSFCell dataCell = faRow.createCell(i + 1);
					dataCell.setCellStyle(dataStyle);
					faSheet.autoSizeColumn(i + 1);
					String value = list.get(faFieldArray[i]);
					if (value != null) {
						dataCell.setCellValue(value.toString());// 为当前列赋值
					}
				}
				
			}
		}
		String path = request.getServletContext().getRealPath("/upload/");
		File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
		}
		Date time = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		StringBuffer flName = new StringBuffer(fileName) ;
		flName.insert(fileName.lastIndexOf("."), sdf.format(time));
		flName.replace(flName.lastIndexOf(".")+1,flName.length(),"xls");
		path = path +"/";
		OutputStream out = new FileOutputStream(path+flName.toString());
		wb.write(out);// HSSFWorkbook写入流
		out.flush();// 刷新流
		out.close();// 关闭流
		String result = ExcelUpload.imgser(path+flName.toString(), "other");
		HashMap jsonMap = JSON.parseObject(result, HashMap.class);
		String reUrl = jsonMap.get("reUrl").toString();
		new File(path, flName.toString()).delete();
		return reUrl;
	}



	public static String exportList(HttpServletResponse response, String fileName, String[] excelHeader,
			List<Map<String, String>> dataList, HttpServletRequest request) throws Exception{
		// 设置请求
		// 创建一个Workbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 设置标题样式
		HSSFCellStyle titleStyle = wb.createCellStyle();
		// 设置单元格边框样式
		titleStyle.setBorderTop(CellStyle.BORDER_THIN);// 上边框 细边线
		titleStyle.setBorderBottom(CellStyle.BORDER_THIN);// 下边框 细边线
		titleStyle.setBorderLeft(CellStyle.BORDER_THIN);// 左边框 细边线
		titleStyle.setBorderRight(CellStyle.BORDER_THIN);// 右边框 细边线
		// 设置单元格对齐方式
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
		titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
		// 设置字体样式
		Font titleFont = wb.createFont();
		titleFont.setFontHeightInPoints((short) 15); // 字体高度
		titleFont.setFontName("黑体"); // 字体样式
		titleStyle.setFont(titleFont);
		// 在Workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("三方订单(给出)");
		// 在sheet中添加标题行
		HSSFRow row = sheet.createRow(0);// 行数从0开始
		HSSFCell sequenceCell = row.createCell(0);// cell列 从0开始 第一列添加序号
		sequenceCell.setCellValue("序号");
		sequenceCell.setCellStyle(titleStyle);
		sheet.autoSizeColumn(0);// 自动设置宽度
		// 标题数组
		String[] titleArray = new String[excelHeader.length];
		// 字段名数组
		String[] fieldArray = new String[excelHeader.length];
		for (int i = 0; i < excelHeader.length; i++) {
			String[] tempArray = excelHeader[i].split("#");// 临时数组 分割#
			titleArray[i] = tempArray[0];
			fieldArray[i] = tempArray[1];
		}
		// 为标题行赋值
		for (int i = 0; i < titleArray.length; i++) {
			HSSFCell titleCell = row.createCell(i + 1);// 0号位被序号占用，所以需+1
			titleCell.setCellValue(titleArray[i]);
			titleCell.setCellStyle(titleStyle);
			sheet.autoSizeColumn(i + 1);// 0号位被序号占用，所以需+1
		}
		// 数据样式 因为标题和数据样式不同 需要分开设置 不然会覆盖
		HSSFCellStyle dataStyle = wb.createCellStyle();
		// 设置数据边框
		dataStyle.setBorderBottom(CellStyle.BORDER_THIN);
		dataStyle.setBorderTop(CellStyle.BORDER_THIN);
		dataStyle.setBorderLeft(CellStyle.BORDER_THIN);
		dataStyle.setBorderRight(CellStyle.BORDER_THIN);
		// 设置居中样式
		dataStyle.setAlignment(CellStyle.ALIGN_CENTER); // 水平居中
		dataStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER); // 垂直居中
		// 设置数据字体
		Font dataFont = wb.createFont();
		dataFont.setFontHeightInPoints((short) 12); // 字体高度
		dataFont.setFontName("宋体"); // 字体
		dataStyle.setFont(dataFont);
		// 遍历集合数据，产生数据行
		int index = 0;
		if(dataList != null) {
			if(!dataList.isEmpty()){
				for (Map<String,String> list : dataList) {
					index++;
					row = sheet.createRow(index);
					HSSFCell sequenceCellValue = row.createCell(0);// 序号值永远是第0列
					sequenceCellValue.setCellValue(index);
					sequenceCellValue.setCellStyle(dataStyle);
					sheet.autoSizeColumn(0);
					for (int i = 0;i < fieldArray.length; i++) {
						HSSFCell dataCell = row.createCell(i + 1);
						dataCell.setCellStyle(dataStyle);
						sheet.autoSizeColumn(i + 1);
						String value = list.get(fieldArray[i]);
						if (value != null) {
							dataCell.setCellValue(value.toString());// 为当前列赋值
						}
					}
					
				}
			}
		}
		
		String path = request.getServletContext().getRealPath("/upload/");
		File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
		}
		Date time = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM ddHHmmss");
		StringBuffer flName = new StringBuffer(fileName) ;
		flName.insert(fileName.lastIndexOf("."), sdf.format(time));
		flName.replace(flName.lastIndexOf(".")+1,flName.length(),"xls");
		path = path +"/";
		OutputStream out = new FileOutputStream(path+flName.toString());
		wb.write(out);// HSSFWorkbook写入流
		out.flush();// 刷新流
		out.close();// 关闭流
		String result = ExcelUpload.imgser(path+flName.toString(), "other");
		HashMap jsonMap = JSON.parseObject(result, HashMap.class);
		String reUrl = jsonMap.get("reUrl").toString();
		new File(path, flName.toString()).delete();
		return reUrl;
	
	}
}