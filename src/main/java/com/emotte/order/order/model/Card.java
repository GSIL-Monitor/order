package com.emotte.order.order.model;

import java.math.BigDecimal;

import org.wildhorse.server.core.model.BaseModel;

public class Card extends BaseModel {
	/**
	 * 礼品卡类型查询
	 */
	private static final long serialVersionUID = 1L;
	private Long id; // 卡类型id
	private Long userId; // 绑定人
	private String money; // 卡金额
	private String image; // 卡图片
	private String name; // 卡名称
	private String describe; // 卡描述
	private String validTime; // 卡有效期
	private String cardNum; // 卡号
	private String BuyTime; // 购买日期
	private Integer currentStatus; // 卡状态(1未销售，2已登记，3已激活 ，4已绑定，5已过期)
	private String statusName; // 卡状态汉字
	private String lifeOverTime; // 卡有效日期
	private BigDecimal balance; // 卡余额
	private String activateTime; // 绑定时间
	private String range; // 适应品类范围
	private String area; // 地区范围
	private String headImage; // 背景大图图片地址
	private String cityCode; // 城市码
	private String productType; // 品类
	private Long accountId;// 订单id
	private String lifeTime; // 有效期
	private Integer judgeType; // 用于判断查询礼品卡列表或支付时可用的礼品卡列表 1 礼品卡列表, 2 支付时可用的礼品卡列表
	private Integer valid;
	private String presentation; // 礼品卡介绍
	private Integer type;
	private BigDecimal giftBalance;

	public BigDecimal getGiftBalance() {
		return giftBalance;
	}

	public void setGiftBalance(BigDecimal giftBalance) {
		this.giftBalance = giftBalance;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public Integer getJudgeType() {
		return judgeType;
	}

	public void setJudgeType(Integer judgeType) {
		this.judgeType = judgeType;
	}

	public String getLifeTime() {
		return lifeTime;
	}

	public void setLifeTime(String lifeTime) {
		this.lifeTime = lifeTime;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getLifeOverTime() {
		return lifeOverTime;
	}

	public void setLifeOverTime(String lifeOverTime) {
		this.lifeOverTime = lifeOverTime;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getActivateTime() {
		return activateTime;
	}

	public void setActivateTime(String activateTime) {
		this.activateTime = activateTime;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public Integer getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(Integer currentStatus) {
		this.currentStatus = currentStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getBuyTime() {
		return BuyTime;
	}

	public void setBuyTime(String buyTime) {
		BuyTime = buyTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
