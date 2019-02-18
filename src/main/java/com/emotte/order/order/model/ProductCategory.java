package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order_serial
 * 
 * 
 * @author chesl
 */
public class ProductCategory  extends BaseModel {
	
	//主键id
	private Long id;
	
	//父id
	private Long fid;
	
	//商品类型表id
	private Long productTypeId;
	
	//分类code码
	private String code;
	
	//分类组织码
	private String orgCode;
	
	//中文分类名
	private String cname;
	
	//英文分类名
	private String ename;
	
	//是否显示  1显示   2 不显示
	private Long display;
	
	//是否有效  1 有效  ， 2 无效
	private Long valid;
	
	//判断商品分类类型   1:单次服务，2:延续性服务，3:商品，
	private int cateType;
	private String cityCode;
	
	private int length; // code长度
	
	
	
	
	
	
	public Long getDisplay() {
		return display;
	}
	public void setDisplay(Long display) {
		this.display = display;
	}
	public Long getValid() {
		return valid;
	}
	public void setValid(Long valid) {
		this.valid = valid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFid() {
		return fid;
	}
	public void setFid(Long fid) {
		this.fid = fid;
	}
	public Long getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	
	public int getCateType() {
		return cateType;
	}
	public void setCateType(int cateType) {
		this.cateType = cateType;
	}
	
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	
	
}
