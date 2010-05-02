package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.proxy.ProxyConfig;

@SuppressWarnings("serial")
public class ListResultProxyConfig implements Serializable {
	private ProxyConfig[] list;

	public ListResultProxyConfig() {
	}

	public ListResultProxyConfig(ProxyConfig[] list) {
		this.list = list;
	}

	public ProxyConfig[] getList() {
		return list;
	}

	public void setList(ProxyConfig[] list) {
		this.list = list;
	}
}
