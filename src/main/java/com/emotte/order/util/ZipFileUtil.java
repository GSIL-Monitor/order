package com.emotte.order.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 打包工具（使用java自带的API）
 * @author ChengJing 
 * 2015年5月12日
 */
public class ZipFileUtil {

	private File zipFile;

	public ZipFileUtil(String zipFilePath) {
		zipFile = new File(zipFilePath);
	}

	public void compress(String srcPath) {
		File file = new File(srcPath);
		ZipOutputStream zos = null;
		try {
			// 创建写出流操作
			OutputStream os = new FileOutputStream(zipFile);
			BufferedOutputStream bos = new BufferedOutputStream(os);
			zos = new ZipOutputStream(bos);
			compress(file, zos, "");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (null != zos) {
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 压缩文件
	 * @param source 源文件
	 * @param basePath
	 * @param zos
	 */
	private void compress(File file, ZipOutputStream zos, String basePath) {
		InputStream is = null;
		BufferedInputStream bis = null;
		try {
			if (file.isDirectory()) {
				basePath += file.getName() + "/";
				zos.putNextEntry(new ZipEntry(basePath));
				for (File _file : file.listFiles()) {
					compress(_file, zos, basePath);
				}
			} else {
				is = new FileInputStream(file);
				bis = new BufferedInputStream(is);
				zos.putNextEntry(new ZipEntry(basePath + file.getName()));
				int length = 0;
				byte[] buf = new byte[1024];
				while ((length = bis.read(buf)) > 0) {
					zos.write(buf, 0, length);
				}
				bis.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String srcPath = "c:/temp/pyrios-new";
		String zipPath = "c:/temp/pyrios-new.zip";
		ZipFileUtil zipUtil = new ZipFileUtil(zipPath);
		zipUtil.compress(srcPath);
	}
}
