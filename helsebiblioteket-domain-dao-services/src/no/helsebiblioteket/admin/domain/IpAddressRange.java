package no.helsebiblioteket.admin.domain;


public class IpAddressRange {
	// Local values
	private IpAddress ipAddressFrom;
	private IpAddress ipAddressTo;
	private IpAddressSet ipAddressSet = new IpAddressSet();
	
	// Constructors
	public IpAddressRange() {	
	}
	public IpAddressRange(IpAddress ipAddressFrom, IpAddress ipAddressTo) {
		this.ipAddressFrom = ipAddressFrom;
		this.ipAddressTo = ipAddressTo;
	}

	// Helpers
	public int hashCode() {
        int result;
        result = (ipAddressFrom != null ? ipAddressFrom.hashCode() : 0);
        result = 31 * result + (ipAddressTo != null ? ipAddressTo.hashCode() : 0);
        return result;
    }
	public boolean equals(IpAddressRange ipRange) {
        if (this == ipRange) return true;
        if (ipAddressFrom != null ? !ipRange.equals(ipRange.ipAddressFrom) : ipRange.ipAddressFrom != null) return false;
        if (ipAddressTo != null ? !ipAddressTo.equals(ipRange.ipAddressTo) : ipRange.ipAddressTo != null) return false;
        return true;
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
	public IpAddressSet getIpAddressSet() {
		return ipAddressSet;
	}
	public void setIpAddressSet(IpAddressSet ipAddressSet) {
		this.ipAddressSet = ipAddressSet;
	}
}
