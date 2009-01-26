package no.helsebiblioteket.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import no.helsebiblioteket.admin.ModifiedListHelper;
import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.ContactInformationDao;
import no.helsebiblioteket.admin.dao.IpRangeDao;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.dao.PositionDao;
import no.helsebiblioteket.admin.dao.ResourceDao;
import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpRange;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.PositionList;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.request.PageRequest;
import no.helsebiblioteket.admin.request.PageResult;
import no.helsebiblioteket.admin.service.OrganizationService;

public class OrganizationServiceImpl implements OrganizationService {
	private static final long serialVersionUID = 1L;
	private OrganizationDao organizationDao;
	private OrganizationTypeDao organizationTypeDao;
	private PositionDao positionDao;
	private PersonDao personDao;
	private AccessDao accessDao;
	private ContactInformationDao contactInformationDao;
	private IpRangeDao ipRangeDao;
	private ResourceDao resourceDao;
	private SupplierSourceDao supplierSourceDao;
	
	
	// daos
	
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}

	public void setOrganizationTypeDao(OrganizationTypeDao organizationTypeDao) {
		this.organizationTypeDao = organizationTypeDao;
	}

	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}

	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}

	public void setAccessDao(AccessDao accessDao) {
		this.accessDao = accessDao;
	}

	public void setContactInformationDao(ContactInformationDao contactInformationDao) {
		this.contactInformationDao = contactInformationDao;
	}

	public void setIpRangeDao(IpRangeDao ipRangeDao) {
		this.ipRangeDao = ipRangeDao;
	}

	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	public void setSupplierSourceDao(SupplierSourceDao supplierSourceDao) {
		this.supplierSourceDao = supplierSourceDao;
	}
	
	
	// service methods

	public OrganizationType getOrganizationTypeByKey(String key) {
		return this.organizationDao.getOrganizationTypeByKey(key);
	}
	
	public void saveOrganization(Organization organization) {
		saveOrganization(organization, organizationDao.getOrganizationById(organization.getId()));
	}
	
	public List<Organization> getOrganizationList() {
		List<Organization> organizationList = null;
		organizationList = organizationDao.getOrganizationList();
		List<Organization> organizationListPopulated = new ArrayList<Organization>();
		for (Organization organization : organizationList) {
			organizationListPopulated.add(populateOrganization(organization));
		}
		return organizationListPopulated;
	}
	
	public void saveOrganization(Organization changedOrganization, Organization originalOrganization) {
		if (changedOrganization.getId() != null) {
			// TODO: Handle optimistic locking.
			//if (originalOrganization == null || (originalOrganization != null && !originalOrganization.getLastChanged().equals(changedOrganization.getLastChanged()))) {
			//	throw new OptimisticLockingFailureException("Organization has been changed by another caller since last time it was loaded from datastore");
			//}
			updateOrganization(changedOrganization, originalOrganization);
		} else {
			insertOrganization(changedOrganization);
		}
	}
	
	public Organization getOrganizationById(Integer organizationId) {
		Organization organization = null;
		organization = organizationDao.getOrganizationById(organizationId);
		organization = populateOrganization(organization);
		return organization;
	}
	
	public List<OrganizationType> getOrganizationTypeList() {
		return this.organizationTypeDao.getOrganizationTypeList();
	}
	
	public PageResult<Organization> findOrganizationsBySearchStringRoles( String searchString, PageRequest<Organization> request) {
		List<Organization> allOrganizations = this.organizationDao.getOrganizationList();
		
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
		PageResult<Organization> result = new PageResult<Organization>();
		result.from = 0; result.result = someOrganizations; result.total = someOrganizations.size();
		return result;
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
	
	private void updateOrganization(Organization changedOrganization, Organization originalOrganization) {		
		organizationDao.updateOrganization(changedOrganization);
		setForeignKeysForOrganization(changedOrganization);
		savePerson(changedOrganization.getContactPerson(), changedOrganization.getContactPerson());
		saveContactInformation(changedOrganization.getContactInformation(), originalOrganization.getContactInformation());
		saveIpRangeList(changedOrganization.getIpRangeList(), originalOrganization.getIpRangeList());
		saveOrganizationNameList(changedOrganization.getNameList(), originalOrganization.getNameList());
		saveAccessList(changedOrganization.getAccessList(), originalOrganization.getAccessList());
		saveSupplierSourceList(changedOrganization.getSupplierSourceList(), originalOrganization.getSupplierSourceList());
	}
	
	private void savePerson(Person person, Person originalPerson) {
		saveContactInformation(person.getContactInformation(), originalPerson.getContactInformation());
		
		if (person.getId() != null) {
			personDao.updatePerson(person);
		} else {
			personDao.insertPerson(person);
		}
	}
	
	private void saveContactInformation(ContactInformation changedContactInformation, ContactInformation originalContactInformation) {
		if (originalContactInformation != null && changedContactInformation == null) {
			contactInformationDao.deleteContactInformation(originalContactInformation);
		} else {
			if (changedContactInformation.getId() == null) {
				contactInformationDao.insertContactInformation(changedContactInformation);
			} else {
				contactInformationDao.updateContactInformation(changedContactInformation);
			}
		}
	}
	
	private void saveAccess(Access access) {
		
	}
	
	
	
	private void saveIpRangeList(List<IpRange> changedIpRangeList, List<IpRange> originalIpRangeList) {
		ModifiedListHelper<IpRange> listHelper = new ModifiedListHelper<IpRange>();
		List<IpRange> deleteList = listHelper.getDeleteList(changedIpRangeList, originalIpRangeList);
		List<IpRange> insertAndUpdateList = listHelper.getInsertAndUpdateList(changedIpRangeList, originalIpRangeList);
		if (deleteList != null) {
			for (IpRange ipRange : deleteList) {
				ipRangeDao.deleteIpRange(ipRange);
			}
		}
		if (insertAndUpdateList != null) {
			for (IpRange ipRange : insertAndUpdateList) {
				ipRangeDao.saveIpRange(ipRange);
			}
		}
	}
	
	private void saveOrganizationNameList(List<OrganizationName> changedOrganizationNameList, List<OrganizationName> originalOrganizationNameList) {
		ModifiedListHelper<OrganizationName> listHelper = new ModifiedListHelper<OrganizationName>();
		List<OrganizationName> deleteList = listHelper.getDeleteList(changedOrganizationNameList, originalOrganizationNameList);;
		List<OrganizationName> insertAndUpdateList = listHelper.getInsertAndUpdateList(changedOrganizationNameList, originalOrganizationNameList);
		if (deleteList != null) {
			for (OrganizationName organizationName : deleteList) {
				organizationDao.deleteOrganizationName(organizationName);
			}
		}
		if (insertAndUpdateList != null) {
			for (OrganizationName organizationName : insertAndUpdateList) {
				organizationDao.saveOrganizationName(organizationName);
			}
		}
	}
	
	private void saveAccessList(List<Access> changedAccessList, List<Access> originalAccessList) {
		ModifiedListHelper<Access> listHelper = new ModifiedListHelper<Access>();
		List<Access> deleteList = listHelper.getDeleteList(changedAccessList, originalAccessList);
		List<Access> insertAndUpdateList = listHelper.getInsertAndUpdateList(changedAccessList, originalAccessList);
		if (deleteList != null) {
			for (Access access : deleteList) {
				accessDao.deleteAccess(access);
			}
		}
		if (insertAndUpdateList != null) {
			for (Access access : insertAndUpdateList) {
				saveAccess(access);
			}
		}
	}
	
	private void insertOrganization(Organization organization) {
		if (organization.getContactPerson() != null) {
			if (organization.getContactPerson().getContactInformation() != null) {
				contactInformationDao.insertContactInformation(organization.getContactPerson().getContactInformation());
			}
			personDao.insertPerson(organization.getContactPerson());
		}
		
		if (organization.getContactInformation() != null) {
			contactInformationDao.insertContactInformation(organization.getContactInformation());
		}
		
		organizationDao.insertOrganization(organization);
		setForeignKeysForOrganization(organization);
		
		if (organization.getNameList() != null) {
			for (OrganizationName organizationName : organization.getNameList()) {		
				organizationDao.insertOrganizationName(organizationName);
			}
		}
		if (organization.getIpRangeList() != null) {
			for (IpRange ipRange : organization.getIpRangeList()) {
				ipRangeDao.insertIpRange(ipRange);
			}
		}
	}
	
	private Organization populateOrganization(Organization organization) {
		//organization = (Organization) getSqlMapClientTemplate().queryForObject("getOrganizationById", organization.getId());
		if (organization.getParent() != null) {
			organization.setParent(organizationDao.getOrganizationById(organization.getParent().getId()));
		}
		if (organization.getContactInformation() != null) {
			organization.setContactInformation(contactInformationDao.getContactInformationById(organization.getContactInformation().getId()));
		}
		if (organization.getContactPerson() != null) {
			if (organization.getContactPerson().getContactInformation() != null) {
				organization.getContactPerson().setContactInformation(contactInformationDao.getContactInformationById(organization.getContactPerson().getContactInformation().getId()));
			}
			organization.setContactPerson(personDao.getPersonById(organization.getContactPerson().getId()));
		}
		organization.setIpRangeList(ipRangeDao.getIpRangeListByOrganizationId(organization.getId()));
		organization.setAccessList(accessDao.getAccessListByOrganizationId(organization.getId()));
		organization.setNameList(organizationDao.getOrganizationNameListByOrganizationId(organization.getId()));	
		return organization;
	}
	
	private void saveSupplierSourceList(List<SupplierSource> changedSupplierSourceList, List<SupplierSource> originalSupplierSourceList) {
		ModifiedListHelper<SupplierSource> listHelper = new ModifiedListHelper<SupplierSource>();
		List<SupplierSource> deleteList = listHelper.getDeleteList(changedSupplierSourceList, originalSupplierSourceList);
		List<SupplierSource> insertAndUpdateList = listHelper.getInsertAndUpdateList(changedSupplierSourceList, originalSupplierSourceList);
		if (deleteList != null) {
			for (SupplierSource supplierSource : deleteList) {
				supplierSourceDao.deleteSupplierSource(supplierSource);
				resourceDao.deleteResource(supplierSource);
			}
		}
		if (insertAndUpdateList != null) {
			for (SupplierSource supplierSource : insertAndUpdateList) {
				saveSupplierSource(supplierSource);
			}
		}
	}
	
	private void saveSupplierSource(SupplierSource supplierSource) {
		if (supplierSource.getSupplierSourceId() == null) {
			supplierSourceDao.insertSupplierSource(supplierSource);
			resourceDao.insertResource(supplierSource);
		} else {
			supplierSourceDao.updateSupplierSource(supplierSource);
			resourceDao.updateResource(supplierSource);
		}
	}
	
	
	// helper methods
	private void setForeignKeysForOrganization(Organization organization) {
		if (organization != null) {
			if (organization.getIpRangeList() != null) {
				for (IpRange ipRange : organization.getIpRangeList()) {
					ipRange.setOrganizationId(organization.getId());
				}
			}
			if (organization.getNameList() != null) {
				for (OrganizationName organizationName: organization.getNameList()) {
					organizationName.setOrganizationId(organization.getId());
				}
			}
		}
	}
}