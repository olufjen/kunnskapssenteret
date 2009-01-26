package no.helsebiblioteket.admin.domain;

public class IpAddress {
	private String address;
	public IpAddress(){ }
	public IpAddress(String address){ this.address = address; }
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	// FIXME: Must have hashCode and equals methods! Used in IpRange
}
