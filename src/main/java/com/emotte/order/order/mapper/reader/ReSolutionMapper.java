package com.emotte.order.order.mapper.reader;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.Solution;

  @Component("reSolutionMapper")
public interface ReSolutionMapper extends ReBaseMapper{
    
	public Solution loadSolution(Long id);
	
	public List<Solution> querySolutionItem(Solution solution);

	public BigDecimal querySolutionPlanFee(Solution solution);
	
	public List<Solution> querySolutionAgreement(Solution solution);

	  /**
	   * 根据用户手机号查询关联方案订单表
	   *
	   * @param phoneNumber	用户手机号
	   * @return
	   */
	  List<Solution> findSolutionByPhone(@Param("phoneNumber") String phoneNumber);
  }