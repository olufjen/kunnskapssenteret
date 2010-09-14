package no.helsebiblioteket.admin.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.service.EmailService;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.translator.EmailMessageTranslator;
import no.helsebiblioteket.admin.translator.OrganizationToLoggedInOrganizationTranslator;
import no.helsebiblioteket.admin.translator.UserToLoggedInUserTranslator;
import no.helsebiblioteket.admin.validator.EmailValidator;
import no.helsebiblioteket.admin.domain.Email;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.LoggedInOrganizationResult;
import no.helsebiblioteket.admin.domain.requestresult.LoggedInUserResult;
import no.helsebiblioteket.admin.domain.requestresult.SendPasswordEmailResult;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.util.Base64;

public class LoginServiceImpl implements LoginService {
	protected final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private EmailService emailService;
	private OrganizationService organizationService;
	
	/**
	 * Loads the user from the database and compares the passwords.
	 * Returns the complete user object.
	 * Returns EmptyResult if user not found or passwords not equal.
	 */
	@Override
	public LoggedInUserResult loginUserByUsernamePassword(String username, String password) {
		SingleResultUser find = this.userService.findUserByUsername(username);
		LoggedInUserResult result = new LoggedInUserResult();
		User found = null;
		if (find instanceof ValueResultOrganizationUser) {
			found = ((ValueResultOrganizationUser) find).getValue().getUser();
		} else if (find instanceof ValueResultUser) {
			found = ((ValueResultUser)find).getValue();
		}
		if (null != found && found.getPassword().equals(password)) {
			UserToLoggedInUserTranslator userTranslator = new UserToLoggedInUserTranslator();
			result.setUser(userTranslator.translate(found));
			result.setSuccess(true);
			return result;
		}
		return result;
	}
	/**
	 * Loads the organization from the database and returns the complete
	 * object. Finding the organizations with the matching address is
	 * delegated to IpRangeDao.
	 * Returns the first found if there are more than one match.
	 */
	@Override
	public LoggedInOrganizationResult loginOrganizationByIpAddress(IpAddress ipAddress) {
		LoggedInOrganizationResult returnThis = new LoggedInOrganizationResult();
		ListResultOrganizationListItem result = this.organizationService.getOrganizationListByIpAddress(ipAddress);
		OrganizationListItem[] list = result.getList();
		if(list.length >= 1) {			
			if (list.length > 1) {
				logger.warn(list.length + " organizations found for IP address '" + ipAddress + "'. Expected only one");
			}
			SingleResultOrganization memberResult = this.organizationService.getOrganizationByListItem(list[0]);
			if(memberResult instanceof ValueResultMemberOrganization) {
				MemberOrganization memberOrganization = ((ValueResultMemberOrganization)memberResult).getValue();
				OrganizationToLoggedInOrganizationTranslator translator = new OrganizationToLoggedInOrganizationTranslator();
				returnThis.setOrganization(translator.translate(memberOrganization.getOrganization()));
				returnThis.setSuccess(true);
				return returnThis;
			}
		}
		return returnThis;
	}
	
	/**
	 * Loads the organization from the database and returns the complete
	 * object. Finding the organizations with the matching address is
	 * delegated to IpRangeDao.
	 * Returns the first found if there are more than one match.
	 */
	@Override
	public LoggedInOrganizationResult loginOrganizationByReferringDomain(String accessDomain, String key) {
		LoggedInOrganizationResult returnThis = new LoggedInOrganizationResult();
		if (! validateAccessDomainKey(key)) {
			return returnThis;
		}
		ListResultOrganizationListItem result = this.organizationService.getOrganizationListByAccessDomain(accessDomain);
		OrganizationListItem[] list = result.getList();
		if(list.length >= 1) {
			if (list.length > 1) {
				logger.warn(list.length + " organizations found for access domain '" + accessDomain + "'. Expected only one");
			}
			SingleResultOrganization memberResult = this.organizationService.getOrganizationByListItem(list[0]);
			if(memberResult instanceof ValueResultMemberOrganization) {
				MemberOrganization memberOrganization = ((ValueResultMemberOrganization)memberResult).getValue();
				OrganizationToLoggedInOrganizationTranslator translator = new OrganizationToLoggedInOrganizationTranslator();
				returnThis.setOrganization(translator.translate(memberOrganization.getOrganization()));
				returnThis.setSuccess(true);
				return returnThis;
			}
		}
		return returnThis;
	}
	
