package no.helsebiblioteket.admin.domain;

public class ContactInformation {
	private Integer id = null;
	private String postalAddress = null;
	private String postalCode = null;
	private String telephoneNumber= null;
	private String postalLocation = null;
	private String email = null;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getPostalLocation() {
		return postalLocation;
	}
	public void setPostalLocation(String postalLocation) {
		this.postalLocation = postalLocation;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
