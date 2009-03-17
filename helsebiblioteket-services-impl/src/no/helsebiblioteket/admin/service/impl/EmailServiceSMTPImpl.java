package no.helsebiblioteket.admin.service.impl;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Email;
import no.helsebiblioteket.admin.service.EmailService;

public class EmailServiceSMTPImpl implements EmailService {
	private Log logger = LogFactory.getLog(getClass());
	private String host;
	private boolean noSend;
	private boolean debug;
	public Boolean sendEmail(Email email) {
		String to = email.getToName() + "<" + email.getToEmail() + ">";
		String from = email.getFromName() + "<" + email.getFromEmail() + ">";
		if(noSend){
			this.logger.info("Would have sent email on host " + this.host + " to '" + to + "' with subject '" + email.getSubject() + "'.");
		} else {
	        Properties props = new Properties();
	        props.put("mail.smtp.host", this.host);
	        props.put("mail.debug", this.debug);
	        Session session = Session.getInstance(props);
	        try {
	            Message msg = new MimeMessage(session);
	            msg.setFrom(new InternetAddress(from));
	            InternetAddress[] address = {new InternetAddress(to)};
	            msg.setRecipients(Message.RecipientType.TO, address);
	            msg.setSubject(email.getSubject());
	            msg.setSentDate(new Date());
	            msg.setText(email.getMessage());
	            Transport.send(msg);
	        } catch (MessagingException mex) {
	            logger.error("Could not send email: " + mex.getLocalizedMessage());
	            return false;
	        }
		}
        return true;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void setNoSend(boolean noSend) {
		this.noSend = noSend;
	}
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
