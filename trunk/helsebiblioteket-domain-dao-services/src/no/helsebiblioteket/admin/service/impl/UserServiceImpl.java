package no.helsebiblioteket.admin.service.impl;

import java.util.List;

import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.UserService;

public class UserServiceImpl implements UserService {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public User findUserByUsername(User user) {
		return userDao.findUserByUsername(user);
	}
	
	public void createUser(User user) {
		userDao.createUser(user);
	}

	public List<User> getUserList() {
		return userDao.getUserList();
	}
}