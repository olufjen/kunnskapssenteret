package no.helsebiblioteket.admin.ssoservice;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Email;
import no.helsebiblioteket.admin.service.EmailService;

@SuppressWarnings("serial")
public class EmailServiceSso extends SsoService implements EmailService {
	protected static final Log logger = LogFactory.getLog(EmailServiceSso.class);
	
	public Log getLogger() { return logger; }
	
	private EmailService emailService;
	
	@Override
	public Boolean sendEmail(Email email) {
		return emailService.sendEmail(email);
	}
	
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
}
