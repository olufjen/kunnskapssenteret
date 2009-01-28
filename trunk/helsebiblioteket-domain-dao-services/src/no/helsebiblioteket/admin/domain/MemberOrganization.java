package no.helsebiblioteket.admin.domain;

import java.util.List;

public class MemberOrganization extends Organization{
	// Values loaded by IpAddressLine
	private List<IpAddressSet> ipAddressSetList;
	
	// GET/SET
	public List<IpAddressSet> getIpAddressSetList() {
		return ipAddressSetList;
	}
	public void setIpAddressSetList(List<IpAddressSet> ipAddressSetList) {
		this.ipAddressSetList = ipAddressSetList;
	}

}
