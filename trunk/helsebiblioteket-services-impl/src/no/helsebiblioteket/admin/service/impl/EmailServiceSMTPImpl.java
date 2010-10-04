package no.helsebiblioteket.admin.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
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
	private String contentType;
	
	
	private String charset;
	public Boolean sendEmail(Email email) {
		String to = email.getToName() + "<" + email.getToEmail() + ">";
//		String from = email.getFromName() + "<" + email.getFromEmail() + ">";
		if(noSend){
			this.logger.info("Would have sent email on host " + this.host + " to '" + to + "' with subject '" + email.getSubject() + "' and body '" + email.getMessage() + "'");
		} else {
	        Properties props = new Properties();
	        props.put("mail.smtp.host", this.host);
	        props.put("mail.debug", this.debug);
	        
	        Session session = Session.getInstance(props);
	        
	        try {
	            MimeMessage msg = new MimeMessage(session);
	            InternetAddress internetAddressTo = new InternetAddress(email.getToEmail());
	            msg.addRecipient(Message.RecipientType.TO, internetAddressTo);
	            InternetAddress internetAddressFrom = new InternetAddress(email.getFromEmail());
	            if (email.getFromName() != null && !"".equals(email.getFromName())) {
	                try {
						internetAddressFrom.setPersonal(email.getFromName());
					} catch (UnsupportedEncodingException usee) {
						logger.error("Error setting name for email sender", usee);
					}
	            }
	            msg.setFrom(internetAddressFrom);
	            msg.setReplyTo(new Address[]{internetAddressFrom});	         
	            msg.setSubject(email.getSubject());
	            msg.setSentDate(new Date());
	            // msg.setText(email.getMessage(), "UTF-8");
	            msg.setContent(email.getMessage(), getMimeType());
	            Transport.send(msg);
	        } catch (MessagingException mex) {
	            logger.error("Could not send email: " + mex.getLocalizedMessage());
	            return false;
	        }
		}
        return true;
	}
	private String getMimeType() {
		return "" + this.contentType + ";charset=\"" + this.charset + "\"";
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
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
}