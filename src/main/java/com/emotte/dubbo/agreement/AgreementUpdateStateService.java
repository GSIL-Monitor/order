package com.emotte.dubbo.agreement;

public interface AgreementUpdateStateService {
	
	/**
	 * 
	* @Description: 修改合同审核状态
	* @author lidenghui
	* @date 2018年9月19日 下午2:17:20 
	* @version
	* @param json={'agreementCode':'15168238977LRMG',state:'1',updateBy:'2'}state(1,未处理,2,审核通过,3,驳回)
	* @return {'code':'0','msg':'请求操作成功'} 0成功1失败
	 */
	String agreementUpdateState(String json);

}
