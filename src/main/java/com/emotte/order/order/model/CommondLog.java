package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

public class CommondLog extends BaseModel{

	//指令表ID
	private Long id ;
	//指令的key
	private String keyConstent;
	//指令的类型
	private String typeConstent;
	//指令的类型值
	private String typeValue;
	//是否已执行，1 是，2否
	private Integer flag;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKeyConstent() {
		return keyConstent;
	}
	public void setKeyConstent(String keyConstent) {
		this.keyConstent = keyConstent;
	}
	public String getTypeConstent() {
		return typeConstent;
	}
	public void setTypeConstent(String typeConstent) {
		this.typeConstent = typeConstent;
	}
	public String getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
	
}
