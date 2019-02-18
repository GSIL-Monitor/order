package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : T_AUTH_MANAGER
 * @author army
 */

public class Managers extends BaseModel {

	// 管理员用户编号 : ID
	private Long id;

	// 登录用户 : USER_NAME
	private String userName;

	// 登录密码 : PASSWORD
	private String password;

	// 真实姓名 : REAL_NAME
	private String realName;

	// 登录次数 : LOGIN_COUNT
	private String loginCount;

	// 上次登录时间 : LAST_LOGIN_TIME
	private String lastLoginTime;

	// 上次登录IP : LAST_LOGIN_IP
	private String lastLoginIp;

	// 状态（1：启用；2：禁用） : STATE
	private Integer state;

	// 部门 : DEPARTMENT_ID
	private Long orgId;
	
	// 备注 : REMARK
	private String remark;

	// 电话 : PHONE
	private String phone;

	// MANAGER_TYPE 用户角色（1：普通员工；2分公司负责人；3总公司负责人）
	private Integer managerType;

	private String userKey;

	//部门名称
	private String deptName;

	//状态名称
	private String stateName;

	// 权限级别（1：公开；2保密；3绝密)
	private Integer permissionLevel; 

	// 所属业务部门id
	private Long deptId; 

	//颜色样式
	private String styleColor;
	
	// 是否有效，1有效；2无效 : valid
	private Integer valid;

	
	//辅助属性 管家的已分包客户数量(累计分出的客户)
	private Integer outCustom;
	
	//辅助属性 管家的待分包客户数量(属于这个管家负责，但不属于他营销维护的客户，当解除分包之后由此营销管家进行分包)
	private Integer responseCustom;
	
	//辅助属性 管家私有的客户数量，由此管家回访营销
	private Integer ownCustom;
	
	//辅助属性  解除分包数量
	private Integer relieveCustomNumber;
	
	//用户岗位code
	private String post;
	
	//用户岗位名字
	private String postName;
	
	//辅助属性 查找范围（对应用户的权限级别）
	private int permission;
	
	private String orgCode;
	//订单类型
	private int type2;
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	private String orgcityCode;
	
	private Dictionary dictionary;
	private Integer orgType;
	
	public Integer getOrgType() {
		return orgType;
	}
	public void setOrgType(Integer orgType) {
		this.orgType = orgType;
	}
	/**
	 * constitution
	 *
	 */

	
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public Integer getValid() {
		return valid;
	}
	
	/**
	 * 管理员用户编号 : ID
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 管理员用户编号 : ID
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 登录用户 : USER_NAME
	 * 
	 * @return
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 登录用户 : USER_NAME
	 * 
	 * @return
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 登录密码 : PASSWORD
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 登录密码 : PASSWORD
	 * 
	 * @return
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 真实姓名 : REAL_NAME
	 * 
	 * @return
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * 真实姓名 : REAL_NAME
	 * 
	 * @return
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * 登录次数 : LOGIN_COUNT
	 * 
	 * @return
	 */
	public String getLoginCount() {
		return loginCount;
	}

	/**
	 * 登录次数 : LOGIN_COUNT
	 * 
	 * @return
	 */
	public void setLoginCount(String loginCount) {
		this.loginCount = loginCount;
	}

	/**
	 * 上次登录时间 : LAST_LOGIN_TIME
	 * 
	 * @return
	 */
	public String getLastLoginTime() {
		return lastLoginTime;
	}

	/**
	 * 上次登录时间 : LAST_LOGIN_TIME
	 * 
	 * @return
	 */
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	/**
	 * 上次登录IP : LAST_LOGIN_IP
	 * 
	 * @return
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}

	/**
	 * 上次登录IP : LAST_LOGIN_IP
	 * 
	 * @return
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	/**
	 * 状态（1：启用；2：禁用） : STATE
	 * 
	 * @return
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * 状态（1：启用；2：禁用） : STATE
	 * 
	 * @return
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 电话 : PHONE
	 * 
	 * @return
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 电话 : PHONE
	 * 
	 * @return
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 管理类型 : MANAGER_TYPE
	 * 
	 * @return
	 */
	public Integer getManagerType() {
		return managerType;
	}

	/**
	 * 管理类型 : MANAGER_TYPE
	 * 
	 * @return
	 */
	public void setManagerType(Integer managerType) {
		this.managerType = managerType;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPermissionLevel() {
		return permissionLevel;
	}

	public void setPermissionLevel(Integer permissionLevel) {
		this.permissionLevel = permissionLevel;
	}


	public String getStyleColor() {
		return styleColor;
	}

	public void setStyleColor(String styleColor) {
		this.styleColor = styleColor;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Integer getOutCustom() {
		return outCustom;
	}
	public void setOutCustom(Integer outCustom) {
		this.outCustom = outCustom;
	}
	public Integer getResponseCustom() {
		return responseCustom;
	}
	public void setResponseCustom(Integer responseCustom) {
		this.responseCustom = responseCustom;
	}
	public Integer getOwnCustom() {
		return ownCustom;
	}
	public void setOwnCustom(Integer ownCustom) {
		this.ownCustom = ownCustom;
	}
	public Integer getRelieveCustomNumber() {
		return relieveCustomNumber;
	}
	public void setRelieveCustomNumber(Integer relieveCustomNumber) {
		this.relieveCustomNumber = relieveCustomNumber;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public int getPermission() {
		return permission;
	}
	public void setPermission(int permission) {
		this.permission = permission;
	}
	public String getOrgcityCode() {
		return orgcityCode;
	}
	public void setOrgcityCode(String orgcityCode) {
		this.orgcityCode = orgcityCode;
	}
	public Dictionary getDictionary() {
		return dictionary;
	}
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	public int getType2() {
		return type2;
	}
	public void setType2(int type2) {
		this.type2 = type2;
	}

	

}
