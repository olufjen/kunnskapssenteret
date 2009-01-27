package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.listobjects.OrganizationListItem;

public interface OrganizationListDao {
	public List<OrganizationListItem> getOrganizationListPaged(int from, int max);
	public List<OrganizationListItem> getOrganizationListPagedSearchString(String searchString, int from, int max);
}
