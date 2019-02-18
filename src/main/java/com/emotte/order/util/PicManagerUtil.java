package com.emotte.order.util;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@SuppressWarnings("deprecation")
public class PicManagerUtil {

	public static String upload(HttpServletRequest request) throws Exception {
		String result = null;
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			List<File> files = new ArrayList<File>();
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 将MultipartFile转换为File
					String realPath = request.getSession().getServletContext().getRealPath("/"); // 项目根路径
					String filePath = getPath(myFileName); // 新文件路径
					File localFile = new File(realPath + filePath);
					if (!localFile.getParentFile().exists()) {
						localFile.getParentFile().mkdirs();
					}
					file.transferTo(localFile);
					files.add(localFile);
				}
			}
			//文件上传到文件服务器
			result =  uploadImg(files);
			//删除本地文件
			for(File f : files){
				f.delete();
			}
		}
		return result;
	}

	/**
	 * 验证上传文件的拓展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String verifyExt(String fileName) {
		String result = "img" ;
			try {
				String ext = fileName.substring(fileName.lastIndexOf("."),fileName.length());
				if (ext != null && !ext.equals("")) {
					if (ext.equals(".jpg") || ext.equals(".png")|| ext.equals(".gif")|| ext.equals(".jpeg")) {
						result =  "img" ;
					} else {
						result =  "file" ;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return result;
	}

	/**
	 * 获取文件保存路径（如：/dataFile/qrCode/2016/04/04/1459764452490.gif）
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getPath(String fileName) {
		String path = "/temp/";
		String ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		//		Date now = new Date();
		UUID uuid = UUID.randomUUID();
		//		path += sdf.format(now) + "/" + now.getTime() + ext;
		path += uuid.toString() + ext;
		return path;
	}

	/**
	 * 向图片服务器上传图片
	 * @param files 要上传的图片集合
	 * @param type 上传图片的类型
	 * @return
	 * @throws Exception
	 */
	public static String uploadImg(List<File> files) throws Exception {
		String type = "";
		String jsonStr = null;
		@SuppressWarnings({ "resource" })
		HttpClient httpclient = new DefaultHttpClient();
		String uploadType = "img";
		for (File file : files) {
			uploadType = verifyExt(file.getName());
		}
		ResourceBundle config = ResourceBundle.getBundle("config");
		String url = "";
		if(uploadType.equals("img")){
			//jpg、png、gif、jpeg
			url = config.getString("imgUploadPath");
			type = "ORIGINAL";
		}else{
			//pdf、svg
			url = config.getString("excelUploadPath");
			type = "OTHER";
		}
		HttpPost httppost = new HttpPost(url);
		// 一个字符串
		// 多部分的实体
		MultipartEntity reqEntity = new MultipartEntity();
		// 增加
		if(uploadType.equals("img")){
			//jpg、png、gif、jpeg
			for (File file : files) {
				reqEntity.addPart("files", new FileBody(file));
			}
		}else{
			//pdf、svg
			for (File file : files) {
				reqEntity.addPart("myFile", new FileBody(file));
			}
		}
		reqEntity.addPart("type",new StringBody(type, Charset.forName("UTF-8")));
		// 设置
		httppost.setEntity(reqEntity);
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity resEntity = response.getEntity();
		if (resEntity != null) {
			jsonStr = EntityUtils.toString(resEntity);
		}
		System.out.println(jsonStr);
		return jsonStr;
	}
}
