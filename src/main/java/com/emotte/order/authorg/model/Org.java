package com.emotte.order.authorg.model;

import java.util.Arrays;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_auth_org
 * 2017年01月09日
 */
public class Org extends BaseModel {
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
     *  : NAME
	 * 2017年01月09日
	 */ 
	private String name;
 	/**
     *  : FID
	 * 2017年01月09日
	 */ 
	private Long fid;
 	/**
     *  : CODE
	 * 2017年01月09日
	 */ 
	private String code;
 	/**
     *  : TYPE
	 * 2017年01月09日
	 */ 
	private Byte type;
 	/**
     *  : STATUS
	 * 2017年01月09日
	 */ 
	private Byte status;
 	/**
     *  : FAX
	 * 2017年01月09日
	 */ 
	private String fax;
 	/**
     *  : MANAGER
	 * 2017年01月09日
	 */ 
	private String manager;
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
     *  : UPDATE_BY
	 * 2017年01月09日
	 */ 
	private Long updateBy;
 	/**
     *  : UPDATE_TIME
	 * 2017年01月09日
	 */ 
	private String updateTime;
 	/**
     *  : VALID
	 * 2017年01月09日
	 */ 
	private Byte valid;
 	/**
     *  : POSITION
	 * 2017年01月09日
	 */ 
	private Integer position;
 	/**
     *  : TELEPHONE
	 * 2017年01月09日
	 */ 
	private String telephone;
 	/**
     *  : CITY_CODE
	 * 2017年01月09日
	 */ 
	private String cityCode;
 	/**
     *  : LONGITUDE
	 * 2017年01月09日
	 */ 
	private String longitude;
 	/**
     *  : LATITUDE
	 * 2017年01月09日
	 */ 
	private String latitude;
 	/**
     *  : ADDR
	 * 2017年01月09日
	 */ 
	private String addr;
 	/**
     *  : IS_ALL_CATEGORY
	 * 2017年01月09日
	 */ 
	private Byte isAllCategory;
 	/**
     *  : CATEGORY_CODE
	 * 2017年01月09日
	 */ 
	private String categoryCode;
 	/**
     *  : IS_OLD_DATA
	 * 2017年01月09日
	 */ 
	private String isOldData;
 	/**
     *  : ORG_ABSTRACT
	 * 2017年01月09日
	 */ 
	private String orgAbstract;
 	/**
     *  : CONTRACT_HEADER
	 * 2017年01月09日
	 */ 
	private String contractHeader;
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
	 * 公司类型
	 */
	private Integer type2;
	/**
	 * 接单类型（1全市2手动设置）
	 */
	private Integer acceptType;
	/**
	 * 接单范围
	 */
	private String acceptRange;
	/**
	 * 订单id
	 */
	private Long orderId;
	/**
	 * 管家部门id
	 */
	private Long deptId;
	/**
	 * 部门负责人id
	 */
	private Long managerId;
	
	/**
	 * 签章图片
	 */
	private String signaturePicture;
	/**
	 * 机构号
	 */
	private String organizationNumber;
	
