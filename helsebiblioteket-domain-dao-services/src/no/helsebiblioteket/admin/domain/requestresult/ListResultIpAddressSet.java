package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.IpAddressSet;

@SuppressWarnings("serial")
public class ListResultIpAddressSet implements Serializable {
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
