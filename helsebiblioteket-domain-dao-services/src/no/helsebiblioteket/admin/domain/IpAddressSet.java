package no.helsebiblioteket.admin.domain;

import java.util.Date;

import no.helsebiblioteket.admin.domain.base.Identifiable;

public abstract class IpAddressSet implements Identifiable{
	// Primary key
	private Integer ipAddressId;
	
	// Local values
	private Date lastChanged;
	
	// Helpers
	public Integer getId() { return getIpAddressId(); }

	// GET/SET
	public Integer getIpAddressId() {
		return ipAddressId;
	}
	public void setIpAddressId(Integer id) {
		this.ipAddressId = id;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
}
