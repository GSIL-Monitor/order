package test;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emotte.order.order.model.Order;
import com.emotte.order.order.service.TestService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/application*.xml" })
public class TestTransactional {

	@Resource
	private TestService testService;
	
	@org.junit.Test
	public void Test1(){
		Order order = new Order();
		order.setId(971449823453718L);
		order.setUpdateBy(2222222222L);
		this.testService.update2(order);
	}

}
