package com.emotte.order.order.mapper.reader;

import java.util.List;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.AfterFees;
import com.emotte.order.order.model.Payfee;

@Component("reAfterFeesMapper")
public interface ReAfterFeesMapper extends ReBaseMapper {

	public AfterFees loadAfterFees(Long id);

	public List<AfterFees> queryAfterFees(AfterFees afterFees);

	public Integer countAfterFees(AfterFees afterFees);

	public AfterFees queryPayMoney(AfterFees afterFees);

	public Payfee queryAccountPay(AfterFees afterFees);

	public List<AfterFees> queryMembershipFee(AfterFees afterFees);
	
	public List<AfterFees> queryNotIousSalaryMoney(AfterFees afterFees);

	public List<AfterFees> queryInstallmentFee(AfterFees afterFees);

}