package no.helsebiblioteket.admin.domain;

import java.util.Date;

public class IpRange {
	private Integer id;
	private IpAddress ipAddressFrom;
	private IpAddress ipAddressTo;
	private Date lastChanged;
	
	// foreign keys
	private Integer organizationId;
	
	public Date getLastChanged() {
		return lastChanged;
	}

	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}

	public IpRange() {	
	}
	
	public IpRange(IpAddress ipAddressFrom, IpAddress ipAddressTo) {
		this.ipAddressFrom = ipAddressFrom;
		this.ipAddressTo = ipAddressTo;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public IpAddress getIpAddressFrom() {
		return ipAddressFrom;
	}
	public void setIpAddressFrom(IpAddress ipAddressFrom) {
		this.ipAddressFrom = ipAddressFrom;
	}
	public IpAddress getIpAddressTo() {
		return ipAddressTo;
	}
	public void setIpAddressTo(IpAddress ipAddressTo) {
		this.ipAddressTo = ipAddressTo;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
	public int hashCode() {
        int result;
        result = (ipAddressFrom != null ? ipAddressFrom.hashCode() : 0);
        result = 31 * result + (ipAddressTo != null ? ipAddressTo.hashCode() : 0);
        return result;
    }
	
	public boolean equals(IpRange ipRange) {
        if (this == ipRange) return true;
        if (ipAddressFrom != null ? !ipRange.equals(ipRange.ipAddressFrom) : ipRange.ipAddressFrom != null) return false;
        if (ipAddressTo != null ? !ipAddressTo.equals(ipRange.ipAddressTo) : ipRange.ipAddressTo != null) return false;
        return true;
    }
}
