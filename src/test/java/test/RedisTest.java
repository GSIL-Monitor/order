package test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emotte.kernel.redis.EJedisPool;
import com.emotte.kernel.spring.SpringContext;

import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/application*.xml"})
public class RedisTest {
	
	@org.junit.Test
	public void Test(){
			EJedisPool pool=(EJedisPool)SpringContext.getBean("jedisPool");
			Jedis jedis = pool.getResource();
		 for(int i = 2000; i<3000; i++) {
			    jedis.set("user:" + i, jedis.toString());
			    System.out.println(jedis);
			}    
	}

}
