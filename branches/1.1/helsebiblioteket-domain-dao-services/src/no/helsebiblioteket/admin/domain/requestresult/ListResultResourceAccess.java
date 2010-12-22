package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.ResourceAccess;

@SuppressWarnings("serial")
public class ListResultResourceAccess implements Serializable {
	private ResourceAccess[] list;

	public ListResultResourceAccess() {
	}

	public ListResultResourceAccess(ResourceAccess[] list) {
		this.list = list;
	}

	public ResourceAccess[] getList() {
		return list;
	}

	public void setList(ResourceAccess[] list) {
		this.list = list;
	}
}
