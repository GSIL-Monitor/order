package com.emotte.order.order.mapper.reader;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.ThreeOrderBaseCity;

@Component("reThreeOrderBaseCityMapper")
public interface ReThreeOrderBaseCityMapper extends ReBaseMapper {
	/**
	 * 根据cityName获得cityCode 
	 */
	public ThreeOrderBaseCity getBaseCityCodeByName(ThreeOrderBaseCity baseCity);
	
	
}
