package com.emotte.order.order.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wildhorse.server.auth.annotation.UserAnnotation;

import com.emotte.order.util.ZipFileUtil;
import com.emotte.order.util.pdfToImgUtil; 

@Controller
@RequestMapping("/pdfToImg")
public class PdfToImgController {
	
	/**
	 * 
	* @Description: TODO 
	* @author lidenghui
	* @date 2018年7月9日 上午11:51:16 
	* @version
	* @param request
	* @param response
	* @param oldFileName 文件名称
	 */
	 @RequestMapping(value = "/download", method={RequestMethod.POST,RequestMethod.GET})	
 	  public void download(HttpServletRequest request,HttpServletResponse response,String oldFileName){
 		  String fileName="";
 		  String zipPath="";
  		  String filePath="";
 		  String inPath=""; 
 	    try 
 	    {  
 	    	if(pdfToImgUtil.isURL(oldFileName)){
				 inPath=pdfToImgUtil.urlPath(oldFileName);
			}else{
				inPath=oldFileName;
			}
 	    	System.out.println(inPath);
 	    	//String oldPath ="D:/15588858789DGEW.pdf"; 
 			String path =pdfToImgUtil.getPath();
 		    filePath =pdfToImgUtil.pdf2Image(inPath, path, 180);
 			if(filePath!=null){
 				zipPath = filePath+".zip";
 				ZipFileUtil zipUtil = new ZipFileUtil(zipPath);
 				zipUtil.compress(filePath);
 			}
 	       fileName=zipPath;
 	       File file=new File(oldFileName);
 		   int dot = file.getName().lastIndexOf('.');
 		   String imagePDFName = file.getName().substring(0, dot)+".zip"; // 获取图片文件名
 	        // 得到要下载的文件  
 	        File downloadFile = new File(fileName);
 	        System.err.println(fileName);
 	        // 如果文件不存在  
 	        if (!downloadFile.exists()) {  
 	            System.out.println("您要下载的资源已被删除！！");  
 	            return;  
 	        }  
 	        // 设置响应头，控制浏览器下载该文件  
		    response.setHeader("Content-disposition", "attachment;filename="+new String(imagePDFName.getBytes(),"iso-8859-1"));
		    response.setContentType("application/vnd.ms-excel"); 
 	        // 读取要下载的文件，保存到文件输入流  
 	        FileInputStream in = new FileInputStream(downloadFile);  
 	        // 创建输出流  
 	        OutputStream out = response.getOutputStream();  
 	        // 创建缓冲区  
 	        byte buffer[] = new byte[1024];  
 	        int len = 0;  
 	        // 循环将输入流中的内容读取到缓冲区当中  
 	        while ((len = in.read(buffer)) > 0) {  
 	            // 输出缓冲区的内容到浏览器，实现文件下载  
 	            out.write(buffer, 0, len);  
 	        }  
 	        // 关闭文件输入流  
 	        in.close();  
 	        out.flush();
 	        // 关闭输出流  
 	        out.close();  
 	    } catch (Exception e) {  
 	    	e.printStackTrace();
 	    }  finally {
 	    	pdfToImgUtil.DeleteFolder(filePath);
 	    	pdfToImgUtil.DeleteFolder(zipPath);
 	    	pdfToImgUtil.DeleteFolder(inPath);
		}
 	  }
	 
	 
	 /**
	  * 
	  * @param request
	  * @param response
	  * @param pdfUrl
	  */
	  @UserAnnotation(methodName = "下载文件")
	  @RequestMapping(value = "/downloadFile", method = { RequestMethod.POST, RequestMethod.GET })
	  public void downloadFile(HttpServletRequest request,HttpServletResponse response,String pdfUrl){
		  InputStream inStream = null;
		  OutputStream os = null;
		  BufferedOutputStream bos = null;
		  File zipFile = null;
		  String dstImgFolder = "";//存放临时文件的文件夹
	    try{ 
	    	dstImgFolder = request.getSession().getServletContext().getRealPath("/")+"temp1";//项目根路径+临时文件夹
			int dpi = 180;
	    	String zipUrl = pdfToImgUtil.pdfToImageZip(pdfUrl,dstImgFolder,dpi,"png");
	    	if(null != zipUrl && !"".equals(zipUrl)){
	    		zipFile = new File(zipUrl);
	    		inStream = new FileInputStream(zipFile);
				response.setCharacterEncoding("utf-8");
				response.addHeader("Content-Disposition", "attachment; filename="+new String(zipFile.getName().getBytes(),"UTF-8"));
				response.setContentType("application/x-download");
				os = response.getOutputStream();
				bos = new BufferedOutputStream(os);
				byte[] buffer = new byte[2048];
				int len = 0;
				while ((len = inStream.read(buffer)) != -1) {
					bos.write(buffer, 0, len);
				}
	    	}
	    } catch (Exception e) {  
	    	e.printStackTrace();
	    }  finally {
	    		try {
	    			if(inStream != null){
	    				inStream.close();
	    			}
	    			if(bos != null){
	    				bos.flush();
	    				bos.close();
	    			}
	    			if(os != null){
	    				os.flush();
	    				os.close();
	    			}
    			    if(zipFile.isFile() && zipFile.exists()) {
    		        	boolean zipFlag = zipFile.delete();//删除zip临时文件
    		        	if(zipFlag){
    		        		System.out.println(zipFile.getName()+"->临时文件删除成功");
    		        	}else{
    		        		System.out.println(zipFile.getName()+"->临时文件删除失败");
    		        	}
    		        	if(!"".equals(dstImgFolder)){
    		        		String name =  zipFile.getName();
    		        		String folder = name.substring(0,name.lastIndexOf('.'));
    		        		folder = (dstImgFolder+File.separator+folder);
    		        		boolean imgFlag = pdfToImgUtil.deleteDirectory(folder);//删除图片临时文件
    		        		if(imgFlag){
    		        			System.out.println(folder+"->临时文件夹删除成功");
    		        		}else{
    		        			System.out.println(folder+"->临时文件夹删除失败");
    		        		}
    		        	}
    		        }
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	  }
	 
}
