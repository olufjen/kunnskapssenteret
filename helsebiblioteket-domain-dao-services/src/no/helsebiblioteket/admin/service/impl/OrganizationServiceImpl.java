package no.helsebiblioteket.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.request.PageRequest;
import no.helsebiblioteket.admin.request.PageResult;
import no.helsebiblioteket.admin.service.OrganizationService;

public class OrganizationServiceImpl implements OrganizationService {
	private static final long serialVersionUID = 1L;
	private OrganizationDao organizationDao;
	private OrganizationTypeDao organizationTypeDao;

	public List<OrganizationType> getOrganizationTypeList() {
		return this.organizationTypeDao.getOrganizationTypeList();
	}

	public Organization getOrganization(Integer organizationId) {
		return this.organizationDao.getOrganization(organizationId);
	}
	
	public MemberOrganization getMemberOrganization(Integer organizationId) {
		return (MemberOrganization) getOrganization(organizationId);
	}
	
	public List<SupplierOrganization> getSupplierList() {
		return this.organizationDao.getSupplierList();
	}
	
	public List<Organization> getAllOrganizations() {
		return this.organizationDao.getAllOrganizations();
	}
	
	public PageResult<Organization> findOrganizationsBySearchStringRoles( String searchString, PageRequest<Organization> request) {
		List<Organization> allOrganizations = this.organizationDao.getAllOrganizations();
		List<Organization> someOrganizations = new ArrayList<Organization>();
		for (Organization organization : allOrganizations) {
			if(organization.getName().toLowerCase().contains(searchString.toLowerCase())){
				someOrganizations.add(organization);
			}
		}
//		FIXME: REMOVE DUMMY DATA!
//		
//		
//		
//		{
//			Organization organization = new Organization();
//			organization.setName("Sykehuset i Bod�");
//			myOrganizations.add(organization);
//		}
//		{
//			Organization organization = new Organization();
//			organization.setName("Sykehuset i Bod�");
//			myOrganizations.add(organization);
//		}
//		this.organizations = myOrganizations;

		PageResult<Organization> result = new PageResult<Organization>();
		result.from = 0; result.result = someOrganizations; result.total = someOrganizations.size();
		return result;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) { this.organizationDao = organizationDao; }

	public void setOrganizationTypeDao(OrganizationTypeDao organizationTypeDao) {
		this.organizationTypeDao = organizationTypeDao;
	}

	public void createOrganization(Organization organization) {
		// TODO: Save new organizations!
		// TODO: Is this doing the right thing?
		this.organizationDao.saveOrganization(organization);
	}
}
