package com.emotte.order.order.service.chain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.emotte.order.order.service.chain.model.ChainModel;

/**
 * 
 * @author weixd
 * @date 20180726
 */
public abstract class TopBalanceChain implements IBalanceChain {
	public static final String TOP_RESTRICTSTR = ""; // $NON_NLS_1$
	public String[] top_restricts = new String[]{};
	
	@Override
	public List<Map<String, String>> doChain(ChainModel model, Chains chain, int index) throws Exception {
		List<Map<String, String>> list = new ArrayList<>();
		if (validate(model)) {// 校验
			list = balance(model);// 业务处理
			return list;
		}
		list = chain.doChain(model, chain, index);
		return list;
	}

	abstract public List<Map<String, String>> balance(ChainModel model);

	abstract public boolean validate(ChainModel model);

	public boolean propValidate(Map<String, String> data) throws Exception {
		if(top_restricts.length > 0) {
			for (String restrict : top_restricts) {
				String val = data.get(restrict);
				if (null == val)
					throw new Exception("模板不正确，请确认是否包含" + TOP_RESTRICTSTR);
			}
		}
		return true;
	}

}
