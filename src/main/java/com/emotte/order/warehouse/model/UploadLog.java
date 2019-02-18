package com.emotte.order.warehouse.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_warehouse_upload_log
 */
public class UploadLog extends BaseModel {

 	// 主键 : ID
	private Long id;
	
	
 	// T_WAREHOUSE_PACKAGE表的id : PACKAGE_ID
	private Long packageId;
	
	
 	// 包裹状态（1新订单 2待发货 3已发货 4已完成5失败） : STATE
	private Integer state;
	
	
 	// 原因 : REASON
	private String reason;
	
	
 	// 创建人 : CREATE_BY
	private Long createBy;
	
	
 	// 是否老数据 : IS_OLD_DATA
	private String isOldData;
	
	// 1自营、2他营 : TYPE
	private Integer type;
	
	
 	// 日志 : LOG
	private String log;
	
	//辅助属性 parcel表
	private Parcel parcel;
	
	//辅助属性 Dictionary表
	private Dictionary dictionary;
	
	//辅助属性权限部门code、
	private String Citycode;
	
	

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
	 * T_WAREHOUSE_PACKAGE表的id : PACKAGE_ID
	 * 
	 * @return
	 */
	public Long getPackageId() {
		return packageId;
	}

	/**
	 * T_WAREHOUSE_PACKAGE表的id : PACKAGE_ID
	 * 
	 * @return
	 */
	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}
	
	/**
	 * 包裹状态（1新订单 2待发货 3已发货 4已完成5失败） : STATE
	 * 
	 * @return
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 包裹状态（1新订单 2待发货 3已发货 4已完成5失败） : STATE
	 * 
	 * @return
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	
	/**
	 * 原因 : REASON
	 * 
	 * @return
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * 原因 : REASON
	 * 
	 * @return
	 */
	public void setReason(String reason) {
		this.reason = reason;
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

	public Parcel getParcel() {
		return parcel;
	}

	public void setParcel(Parcel parcel) {
		this.parcel = parcel;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public String getCitycode() {
		return Citycode;
	}

	public void setCitycode(String citycode) {
		Citycode = citycode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
}