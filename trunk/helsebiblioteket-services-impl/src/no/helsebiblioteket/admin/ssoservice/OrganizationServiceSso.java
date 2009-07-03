package no.helsebiblioteket.admin.ssoservice;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressRange;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.IpAddressSingle;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.cache.key.CacheKey;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultIpAddressSet;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ListResultSupplierSourceResource;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganizationType;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.service.OrganizationService;

@SuppressWarnings("serial")
public class OrganizationServiceSso extends SsoService implements OrganizationService {
	protected static final Log logger = LogFactory.getLog(OrganizationServiceSso.class);
	
	private OrganizationService organizationService;

	@Override
	public ListResultOrganizationType getOrganizationTypeListAll(String DUMMY) {
		return organizationService.getOrganizationTypeListAll(DUMMY);
	}
	@Override
	public SingleResultOrganizationType getOrganizationTypeByKey(OrganizationTypeKey organizationTypeKey) {
		/*String key = (
				((organizationTypeKey != null) ? (organizationTypeKey.getValue()) : "")
				);
		Object result = cacheHelper.findCache(CacheKey.organizationServiceWebGetOrganizationTypeByKeyCache, key);
		if (null == result) {
			result = organizationService.getOrganizationTypeByKey(organizationTypeKey);
			cacheHelper.addCache(CacheKey.organizationServiceWebGetOrganizationTypeByKeyCache, key, result);
		}
		return (SingleResultOrganizationType) result;*/
		return organizationService.getOrganizationTypeByKey(organizationTypeKey);
	}
	@Override
	public PageResultOrganizationListItem getOrganizationListAll(PageRequest request) {
		return organizationService.getOrganizationListAll(request);
	}
	@Override
	public PageResultOrganizationListItem getOrganizationListBySearchString(PageRequest request, String searchString) {
		return organizationService.getOrganizationListBySearchString(request, searchString);
	}

	@Override
	public SingleResultOrganization getOrganizationByListItem(OrganizationListItem organizationListItem) {
		return organizationService.getOrganizationByListItem(organizationListItem);
	}
	@Override
	public SingleResultOrganization insertMemberOrganization(MemberOrganization memberOrganization) {
		// TODO Fase2: These tests should not be here.
		if(memberOrganization.getIpAddressRangeList() == null) {
			throw new NullPointerException("IpAddressRangeList should not be null");
		}
		if(memberOrganization.getIpAddressSingleList() == null){
			throw new NullPointerException("IpAddressSingleList should not be null");
		}
		return organizationService.insertMemberOrganization(memberOrganization);
	}
	
	@Override
	public SingleResultOrganization insertSupplierOrganization(SupplierOrganization supplierOrganization) {
		// TODO Fase2: These tests should not be here.
		if(supplierOrganization.getResourceList() == null){
			throw new NullPointerException("ResourceList should not be null");
		}
		return organizationService.insertSupplierOrganization(supplierOrganization);
	}
	
	@Override
	public SingleResultOrganization updateOrganization(Organization organization) {
		return organizationService.updateOrganization(organization);
	}
	
	@Override
	public ListResultIpAddressSet addIpAddresses(Organization organization, IpAddressSingle[] ipAddressSets) {
		return organizationService.addIpAddresses(organization, ipAddressSets);
	}
	@Override
	public ListResultIpAddressSet addIpAddressRanges(Organization organization, IpAddressRange[] ipAddressRanges) {
		return organizationService.addIpAddressRanges(organization, ipAddressRanges);
	}
	@Override
	public Boolean deleteIpAddresses(IpAddressSet[] ipAddressSets) {
		return organizationService.deleteIpAddresses(ipAddressSets);
	}
	@Override
	public ListResultOrganizationListItem getOrganizationListByIpAddress(IpAddress ipAddress) {
		return organizationService.getOrganizationListByIpAddress(ipAddress);
	}
	@Override
	public ListResultOrganizationListItem getOrganizationListByAccessDomain(String accessDomain) {
		return organizationService.getOrganizationListByAccessDomain(accessDomain);
	}
	@Override
	public ListResultSupplierSourceResource addResources(SupplierSourceResource[] resources) {
		return organizationService.addResources(resources);
	}
	@Override
	public Boolean deleteResources(SupplierSourceResource[] resources) {
		return organizationService.deleteResources(resources);
	}
	
	public Log getLogger() {
		return logger;
	}
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
}