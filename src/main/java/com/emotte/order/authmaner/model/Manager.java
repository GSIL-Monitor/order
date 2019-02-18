package com.emotte.order.authmaner.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_auth_manager
 * 2017年01月09日
 */
public class Manager extends BaseModel {
 	/**
	 * TODO
	 * 2017年01月09日
	 */
	private static final long serialVersionUID = 1L;

 	/**
     *  : ID
	 * 2017年01月09日
	 */ 
	private Long id;
 	/**
     *  : USER_NAME
	 * 2017年01月09日
	 */ 
	private String userName;
 	/**
     *  : PASSWORD
	 * 2017年01月09日
	 */ 
	private String password;
 	/**
     *  : REAL_NAME
	 * 2017年01月09日
	 */ 
	private String realName;
 	/**
     *  : LOGIN_COUNT
	 * 2017年01月09日
	 */ 
	private Long loginCount;
 	/**
     *  : LAST_LOGIN_TIME
	 * 2017年01月09日
	 */ 
	private java.util.Date lastLoginTime;
 	/**
     *  : LAST_LOGIN_IP
	 * 2017年01月09日
	 */ 
	private String lastLoginIp;
 	/**
     *  : STATE
	 * 2017年01月09日
	 */ 
	private Byte state;
 	/**
     *  : DEPT_ID
	 * 2017年01月09日
	 */ 
	private Long deptId;
 	/**
     *  : PHONE
	 * 2017年01月09日
	 */ 
	private String phone;
 	/**
     *  : MANAGER_TYPE
	 * 2017年01月09日
	 */ 
	private Long managerType;
 	/**
     *  : STYLE_COLOR
	 * 2017年01月09日
	 */ 
	private String styleColor;
 	/**
     *  : REMARK
	 * 2017年01月09日
	 */ 
	private String remark;
 	/**
     *  : CREATE_BY
	 * 2017年01月09日
	 */ 
	private Long createBy;
 	/**
     *  : CREATE_TIME
	 * 2017年01月09日
	 */ 
	private String createTime;
 	/**
     *  : PERMISSION_LEVEL
	 * 2017年01月09日
	 */ 
	private Long permissionLevel;
 	/**
     *  : UPDATE_TIME
	 * 2017年01月09日
	 */ 
	private String updateTime;
 	/**
     *  : UPDATE_BY
	 * 2017年01月09日
	 */ 
	private Long updateBy;
 	/**
     *  : VALID
	 * 2017年01月09日
	 */ 
	private Byte valid;
 	/**
     *  : POST
	 * 2017年01月09日
	 */ 
	private String post;
 	/**
     *  : WITH_USER
	 * 2017年01月09日
	 */ 
	private String withUser;
 	/**
     *  : JOB_NUMBER
	 * 2017年01月09日
	 */ 
	private String jobNumber;
 	/**
     *  : IS_OLD_DATA
	 * 2017年01月09日
	 */ 
	private String isOldData;
 	/**
     *  : LOG
	 * 2017年01月09日
	 */ 
	private String log;
	/** 
	 * id数组
	 * 2017年01月09日
	 */
	private String[] ids;
	
