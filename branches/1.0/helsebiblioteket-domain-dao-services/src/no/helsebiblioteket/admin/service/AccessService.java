package no.helsebiblioteket.admin.service;

/**
 * Services related to giving access and finding what
 * access users and organizations have.
 */

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.Role;
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

public interface AccessService extends Serializable {
	public SingleResultSupplierSourceResource insertSupplierSourceResource(SupplierSourceResource resource);
	public Boolean deleteSupplierSourceResource(SupplierSourceResource resource);
	public Boolean deleteResourceAccess(ResourceAccessListItem access);
	
	public Boolean insertUserResourceAccess(User user, ResourceAccess access);
	public Boolean insertUserRoleResourceAccess(Role userRole, ResourceAccess access);
	public Boolean insertOrganizationResourceAccess(Organization organization, ResourceAccess access);
	public Boolean insertOrganizationTypeResourceAccess(OrganizationType organizationType, ResourceAccess access);
	public Boolean insertNationalResourceAccess(ResourceAccess access);

	public SingleResultAccessType getAccessTypeByTypeCategory(AccessTypeKey accessTypeKey, AccessTypeCategory accessTypeCategory);
	public SingleResultResourceType getResourceTypeByKey(ResourceTypeKey resourceTypeKey);

	public ListResultResourceAccessListItem getAccessListForAll();
	public ListResultResourceAccessListItem getAccessListByUser(UserListItem user);
	public ListResultResourceAccessListItem getAccessListByRole(UserRoleKey role);
	public ListResultResourceAccessListItem getAccessListByOrganization(OrganizationListItem organization);
	public ListResultResourceAccessListItem getAccessListByOrganizationType(OrganizationTypeKey organizationType);
	public ListResultResourceAccessListItem getAccessListNational();

	public ListResultSupplierSource getSupplierSourceListAll(String DUMMY);
	public SingleResultSupplierSource getSupplierSourceByDomain(Url url);
	public ListResultSupplierSourceResource getSupplierSourceResourceListAll(String DUMMY);
	
	public ListResultProxyConfig getProxyConfigListAll(String DUMMY);
}