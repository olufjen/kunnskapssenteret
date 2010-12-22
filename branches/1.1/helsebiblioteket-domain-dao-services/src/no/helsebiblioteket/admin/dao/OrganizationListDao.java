package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;

public interface OrganizationListDao {
	public Integer getOrganizationNumberSearchString(String searchString);
	public List<OrganizationListItem> getOrganizationListPagedSearchString(String searchString, int from, int max);

	public Integer getOrganizationNumberByTypes(List<OrganizationTypeKey> types);
	public List<OrganizationListItem> getOrganizationListByTypes(List<OrganizationTypeKey> types, int from, int max);
	
	public List<OrganizationListItem> getOrganizationListByIpAddress(IpAddress ipAddress);
	public List<OrganizationListItem> getOrganizationListByAccessDomain(String accessDomain);
}
