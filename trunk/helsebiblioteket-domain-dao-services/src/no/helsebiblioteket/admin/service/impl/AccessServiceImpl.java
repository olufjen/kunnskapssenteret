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
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;
import no.helsebiblioteket.admin.domain.requestresult.ListResultResourceAccess;
import no.helsebiblioteket.admin.domain.requestresult.ListResultSupplierSource;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultAccessType;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultResourceType;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultSupplierSourceResource;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultAccessType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultResourceType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSupplierSourceResource;
import no.helsebiblioteket.admin.service.AccessService;

@SuppressWarnings(value={"serial"})
public class AccessServiceImpl implements AccessService {
	private AccessDao accessDao;
	private SupplierSourceDao supplierSourceDao;
	private ResourceTypeDao resourceTypeDao;
	private AccessTypeDao accessTypeDao;
	private ResourceDao resourceDao;
	@Override
	public SingleResultSupplierSourceResource insertSupplierSourceResource(SupplierSourceResource resource) {
		this.resourceDao.insertSupplierSourceResource(resource);
		return new ValueResultSupplierSourceResource(resource);
	}
	@Override
	public Boolean deleteSupplierSourceResource(SupplierSourceResource resource) {
		this.resourceDao.deleteSupplierSourceResource(resource);
		return true;
	}
	@Override
	public Boolean deleteResourceAccess(ResourceAccess access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setResourceAccess(access);
		this.accessDao.deleteResourceAccessForeignKeys(keys);
		return true;
	}
	@Override
	public Boolean insertUserResourceAccess(User user, ResourceAccess access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setUserId(user.getId());
		keys.setResourceAccess(access);
		this.accessDao.insertResourceAccessForeignKeys(keys);
		return true;
	}
	@Override
	public Boolean insertUserRoleResourceAccess(Role userRole, ResourceAccess access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setUserRoleId(userRole.getUserRoleId());
		keys.setResourceAccess(access);
		this.accessDao.insertResourceAccessForeignKeys(keys);
		return true;
	}
	@Override
	public Boolean insertOrganizationResourceAccess(Organization organization, ResourceAccess access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setOrgUnitId(organization.getId());
		keys.setResourceAccess(access);
		this.accessDao.insertResourceAccessForeignKeys(keys);
		return true;
	}
	@Override
	public Boolean insertOrganizationTypeResourceAccess(OrganizationType organizationType, ResourceAccess access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setOrgTypeId(organizationType.getId());
		keys.setResourceAccess(access);
		this.accessDao.insertResourceAccessForeignKeys(keys);
		return true;
	}
	@Override
	public ListResultResourceAccess getAccessListByUser(User user) {
		return translateList(this.accessDao.getAccessListByUser(user));
	}
	@Override
	public ListResultResourceAccess getAccessListByOrganization(Organization organization) {
		return translateList(this.accessDao.getAccessListByOrganization(organization));
	}
	@Override
	public ListResultResourceAccess getAccessListByOrganizationType(OrganizationType organizationType) {
		return translateList(this.accessDao.getAccessListByOrganizationType(organizationType));
	}
	@Override
	public ListResultResourceAccess getAccessListByRole(Role role) {
		return translateList(this.accessDao.getAccessListByUserRole(role));
	}
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
	@Override
	public SingleResultAccessType getAccessTypeByTypeCategory( AccessTypeKey accessTypeKey, AccessTypeCategory accessTypeCategory) {
		return new ValueResultAccessType(this.accessTypeDao.getAccessTypeByKey(accessTypeKey, accessTypeCategory));
	}
	@Override
	public SingleResultResourceType getResourceTypeByKey(ResourceTypeKey resourceTypeKey) {
		return new ValueResultResourceType(this.resourceTypeDao.getResourceTypeByKey(resourceTypeKey));
	}

	
	private ListResultResourceAccess translateList(List<ResourceAccessForeignKeys> keyList) {
		ListResultResourceAccess result = new ListResultResourceAccess();
		ResourceAccess[] list = new ResourceAccess[keyList.size()];
		int i=0;
		for (ResourceAccessForeignKeys key : keyList) {
			list[i] = key.getResourceAccess();
			i++;
		}
		result.setList(list);
		return result;
	}

	
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
