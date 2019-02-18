package com.emotte.dubbo.agreement.impl;

import javax.annotation.Resource;

import com.emotte.dubbo.agreement.AgreementUpdateStateService;
import com.emotte.kernel.exception.BaseException;
import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.order.mapper.writer.WrAgreementMapper;
import com.emotte.order.order.model.Agreement;

import net.sf.json.JSONObject;


public class AgreementUpdateStateServiceImpl implements AgreementUpdateStateService {
	
	@Resource
	private WrAgreementMapper wrAgreementMapper;
	
	@Override
	public String agreementUpdateState(String json) {
		//{'agreementCode':'15168238977LRMG',state:'1',updateBy:'2'}
		LogHelper.info(getClass() + ".agreementUpdateState()", "接收的数据:" + json);
		String result = "{'code':'1','msg':'请求操作失败'}";
		JSONObject jsonObject=JSONObject.fromObject(json);
		String reason=null;	
		Agreement agreement=new Agreement();
		try {
			String agreementCode=(String) jsonObject.get("agreementCode");
			String state=(String) jsonObject.get("state");
			String updateBy=(String)jsonObject.get("updateBy");
			if(state.equals("3")){
				reason=(String)jsonObject.get("reason");
			}
			if(!"".equals(agreementCode) && !"".equals(state) && !"".equals(updateBy) && !"".equals(reason)){
				agreement.setAgreementCode(agreementCode);
				agreement.setCheckStatus(Integer.valueOf(state));
				agreement.setUpdateBy(Long.valueOf(updateBy));
				agreement.setReason(reason);
				int num =wrAgreementMapper.updateAgreementState(agreement);
				if(num > 0){
					result = "{'code':'0','msg':'请求操作成功'}";
				}
			}else{
				throw new BaseException("参数为空!!");
			}
		} catch (Exception e) {
			LogHelper.info(getClass() + ".agreementUpdateState()", e.getMessage());
		}
		LogHelper.info(getClass() + ".agreementUpdateState()返回去的数据",result);
		return result;
	}

}
