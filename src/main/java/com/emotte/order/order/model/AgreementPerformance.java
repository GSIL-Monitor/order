package com.emotte.order.order.model;

import java.math.BigDecimal;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : T_ORDER_AGREEMENT_PERFORMANCE
 */
public class AgreementPerformance extends BaseModel {

	// 主键 : ID
	private Long id;

	// 适用城市编号,对应订单城市 : CITY_CODE
	private String cityCode;

	// 适用开始时间 : START_TIME
	private String startTime;

	// 适用结束时间 : END_TIME
	private String endTime;

	// 基本工资比例,如:0.90 : PE_CONTRACT_BASIC
	private BigDecimal peContractBasic;

	// 绩效工资比例,如:0.10 : PE_CONTRACT_PERFORMANCE
	private BigDecimal peContractPerformance;

	// 创建人 : CREATE_BY
	private Long createBy;

	// 更新人 : UPDATE_BY
	private Long updateBy;

	// 版本号 : VERSION
	private Long version;

	// 是否有效,1:有效,2:无效 : VALID
	private Integer valid;

	// 备注 : REMARK
	private String remark;

	// 手动日志 : LOG
	private String log;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public BigDecimal getPeContractBasic() {
		return peContractBasic;
	}

	public void setPeContractBasic(BigDecimal peContractBasic) {
		this.peContractBasic = peContractBasic;
	}

	public BigDecimal getPeContractPerformance() {
		return peContractPerformance;
	}

	public void setPeContractPerformance(BigDecimal peContractPerformance) {
		this.peContractPerformance = peContractPerformance;
	}

}