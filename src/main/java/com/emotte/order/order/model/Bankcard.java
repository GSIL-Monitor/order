package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_user_bankcard
 * 
 * 
 * @author army
 */
public class Bankcard extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 主键ID : id
	private Long id;

	// 客户表主键ID
	private Long custId;

	// 开户行 : bank_name
	private String bankName;

	// 支行信息 : bank_branch
	private String bankBranch;

	// 用户名 : card_name
	private String cardName;

	// 银行卡号 : bank_card
	private String bankCard;

	// 是否默认（1是；2不是） : is_default
	private Integer isDefault;

	// 创建人 : create_by
	private Long createBy;

	// 创建时间 : create_time
	private String createTime;

	// 修改人 : update_by
	private Long updateBy;

	// 修改时间 : update_time
	private String updateTime;

	// 版本号 : version
	private Long version;

	// 是否有效 1有效；2无效 : valid
	private String valid;

	private String bankCity;
	private Integer flag; // 状态（1正常；2修改覆盖；3删除）
	private String bankNumber; // 无隐藏的银行卡号
	private String icon; // 银行卡图标地址
	private Long bankSupportId;// t_bank_supportId表id
	private String platform;// 平台
	private String reservedPhone;
	private String idCardNum;

	public String getReservedPhone() {
		return reservedPhone;
	}

	public void setReservedPhone(String reservedPhone) {
		this.reservedPhone = reservedPhone;
	}

	public String getIdCardNum() {
		return idCardNum;
	}

	public void setIdCardNum(String idCardNum) {
		this.idCardNum = idCardNum;
	}

	/**
	 * constitution
	 *
	 */

	public String getBankNumber() {
		return bankNumber;
	}

	public Long getBankSupportId() {
		return bankSupportId;
	}

	public void setBankSupportId(Long bankSupportId) {
		this.bankSupportId = bankSupportId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	/**
	 * 主键ID : id
	 * 
	 * @return
	 */

	public Long getId() {
		return id;
	}

	/**
	 * 主键ID : id
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	/**
	 * 开户行 : bank_name
	 * 
	 * @return
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * 开户行 : bank_name
	 * 
	 * @return
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * 支行信息 : bank_branch
	 * 
	 * @return
	 */
	public String getBankBranch() {
		return bankBranch;
	}

	/**
	 * 支行信息 : bank_branch
	 * 
	 * @return
	 */
	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	/**
	 * 用户名 : card_name
	 * 
	 * @return
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * 用户名 : card_name
	 * 
	 * @return
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	/**
	 * 银行卡号 : bank_card
	 * 
	 * @return
	 */
	public String getBankCard() {
		return bankCard;
	}

	/**
	 * 银行卡号 : bank_card
	 * 
	 * @return
	 */
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	/**
	 * 是否默认（1是；2不是） : is_default
	 * 
	 * @return
	 */
	public Integer getIsDefault() {
		return isDefault;
	}

	/**
	 * 是否默认（1是；2不是） : is_default
	 * 
	 * @return
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * 创建人 : create_by
	 * 
	 * @return
	 */
	@Override
	public Long getCreateBy() {
		return createBy;
	}

	/**
	 * 创建人 : create_by
	 * 
	 * @return
	 */
	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	/**
	 * 创建时间 : create_time
	 * 
	 * @return
	 */
	@Override
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间 : create_time
	 * 
	 * @return
	 */
	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * 修改人 : update_by
	 * 
	 * @return
	 */
	@Override
	public Long getUpdateBy() {
		return updateBy;
	}

	/**
	 * 修改人 : update_by
	 * 
	 * @return
	 */
	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * 修改时间 : update_time
	 * 
	 * @return
	 */
	@Override
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间 : update_time
	 * 
	 * @return
	 */
	@Override
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 版本号 : version
	 * 
	 * @return
	 */
	@Override
	public Long getVersion() {
		return version;
	}

	/**
	 * 版本号 : version
	 * 
	 * @return
	 */
	@Override
	public void setVersion(Long version) {
		this.version = version;
	}

	/**
	 * 是否有效 1有效；2无效 : valid
	 * 
	 * @return
	 */
	public String getValid() {
		return valid;
	}

	/**
	 * 是否有效 1有效；2无效 : valid
	 * 
	 * @return
	 */
	public void setValid(String valid) {
		this.valid = valid;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}
}
