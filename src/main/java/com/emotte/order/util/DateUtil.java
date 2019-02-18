package com.emotte.order.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 对java.util.Date的一些简便操作
 * @author ZhangYang
 * @time 2016年1月22日22:12:19
 * @version 1.0
 */
public class DateUtil {
	private static Calendar c = Calendar.getInstance();
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 获取某个日期几天后的日期
	 * @param d 日期实例，如果为null则默认为当前系统时间
	 * @param pram 要加几天（可以是负数，表示减）
	 * @return 结果Date实例
	 */
	public static Date addDay(Date d, int pram){
		if(d==null)
			d = new Date();
		c.setTime(d);
		c.add(Calendar.DAY_OF_YEAR, pram);
		return c.getTime();
	}
	
	/**
	 * 获取某个日期几周后的日期
	 * @param d 日期实例，如果为null则默认为当前系统时间
	 * @param pram 要加几周（可以是负数，表示减）
	 * @return 结果Date实例
	 */
	public static Date addWeek(Date d, int pram){
		if(d==null)
			d = new Date();
		c.setTime(d);
		c.add(Calendar.WEEK_OF_YEAR, pram);
		return c.getTime();
	}
	
	/**
	 * 获取某个日期几月后的日期
	 * @param d 日期实例，如果为null则默认为当前系统时间
	 * @param pram 要加几月（可以是负数，表示减）
	 * @return 结果Date实例
	 */
	public static Date addMonth(Date d, int pram){
		if(d==null)
			d = new Date();
		c.setTime(d);
		c.add(Calendar.MONTH, pram);
		return c.getTime();
	}
	
	/**
	 * 返回日期  yyyy-MM-dd HH:mm:ss 格式的字符串
	 * @param d 日期实例，如果为null则默认为当前系统时间
	 * @return  结果字符串
	 */
	public static String getY_mdhmd(Date d){
		if(d==null)
			d = new Date();
		return sdf.format(d);
	}
	
	/**
	 * 返回日期  yyyy-MM-dd 格式的字符串
	 * @param d 日期实例，如果为null则默认为当前系统时间
	 * @return  结果字符串
	 */
	public static String getYmd(Date d){
		if(d==null)
			d = new Date();
		return ft.format(d);
	}
	
	/**
	 * 返回日期指定格式的字符串
	 * @param d 日期实例，如果为null则默认为当前系统时间
	 * @param format 指定的格式，必须为合法格式
	 * @return  结果字符串
	 */
	public static String format(Date d, String format){
		if(d==null)
			d = new Date();
		SimpleDateFormat sdft = new SimpleDateFormat(format);
		return sdft.format(d);
	}
	
	
	public static Date str2Date(String date){
		if(notEmpty(date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				sdf = new SimpleDateFormat("yyyy-MM-dd");			
				try {
					return sdf.parse(date);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			return new Date();
		}else{
			return null;
		}
	}
	
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	
	
	public static Date stringDayToDate(String s) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		return sdf.parse(s);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.format(date);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static String dateToTimeStamp(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            return sdf.format(date);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    
    public static String dateToStringDay(Date date) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	try {
    		return sdf.format(date);
    	}
    	catch (Exception e) {
    		return null;
    	}
    }
    /**
     * 实现给定某日期，判断是星期几  
     * 操作人：周鑫
     * 2018年12月17日上午11:21:57
     */
	public static String getWeekday(String date){//必须yyyy-MM-dd
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");  
        SimpleDateFormat sdw = new SimpleDateFormat("E");  
        Date d = null;  
        try {  
            d = sd.parse(date);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }
        String format = sdw.format(d);
        if (format.equals("星期日")) {
			format="星期天";
		}
        return format;
	 }
}
