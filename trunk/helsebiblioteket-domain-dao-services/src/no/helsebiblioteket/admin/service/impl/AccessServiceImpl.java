package no.helsebiblioteket.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.dao.keys.ResourceAccessForeignKeys;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.requestresult.ListResultResourceAccess;
import no.helsebiblioteket.admin.domain.requestresult.ListResultSupplierSource;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultAccessType;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.service.AccessService;

@SuppressWarnings(value={"serial"})
public class AccessServiceImpl implements AccessService {
	private AccessDao accessDao;
	private SupplierSourceDao supplierSourceDao;
	public Boolean insertUserAccess(User user, Access access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setUserId(user.getId());
		this.accessDao.insertResourceAccessForeignKeys(keys);
		return true;
	}
	public Boolean insertUserRoleAccess(Role userRole, Access access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setUserRoleId(userRole.getUserRoleId());
		this.accessDao.insertResourceAccessForeignKeys(keys);
		return true;
	}
	public Boolean insertOrganizationAccess(MemberOrganization organization, Access access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setOrgUnitId(organization.getOrganization().getId());
		this.accessDao.insertResourceAccessForeignKeys(keys);
		return true;
	}
	public Boolean insertOrganizationTypeAccess(OrganizationType organizationType, Access access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setOrgTypeId(organizationType.getId());
		this.accessDao.insertResourceAccessForeignKeys(keys);
		return true;
	}
	
	public ListResultResourceAccess getAccessListByUser(User user) {
		return translateList(this.accessDao.getAccessListByUser(user));
	}
	public ListResultResourceAccess getAccessListByOrganization(MemberOrganization organization) {
		return translateList(this.accessDao.getAccessListByOrganization(organization));
	}
	public ListResultResourceAccess getAccessListByOrganizationType(OrganizationType organizationType) {
		return translateList(this.accessDao.getAccessListByOrganizationType(organizationType));
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
	public ListResultResourceAccess getAccessListByRole(Role role) {
		// TODO Auto-generated method stub
		return null;
	}
	public SingleResultAccessType getAccessTypeByTypeCategory( AccessTypeKey accessTypeKey, AccessTypeCategory accessTypeCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAccessDao(AccessDao accessDao) {
		this.accessDao = accessDao;
	}
	public void setSupplierSourceDao(SupplierSourceDao supplierSourceDao) {
		this.supplierSourceDao = supplierSourceDao;
	}
}
