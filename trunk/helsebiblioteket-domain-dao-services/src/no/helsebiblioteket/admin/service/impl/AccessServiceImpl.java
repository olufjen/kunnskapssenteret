package no.helsebiblioteket.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.daoobjects.ResourceAccessForeignKeys;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.UserRole;
import no.helsebiblioteket.admin.service.AccessService;

@SuppressWarnings(value={"serial"})
public class AccessServiceImpl implements AccessService {
	private AccessDao accessDao;
	private SupplierSourceDao supplierSourceDao;
	public void insertUserAccess(User user, Access access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setUserId(user.getId());
		this.accessDao.insertResourceAccessForeignKeys(keys);
	}
	public void insertUserRoleAccess(UserRole userRole, Access access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setUserRoleId(userRole.getUserRoleId());
		this.accessDao.insertResourceAccessForeignKeys(keys);
	}
	public void insertOrganizationAccess(Organization organization, Access access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setOrgUnitId(organization.getId());
		this.accessDao.insertResourceAccessForeignKeys(keys);
	}
	public void insertOrganizationTypeAccess(OrganizationType organizationType, Access access) {
		ResourceAccessForeignKeys keys = new ResourceAccessForeignKeys();
		keys.setOrgTypeId(organizationType.getId());
		this.accessDao.insertResourceAccessForeignKeys(keys);
	}
	
	public List<ResourceAccess> getAccessListByUser(User user) {
		return translateList(this.accessDao.getAccessListByUser(user));
	}
	public List<ResourceAccess> getAccessListByOrganization(MemberOrganization organization) {
		return translateList(this.accessDao.getAccessListByOrganization(organization));
	}
	public List<ResourceAccess> getAccessListByOrganizationType(OrganizationType organizationType) {
		return translateList(this.accessDao.getAccessListByOrganizationType(organizationType));
	}
	private List<ResourceAccess> translateList(List<ResourceAccessForeignKeys> keyList) {
		List<ResourceAccess> result = new ArrayList<ResourceAccess>();
		for (ResourceAccessForeignKeys key : keyList) {
			result.add(key.getResourceAccess());
		}
		return result;
	}
	public void setAccessDao(AccessDao accessDao) {
		this.accessDao = accessDao;
	}
	public List<SupplierSource> getSupplierSourceListAll() {
		return this.supplierSourceDao.getSupplierSourceListAll();
	}
	public void setSupplierSourceDao(SupplierSourceDao supplierSourceDao) {
		this.supplierSourceDao = supplierSourceDao;
	}
}
