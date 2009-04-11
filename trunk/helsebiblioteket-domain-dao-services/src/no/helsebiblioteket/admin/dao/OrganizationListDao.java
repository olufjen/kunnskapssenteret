package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;

public interface OrganizationListDao {
	// TODO: Not in use. Use getOrganizationListPagedSearchString with empty string!
//	public List<OrganizationListItem> getOrganizationListPaged(int from, int max);
	
	public Integer getOrganizationNumberSearchString(String searchString);
	public List<OrganizationListItem> getOrganizationListPagedSearchString(String searchString, int from, int max);
	public List<OrganizationListItem> getOrganizationListByIpAddress(IpAddress ipAddress);

}
