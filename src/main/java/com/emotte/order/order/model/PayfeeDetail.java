package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order_payfee_detail
 * 
 * 
 * @author army
 */
public class PayfeeDetail  extends BaseModel{

		//主键 : id 	
	private Long id; 
	
			//费用项目 : charge_code 	
	private String chargeCode; 
	
			//缴费人类型 ：1 服务人员 2，客户 : person_type 	
	private Integer personType; 
	
			//缴费主表ID : order_payfee_id 	
	private Long orderPayfeeId; 
	
			//客户或者服务人员ID : person_id 	
	private Long personId; 
	
			//缴费明细金额 : fee_detail_sum 	
	private java.math.BigDecimal feeDetailSum; 
	
			//创建时间 : create_time 	
	private String createTime; 
	
			//创建人 : create_by 	
	private Long createBy; 
	
			//修改时间 : update_time 	
	private String updateTime; 
	
			//修改人 : update_by 	
	private Long updateBy; 
	
			//版本号 : version 	
	private Long version; 
	
			//是否需要对账:1需要（挑勾） 2 不需要（不挑勾） : bank_status 	
	private Integer bankStatus; 
	
			//备注 : remark 	
	private String remark; 
	
	
/**
  * constitution
  *
  */

		
	/**
	 * 主键 : id
	 * 
	 * @return 
	 */
	public Long getId () {
		return id;
	}
	
	/**
	 * 主键 : id
	 * 
	 * @return 
	 */
	public void setId (Long id) {
		this.id = id;
	}
			
	/**
	 * 费用项目 : charge_code
	 * 
	 * @return 
	 */
	public String getChargeCode () {
		return chargeCode;
	}
	
	/**
	 * 费用项目 : charge_code
	 * 
	 * @return 
	 */
	public void setChargeCode (String chargeCode) {
		this.chargeCode = chargeCode;
	}
			
	/**
	 * 缴费人类型 ：1 服务人员 2，客户 : person_type
	 * 
	 * @return 
	 */
	public Integer getPersonType () {
		return personType;
	}
	
	/**
	 * 缴费人类型 ：1 服务人员 2，客户 : person_type
	 * 
	 * @return 
	 */
	public void setPersonType (Integer personType) {
		this.personType = personType;
	}
			
	/**
	 * 缴费主表ID : order_payfee_id
	 * 
	 * @return 
	 */
	public Long getOrderPayfeeId () {
		return orderPayfeeId;
	}
	
	/**
	 * 缴费主表ID : order_payfee_id
	 * 
	 * @return 
	 */
	public void setOrderPayfeeId (Long orderPayfeeId) {
		this.orderPayfeeId = orderPayfeeId;
	}
			
	/**
	 * 客户或者服务人员ID : person_id
	 * 
	 * @return 
	 */
	public Long getPersonId () {
		return personId;
	}
	
	/**
	 * 客户或者服务人员ID : person_id
	 * 
	 * @return 
	 */
	public void setPersonId (Long personId) {
		this.personId = personId;
	}
			
	/**
	 * 缴费明细金额 : fee_detail_sum
	 * 
	 * @return 
	 */
	public java.math.BigDecimal getFeeDetailSum () {
		return feeDetailSum;
	}
	
	/**
	 * 缴费明细金额 : fee_detail_sum
	 * 
	 * @return 
	 */
	public void setFeeDetailSum (java.math.BigDecimal feeDetailSum) {
		this.feeDetailSum = feeDetailSum;
	}
			

	/**
	 * 创建人 : create_by
	 * 
	 * @return 
	 */
	@Override
	public Long getCreateBy () {
		return createBy;
	}
	
	/**
	 * 创建人 : create_by
	 * 
	 * @return 
	 */
	@Override
	public void setCreateBy (Long createBy) {
		this.createBy = createBy;
	}
			
	/**
	 * 创建时间 : create_time
	 * 
	 * @return 
	 */
	@Override
	public String getCreateTime () {
		return createTime;
	}
	
	/**
	 * 创建时间 : create_time
	 * 
	 * @return 
	 */
	@Override
	public void setCreateTime (String createTime) {
		this.createTime = createTime;
	}
			
	/**
	 * 更新人 : update_by
	 * 
	 * @return 
	 */
	@Override
	public Long getUpdateBy () {
		return updateBy;
	}
	
	/**
	 * 更新人 : update_by
	 * 
	 * @return 
	 */
	@Override
	public void setUpdateBy (Long updateBy) {
		this.updateBy = updateBy;
	}
			
	/**
	 * 更新时间 : update_time
	 * 
	 * @return 
	 */
	@Override
	public String getUpdateTime () {
		return updateTime;
	}
	
	/**
	 * 更新时间 : update_time
	 * 
	 * @return 
	 */
	@Override
	public void setUpdateTime (String updateTime) {
		this.updateTime = updateTime;
	}
			
	/**
	 * 版本号 : version
	 * 
	 * @return 
	 */
	@Override
	public Long getVersion () {
		return version;
	}
	
	/**
	 * 版本号 : version
	 * 
	 * @return 
	 */
	@Override
	public void setVersion (Long version) {
		this.version = version;
	}
			
	/**
	 * 是否需要对账:1需要（挑勾） 2 不需要（不挑勾） : bank_status
	 * 
	 * @return 
	 */
	public Integer getBankStatus () {
		return bankStatus;
	}
	
	/**
	 * 是否需要对账:1需要（挑勾） 2 不需要（不挑勾） : bank_status
	 * 
	 * @return 
	 */
	public void setBankStatus (Integer bankStatus) {
		this.bankStatus = bankStatus;
	}
			
	/**
	 * 备注 : remark
	 * 
	 * @return 
	 */
	public String getRemark () {
		return remark;
	}
	
	/**
	 * 备注 : remark
	 * 
	 * @return 
	 */
	public void setRemark (String remark) {
		this.remark = remark;
	}
	}
