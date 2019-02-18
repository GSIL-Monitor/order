package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order_dictionary
 * 
 * 
 * @author army
 */
public class Dictionary extends BaseModel {

	// : id
	private Long id;

	// 上级字典id : pid
	private Long pid;

	// 字典类型 : types
	private String types;

	// 类别主键 : dkey
	private String dkey; 

	// 类别值 : dvalue
	private String dvalue;

	// 历史标志 : valid
	private Integer valid;

	// 创建人 : create_by
	private Long createBy;

	// 创建时间 : create_time
	private String createTime;

	// 备注 : remarks
	private String remarks;

	private int length; // 备用长度
	private String level ;
	// 是否管家可见，1是2否
	private Integer housekeeperAvailable;
	// 是否他人代收，1允许2不允许
	private Integer isCollection;
	//易盟渠道经理ID
	private Long managerId;
	//合作方渠道经理ID
	private Long partnerManagerId;
	

	public Long getPartnerManagerId() {
		return partnerManagerId;
	}

	public void setPartnerManagerId(Long partnerManagerId) {
		this.partnerManagerId = partnerManagerId;
	}

	/**
	 * constitution
	 *
	 */

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	/**
	 * : id
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * : id
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 上级字典id : pid
	 * 
	 * @return
	 */
	public Long getPid() {
		return pid;
	}

	/**
	 * 上级字典id : pid
	 * 
	 * @return
	 */
	public void setPid(Long pid) {
		this.pid = pid;
	}

	/**
	 * 字典类型 : types
	 * 
	 * @return
	 */
	public String getTypes() {
		return types;
	}

	/**
	 * 字典类型 : types
	 * 
	 * @return
	 */
	public void setTypes(String types) {
		this.types = types;
	}

	/**
	 * 类别主键 : dkey
	 * 
	 * @return
	 */
	public String getDkey() {
		return dkey;
	}

	/**
	 * 类别主键 : dkey
	 * 
	 * @return
	 */
	public void setDkey(String dkey) {
		this.dkey = dkey;
	}

	/**
	 * 类别值 : dvalue
	 * 
	 * @return
	 */
	public String getDvalue() {
		return dvalue;
	}

	/**
	 * 类别值 : dvalue
	 * 
	 * @return
	 */
	public void setDvalue(String dvalue) {
		this.dvalue = dvalue;
	}

	/**
	 * 历史标志 : valid
	 * 
	 * @return
	 */
	public Integer getValid() {
		return valid;
	}

	/**
	 * 历史标志 : valid
	 * 
	 * @return
	 */
	public void setValid(Integer valid) {
		this.valid = valid;
	}

	/**
	 * 创建人 : create_name
	 * 
	 * @return
	 */
	@Override
	public Long getCreateBy() {
		return createBy;
	}

	/**
	 * 创建人 : create_name
	 * 
	 * @return
	 */
	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	/**
	 * 创建时间 : create_date
	 * 
	 * @return
	 */
	@Override
	public String getCreateTime() {
		return createTime;
	}


	/**
	 * 备注 : remarks
	 * 
	 * @return
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 备注 : remarks
	 * 
	 * @return
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Integer getHousekeeperAvailable() {
		return housekeeperAvailable;
	}

	public void setHousekeeperAvailable(Integer housekeeperAvailable) {
		this.housekeeperAvailable = housekeeperAvailable;
	}

	public Integer getIsCollection() {
		return isCollection;
	}

	public void setIsCollection(Integer isCollection) {
		this.isCollection = isCollection;
	}

	
}
