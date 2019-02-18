package com.emotte.order.order.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.auth.annotation.UserAnnotation;
import org.wildhorse.server.core.controller.BaseController;

import com.emotte.order.util.Constants;
import com.emotte.order.util.PicManagerUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/scanFile")
public class salaryFile extends BaseController {

	/**
	 * 上传文件
	 * 
	 * @param request
	 * @param response
	 */
	@UserAnnotation(methodName = "上传文件")
	@RequestMapping(value = "/uploadFile", method = { RequestMethod.POST, RequestMethod.GET })
	public void uploadFile(HttpServletRequest request, HttpServletResponse response) {
		JSONObject jsonObject = new JSONObject();
		String imgInfo = null;
		try {
			imgInfo = PicManagerUtil.upload(request);
			msg = Constants.SCUUESS;
		} catch (Exception e) {
			log.error("uploadFile:" + e);
			msg = Constants.FAIL;
		}
		jsonObject.accumulate("msg", msg);
		jsonObject.accumulate("imgInfo", imgInfo);
		responseSendMsg(response, jsonObject.toString());
	}

	/**
	 * 下载文件
	 * 
	 * @param realPath
	 * @param fileName
	 * @param response
	 * @throws Exception
	 */
	@UserAnnotation(methodName = "下载文件")
	@RequestMapping(value = "/downloadFile", method = { RequestMethod.POST, RequestMethod.GET })
	public void downloadFile(HttpServletRequest request, HttpServletResponse response, String httpUrl) {
		try {
			httpUrl = URLDecoder.decode(httpUrl, "UTF-8");
			String fileName = getFileNameFromUrl(httpUrl);
			URL url = new URL(httpUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5 * 1000);
			InputStream inStream = conn.getInputStream();
			//设置响应参数
			response.setCharacterEncoding("utf-8");
			response.setContentType("multipart/form-data");
			response.addHeader("Content-Disposition", "attachment; filename=" +fileName);
			response.setContentType("application/x-download");
			OutputStream os = response.getOutputStream();
			byte[] buffer = new byte[2048];
			int len = 0;
			while ((len = inStream.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
			inStream.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			log.error("downloadFile:" + e);
		}
	}

	/**
	 * 提取url中的文件名称
	 * 
	 * @param url
	 * @return
	 */
	public static String getFileNameFromUrl(String url) {
		String name = new Long(System.currentTimeMillis()).toString() + ".X";
		int index = url.lastIndexOf("/");
		if (index > 0) {
			name = url.substring(index + 1);
			if (name.trim().length() > 0) {
				return name;
			}
		}
		return name;
	}

	/**
	 * 读输入流中的数据
	 * 
	 * @param inStream
	 * @return
	 * @throws Exception
	 */
	public static byte[] readInputStream(InputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[2048];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		inStream.close();
		return outStream.toByteArray();
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String url = "http://erp.95081.com/img/img/2017/07/17/d40b252fc4934d3486c1f1b58b3e7d8e.jpg";
		String fileName = getFileNameFromUrl(url);
		System.out.println(fileName);
	}

}