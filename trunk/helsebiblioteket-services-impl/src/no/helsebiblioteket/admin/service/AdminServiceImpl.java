package no.helsebiblioteket.admin.service;

import java.util.List;

import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.domain.OrganizationType;

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