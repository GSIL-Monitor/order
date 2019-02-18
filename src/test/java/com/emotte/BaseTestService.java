package com.emotte;

import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/application*.xml"})
public class BaseTestService {
	public static ApplicationContext ac;
	public static void init() {
		ac = new ClassPathXmlApplicationContext("classpath*:/application*.xml");
	}

}
