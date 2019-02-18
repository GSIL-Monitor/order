package com.emotte.kernel.file.rw.impl.excel;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.emotte.kernel.file.model.Position;
import com.emotte.kernel.file.rw.IFileRWService;

/**
 * Excel文件读取
 * @author ChengJing
 * 2016年7月14日
 */
public class ExcelServiceImpl implements IFileRWService<List<Map<String, String>>, List<Map<String, Object>>> {

	private CellStyle cellStyle;
	
	@Override
	public List<Map<String, String>> readContent(String filePath) {
		Workbook hsb = null;
		Sheet sheet = null;
		List<Map<String, String>> list = null;
		Map<String, String> map = null;
		// 将Excel中的数据读取到listL中
		hsb = BaseSSService.getWorkBook(filePath);
		sheet = hsb.getSheetAt(0);
		list = new ArrayList<Map<String, String>>();
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 0; i <= lastRowNum; i++) {
			map = new HashMap<String, String>();
			Row row = sheet.getRow(i);
			int lastColNum = row.getLastCellNum();
			for (int j = 0; j < lastColNum; j++) {
				Position position = new Position(j, i); // 对于Excel来说，行表示的是纵坐标，列表示的是横坐标 
				String content = BaseSSService.getCellValue(sheet, position);
				if (null == content || "".equals(content.trim())) content = "";
				map.put(j+"", content.trim());
			}
			list.add(map);
		}
		return list;
	}
	
	@Override
	public List<Map<String, String>> readContent(String filePath, Map<String, String> mapper) {
		Workbook hsb = null;
		Sheet sheet = null;
		List<Map<String, String>> list = null;
		Map<String, String> map = null;
		// 将Excel中的数据读取到listL中
		hsb = BaseSSService.getWorkBook(filePath);
		sheet = hsb.getSheetAt(0); // 获得第一个sheet
		list = new ArrayList<Map<String, String>>();
		int lastRowNum = sheet.getLastRowNum();
		// 获得标题
		Map<Integer, String> titles = new HashMap<Integer, String>();
		Row row = sheet.getRow(0);
		int lastColNum = row.getLastCellNum();
		for (int i = 0; i < lastColNum; i++) {
			Position position = new Position(i, 0); // 对于Excel来说，行表示的是纵坐标，列表示的是横坐标 
			String content = BaseSSService.getCellValue(sheet, position);
			if (null == content || "".equals(content.trim())) content = "";
			titles.put(i, content.trim());
		}
		for (int j = 1; j <= lastRowNum; j++) {
			map = new HashMap<String, String>();
			row = sheet.getRow(j); // 获得行
			lastColNum = row.getLastCellNum();
			for (int i = 0; i < lastColNum; i++) {
				Position position = new Position(i, j); // 对于Excel来说，行表示的是纵坐标，列表示的是横坐标 
				String content = BaseSSService.getCellValue(sheet, position);
				if (null == content || "".equals(content.trim())) content = "";
				String mapperKey = mapper.get(titles.get(i));
				if(!StringUtils.isBlank(mapperKey)) {
					map.put(mapperKey, content.trim()); // 存储映射标题
				} else {
					map.put(titles.get(i), content.trim()); // 没有找到映射标题，则存储文件的标题
				}
			}
			list.add(map);
		}
		return list;
	}
	
	@Override
	public List<Map<String, String>> readContent(InputStream stream, String fileName) {
		Workbook hsb = null;
		Sheet sheet = null;
		List<Map<String, String>> list = null;
		Map<String, String> map = null;
		// 将Excel中的数据读取到listL中
		hsb = BaseSSService.getWorkBook(stream, fileName);
		sheet = hsb.getSheetAt(0);
		list = new ArrayList<Map<String, String>>();
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 0; i <= lastRowNum; i++) {
			map = new HashMap<String, String>();
			Row row = sheet.getRow(i);
			int lastColNum = row.getLastCellNum();
			for (int j = 0; j < lastColNum; j++) {
				Position position = new Position(j, i); // 对于Excel来说，行表示的是纵坐标，列表示的是横坐标 
				String content = BaseSSService.getCellValue(sheet, position);
				if (null == content || "".equals(content.trim())) content = "";
				map.put(j+"", content.trim());
			}
			list.add(map);
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Map> readContent2(InputStream stream, String fileName) {
		Workbook hsb = null;
		Sheet sheet = null;
		List<Map> list = null;
		Map<String, String> map = null;
		// 将Excel中的数据读取到listL中
		hsb = BaseSSService.getWorkBook(stream, fileName);
		sheet = hsb.getSheetAt(0);
		list = new ArrayList<Map>();
		int lastRowNum = sheet.getLastRowNum();
		for (int i = 0; i <= lastRowNum; i++) {
			map = new HashMap<String, String>();
			Row row = sheet.getRow(i);
			int lastColNum = row.getLastCellNum();
			for (int j = 0; j < lastColNum; j++) {
				Position position = new Position(j, i); // 对于Excel来说，行表示的是纵坐标，列表示的是横坐标 
				String content = BaseSSService.getCellValue(sheet, position);
				if (null == content || "".equals(content.trim())) content = "";
				map.put(j+"", content.trim());
			}
			list.add(map);
		}
		return list;
	}
	@Override
	public List<Map<String, String>> readContent(InputStream stream, String fileName, Map<String, String> mapper) {
		Workbook hsb = null;
		Sheet sheet = null;
		List<Map<String, String>> list = null;
		Map<String, String> map = null;
		// 将Excel中的数据读取到listL中
		hsb = BaseSSService.getWorkBook(stream, fileName);
		sheet = hsb.getSheetAt(0); // 获得第一个sheet
		list = new ArrayList<Map<String, String>>();
		int lastRowNum = sheet.getLastRowNum();
		// 获得标题
		Map<Integer, String> titles = new HashMap<Integer, String>();
		Row row = sheet.getRow(0);
		int lastColNum = row.getLastCellNum();
		for (int i = 0; i < lastColNum; i++) {
			Position position = new Position(i, 0); // 对于Excel来说，行表示的是纵坐标，列表示的是横坐标 
			String content = BaseSSService.getCellValue(sheet, position);
			if (null == content || "".equals(content.trim())) content = "";
			titles.put(i, content.trim());
		}
		for (int j = 1; j <= lastRowNum; j++) {
			map = new HashMap<String, String>();
			row = sheet.getRow(j); // 获得行
			lastColNum = row.getLastCellNum();
			for (int i = 0; i < lastColNum; i++) {
				Position position = new Position(i, j); // 对于Excel来说，行表示的是纵坐标，列表示的是横坐标 
				String content = BaseSSService.getCellValue(sheet, position);
				if (null == content || "".equals(content.trim())) content = "";
				String mapperKey = null;
				if (null != mapper) mapperKey = mapper.get(titles.get(i));
				if(!StringUtils.isBlank(mapperKey)) {
					map.put(mapperKey, content.trim()); // 存储映射标题
				} else {
					map.put(titles.get(i), content.trim()); // 没有找到映射标题，则存储文件的标题
				}
			}
			list.add(map);
		}
		return list;
	}
	@Override
	public List<Map<String, String>> readContent(InputStream stream, String fileName, Integer titleRow, Integer recordStartRow, String endFlag, Map<String, String> mapper) {
		Workbook hsb = null;
		Sheet sheet = null;
		List<Map<String, String>> list = null;
		Map<String, String> map = null;
		// 将Excel中的数据读取到listL中
		hsb = BaseSSService.getWorkBook(stream, fileName);
		sheet = hsb.getSheetAt(0); // 获得第一个sheet
		list = new ArrayList<Map<String, String>>();
		int lastRowNum = sheet.getLastRowNum();
		
		int titleRow_ = titleRow == null?0:titleRow; // 标题行
		int startRow = titleRow_ + 1; // 记录开始行
		if (recordStartRow != null) startRow = recordStartRow;
		// 获得标题
		Map<Integer, String> titles = new HashMap<Integer, String>(); // 存储标题
//		readTitle(sheet, titleRow_, 0, startRow, "", titles); // 读取标题
		Row row = sheet.getRow(titleRow_); // 读取标题所在行
		int lastColNum = row.getLastCellNum();
		for (int i = 0; i < lastColNum; i++) {
			Position position = new Position(i, titleRow_); // 对于Excel来说，行表示的是纵坐标，列表示的是横坐标
			String content = BaseSSService.getCellValue(sheet, position);
			if (null == content || "".equals(content.trim())) content = "";
			titles.put(i, content.trim());
		}
		boolean isEnd = false;
		for (int j = startRow; j <= lastRowNum; j++) {
			map = new HashMap<String, String>();
			row = sheet.getRow(j); // 获得行
			lastColNum = row.getLastCellNum();
			boolean isColEmpty = true;
			for (int i = 0; i < lastColNum; i++) {
				Position position = new Position(i, j); // 对于Excel来说，行表示的是纵坐标，列表示的是横坐标 
				String content = BaseSSService.getCellValue(sheet, position);
				if (StringUtils.isBlank(content)) {
					content = "";
				} else {
					isColEmpty = false; // 该行只要出现有数据的单元格则表示记录没有结束
				}
				if (null != endFlag && i == 0 && content.startsWith(endFlag)) { // 在第一列中遇到结束标记，则跳出循环, 结束行标识放在第一列中
					isEnd = true; // 设置读取结束
					break; // 跳出循环
				}
				String mapperKey = null;
				if (null != mapper) mapperKey = mapper.get(titles.get(i));
				if(!StringUtils.isBlank(mapperKey)) {
					map.put(mapperKey, content.trim()); // 存储映射标题
				} else {
					map.put(titles.get(i), content.trim()); // 没有找到映射标题，则存储文件的标题
				}
			}
			if (isEnd || isColEmpty) break; // 如果为true表示读取结束，则跳出循环
			list.add(map);
		}
		return list;
	}
	
	private void readTitle (Sheet sheet, int row, int col, int recordStartRow, String parentContent, Map<Integer, String> titles) {
		/*
		 * 先纵向一行一行读到底层，存储标题（父标题+子标题）
		 * 再横向一列一列读取兄弟标题，如此向上迭代
		 */
		if (recordStartRow == row) return; // 迭代到记录开始行，则结束迭代
		Position position = new Position(col, row); // 对于Excel来说，行表示的是纵坐标，列表示的是横坐标
		String content = BaseSSService.getCellValue(sheet, position);
		if (StringUtils.isBlank(content)) return; // 内容为空表示,没有子标题了，返回
		content = content.trim();
		readTitle(sheet, row+1, col, recordStartRow, content, titles);
		titles.put(col, parentContent+content); // 纵向读到底层，存储标题
		System.out.println("("+col +","+ row+")" + parentContent+content);
//		while (true) { // 横向读取标题
//			position = new Position(col+1, row); // 读取同一行的下一列
//			content = BaseSSService.getCellValue(sheet, position);
//			if (StringUtils.isBlank(content)) break; // 如果为空，表示右侧没有子标题，则跳出循环
			readRight(sheet, row, col+1, recordStartRow, parentContent, titles); // 横向读取标题
//		}
	}
	
	private void readRight(Sheet sheet, int row, int col, int recordStartRow, String parentContent, Map<Integer, String> titles) {
		Position position = new Position(col, row); // 对于Excel来说，行表示的是纵坐标，列表示的是横坐标
		String content = BaseSSService.getCellValue(sheet, position);
		Row r = sheet.getRow(row);
		int lastCol = r.getLastCellNum();
		if (StringUtils.isBlank(content)) {
			if (lastCol > col) {
				readRight(sheet, row, col+1, recordStartRow, parentContent, titles);
			}
			return; // 内容为空表示,没有子标题了，返回
		}
		content = content.trim();
		readTitle(sheet, row, col, recordStartRow, content, titles);
	}

	@Override
	public List<Map<String, String>> readContentByCol(InputStream stream, String fileName, Integer recordStartRow, String endFlag, Map<String, String> mapper) {
		Workbook hsb = null;
		Sheet sheet = null;
		List<Map<String, String>> list = null;
		Map<String, String> map = null;
		// 将Excel中的数据读取到listL中
		hsb = BaseSSService.getWorkBook(stream, fileName);
		sheet = hsb.getSheetAt(0); // 获得第一个sheet
		list = new ArrayList<Map<String, String>>();
		int lastRowNum = sheet.getLastRowNum();
		
		int startRow = 1; // 记录开始行
		if (recordStartRow != null) startRow = recordStartRow;
		boolean isEnd = false;
		for (int j = startRow; j <= lastRowNum; j++) {
			map = new HashMap<String, String>();
			Row row = sheet.getRow(j); // 获得行
			int lastColNum = row.getLastCellNum();
			boolean isColEmpty = true;
			for (int i = 0; i < lastColNum; i++) {
				Position position = new Position(i, j); // 对于Excel来说，行表示的是纵坐标，列表示的是横坐标 
				String content = BaseSSService.getCellValue(sheet, position);
				if (null == content || "".equals(content.trim())) {
					content = "";
				} else {
					isColEmpty = false; // 该行只要出现有数据的单元格则表示记录没有结束
				}
				if (null != endFlag && i == 0 && content.startsWith(endFlag)) { // 在第一列中遇到结束标记，则跳出循环, 结束行标识放在第一列中
					isEnd = true; // 设置读取结束
					break; // 跳出循环
				}
				String mapperKey = null;
				if (null != mapper) mapperKey = mapper.get(i+"");
				if(!StringUtils.isBlank(mapperKey)) {
					map.put(mapperKey, content.trim()); // 存储映射标题
				} else {
					map.put(i+"", content.trim()); // 没有找到映射标题，则存储文件的标题
				}
			}
			if (isEnd || isColEmpty) break; // 如果为true表示读取结束，则跳出循环
			list.add(map);
		}
		return list;
	}
	@Override
	public String readCell (InputStream stream, String fileName, Position position) {
		Workbook hsb = null;
		Sheet sheet = null;
		hsb = BaseSSService.getWorkBook(stream, fileName);
		sheet = hsb.getSheetAt(0); // 获得第一个sheet
		return BaseSSService.getCellValue(sheet, position);
	}
	@Override
	public void write2File(List<Map<String, Object>> list, String filePath) {
		/*
		 * 原理
		 * list的第一列存储的是横坐标和标题的映射关系，剩余行存储的是标题和对应值得映射关系
		 * 标题存储规则：根据横坐标(key)，找到对应的标题(value)，将标题存入excel的(value, 0) 位置
		 * 内容存储规则：遍历list(从1开始，记录值为i),在其中遍历titles(记录值为j)根据横坐标(j)从titles中找到对应的标题，然后根据标题在map（遍历list中除标题栏的内容）找到对应的值，将其存入excel (j,i) 位置
		 */
		Workbook wb = BaseSSService.createWorkbook(filePath);
		Sheet sheet = wb.createSheet();
		// set titles
		Map<String, Object> titles = list.get(0);
		Set<Entry<String, Object>> set = titles.entrySet();
		int length = 0;
		Row row = sheet.createRow(0); // 创建第一条记录（标题）
		for (Entry<String, Object> entry : set) {
			Cell cell = row.createCell(Integer.parseInt(entry.getKey())); // 创建单元格（titles中存储的是单元格横坐标和存储值得映射）
			BaseSSService.setCellValue(cell, entry.getValue(), getCellStyle(wb)); // 在创建的单元格中存储值
			length = getCellLength(entry.getValue());
			sheet.setColumnWidth(Integer.parseInt(entry.getKey()), length*2); //设置单元格宽度，这里乘以2是考虑中文字符
		}
		// 存储内容
		for (int i = 1; i < list.size(); i++) { // 从第二行开始，因为第一行是标题，已经记录了
			Map<String, Object> map = list.get(i);
			row = sheet.createRow(i); // 创建行（标题）
			for (int j = 0; j < titles.size(); j++) {
				Cell cell = row.createCell(j); // 创建单元格
				String title = (String) titles.get(j+"");
				Object value = map.get(title);
				if ("序号".equals(title)) {
					value = i; // 设置序号
				}
				BaseSSService.setCellValue(cell, value, getCellStyle(wb)); // 从titles中的获取第i个位置对应的title值，并在map中找对对应的值
				// 设置单元格宽度
				length = getCellLength(value);
				if(sheet.getColumnWidth(j) < (length)){
				    sheet.setColumnWidth(j, length);
				}
			}
		}
		BaseSSService.write2File(wb, filePath);
	}
	
	public void write2File2(@SuppressWarnings("rawtypes") List<Map> list, String filePath) {
		/*
		 * 原理
		 * list的第一列存储的是横坐标和标题的映射关系，剩余行存储的是标题和对应值得映射关系
		 * 标题存储规则：根据横坐标(key)，找到对应的标题(value)，将标题存入excel的(value, 0) 位置
		 * 内容存储规则：遍历list(从1开始，记录值为i),在其中遍历titles(记录值为j)根据横坐标(j)从titles中找到对应的标题，然后根据标题在map（遍历list中除标题栏的内容）找到对应的值，将其存入excel (j,i) 位置
		 */
		Workbook wb = BaseSSService.createWorkbook(filePath);
		Sheet sheet = wb.createSheet();
		// set titles
		@SuppressWarnings("rawtypes")
		Map titles = list.get(0);
		Set<?> set = titles.entrySet();
		int length = 0;
		Row row = sheet.createRow(0); // 创建第一条记录（标题）
		for (Object obj : set) {
			@SuppressWarnings("unchecked")
			Entry<String, ?> entry = (Entry<String, ?>) obj;
			Cell cell = row.createCell(Integer.parseInt(entry.getKey())); // 创建单元格（titles中存储的是单元格横坐标和存储值得映射）
			BaseSSService.setCellValue(cell, entry.getValue(), getCellStyle(wb)); // 在创建的单元格中存储值
			length = getCellLength(entry.getValue());
			sheet.setColumnWidth(Integer.parseInt(entry.getKey()), length*2); //设置单元格宽度，这里乘以2是考虑中文字符
		}
		// 存储内容
		for (int i = 1; i < list.size(); i++) { // 从第二行开始，因为第一行是标题，已经记录了
			@SuppressWarnings("rawtypes")
			Map map = list.get(i);
			row = sheet.createRow(i); // 创建行（标题）
			for (int j = 0; j < titles.size(); j++) {
				Cell cell = row.createCell(j); // 创建单元格
				String title = (String) titles.get(j+"");
				Object value = map.get(title);
				if ("序号".equals(title)) {
					value = i; // 设置序号
				}
				BaseSSService.setCellValue(cell, value, getCellStyle(wb)); // 从titles中的获取第i个位置对应的title值，并在map中找对对应的值
				// 设置单元格宽度
				length = getCellLength(value);
				if(sheet.getColumnWidth(j) < (length)){
				    sheet.setColumnWidth(j, length);
				}
			}
		}
		BaseSSService.write2File(wb, filePath);
	}
	
	/**
	 * 获得字符长度
	 * @param object
	 * @return
	 * 2016年8月31日
	 */
	private int getCellLength (Object object) {
		int count = 0;
		if (object instanceof String || object instanceof Double || object instanceof BigDecimal || object instanceof Integer || object instanceof Float || object instanceof Long) {
			count = object.toString().getBytes().length;
		}
		return (count > 50 ? 50 : count) * 256; // 为了查看效果excel单元格宽度不能大于100个字符
	}
	
	/**
	 * 设置单元格样式
	 * @param cellStyle
	 * 2016年7月14日
	 */
	public void setCellStyle(CellStyle cellStyle) {
		this.cellStyle = cellStyle;
	}
	/**
	 * 获得单元格样式
	 * @param wb
	 * @return
	 * 2016年7月14日
	 */
	public CellStyle getCellStyle(Workbook wb) {
		if (null != this.cellStyle) {
			return this.cellStyle;
		}
		// 生成一个字体   
		Font font = wb.createFont();
		font.setFontName("宋体"); //设置字体
		font.setFontHeightInPoints((short) 11); //设置字号
		font.setBoldweight(Font.BOLDWEIGHT_NORMAL); //设置字体样式 正常显示
		//定义单元格格式
		CellStyle cellStyle = wb.createCellStyle();
		//设置单元格对齐格式 居中 垂直
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		//单元格自动换行
		cellStyle.setWrapText(true);
		cellStyle.setFont(font);
		this.cellStyle = cellStyle;
		return cellStyle;
	}

	@Override
	public void write2Http(List<Map<String, Object>> arg0, String arg1, HttpServletResponse arg2) throws IOException {
		// TODO Auto-generated method stub
		
	}
	
}
