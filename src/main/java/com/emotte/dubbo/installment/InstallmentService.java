package com.emotte.dubbo.installment;

public interface InstallmentService {

	/**
	 * 验证银行四要素(1.客户，加风控  2.服务人员 )
	 * {customName:张三，applyType：1，customIdCard：1304221212123131，bankName：建设银行，bankAccountNum：62212155451212，bankMobile：15311124548}
	 * @param json
	 * @return
	 * {code:0,message:成功}
	 * @throws Exception
	 */
	public String validateHaiJinBaoLi(String json);

	/**
	 管家操作合同终止
	 * <p>Title: haijinblAgreementEnd</p>

	 * <p>Description: </p>

	 * @param json
	 * @return

	 */
	public String haijinblAgreementEnd(String json);	
}
