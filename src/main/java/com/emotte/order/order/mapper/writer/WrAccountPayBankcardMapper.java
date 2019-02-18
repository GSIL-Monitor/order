package com.emotte.order.order.mapper.writer;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.WrBaseMapper;

import com.emotte.order.order.model.AccountPayBankcard; 
@Component("wrAccountPayBankcardMapper") 
public interface WrAccountPayBankcardMapper extends WrBaseMapper{ 

	public int insertAccountPayBankcard(AccountPayBankcard accountpaybankcard);

	public int updateAccountPayBankcard(AccountPayBankcard accountpaybankcard);

} 
