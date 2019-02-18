package com.emotte.order.warehouse.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_warehouse_parcel
 */
public class Parcel extends BaseModel {

 	// 主键 : ID
	private Long id;
	
	
 	// T_WAREHOUSE表的id : WAREHOUSE_ID
	private Long warehouseId;
	
	
 	// T_ORDER表的id : ORDER_ID
	private Long orderId;
	
	
 	// T_BASE_DICTIONARY表的code : DICTARY_CODE
	private String dictaryCode;
	
	
 	// 包裹号 : PACKAGE_NUMBER
	private Long packageNumber;
	
	
 	// 创建人 : CREATE_BY
	private Long createBy;
	
	
 	// 更新人 : UPDATE_BY
	private Long updateBy;
	
	
 	// 版本号 : VERSION
	private Long version;
	
	
 	// 是否有效：1有效，2无效 : VALID
	private Integer valid;
	
	
 	// 包裹状态（1新订单 2待发货 3已发货 4已完成） : STATE
	private Integer state;
	
	
 	// 物流单号 : LOGISTICS_NUMBER
	private Long logisticsNumber;
	
	
 	// 是否老数据 : IS_OLD_DATA
	private String isOldData;
	
	
 	// 日志 : LOG
	private String log;
	
	
 	// 1自营、2他营 : TYPE
	private Integer type;
	
	// 辅助属性order表
	private Order order;
	
	// 辅助属性Item表
	private Item item;
	
	// 辅助属性录入人
	private String orderCreateBy;

	// 辅助属性录入人部门
	private String createByDept;
	
	// 辅助属性负责人
	private String orderRechargeBy;
	
	// 辅助属性负责人部门
	private String orderRechargeDept;
	
	// 辅助属性 id串
	private String idStr;
	
	private Integer ordercount;
	
	private Integer dateNumber;
	
	//辅助属性下单时间
	private String submitStartTime;
	
	//辅助属性下单时间
	private String submitEndTime;
	
	//辅助属性权限部门code、
	private String Citycode;
	
	//辅助属性物流json
	private String json;
	
	//辅助属性物流名字dictName
	private String dictName;
	
	//辅助属性物流code  dictCode
	private String dictCode;
	
	//辅助属性订单状态  orderSourceName
	private String orderSourceName;
	
	//辅助属性拆分包裹，itemid串
	private String arrItemId;
	
	/**
	 * 主键 : ID
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 主键 : ID
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * T_WAREHOUSE表的id : WAREHOUSE_ID
	 * 
	 * @return
	 */
	public Long getWarehouseId() {
		return warehouseId;
	}

	/**
	 * T_WAREHOUSE表的id : WAREHOUSE_ID
	 * 
	 * @return
	 */
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	/**
	 * T_ORDER表的id : ORDER_ID
	 * 
	 * @return
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * T_ORDER表的id : ORDER_ID
	 * 
	 * @return
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * T_BASE_DICTIONARY表的code : DICTARY_CODE
	 * 
	 * @return
	 */
	public String getDictaryCode() {
		return dictaryCode;
	}

	/**
	 * T_BASE_DICTIONARY表的code : DICTARY_CODE
	 * 
	 * @return
	 */
	public void setDictaryCode(String dictaryCode) {
		this.dictaryCode = dictaryCode;
	}
	
	/**
	 * 包裹号 : PACKAGE_NUMBER
	 * 
	 * @return
	 */
	public Long getPackageNumber() {
		return packageNumber;
	}

	/**
	 * 包裹号 : PACKAGE_NUMBER
	 * 
	 * @return
	 */
	public void setPackageNumber(Long packageNumber) {
		this.packageNumber = packageNumber;
	}
	
	/**
	 * 创建人 : CREATE_BY
	 * 
	 * @return
	 */
	@Override
	public Long getCreateBy() {
		return createBy;
	}

	/**
	 * 创建人 : CREATE_BY
	 * 
	 * @return
	 */
	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	
	/**
	 * 更新人 : UPDATE_BY
	 * 
	 * @return
	 */
	@Override
	public Long getUpdateBy() {
		return updateBy;
	}

