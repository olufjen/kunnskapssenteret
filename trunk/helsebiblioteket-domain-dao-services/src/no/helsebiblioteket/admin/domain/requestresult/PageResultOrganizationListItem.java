package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.list.OrganizationListItem;

public class PageResultOrganizationListItem {
	public OrganizationListItem[] result;
	public Integer total;
	public Integer skipped;
}
