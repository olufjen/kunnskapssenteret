package no.helsebiblioteket.admin.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.service.EmailService;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.domain.Email;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;

public class LoginServiceImpl implements LoginService {
	protected final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private EmailService emailService;
	private OrganizationService organizationService;
	private String emailFromName;
	private String emailFromEmail;
	private String emailSubject;
	private String emailMessage;
	
	/**
	 * Loads the user from the database and compares the passwords.
	 * Returns the complete user object.
	 * Returns EmptyResult if user not found or passwords not equal.
	 */
	public SingleResultUser loginUserByUsernamePassword(String username, String password){
		SingleResultUser result = this.userService.findUserByUsername(username);
		if(result instanceof ValueResultUser){
			User loggedIn = ((ValueResultUser)result).getValue();
			if(loggedIn.getPassword().equals(password)){
				return result;
			}
		}
		return new EmptyResultUser();
	}
	/**
	 * Loads the organization from the database and returns the complete
	 * object. Finding the organizations with the matching address is
	 * delegated to IpRangeDao.
	 * Returns the first found if there are more than one match.
	 */
	public SingleResultMemberOrganization loginOrganizationByIpAddress(IpAddress ipAddress) {
		ListResultOrganizationListItem result = this.organizationService.getOrganizationListByIpAddress(ipAddress);
		OrganizationListItem[] list = result.getList();
		if(list.length >= 1) {			
			if (list.length > 1) {
				logger.warn(list.length + " organizations found for IP address '" + ipAddress + "'. Expected only one");
			}
			SingleResultOrganization memberResult = this.organizationService.getOrganizationByListItem(list[0]);
			if(memberResult instanceof ValueResultMemberOrganization) {
				MemberOrganization memberOrganization = ((ValueResultMemberOrganization)memberResult).getValue();
				return new ValueResultMemberOrganization(memberOrganization);
			}
		}
		return new EmptyResultMemberOrganization();
	}
	/**
	 * Sends an email to the user. This is delegated to EmailService.
	 */
	public Boolean sendPasswordEmail(String emailaddress) {
		logger.info("Sending email to :" + emailaddress);
		Email email = new Email();
		email.setFromName(this.emailFromName);
		email.setFromEmail(this.emailFromEmail);

		email.setToName(emailaddress);
		email.setToEmail(emailaddress);

		email.setSubject(this.emailSubject);
		email.setMessage(this.emailMessage);
		
		emailService.sendEmail(email);
		return true;
	}
	
	public void setEmailFromName(String emailFromName) {
		this.emailFromName = emailFromName;
	}
	public void setEmailFromEmail(String emailFromEmail) {
		this.emailFromEmail = emailFromEmail;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
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
