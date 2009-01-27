package no.helsebiblioteket.admin.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.dao.EmailDAO;
import no.helsebiblioteket.admin.dao.IpRangeDao;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.requestresult.EmptyResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
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
	private IpRangeDao ipRangeDao;
	public SingleResult<User> loginUserByUsernamePassword(String username, String password){
		User loggedIn = this.userDao.getUserByUsername(username);
		if(loggedIn != null && loggedIn.getPassword().equals(password)){
			return new ValueResult<User>(loggedIn);
		} else {
			return new EmptyResult<User>();
		}
	}
	public SingleResult<Organization> loginOrganizationByIpAddress(IpAddress ipAddress) {
		List<Organization> list = this.ipRangeDao.getOrganizationListByIpAdress(ipAddress);
		if(list.size() == 0){
			return new EmptyResult<Organization>();
		} else {
			// TODO: Log incidents of more than one organization per IP Address?
			return new ValueResult<Organization>(list.get(0));
		}
	}
	public Boolean sendPasswordEmail(User user) {
		logger.info("Sends email to :" + user.getUsername());
		// TODO: What should the email contain and how will it be sent?
		Email email = new Email();
		email.setFromName("Helsebiblioteket");
		email.setFromEmail("test@example.org");

		email.setToName(user.getUsername());
		email.setToEmail(user.getUsername());

		email.setMessage("Here is your new password: jHHns908");
		email.setSubject("Lost password");
		
		emailDao.sendEmail(email);
		return true;
	}
	public void setEmailDao(EmailDAO emailDao) {
		this.emailDao = emailDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void setIpRangeDao(IpRangeDao ipRangeDao) {
		this.ipRangeDao = ipRangeDao;
	}
}
