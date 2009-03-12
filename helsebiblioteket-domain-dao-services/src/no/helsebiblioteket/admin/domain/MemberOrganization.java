package no.helsebiblioteket.admin.domain;

public class MemberOrganization {
	// Values loaded by IpAddressLine
	private IpAddressRange[] ipAddressRangeList;
	private IpAddressSingle[] ipAddressSingleList;
	
	// Organization
	private Organization organization = new Organization();
	
	// Helpers
	@Override
	public String toString() {
		return "Member: " + organization;
	}
	
	// GET/SET
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public IpAddressRange[] getIpAddressRangeList() {
		return ipAddressRangeList;
	}
	public void setIpAddressRangeList(IpAddressRange[] ipAddressRangeList) {
		this.ipAddressRangeList = ipAddressRangeList;
	}
	public IpAddressSingle[] getIpAddressSingleList() {
		return ipAddressSingleList;
	}
	public void setIpAddressSingleList(IpAddressSingle[] ipAddressSingleList) {
		this.ipAddressSingleList = ipAddressSingleList;
	}
}
