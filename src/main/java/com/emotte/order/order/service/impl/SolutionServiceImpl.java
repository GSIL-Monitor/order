package com.emotte.order.order.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emotte.order.order.mapper.reader.ReSolutionMapper;
import com.emotte.order.order.model.Solution;
import com.emotte.order.order.service.SolutionService;

@Service("solutionService")
@Transactional
public class SolutionServiceImpl implements SolutionService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ReSolutionMapper reSolutionMapper;
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public Solution loadSolution(Long id) {
		Solution custSolution = this.reSolutionMapper.loadSolution(id);
		return custSolution;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<Solution> querySolutionItem(Solution solution) {
		List<Solution> list = this.reSolutionMapper.querySolutionItem(solution);
		return list;
	}

	@Override
	public BigDecimal querySolutionPlanFee(Solution solution) {
		BigDecimal planFeeSum = this.reSolutionMapper.querySolutionPlanFee(solution);
		return planFeeSum;
	}


}