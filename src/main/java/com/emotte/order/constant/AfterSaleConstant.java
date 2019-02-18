package com.emotte.order.constant;

public interface AfterSaleConstant {
	
	/**
	 * 售后缴费明细key
	 */
	public static final String REDIS_AFTERSALE_PAYFEE_DETAIL = "detail:aftersale";
	/**
	 * 更新售后缴费明细key
	 */
	public static final String REDIS_UPDATE_AFTERSALE_PAYFEE_DETAIL = "detail:aftersale:update";
	/**
	 * 待确认
	 */
	public static final String AFTERSALE_AUDIT_FLAG_01 = "20130001";
	/**
	 * 确认无效
	 */
	public static final String AFTERSALE_AUDIT_FLAG_02 = "20130002";
	/**
	 * 确认有效
	 */
	public static final String AFTERSALE_AUDIT_FLAG_03 = "20130003";
	/**
	 * 审核中
	 */
	public static final String AFTERSALE_AUDIT_FLAG_04 = "20130004";
	/**
	 * 审核失败
	 */
	public static final String AFTERSALE_AUDIT_FLAG_05 = "20130005";
	/**
	 * 审核成功
	 */
	public static final String AFTERSALE_AUDIT_FLAG_06 = "20130006";
	/**
	 * 退款中
	 */
	public static final String AFTERSALE_AUDIT_FLAG_07 = "20130007";
	/**
	 * 退款成功
	 */
	public static final String AFTERSALE_AUDIT_FLAG_08 = "20130008";
	/**
	 * 退款失败
	 */
	public static final String AFTERSALE_AUDIT_FLAG_09 = "20130009";
	/**
	 * 已删除
	 */
	public static final String AFTERSALE_AUDIT_FLAG_10 = "20130010";
	/**
	 * 已取消
	 */
	public static final String AFTERSALE_AUDIT_FLAG_11 = "20260001";
	/**
	 * 唯品会银行卡号
	 */
	public static final String AFTERSALE_BANK_CARD_VPH = "391090100100085876";
	/**
	 * 唯品会银行城市编码
	 */
	public static final String AFTERSALE_BANK_CITY_CODE_VPH = "101019001";
	/**
	 * 唯品会开户行名称
	 */
	public static final String AFTERSALE_BANK_NAME_VPH = "广州花城支行";
	/**
	 * 唯品会开户人名称
	 */
	public static final String AFTERSALE_BANK_USER_NAME_VPH = "广州唯品会小额贷款有限公司";
	
	/**
	 * 
	 * 
	 * 
	 * 	@name:银行信息
	 * 
	 * 
	 * 
	 */
	/**
	 * 工商银行
	 */
	public static final String BANK_NAME_01 = "工商银行";
	/**
	 * 工商银行
	 */
	public static final String BANK_SUPPORT_ID_01 = "1";
	/**
	 * 建设银行
	 */
	public static final String BANK_NAME_02 = "建设银行";
	/**
	 * 建设银行
	 */
	public static final String BANK_SUPPORT_ID_02 = "2";
	/**
	 * 农业银行
	 */
	public static final String BANK_NAME_03 = "农业银行";
	/**
	 * 农业银行
	 */
	public static final String BANK_SUPPORT_ID_03 = "3";
	/**
	 * 中国银行
	 */
	public static final String BANK_NAME_04 = "中国银行";
	/**
	 * 中国银行
	 */
	public static final String BANK_SUPPORT_ID_04 = "4";
	/**
	 * 兴业银行
	 */
	public static final String BANK_NAME_05 = "兴业银行";
	/**
	 * 兴业银行
	 */
	public static final String BANK_SUPPORT_ID_05 = "5";
	/**
	 * 招商银行
	 */
	public static final String BANK_NAME_07 = "招商银行";
	/**
	 * 招商银行
	 */
	public static final String BANK_SUPPORT_ID_07 = "7";
	/**
	 * 交通银行
	 */
	public static final String BANK_NAME_08 = "交通银行";
	/**
	 * 交通银行
	 */
	public static final String BANK_SUPPORT_ID_08 = "8";
	/**
	 * 民生银行
	 */
	public static final String BANK_NAME_06 = "民生银行";
	/**
	 * 民生银行
	 */
	public static final String BANK_SUPPORT_ID_06 = "6";
	/**
	 * 北京银行
	 */
	public static final String BANK_NAME_09 = "北京银行";
	/**
	 * 北京银行
	 */
	public static final String BANK_SUPPORT_ID_09 = "9";
	/**
	 * 宁波银行
	 */
	public static final String BANK_NAME_10 = "宁波银行";
	/**
	 * 宁波银行
	 */
	public static final String BANK_SUPPORT_ID_10 = "10";
	
	
	/**
	 * 海金保理银行卡号
	 */
	public static final String AFTERSALE_BANK_CARD_HJBL = "20000028981600003716521";
	/**
	 * 海金保理银行城市编码
	 */
	public static final String AFTERSALE_BANK_CITY_CODE_HJBL = "100000";
	/**
	 * 海金保理开户行名称
	 */
	public static final String AFTERSALE_BANK_NAME_HJBL = "北京银行国兴家园支行";
	/**
	 * 海金保理开户人名称
	 */
	public static final String AFTERSALE_BANK_USER_NAME_HJBL = "北京海金商业保理有限公司";

	/**
	 * 招行银行卡号
	 */
	public static final String AFTERSALE_BANK_CARD_ZSYH = "216089198610001";
	/**
	 * 招行银行城市编码
	 */
	public static final String AFTERSALE_BANK_CITY_CODE_ZSYH = "200000";
	/**
	 * 招行开户行名称
	 */
	public static final String AFTERSALE_BANK_NAME_ZSYH = "招商银行上海分行营业部";
	/**
	 * 招行开户人名称
	 */
	public static final String AFTERSALE_BANK_USER_NAME_ZSYH = "招商银行股份有限公司信用卡中心";
	
	/**
	 * 解决方案key
	 */
	public static final String REDIS_AFTERSALE_SOLUTION_PAYFEE_DETAIL ="detail:solutionafter:update";

	/**
	 * 电子合同确认KEY
	 */
	public static final String ELECTRONIC_CONTRACT_KEY = "ELECTRONIC:CONTRACT:KEY";
	
	
	
	/** 周鑫 2019-01-09  **/
	/**
	 * 汇嘉银行卡号 
	 */
	public static final String AFTERSALE_BANK_CARD_HJ = "77040122000143335";
	/**
	 * 汇嘉银行城市编码 
	 */
	public static final String AFTERSALE_BANK_CITY_CODE_HJ = "101019003";
	/**
	 * 汇嘉开户行名称 
	 */
	public static final String AFTERSALE_BANK_NAME_HJ = "宁波银行北京丰台支行";
	/**
	 * 汇嘉开户人名称   
	 */
	public static final String AFTERSALE_BANK_USER_NAME_HJ = "深圳市汇嘉商业保理有限公司";
	/** 周鑫 2019-01-09  **/
	
}
