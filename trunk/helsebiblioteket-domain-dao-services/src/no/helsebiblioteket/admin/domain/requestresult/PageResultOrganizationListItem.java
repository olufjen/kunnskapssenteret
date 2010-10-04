package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.requestresult.PageResult;

@SuppressWarnings("serial")
public class PageResultOrganizationListItem extends PageResult implements Serializable {
	private OrganizationListItem[] result;
	public OrganizationListItem[] getResult() {
		return result;
	}
	public void setResult(OrganizationListItem[] result) {
		this.result = result;
	}
}
