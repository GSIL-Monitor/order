package com.emotte.order.order.model;

import java.io.Serializable;
/**
 * 服务人员级别表
 * @author 李登辉
 *
 * 2017年11月13日下午2:39:15
 */
public class PersonaLevel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Long id;
	String levelName;
	Long typeCode;
	public Long getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(Long typeCode) {
		this.typeCode = typeCode;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
}