	/**
	 * 更新人 : UPDATE_BY
	 * 
	 * @return
	 */
	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	/**
	 * 版本号 : VERSION
	 * 
	 * @return
	 */
	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	public void setVersion(Long version) {
		this.version = version;
	}
	
	/**
	 * 是否有效：1有效，2无效 : VALID
	 * 
	 * @return
	 */
	public Integer getValid() {
		return valid;
	}


	/**
	 * 是否有效：1有效，2无效 : VALID
	 * 
	 * @return
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	
	/**
	 * 包裹状态（1新订单 2待发货 3已发货 4已完成） : STATE
	 * 
	 * @return
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 包裹状态（1新订单 2待发货 3已发货 4已完成） : STATE
	 * 
	 * @return
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	/**
	 * 物流单号 : LOGISTICS_NUMBER
	 * 
	 * @return
	 */
	public Long getLogisticsNumber() {
		return logisticsNumber;
	}

	/**
	 * 物流单号 : LOGISTICS_NUMBER
	 * 
	 * @return
	 */
	public void setLogisticsNumber(Long logisticsNumber) {
		this.logisticsNumber = logisticsNumber;
	}
	
	/**
	 * 是否老数据 : IS_OLD_DATA
	 * 
	 * @return
	 */
	public String getIsOldData() {
		return isOldData;
	}

	/**
	 * 是否老数据 : IS_OLD_DATA
	 * 
	 * @return
	 */
	public void setIsOldData(String isOldData) {
		this.isOldData = isOldData;
	}
	
	/**
	 * 日志 : LOG
	 * 
	 * @return
	 */
	public String getLog() {
		return log;
	}

	/**
	 * 日志 : LOG
	 * 
	 * @return
	 */
	public void setLog(String log) {
		this.log = log;
	}
	
	/**
	 * 1自营、2他营 : TYPE
	 * 
	 * @return
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * 1自营、2他营 : TYPE
	 * 
	 * @return
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getCreateByDept() {
		return createByDept;
	}

	public void setCreateByDept(String createByDept) {
		this.createByDept = createByDept;
	}

	public String getOrderCreateBy() {
		return orderCreateBy;
	}

	public void setOrderCreateBy(String orderCreateBy) {
		this.orderCreateBy = orderCreateBy;
	}

	public String getOrderRechargeBy() {
		return orderRechargeBy;
	}

	public void setOrderRechargeBy(String orderRechargeBy) {
		this.orderRechargeBy = orderRechargeBy;
	}

	public String getOrderRechargeDept() {
		return orderRechargeDept;
	}

	public void setOrderRechargeDept(String orderRechargeDept) {
		this.orderRechargeDept = orderRechargeDept;
	}

	public String getIdStr() {
		return idStr;
	}

	public void setIdStr(String idStr) {
		this.idStr = idStr;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Integer getOrdercount() {
		return ordercount;
	}

	public void setOrdercount(Integer ordercount) {
		this.ordercount = ordercount;
	}

	public Integer getDateNumber() {
		return dateNumber;
	}

	public void setDateNumber(Integer dateNumber) {
		this.dateNumber = dateNumber;
	}

	public String getSubmitStartTime() {
		return submitStartTime;
	}

	public void setSubmitStartTime(String submitStartTime) {
		this.submitStartTime = submitStartTime;
	}

	public String getSubmitEndTime() {
		return submitEndTime;
	}

	public void setSubmitEndTime(String submitEndTime) {
		this.submitEndTime = submitEndTime;
	}

	public String getCitycode() {
		return Citycode;
	}

	public void setCitycode(String citycode) {
		Citycode = citycode;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getOrderSourceName() {
		return orderSourceName;
	}

	public void setOrderSourceName(String orderSourceName) {
		this.orderSourceName = orderSourceName;
	}

	public String getArrItemId() {
		return arrItemId;
	}

	public void setArrItemId(String arrItemId) {
		this.arrItemId = arrItemId;
	}
	
	
}