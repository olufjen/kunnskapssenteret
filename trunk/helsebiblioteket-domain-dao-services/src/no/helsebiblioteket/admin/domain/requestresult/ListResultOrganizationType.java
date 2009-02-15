package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.OrganizationType;

public class ListResultOrganizationType {
	private OrganizationType[] list;

	public ListResultOrganizationType() {
	}

	public ListResultOrganizationType(OrganizationType[] list) {
		this.list = list;
	}

	public OrganizationType[] getList() {
		return list;
	}

	public void setList(OrganizationType[] list) {
		this.list = list;
	}
}
