package no.helsebiblioteket.admin.test.dao;

import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.test.BeanFactory;

public class UserDaoTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testUser(){
		UserDao userDao = beanFactory.getUserDao();
		
	}

	public void insertUser(User user){
		UserDao userDao = beanFactory.getUserDao();
		userDao.insertUser(user);
	}
	public void removeUser(User user){
		UserDao userDao = beanFactory.getUserDao();
		userDao.deleteUser(user);
	}
}
