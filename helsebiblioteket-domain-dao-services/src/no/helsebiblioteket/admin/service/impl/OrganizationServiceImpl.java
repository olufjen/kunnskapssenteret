package no.helsebiblioteket.admin.service.impl;

import java.util.List;

import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.domain.Contract;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.service.OrganizationService;

public class OrganizationServiceImpl implements OrganizationService {
	private static final long serialVersionUID = 1L;
	private OrganizationDao organizationDao;

	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
	
	public List<Organization> getOrganizationList() {
		return this.organizationDao.getOrganizationList();
	}
	
	public Organization getOrganization(Integer organizationId) {
		return this.organizationDao.getOrganization(organizationId);
	}
	
	public List<SupplierOrganization> getSupplierList() {
		return this.organizationDao.getSupplierList();
	}

	public List<Contract> getContractList(OrganizationType organizationType) {
		return this.organizationDao.getContractList(organizationType);
	}
}