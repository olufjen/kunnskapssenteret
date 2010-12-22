package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.OrganizationType;

@SuppressWarnings("serial")
public class ListResultOrganizationType implements Serializable {
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
