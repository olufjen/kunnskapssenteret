package no.helsebiblioteket.admin.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.dao.EmailDAO;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.domain.Email;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.User;

public class LoginServiceImpl implements LoginService {
	protected final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	private EmailDAO emailDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public User logInUser(User user) {
		User loggedIn = this.userDao.getUserByUsername(user);
		if(loggedIn != null && loggedIn.getPassword().equals(user.getPassword())){
			return loggedIn;
		} else {
			return null;
		}
	}
	public void sendPasswordEmail(User user) {
		logger.info("Sends email to :" + user.getUsername());
		// TODO: Complete this!
		Email email = new Email();
		email.setFromName("Helsebiblioteket");
		email.setFromEmail("test@example.org");

		email.setToName(user.getUsername());
		email.setToEmail(user.getUsername());

		email.setMessage("Here is your new password: jHHns908");
		email.setSubject("Lost password");
		
		emailDao.sendEmail(email);
	}
	public Organization logInIpAddress(IpAddress address) {
		
		logger.info("LOGGING IN WITH IP :" + address.getAddress());
		
		// TODO Do DAO lookup here
		Organization organization = new Organization();
		organization.setName("Universitetssykehuset i Oslo");
		return organization;
	}
	public void setEmailDao(EmailDAO emailDao) {
		this.emailDao = emailDao;
	}
}
