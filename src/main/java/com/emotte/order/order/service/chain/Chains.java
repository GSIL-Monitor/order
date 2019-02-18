package com.emotte.order.order.service.chain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.emotte.order.order.service.chain.model.ChainModel;

/**
 * 
 * @author weixd
 * @date 20180726
 */
@Component
public class Chains implements IBalanceChain {

	private List<IBalanceChain> listChain = new ArrayList<>();
	
	public Chains addFilter(IBalanceChain chain) {
		listChain.add(chain);
		return this;
	}
	
	@Override
	public List<Map<String, String>> doChain (ChainModel model, Chains chain, int index) throws Exception{
		if(index == listChain.size()) throw new Exception("未找到模板");
		IBalanceChain balanceChain = listChain.get(index);
		index++;
		List<Map<String, String>> list = balanceChain.doChain(model, chain, index);
		index--;
		return list;
	}
	
}
