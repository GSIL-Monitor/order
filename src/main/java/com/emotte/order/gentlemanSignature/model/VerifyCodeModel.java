package com.emotte.order.gentlemanSignature.model;

import java.util.Arrays;

public class VerifyCodeModel {
	private String mobile;
	private String imgCode;
	private String imgKey;
	private String verifyCode;
	private String type;
	private String templet;
	private String channel;
	private String system;
	private String rate;
	private String source;
	private String hopeSendTime;
	private Object[] params;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getImgCode() {
		return imgCode;
	}
	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}
	public String getImgKey() {
		return imgKey;
	}
	public void setImgKey(String imgKey) {
		this.imgKey = imgKey;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTemplet() {
		return templet;
	}
	public void setTemplet(String templet) {
		this.templet = templet;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getSystem() {
		return system;
	}
	public void setSystem(String system) {
		this.system = system;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getHopeSendTime() {
		return hopeSendTime;
	}
	public void setHopeSendTime(String hopeSendTime) {
		this.hopeSendTime = hopeSendTime;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
	@Override
	public String toString() {
		return "VerifyCodeModel [mobile=" + mobile + ", imgCode=" + imgCode + ", imgKey=" + imgKey + ", verifyCode="
				+ verifyCode + ", type=" + type + ", templet=" + templet + ", channel=" + channel + ", system=" + system
				+ ", rate=" + rate + ", source=" + source + ", hopeSendTime=" + hopeSendTime + ", params="
				+ Arrays.toString(params) + "]";
	}
	
	
	
	
}
