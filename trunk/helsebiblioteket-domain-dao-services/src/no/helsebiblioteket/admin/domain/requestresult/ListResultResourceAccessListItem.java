package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;

public class ListResultResourceAccessListItem {
	private ResourceAccessListItem[] list;

	public ListResultResourceAccessListItem() {
	}

	public ListResultResourceAccessListItem(ResourceAccessListItem[] list) {
		this.list = list;
	}

	public ResourceAccessListItem[] getList() {
		return list;
	}

	public void setList(ResourceAccessListItem[] list) {
		this.list = list;
	}
}
