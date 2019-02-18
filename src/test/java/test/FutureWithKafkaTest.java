package test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.emotte.kernel.arch.future.FutureWithKafka;
import com.emotte.kernel.arch.model.ResultData;
import com.emotte.kernel.redis.EJedisPool;
import com.emotte.kernel.spring.SpringContext;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/application*.xml"})
public class FutureWithKafkaTest {
	

	@Test
	public  void test(){
		EJedisPool pool=(EJedisPool)SpringContext.getBean("jedisPool");
		FutureWithKafka futureWithKafka=new FutureWithKafka("order", pool);
		ResultData<String> r=futureWithKafka.setData("lisi", "505474805658750");
		String s=futureWithKafka.getData(r);
		System.out.println(s);
	}
	
	
	
	
}