	private boolean validateAccessDomainKey(String key) {
		boolean ret = false;
		String urlDecodedKey = null;
		try {
			urlDecodedKey = URLDecoder.decode(key, "UTF-8");
		} catch (UnsupportedEncodingException uee) {
			logger.error("Could not perform url decoding for string '" + key + "'. Message: " + uee.getMessage());
		}
		String urlDecodedBase64DecodedKey = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			Base64.decode(urlDecodedKey, baos);
		} catch (IOException ioe) {
			logger.error("Could not base64 decode string '" + key + "'. Message: " + ioe.getMessage()); 
		}
		try {
			urlDecodedBase64DecodedKey = new String(baos.toByteArray(), "UTF-8");
		} catch (UnsupportedEncodingException uee) {
			logger.error("Could not perform base64 decoding for string '" + key + "'. Message: " + uee.getMessage());
		}
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateAsString = formatter.format(new Date());
        if (dateAsString.equals(urlDecodedBase64DecodedKey)) {
        	ret = true;
        }
		return ret;
	}
	
	/**
	 * Sends an email to the user. This is delegated to EmailService.
	 */
	@Override
	public SendPasswordEmailResult sendPasswordEmail(String emailaddressOrUsername, Email email) {
		SendPasswordEmailResult result = new SendPasswordEmailResult();
		if(EmailValidator.getInstance().isValidEmailAdress(emailaddressOrUsername)){
			User[] users = this.userService.getUserListByEmailAddress(emailaddressOrUsername).getList();
			if(users.length == 0){
				result.setValue(SendPasswordEmailResult.notFoundEmail);
			} else if(users.length == 1){
				if (email.getToEmail() == null || "".equals(email.getToEmail())) {
					email.setToEmail(emailaddressOrUsername);
				}
				this.sendEmail(users[0], email);
				result.setFailed(false);
				result.setValue(SendPasswordEmailResult.sentEmail);
			} else {
				result.setValue(SendPasswordEmailResult.multipleEmail);
			}
		} else {
			SingleResultUser userResult = this.userService.findUserByUsername(emailaddressOrUsername);
			if(userResult instanceof ValueResultUser){
				User user = ((ValueResultUser)userResult).getValue();
				if(user.getPerson() != null &&
						user.getPerson().getContactInformation() != null &&
						user.getPerson().getContactInformation().getEmail() != null &&
						EmailValidator.getInstance().isValidEmailAdress(
								user.getPerson().getContactInformation().getEmail())){
					if (email.getToEmail() == null || "".equals(email.getToEmail())) {
						email.setToEmail(user.getPerson().getContactInformation().getEmail());
					}
					this.sendEmail(user, email);
					result.setFailed(false);
					result.setValue(SendPasswordEmailResult.sentUser);
				} else {
					result.setValue(SendPasswordEmailResult.noEmailAddress);
				}
			} else {
				result.setValue(SendPasswordEmailResult.notFoundUser);
			}
		}
		return result;
	}
	
	private void sendEmail(User user, Email email) {
		user.setPassword(this.newPasssword());
		this.userService.updateUser(user);
		EmailMessageTranslator emailMessageTranslator = new EmailMessageTranslator();
		email.setMessage(emailMessageTranslator.translate(email.getMessage(), user));
		emailService.sendEmail(email);
		logger.debug("Sending email to user:" + user.getUsername());
	}
	
	private String newPasssword() {
		String password = "";
		String values = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		Random random = new Random();
		for(int i=0; i<9;i++) {
			int val = random.nextInt(62);
			password += values.substring(val, val+1);
		}
		return password;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
}