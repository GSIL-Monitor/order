package com.emotte.order.util;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class ConfigComm extends Properties{
	private static Log logs = LogFactory.getLog(ConfigComm.class);
	private static HashMap<String,ConfigComm> configMap = new HashMap<String, ConfigComm>();
	private static InputStream inputStream;
	
	/**
	 * 构造方法获得指定文件的输入流
	 * @param configName  指定配置文件
	 */
	private ConfigComm(String configName){
		try {
			String configPath = this.getClass().getClassLoader().getResource(configName).getPath() ;
			logs.info("配置文件=" + configPath);
			//从类加载路径取得指定文件的输入流
			inputStream = ConfigComm.class.getClassLoader().getResourceAsStream(configName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 获得载入文件的实例化对象
	 * @param configName 配置文件名称
	 * @return  ConfigComm
	 */
	public synchronized static ConfigComm getInstance(String configName){
		ConfigComm instance = null;
		if(configMap.get(configName)==null){
			instance = new ConfigComm(configName);
			//载入文件
			try {
				instance.load(inputStream);
				configMap.put(configName, instance);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			instance = configMap.get(configName);
		}
		return instance;
	}
}
