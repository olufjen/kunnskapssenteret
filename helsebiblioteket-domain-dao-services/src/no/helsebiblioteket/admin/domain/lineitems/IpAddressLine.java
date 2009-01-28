package no.helsebiblioteket.admin.domain.line;

import java.util.Date;

public class IpAddressLine {
	// Primary key
	private Integer ipAddressId;

	// Table values
	private String ipAddressFrom;
	private String ipAddressTo;
	private Integer orgUnitId;
	private Date lastChanged;

	// GET/SET
	public Integer getIpAddressId() {
		return ipAddressId;
	}
	public void setIpAddressId(Integer ipAddressId) {
		this.ipAddressId = ipAddressId;
	}
	public String getIpAddressFrom() {
		return ipAddressFrom;
	}
	public void setIpAddressFrom(String ipAddressFrom) {
		this.ipAddressFrom = ipAddressFrom;
	}
	public String getIpAddressTo() {
		return ipAddressTo;
	}
	public void setIpAddressTo(String ipAddressTo) {
		this.ipAddressTo = ipAddressTo;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
	public Integer getOrgUnitId() {
		return orgUnitId;
	}
	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}
}
