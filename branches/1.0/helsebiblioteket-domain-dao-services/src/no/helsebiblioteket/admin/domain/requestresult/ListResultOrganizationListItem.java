package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.list.OrganizationListItem;

public class ListResultOrganizationListItem implements Serializable {
	private OrganizationListItem[] list;
	
	public ListResultOrganizationListItem() {
	}
	public ListResultOrganizationListItem(OrganizationListItem[] list){
		this.list = list;
	}
	
	public OrganizationListItem[] getList() {
		return list;
	}
	public void setList(OrganizationListItem[] list) {
		this.list = list;
	}
}
