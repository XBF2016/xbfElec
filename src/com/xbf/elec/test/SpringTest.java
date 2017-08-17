package com.xbf.elec.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.xbf.elec.domain.ElecTest;
import com.xbf.elec.service.ElecTestService;

public class SpringTest {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"beans.xml");
		ElecTest elecTest = (ElecTest) applicationContext.getBean("elecTest");
		System.out.println(elecTest.getTestName());
	}

	public void testSpringCombineHibernate() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"beans.xml");
		System.out.println(applicationContext);
		HibernateTemplate hibernateTemplate = (HibernateTemplate) applicationContext
				.getBean("hibernateTemplate");
		System.out.println(hibernateTemplate);
		com.xbf.elec.domain.ElecTest elecTest = new com.xbf.elec.domain.ElecTest();
		elecTest.setTestName("谢宝发2");
		elecTest.setTestDate(new Date());
		elecTest.setTestComment("测试2");
		hibernateTemplate.save(elecTest);
	}

	@Test
	public void testDAO() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"beans.xml");
		ElecTestService elecTestService = (ElecTestService) applicationContext
				.getBean("elecTestService");
		// 测试添加
		// com.xbf.elec.domain.ElecTest elecTest = new
		// com.xbf.elec.domain.ElecTest();
		// elecTest.setTestName("谢宝发3");
		// elecTest.setTestDate(new Date());
		// elecTest.setTestComment("测试3");
		// elecTestService.add(elecTest);
		// 测试查找
		System.out.println(elecTestService
				.findById("8a70418c54c8ced00154c8ced4560001"));
	}
}
