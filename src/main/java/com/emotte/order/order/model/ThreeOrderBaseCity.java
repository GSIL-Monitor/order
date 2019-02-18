package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

public class ThreeOrderBaseCity extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long pid;
	private String code;
	private String org_code;
	private String name;
	private String short_name;
	private String levels;
	private String tel;
	private String spelling;
	private String valid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOrg_code() {
		return org_code;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public String getLevels() {
		return levels;
	}
	public void setLevels(String levels) {
		this.levels = levels;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getSpelling() {
		return spelling;
	}
	public void setSpelling(String spelling) {
		this.spelling = spelling;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
}
