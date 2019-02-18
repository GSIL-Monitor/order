package com.emotte.order.order.mapper.reader;

import java.util.List;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.ProductDivide; 
@Component("reProductDivideMapper") 
public interface ReProductDivideMapper extends ReBaseMapper{ 
	public List<ProductDivide> queryProductDivide(ProductDivide productdivide); 
} 
