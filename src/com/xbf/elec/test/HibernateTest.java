package com.xbf.elec.test;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class HibernateTest {
	public static void main(String[] args) {
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		com.xbf.elec.domain.ElecTest elecTest = new com.xbf.elec.domain.ElecTest();
		elecTest.setTestName("谢宝发");
		elecTest.setTestDate(new Date());
		elecTest.setTestComment("测试");
		session.save(elecTest);
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
}
