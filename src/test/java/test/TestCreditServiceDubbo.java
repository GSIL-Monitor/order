package test;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emotte.dubbo.credit.CreditService;
import com.emotte.dubbo.order.PersonnelScheduleService;

import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/application*.xml" })
public class TestCreditServiceDubbo {

	@Resource
	private CreditService creditService;
	
	@Resource
	private PersonnelScheduleService personnelScheduleService;
	

	@org.junit.Test
	public void checkIdCard() {
		String name = "王丽";
		String cardNum = "370629197211115723";
		Long flagId = 2L;
		String result = this.creditService.checkIdCard(name, cardNum, flagId);
		System.err.println("======================================\n"+result+"\n======================================");
	}
	
	
	@org.junit.Test
	public void testQueryConflictSchedule() {
		JSONObject jo1 =  new JSONObject();
		JSONObject jo2 =  new JSONObject();
		jo1.accumulate("startDate", "20180815");
		jo1.accumulate("endDate", "20180815");
		jo1.accumulate("startTime", "73000");
		jo1.accumulate("endTime", "83000");
		jo1.accumulate("empId","415497352749463");
		jo2.accumulate("personnelSchedule", jo1.toString());
		String result = this.personnelScheduleService.queryConflictSchedule(jo2.toString());
		System.err.println("======================================\n"+result+"\n======================================");
	}
	
}
