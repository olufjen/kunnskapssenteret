package no.helsebiblioteket.admin.ssoservice;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultProxyConfig;
import no.helsebiblioteket.admin.domain.requestresult.ListResultResourceAccessListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultSupplierSource;
import no.helsebiblioteket.admin.domain.requestresult.ListResultSupplierSourceResource;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultAccessType;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultResourceType;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultSupplierSource;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultSupplierSourceResource;
import no.helsebiblioteket.admin.service.AccessService;

@SuppressWarnings("serial")
public class AccessServiceSso extends SsoService implements AccessService{
	protected static final Log logger = LogFactory.getLog(AccessServiceSso.class);
	private AccessService accessService;
	
	@Override
	public SingleResultSupplierSourceResource insertSupplierSourceResource(SupplierSourceResource resource) {
		return accessService.insertSupplierSourceResource(resource);
	}
	@Override
	public Boolean deleteSupplierSourceResource(SupplierSourceResource resource) {
		return accessService.deleteSupplierSourceResource(resource);
	}
	@Override
	public Boolean deleteResourceAccess(ResourceAccessListItem access) {
		return accessService.deleteResourceAccess(access);
	}
	@Override
	public ListResultResourceAccessListItem getAccessListForAll() {
		return accessService.getAccessListForAll();
	}
	@Override
	public ListResultResourceAccessListItem getAccessListByOrganization(OrganizationListItem organization) {
		return getAccessListByOrganization(organization);
	}
	@Override
	public ListResultResourceAccessListItem getAccessListByOrganizationType(OrganizationTypeKey organizationType) {
		return accessService.getAccessListByOrganizationType(organizationType);
	}
	@Override
	public ListResultResourceAccessListItem getAccessListByRole(UserRoleKey role) {
		return accessService.getAccessListByRole(role);
	}
	@Override
	public ListResultResourceAccessListItem getAccessListByUser(UserListItem user) {
		return accessService.getAccessListByUser(user);
	}
	@Override
	public SingleResultAccessType getAccessTypeByTypeCategory(AccessTypeKey accessTypeKey, AccessTypeCategory accessTypeCategory) {
		return accessService.getAccessTypeByTypeCategory(accessTypeKey, accessTypeCategory);
	}
	@Override
	public SingleResultResourceType getResourceTypeByKey(ResourceTypeKey resourceTypeKey){
		return accessService.getResourceTypeByKey(resourceTypeKey);
	}
	@Override
	public ListResultSupplierSource getSupplierSourceListAll(String DUMMY) {
		return accessService.getSupplierSourceListAll(DUMMY);
	}
	@Override
	public Boolean insertOrganizationResourceAccess(Organization organization, ResourceAccess access) {
		return accessService.insertOrganizationResourceAccess(organization, access);
	}
	@Override
	public Boolean insertOrganizationTypeResourceAccess(OrganizationType organizationType, ResourceAccess access) {
		return accessService.insertOrganizationTypeResourceAccess(organizationType, access);
	}
	@Override
	public Boolean insertUserResourceAccess(User user, ResourceAccess access) {
		return accessService.insertUserResourceAccess(user, access);
	}
	@Override
	public Boolean insertUserRoleResourceAccess(Role userRole, ResourceAccess access) {
		return accessService.insertUserRoleResourceAccess(userRole, access);
	}
	@Override
	public ListResultSupplierSourceResource getSupplierSourceResourceListAll(String DUMMY) {
		return accessService.getSupplierSourceResourceListAll(DUMMY);
	}
	@Override
	public SingleResultSupplierSource getSupplierSourceByDomain(Url url) {
		return accessService.getSupplierSourceByDomain(url);
	}
	@Override
	public Log getLogger() {
		return logger;
	}
	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}
	@Override
	public ListResultResourceAccessListItem getAccessListNational() {
		return this.accessService.getAccessListNational();
	}
	@Override
	public Boolean insertNationalResourceAccess(ResourceAccess access) {
		return this.accessService.insertNationalResourceAccess(access);
	}
	@Override
	public ListResultProxyConfig getProxyConfigListAll(String DUMMY) {
		return this.accessService.getProxyConfigListAll(DUMMY);
	}
}
