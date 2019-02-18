package com.emotte.order.order.mapper.reader;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

/**
 * 处理订单批量导入mapper
 * @author chaigy
 *
 */
@Component("reBathMapper")
public interface ReBathMapper  extends ReBaseMapper{
	/**
	 * 通过城市商品code和价格体系，获取商品详情
	 * @param product_code 城市商品code
	 * @param price_code   价格体系
	 * @return	返回查询的结果
	 */
	public Map<String,Object> getDetail(String product_code, String price_code);
	public String queryIsSingleBatch(String product_code);
	
	/**
	 * 通过客户电话，和客户地址获取客户的信息
	 * @param mobile 	客户电话
	 * @param address 	客户地址
	 * @return
	 */
	public Map<String,Object> getUseDetail(String mobile,String address);
	public Map<String,String> getUseDetailNew(String mobile,String address);
	
	
}
