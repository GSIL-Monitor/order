package com.emotte.order.order.model;

import java.math.BigDecimal;
import java.util.List;

import org.wildhorse.server.core.model.BaseModel;

public class ThreeOrderInProductModel extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 产品id
	 */
	private String productId;
	
	/**
	 * 产品code 
	 */
	private String product_code;
	
	/**
	 * 产品名称 
	 */
	private String product_name;
	
	/**
	 * 服务类型 
	 */
	private String serve_type;
	
	/**
	 * 三级分类
	 */
	private String category_code;
	
	/**
	 * 城市code 
	 */
	private String city_code;
	
	/**
	 * 价格
	 */
	private BigDecimal price;
	
	/**
	 * 价格体系 
	 */
	private String dict_code;
	
	/**
	 * 产品规格名 ：颜色
	 */
	private String typeSpecName;

	/**
	 * 产品规格值 ：红色
	 */
	private String typeSpecValue;
	
	/**
	 * 客户Id,用于检索产品的价格体系 
	 */
	private Long user_id;
	
	/**
	 * 是否VIP 1是 
	 */
	private Integer is_vip;
	/**
	 * 是否大客户 1是 
	 */
	private Integer is_big_cust;
	
	/**
	 * 商品数量 
	 */
	private Integer product_count;
	
	/**
	 * 商品重算商品用，用于存放前台传人的product_code集合 
	 */
	private String product_code_count_json;
	
	/**
	 * 商品单价乘以数量 
	 */
	private BigDecimal product_amount;
	
	/**
	 * 商品图片 
	 */
	private String product_img;
	
	/**
	 * 商品最小单位起订数 
	 */
	private Integer minUnitCount;
	
	/***
	 * 产品的最小单位，只有服务型产品才会用到 
	 */
	private String leastUnit;
	/**
	 * 用于接受前台传入的商品列表
	 */
	List<ThreeOrderInProductPriceDiscMo> priceDiscList ;
	
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getServe_type() {
		return serve_type;
	}

	public void setServe_type(String serve_type) {
		this.serve_type = serve_type;
	}

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDict_code() {
		return dict_code;
	}

	public void setDict_code(String dict_code) {
		this.dict_code = dict_code;
	}

	public String getTypeSpecName() {
		return typeSpecName;
	}

	public void setTypeSpecName(String typeSpecName) {
		this.typeSpecName = typeSpecName;
	}

	public String getTypeSpecValue() {
		return typeSpecValue;
	}

	public void setTypeSpecValue(String typeSpecValue) {
		this.typeSpecValue = typeSpecValue;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Integer getIs_vip() {
		return is_vip;
	}

	public void setIs_vip(Integer is_vip) {
		this.is_vip = is_vip;
	}

	public Integer getIs_big_cust() {
		return is_big_cust;
	}

	public void setIs_big_cust(Integer is_big_cust) {
		this.is_big_cust = is_big_cust;
	}

	public List<ThreeOrderInProductPriceDiscMo> getPriceDiscList() {
		return priceDiscList;
	}

	public void setPriceDiscList(List<ThreeOrderInProductPriceDiscMo> priceDiscList) {
		this.priceDiscList = priceDiscList;
	}

	public Integer getProduct_count() {
		return product_count;
	}

	public void setProduct_count(Integer product_count) {
		this.product_count = product_count;
	}

	public String getProduct_code_count_json() {
		return product_code_count_json;
	}

	public void setProduct_code_count_json(String product_code_count_json) {
		this.product_code_count_json = product_code_count_json;
	}

	public BigDecimal getProduct_amount() {
		return product_amount;
	}

	public void setProduct_amount(BigDecimal product_amount) {
		this.product_amount = product_amount;
	}

	public String getProduct_img() {
		return product_img;
	}

	public void setProduct_img(String product_img) {
		this.product_img = product_img;
	}

	public Integer getMinUnitCount() {
		return minUnitCount;
	}

	public void setMinUnitCount(Integer minUnitCount) {
		this.minUnitCount = minUnitCount;
	}

	public String getLeastUnit() {
		return leastUnit;
	}

	public void setLeastUnit(String leastUnit) {
		this.leastUnit = leastUnit;
	}
}
