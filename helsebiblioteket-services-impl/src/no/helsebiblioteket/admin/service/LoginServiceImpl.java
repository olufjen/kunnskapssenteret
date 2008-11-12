package no.helsebiblioteket.admin.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.domain.User;

public class LoginServiceImpl implements LoginService {
	protected final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public boolean logInUser(User user) {
		User loggedIn = this.userDao.findUserByUsername(user);
		return (loggedIn != null) && loggedIn.getPassword().equals(user.getPassword());
	}
	public void sendPasswordEmail(User user) {
		logger.info("Sends email to :" + user.getUsername());
	}
}
