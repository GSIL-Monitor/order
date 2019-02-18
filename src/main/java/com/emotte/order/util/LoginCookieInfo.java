/**
 * 
 */
package com.emotte.order.util;

import java.io.Serializable;

import org.apache.log4j.Logger;

/**
 * TODO 用户登录信息Cookie实体类
 * @author Administrator
 * @createTime 2016年3月29日 上午10:06:01
 * @package com.emotte.cps.util
 * @className LoginCookieInfo.java
 * @version 
 */
public final class LoginCookieInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 300396492968667936L;
	
	private Logger LOGGER = Logger.getLogger(this.getClass());
	/** 当前登录人员ID */
	private Long id;
	/**登录人员名称*/
	private String userName;
	/**登录人员真实名称*/
	private String realName;
	/**登录人员所属部门*/
	private Long deptId;
	/**管家类型*/
	private Integer managerType;
	/**城市编码*/
	private String cityCode;
	/** 城市码 全部的可能到区 */
	private String allCode;
	/** 部门类型： 公司 子公司  门店 基地 等 */
	private Integer orgType;
	/** 登录人员电话 */
	private String phone;
	/** 登录人员岗位   和字典表有对应 */
	private String post;
	/** 部门名称 :Eg:北京总公司 */
	private String orgName;
	/** 工号 坐席使用 */
	private String jobNumber;
	/**  部门id(同deptId) */
	private Long orgId;
	/** 部门code码  */
	private String orgCode;
	/** 权限级别   */
	private Integer permissionLevel;
	/**合同标头*/
	private String contractHeader;
	/** 乙方地址*/
	private String orgAddr;
	
	@Override
	public String toString() {
		return 
				" id = "+id+
				" userName = "+userName+
				" realName = "+realName+
				" deptId = "+deptId+
				" managerType = "+managerType+
				" cityCode = "+cityCode+
				" allCode = "+allCode+
				" orgType = "+orgType+
				" phone = "+phone+
				" post = "+post+
				" orgName = "+orgName+
				" jobNumber = "+jobNumber+
				" orgId = "+orgId+
				" orgCode = "+orgCode+
				" contractHeader = "+contractHeader+
				" permissionLevel = "+permissionLevel
				;
		
	}
	
	/**
	 * @return the lOGGER
	 */
	public Logger getLOGGER() {
		return LOGGER;
	}

	/**
	 * @param lOGGER the lOGGER to set
	 */
	public void setLOGGER(Logger lOGGER) {
		LOGGER = lOGGER;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the deptId
	 */
	public Long getDeptId() {
		return deptId;
	}

	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	/**
	 * @return the managerType
	 */
	public Integer getManagerType() {
		return managerType;
	}

	/**
	 * @param managerType the managerType to set
	 */
	public void setManagerType(Integer managerType) {
		this.managerType = managerType;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Integer getOrgType() {
		return orgType;
	}

	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getAllCode() {
		return allCode;
	}

	public void setAllCode(String allCode) {
		this.allCode = allCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public Integer getPermissionLevel() {
		return permissionLevel;
	}

	public void setPermissionLevel(Integer permissionLevel) {
		this.permissionLevel = permissionLevel;
	}

	public String getContractHeader() {
		return contractHeader;
	}

	public void setContractHeader(String contractHeader) {
		this.contractHeader = contractHeader;
	}

	public String getOrgAddr() {
		return orgAddr;
	}

	public void setOrgAddr(String orgAddr) {
		this.orgAddr = orgAddr;
	}

	
	
	
}
