package no.helsebiblioteket.admin.service;

/**
 * Organization service
 */

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressRange;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.IpAddressSingle;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.parameter.ProxyExportParameter;
import no.helsebiblioteket.admin.domain.parameter.ProxyHitParameterList;
import no.helsebiblioteket.admin.domain.requestresult.ListResultIpAddressSet;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ListResultProxyResult;
import no.helsebiblioteket.admin.domain.requestresult.ListResultSupplierSourceResource;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganizationType;
import no.helsebiblioteket.admin.requestresult.PageRequest;

public interface OrganizationService extends Serializable {
	public ListResultOrganizationType getOrganizationTypeListAll(String DUMMY);
	public SingleResultOrganizationType getOrganizationTypeByKey(OrganizationTypeKey key);

	public PageResultOrganizationListItem getOrganizationListAll(PageRequest request);
	public PageResultOrganizationListItem getOrganizationListBySearchString(PageRequest request, String searchString);
	public PageResultOrganizationListItem getMemberOrganizationListAll(PageRequest request);
	public PageResultOrganizationListItem getSupplierOrganizationListAll(PageRequest request);
	
	public ListResultOrganizationListItem getOrganizationListByIpAddress(IpAddress ipAddress);
	public ListResultOrganizationListItem getOrganizationListByAccessDomain(String accessDomain);

	public SingleResultOrganization getOrganizationByListItem(OrganizationListItem organizationListItem);
	public SingleResultOrganization getOrganizationByAdminUser(User user);

	public SingleResultOrganization insertMemberOrganization(MemberOrganization memberOrganization);
	public SingleResultOrganization insertSupplierOrganization(SupplierOrganization supplierOrganization);
	public SingleResultOrganization updateOrganization(Organization organization);
	
	public Boolean deleteOrganization(Organization organization);

	public ListResultIpAddressSet addIpAddresses(Organization organization, IpAddressSingle[] ipAddressSingles);
	public ListResultIpAddressSet addIpAddressRanges(Organization organization, IpAddressRange[] ipAddressRanges);
	public Boolean deleteIpAddresses(IpAddressSet[] ipAddressSets);

	public Boolean deleteResources(SupplierSourceResource[] resources);
	public ListResultSupplierSourceResource addResources(SupplierSourceResource[] resources);
	
	public ListResultProxyResult getProxyExportList(ProxyExportParameter parameter);
	public Boolean insertProxyHits(ProxyHitParameterList parameter);
}
