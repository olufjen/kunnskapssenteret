package no.helsebiblioteket.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.dao.PositionDao;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.PositionList;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.request.PageRequest;
import no.helsebiblioteket.admin.request.PageResult;
import no.helsebiblioteket.admin.service.OrganizationService;

public class OrganizationServiceImpl implements OrganizationService {
	private static final long serialVersionUID = 1L;
	private OrganizationDao organizationDao;
	private OrganizationTypeDao organizationTypeDao;
	private PositionDao positionDao;
	
	public void saveOrganization(Organization organization) {
		this.organizationDao.saveOrganization(organization);
	}

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
		
		// TODO: Must set the value here!
		for (Organization organization : allOrganizations) {
			if(organization.getNameList() == null){
				ArrayList<OrganizationName> list = new ArrayList<OrganizationName>();
				organization.setNameList(list);
			}
		}
		
		
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
	public void createOrganization(Organization organization) {
		// TODO: Save new organizations!
		// TODO: Is this doing the right thing?
		this.organizationDao.saveOrganization(organization);
	}
	public PositionList getAllPositions(String dummy) {
		PositionList list = new PositionList();
		List<Position> res = this.positionDao.getAllPositions();
		Position arr[] = new Position[res.size()];
		int i=0;
		for (Position position : res) {
			arr[i++] = position;
		}
		list.setList(arr);
		return list;
	}

	public void setOrganizationDao(OrganizationDao organizationDao) { this.organizationDao = organizationDao; }

	public void setOrganizationTypeDao(OrganizationTypeDao organizationTypeDao) {
		this.organizationTypeDao = organizationTypeDao;
	}

	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	public OrganizationType getOrganizationTypeByKey(String key) {
		return this.organizationDao.getOrganizationTypeByKey(key);
	}
}