package test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emotte.dubbo.account.AccountService;
import com.emotte.dubbo.activities.service.GJBAPPCardInterfaceService;
import com.emotte.dubbo.activities.service.SolutionDubboCardService;
import com.emotte.dubbo.activities.solution.SolutionOrderInterfaceService;
import com.emotte.dubbo.custom.VipRecordService;
import com.emotte.dubbo.order.OrderInterfaceService;
import com.emotte.order.order.mapper.reader.ReOrderBaseMapper;
import com.emotte.order.order.mapper.reader.ReOrderMapper;
import com.emotte.order.order.mapper.writer.WrOrderMapper;
import com.emotte.order.order.model.Order;
import com.emotte.order.order.model.OrderUser;
import com.emotte.order.order.service.AccountPayService;
import com.emotte.order.order.service.ItemInterviewService;
import com.emotte.order.order.service.OrderDistributeService;
import com.emotte.order.util.ConfigComm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/application*.xml"})
public class PubTestDemo {
	/*@Resource
	TestService testService;

	@org.junit.Test
	public void testDubbo(){
		String tt = testService.testDubbo("测试接口");
		System.out.println(tt);
	}*/
	

	@Resource
	private AccountPayService accountPayService;
	@Resource
	private AccountService accountService;
	@Resource
	private GJBAPPCardInterfaceService gJBAPPCardInterfaceService;
	@Resource
	private OrderInterfaceService orderInterfaceService;
	@Resource
	private SolutionDubboCardService solutionDubboCardService;
	@Resource
	private SolutionOrderInterfaceService solutionOrderInterfaceService;
	@Resource
	private ItemInterviewService itemInterviewService;
	@Resource
	private VipRecordService vipRecordService;
	@Resource
	private ReOrderBaseMapper reOrderBaseMapper;
	@Resource
	private ReOrderMapper reOrderMapper;
	@Resource
	private WrOrderMapper wrOrderMapper;
	
	@Resource
	private OrderDistributeService orderDistributeService;

	@org.junit.Test
	public void aa(){
		System.out.println("11111111111");
		System.out.println(ConfigComm.getInstance("config.properties").get("imgUploadPath"));

	}

	@org.junit.Test
	public void testOrderCon(){

		HashMap mapCondition = new HashMap();
		mapCondition.put("start_time", "2016-05-02");
		mapCondition.put("end_time", "2016-06-01");
		mapCondition.put("order_id", 277991562829156l);				
		List listMap = this.itemInterviewService.querySalaryCondition(mapCondition);
		if(listMap!=null&&listMap.size()>0){
			for (int j = 0; j < listMap.size(); j++) {
				Map map = (Map) listMap.get(j);
				System.out.println("服务人员ID："+map.get("PERSONID")+"开始时间："+map.get("STARTTIME")+" 结束时间："+map.get("ENDTIME"));
			}
		}
		System.out.println("测试延续性服务服务人员服务费条件计算");

	}
	
	
	@org.junit.Test
	public void testReMatchOrder(){
		List<Order> list = this.reOrderMapper.queryOrderReMatch(new Order());
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Order orderReMatch = list.get(i);
				OrderUser userMa=null;
				userMa = this.reOrderBaseMapper.queryOrderUserFollowByOrg(orderReMatch) ;
				if(userMa!=null&&userMa.getId()!=null){
					orderReMatch.setRechargeDept(userMa.getId());
					this.wrOrderMapper.updateOrder(orderReMatch);
				}
			}
		}

	}
	
	@org.junit.Test
	public void testOrderBaojie(){
		
	}
	
	
	@org.junit.Test
	public void testPiPei(){
		System.out.println("测试匹配");
	}
	
	@org.junit.Test
	public void testFenfa(){
		Order order = new Order();
		order.setOrderChannel("608446272420405");
		order.setProductCode("101002001386549852351156");
		order.setCateType(2);
		order.setLongitude("117.87134");
		order.setLatitude("38.945667");
		order.setCity("101002001");
		orderDistributeService.distribute (order);
		System.out.println(order.getRechargeDept());
		
	}
	
}