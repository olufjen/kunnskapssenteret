package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.IpAddressSet;

public class ListResultIpAddressSet {
	private IpAddressSet[] list;

	public ListResultIpAddressSet() {
	}

	public ListResultIpAddressSet(IpAddressSet[] list) {
		this.list = list;
	}

	public IpAddressSet[] getList() {
		return list;
	}

	public void setList(IpAddressSet[] list) {
		this.list = list;
	}
}
