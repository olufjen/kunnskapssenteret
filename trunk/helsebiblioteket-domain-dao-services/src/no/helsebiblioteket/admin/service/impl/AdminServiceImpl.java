package no.helsebiblioteket.admin.service.impl;

import java.util.List;

import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.service.AdminService;

public class AdminServiceImpl implements AdminService {
	private static final long serialVersionUID = 1L;
	private OrganizationTypeDao organizationTypeDao;

	public void setOrganizationTypeDao(OrganizationTypeDao organizationTypeDao) {
		this.organizationTypeDao = organizationTypeDao;
	}
	
	public List<OrganizationType> getOrganizationTypeList() {
		 return organizationTypeDao.getOrganizationTypeList();
	}
}