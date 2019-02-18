package com.emotte.order.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;


// AES加解密
public class AESUtil {
	private static final String KEY_AES = "AES";
	private static final String CHARSET = "UTF-8";
	private static final String KEY_MD5 = "MD5";

	// 加密
	public static String encrypt(String data, String key) {
		return doAES(data, key, Cipher.ENCRYPT_MODE);
	} // 解密

	public static String decrypt(String data, String key) {
		return doAES(data, key, Cipher.DECRYPT_MODE);
	}

	private static String doAES(String data, String key, int mode) {
		try {
			if (StringUtils.isBlank(data) || StringUtils.isBlank(key)) {
				return null;
			}
			boolean encrypt = mode == Cipher.ENCRYPT_MODE;
			byte[] content;
			if (encrypt) {
				content = data.getBytes(CHARSET);
			} else {
				content = Base64.decodeBase64(data);
			}
			MessageDigest md5Digest = MessageDigest.getInstance(KEY_MD5);
			SecretKeySpec keySpec = new SecretKeySpec(md5Digest.digest(key.getBytes(CHARSET)), KEY_AES);
			Cipher cipher = Cipher.getInstance(KEY_AES);// 创建密码器
			cipher.init(mode, keySpec);// 初始化
			byte[] result = cipher.doFinal(content);
			if (encrypt) {
				return Base64.encodeBase64String(result);
			} else {
				return new String(result, CHARSET);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/*@Test
	public void test() throws Exception {
		String url = "http://insurertest.union­ins.cn/tripinsureplat/api/partnerinsure/property/v1/118/entity";
		//双方预约好的md5秘钥
		String signSecretKey = "signSecretKey";
		
		//待签名数据
		String data = "{\"order\": {\"orderId\": \"20170802110851000\",\"productCode\": \"xxxxxxxxxxxxxx\",\"applyTime\": \"2017-08-02 13:25:20\",\"extendInfos\": {}},\"insObject\": {\"totalPremium\": \"65\",\"totalAmount\": \"6400000\",\"effectiveStartTime\": \"2017-08-02 00:00:00\",\"effectiveEndTime\": \"2017-08-02 23:59:59\",\"province\":\"北京\",\"city\":\"北京\",\"address\":\"海淀区杏石口路九号合众大厦\",\"extendInfos\": {}},\"holder\": {\"holderType\": \"1\",\"holderName\": \"cjx\",\"holderPhone\": \"13522212417\",\"holderCidtype\": \"99\",\"holderCidno\": \"123456789\",\"extendInfos\": {}},\"insured\": {\"insuredRelation\": \"01\",\"insuredCidType\": \"\",\"insuredCidNo\": \"\",\"insuredName\": \"\",\"insuredPhone\": \"\",\"extendInfos\": {}}}";
		
		
		JSONObject reqJson = new JSONObject();
		reqJson.put("orderId", "123456");
		// 双方约定好的AES加密key
		String encryptionKey = "encryptionKey";
		// 获得加密的密文 
		data = AESUtil.encrypt(reqJson.toJSONString(), encryptionKey);
		System.out.println(data);
 
		
		// 准备待签名字符串
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(data);
		stringBuilder.append(signSecretKey);
		// 计算签名
		String sign = Md5Encrypt.getMD5Mac(stringBuilder.toString());
		
	}*/
	
	
}