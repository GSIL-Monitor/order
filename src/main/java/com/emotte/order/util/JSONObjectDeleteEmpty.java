package com.emotte.order.util;

import java.util.Iterator;

import com.google.gson.Gson;

import net.sf.json.JSONObject;

public class JSONObjectDeleteEmpty {
	private static final Gson gson = new Gson();
	
	public static String deleteEmpty(String json){
		JSONObject jobj=JSONObject.fromObject(json);
		Iterator it = jobj.keys();
		StringBuffer result = new StringBuffer("{");
		while(it.hasNext()){
			String key = (String) it.next();  
			String value = jobj.getString(key);
			if(value!=null && !value.equals("")){
				if(!result.toString().equals("{")) result.append(",");
				result.append("\"" +key +"\":\"" +value +"\"");
			}
		}
		result.append("}");
		return result.toString();
	}
	public static String toJson(Object object) {
		return gson.toJson(object);
	}
}
