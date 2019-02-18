package com.emotte.dubbo.wms;

/**
 * 分包接口
 * @author Administrator
 *
 */
public interface SubPackageService {
	// 新增订单时调用,传订单id
	public String subPackage(Long id);
	// 取消订单时调用,传订单id
	public String cancelPackage(Long id,Long updateBy);
	//订阅物流
	public String subscription(String logisticsCompany,String LogisticsNumber);
}
