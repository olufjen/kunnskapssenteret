package no.helsebiblioteket.admin.domain.requestresult;

public class SendPasswordEmailResult {
	public static final String sentUser = "sentUser";
	public static final String sentEmail = "sentEmail";
	public static final String notFoundUser = "notFoundUser";
	public static final String notFoundEmail = "notFoundEmail";
	public static final String noEmailAddress = "noEmailAddress";
	public static final String multipleEmail = "multipleEmail";
	
	private String value = "";
	private Boolean failed = true;

	public Boolean getFailed() {
		return failed;
	}
	public void setFailed(Boolean failed) {
		this.failed = failed;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
