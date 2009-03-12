package no.helsebiblioteket.admin.service.impl;

import java.util.List;

import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.AccessTypeDao;
import no.helsebiblioteket.admin.dao.ResourceDao;
import no.helsebiblioteket.admin.dao.ResourceTypeDao;
import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.dao.keys.ResourceAccessForeignKeys;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultResourceAccessListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultSupplierSource;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultAccessType;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultResourceType;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultSupplierSourceResource;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultAccessType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultResourceType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSupplierSourceResource;
import no.helsebiblioteket.admin.service.AccessService;
import no.helsebiblioteket.admin.service.URLService;

@SuppressWarnings(value={"serial"})
public class AccessServiceImpl implements AccessService {
	private AccessDao accessDao;
	private SupplierSourceDao supplierSourceDao;
	private ResourceTypeDao resourceTypeDao;
	private AccessTypeDao accessTypeDao;
	private ResourceDao resourceDao;
	/**
	 * Inserts a new resource. Uses the DAO.
	 */
	@Override
	public SingleResultSupplierSourceResource insertSupplierSourceResource(SupplierSourceResource resource) {
		this.supplierSourceDao.insertSupplierSource(resource.getSupplierSource());
		this.resourceDao.insertSupplierSourceResource(resource);
		return new ValueResultSupplierSourceResource(resource);
	}
	/**
	 * Deletes a resource. Delegates to DAO.
	 */
	@Override
	public Boolean deleteSupplierSourceResource(SupplierSourceResource resource) {
		this.resourceDao.deleteSupplierSourceResource(resource);
		return true;
	}
	/**
	 * Deletes access to a resource. The resource is not deleted.
	 */
	@Override
	public Boolean deleteResourceAccess(ResourceAccessListItem access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setResourceAccess(new ResourceAccess());
		keys.getResourceAccess().getAccess().setId(access.getId());
		keys.getResourceAccess().getAccess().setLastChanged(access.getLastChanged());
		this.accessDao.deleteResourceAccessForeignKeys(keys);
		return true;
	}
	/**
	 * Inserts access to a resource for a user.
	 */
	@Override
	public Boolean insertUserResourceAccess(User user, ResourceAccess access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setUserId(user.getId());
		keys.setResourceAccess(access);
		this.accessDao.insertResourceAccessForeignKeys(keys);
		return true;
	}
	/**
	 * Inserts access to a resource for a user role.
	 */
	@Override
	public Boolean insertUserRoleResourceAccess(Role userRole, ResourceAccess access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setUserRoleId(userRole.getId());
		keys.setResourceAccess(access);
		this.accessDao.insertResourceAccessForeignKeys(keys);
		return true;
	}
	/**
	 * Inserts access to a resource for an organization.
	 */
	@Override
	public Boolean insertOrganizationResourceAccess(Organization organization, ResourceAccess access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setOrgUnitId(organization.getId());
		keys.setResourceAccess(access);
		this.accessDao.insertResourceAccessForeignKeys(keys);
		return true;
	}
	/**
	 * Inserts access to a resource for an organization type.
	 */
	@Override
	public Boolean insertOrganizationTypeResourceAccess(OrganizationType organizationType, ResourceAccess access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setOrgTypeId(organizationType.getId());
		keys.setResourceAccess(access);
		this.accessDao.insertResourceAccessForeignKeys(keys);
		return true;
	}
	/**
	 * Retrieves all the accesses a user has. This is not used to check if a user
	 * has access to a resource. A user may have access through the user role,
	 * organization or organization type.
	 * 
	 * @see URLService#hasAccess(User, Url)
	 */
	@Override
	public ListResultResourceAccessListItem getAccessListByUser(User user) {
		return translateList(this.accessDao.getAccessListByUser(user));
	}
	/**
	 * Retrieves all the accesses an organization has. This is not used to check
	 * if an organization has access to a resource. An organization may have access
	 * through the organization type.
	 * 
	 * @see URLService#hasAccess(no.helsebiblioteket.admin.domain.MemberOrganization, Url)
	 */
	@Override
	public ListResultResourceAccessListItem getAccessListByOrganization(Organization organization) {
		return translateList(this.accessDao.getAccessListByOrganization(organization));
	}
	/**
	 * Retrieves all the accesses an organization type has. This is not used to
	 * check if an organization has access to a resource. Use 
	 * URLService.hasAccess(MemberOrganization, Url) for that.
	 * 
	 * @see URLService#hasAccess(no.helsebiblioteket.admin.domain.MemberOrganization, Url)
	 */
	@Override
	public ListResultResourceAccessListItem getAccessListByOrganizationType(OrganizationType organizationType) {
		return translateList(this.accessDao.getAccessListByOrganizationType(organizationType));
	}
	/**
	 * Retrieves all the accesses a user role has. This is not used to
	 * check if a user has access to a resource. Use 
	 * URLService.hasAccess(User, Url) for that.
	 * 
	 * @see URLService#hasAccess(User, Url)
	 */
	@Override
	public ListResultResourceAccessListItem getAccessListByRole(Role role) {
		return translateList(this.accessDao.getAccessListByUserRole(role));
	}
	/**
	 * Retrieves a list of all the supplier sources. The list result should perhaps
	 * be replaced by a paged result, but there are still just a few of these in
	 * the database.
	 */
	@Override
	public ListResultSupplierSource getSupplierSourceListAll(String DUMMY) {
		List<SupplierSource> all = this.supplierSourceDao.getSupplierSourceListAll();
		ListResultSupplierSource result = new ListResultSupplierSource();
		SupplierSource[] list = new SupplierSource[all.size()];
		int i = 0;
		for (SupplierSource supplierSource : all) {
			list[i] = supplierSource;
			i++;
		}
		result.setList(list);
		return result;
	}
	/**
	 * Finds the 'static' value for the Access Type. These tables are rarely
	 * edited and the result is usually buffered.
	 */
	@Override
	public SingleResultAccessType getAccessTypeByTypeCategory( AccessTypeKey accessTypeKey, AccessTypeCategory accessTypeCategory) {
		return new ValueResultAccessType(this.accessTypeDao.getAccessTypeByKey(accessTypeKey, accessTypeCategory));
	}
	/**
	 * Finds the 'static' value for the Resource Type. These tables are rarely
	 * edited and the result is usually buffered.
	 */
	@Override
	public SingleResultResourceType getResourceTypeByKey(ResourceTypeKey resourceTypeKey) {
		return new ValueResultResourceType(this.resourceTypeDao.getResourceTypeByKey(resourceTypeKey));
	}

	
	/**
	 * Helper. Translates java.util.List into List Result.
	 * 
	 * @param keyList
	 * @return
	 */
	private ListResultResourceAccessListItem translateList(List<ResourceAccessListItem> itemList) {
		ListResultResourceAccessListItem result = new ListResultResourceAccessListItem();
		ResourceAccessListItem[] list = new ResourceAccessListItem[itemList.size()];
		int i=0;
		for (ResourceAccessListItem key : itemList) {
			list[i] = key;
//			list[i].setId(key.getResourceAccess().getAccess().getId());
//			list[i].setCategory(key.getResourceAccess().getAccess().getAccessType().getCategory());
//			list[i].setKey(key.getResourceAccess().getAccess().getAccessType().getKey());
//			list[i].setUrl(key.getResourceAccess().getResource().getSupplierSource().getUrl());
			i++;
		}
		result.setList(list);
		return result;
	}


	// DAO. Set by dependency injection.
	public void setAccessDao(AccessDao accessDao) {
		this.accessDao = accessDao;
	}
	public void setSupplierSourceDao(SupplierSourceDao supplierSourceDao) {
		this.supplierSourceDao = supplierSourceDao;
	}
	public void setResourceTypeDao(ResourceTypeDao resourceTypeDao) {
		this.resourceTypeDao = resourceTypeDao;
	}
	public void setAccessTypeDao(AccessTypeDao accessTypeDao) {
		this.accessTypeDao = accessTypeDao;
	}
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
}
