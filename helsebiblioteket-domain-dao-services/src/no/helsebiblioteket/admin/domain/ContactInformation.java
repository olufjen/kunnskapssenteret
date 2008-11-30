package no.helsebiblioteket.admin.domain;

public class ContactInformation {
	private Integer id = null;
	private String postalAddress = null;
	private String postalCode = null;
	private String telephoneNumber= null;
	private String postalLocation = null;
	private String email = null;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
