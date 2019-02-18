package com.emotte.order.util;

import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import net.sf.json.JSONObject;


/**
 * 百度地图经纬度相关工具
 * @author Administrator
 *
 */
public class LngAndLatUtil {
	static double DEF_PI = 3.14159265359; // PI
	static double DEF_R = 6370996.81; // 6370693.5 // radius of earth
	
	// 通过地址解析出百度地图对应的经纬度
	public static Map<String,Double> getLngAndLat(String address){
		Map<String,Double> map=new HashMap<String, Double>();
		String url = "http://api.map.baidu.com/geocoder/v2/?address="+address+"&output=json&ak=tIqExiZw1IyBNAshjMWI1PT1DtIhOsuc";
	       String json = loadJSON(url);
	       JSONObject obj = JSONObject.fromObject(json);
	       if(obj.get("status").toString().equals("0")){
	       	double lng=obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
	       	double lat=obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
	       	map.put("longitude", lng);
	       	map.put("latitude", lat);
	       	//System.out.println("经度："+longitude+"---纬度："+latitude);
	       }else{
	    	   // 设置为北京天安门经纬度：（116.404,39.915)
	    	   map.put("longitude", 116.404);
		       map.put("latitude", 39.915);
	       }
		return map;
	}
	// 地图解析配套方法，
	public static String loadJSON (String url) {
       StringBuilder json = new StringBuilder();
       try {
           URL oracle = new URL(url);
           URLConnection yc = oracle.openConnection();
           
           BufferedReader in = new BufferedReader(new InputStreamReader(
                                       yc.getInputStream(),"utf-8"));
           String inputLine = null;
           while ( (inputLine = in.readLine()) != null) {
               json.append(inputLine);
           }
           in.close();
       } catch (MalformedURLException e) {
       } catch (IOException e) {
       }
       return json.toString();
   }
	
	// 适用于远距离 ,百度地图通用的是远距离查询的方法
	public static double getLongDistance(double lon1, double lat1, double lon2, double lat2) {
		double ew1, ns1, ew2, ns2;
		double distance;
		// 角度转换为弧度
		ew1 = lon1 * (DEF_PI / 180);
		ns1 = lat1 * (DEF_PI / 180);
		ew2 = lon2 * (DEF_PI / 180);
		ns2 = lat2 * (DEF_PI / 180);

		// 求大圆劣弧与球心所夹的角(弧度)
		distance = Math.sin(ns1) * Math.sin(ns2) + Math.cos(ns1) * Math.cos(ns2) * Math.cos(ew1 - ew2);
		// 调整到[-1..1]范围内，避免溢出
		if (distance > 1.0)
			distance = 1.0;
		else if (distance < -1.0)
			distance = -1.0;

		// 求大圆劣弧长度
		distance = DEF_R * Math.acos(distance);
		return distance;

	}
		
	// 适用于近距离，相当于平面距离
	public static double getShortDistance(double lon1, double lat1, double lon2, double lat2) {
		double ew1, ns1, ew2, ns2;
		double dx, dy, dew;
		double distance;
		// 角度转换为弧度
		ew1 = lon1 * (DEF_PI / 180);
		ns1 = lat1 * (DEF_PI / 180);
		ew2 = lon2 * (DEF_PI / 180);
		ns2 = lat2 * (DEF_PI / 180);

		// 经度差
		dew = ew1 - ew2;
		// 若跨东经和西经180 度，进行调整
		if (dew > DEF_PI)
			dew = 2 * DEF_PI - dew;
		else if (dew < -DEF_PI)
			dew = 2 * DEF_PI + dew;
		dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
		dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
		// 勾股定理求斜边长
		distance = Math.sqrt(dx * dx + dy * dy);
		return distance;

	}

	
}