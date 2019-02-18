package com.emotte.order.order.model;
import org.wildhorse.server.core.model.BaseModel;
public class AccountPayBankcard extends BaseModel{ 
	/**
	  主键ID
	*/
	private Long id;

	/**
	  银行卡号
	*/
	private String bankCard;

	/**
	  开户行银行名
	*/
	private String bankName;

	/**
	  银行卡姓名
	*/
	private String bankUserName;

	/**
	  用户ID
	*/
	private Long userId;

	/**
	  退款结算单ID
	*/
	private Long accountPayId;

	/**
	  开户行所在城市
	*/
	private String city;

	/**
	  开户行支行详细信息
	*/
	private String bankDetail;

	/**
	  是否老数据
	*/
	private String isOldData;
	//银行类型ID
	private Long bankSupportId;
	//主行名称
	private String bankMainName;
	// 是否有效 1 有效 ， 2 无效 : valid
	private Integer valid;
	
	public Long getId(){
		return id; 
	}

	public void setId(Long id){
		this.id = id; 
	}

	public String getBankCard(){
		return bankCard; 
	}

	public void setBankCard(String bankCard){
		this.bankCard = bankCard; 
	}

	public String getBankName(){
		return bankName; 
	}

	public void setBankName(String bankName){
		this.bankName = bankName; 
	}

	public String getBankUserName(){
		return bankUserName; 
	}

	public void setBankUserName(String bankUserName){
		this.bankUserName = bankUserName; 
	}

	public Long getUserId(){
		return userId; 
	}

	public void setUserId(Long userId){
		this.userId = userId; 
	}

	public Long getAccountPayId(){
		return accountPayId; 
	}

	public void setAccountPayId(Long accountPayId){
		this.accountPayId = accountPayId; 
	}

	public String getCity(){
		return city; 
	}

	public void setCity(String city){
		this.city = city; 
	}

	public String getBankDetail(){
		return bankDetail; 
	}

	public void setBankDetail(String bankDetail){
		this.bankDetail = bankDetail; 
	}

	public String getIsOldData(){
		return isOldData; 
	}

	public void setIsOldData(String isOldData){
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

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	
}