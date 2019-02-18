package com.emotte.order.order.model;

public class ThreeOrderCard {
	
	private Long cardId;
	
	private String cardBalance;
	
	private String cardNumb;
	
	private String validTime;

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public String getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(String cardBalance) {
		this.cardBalance = cardBalance;
	}

	public String getCardNumb() {
		return cardNumb;
	}

	public void setCardNumb(String cardNumb) {
		this.cardNumb = cardNumb;
	}

	public String getValidTime() {
		return validTime;
	}

	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}

}