	/**
	 * 企业邮箱
	 */
	private String email;
	
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
	 *  : NAME
	 * @return
	 * 2017年01月09日
	 */
	public String getName() {
		return name;
	}
	/**
	 *  : NAME
	 * @return
	 * 2017年01月09日
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 *  : FID
	 * @return
	 * 2017年01月09日
	 */
	public Long getFid() {
		return fid;
	}
	/**
	 *  : FID
	 * @return
	 * 2017年01月09日
	 */
	public void setFid(Long fid) {
		this.fid = fid;
	}
	/**
	 *  : CODE
	 * @return
	 * 2017年01月09日
	 */
	public String getCode() {
		return code;
	}
	/**
	 *  : CODE
	 * @return
	 * 2017年01月09日
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 *  : TYPE
	 * @return
	 * 2017年01月09日
	 */
	public Byte getType() {
		return type;
	}
	/**
	 *  : TYPE
	 * @return
	 * 2017年01月09日
	 */
	public void setType(Byte type) {
		this.type = type;
	}
	/**
	 *  : STATUS
	 * @return
	 * 2017年01月09日
	 */
	public Byte getStatus() {
		return status;
	}
	/**
	 *  : STATUS
	 * @return
	 * 2017年01月09日
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}
	/**
	 *  : FAX
	 * @return
	 * 2017年01月09日
	 */
	public String getFax() {
		return fax;
	}
	/**
	 *  : FAX
	 * @return
	 * 2017年01月09日
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 *  : MANAGER
	 * @return
	 * 2017年01月09日
	 */
	public String getManager() {
		return manager;
	}
	/**
	 *  : MANAGER
	 * @return
	 * 2017年01月09日
	 */
	public void setManager(String manager) {
		this.manager = manager;
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
	 *  : POSITION
	 * @return
	 * 2017年01月09日
	 */
	public Integer getPosition() {
		return position;
	}
	/**
	 *  : POSITION
	 * @return
	 * 2017年01月09日
	 */
	public void setPosition(Integer position) {
		this.position = position;
	}
	/**
	 *  : TELEPHONE
	 * @return
	 * 2017年01月09日
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 *  : TELEPHONE
	 * @return
	 * 2017年01月09日
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 *  : CITY_CODE
	 * @return
	 * 2017年01月09日
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 *  : CITY_CODE
	 * @return
	 * 2017年01月09日
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 *  : LONGITUDE
	 * @return
	 * 2017年01月09日
	 */
	public String getLongitude() {
		return longitude;
	}
	/**
	 *  : LONGITUDE
	 * @return
	 * 2017年01月09日
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 *  : LATITUDE
	 * @return
	 * 2017年01月09日
	 */
	public String getLatitude() {
		return latitude;
	}
	/**
	 *  : LATITUDE
	 * @return
	 * 2017年01月09日
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 *  : ADDR
	 * @return
	 * 2017年01月09日
	 */
	public String getAddr() {
		return addr;
	}
	/**
	 *  : ADDR
	 * @return
	 * 2017年01月09日
	 */
	public void setAddr(String addr) {
		this.addr = addr;
	}
	/**
	 *  : IS_ALL_CATEGORY
	 * @return
	 * 2017年01月09日
	 */
	public Byte getIsAllCategory() {
		return isAllCategory;
	}
	/**
	 *  : IS_ALL_CATEGORY
	 * @return
	 * 2017年01月09日
	 */
	public void setIsAllCategory(Byte isAllCategory) {
		this.isAllCategory = isAllCategory;
	}
	/**
	 *  : CATEGORY_CODE
	 * @return
	 * 2017年01月09日
	 */
	public String getCategoryCode() {
		return categoryCode;
	}
	/**
	 *  : CATEGORY_CODE
	 * @return
	 * 2017年01月09日
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
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
	 *  : ORG_ABSTRACT
	 * @return
	 * 2017年01月09日
	 */
	public String getOrgAbstract() {
		return orgAbstract;
	}
	/**
	 *  : ORG_ABSTRACT
	 * @return
	 * 2017年01月09日
	 */
	public void setOrgAbstract(String orgAbstract) {
		this.orgAbstract = orgAbstract;
	}
	/**
	 *  : CONTRACT_HEADER
	 * @return
	 * 2017年01月09日
	 */
	public String getContractHeader() {
		return contractHeader;
	}
	/**
	 *  : CONTRACT_HEADER
	 * @return
	 * 2017年01月09日
	 */
	public void setContractHeader(String contractHeader) {
		this.contractHeader = contractHeader;
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
	
	
	public Integer getType2() {
		return type2;
	}
	public void setType2(Integer type2) {
		this.type2 = type2;
	}
	public Integer getAcceptType() {
		return acceptType;
	}
	public void setAcceptType(Integer acceptType) {
		this.acceptType = acceptType;
	}
	public String getAcceptRange() {
		return acceptRange;
	}
	public void setAcceptRange(String acceptRange) {
		this.acceptRange = acceptRange;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	
	public Long getManagerId() {
		return managerId;
	}
	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}
	
	public String getSignaturePicture() {
		return signaturePicture;
	}
	public void setSignaturePicture(String signaturePicture) {
		this.signaturePicture = signaturePicture;
	}
	public String getOrganizationNumber() {
		return organizationNumber;
	}
	public void setOrganizationNumber(String organizationNumber) {
		this.organizationNumber = organizationNumber;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Org [id=" + id + ", name=" + name + ", fid=" + fid + ", code=" + code + ", type=" + type + ", status="
				+ status + ", fax=" + fax + ", manager=" + manager + ", createBy=" + createBy + ", createTime="
				+ createTime + ", updateBy=" + updateBy + ", updateTime=" + updateTime + ", valid=" + valid
				+ ", position=" + position + ", telephone=" + telephone + ", cityCode=" + cityCode + ", longitude="
				+ longitude + ", latitude=" + latitude + ", addr=" + addr + ", isAllCategory=" + isAllCategory
				+ ", categoryCode=" + categoryCode + ", isOldData=" + isOldData + ", orgAbstract=" + orgAbstract
				+ ", contractHeader=" + contractHeader + ", log=" + log + ", ids=" + Arrays.toString(ids) + ", type2="
				+ type2 + ", acceptType=" + acceptType + ", acceptRange=" + acceptRange + ", orderId=" + orderId
				+ ", deptId=" + deptId + "]";
	}
	
	
}