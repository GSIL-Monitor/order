package com.emotte.order.order.mapper.reader;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.ThreeOrderInProductModel;

@Component("reThreeOrderProCategoryMapper")
public interface ReThreeOrderProCategoryMapper extends ReBaseMapper{
	
	/**
	 * 根据产品三级类别获得产品 
	 */
	public List<ThreeOrderInProductModel> getProductList(ThreeOrderInProductModel threeOrderInProduct);
	
	/**
	 * 根据商品的codes获得产品信息
	 */
	public List<ThreeOrderInProductModel> getProductListByCodes(Map<String,Object> params);
	
}
