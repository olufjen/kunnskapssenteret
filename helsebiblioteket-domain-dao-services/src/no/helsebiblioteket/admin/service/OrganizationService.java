package no.helsebiblioteket.admin.service;

/**
 * Organization service
 */

import java.io.Serializable;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressRange;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.IpAddressSingle;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultIpAddressSet;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganizationType;
import no.helsebiblioteket.admin.requestresult.PageRequest;

public interface OrganizationService extends Serializable {
	public ListResultOrganizationType getOrganizationTypeListAll(String DUMMY);
	public SingleResultOrganizationType getOrganizationTypeByKey(OrganizationTypeKey key);

	// TODO: Remove this and use findOrganizationsBySearchStringRoles with empty string?
	public PageResultOrganizationListItem getOrganizationListAll(PageRequest request);
	public PageResultOrganizationListItem findOrganizationsBySearchString(String searchString, PageRequest request);

	public SingleResultOrganization getOrganizationByListItem(OrganizationListItem organizationListItem);

	public ListResultOrganizationListItem getOrganizationListByIpAddress(IpAddress ipAddress);

	public SingleResultOrganization insertOrganization(Organization organization);
	public SingleResultOrganization updateOrganization(Organization organization);

	public ListResultIpAddressSet addIpAddresses(Organization organization, IpAddressSingle[] ipAddressSingles);
	public ListResultIpAddressSet addIpAddressRanges(Organization organization, IpAddressRange[] ipAddressRanges);
	public Boolean deleteIpAddresses(IpAddressSet[] ipAddressSets);
}
