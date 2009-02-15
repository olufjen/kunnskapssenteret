package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.ResourceAccess;

public class ListResultResourceAccess {
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
