package com.emotte.order.gentlemanSignature;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.apache.commons.codec.binary.Base64;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.emotte.gentlemanSignature.core.controller.LocalSignController;
import com.emotte.gentlemanSignature.core.model.GSResult;
import com.emotte.gentlemanSignature.core.model.LocalSign;
import com.emotte.gentlemanSignature.core.model.LocalSign_fileType0;
import com.emotte.gentlemanSignature.http.HttpClient;
import com.emotte.gentlemanSignature.properties.GSPropertiesUtil;
import com.emotte.order.authorg.model.Org;
import com.emotte.order.order.model.Agreement;

import net.sf.ezmorph.bean.MorphDynaBean;

public enum Util {

	Instance;

	/**
	 * 签名
	 */
	public String gsignature(ServletContext servletContext, String filePath, String fileName, Agreement agreement,
			LocalSign_fileType0 ls) throws Exception {

		ls.setFileData(new File(filePath));
		ls.setContractName(agreement.getAgreementCode());
		
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		LocalSignController LocalSignController = (LocalSignController) context.getBean("localSignController");
		GSResult GSResult = LocalSignController.process(LocalSignController.createCommonParam(ls));
		
		if (null != GSResult && (GSResult.isSuccess() || GSResult.getCode() == 0)) {
			Object data = GSResult.getData();
			if (null != data) {

				MorphDynaBean MorphDynaBean = (MorphDynaBean) data;
				if (null != MorphDynaBean && null != MorphDynaBean.get("signData")) {
					
					String IPFromSig = GSPropertiesUtil.getProperties("IPFromSig");
					String filePathFromSig = GSPropertiesUtil.getProperties("filePathFromSig");
					final String tempFilePath = MorphDynaBean.get("signData").toString();
					new File(filePath).delete();
					String copyFileUsingFileChannel = GSPropertiesUtil.getProperties("copyFileUsingFileChannel");
					String destPathEnd = getPath()+"/"+ls.getContractName()+".pdf";
					final String destPath =  filePathFromSig+destPathEnd;
					
					HttpClient.doPost(copyFileUsingFileChannel, new HashMap<String,Object>() {{
						put("sourcePath", tempFilePath);
						put("destPath",destPath);
					}});
					fileName = IPFromSig + destPathEnd;
				}
			} else {
				throw new Exception("签章数据异常,data=null");
			}
		} else {
			throw new Exception("签章未成功,success=false");
		}

		return fileName;
	}

	public void setOrgSignaturePic(LocalSign ls, Org org_database, boolean usePageChapte) {
		try {
			Base64 base64 = new Base64();
			byte[] signImgByte = file2Byte(org_database.getSignaturePicture());
			String signImg = base64.encodeToString(signImgByte);

			String signatories = "[\r\n"
					+ "{\"identityType\":\"11\",\"fullName\":\"管家帮\",\"email\":\"guanjiabang@www.bccto.me\",\"identityCard\":\"916400007508102806\",\r\n"
					+ "\"signImg\":\"" + signImg + "\"";
			if (usePageChapte) {
				signatories = signatories + ",\"pageChapteJson\":[{\r\n" + "  \"page\":\"-1\",\r\n"
						+ "    \"chaptes\":[\r\n" + " {\"offsetX\":\"0.32\",\"offsetY\":\"0.75\"}\r\n" + " ]\r\n"
						+ "}]\r\n}]";
			} else {
				signatories = signatories + "}]";
			}

			ls.setSignatories(signatories);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private byte[] file2Byte(String signatureFilePath) {
		File file = new File(signatureFilePath);
		FileInputStream stream = null;
		ByteArrayOutputStream out = null;
		try {
			stream = new FileInputStream(file);
			out = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = stream.read(b)) != -1) {
				out.write(b, 0, n);
			}
			return out.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				stream.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	public void copyFileUsingFileChannels(File source, File dest) throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} finally {
			inputChannel.close();
			outputChannel.close();
			source.delete();
		}
	}
	
	//获得一个时间标识保存路径的一部分（yyyy/mm/dd/）
	public String getPath(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(new Date());
	}
	
	
	public static void main(String[] args) {
		String ss = "http://dzht.95081.com/2019/08/09";
		System.out.println(	ss.substring("http://dzht.95081.com".length(),ss.length()));
	}
}
