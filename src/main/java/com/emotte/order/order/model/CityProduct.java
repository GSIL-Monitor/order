package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_product_city
 * 
 * 
 * @author army
 */
public class CityProduct extends BaseModel {

	// 主键 : id
	private Long id;

	// 商品编码 唯一值 唯一编码 sku : product_code
	private String productCode;
	
	private String productName;

	// 商品id : product_id
	private Long productId;

	// 商品所属城市 创建商品人所在城市 待商议 : city_code
	private String cityCode;

	// 市场价 : market_price
	private java.math.BigDecimal marketPrice;
	// 拼接价格
	private String priceText;
	// 是否参加解决方案 1是 2否 : is_solution
	private Integer isSolution;

	// 解决方案起订数量 : solution_count
	private Integer solutionCount;

	// 商品的上架 和 下架 : status
	private Integer status;

	// 派送区域 : delivery_area
	private String deliveryArea;

	// 安全库存 : safe_repertory
	private Integer safeRepertory;

	// 可用库存 : usable_repertory
	private Integer usableRepertory;

	// 在途库存 : transportation_repertory
	private Integer transportationRepertory;

	// 全网库存 : full_network_repertory
	private Integer fullNetworkRepertory;

	// 商品详情 : detail
	private String detail;

	// 创建人 系统登录表的id : create_by
	private Long createBy;

	// 修改人 : update_by
	private Long updateBy;

	// 是否有效 1 有效 ， 2 无效 : valid
	private Integer valid;

	// 版本号 : version
	private Long version;
	
	//辅助 商品表
	private Product product; 
	
	
	//商品分类code码
	private String code;
	
	// 类型:1单次服务
	private int cateType;
	// 商品分类码
	private String categoryCode;
	
	// 商品图片地址
	private String productImg;
	
	private String typeSpecName; // 规格名
	private String typeSpecValue; // 规格值
	private String dictCode ; // 价格类型
	private String productInventoryId ;// 仓库Id
	private String productPriceType; // 商品价格体系
	private String productPriceTypeText; // 商品价格体系名称
	private String productUnit; // 商品单位
	private String productUnitText; // 商品单位名称
	private String productSpec; // 商品规格
	private String productSpecText; // 商品规格名称
	private String typeSpecId;// 规格类型ID(t_product_type_spec)
	

	/**
	 * constitution
	 *
	 */

	/**
	 * 主键 : id
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 主键 : id
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 商品编码 唯一值 唯一编码 sku : product_code
	 * 
	 * @return
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * 商品编码 唯一值 唯一编码 sku : product_code
	 * 
	 * @return
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * 商品id : product_id
	 * 
	 * @return
	 */
	public Long getProductId() {
		return productId;
	}

	/**
	 * 商品id : product_id
	 * 
	 * @return
	 */
	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
	 * 商品所属城市 创建商品人所在城市 待商议 : city_code
	 * 
	 * @return
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * 商品所属城市 创建商品人所在城市 待商议 : city_code
	 * 
	 * @return
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * 市场价 : market_price
	 * 
	 * @return
	 */
	public java.math.BigDecimal getMarketPrice() {
		return marketPrice;
	}

	/**
	 * 市场价 : market_price
	 * 
	 * @return
	 */
	public void setMarketPrice(java.math.BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}


	public String getPriceText() {
		return priceText;
	}

	public void setPriceText(String priceText) {
		this.priceText = priceText;
	}

	/**
	 * 是否参加解决方案 1是 2否 : is_solution
	 * 
	 * @return
	 */
	public Integer getIsSolution() {
		return isSolution;
	}

	/**
	 * 是否参加解决方案 1是 2否 : is_solution
	 * 
	 * @return
	 */
	public void setIsSolution(Integer isSolution) {
		this.isSolution = isSolution;
	}

	/**
	 * 解决方案起订数量 : solution_count
	 * 
	 * @return
	 */
	public Integer getSolutionCount() {
		return solutionCount;
	}

