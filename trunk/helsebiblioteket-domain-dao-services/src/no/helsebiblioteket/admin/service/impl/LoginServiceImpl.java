package no.helsebiblioteket.admin.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.dao.EmailDAO;
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
	public SingleResult<User> loginUserByUsernamePassword(String username, String password){
		User loggedIn = this.userDao.getUserByUsername(username);
		if(loggedIn != null && loggedIn.getPassword().equals(password)){
			return new ValueResult<User>(loggedIn);
		} else {
			return new EmptyResult<User>();
		}
	}
	public SingleResult<Organization> loginOrganizationByIpAddress(IpAddress ipAddress) {
		// TODO: Remove logging!
		logger.info("LOGGING IN WITH IP :" + ipAddress.getAddress());
		
		// TODO: Complete this!
		Organization organization = new Organization();
		organization.setNameNorwegianNormal("Universitetssykehuset i Oslo");
		organization.setNameEnglishNormal("University Hospital of Oslo");
		organization.setNameNorwegianShort("UH i Oslo");
		organization.setNameEnglishShort("UH in Oslo");
		
		return new ValueResult<Organization>(organization);
	}
	public Boolean sendPasswordEmail(User user) {
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
		return true;
	}
	public void setEmailDao(EmailDAO emailDao) {
		this.emailDao = emailDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
