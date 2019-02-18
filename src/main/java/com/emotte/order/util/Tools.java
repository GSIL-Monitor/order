package com.emotte.order.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
	
	/**
	 * ���������λ����֤�� 
	 * @return
	 */
	public static int getRandomNum(){
		 Random r = new Random();
		 return r.nextInt(900000)+100000;//(Math.random()*(999999-100000)+100000)
	}
	
	/**
	 * ����ַ����Ƿ�Ϊ��(null,"","null")
	 * @param s
	 * @return ��Ϊ���򷵻�true�����򷵻�false
	 */
	public static boolean notEmpty(String s){
		return s!=null && !"".equals(s) && !"null".equals(s);
	}
	public static  String lindHoursNew(String hours){
		String hour = "" ;
		String[] ary = hours.split("-");
		for(int i=1; i<=ary.length; i++){
			if(i==1){
				hour += ary[i-1].replace(":", "") +"00-";
			}else{
				String[] longs=ary[i-1].split(":");
				if("00".equals(longs[1])){
					hour+=Long.toString(Long.parseLong(ary[i-1].replace(":", ""))+30)+"00" ;
				}else{
					hour+=Long.toString(Long.parseLong(ary[i-1].replace(":", ""))+70)+"00" ;//将20:30   转换为21000   加100 减去30
				}
			}
		}
		return hour ;
	}
	/**
	 * ����ַ����Ƿ�Ϊ��(null,"","null")
	 * @param s
	 * @return Ϊ���򷵻�true�������򷵻�false
	 */
	public static boolean isEmpty(String s){
		return s==null || "".equals(s) || "null".equals(s);
	}
	
	/**
	 * �ַ���ת��Ϊ�ַ�������
	 * @param str �ַ���
	 * @param splitRegex �ָ���
	 * @return
	 */
	public static String[] str2StrArray(String str,String splitRegex){
		if(isEmpty(str)){
			return null;
		}
		return str.split(splitRegex);
	}
	
	/**
	 * ��Ĭ�ϵķָ���(,)���ַ���ת��Ϊ�ַ�������
	 * @param str	�ַ���
	 * @return
	 */
	public static String[] str2StrArray(String str){
		return str2StrArray(str,",\\s*");
	}
	
	/**
	 * ����yyyy-MM-dd HH:mm:ss�ĸ�ʽ������ת�ַ���
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * ����yyyy-MM-dd HH:mm:ss�ĸ�ʽ���ַ���ת����
	 * @param date
	 * @return
	 */
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
	
	/**
	 * ���ղ���format�ĸ�ʽ������ת�ַ���
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	
	public static String getTime(java.sql.Timestamp time){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(time);
	}
	
	/**
	 * ��ʱ�����ʱ���֡���ת��Ϊʱ���
	 * @param StrDate
	 */
	public static String getTimes(String StrDate){
		String resultTimes = "";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    java.util.Date now;
	    
	    try {
	    	now = new Date();
	    	java.util.Date date=df.parse(StrDate);
	    	long times = now.getTime()-date.getTime();
	    	long day  =  times/(24*60*60*1000);
	    	long hour = (times/(60*60*1000)-day*24);
	    	long min  = ((times/(60*1000))-day*24*60-hour*60);
	    	long sec  = (times/1000-day*24*60*60-hour*60*60-min*60);
	        
	    	StringBuffer sb = new StringBuffer();
	    	//sb.append("�����ڣ�");
	    	if(hour>0 ){
	    		sb.append(hour+"Сʱǰ");
	    	} else if(min>0){
	    		sb.append(min+"����ǰ");
	    	} else{
	    		sb.append(sec+"��ǰ");
	    	}
	    		
	    	resultTimes = sb.toString();
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    
	    return resultTimes;
	}
	
	/**
	 * дtxt��ĵ�������
	 * @param filePath  �ļ�·��
	 * @param content  д�������
	 */
	public static void writeFile(String fileP,String content){
		String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//��Ŀ·��
		filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
		if(filePath.indexOf(":") != 1){
			filePath = File.separator + filePath;
		}
		try {
	        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");      
	        BufferedWriter writer=new BufferedWriter(write);          
	        writer.write(content);      
	        writer.close(); 

	        
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	  * ��֤����
	  * @param email
	  * @return
	  */
	 public static boolean checkEmail(String email){
	  boolean flag = false;
	  try{
	    String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	    Pattern regex = Pattern.compile(check);
	    Matcher matcher = regex.matcher(email);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	
	 /**
	  * ��֤�ֻ�����
	  * @param mobiles
	  * @return
	  */
	 public static boolean checkMobileNumber(String mobileNumber){
	  boolean flag = false;
	  try{
	    Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
	    Matcher matcher = regex.matcher(mobileNumber);
	    flag = matcher.matches();
	   }catch(Exception e){
	    flag = false;
	   }
	  return flag;
	 }
	 
	/**
	 * ���KEY�Ƿ���ȷ
	 * @param paraname  �������
	 * @param FKEY		���յ� KEY
	 * @return Ϊ���򷵻�true�������򷵻�false
	 */
//	public static boolean checkKey(String paraname, String FKEY){
//		paraname = (null == paraname)? "":paraname;
//		return MD5.md5(paraname+DateUtil.getDays()+",fh,").equals(FKEY);
//	}
	 
	/**
	 * ��ȡtxt��ĵ�������
	 * @param filePath  �ļ�·��
	 */
	public static String readTxtFile(String fileP) {
		try {
			
			String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))+"../../";	//��Ŀ·��
			filePath = filePath.replaceAll("file:/", "");
			filePath = filePath.replaceAll("%20", " ");
			filePath = filePath.trim() + fileP.trim();
			if(filePath.indexOf(":") != 1){
				filePath = File.separator + filePath;
			}
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { 		// �ж��ļ��Ƿ����
				InputStreamReader read = new InputStreamReader(
				new FileInputStream(file), encoding);	// ���ǵ������ʽ
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					return lineTxt;
				}
				read.close();
			}else{
				System.out.println("�Ҳ���ָ�����ļ�,�鿴��·���Ƿ���ȷ:"+filePath);
			}
		} catch (Exception e) {
			System.out.println("��ȡ�ļ����ݳ���");
		}
		return "";
	}
	
    public static String getGeneralTime() {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat(
            "yyyyMMddHHmmss", Locale.ENGLISH);
        Calendar localtime = Calendar.getInstance();
        String s = bartDateFormat.format(localtime.getTime());
        return s;

    }
 // 排期小时转换处理
 	public static  String lindHoursNew1(String hours){
 		String hour = "" ;
 		String[] ary = hours.split("-");
 		for(int i=1; i<=ary.length; i++){
 			if(i==1){
 				hour += ary[i-1].replace(":", "") +"00-";
 			}else{
 				String[] longs=ary[i-1].split(":");
 				hour+=Long.toString(Long.parseLong(ary[i-1].replace(":", "")))+"00" ;
 				/*if("00".equals(longs[1])){
 					hour+=Long.toString(Long.parseLong(ary[i-1].replace(":", "")))+"00" ;
 				}else{
 					hour+=Long.toString(Long.parseLong(ary[i-1].replace(":", ""))+30)+"00" ;//将20:30   转换为21000   加100 减去30
 				}*/
 			}
 		}
 		return hour ;
 	}
	public static String ChangeTime(String hourse){
		StringBuffer hour = new StringBuffer("");
		try {
			String[] times = hourse.split("-");
			SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
			Calendar rightNow = Calendar.getInstance();
			Date end;
			rightNow.setTime(sdf.parse(times[0]));// 开始时间
			end = sdf.parse(times[1]);// 结束时间
			// 循环一次开始时间+30分钟 当开始时间等于结束时间时结束循环
			while (rightNow.getTime().getTime() != end.getTime()
					&& rightNow.getTime().getTime() < end.getTime()) {
				hour.append(sdf.format(rightNow.getTime()) + ",");
				rightNow.add(Calendar.MINUTE, 30);
			}
			if (hour.length() > 0) {
				hour.deleteCharAt(hour.length() - 1);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return hour.toString();
	}
	public static String lindHoursOld(String hours){
		String hour = "" ;
		String[] ary = hours.split(",");
		for(int i=0; i<=ary.length; i++){
			if(i==1){
				hour += ary[i-1].replace(":", "") +"00-";
			}
			if(i==ary.length-1){
				String[] longs=ary[i].split(":");
				if("00".equals(longs[1])){
					hour+=Long.toString(Long.parseLong(ary[i].replace(":", ""))+30)+"00" ;
				}else{
					hour+=Long.toString(Long.parseLong(ary[i].replace(":", ""))+70)+"00" ;//将20:30   转换为21000   加100 减去30
				}
			}
		}
		return hour ;
	}
	
	//8:00,8:30,9:00,9:30       8:00-10:00
		public static String hourTimes(String string){
			String maxString ="";
			for(int i=0;i<=string.split(",").length-1;i++){
				if(i==string.split(",").length-1){
					maxString=string.split(",")[i];
				}
			}
			if(maxString.split(":")[1].equals("00")){
				maxString=maxString.split(":")[0]+":"+"30";
			}else{
				maxString=String.valueOf((Long.valueOf(maxString.split(":")[0])+1))+":"+"00";
			}
			System.err.println(string.split(",")[0]+'-'+maxString);
			return string.split(",")[0]+'-'+maxString;
		}
		
		//转换星期 星期一,星期二,星期三,星期四,星期五,星期六      1,2,3,4,5,6
		public static String transitionWeeks(String string){
			String result="";
			if(null!= string && !"".equals(string)){
			String[] week=string.split(",");
			String data="";
			for (int i = 0; i < week.length; i++) {
				switch (week[i]) {
				case "星期一":
					data+=1+",";
					break;
				case "星期二":
					data+=2+",";
					break;
				case "星期三":
					data+=3+",";
					break;
				case "星期四":
					data+=4+",";
					break;
				case "星期五":
					data+=5+",";
					break;
				case "星期六":
					data+=6+",";
					break;
				case "星期日":
					data+=7+",";
					break;
				}
			}
			result=data.substring(0,data.length()-1);
			}
			return result;
		}
		public static String transitionTimes(String string){
			String data="";
			if(null!= string && !"".equals(string)){
				String maxString ="";
				for(int i=0;i<=string.split(",").length-1;i++){
					if(i==string.split(",").length-1){
						maxString=string.split(",")[i];
					}
				}
				if(maxString.split(":")[1].equals("00")){
					maxString=maxString.split(":")[0]+"30";
				}else{
					maxString=String.valueOf((Long.valueOf(maxString.split(":")[0])+1))+"00";
				}
				System.err.println(string.split(",")[0].replace(":","")+"00"+','+maxString+"00");
				data = string.split(",")[0].replace(":","")+"00"+','+maxString+"00";
			}
			return data;
		}

		public static void main(String[] args) {
			System.err.println(lindHoursOld("8:00")+"---");
			System.err.println(lindHours("8:00"));
		}
		
		/**
		 * 将','分融的时间转换成时间段
		 * @param hours 8:00,8:30,9:00,9:30
		 * @return 80000-100000
		 */
		public static String lindHours(String hours){
			String hour = "" ;
			if(null != hours && !"".equals(hours)){
				String[] hoursArr = hours.split(",");
				int len = hoursArr.length;
				String start = hoursArr[0].replace(":", "") +"00-";
				String end = "";
				String[] longs = hoursArr[len-1].split(":");
				if("00".equals(longs[1])){
					end = Long.toString(Long.parseLong(hoursArr[len-1].replace(":", ""))+30)+"00" ;
				}else{
					end = Long.toString(Long.parseLong(hoursArr[len-1].replace(":", ""))+100-30)+"00" ;
				}
				hour = start+end;
			}
			return hour ;
		}
}
