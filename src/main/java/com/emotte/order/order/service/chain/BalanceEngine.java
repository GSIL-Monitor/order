package com.emotte.order.order.service.chain;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.emotte.order.order.service.chain.model.ChainModel;
import com.emotte.order.util.LogHelper;

@Component
public class BalanceEngine implements BeanPostProcessor{

	@Resource
	private Chains chain;
	
	public List<Map<String, String>> doBalance(ChainModel model) throws Exception {
		List<Map<String, String>> list = chain.doChain(model, chain, 0);
		return list;
	}
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if(bean instanceof IBalanceChain ) {
			//装载分成器
			chain.addFilter((IBalanceChain)bean);
			LogHelper.info(getClass(), "【装载对账器】" + bean.getClass() + " 装载完毕");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}


}
