package no.helsebiblioteket.admin.service.impl;

import java.util.List;

import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.AccessService;

@SuppressWarnings(value={"serial"})
public class AccessServiceImpl implements AccessService {
	private AccessDao accessDao;
	private SupplierSourceDao supplierSourceDao;
	
	public List<ResourceAccess> getAccessListByUser(User user) {
		return this.accessDao.getAccessListByUser(user);
	}
	public List<ResourceAccess> getAccessListByOrganization(MemberOrganization organization) {
		return this.accessDao.getAccessListByOrganization(organization);
	}
	public List<ResourceAccess> getAccessListByOrganizationType(OrganizationType organizationType) {
		return this.accessDao.getAccessListByOrganizationType(organizationType);
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
