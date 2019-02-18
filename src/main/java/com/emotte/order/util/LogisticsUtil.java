package com.emotte.order.util;

public class LogisticsUtil {
	/**
	 * 将快递公司名称转成拼音
	 * @param name
	 * @return
	 */
	public static String nameToPinyin(String name){
		String result = "自配送";
		if(!"".equals(name) && name != null){
			if ("顺丰快递".equals(name) || "北京顺丰".equals(name) || "燕窝顺丰".equals(name) 
					|| "青岛顺丰".equals(name) || "百年顺丰".equals(name)) {
				result = "shunfeng";
			} else if("北京极客".equals(name)){//result = "beijingjike";
				result = "北京极客";
			}else if("圆通测试".equals(name)){
				result = "yuantong";
			}else if("每周一约".equals(name)){//result = "meizhouyiyue";
				result = "每周一约";
			}else if("申通快递".equals(name)){
				result = "shentong";
			}else if("天天快递".equals(name)){//result = "tiantian";
				result = "天天快递";
			}else if("德邦快递".equals(name)){
				result = "debangwuliu";
			}else if("第三方".equals(name)){
				result = "第三方";
			}else if("中通快递".equals(name)){
				result = "zhongtong";
			}else if("宅急送".equals(name)){
				result = "zhaijisong";
			}else if("韵达快递".equals(name)){
				result = "yunda";
			}else if("预售".equals(name)){
				result = "预售";
			}else if("礼品兑换".equals(name)){
				result = "礼品兑换";
			}else if("天津分公司".equals(name)){
				result = "天津分公司";
			}else if("网络运营一部".equals(name)){
				result = "网络运营一部";
			}else if("网络运营二部".equals(name)){
				result = "网络运营二部";
			}
		}
		return result;
	}
}
