package com.emotte.order.order.model;


import org.wildhorse.server.core.model.BaseModel;

/**
 * : T_ORDER_PUSH_LOG
 */
@SuppressWarnings("serial")
public class OrderPushLog extends BaseModel {

 	
	private Long id;
 	
	/**
	  关联的订单ID
	*/
	private Long orderId;
	
	/**
	  客户id
	*/
	private Long managerId;
	
	/**
	 * 消息
	 */
	private String msg;
	
	/**
	 * 类型
	 */
	private Long type;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	
	/**
	 * 修改时间
	 */
	private String updateTime;
	
	
	/**
	 *创建人
	*/
	private Long createBy;
	
	
	/**
	 *更新人
	*/
	private Long updateBy;
	
	
	/**
	 *版本号
	*/
	private Long version;
	
	
	/**
	  是否有效 1有效，2无效
	*/
	private Long valid;
	
	/**
	  *日志
	*/
	private String log;

	/**
	 *所属业务类型
	 */
	private Integer  distinguish;
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
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

	@Override
	public Long getVersion() {
		return version;
	}

	@Override
	public void setVersion(Long version) {
		this.version = version;
	}

	public Long getValid() {
		return valid;
	}

	public void setValid(Long valid) {
		this.valid = valid;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Integer getDistinguish() {
		return distinguish;
	}

	public void setDistinguish(Integer distinguish) {
		this.distinguish = distinguish;
	}
	
	
	
	
}