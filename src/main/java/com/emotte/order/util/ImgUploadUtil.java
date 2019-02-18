package com.emotte.order.util;

import java.io.File;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
public class ImgUploadUtil {

	public static String upload(HttpServletRequest request ,String type) throws Exception {
		String result = null;
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
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
					if (!verifyExt(myFileName)) {
						throw new Exception("上传的文件类型有误！只能是jpg、png或gif");
					}
					// 将MultipartFile转换为File
					String realPath = request.getSession().getServletContext()
							.getRealPath("/"); // 项目根路径
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
			result =  uploadImg(files, type);
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
	public static boolean verifyExt(String fileName) {
		try {
			String ext = fileName.substring(fileName.lastIndexOf("."),
					fileName.length());
			if (ext == null) {
				return false;
			} else {
				ext = ext.toLowerCase();
				if (ext.equals(".jpg") || ext.equals(".png")
						|| ext.equals(".gif")) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 获取文件保存路径（如：/dataFile/qrCode/2016/04/04/1459764452490.gif）
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getPath(String fileName) {
		String path = "/temp/";
		String ext = fileName.substring(fileName.lastIndexOf("."),
				fileName.length());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date now = new Date();
		path += sdf.format(now) + "/" + now.getTime() + ext;
		return path;
	}

	/**
	 * 向图片服务器上传图片
	 * @param files 要上传的图片集合
	 * @param type 上传图片的类型
	 * @return
	 * @throws Exception
	 */
	public static String uploadImg(List<File> files,String type) throws Exception {
		String jsonStr = null;
		@SuppressWarnings({ "resource" })
		HttpClient httpclient = new DefaultHttpClient();
		//HttpPost httppost = new HttpPost(
		//		"http://192.168.1.230:8081/img_service/ul/uploadImg.action");
		String imgStr = ConfigComm.getInstance("config.properties").get("imgUploadPath").toString();
		HttpPost httppost = new HttpPost(imgStr);
		// 多部分的实体
		MultipartEntity reqEntity = new MultipartEntity();
		// 增加
		for (File file : files) {
			reqEntity.addPart("files", new FileBody(file));
		}
		reqEntity.addPart("type",
				new StringBody("original", Charset.forName("UTF-8")));

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
