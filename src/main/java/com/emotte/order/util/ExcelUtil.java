package com.emotte.order.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public Map<String, List<Map<String, String>>> excelToList(String[] parm, File file) {
		try {
			Map<String, List<Map<String, String>>> resultMap = null;
			InputStream input = new FileInputStream(file);
			String fileName = file.getName();
			// 获取文件扩展名
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if ("xls".equalsIgnoreCase(fileExt)) {
				resultMap = readExcel2003(parm, input);
			} else if ("xlsx".equalsIgnoreCase(fileExt)) {
				resultMap = readExcel2007(parm, input);
			}
			return resultMap;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	public Map<String, List<Map<String, String>>> excelToList(String[] parm, InputStream input, String fileName) {
		try {
			Map<String, List<Map<String, String>>> resultMap = null;
			// 获取文件扩展名
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if ("xls".equalsIgnoreCase(fileExt)) {
				resultMap = readExcel2003(parm, input);
			} else if ("xlsx".equalsIgnoreCase(fileExt)) {
				resultMap = readExcel2007(parm, input);
			}
			return resultMap;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public Map<String, List<Map<String, String>>> excelToList(String[] parm,String[] faParm, InputStream input, String fileName) {
		try {
			Map<String, List<Map<String, String>>> resultMap = null;
			// 获取文件扩展名
			String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
			if ("xls".equalsIgnoreCase(fileExt)) {
				resultMap = readExcel2003(parm,faParm, input);
			} else if ("xlsx".equalsIgnoreCase(fileExt)) {
				resultMap = readExcel2007(parm,faParm, input);
			}
			return resultMap;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	// 读取2003版excel
	private Map<String, List<Map<String, String>>> readExcel2003(String[] parm, InputStream input) throws IOException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String,List<Map<String, String>>> resultMap = new HashMap<String,List<Map<String, String>>>();
		HSSFWorkbook wb = new HSSFWorkbook(input);
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = null;
		int rowNum = sheet.getLastRowNum();
		for (int i = 1; i <= rowNum; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("row", String.valueOf(i + 1));
			row = sheet.getRow(i);
			if (getCellFormatValue(row.getCell(0)) == null || getCellFormatValue(row.getCell(0)).equals("")) {
				continue;
			} else {
				for (int j = 0; j < parm.length; j++) {
					map.put(parm[j], getCellFormatValue(row.getCell(j + 1)));
				}
			}
			list.add(map);
		}
		resultMap.put("list",list );
		return resultMap;
	}
	
	
	private Map<String,List<Map<String, String>>> readExcel2003(String[] parm,String[] faParm, InputStream input) throws IOException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		List<Map<String, String>> faList = new ArrayList<Map<String, String>>();
		Map<String,List<Map<String, String>>> resultMap = new HashMap<String,List<Map<String, String>>>();
		HSSFWorkbook wb = new HSSFWorkbook(input);
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFSheet faSheet = wb.getSheetAt(1);
		HSSFRow row = null;
		int rowNum = sheet.getLastRowNum();
		int faRowNum = faSheet.getLastRowNum();
		for (int i = 1; i <= rowNum; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("row", String.valueOf(i + 1));
			row = sheet.getRow(i);
			if (getCellFormatValue(row.getCell(0)) == null || getCellFormatValue(row.getCell(0)).equals("")) {
				continue;
			} else {
				for (int j = 0; j < parm.length; j++) {
					map.put(parm[j], getCellFormatValue(row.getCell(j + 1)));
				}
			}
			list.add(map);
		}
		for (int i = 1; i <= faRowNum; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("row", String.valueOf(i + 1));
			row = faSheet.getRow(i);
			if (getCellFormatValue(row.getCell(0)) == null || getCellFormatValue(row.getCell(0)).equals("")) {
				continue;
			} else {
				for (int j = 0; j < faParm.length; j++) {
					map.put(faParm[j], getCellFormatValue(row.getCell(j + 1)));
				}
			}
			faList.add(map);
		}
		resultMap.put("faList",faList );
		resultMap.put("list",list );
		return resultMap;
	}

	// 读取2007版excel
	private Map<String, List<Map<String, String>>> readExcel2007(String[] parm, InputStream input) throws IOException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String,List<Map<String, String>>> resultMap = new HashMap<String,List<Map<String, String>>>();
		XSSFWorkbook wb = new XSSFWorkbook(input);
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFRow row = null;
		int rowNum = sheet.getLastRowNum();
		for (int i = 1; i <= rowNum; i++) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("row", String.valueOf(i + 1));
			row = sheet.getRow(i);
			String cell = getCellFormatValue(row.getCell(0));
			if (cell == null || cell.equals("")) {
				continue;
			} else {
				for (int j = 0; j < parm.length; j++) {
					map.put(parm[j], getCellFormatValue(row.getCell(j + 1)));
				}
			}
			list.add(map);
		}
		resultMap.put("list",list );
		return resultMap;
	}

	// 读取2007版excel
		private Map<String,List<Map<String, String>>> readExcel2007(String[] parm,String[] faParm, InputStream input) throws IOException {
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			List<Map<String, String>> faList = new ArrayList<Map<String, String>>();
			Map<String,List<Map<String, String>>>  resultMap = new HashMap<String,List<Map<String, String>>>(); 
			XSSFWorkbook wb = new XSSFWorkbook(input);
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFSheet faSheet = wb.getSheetAt(1);
			XSSFRow row = null;
			int rowNum = sheet.getLastRowNum();
			int faRowNum  =faSheet.getLastRowNum();
			for (int i = 1; i <= rowNum; i++) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("row", String.valueOf(i + 1));
				row = sheet.getRow(i);
				String cell = getCellFormatValue(row.getCell(0));
				if (cell == null || cell.equals("")) {
					continue;
				} else {
					for (int j = 0; j < parm.length; j++) {
						map.put(parm[j], getCellFormatValue(row.getCell(j + 1)));
					}
				}
				list.add(map);
			}
			for (int i = 1; i <= faRowNum; i++) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("row", String.valueOf(i + 1));
				row = faSheet.getRow(i);
				String cell = getCellFormatValue(row.getCell(0));
				if (cell == null || cell.equals("")) {
					continue;
				} else {
					for (int j = 0; j < faParm.length; j++) {
						map.put(faParm[j], getCellFormatValue(row.getCell(j + 1)));
					}
				}
				faList.add(map);
			}
			resultMap.put("faList",faList );
			resultMap.put("list",list );
			return resultMap;
		}
	
	
	// 2003版获取单元格中的值
	private String getCellFormatValue(HSSFCell cell) {
		if (cell == null) {
			return "";
		}
		DecimalFormat b = new DecimalFormat("#");
		String strCell = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(b.format(cell.getNumericCellValue()));
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		return strCell.trim();
	}

	// 2007版获取单元格中的值
	private String getCellFormatValue(XSSFCell cell) {
		if (cell == null) {
			return "";
		}
		DecimalFormat b = new DecimalFormat("#");
		String strCell = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(b.format(cell.getNumericCellValue()));
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		return strCell.trim();
	}

	private Boolean match(String regex, String str) { // 正则验证格式
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

}