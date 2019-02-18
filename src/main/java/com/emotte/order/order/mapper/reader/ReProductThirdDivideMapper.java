package com.emotte.order.order.mapper.reader;

import java.util.List;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.ProductThirdDivide; 
@Component("reProductThirdDivideMapper") 
public interface ReProductThirdDivideMapper extends ReBaseMapper{ 
	public List<ProductThirdDivide> queryProductThirdDivide(ProductThirdDivide productthirddivide); 
} 
