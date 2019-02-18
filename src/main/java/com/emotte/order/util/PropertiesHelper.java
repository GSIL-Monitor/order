package com.emotte.order.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class PropertiesHelper {
	/**
	 * 获得property文件中[name]对应的值
	 * @param path 配置文件名
	 * @param name 属性名
	 * @return properties文件中[name]对应的值
	 * 2014年10月31日
	 */
	public static String getValue (String path, String name) {
		String value = readContent(path, name);
		Pattern p = Pattern.compile("\\$\\{([\\w_]+)\\}");
		Matcher m = p.matcher(value);
		while(m.find()) {
			value = value.replace(m.group(), readContent(path, m.group(1)));
		}
		return value;
	}
	
	/**
	 * 获得property文件中[name]对应的值
	 * @param path 配置文件名
	 * @param name 属性名
	 * @param defaultValue 默认值
	 * @return
	 * 2016年5月6日
	 */
	public static String getValue (String path, String name, String defaultValue) {
		String value = readContent(path, name);
		if (null == value) return defaultValue;
		Pattern p = Pattern.compile("\\$\\{([\\w_]+)\\}");
		Matcher m = p.matcher(value);
		while(m.find()) {
			value = value.replace(m.group(), readContent(path, m.group(1)));
		}
		return value;
	}
	
    /**
     * 读取动态数据
     * @param filePath
     * @param key
     * @return
     * 2015年9月10日
     */
    private static String readContent(String filePath, String key) {
    	if (key == null) return null;
    	Properties props = new Properties();
		String value = null;
		URL url = PropertiesHelper.class.getClassLoader().getResource(filePath);
		if (null != url) {
			try {
				props.load(getInputStream(filePath));
				value = props.getProperty(new String(key.getBytes("UTF-8"), "ISO-8859-1"));
				if (value != null) {
					value =new String(value.getBytes("ISO-8859-1"), "UTF-8"); // 处理中文乱码
				}
			} catch (FileNotFoundException e) {
				LogHelper.error(PropertiesHelper.class, e.getMessage(), e);
			} catch (IOException e) {
				LogHelper.error(PropertiesHelper.class, e.getMessage(), e);
			}
		}
		return value;
    }
    
    public static Map<String, Object> getMap(String filePath) {
    	Properties props = new Properties();
		URL url = PropertiesHelper.class.getClassLoader().getResource(filePath);
		Map<String, Object> map = new HashMap<String, Object>();
		if (null != url) {
			try {
				props.load(getInputStream(filePath));
				Set<Entry<Object, Object>> set = props.entrySet();
				for (Entry<Object, Object> entry : set) {
					String key = (String) entry.getKey();
					String value = (String) entry.getValue();
					if(StringUtils.isBlank(key)) continue; // key为null则不存储
					key = new String(key.trim().getBytes("ISO-8859-1"), "UTF-8"); // 处理中文乱码
					if(!StringUtils.isBlank(value)) value = new String(value.trim().getBytes("ISO-8859-1"), "UTF-8"); // 处理中文乱码
					map.put(key, value); 
				}
			} catch (FileNotFoundException e) {
				LogHelper.error(PropertiesHelper.class, e.getMessage(), e);
			} catch (IOException e) {
				LogHelper.error(PropertiesHelper.class, e.getMessage(), e);
			}
		}
		return map;
    }
    
	private static InputStream getInputStream(String fileName) {
		return PropertiesHelper.class.getClassLoader().getResourceAsStream(fileName);
	}
    
}
