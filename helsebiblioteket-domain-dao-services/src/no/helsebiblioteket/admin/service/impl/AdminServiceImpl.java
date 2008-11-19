package no.helsebiblioteket.admin.service.impl;

import java.util.List;

import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.service.AdminService;
import no.helsebiblioteket.domain.User;

public class AdminServiceImpl implements AdminService {
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

	@Override
	public List<OrganizationType> getOrganizationTypeList() {
		// TODO Auto-generated method stub
		return null;
	}
}
