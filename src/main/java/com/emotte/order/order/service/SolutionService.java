package com.emotte.order.order.service;
import java.math.BigDecimal;
import java.util.List;

import com.emotte.order.order.model.Solution;

public interface SolutionService{
    
	/**
	 * 
	 * @param id 解决方案订单ID
	 * @return
	 */
	public Solution loadSolution(Long id);
	/**
	 * 查询解决方案下单表
	 * @param solution 解决方案实体
	 * @return  查询解决方案订单表
	 */
	public List<Solution> querySolutionItem(Solution solution);

	/**
	 * 解决方案未生成执行订单的排期总金额
	 * @param solution
	 * @return
	 */
	public BigDecimal querySolutionPlanFee(Solution solution);
}