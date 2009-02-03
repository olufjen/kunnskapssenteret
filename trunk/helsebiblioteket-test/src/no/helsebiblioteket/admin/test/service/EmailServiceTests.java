package no.helsebiblioteket.admin.test.service;

import no.helsebiblioteket.admin.domain.Email;
import no.helsebiblioteket.admin.service.EmailService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class EmailServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testSendMail(){
		Email email = new Email();
		email.setFromEmail("test@test.org");
		email.setFromName("Test Testeson");
		email.setMessage("Hello. You are my best friend!");
		email.setSubject("Important message");
		email.setToEmail("othertest@test.org");
		email.setToName("My Friend");
		
		EmailService emailService = this.beanFactory.getEmailService();
		emailService.sendEmail(email);
	}
}
