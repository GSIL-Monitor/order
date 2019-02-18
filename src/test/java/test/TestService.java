package test;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emotte.kernel.helper.LogHelper;
import com.emotte.order.order.service.OrderService;
import com.emotte.order.timer.AccountPayTask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/application*.xml" })
public class TestService {

	@Resource
	private OrderService orderService;
	
	@Resource
	private AccountPayTask accountPayTask;


	@Test
	public void testInsertActivityAccount() {
		// 查询解决方案负责人和负责部门{RECHARGEBY=95898, RECHARGEDEPT=12619003}
		Map<String, Object> rp = null;
		try {
		rp = this.orderService.querySolutionResponsiblePerson(741768303922198L);
		System.err.println(rp == null || (rp.get("RECHARGEBY") == null || "0".equals(rp.get("RECHARGEBY").toString())) || (rp.get("RECHARGEDEPT") == null || "0".equals(rp.get("RECHARGEDEPT").toString())));
		System.err.println(rp.get("rechargeBy"));
		System.err.println(rp.get("rechargeDept"));
		System.err.println(rp.get("RECHARGEBY"));
		System.err.println(rp.get("RECHARGEDEPT"));
		} catch (Exception e) {
			LogHelper.info(getClass(), "定时器更新解决方案负责人失败"+e);
		}
		System.err.println(rp);
	}
	
	@Test
	public void testPayCompareFeeSum(){
		boolean flag = orderService.queryPayCompareFeeSum(Long.parseLong("564065526356758"));
		System.err.println(flag+"2222222");
	}
}









