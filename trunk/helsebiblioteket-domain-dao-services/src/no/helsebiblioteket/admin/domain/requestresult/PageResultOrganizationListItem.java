package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.requestresult.PageResult;

public class PageResultOrganizationListItem extends PageResult{
	private OrganizationListItem[] result;
	public OrganizationListItem[] getResult() {
		return result;
	}
	public void setResult(OrganizationListItem[] result) {
		this.result = result;
	}
}
