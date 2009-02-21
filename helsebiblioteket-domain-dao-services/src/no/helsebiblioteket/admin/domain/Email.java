package no.helsebiblioteket.admin.domain;

public class Email {
	// Not in database, yet.
	private String toName;
	private String fromName;
	private String toEmail;
	private String fromEmail;
	private String subject;
	private String message;
	
	// Helpers
	@Override
	public String toString() {
		return toName + "<" + toEmail + ">: " + subject;
	}
	
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String getFromEmail() {
		return fromEmail;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
