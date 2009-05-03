package no.helsebiblioteket.admin.domain;

import java.io.Serializable;

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
	
	// TODO: Create normalized string here: 192.001.101.002
	//       with all number of size 3! Used for alphabetic
	//       search!
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	// FIXME: Must have hashCode and equals methods? Used in IpRange
}
