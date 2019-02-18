package com.emotte.order.warehouse.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : T_WAREHOUSE_PACKAGE_ITEM_REF
 */
public class PackageItemRef extends BaseModel {

 	// 主键 : ID
	private Long id;
	
	
 	// T_ORDER_ITEM表的id : ORDER_ITEM_ID
	private Long orderItemId;
	
	
 	// T_WAREHOUSE_PACKAGE表的id : PACKAGE_ID
	private Long packageId;
	
	
 	// 创建人 : CREATE_BY
	private Long createBy;
	
	
 	// 更新人 : UPDATE_BY
	private Long updateBy;
	
	
 	// 版本号 : VERSION
	private Long version;
	
	
 	// 是否有效：1有效，2无效 : VALID
	private Integer valid;
	
	
 	// 是否老数据 : IS_OLD_DATA
	private String isOldData;
	
	
 	// 日志 : LOG
	private String log;
	
	

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
	 * T_ORDER_ITEM表的id : ORDER_ITEM_ID
	 * 
	 * @return
	 */
	public Long getOrderItemId() {
		return orderItemId;
	}

	/**
	 * T_ORDER_ITEM表的id : ORDER_ITEM_ID
	 * 
	 * @return
	 */
	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
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

	/**
	 * 版本号 : VERSION
	 * 
	 * @return
	 */
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
	
	
}