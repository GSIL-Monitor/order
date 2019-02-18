package com.emotte.order.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

public class IdCardUtil {

	/**
	 * 水瓶座1.20-2.18 
	 * 双鱼座2.19-3.20
	 * 白羊座3.21-4.19
	 * 金牛座4.20-5.20
	 * 双子座5.21-6.21
	 * 巨蟹座6.22-7.22
	 * 狮子座7.23-8.22
	 * 处女座8.23-9.22
	 * 天秤座9.23-10.23
	 * 天蝎座10.24-11.22
	 * 射手座11.23-12.21
	 * 摩羯座12.22-1.19
	 * 根据出生日期计算星座
	 * @param dateStr {String}
	 * @throws ParseException 
	 * @returns {String}
	 */
	public static String getConstellation(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd"); 
		String result = "";
		try {
			Date d = sdf.parse(dateStr);
			int month = d.getMonth()+1;
			int day = d.getDate();
			if(month > 0 && day > 0){
				String str = "摩羯水瓶双鱼白羊金牛双子巨蟹狮子处女天秤天蝎射手摩羯";
				int[] arr = {20,19,21,20,21,22,23,23,23,24,23,22};
				int start = month * 2 - (day < arr[month - 1] ? 2 : 0);
				int end = start+2;
				result = str.substring(start, end)+"座";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据出生日期计算属相
	 * @param dateStr {String}
	 * @returns {String}
	 */
	public static String getZodiac(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd"); 
		String result = "";
		try {
			int start = 1901;
			Date d = sdf.parse(dateStr);
			//getYear()获取年份时是从1900年开始计算的,加上1900
			int birthyear = d.getYear()+1900;
			if(birthyear > 0){
				int x = (start - birthyear) % 12;
				if (x == 1 || x == -11) {result = "鼠";}
				if (x == 0) {result = "牛";}
				if (x == 11 || x == -1) {result = "虎";}
				if (x == 10 || x == -2) {result = "兔";}
				if (x == 9 || x == -3)  {result = "龙";}
				if (x == 8 || x == -4)  {result = "蛇";}
				if (x == 7 || x == -5)  {result = "马";}
				if (x == 6 || x == -6)  {result = "羊";}
				if (x == 5 || x == -7)  {result = "猴";}
				if (x == 4 || x == -8)  {result = "鸡";}
				if (x == 3 || x == -9)  {result = "狗";}
				if (x == 2 || x == -10) {result = "猪";}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据身份证号获取出生日期
	 * @param IDCardNum 
	 * @returns {String}
	 */
	public static String getBirthday(String IDCardNum){
		String result = "";
		IDCardNum = IDCardNum.replaceAll("[ ]+", "");
		if(IDCardNum != null && IDCardNum.length() == 18){
		    	result = IDCardNum.substring(6,14);
		    	result = result.substring(0,4)+"-"+result.substring(4,6)+"-"+result.substring(6,8);
		}
		if(IDCardNum != null && IDCardNum.length() == 15){
	    	result = IDCardNum.substring(6,12);
	    	result = "19"+result.substring(0,2)+"-"+result.substring(2,4)+"-"+result.substring(4,6);
		}
		 return result;
	}


	/**
	 * 根据出生日期计算年龄
	 * 
	 * @param birthdayStr
	 * @returns {Number}
	 */
	public static int getAgeByBirthday(String birthdayStr) {
		SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd"); 
		int age = -1;
		try {
			Date today = new Date();
			int todayYear = today.getYear()+1900;
			int todayMonth = today.getMonth() + 1;
			int todayDay = today.getDate();
			Date birthday = sdf.parse(birthdayStr.replaceAll("[ ]+", ""));
				int birthdayYear = birthday.getYear()+1900;
				int birthdayMonth = birthday.getMonth()+1;
				int birthdayDay = birthday.getDate();
				if (todayYear >= birthdayYear) {
					if (todayMonth < birthdayMonth) {
						age = (todayYear - birthdayYear) - 1;
					}else if(todayMonth > birthdayMonth){
						age = (todayYear - birthdayYear);
					}else{
						if (todayDay < birthdayDay) {
							age = (todayYear - birthdayYear)-1;
						}else {
							age = (todayYear - birthdayYear);
						}
					}
				}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return age;
	}


	//获取生日、年龄、生肖、星座
	public static JSONObject getInfoByCard(String IDCardNum){
		JSONObject result = new JSONObject();
		String birthday = getBirthday(IDCardNum);
		int age = getAgeByBirthday(birthday);
		String zodiac= getZodiac(birthday);
		String constellation = getConstellation(birthday);
		result.accumulate("birthday", birthday);
		result.accumulate("age",age==-1?"":age);
		result.accumulate("zodiac",zodiac);
		result.accumulate("constellation", constellation);
		return result;
	}
	
	//验证身份证号
	public static boolean checkIdCard(String IDCardNum){
		String reg18 =  "((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65|71|81|82|91)\\d{4})((((19|20)(([02468][048])|([13579][26]))0229))|((20[0-9][0-9])|(19[0-9][0-9]))((((0[1-9])|(1[0-2]))((0[1-9])|(1\\d)|(2[0-8])))|((((0[1,3-9])|(1[0-2]))(29|30))|(((0[13578])|(1[02]))31))))((\\d{3}(x|X))|(\\d{4}))";
	    Pattern pattern18 = Pattern.compile(reg18);
	    Matcher matcher18 = pattern18.matcher(IDCardNum);
	    boolean r18 = matcher18.matches();
		if (r18) {
			return true;
		}
		return false;
	}
}