	/**
	 *  : ID
	 * @return
	 * 2017年01月09日
	 */
	public Long getId() {
		return id;
	}
	/**
	 *  : ID
	 * @return
	 * 2017年01月09日
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 *  : USER_NAME
	 * @return
	 * 2017年01月09日
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 *  : USER_NAME
	 * @return
	 * 2017年01月09日
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 *  : PASSWORD
	 * @return
	 * 2017年01月09日
	 */
	public String getPassword() {
		return password;
	}
	/**
	 *  : PASSWORD
	 * @return
	 * 2017年01月09日
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 *  : REAL_NAME
	 * @return
	 * 2017年01月09日
	 */
	public String getRealName() {
		return realName;
	}
	/**
	 *  : REAL_NAME
	 * @return
	 * 2017年01月09日
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/**
	 *  : LOGIN_COUNT
	 * @return
	 * 2017年01月09日
	 */
	public Long getLoginCount() {
		return loginCount;
	}
	/**
	 *  : LOGIN_COUNT
	 * @return
	 * 2017年01月09日
	 */
	public void setLoginCount(Long loginCount) {
		this.loginCount = loginCount;
	}
	/**
	 *  : LAST_LOGIN_TIME
	 * @return
	 * 2017年01月09日
	 */
	public java.util.Date getLastLoginTime() {
		return lastLoginTime;
	}
	/**
	 *  : LAST_LOGIN_TIME
	 * @return
	 * 2017年01月09日
	 */
	public void setLastLoginTime(java.util.Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	/**
	 *  : LAST_LOGIN_IP
	 * @return
	 * 2017年01月09日
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	/**
	 *  : LAST_LOGIN_IP
	 * @return
	 * 2017年01月09日
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	/**
	 *  : STATE
	 * @return
	 * 2017年01月09日
	 */
	public Byte getState() {
		return state;
	}
	/**
	 *  : STATE
	 * @return
	 * 2017年01月09日
	 */
	public void setState(Byte state) {
		this.state = state;
	}
	/**
	 *  : DEPT_ID
	 * @return
	 * 2017年01月09日
	 */
	public Long getDeptId() {
		return deptId;
	}
	/**
	 *  : DEPT_ID
	 * @return
	 * 2017年01月09日
	 */
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	/**
	 *  : PHONE
	 * @return
	 * 2017年01月09日
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 *  : PHONE
	 * @return
	 * 2017年01月09日
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 *  : MANAGER_TYPE
	 * @return
	 * 2017年01月09日
	 */
	public Long getManagerType() {
		return managerType;
	}
	/**
	 *  : MANAGER_TYPE
	 * @return
	 * 2017年01月09日
	 */
	public void setManagerType(Long managerType) {
		this.managerType = managerType;
	}
	/**
	 *  : STYLE_COLOR
	 * @return
	 * 2017年01月09日
	 */
	public String getStyleColor() {
		return styleColor;
	}
	/**
	 *  : STYLE_COLOR
	 * @return
	 * 2017年01月09日
	 */
	public void setStyleColor(String styleColor) {
		this.styleColor = styleColor;
	}
	/**
	 *  : REMARK
	 * @return
	 * 2017年01月09日
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 *  : REMARK
	 * @return
	 * 2017年01月09日
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 *  : CREATE_BY
	 * @return
	 * 2017年01月09日
	 */
	@Override
	public Long getCreateBy() {
		return createBy;
	}
	/**
	 *  : CREATE_BY
	 * @return
	 * 2017年01月09日
	 */
	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	/**
	 *  : CREATE_TIME
	 * @return
	 * 2017年01月09日
	 */
	@Override
	public String getCreateTime() {
		return createTime;
	}
	/**
	 *  : CREATE_TIME
	 * @return
	 * 2017年01月09日
	 */
	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	/**
	 *  : PERMISSION_LEVEL
	 * @return
	 * 2017年01月09日
	 */
	public Long getPermissionLevel() {
		return permissionLevel;
	}
	/**
	 *  : PERMISSION_LEVEL
	 * @return
	 * 2017年01月09日
	 */
	public void setPermissionLevel(Long permissionLevel) {
		this.permissionLevel = permissionLevel;
	}
	/**
	 *  : UPDATE_TIME
	 * @return
	 * 2017年01月09日
	 */
	@Override
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 *  : UPDATE_TIME
	 * @return
	 * 2017年01月09日
	 */
	@Override
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 *  : UPDATE_BY
	 * @return
	 * 2017年01月09日
	 */
	@Override
	public Long getUpdateBy() {
		return updateBy;
	}
	/**
	 *  : UPDATE_BY
	 * @return
	 * 2017年01月09日
	 */
	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 *  : VALID
	 * @return
	 * 2017年01月09日
	 */
	public Byte getValid() {
		return valid;
	}
	/**
	 *  : VALID
	 * @return
	 * 2017年01月09日
	 */
	public void setValid(Byte valid) {
		this.valid = valid;
	}
	/**
	 *  : POST
	 * @return
	 * 2017年01月09日
	 */
	public String getPost() {
		return post;
	}
	/**
	 *  : POST
	 * @return
	 * 2017年01月09日
	 */
	public void setPost(String post) {
		this.post = post;
	}
	/**
	 *  : WITH_USER
	 * @return
	 * 2017年01月09日
	 */
	public String getWithUser() {
		return withUser;
	}
	/**
	 *  : WITH_USER
	 * @return
	 * 2017年01月09日
	 */
	public void setWithUser(String withUser) {
		this.withUser = withUser;
	}
	/**
	 *  : JOB_NUMBER
	 * @return
	 * 2017年01月09日
	 */
	public String getJobNumber() {
		return jobNumber;
	}
	/**
	 *  : JOB_NUMBER
	 * @return
	 * 2017年01月09日
	 */
	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
	/**
	 *  : IS_OLD_DATA
	 * @return
	 * 2017年01月09日
	 */
	public String getIsOldData() {
		return isOldData;
	}
	/**
	 *  : IS_OLD_DATA
	 * @return
	 * 2017年01月09日
	 */
	public void setIsOldData(String isOldData) {
		this.isOldData = isOldData;
	}
	/**
	 *  : LOG
	 * @return
	 * 2017年01月09日
	 */
	public String getLog() {
		return log;
	}
	/**
	 *  : LOG
	 * @return
	 * 2017年01月09日
	 */
	public void setLog(String log) {
		this.log = log;
	}
	
	/**
	 * id数组
	 * @return 
	 * 2017年01月09日
	 */
	public String[] getIds() {
		return ids;
	}
	/**
	 * id数组
	 * @param ids
	 * 2017年01月09日
	 */
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	@Override
	public String toString() {
		return "Manager["
		+ "id=" + id
		+ "userName=" + userName
		+ "password=" + password
		+ "realName=" + realName
		+ "loginCount=" + loginCount
		+ "lastLoginTime=" + lastLoginTime
		+ "lastLoginIp=" + lastLoginIp
		+ "state=" + state
		+ "deptId=" + deptId
		+ "phone=" + phone
		+ "managerType=" + managerType
		+ "styleColor=" + styleColor
		+ "remark=" + remark
		+ "createBy=" + createBy
		+ "createTime=" + createTime
		+ "permissionLevel=" + permissionLevel
		+ "updateTime=" + updateTime
		+ "updateBy=" + updateBy
		+ "valid=" + valid
		+ "post=" + post
		+ "withUser=" + withUser
		+ "jobNumber=" + jobNumber
		+ "isOldData=" + isOldData
		+ "log=" + log
		+ "]";
	}
	
}