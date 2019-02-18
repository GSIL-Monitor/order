package com.emotte.dubbo.order;

import javax.annotation.Resource;

import org.junit.Test;


import com.emotte.BaseTestService;

/**
 * 
* @ClassName: PersonInterviewServiceTest 
* @Description: 测试约Ta面试接口
* @author lidenghui 
* @date 2018年3月29日 下午2:13:08 
*
 */
public class PersonInterviewServiceTest extends BaseTestService{
	
	@Resource
	public PersonInterviewService personInterviewService;
	
	@Test  //'397666325473319,679614472978727,201672654234311' //月嫂类型{"orderId":"187511909821223","personIds":"831067868156100,109496839752741"}
	public void  test(){
		String json="{'orderId':'187511909821223',personIds:'831067868156100,109496839752741'}";
		String result=personInterviewService.personInterview(json);
		System.out.println(result);
	}
	@Test  //{"orderId":"186209102885879","personIds":"248122032416100,308926371462100"}
	public void  test2(){
		String json="{'orderId':'186209102885879','personIds':'248122032416100,308926371462100'}";
		String result=personInterviewService.personInterview(json);
		System.out.println(result);
	}
	
	@Test  //'769310045753877,310268402891541,804330717231125' //钟点工类型
	public void  test1(){
		String json="{'order':'478159574975127',personIds:'769310045753877,310268402891541,804330717231125'}";
		String result=personInterviewService.personInterview(json);
		System.out.println(result);
	}
}