	/**
	 * 解决方案起订数量 : solution_count
	 * 
	 * @return
	 */
	public void setSolutionCount(Integer solutionCount) {
		this.solutionCount = solutionCount;
	}

	/**
	 * 商品的上架 和 下架 : status
	 * 
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * 商品的上架 和 下架 : status
	 * 
	 * @return
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * 派送区域 : delivery_area
	 * 
	 * @return
	 */
	public String getDeliveryArea() {
		return deliveryArea;
	}

	/**
	 * 派送区域 : delivery_area
	 * 
	 * @return
	 */
	public void setDeliveryArea(String deliveryArea) {
		this.deliveryArea = deliveryArea;
	}

	/**
	 * 安全库存 : safe_repertory
	 * 
	 * @return
	 */
	public Integer getSafeRepertory() {
		return safeRepertory;
	}

	/**
	 * 安全库存 : safe_repertory
	 * 
	 * @return
	 */
	public void setSafeRepertory(Integer safeRepertory) {
		this.safeRepertory = safeRepertory;
	}

	/**
	 * 可用库存 : usable_repertory
	 * 
	 * @return
	 */
	public Integer getUsableRepertory() {
		return usableRepertory;
	}

	/**
	 * 可用库存 : usable_repertory
	 * 
	 * @return
	 */
	public void setUsableRepertory(Integer usableRepertory) {
		this.usableRepertory = usableRepertory;
	}

	/**
	 * 在途库存 : transportation_repertory
	 * 
	 * @return
	 */
	public Integer getTransportationRepertory() {
		return transportationRepertory;
	}

	/**
	 * 在途库存 : transportation_repertory
	 * 
	 * @return
	 */
	public void setTransportationRepertory(Integer transportationRepertory) {
		this.transportationRepertory = transportationRepertory;
	}

	/**
	 * 全网库存 : full_network_repertory
	 * 
	 * @return
	 */
	public Integer getFullNetworkRepertory() {
		return fullNetworkRepertory;
	}

	/**
	 * 全网库存 : full_network_repertory
	 * 
	 * @return
	 */
	public void setFullNetworkRepertory(Integer fullNetworkRepertory) {
		this.fullNetworkRepertory = fullNetworkRepertory;
	}

	/**
	 * 商品详情 : detail
	 * 
	 * @return
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 商品详情 : detail
	 * 
	 * @return
	 */
	public void setDetail(String detail) {
		this.detail = detail;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCateType() {
		return cateType;
	}

	public void setCateType(int cateType) {
		this.cateType = cateType;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getProductInventoryId() {
		return productInventoryId;
	}

	public void setProductInventoryId(String productInventoryId) {
		this.productInventoryId = productInventoryId;
	}

	public String getProductPriceType() {
		return productPriceType;
	}

	public void setProductPriceType(String productPriceType) {
		this.productPriceType = productPriceType;
	}

	public String getProductPriceTypeText() {
		return productPriceTypeText;
	}

	public void setProductPriceTypeText(String productPriceTypeText) {
		this.productPriceTypeText = productPriceTypeText;
	}

	public String getProductUnit() {
		return productUnit;
	}

	public void setProductUnit(String productUnit) {
		this.productUnit = productUnit;
	}

	public String getProductUnitText() {
		return productUnitText;
	}

	public void setProductUnitText(String productUnitText) {
		this.productUnitText = productUnitText;
	}

	public String getProductSpec() {
		return productSpec;
	}

	public void setProductSpec(String productSpec) {
		this.productSpec = productSpec;
	}

	public String getProductSpecText() {
		return productSpecText;
	}

	public void setProductSpecText(String productSpecText) {
		this.productSpecText = productSpecText;
	}

	public String getTypeSpecId() {
		return typeSpecId;
	}

	public void setTypeSpecId(String typeSpecId) {
		this.typeSpecId = typeSpecId;
	}

	
	
}
