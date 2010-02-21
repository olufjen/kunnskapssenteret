package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;

@SuppressWarnings("serial")
public class ListResultResourceAccessListItem implements Serializable {
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
