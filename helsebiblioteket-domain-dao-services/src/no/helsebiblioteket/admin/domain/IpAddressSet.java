package no.helsebiblioteket.admin.domain;

import java.io.Serializable;
import java.util.Date;

public class IpAddressSet implements Serializable {
	// Primary key
	private Integer id;
	
	// Local values
	private Date lastChanged;
	
	// Helpers
	@Override
	public String toString() {
		return id + ", " + lastChanged;
	}

	// GET/SET
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
}
