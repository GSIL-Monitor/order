package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order_serial
 * 
 * 
 * @author army
 */
public class Serial  extends BaseModel{

		// : id 	
	private Long id; 
	
			//订单ID : order_id 	
	private Long orderId; 
	
			//订单ID前段(5位) : order_id_front 	
	private Long orderIdFront; 
	
			//订单ID后段(5位) : order_id_after 	
	private Long orderIdAfter; 
	
			//创建时间 : create_date 	
	private String createTime; 
	
			//创建时间 : update_time 	
	private String updateTime; 
	
			//版本号 : version 	
	private Long version; 
	
	
/**
  * constitution
  *
  */

		
	/**
	 *  : id
	 * 
	 * @return 
	 */
	public Long getId () {
		return id;
	}
	
	/**
	 *  : id
	 * 
	 * @return 
	 */
	public void setId (Long id) {
		this.id = id;
	}
			
	/**
	 * 订单ID : order_id
	 * 
	 * @return 
	 */
	public Long getOrderId () {
		return orderId;
	}
	
	/**
	 * 订单ID : order_id
	 * 
	 * @return 
	 */
	public void setOrderId (Long orderId) {
		this.orderId = orderId;
	}
			
	/**
	 * 订单ID前段(5位) : order_id_front
	 * 
	 * @return 
	 */
	public Long getOrderIdFront () {
		return orderIdFront;
	}
	
	/**
	 * 订单ID前段(5位) : order_id_front
	 * 
	 * @return 
	 */
	public void setOrderIdFront (Long orderIdFront) {
		this.orderIdFront = orderIdFront;
	}
			
	/**
	 * 订单ID后段(5位) : order_id_after
	 * 
	 * @return 
	 */
	public Long getOrderIdAfter () {
		return orderIdAfter;
	}
	
	/**
	 * 订单ID后段(5位) : order_id_after
	 * 
	 * @return 
	 */
	public void setOrderIdAfter (Long orderIdAfter) {
		this.orderIdAfter = orderIdAfter;
	}
			
	/**
	 * @return the createTime
	 */
	@Override
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	@Override
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	@Override
	public void setUpdateTime(String updateTime) {
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
	
	
}
