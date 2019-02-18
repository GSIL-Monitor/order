package com.emotte.order.order.model;

import org.wildhorse.server.core.model.BaseModel;

/**
 * : t_order_daily
 * 
 * 
 * @author army
 */
public class Daily  extends BaseModel{

		// : id 	
	private Long id; 
	
			//订单id : order_id 	
	private Long orderId; 
	
			//用户名 : user_name 	
	private String userName; 
	
			//登陆ip : user_ip 	
	private String userIp; 
	
			//用户id : user_id 	
	private Long userId; 
	
			//创建时间 : create_date 	
	private java.util.Date createDate; 
	
			//表名 : tablename 	
	private String tablename; 
	
			//父id : pid 	
	private Long pid; 
	
			//日志内容 : logs 	
	private String logs; 
	
	
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
	 * 订单id : order_id
	 * 
	 * @return 
	 */
	public Long getOrderId () {
		return orderId;
	}
	
	/**
	 * 订单id : order_id
	 * 
	 * @return 
	 */
	public void setOrderId (Long orderId) {
		this.orderId = orderId;
	}
			
	/**
	 * 用户名 : user_name
	 * 
	 * @return 
	 */
	public String getUserName () {
		return userName;
	}
	
	/**
	 * 用户名 : user_name
	 * 
	 * @return 
	 */
	public void setUserName (String userName) {
		this.userName = userName;
	}
			
	/**
	 * 登陆ip : user_ip
	 * 
	 * @return 
	 */
	public String getUserIp () {
		return userIp;
	}
	
	/**
	 * 登陆ip : user_ip
	 * 
	 * @return 
	 */
	public void setUserIp (String userIp) {
		this.userIp = userIp;
	}
			
	/**
	 * 用户id : user_id
	 * 
	 * @return 
	 */
	public Long getUserId () {
		return userId;
	}
	
	/**
	 * 用户id : user_id
	 * 
	 * @return 
	 */
	public void setUserId (Long userId) {
		this.userId = userId;
	}
			
	/**
	 * 创建时间 : create_date
	 * 
	 * @return 
	 */
	public java.util.Date getCreateDate () {
		return createDate;
	}
	
	/**
	 * 创建时间 : create_date
	 * 
	 * @return 
	 */
	public void setCreateDate (java.util.Date createDate) {
		this.createDate = createDate;
	}
			
	/**
	 * 表名 : tablename
	 * 
	 * @return 
	 */
	public String getTablename () {
		return tablename;
	}
	
	/**
	 * 表名 : tablename
	 * 
	 * @return 
	 */
	public void setTablename (String tablename) {
		this.tablename = tablename;
	}
			
	/**
	 * 父id : pid
	 * 
	 * @return 
	 */
	public Long getPid () {
		return pid;
	}
	
	/**
	 * 父id : pid
	 * 
	 * @return 
	 */
	public void setPid (Long pid) {
		this.pid = pid;
	}
			
	/**
	 * 日志内容 : logs
	 * 
	 * @return 
	 */
	public String getLogs () {
		return logs;
	}
	
	/**
	 * 日志内容 : logs
	 * 
	 * @return 
	 */
	public void setLogs (String logs) {
		this.logs = logs;
	}
	}
