package com.emotte.order.util;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * @ClassName: JsonUtil
 * @Description: json处理类
 * @author Liucx
 * @date 2014-4-24 下午4:43:04
 * 
 * @tag v1.0
 */
public class JsonUtil {

	private static final Gson gson = new Gson();
	private JsonUtil() {

	}

	public static <T> T fromJson(String jsonStr, Class<T> c)
			throws JsonSyntaxException {
		return gson.fromJson(jsonStr, c);
	}

	public static <T> T fromJsonList(String jsonStr) throws JsonSyntaxException {
		return gson.fromJson(jsonStr, new TypeToken<T>() {
		}.getType());
	}

	public static String toJson(Object object) {
		return gson.toJson(object);
	}

	public static Map<String, Object> mapFromJson(String jsondata) {
		Type type = new TypeToken<Map<String, Object>>() {
		}.getType();
		Gson gson = new Gson();
		Map<String, Object> map = gson.fromJson(jsondata, type);
		return map;
	}

	public static <T> Map<String, T> mapTFromJson(String jsondata) {
		Type type = new TypeToken<Map<String, T>>() {
		}.getType();
		Gson gson = new Gson();
		Map<String, T> map = gson.fromJson(jsondata, type);
		return map;
	}

	public static <T> List<T> listFromJson(String jsondata) {
		Type type = new TypeToken<List<T>>() {
		}.getType();
		Gson gson = new Gson();
		List<T> map = gson.fromJson(jsondata, type);
		return map;
	}
	
	public static Object toBean(String className, String json) {
		JSONObject obj = JSONObject.fromObject(json);

		Class c = null;
		try {
			c = Class.forName(className);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Object toBean = null;
		try {
			toBean = c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		toBean = JSONObject.toBean(obj, toBean.getClass());
		return toBean;
	}

	public static String toJson(Object object, String timeType) {
		if (null == timeType || "".equals(timeType.trim())) {
			timeType = "yyyy-MM-dd HH:mm:ss";
		}
		return new GsonBuilder().setDateFormat(timeType).create()
				.toJson(object);
	}

	/***
	 * json串转list
	 * 
	 * @param jsonString
	 * @param classT
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public static <T> List<T> jsonToList(String jsonString, Class<T> classT)
			throws JsonParseException, JsonMappingException, IOException {

		JSONArray ja = JSONArray.fromObject(jsonString);
		List<T> list = null;
		list = new ArrayList<T>(ja.size());
		ObjectMapper objectMapper = null;
		for (int i = 0; i < ja.size(); ++i) {
			objectMapper = new ObjectMapper();
			T t = objectMapper.readValue(ja.getString(i), classT);
			list.add(t);
		}

		return list;

	}

	public static void main(String[] args) {
	}
}
