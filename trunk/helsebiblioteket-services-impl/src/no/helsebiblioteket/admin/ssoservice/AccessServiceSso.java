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
	
	@SuppressWarnings("unchecked")
	@Override
	public SingleResultSupplierSourceResource insertSupplierSourceResource(SupplierSourceResource resource) {
		return accessService.insertSupplierSourceResource(resource);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean deleteSupplierSourceResource(SupplierSourceResource resource) {
		return accessService.deleteSupplierSourceResource(resource);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean deleteResourceAccess(ResourceAccessListItem access) {
		return accessService.deleteResourceAccess(access);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultResourceAccessListItem getAccessListForAll() {
		return accessService.getAccessListForAll();
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultResourceAccessListItem getAccessListByOrganization(OrganizationListItem organization) {
		return getAccessListByOrganization(organization);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultResourceAccessListItem getAccessListByOrganizationType(OrganizationTypeKey organizationType) {
		return accessService.getAccessListByOrganizationType(organizationType);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultResourceAccessListItem getAccessListByRole(UserRoleKey role) {
		return accessService.getAccessListByRole(role);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultResourceAccessListItem getAccessListByUser(UserListItem user) {
		return accessService.getAccessListByUser(user);
	}
	@SuppressWarnings("unchecked")
	@Override
	public SingleResultAccessType getAccessTypeByTypeCategory(AccessTypeKey accessTypeKey, AccessTypeCategory accessTypeCategory) {
		return accessService.getAccessTypeByTypeCategory(accessTypeKey, accessTypeCategory);
	}
	@SuppressWarnings("unchecked")
	@Override
	public SingleResultResourceType getResourceTypeByKey(ResourceTypeKey resourceTypeKey){
		return accessService.getResourceTypeByKey(resourceTypeKey);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultSupplierSource getSupplierSourceListAll(String DUMMY) {
		return accessService.getSupplierSourceListAll(DUMMY);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean insertOrganizationResourceAccess(Organization organization, ResourceAccess access) {
		return accessService.insertOrganizationResourceAccess(organization, access);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean insertOrganizationTypeResourceAccess(OrganizationType organizationType, ResourceAccess access) {
		return accessService.insertOrganizationTypeResourceAccess(organizationType, access);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean insertUserResourceAccess(User user, ResourceAccess access) {
		return accessService.insertUserResourceAccess(user, access);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Boolean insertUserRoleResourceAccess(Role userRole, ResourceAccess access) {
		return accessService.insertUserRoleResourceAccess(userRole, access);
	}
	@SuppressWarnings("unchecked")
	@Override
	public ListResultSupplierSourceResource getSupplierSourceResourceListAll(String DUMMY) {
		return accessService.getSupplierSourceResourceListAll(DUMMY);
	}
	@SuppressWarnings("unchecked")
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
}