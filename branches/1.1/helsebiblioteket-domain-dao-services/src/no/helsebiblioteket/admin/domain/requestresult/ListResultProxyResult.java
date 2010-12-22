package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.export.ProxyResult;

@SuppressWarnings("serial")
public class ListResultProxyResult implements Serializable {
	private ProxyResult[] list;
	
	public ListResultProxyResult() {
	}
	public ListResultProxyResult(ProxyResult[] list){
		this.list = list;
	}
	
	public ProxyResult[] getList() {
		return list;
	}
	public void setList(ProxyResult[] list) {
		this.list = list;
	}
}
