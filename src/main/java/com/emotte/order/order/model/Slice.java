package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : T_ORDER_SLICE
 */
public class Slice extends BaseModel {

 	// 主键ID : ID
	private Long id;
	
	
 	// 订单ID : ORDER_ID
	private Long orderId;
	
	
 	// 分层类型 （1，三方合作分层，2，易盟二次分账分层） : IS_OTHER
	private Byte isOther;
	
	
 	// 服务人员ID（三方合作的时候，没有此id，延续性服务二次分账的时候，需要记录此ID） : PERSON_ID
	private Long personId;
	
	
	
 	// 创建人ID : CREATE_BY
	private Long createBy;
	
	
 	// 分层表ID（如果是三方合作的存放的是t_product_third_divide的id  如果是二次分账的话，存放的是t_product_divide的ID） : DIVIDE_ID
	private Long divideId;
	
	//是否是默认分成
	private byte isDefault;
	
	
	

	public byte getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(byte isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * 主键ID : ID
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 主键ID : ID
	 * 
	 * @return
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 订单ID : ORDER_ID
	 * 
	 * @return
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * 订单ID : ORDER_ID
	 * 
	 * @return
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * 分层类型 （1，三方合作分层，2，易盟二次分账分层） : IS_OTHER
	 * 
	 * @return
	 */
	public Byte getIsOther() {
		return isOther;
	}

	/**
	 * 分层类型 （1，三方合作分层，2，易盟二次分账分层） : IS_OTHER
	 * 
	 * @return
	 */
	public void setIsOther(Byte isOther) {
		this.isOther = isOther;
	}
	
	/**
	 * 服务人员ID（三方合作的时候，没有此id，延续性服务二次分账的时候，需要记录此ID） : PERSON_ID
	 * 
	 * @return
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * 服务人员ID（三方合作的时候，没有此id，延续性服务二次分账的时候，需要记录此ID） : PERSON_ID
	 * 
	 * @return
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	
	
	/**
	 * 创建人ID : CREATE_BY
	 * 
	 * @return
	 */
	@Override
	public Long getCreateBy() {
		return createBy;
	}

	/**
	 * 创建人ID : CREATE_BY
	 * 
	 * @return
	 */
	@Override
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	
	/**
	 * 分层表ID（如果是三方合作的存放的是t_product_third_divide的id  如果是二次分账的话，存放的是t_product_divide的ID） : DIVIDE_ID
	 * 
	 * @return
	 */
	public Long getDivideId() {
		return divideId;
	}

	/**
	 * 分层表ID（如果是三方合作的存放的是t_product_third_divide的id  如果是二次分账的话，存放的是t_product_divide的ID） : DIVIDE_ID
	 * 
	 * @return
	 */
	public void setDivideId(Long divideId) {
		this.divideId = divideId;
	}
	
	
}