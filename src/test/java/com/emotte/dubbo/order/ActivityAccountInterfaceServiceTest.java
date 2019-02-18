package com.emotte.dubbo.order;

import javax.annotation.Resource;

import org.junit.Test;
import com.emotte.BaseTestService;
import com.emotte.dubbo.installment.CustomerInfoService;
import com.emotte.dubbo.order.impl.OrderInterfaceServiceImpl;

/**
 * 
* @ClassName: PersonInterviewServiceTest 
* @Description: 测试约Ta面试接口
* @author lidenghui 
* @date 2018年3月29日 下午2:13:08 
*
 */
public class ActivityAccountInterfaceServiceTest extends BaseTestService{
	
	@Resource
	public OrderInterfaceServiceImpl orderInterfaceServiceImpl;
	
	@Resource
	public  CustomerInfoService customerInfoService;
	
	@Test
	public void  test(){
		String json="{\"updateBy\":649010343822807,\"accountAmount\":\"30000.00\",\"accountId\":757868603275079,\"isManual\":2,\"type\":\"20460002\",\"endTime\":\"2018-06-08 14:02:14\",\"createBy\":649010343822807,\"version\":0,\"startTime\":\"2018-06-08 14:02:14\",\"payStatus\":\"20110001\",\"payType\":3,\"prestoreId\":634396088133655,\"payKind\":2,\"bussStatus\":1,\"orderId\":492772743154743,\"isBackType\":2}";
		String result=orderInterfaceServiceImpl.insertActivityAccount(json);
		System.out.println(result);
	}
	@Test
	public void  test2(){
		String json = "{\"accountreceivdate\":\"\",\"agEndDate\":null,\"agEndDateStr\":\"2018-12-18 10:20:10\",\"agServiceIdcard\":\"342923197011291014\",\"agServiceName\":\"陈洛生\",\"agSignDate\":null,\"agSignDateStr\":\"2018-12-18 10:18:16\",\"agStartDate\":null,\"agStartDateStr\":\"2018-12-18 10:18:20\",\"applyType\":0,\"bankAccountNum\":\"6217000010128222166\",\"bankMobile\":\"16619945679\",\"bankName\":\"中国银行\",\"batchId\":\"\",\"bindNo\":\"\",\"callbackContract\":\"\",\"callbackContractUpdatetime\":null,\"callbackContractUpdatetimeStr\":\"\",\"callbackPayload\":\"\",\"callbackPayloadUpdatetime\":null,\"callbackPayloadUpdatetimeStr\":\"\",\"callbackPeriod\":\"\",\"callbackPeriodUpdatetime\":null,\"callbackPeriodUpdatetimeStr\":\"\",\"contractCode\":\"13121201111SWPA\",\"contractImageUrl\":\"\",\"createBy\":1,\"createTime\":null,\"createTimeStr\":\"\",\"custId\":554925767523559,\"customIdcard\":\"220182199007017617\",\"customName\":\"老老四\",\"eachPeriodMoney\":111,\"exception\":0,\"flag\":\"\",\"ftpContractImageUrl\":\"\",\"ftpIdcardImageUrl\":\"\",\"hegeerrmsg\":\"\",\"hegeflag\":\"\",\"hjblOrderId\":\"\",\"id\":0,\"idcardImageUrl\":\"\",\"interviewId\":0,\"interviewStartTime\":null,\"interviewStartTimeStr\":\"\",\"isUnbinding\":0,\"log\":\"\",\"oOrderType\":\"100200010001\",\"oauthCode\":\"\",\"orderId\":\"756922592836696\",\"stagingNumber\":\"1\",\"state\":0,\"submittime\":null,\"submittimeStr\":\"\",\"updateBy\":0,\"updateContractTime\":null,\"updateContractTimeStr\":\"\",\"updateInfoTime\":null,\"updateInfoTimeStr\":\"\",\"updateTime\":null,\"updateTimeStr\":\"\",\"usrBusiAgreementId\":\"\",\"usrPayAgreementId\":\"\",\"valid\":0,\"verifyCode\":\"\",\"version\":0,\"whiteTotalMoney\":111,\"zhangkuanerrmsg\":\"\",\"zhangkuanflag\":\"\"}";
		String result=customerInfoService.saveCustomerInfo(json);
		System.err.println(result);
	}

}
