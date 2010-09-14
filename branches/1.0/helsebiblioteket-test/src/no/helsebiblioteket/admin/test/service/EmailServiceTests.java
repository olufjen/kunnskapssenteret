package no.helsebiblioteket.admin.test.service;

import no.helsebiblioteket.admin.domain.Email;
import no.helsebiblioteket.admin.service.EmailService;
import no.helsebiblioteket.admin.test.BeanFactory;

public class EmailServiceTests {
	private BeanFactory beanFactory = BeanFactory.factory();
	@org.junit.Test
	public void testSendMail(){
		Email email = new Email();
		email.setFromName("Test Testeson");
		email.setFromEmail("test@example.org");
		email.setToName("My Friend");
		email.setToEmail("othertest@example.org");
		email.setSubject("Important message");
		email.setMessage("Hello. You are my best friend!");
		
		EmailService emailService = this.beanFactory.getEmailService();
		
//		TEST: public Boolean sendEmail(Email email);
		emailService.sendEmail(email);
	}
}
