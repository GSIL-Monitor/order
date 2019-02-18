package com.emotte.order.order.model;
import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_solution_cust_solution_plan
 */
public class CustSolutionPlan extends BaseModel {

 	// 排期ID : ID
	private Long id;
	
	
 	// 套餐ID : CUST_SOLUTION_ITEM_ID
	private Long custSolutionItemId;
	
	
 	// 解决方案ID : SOLUTION_CUST__SOLUTION_ID
	private Long solutionCustSolutionId;
	
	
 	// 订单ID : ORDER_ID
	private Long orderId;
	
	
 	// 类型ID : TYPE_ID
	private Long typeId;
	
	
 	// 送货时间 : SERVICE_DATE
	private java.util.Date serviceDate;
	
	
 	// 接收人名称 : CUSTOM_NAME
	private String customName;
	
	
 	// 接收人电话 : CUSTOM_PHONE
	private String customPhone;
	
	
 	// 接收人省 : CUSTOM_PROVINCE
	private String customProvince;
	
	
 	// 接收人市 : CUSTOM_CITY
	private String customCity;
	
	
 	// 接收人区 : CUSTOM_AREA
	private String customArea;
	
	
 	// 客户地址 : CUSTOM_ADDRESS
	private String customAddress;
	
	
 	// 祝福语 : BLESSING
	private String blessing;
	
	
 	// 备注 : REMARK
	private String remark;
	
	
 	// 创建时间 : CREATE_TIME
	private String createTime;
	
	
 	// 创建者 : CREATE_BY
	private Long createBy;
	
	
 	// 修改时间 : UPDATE_TIME
	private String updateTime;
	
	
 	// 修改人 : UPDATE_BY
	private Long updateBy;
	
	
 	// 版本号 : VERSION
	private Long version;
	
	
 	// 市场渠道来源编码 : MCODE
	private String mcode;
	
	
 	// 排期删除标识 ：1删除 2 正常 3 暂停  : DEL_FLAG
	private Integer delFlag;
	
	
 	// 单次配送数量 : QTY_ONCE
	private Integer qtyOnce;
	
	
 	// 商品单价 : ONCE_PRIRCE
	private Double oncePrirce;
	
	
 	// 接收人地区编码 : RECEIVER_CITY_CODE
	private String receiverCityCode;
	
	
 	// 经度 : CUSTOM_LONGITUDE
	private Double customLongitude;
	
	
 	// 纬度 : CUSTOM_LATITUDE
	private Double customLatitude;
	
	
 	// 是否老数据 : IS_OLD_DATA
	private String isOldData;
	
	
 	// 排期删除标识 ：1删除 2 正常 3 暂停  : TEMP_DELFLAG
	private Integer tempDelflag;
	
	private Integer solutionStatus;


	public Integer getSolutionStatus() {
		return solutionStatus;
	}


	public void setSolutionStatus(Integer solutionStatus) {
		this.solutionStatus = solutionStatus;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getCustSolutionItemId() {
		return custSolutionItemId;
	}


	public void setCustSolutionItemId(Long custSolutionItemId) {
		this.custSolutionItemId = custSolutionItemId;
	}


	public Long getSolutionCustSolutionId() {
		return solutionCustSolutionId;
	}


	public void setSolutionCustSolutionId(Long solutionCustSolutionId) {
		this.solutionCustSolutionId = solutionCustSolutionId;
	}


	public Long getOrderId() {
		return orderId;
	}


	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


	public Long getTypeId() {
		return typeId;
	}


	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}


	public java.util.Date getServiceDate() {
		return serviceDate;
	}


	public void setServiceDate(java.util.Date serviceDate) {
		this.serviceDate = serviceDate;
	}


	public String getCustomName() {
		return customName;
	}


	public void setCustomName(String customName) {
		this.customName = customName;
	}


	public String getCustomPhone() {
		return customPhone;
	}


	public void setCustomPhone(String customPhone) {
		this.customPhone = customPhone;
	}


	public String getCustomProvince() {
		return customProvince;
	}


	public void setCustomProvince(String customProvince) {
		this.customProvince = customProvince;
	}


	public String getCustomCity() {
		return customCity;
	}


	public void setCustomCity(String customCity) {
		this.customCity = customCity;
	}


	public String getCustomArea() {
		return customArea;
	}


	public void setCustomArea(String customArea) {
		this.customArea = customArea;
	}


	public String getCustomAddress() {
		return customAddress;
	}


	public void setCustomAddress(String customAddress) {
		this.customAddress = customAddress;
	}


	public String getBlessing() {
		return blessing;
	}


	public void setBlessing(String blessing) {
		this.blessing = blessing;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	@Override
	public String getCreateTime() {
		return createTime;
	}


	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


	@Override
	public Long getCreateBy() {
		return createBy;
	}


	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}


	@Override
	public String getUpdateTime() {
		return updateTime;
	}


	@Override
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}


	@Override
	public Long getUpdateBy() {
		return updateBy;
	}


	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}


	@Override
	public Long getVersion() {
		return version;
	}


	@Override
	public void setVersion(Long version) {
		this.version = version;
	}


	public String getMcode() {
		return mcode;
	}


	public void setMcode(String mcode) {
		this.mcode = mcode;
	}


	public Integer getDelFlag() {
		return delFlag;
	}


	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}


	public Integer getQtyOnce() {
		return qtyOnce;
	}


	public void setQtyOnce(Integer qtyOnce) {
		this.qtyOnce = qtyOnce;
	}


	public Double getOncePrirce() {
		return oncePrirce;
	}


	public void setOncePrirce(Double oncePrirce) {
		this.oncePrirce = oncePrirce;
	}


	public String getReceiverCityCode() {
		return receiverCityCode;
	}


	public void setReceiverCityCode(String receiverCityCode) {
		this.receiverCityCode = receiverCityCode;
	}


	public Double getCustomLongitude() {
		return customLongitude;
	}


	public void setCustomLongitude(Double customLongitude) {
		this.customLongitude = customLongitude;
	}


	public Double getCustomLatitude() {
		return customLatitude;
	}


	public void setCustomLatitude(Double customLatitude) {
		this.customLatitude = customLatitude;
	}


	public String getIsOldData() {
		return isOldData;
	}


	public void setIsOldData(String isOldData) {
		this.isOldData = isOldData;
	}


	public Integer getTempDelflag() {
		return tempDelflag;
	}


	public void setTempDelflag(Integer tempDelflag) {
		this.tempDelflag = tempDelflag;
	}
}
	

