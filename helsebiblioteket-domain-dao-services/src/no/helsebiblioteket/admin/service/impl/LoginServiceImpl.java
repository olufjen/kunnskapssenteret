package no.helsebiblioteket.admin.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.requestresult.EmptyResult;
import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.EmailService;
import no.helsebiblioteket.admin.service.LoginService;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.domain.Email;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;

public class LoginServiceImpl implements LoginService {
	protected final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private EmailService emailService;
	private OrganizationService organizationService;
	private String emailFromName = "Helsebiblioteket";
	private String emailFromEmail = "test@example.org";
	private String emailSubject = "Here is your new password: jHHns908";
	private String emailMessage = "Lost password";
	
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
	 * Returns the first found if there are more than one macth.
	 */
	public SingleResultMemberOrganization loginOrganizationByIpAddress(IpAddress ipAddress) {
		ListResultOrganizationListItem result = this.organizationService.getOrganizationListByIpAdress(ipAddress);
		OrganizationListItem[] list = result.getList();
		if(list.length >= 1){
			// Later: Log incidents of more than one organization per IP Address?
			SingleResultOrganization memberResult = this.organizationService.getOrganizationByListItem(list[0]);
			if(memberResult instanceof ValueResultOrganization){
				// FIXME: re-insert:
				MemberOrganization memberOrganization = null;//(MemberOrganization) ((ValueResultOrganization)memberResult).getValue();
				return new ValueResultMemberOrganization(memberOrganization);
			}
		}
		return new EmptyResultMemberOrganization();
	}
	/**
	 * Sends an email to the user. This is delegated to EmailDAO.
	 */
	public Boolean sendPasswordEmail(User user) {
		logger.info("Sends email to :" + user.getUsername());
		// TODO: What should the email contain and how will it be sent?
		// TODO: Set properties in application context.
		Email email = new Email();
		email.setFromName(this.emailFromName);
		email.setFromEmail(this.emailFromEmail);

		email.setToName(user.getUsername());
		email.setToEmail(user.getPerson().getContactInformation().getEmail());

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
	
	public MemberOrganization loginOrganizationByIpAddressWS(IpAddress ipAddress) {
		SingleResultMemberOrganization result = loginOrganizationByIpAddress(ipAddress);
		if(result instanceof ValueResultMemberOrganization) {
        	return (MemberOrganization)((ValueResultMemberOrganization)result).getValue();
		}
        return null;
	}
	public User loginUserByUsernamePasswordWS(String username, String password) {
		SingleResultUser result = this.userService.findUserByUsername(username);
		if(result instanceof ValueResultUser){
			User loggedIn = ((ValueResultUser)result).getValue();
			if(loggedIn.getPassword().equals(password)){
				return loggedIn;
			}
		}
		return null;
	}
}
