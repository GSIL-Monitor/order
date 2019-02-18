package com.emotte.order.order.model;

import java.util.List;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order_agreement_file
 */
public class AgreementFile extends BaseModel {

	// id : ID
	private Long id;

	// 文件地址 : URL
	private String url;

	// 订单合同id : AGREEMENT_ID
	private Long agreementId;

	// 创建人id : CREATE_BY
	private Long createBy;

	// 修改人id : UPDATE_BY
	private Long updateBy;

	// 是否有效（1:有效 2:无效） : VALID
	private Integer valid;

	// 版本号 : VERSION
	private Long version;

	// 顺序号（第几张）
	private Integer rankNumber;
	
	private List<AgreementFile> list;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Long agreementId) {
		this.agreementId = agreementId;
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

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
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

	public Integer getRankNumber() {
		return rankNumber;
	}

	public void setRankNumber(Integer rankNumber) {
		this.rankNumber = rankNumber;
	}

	public List<AgreementFile> getList() {
		return list;
	}

	public void setList(List<AgreementFile> list) {
		this.list = list;
	}

	

}