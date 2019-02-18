package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.Solution; 
@Component("wrSolutionMapper") 
public interface WrSolutionMapper extends WrBaseMapper{ 

	public int updateSolution(Solution solution);

} 
