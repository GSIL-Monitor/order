package com.emotte.order.order.service;

import java.util.Map;

public interface BathInserService {
	/**
	 * 根据商品code码和商品的价格体系，来获取对应的商品详细
	 * @param product_code 城市商编码
	 * @param price_code   商品价格体系  如（会员价、解决方案价 等）
	 * @return 返回查询到的商品详细
	 */
	public Map<String,Object> getDetail(String product_code,String  price_code);
	public String queryIsSingleBatch(String product_code);
	
	
	/**
	 * 根据客户电话，获取客户的信息，如果客户已存在，则获取对应的地址信息，如果不存在，则新建客户和地址信息
	 * @param mobile 	客户电话
	 * @param name 		 客户姓名
	 * @param province 	省
	 * @param city		市
	 * @param district	区县
	 * @param address   地址
	 * @return  返回查询的结果
	 */
	public Map<String,Object> getUserDetail(String mobile,String name,String province,String city,String district,String address);
	public Map<String,Object> getUserDetailNew(String orderCode,String mobile,String name,String province,String city,String district,String address);
}
