package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : T_ACCOUNT_PAY_BANKCARD
 */
public class PayBankcard extends BaseModel {

	// 主键ID : ID
	private Long id;

	// 银行卡号 : BANK_CARD
	private String bankCard;

	// 开户行银行名 : BANK_NAME
	private String bankName;

	// 银行卡姓名 : BANK_USER_NAME
	private String bankUserName;

	// 用户ID : USER_ID
	private Long userId;

	// 退款结算单ID : ACCOUNT_PAY_ID
	private Long accountPayId;

	// 开户行所在城市 : CITY
	private String city;

	// 开户行支行详细信息 : BANK_DETAIL
	private String bankDetail;

	// 是否老数据 : IS_OLD_DATA
	private String isOldData;

	// 银行类型ID : BANK_SUPPORT_ID
	private Long bankSupportId;

	// 主行名称 : BANK_MAIN_NAME
	private String bankMainName;

	// 是否有效:1有效，2无效 : VALID
	private Long valid;

	// 创建人 : CREATE_BY
	private Long createBy;

	// 更新人 : UPDATE_BY
	private Long updateBy;

	// 版本号 : VERSION
	private Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getAccountPayId() {
		return accountPayId;
	}

	public void setAccountPayId(Long accountPayId) {
		this.accountPayId = accountPayId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBankDetail() {
		return bankDetail;
	}

	public void setBankDetail(String bankDetail) {
		this.bankDetail = bankDetail;
	}

	public String getIsOldData() {
		return isOldData;
	}

	public void setIsOldData(String isOldData) {
		this.isOldData = isOldData;
	}

	public Long getBankSupportId() {
		return bankSupportId;
	}

	public void setBankSupportId(Long bankSupportId) {
		this.bankSupportId = bankSupportId;
	}

	public String getBankMainName() {
		return bankMainName;
	}

	public void setBankMainName(String bankMainName) {
		this.bankMainName = bankMainName;
	}

	public Long getValid() {
		return valid;
	}

	public void setValid(Long valid) {
		this.valid = valid;
	}

	@Override
	public Long getCreateBy() {
		return createBy;
	}

	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	@Override
	public Long getUpdateBy() {
		return updateBy;
	}

	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	public void setVersion(Long version) {
		this.version = version;
	}

}