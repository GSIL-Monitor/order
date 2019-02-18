package com.emotte.order.util;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import net.sf.json.JSONObject; 

/**
 * 
* @ClassName: pdfToImgUtil 
* @Description: pdf转换成图片(png)
* @author lidenghui 
* @date 2018年7月9日 上午11:52:03 
*
 */
public class pdfToImgUtil {
	
	/***
	 * PDF文件转PNG图片，全部页数
	 * @param PdfFilePath pdf完整路径
	 * @param imgFilePath 图片存放的文件夹  
	 * @param dpi dpi越大转换后越清晰，相对转换速度越慢
	 * @return
	 */
	public static String pdf2Image(String PdfFilePath, String dstImgFolder, int dpi) {
		File file = new File(PdfFilePath);
		PDDocument pdDocument;
		String imgFolderPath = null;
		String imgPDFPath = file.getParent();
		int dot = file.getName().lastIndexOf('.');
		String imagePDFName = file.getName().substring(0, dot); // 获取图片文件名
		if (dstImgFolder.equals("")) {
			imgFolderPath = imgPDFPath + File.separator + imagePDFName;// 获取图片存放的文件夹路径
		} else {
			imgFolderPath = dstImgFolder + File.separator + imagePDFName;
		}
		try {
			if (createDirectory(imgFolderPath)) {
				pdDocument = PDDocument.load(file);
 				PDFRenderer renderer = new PDFRenderer(pdDocument);
				/* dpi越大转换后越清晰，相对转换速度越慢 */
				int pages = pdDocument.getNumberOfPages();
				StringBuffer imgFilePath = null;
				for (int i = 0; i < pages; i++) {
					String imgFilePathPrefix = imgFolderPath + File.separator + imagePDFName;
					imgFilePath = new StringBuffer();
					imgFilePath.append(imgFilePathPrefix);
					imgFilePath.append("_");
					imgFilePath.append(String.valueOf(i + 1));
					imgFilePath.append(".png");
					File dstFile = new File(imgFilePath.toString());
					BufferedImage image = renderer.renderImageWithDPI(i, dpi);
					Graphics graphics = image.getGraphics();
					Font font = new Font("宋体",Font.PLAIN, 12);
					graphics.setFont(font);
					ImageIO.write(image, "png", dstFile);
				}
				pdDocument.close();
				System.out.println("PDF文档转PNG图片成功！");
			} else {
				System.out.println("PDF文档转PNG图片失败：" + "创建" + imgFolderPath + "失败");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imgFolderPath;
	}
 
	//创建目录
	public static boolean createDirectory(String folder) {
		File dir = new File(folder);
		if (dir.exists()) {
			return true;
		} else {
			return dir.mkdirs();
		}
	}
	
	//获取路径
	public static  String  getPath(){
		String path=Thread.currentThread().getContextClassLoader().getResource("").toString(); 
		path=path.replace('/', '\\'); // 将/换成\  
		path=path.replace("file:", ""); //去掉file:  
		path=path.replace("classes\\", ""); //去掉classes\  
		path=path.replace("target\\", ""); //去掉target\  
		path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb... 
		path=path.replace("WEB-INF\\", "");
		//文件添加下级目录地址
		path+="uploadImg";
		return path;
	}
	
	
    /**
     * 删除目录以及目录下的文件
     * @param   sPath 被删除目录的路径
     * @return  目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

    
    /**
     *  根据路径删除指定的目录，无论存在与否
     *@param sPath  要删除的目录path
     *@return 删除成功返回 true，否则返回 false。
     */
    public static boolean DeleteFolder(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        System.out.println(file.getName());
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(sPath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(sPath);
            }
        }
    }

    /**
     * 删除单个文件
     * @param   sPath 被删除文件path
     * @return 删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
        	flag = file.delete();
        }
        return flag;
    }

    /**
     * 判断链接格式
     * @param str
     * @return
     */
    public static boolean isURL(String str) {
		str = str.toLowerCase();
		String regex = "^((https|http|ftp|rtsp|mms)?://)" + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
				+ "(([0-9]{1,3}\\.){3}[0-9]{1,3}" 
				+ "|" 
				+ "([0-9a-z_!~*'()-]+\\.)*"
				+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." 
				+ "[a-z]{2,6})" 
				+ "(:[0-9]{1,4})?" 
				+ "((/?)|" 
				+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
		return str.matches(regex);
	}
    
    public static String urlPath(String urlName){
	    InputStream in = null;  
        OutputStream out = null;  
        int connectTimeout = 30 * 1000; // 连接超时:30s  
        int readTimeout = 1 * 1000 * 1000; // IO超时:1min  
        byte[]  buffer = new byte[8 * 1024]; // IO缓冲区:8KB  
        String filePath="";
       try {
    	   URL url = new URL(urlName);  
    	   int dot = url.getFile().lastIndexOf('/');
    	   String imagePDFName = url.getFile().substring(dot+1,url.getFile().length()); // 获取图片文件名
    	   filePath=getPath()+File.separator+imagePDFName;
    	   if(createDirectory(getPath())){
    		   File file = new File(filePath);  
   	        URLConnection conn = url.openConnection();  
   	        conn.setConnectTimeout(connectTimeout);  
   	        conn.setReadTimeout(readTimeout);  
   	        conn.connect();  
   	  
   	        in = conn.getInputStream();  
   	        out = new FileOutputStream(file);  
   	  
   	        for (;;) {  
   	            int bytes = in.read(buffer);  
   	            if (bytes == -1) {  
   	                break;  
   	            }  
   	            out.write(buffer, 0, bytes);  
   	            out.flush();
   	        }  
    	   }
	        in.close(); 
	        out.close(); 
	} catch (Exception e) {
		e.printStackTrace();
	}
       return filePath;
}
    
    
    /***
	 * PDF文件转图片,全部页数
	 * @param PdfFilePath 需转换的pdf完整路径
	 * @param imgFilePath 转换图片存放的文件夹  
	 * @param dpi dpi越大转换后越清晰，相对转换速度越慢
	 * @author zs
	 * @return
     * @throws IOException 
	 */
	public static String pdfToImage(String PdfFilePath, String dstImgFolder, int dpi,String fileType,String imagePDFName){
		System.out.println("pdf转换图片开始->"+PdfFilePath);
		InputStream is = null;
		PDDocument pdDocument = null;
		JSONObject result = new JSONObject();
		if(null == PdfFilePath || "".equals(PdfFilePath)){
			result.put("msg", "fail");
			result.put("text", "PDF文档转图片失败：pdf路径不能为空");
			result.put("imgFolderPath","");
			return result.toString();
		}
		if(null == dstImgFolder || "".equals(dstImgFolder)){
			result.put("msg", "fail");
			result.put("text","PDF文档转图片失败：图片存放位置不能为空");
			result.put("imgFolderPath","");
			return result.toString();
		}
		if(null == PdfFilePath || "".equals(PdfFilePath)){
			dpi = 180;//默认为180dpi
		}
		if(null == fileType || "".equals(fileType)){
			fileType = "jpg";//默认为jpg格式
		}
		Long start = System.currentTimeMillis();
		String imgFolderPath = "";
		try {
			String fileName = new File(PdfFilePath).getName();
			if(!fileName.toLowerCase().endsWith(".pdf")){
				result.put("msg", "fail");
				result.put("text","PDF文档转图片失败：文件类型不是pdf类型");
				result.put("imgFolderPath","");
				return result.toString();
			}
			if(isURL(PdfFilePath)){
				URL url = new URL(PdfFilePath);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(10 * 1000);
				conn.connect();
				is = conn.getInputStream();
			}else{
				is = new FileInputStream(PdfFilePath);
			}
			if(imagePDFName == null || "".equals(imagePDFName)){
				imagePDFName = fileName.substring(0, fileName.lastIndexOf('.')); //获取图片文件名
			}
			imgFolderPath = dstImgFolder + File.separator + imagePDFName;//图片存放的文件夹路径
			if (createDirectory(imgFolderPath)) {
				pdDocument = PDDocument.load(is);
 				PDFRenderer renderer = new PDFRenderer(pdDocument);
				int pages = pdDocument.getNumberOfPages();
				StringBuffer imgFilePath = null;
				for (int i = 0; i < pages; i++) {
					String imgFilePathPrefix = imgFolderPath + File.separator + imagePDFName;
					imgFilePath = new StringBuffer();
					imgFilePath.append(imgFilePathPrefix);
					imgFilePath.append("_");
					imgFilePath.append(String.valueOf(i + 1));
					imgFilePath.append("."+fileType);
					File dstFile = new File(imgFilePath.toString());
					BufferedImage image = renderer.renderImageWithDPI(i, dpi);
					Graphics graphics = image.getGraphics();
					Font font = new Font("宋体",Font.PLAIN, 12);
					graphics.setFont(font);
					//System.err.println(image.getGraphics().getFont());
					ImageIO.write(image,fileType,dstFile);
				}
				result.put("msg", "success");
				result.put("text","PDF文档转图片成功");
				result.put("imgFolderPath",imgFolderPath);
			} else {
				result.put("msg", "fail");
				result.put("text","PDF文档转图片失败:创建" + imgFolderPath + "失败");
				result.put("imgFolderPath","");
				return result.toString();
			}
		}catch (IOException e) {
			e.printStackTrace();
			result.put("msg", "fail");
			result.put("imgFolderPath","");
			result.put("text",e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			result.put("msg", "fail");
			result.put("imgFolderPath","");
			result.put("text",e.getMessage());
		}finally {
				try {
					if(is != null){
						is.close();
					}
					if(pdDocument != null){
						pdDocument.close();
					}
				} catch (IOException e) {
					result.put("msg", "fail");
					result.put("imgFolderPath","");
					result.put("text",e.getMessage());
					e.printStackTrace();
				}
		}
		Long end = System.currentTimeMillis();
		System.out.println("pdf转换图片结束->转换用时"+((end-start)/1000)+"秒");
		System.out.println("pdf转换图片返回结果："+result.toString());
		return result.toString();
	}
	
	/***
	 * PDF文件转图片,并压缩成zip文件
	 * @param PdfFilePath 需转换的pdf完整路径
	 * @param imgFilePath 转换图片存放的文件夹  
	 * @param dpi dpi越大转换后越清晰，相对转换速度越慢
	 * @author zs
	 * @return
     * @throws IOException 
	 */
	public static String pdfToImageZip(String PdfFilePath, String dstImgFolder, int dpi,String fileType){
		String result = pdfToImage(PdfFilePath,dstImgFolder,dpi,fileType,null);
		JSONObject json = JSONObject.fromObject(result);
		String zipPath = "";
		String imgFolderPath = json.get("imgFolderPath") != null?json.get("imgFolderPath").toString():"";
		if(json != null && !"".equals(imgFolderPath)){
				String fileName = new File(PdfFilePath).getName().toLowerCase();
				String imagePDFName = fileName.substring(0, fileName.lastIndexOf('.')); //获取图片文件名
				zipPath = dstImgFolder+File.separator+imagePDFName+".zip";
				ZipFileUtil zipUtil = new ZipFileUtil(zipPath);
				zipUtil.compress(imgFolderPath);
		}
		System.out.println("pdf打包返回结果："+imgFolderPath);
		return zipPath;
	}
	
	/**
	 * 提取url中的文件名称
	 * 
	 * @param url
	 * @return
	 */
	public static String getFileNameFromUrl(String url) {
		String name = "";
		int index = url.lastIndexOf("\\");
		if (index > 0) {
			name = url.substring(index + 1);
			if (name.trim().length() > 0) {
				return name;
			}
		}else{
			return url;
		}
		return name;
	}
	
	/***
	 * PDF文件转图片,全部页数
	 * @param PdfFilePath 需转换的pdf完整路径
	 * @param imgFilePath 转换图片存放的文件夹  
	 * @param dpi dpi越大转换后越清晰，相对转换速度越慢
	 * @author zs
	 * @return
     * @throws IOException s
	 */
	public static String pdfToImageByFolder(String PdfFilePath, String dstImgFolder, int dpi,String fileType){
		File f = new File(PdfFilePath);
		if(f.isDirectory()){
			//是文件夹,读取该文件夹下所有pdf文件
			String[] filelist = f.list();
            for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(PdfFilePath+File.separator+filelist[i]);
                    if (readfile.isFile()) {
                    	String name = readfile.getName().toLowerCase();
                    	if(name.endsWith(".pdf")){
                    		System.err.println("第"+(i+1)+"个->"+name+"开始---------------------------->");
                    		pdfToImage(readfile.getPath(),dstImgFolder,dpi,fileType,null);
                    	}
                    }
            }
		}else{
			return "fail";
		}
		return "success";
	}
	
	public static String pdfToImageByFolder1(String[] t, String dstImgFolder, int dpi,String fileType,String imagePDFName){
		for (int i = 0; i < t.length; i++) {
			System.err.println("第"+(i+1)+"个->"+t[i]+"开始----------------------------");
    		pdfToImage(t[i],dstImgFolder,dpi,fileType,imagePDFName);
    		System.err.println("第"+(i+1)+"个->"+t[i]+"结束----------------------------");
		}
		return "success";
	}
	
	public static void main(String[] args) throws IOException {
		
		String dstImgFolder = "C:/Users/Administrator/Desktop/合同/合同模板_20190117_V1.0/下载";//存放下载文件地址
		String f1 = "C:/Users/Administrator/Desktop/合同/合同模板_20190117_V1.0/下载/（直付合同）管家帮平台信息服务协议-20190117.pdf";
		String f2 = "C:/Users/Administrator/Desktop/合同/合同模板_20190117_V1.0/下载/（非海金代付合同）管家帮平台信息服务协议-20190117.pdf";
		String f3 = "C:/Users/Administrator/Desktop/合同/合同模板_20190117_V1.0/下载/（海金代付合同）管家帮平台信息服务协议-20190117.pdf";
		String f4 = "C:/Users/Administrator/Desktop/合同/合同模板_20190117_V1.0/下载/管家帮分期付款协议-20190117.pdf";
		//pdfToImage(f1,dstImgFolder,180,"jpg","jxzf_zs");
		//pdfToImage(f2,dstImgFolder,180,"jpg","jxdfxdb_zs");
		//pdfToImage(f3,dstImgFolder,180,"jpg","jxdfhj_zs");
		pdfToImage(f4,dstImgFolder,180,"jpg","jxdfhjfq_zs");
		
		String dstImgFolder1 = "C:/Users/Administrator/Desktop/合同/合同模板_20190117_V1.0/预览";//存放下载文件地址
		String p1 = "C:/Users/Administrator/Desktop/合同/合同模板_20190117_V1.0/预览/（直付合同）管家帮平台信息服务协议-20190117.pdf";
		String p2 = "C:/Users/Administrator/Desktop/合同/合同模板_20190117_V1.0/预览/（非海金代付合同）管家帮平台信息服务协议-20190117.pdf";
		String p3 = "C:/Users/Administrator/Desktop/合同/合同模板_20190117_V1.0/预览/（海金代付合同）管家帮平台信息服务协议-20190117.pdf";
		String p4 = "C:/Users/Administrator/Desktop/合同/合同模板_20190117_V1.0/预览/管家帮分期付款协议-20190117.pdf";
		//pdfToImage(p1,dstImgFolder1,180,"jpg","jxzf_yl");
		//pdfToImage(p2,dstImgFolder1,180,"jpg","jxdfxdb_yl");
		//pdfToImage(p3,dstImgFolder1,180,"jpg","jxdfhj_yl");
		pdfToImage(p4,dstImgFolder1,180,"jpg","jxdfhjfq_yl");
	}


}
