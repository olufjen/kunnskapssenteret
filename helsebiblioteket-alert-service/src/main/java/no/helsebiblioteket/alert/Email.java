package no.helsebiblioteket.alert;

import java.util.List;
import java.util.Properties;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author <a href="mailto:karine.haug@edb.com">Karine Haug</a>
 *
 */
public class Email {
	private Log log = LogFactory.getLog(Email.class);
	private String subject;
	private String content;
	private String from;
	private List<String> to;
	private String cc;
	
	public Email(String subject, String content, String from, List<String> to, String cc) {
		this.subject = subject;
		this.content = content;
		this.from = from;
		this.to = to;
		this.cc = cc;
	}

	public void send() {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", "localhost");

		Session mailSession = Session.getDefaultInstance(props, null);
		MimeMessage message = new MimeMessage(mailSession);

		try {
			message.setSubject(this.subject);
			message.setFrom(new InternetAddress(this.from));
			message.setContent(content, "text/html; charset=utf-8");

			for (String receiver : this.to) {
				message.addRecipient(RecipientType.TO, new InternetAddress(receiver));
			}
			message.addRecipient(RecipientType.CC, new InternetAddress(this.cc));

			Transport transport = mailSession.getTransport("smtp");
			transport.connect();
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			log.info(subject + " sent to " + to.toString());
		} catch (MessagingException e) {
			log.error("Sending email failed: " + this.subject, e);
		}
	}
}
