package com.emotte.order.util;

import java.io.File;
import java.nio.charset.Charset;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 基于POI的javaee导出Excel工具类
 * 
 * @author luocc
 * @see POI
 */
public class ExcelUpload {

	/**
	 * @param response
	 *            请求
	 * @param fileName
	 *            文件名 如："订单给出表"
	 * @param excelHeader
	 *            excel表头数组，存放"姓名#name"格式字符串，"姓名"为excel标题行， "name"为对象字段名
	 * @param dataList
	 *            数据集合，需与表头数组中的字段名一致，并且符合javabean规范
	 * @return 返回一个HSSFWorkbook
	 * @throws Exception
	 */
	public static String imgser(String file,String type) {
		String INTERFACE_PATH = ConfigComm.getInstance("config.properties").get("excelUploadPath").toString();
		System.out.println(INTERFACE_PATH);
		String result="";
    	try {
			HttpClient httpclient = new DefaultHttpClient();
	    	HttpPost httppost = new HttpPost(INTERFACE_PATH);
	    	FileBody bin = new FileBody(new File(file));
	
	    	// 一个字符串
	    	// 多部分的实体
	    	MultipartEntity reqEntity = new MultipartEntity();
	    	// 增加
	    	reqEntity.addPart("myFile", bin);
	    	//reqEntity.addPart("files", bin2);
	    	reqEntity.addPart("type", new StringBody(type, Charset.forName("UTF-8")));
	
	    	httppost.setEntity(reqEntity);
	    	System.out.println("执行: " + httppost.getRequestLine());
	    	HttpResponse response = httpclient.execute(httppost);
	    	HttpEntity resEntity = response.getEntity();
	    	System.out.println("----------------------------------------");
	    	System.out.println(response.getStatusLine());
	    	if (resEntity != null) {
	    		System.out.println("返回长度: " + resEntity.getContentLength());
	    		String jsonVal = EntityUtils.toString(resEntity);
	    		System.out.println("文件路径："+jsonVal);
	    		result = jsonVal;
	    	}
		} catch (Exception e) {
			e.printStackTrace();
			result = "2";
		}
    	return result;
    }
}