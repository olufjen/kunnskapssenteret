package no.helsebiblioteket.admin.domain;

import java.util.Date;

public class ContactInformation {
	// Primary key
	private Integer contactInformationId;
	
	// Local values
	private String postalAddress;
	private String postalCode;
	private String telephoneNumber;
	private String postalLocation;
	private String email;
	private Date lastChanged;
	
	public Integer getId() {
		return contactInformationId;
	}
	public void setId(Integer contactInformationId) {
		this.contactInformationId = contactInformationId;
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
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
}
