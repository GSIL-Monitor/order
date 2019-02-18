package com.emotte.order.order.service.impl;	

import javax.annotation.Resource;
import java.util.List;
import org.apache.log4j.Logger;
import org.wildhorse.server.core.exception.BaseException;
import org.wildhorse.server.core.model.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emotte.order.order.model.AfterFees;
import com.emotte.order.order.model.Payfee;
import com.emotte.order.order.mapper.reader.ReAfterFeesMapper;
import com.emotte.order.order.mapper.writer.WrAfterFeesMapper;
import com.emotte.order.order.service.AfterFeesService;

@Service("afterFeesService")
@Transactional
public class AfterFeesServiceImpl implements AfterFeesService {
	Logger log = Logger.getLogger(this.getClass());

	@Resource
	private ReAfterFeesMapper reAfterFeesMapper;

	@Resource
	private WrAfterFeesMapper wrAfterFeesMapper;

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public AfterFees loadAfterFees(Long id) {
		return this.reAfterFeesMapper.loadAfterFees(id);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
	public List<AfterFees> queryAfterFees(AfterFees afterFees, Page page) {
		if (page.needQueryPading()) {
			page.setTotalRecord(reAfterFeesMapper.countAfterFees(afterFees));
		}
		afterFees.setBeginRow(page.getFilterRecord().toString());
		afterFees.setPageSize(page.getPageCount().toString());
		return this.reAfterFeesMapper.queryAfterFees(afterFees);
	}

	@Override
	public void insertAfterFees(AfterFees afterFees) {
		this.wrAfterFeesMapper.insertAfterFees(afterFees);
	}

	@Override
	public void updateAfterFees(AfterFees afterFees) {
		int returnValue;
		try {
			returnValue = this.wrAfterFeesMapper.updateAfterFees(afterFees);
			if (1 != returnValue) {
				throw new BaseException("update fail!");
			}
		} catch (Exception e) {
			log.error("updateAfterFees:" + e);
			throw new BaseException(e);
		}
	}

	@Override
	public AfterFees queryPayMoney(AfterFees afterFees) {
		return this.reAfterFeesMapper.queryPayMoney(afterFees);
	}

	@Override
	public Payfee queryAccountPay(AfterFees afterFees) {
		
		return this.reAfterFeesMapper.queryAccountPay(afterFees);
	}

	@Override
	public List<AfterFees> queryMembershipFee(AfterFees afterFees) {
		return this.reAfterFeesMapper.queryMembershipFee(afterFees);
	}
	
	@Override
	public List<AfterFees> queryNotIousSalaryMoney(AfterFees afterFees) {
		return this.reAfterFeesMapper.queryNotIousSalaryMoney(afterFees);
	}

	@Override
	public List<AfterFees> queryInstallmentFee(AfterFees afterFees) {
		return this.reAfterFeesMapper.queryInstallmentFee(afterFees);
	}

}
