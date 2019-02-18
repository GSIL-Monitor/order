package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_product
 * 
 * 
 * @author army
 */
public class Product extends BaseModel {

	// 主键 最小商品id : id
	private Long id;

	// 是商品 还是服务 1 商品 2 延续性服务 3 非延续性服务 : is_prod_serve
	private Integer isProdServe;

	// t_product_brand品牌表id : brand_id
	private Long brandId;

	// 商品类型id : type_id
	private Long typeId;

	// 分类表code码 : category_code
	private String categoryCode;

	// 商品名 : name
	private String name;

	// 条形码 : bar_code
	private String barCode;

	// 二维码图片路径 : two_dimensional_code
	private String twoDimensionalCode;

	// 商品来源的公司 1自营 2 联营 3第三方 : source
	private Integer source;

	// 图片地址 : product_img
	private String productImg;

	// 创建人 系统登录表的id : create_by
	private Long createBy;

	// : update_by
	private Long updateBy;

	// 是否有效 1 有效 ， 2 无效 : valid
	private Integer valid;

	// 版本号 : version
	private Long version;

	// 溯源码 315溯源网20位数字英文混编溯源查询码 : source_code
	private String sourceCode;

	//辅助  城市商品关联表
	private CityProduct cityProduct;
	
	//商品规格的 名称 如：颜色  尺寸
	private String typeSpecName;
	
	//辅助属性城市商品城市code
	private String cityCode;
	
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

	//商品规格值    红色    M L   500ml
	private String typeSpecValue;
	


	/**
	 * constitution
	 *
	 */

	/**
	 * 主键 最小商品id : id
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 主键 最小商品id : id
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 是商品 还是服务 1 商品 2 延续性服务 3 非延续性服务 : is_prod_serve
	 * 
	 * @return
	 */
	public Integer getIsProdServe() {
		return isProdServe;
	}

	/**
	 * 是商品 还是服务 1 商品 2 延续性服务 3 非延续性服务 : is_prod_serve
	 * 
	 * @return
	 */
	public void setIsProdServe(Integer isProdServe) {
		this.isProdServe = isProdServe;
	}

	/**
	 * t_product_brand品牌表id : brand_id
	 * 
	 * @return
	 */
	public Long getBrandId() {
		return brandId;
	}

	/**
	 * t_product_brand品牌表id : brand_id
	 * 
	 * @return
	 */
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	/**
	 * 商品类型id : type_id
	 * 
	 * @return
	 */
	public Long getTypeId() {
		return typeId;
	}

	/**
	 * 商品类型id : type_id
	 * 
	 * @return
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	/**
	 * 分类表code码 : category_code
	 * 
	 * @return
	 */
	public String getCategoryCode() {
		return categoryCode;
	}

	/**
	 * 分类表code码 : category_code
	 * 
	 * @return
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/**
	 * 商品名 : name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 商品名 : name
	 * 
	 * @return
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 条形码 : bar_code
	 * 
	 * @return
	 */
	public String getBarCode() {
		return barCode;
	}

	/**
	 * 条形码 : bar_code
	 * 
	 * @return
	 */
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	/**
	 * 二维码图片路径 : two_dimensional_code
	 * 
	 * @return
	 */
	public String getTwoDimensionalCode() {
		return twoDimensionalCode;
	}

	/**
	 * 二维码图片路径 : two_dimensional_code
	 * 
	 * @return
	 */
	public void setTwoDimensionalCode(String twoDimensionalCode) {
		this.twoDimensionalCode = twoDimensionalCode;
	}

	/**
	 * 商品来源的公司 1自营 2 联营 3第三方 : source
	 * 
	 * @return
	 */
	public Integer getSource() {
		return source;
	}

	/**
	 * 商品来源的公司 1自营 2 联营 3第三方 : source
	 * 
	 * @return
	 */
	public void setSource(Integer source) {
		this.source = source;
	}

	/**
	 * 图片地址 : product_img
	 * 
	 * @return
	 */
	public String getProductImg() {
		return productImg;
	}

	/**
	 * 图片地址 : product_img
	 * 
	 * @return
	 */
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	/**
	 * 创建人 系统登录表的id : create_by
	 * 
	 * @return
	 */
	@Override
	public Long getCreateBy() {
		return createBy;
	}

	/**
	 * 创建人 系统登录表的id : create_by
	 * 
	 * @return
	 */
	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	/**
	 * : update_by
	 * 
	 * @return
	 */
	@Override
	public Long getUpdateBy() {
		return updateBy;
	}

	/**
	 * : update_by
	 * 
	 * @return
	 */
	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * 是否有效 1 有效 ， 2 无效 : valid
	 * 
	 * @return
	 */
	public Integer getValid() {
		return valid;
	}

	/**
	 * 是否有效 1 有效 ， 2 无效 : valid
	 * 
	 * @return
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
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
	 * 溯源码 315溯源网20位数字英文混编溯源查询码 : source_code
	 * 
	 * @return
	 */
	public String getSourceCode() {
		return sourceCode;
	}

	/**
	 * 溯源码 315溯源网20位数字英文混编溯源查询码 : source_code
	 * 
	 * @return
	 */
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}


	public CityProduct getCityProduct() {
		return cityProduct;
	}

	public void setCityProduct(CityProduct cityProduct) {
		this.cityProduct = cityProduct;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	

}
