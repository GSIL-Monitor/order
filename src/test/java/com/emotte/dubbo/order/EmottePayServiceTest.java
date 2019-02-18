package com.emotte.dubbo.order;

import javax.annotation.Resource;

import org.junit.Test;

import com.emotte.BaseTestService;
import com.emotte.dubbo.emottepay.EmottePayService;
import com.emotte.dubbo.installment.InstallmentService;

public class EmottePayServiceTest extends BaseTestService{
	
	@Resource
	private EmottePayService emottePayService;
	
	@Resource 
	private InstallmentService installmentService;
	
	//659253531701416	AL
	@Test
	public  void test(){
		String json="{'payFeeId':'659253531701416','payMode':'AL'}";
		String result=emottePayService.lianDongZhuSaoPay(json);
		System.err.println(result);

	}
	
	@Test
	public  void test1(){
		String json="{'userMoble':'16619945679','customName':'刘呼呼','bankAccountNum':'621666100000467000','customIdCard':'110107198909141155','applyType':'1'}";
		String result=installmentService.validateHaiJinBaoLi(json);
		System.err.println(result);
		
	}
	
	

}
