package no.helsebiblioteket.admin.domain;

public class IpRange {
	private String id = null;
	private String ipAddressFrom = null;
	private String ipAddressTo = null;
	
	public IpRange() {	
	}
	
	public IpRange(String ipAddressFrom, String ipAddressTo) {
		this.ipAddressFrom = ipAddressFrom;
		this.ipAddressTo = ipAddressTo;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
}
