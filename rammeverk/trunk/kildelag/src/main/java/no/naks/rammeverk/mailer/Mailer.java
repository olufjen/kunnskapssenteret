package no.naks.rammeverk.mailer;

import java.util.Date;
import java.util.List;

import no.naks.rammeverk.kildelag.dao.MailCollector;
import no.naks.rammeverk.kildelag.model.MailReceiver;

import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Dette grensesnittet kan benyttes til ? sende ad hoc e-post til et utvalg mottakere
 * @author olj
 *
 */
public interface Mailer {

	public void setMailSubject(String mailSubject);
	public String getMailText();
	public void setMailText(String mailText);
	public String getMailSalutation();
	public void setMailSalutation(String mailSalutation);
	public String getMailBottom();
	public void setMailBottom(String mailBottom);
	public List getSelection();
	public void setSelection(List selection);
	public void sendEmails(String language,String type);
	public List<MailReceiver> getEmailTexts();
	public List<MailReceiver> getMessages();
	public void setMessages(List<MailReceiver> messages);
	public List<MailReceiver> getReminderReceivers(Date entryDate);
	public MailReceiver getMessage();

	public void setMessage(MailReceiver message);
	public JavaMailSenderImpl getJavaMailSender();

	public void setJavaMailSender(JavaMailSenderImpl javaMailSender);
	public String getMailSenderaddress();
	public void setMailSenderaddress(String mailSenderaddress);
	public String getEmailSender();
	public void setEmailSender(String emailSender);
	
	public MailCollector getMailCollector();
	public void setMailCollector(MailCollector mailCollector);
	public String getMailerType();

	public void setMailerType(String mailerType);
	public String getSchedule();

	public void setSchedule(String schedule);
	public void setMailAuthenticatorUser(String mailAuthenticatorUser);
	public void setMailAuthenticatorPwd(String mailAuthenticatorPwd) ;
	public void setMailTo(String mailTo) ;
	public void setMailFrom(String mailFrom) ;
	public void setHost(String host) ;
	public void setPort(String port) ;
	public void sendEmail();
}
