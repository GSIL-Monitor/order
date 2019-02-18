package com.emotte.order.order.mapper.writer;

import com.emotte.order.order.model.AccountPay;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import java.util.List;
@Component("wrAccountPayMapper") 
public interface WrAccountPayMapper extends WrBaseMapper{ 

	public int insertAccountPay(AccountPay accountpay);

	public int updateAccountPay(AccountPay accountpay);
	
	public int updateAccountList(List<AccountPay> list);

	/**
	 * 根据回访结果修改退款结算单审核状态
	 *
	 * @param accountId
	 * @param auditFlag
	 * @param updateBy
	 * @Author zhanghao
	 * @Date 20181229
     */
	void updateAuditFlagByCallBackStatus(@Param("accountId") Long accountId, @Param("auditFlag") String auditFlag, @Param("updateBy") Long updateBy);

	/**
	 * 删除结算单
	 *
	 * @param accountPay
	 * @Author zhanghao
	 * @Date 20181229
     */
	void deleteAccountPayById(AccountPay accountPay);
}