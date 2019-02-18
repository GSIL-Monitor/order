package com.emotte.order.warehouse.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_base_dictionary
 */
public class Dictionary extends BaseModel {

 	// 主键 : ID
	private Long id;
	
	
 	// 指向主键  父id : FID
	private Long fid;
	
	
 	// 字典码名称 : DICT_NAME
	private String dictName;
	
	
 	// 备注 : REMARK
	private String remark;
	
	
 	// 数据编码 唯一  所有的都用  以此字段为关联字段 : DICT_CODE
	private String dictCode;
	
	
 	// 组织码 -1-2-3 : ORG_CODE
	private String orgCode;
	
	
 	// 排序   权重  倒序 : PRIORITY
	private Long priority;
	
	
 	// 图标路径 : ICON
	private String icon;
	
	
 	// 创建人  系统登录表的id : CREATE_BY
	private Long createBy;
	
	
 	// 修改人 : UPDATE_BY
	private Long updateBy;
	
	
	
 	// 是否有效  1 有效  ， 2 无效 : VALID
	private Integer valid;
	
	
 	// 版本号 : VERSION
	private Long version;
	
	
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
	 * 指向主键  父id : FID
	 * 
	 * @return
	 */
	public Long getFid() {
		return fid;
	}

	/**
	 * 指向主键  父id : FID
	 * 
	 * @return
	 */
	public void setFid(Long fid) {
		this.fid = fid;
	}
	
	/**
	 * 字典码名称 : DICT_NAME
	 * 
	 * @return
	 */
	public String getDictName() {
		return dictName;
	}

	/**
	 * 字典码名称 : DICT_NAME
	 * 
	 * @return
	 */
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	
	/**
	 * 备注 : REMARK
	 * 
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 备注 : REMARK
	 * 
	 * @return
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	/**
	 * 数据编码 唯一  所有的都用  以此字段为关联字段 : DICT_CODE
	 * 
	 * @return
	 */
	public String getDictCode() {
		return dictCode;
	}

	/**
	 * 数据编码 唯一  所有的都用  以此字段为关联字段 : DICT_CODE
	 * 
	 * @return
	 */
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	
	/**
	 * 组织码 -1-2-3 : ORG_CODE
	 * 
	 * @return
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * 组织码 -1-2-3 : ORG_CODE
	 * 
	 * @return
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	/**
	 * 排序   权重  倒序 : PRIORITY
	 * 
	 * @return
	 */
	public Long getPriority() {
		return priority;
	}

	/**
	 * 排序   权重  倒序 : PRIORITY
	 * 
	 * @return
	 */
	public void setPriority(Long priority) {
		this.priority = priority;
	}
	
	/**
	 * 图标路径 : ICON
	 * 
	 * @return
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * 图标路径 : ICON
	 * 
	 * @return
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	/**
	 * 创建人  系统登录表的id : CREATE_BY
	 * 
	 * @return
	 */
	@Override
	public Long getCreateBy() {
		return createBy;
	}

	/**
	 * 创建人  系统登录表的id : CREATE_BY
	 * 
	 * @return
	 */
	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	
	/**
	 * 修改人 : UPDATE_BY
	 * 
	 * @return
	 */
	@Override
	public Long getUpdateBy() {
		return updateBy;
	}

	/**
	 * 修改人 : UPDATE_BY
	 * 
	 * @return
	 */
	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	
	
	/**
	 * 是否有效  1 有效  ， 2 无效 : VALID
	 * 
	 * @return
	 */
	public Integer getValid() {
		return valid;
	}

	/**
	 * 是否有效  1 有效  ， 2 无效 : VALID
	 * 
	 * @return
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
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