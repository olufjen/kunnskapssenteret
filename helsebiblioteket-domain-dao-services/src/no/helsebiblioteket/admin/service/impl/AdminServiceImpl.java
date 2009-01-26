package no.helsebiblioteket.admin.service.impl;

import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.service.AdminService;

public class AdminServiceImpl implements AdminService {
	private static final long serialVersionUID = 1L;
	private OrganizationTypeDao organizationTypeDao;

	
	// TODO: Remove this class!
	
	public void setOrganizationTypeDao(OrganizationTypeDao organizationTypeDao) {
		this.organizationTypeDao = organizationTypeDao;
	}
	
}
