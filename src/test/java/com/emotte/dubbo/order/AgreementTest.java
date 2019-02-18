package com.emotte.dubbo.order;

import javax.annotation.Resource;

import org.junit.Test;

import com.emotte.BaseTestService;
import com.emotte.dubbo.agreement.AgreementUpdateStateService;

public class AgreementTest extends BaseTestService{
	
	@Resource
	public  AgreementUpdateStateService agreementUpdateStateService;
	
	@Test
	public void  test3(){
		//String json="{'agreementCode':'18334777015ZVTJ',state:'3',updateBy:'1',reason:'真的烦'}";
		String json=" ";
		String result=agreementUpdateStateService.agreementUpdateState(json);
		System.out.println(result);
	}

}
