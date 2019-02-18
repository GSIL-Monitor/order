package com.emotte.order.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字转换为大写的中文数字（支持到千万亿），单位：元。如  123.45 结果为：壹佰贰拾叁点肆伍； 1.00 结果为 壹；1.001 结果为 壹点零零壹
 * @author ZhangYang
 */
public class NumberToCN {
	/**
	 * 中文大写汉字
	 */
	private String[] cn_num = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌","玖" };

	/**
	 * 单位
	 */
	private String[] cn_unit = { "", "拾", "佰", "仟", "万", "拾万", "佰万",
			"仟万", "亿", "拾亿", "佰亿", "仟亿", "万亿" };

	/**
	 * 取得数字对应的中文
	 * @param s 要转换的数字，如：1340923 结果为：壹佰叁拾肆万零玖佰贰拾叁
	 * @return
	 */
	public String getCn(String str) {
		String s = str;
		if(str.indexOf(".")!=-1){ //如果包含小数点，先处理小数点之前的部分，最后将结果拼加小数点和后面的部门
			s = str.substring(0, str.indexOf("."));
		}
		
		if(s.length()==0 || s.equals("")){
			return "零";
		}
		char[] c = s.toCharArray(); //拆分成的单个字符数组，如 [1 2 3 4 5 6]
		StringBuffer chSb = new StringBuffer(); //用来保存结果
		int len = c.length; //保存单位索引
		List<String> list = new ArrayList<String>(); //保存所有的单位，做最后处理
		String d = ""; //保存单位
		for (int i = 0; i < c.length; i++) {
			// 如果有几个0挨在一起时, 只显示一个零即可
			if (i > 0 && c[i] == '0' && c[i] == c[i - 1]) {
				--len;
				continue;
			}
			// 得到数字对应的中文
			chSb.append(cn_num[Integer.parseInt((c[i]+""))]);
			// 非零时, 追加单位
			if (!cn_num[Integer.parseInt((c[i]+""))].equals("零")) {
				d = cn_unit[--len];
				list.add(d);
				chSb.append(d);
			} else {
				--len; // 如果是0则不取位数
			}
		}
		String chStr = chSb.toString();
		// 对结果做一些处理
		if (list.contains("万") && list.contains("拾万")) {
			chStr = chStr.replaceAll("拾万", "拾");
		}
		if ((list.contains("万") && list.contains("佰万"))
				||(list.contains("拾万") && list.contains("佰万"))) {
			chStr = chStr.replaceAll("佰万", "佰");
		}
		if ((list.contains("万") && list.contains("仟万"))
				||(list.contains("佰万") && list.contains("仟万"))
				||(list.contains("拾万") && list.contains("仟万"))) {
			chStr = chStr.replaceAll("仟万", "仟");
		}
		
		if (list.contains("亿") && list.contains("拾亿")) {
			chStr = chStr.replaceAll("拾亿", "拾");
		}
		if ((list.contains("亿") && list.contains("佰亿"))
				||(list.contains("拾亿") && list.contains("佰亿"))) {
			chStr = chStr.replaceAll("佰亿", "佰");
		}
		if ((list.contains("亿") && list.contains("仟亿"))
				||(list.contains("拾亿") && list.contains("仟亿"))
				||(list.contains("佰亿") && list.contains("仟亿"))) {
			chStr = chStr.replaceAll("仟亿", "仟");
		}
		if ((list.contains("亿") && list.contains("万亿"))
				||(list.contains("仟亿") && list.contains("万亿"))
				||(list.contains("佰亿") && list.contains("万亿")) 
				||(list.contains("拾亿") && list.contains("万亿"))) {
			chStr = chStr.replaceAll("万亿", "万");
		}
		// 如果最后一位是 0, 则去掉
		if (chStr.length()>1 && (chStr.charAt(chStr.length() - 1)) == '零') {
			chStr = chStr.substring(0, chStr.length() - 1);
		}
		if(str.indexOf(".")!=-1){
			chStr += this.others(str);
		}
		return chStr;
	}
	
	/**
	 * 返回小数点后面的数字（包含”点“）
	 * @param str ： 完整的字符串
	 * @return
	 */
	private String others(String str){
		String result = "点";
		boolean flag = true;
		String s = str.substring(str.indexOf(".")+1);
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++) {
			String cn = cn_num[Integer.parseInt((c[i]+""))];
			if(!cn.equals("零")){
				flag = false;
			}
			result += cn;
		}

		if(flag) //如果点后边都是零，就返回""
			result = "";
		return result;
	}
	
}