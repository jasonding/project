package com.project.spring;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.project.dao.UserDao;
import com.project.domain.mapping.User;
import com.project.enumeration.Gender;
import com.project.service.manage.UserService;

public class SpringTest {
	@Test
	@Deprecated
	public void testConfig() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		User user = (User) context.getBean("user");
		System.out.println(user.getName());
	}
	
	@Test
	public void testSessionFactory(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sessionFactory = (SessionFactory) context.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.out.println(session.disconnect());
		session.close();
	}
	
	@Test
	public void testDao() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		UserDao userDao = (UserDao) context.getBean("userDao");
		User user = new User();
		user.setName("admin");
		user.setPassword("admin123");
		user.setGender(Gender.MAN);
		user.setCreateTime(new Date());
		userDao.save(user);
	}
	
	@Test
	public void testService() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		UserService userService = (UserService) context.getBean("userService");
		//List<User> list = new ArrayList<User>();
		User user = new User();
		user.setName("admin");
		user.setPassword("admin123");
		user.setGender(Gender.MAN);
		user.setCreateTime(new Date());
		userService.saveOrUpdate(user);
//		User findUser = userService.findByUserName("admin", "admin123");
//		System.out.println(findUser.getName() + ":" + findUser.getPassword() + ":" + findUser.getGender().getName());
		
//		for(int i=0; i<5; i++) {
//			u = new User();
//			u.setName("admin" + i);
//			u.setPassword("1111");
//			u.setGender(Gender.MAN);
////			if(i == 4) {
////				u.setName(null);
////			}
//			list.add(u);
//		}
		//userService.saveUsers(list);
	}
	
}
