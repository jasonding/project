package com.project.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

import com.project.domain.mapping.Role;
import com.project.domain.mapping.User;
import com.project.enumeration.Gender;

public class HibernateTest {
	@Test
	public void initHibernate() {
		try {
			Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			Session openSession = sessionFactory.openSession();
			Transaction beginTransaction = openSession.beginTransaction();
			beginTransaction.begin();
			User user = new User();
			user.setName("admin1");
			user.setPassword("admin123");
		 user.setGender(Gender.MAN);
			
//		 Role role =new Role();
//		 role.setName("超级管理员");
//		 
//		 user.addRole(role);
			
			openSession.save(user);
//		 List list = openSession.createQuery("from User where name =? ").setParameter(0, "admin").list();
//		 System.out.println(list.size());
			beginTransaction.commit();
			openSession.clear();
			openSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
