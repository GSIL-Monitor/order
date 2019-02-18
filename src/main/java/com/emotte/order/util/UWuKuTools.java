package com.emotte.order.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.wildhorse.server.core.utils.encryptUtils.MD5;

public class UWuKuTools {
	
	public static String  client_id="w29244";  //商家client_id,平台提供
	public static String appscret="1df301128b42d4992ced511248fba350"; //商家密钥，平台提供
	public static String v ="3.0"; //版本号
	public static String platform = "sina"; //请求平台数据，目前仅支持sina
	public static String sign_type="md5"; //签名加密方式,目前仅支持md5加密方式，默认md5
	public static String format  = "json";  //数据返回格式,目前仅支持json格式
	//public String timestamp=DateUtil.dateToTimeStamp(new Date());
	public static String host = "http://bestweshop.dianking.cn/egou/index.php/api/uwuku/";  //请求主机地址请勿修改
	
	/*
	 * 对参数进行排序
	 * return 排序后的key+value字符串
	 */
	public static String sortKeyVal(HashMap<String,String> map){	
		Set<String> set1 = map.keySet();
		List listKeyVal = new ArrayList(set1);
		Collections.sort(listKeyVal);
		String preSignStr = "";
		StringBuffer bfSim = new StringBuffer("");
		for (Object obj : listKeyVal) {
			System.out.println(obj+"="+map.get(obj));
			bfSim.append(obj+map.get(obj));
		}
		//拼接商家 uid 和 商家密钥 appscret
		preSignStr = client_id +"appscret"+appscret+bfSim.toString();	
		return preSignStr;
	}
	
	
	public static String createSignStr(String preSignstr){
		return MD5.MD5Encode(preSignstr).toUpperCase();
	}
	
	public static HashMap<String,String> getSystemParam(HashMap<String,String> map){
		map.put("uid", client_id);
		map.put("platform", platform);
		map.put("sign_type", sign_type);
		map.put("format", format);
		map.put("v", v);
		map.put("timestamp",DateUtil.dateToTimeStamp(new Date()));
		return map;
	}
		
}
