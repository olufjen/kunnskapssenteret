package no.helsebiblioteket.admin.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class IpAddress implements Serializable {
	// Domain object not in database.
	private String address;
	public IpAddress(){ }
	public IpAddress(String address){ this.address = address; }

	// Helpers
	@Override
	public String toString() {
		return address;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
}
