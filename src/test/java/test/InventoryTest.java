package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emotte.order.order.service.OrderService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/application*.xml"})
public class InventoryTest {
	@Resource
	private  OrderService orderService;
	

	@Test
	public  void test() {
		
		String result =orderService.reduceInventory("100200020001", "100200020001", "2017-06-19", "10:00", "12:00",1,12313427808432l);
		System.out.println(result);
	}
	 
}
