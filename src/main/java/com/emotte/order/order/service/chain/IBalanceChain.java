package com.emotte.order.order.service.chain;

import java.util.List;
import java.util.Map;

import com.emotte.order.order.service.chain.model.ChainModel;



/**
 * 
 * @author weixd
 * @date 20180726
 */
public interface IBalanceChain {
	
	public List<Map<String, String>> doChain (ChainModel model, Chains chain, int index)throws Exception;
}
