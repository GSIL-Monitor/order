package com.emotte.order.order.model;


/**
 * 
  * @Description: t_base_dictionary   
  * @author dengyouqian@95081.com
  * @date 2018年5月15日
 */
public class DataDictionaryModel{
	private String id;            //    主键 
	private String fid;            //      指向主键  父id 
	private String dictName;            //     字典码名称 
	private String remark;            //      备注 
	private String dictCode;            //      数据编码 唯一  所有的都用  以此字段为关联字段 
	private String orgCode;            //     组织码 -1-2-3 
	private String priority;            // 排序   权重  倒序 
	private String icon;            //      图标路径 
	private String createBy;            //      创建人  系统登录表的id 
	private String updateBy;            // 修改人 
	private String createTime;            // 创建时间 
	private String updateTime;            // D更新时间 
	private String valid;            //    是否有效  1 有效  ， 2 无效 
	private String version;            //      版本号 
	private String isOldData;            //     是否老数据 
	private String log;            // 日志 
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getDictName() {
		return dictName;
	}
	public void setDictName(String dictName) {
		this.dictName = dictName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDictCode() {
		return dictCode;
	}
	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getIsOldData() {
		return isOldData;
	}
	public void setIsOldData(String isOldData) {
		this.isOldData = isOldData;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	@Override
	public String toString() {
		return "DataDictionaryModel [id=" + id + ", fid=" + fid + ", dictName=" + dictName + ", remark=" + remark
				+ ", dictCode=" + dictCode + ", orgCode=" + orgCode + ", priority=" + priority + ", icon=" + icon
				+ ", createBy=" + createBy + ", updateBy=" + updateBy + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", valid=" + valid + ", version=" + version + ", isOldData=" + isOldData + ", log=" + log
				+ "]";
	}

	
	
	
}
