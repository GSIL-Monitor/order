package com.emotte.order.order.mapper.writer;

import com.emotte.order.order.model.AccountPay;
import org.springframework.stereotype.Component;

import com.emotte.order.order.model.AccountActivity;
import com.emotte.order.order.model.Payfee;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

@Component("wrPayfeeMapper")
public interface WrPayfeeMapper extends WrBaseMapper {

	public void insertPayfee(Payfee payfee);

	public int updatePayfee(Payfee payfee);

	public void insertAccount(Payfee payfee);

	public int updateAccount(Payfee payfee);
	
	public void updatePayfeeByAccountId(Long accountId);
	public void updateAccountById(Long accountId);
	public void updateAccountByOrderId(Long orderId);

	public int updateOtherDeal(Payfee payfee);

	public void updateOtherRevenue(Payfee payfee);

	//增加t_order_activity_account表
	public int insertAccountActivity(AccountActivity accountActivity1); 
	
	/**
	 * 
	 * @Description: 修改结算单  
	 * @param accountId      
	 * @return: void
	 * @author:ZhouXin
	 * @Date:2019年1月21日
	 * @throws
	 */
	public void updateAccountType(Long accountId);

	/**
	 * 根据结算单ID删除缴费单信息
	 *
	 * @param accountPay
	 * @Author zhanghao
	 * @Date 20181229
     */
	void deletePayfeeByAccountId(AccountPay accountPay);
}