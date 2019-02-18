package com.emotte.dubbo.order;

import javax.annotation.Resource;

import org.junit.Test;

import com.emotte.BaseTestService;

public class OrderInterfaceServiceTest extends BaseTestService {
	
	@Resource
	private OrderInterfaceService service;
	
	@Resource
	public PersonInterviewService personInterviewService;
	
	@Test
	public void testInsertOrUpdateOrder() {
		//测试下单接口
		//String json="{\"handle\":\"1\",\"order\":[{\"orderSourceId\":20180008,\"orderType\":\"100200160005\",\"receiverCityCode\":\"101001001001\",\"priceType\":\"20000002\",\"receiverMobile\":\"15200000000\",\"receiverProvince\":\"北京\",\"receiverAddress\":\"万商大厦石景山游乐园\",\"createBy\":133713642960759,\"orderStatus\":1,\"cateType\":1,\"city\":\"101001001\",\"ip\":\"[图片]192.168.211.114\",\"startTime\":\"2018-05-17 16:30\",\"critical\":2,\"receiverCity\":\"北京市\",\"payStatus\":\"20110001\",\"receiverArea\":\"东城区\",\"userId\":133713642960759,\"userSex\":\"1\",\"userMobile\":\"15210351381\",\"orderChannel\":\"793816172420965\",\"receiverName\":\"杨阳\",\"longitude\":\"116.225883\",\"latitude\":\"39.911888\",\"otype\":\"2\"}],\"lined\":[{\"updateBy\":133713642960759,\"startTime\":\"2018-05-17 16:30\",\"createBy\":133713642960759,\"version\":1}],\"orderItem\":[{\"updateBy\":133713642960759,\"valid\":1,\"productCode\":\"101001001821834008763414\",\"saleType\":1,\"quantity\":4,\"nowPrice\":\"268.00\",\"createBy\":133713642960759,\"productName\":\"家庭搬家小型车\",\"version\":1}],\"account\":[{\"updateBy\":133713642960759,\"accountAmount\":\"268.00\",\"startTime\":\"2018-05-16 16:35:35\",\"payStatus\":\"20110001\",\"isManual\":2,\"payType\":3,\"payKind\":2,\"bussStatus\":1,\"endTime\":\"2018-05-16 16:35:35\",\"createBy\":133713642960759,\"version\":0,\"isBackType\":2}],\"orderItemServer\":[{\"updateBy\":133713642960759,\"startTime\":\"2018-05-17 16:30\",\"valid\":1,\"isShowHhr\":false,\"personNumber\":1,\"address\":\"东城区万商大厦石景山游乐园\",\"interviewAddress\":\"东城区万商大厦石景山游乐园\",\"createBy\":133713642960759,\"version\":0}]}\r\n" +"";
		String json ="{\"handle\":\"1\",\"account\":[{\"accountAmount\":100}],\"order\":[{\"isShow\":1,\"cancelText\":\"sfjsdjfdssdfdfdfd\",\"recommendUserId\":\"123456789\",\"orderPushType\":\"1\",\"userCity\":\"\",\"orderType\":\"100300010003\",\"threeMatchLock\":1,\"critical\":2,\"userProvince\":\"\",\"city\":\"101001001\",\"latitude\":39.9122,\"receiverCityCode\":\"101001001001\",\"orderStatus\":2,\"receiverProvince\":\"北京\",\"remark\":\"\",\"receiverCity\":\"北京市\",\"orderChannel\":\"793816172420965\",\"userCountry\":\"\",\"receiptTime\":\"2018-06-01 09:38:00\",\"cateType\":1,\"followBy\":\"\",\"userMobile\":\"15474478568\",\"userEmail\":\"\",\"startTime\":\"2018-06-01 09:38:00\",\"userCityCode\":\"101001001001\",\"longitude\":116.226225,\"orderSourceId\":\"20180001\",\"receiverName\":\"测试\",\"ip\":\"192.168.211.124\",\"receiverMobile\":\"15666652536\",\"priceType\":\"20000005\",\"receiverArea\":\"东城区\",\"userName\":\"\",\"userId\":103811599829607,\"parentId\":191958020995703,\"userAddress\":\"\",\"receiverAddress\":\"万商大厦Jack哦OK了家里好哈哈啊的啦几哈好考虑\",\"followDept\":\"\",\"createBy\":103811599829607,\"followTime\":\"\",\"createDept\":\"\",\"payStatus\":20110003}],\"orderItem\":[{\"nowPrice\":98,\"createBy\":103811599829607,\"productCode\":\"101001001151475909448901\",\"quantity\":1,\"orderId\":191958020995703,\"saleType\":1,\"productName\":\"有机柴鸡蛋\"}]}";
		String r = service.insertOrUpdateOrder(json);
		System.err.println("测试下单接口："+r);
	}
	
	@Test
	public void  testp(){
		String json="{'order':'622289475223093',personIds:'123456,789456,12312'}";
		personInterviewService.personInterview(json);
	}
	
}
