package com.emotte.order.gentlemanSignature.model;


import org.wildhorse.server.core.model.BaseModel;
/**
 * T_ORDER_AGREEMENT_GS
 * 
 * @author shangq
 *
 */
public class AgreementGs extends BaseModel{
	
	private Long id;
	//角色
	private Long role;
	//合同ID
	private Long contactId;
	//图片信息
	private byte[] base64image;
	
	//合同文件
	private Long fileId;
	
	private String filePath;
	
    private String createTime;
	
	private String updateTime;
	
	private Long createBy;
	
	private Long updateBy;
	
	private Long valid;
	
	private Long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public byte[] getBase64image() {
		return base64image;
	}

	public void setBase64image(byte[] base64image) {
		this.base64image = base64image;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String getUpdateTime() {
		return updateTime;
	}

	@Override
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public Long getCreateBy() {
		return createBy;
	}

	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	@Override
	public Long getUpdateBy() {
		return updateBy;
	}

	@Override
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Long getValid() {
		return valid;
	}

	public void setValid(Long valid) {
		this.valid = valid;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	public void setVersion(Long version) {
		this.version = version;
	}
	
	

}
