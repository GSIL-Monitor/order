package com.emotte.order.order.mapper.reader;

import org.springframework.stereotype.Component;
import org.wildhorse.server.core.dao.mybatis.model.ReBaseMapper;

import com.emotte.order.order.model.AccountPayBankcard; 
@Component("reAccountPayBankcardMapper") 
public interface ReAccountPayBankcardMapper extends ReBaseMapper{ 
	public AccountPayBankcard queryAccountPayBankcard(AccountPayBankcard accountpaybankcard); 
} 
