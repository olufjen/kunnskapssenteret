package no.helsebiblioteket.admin.domain;

public class MemberOrganization {
	// Values loaded by IpAddressLine
	private IpAddressSet[] ipAddressSetList;
	private Organization organization = new Organization();;
	
	// GET/SET
	public IpAddressSet[] getIpAddressSetList() {
		return ipAddressSetList;
	}
	public void setIpAddressSetList(IpAddressSet[] ipAddressSetList) {
		this.ipAddressSetList = ipAddressSetList;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
