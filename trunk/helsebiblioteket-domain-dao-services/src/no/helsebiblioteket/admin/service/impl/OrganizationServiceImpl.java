package no.helsebiblioteket.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import no.helsebiblioteket.admin.ModifiedListHelper;
import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.ContactInformationDao;
import no.helsebiblioteket.admin.dao.IpRangeDao;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.OrganizationListDao;
import no.helsebiblioteket.admin.dao.OrganizationNameDao;
import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.dao.PositionDao;
import no.helsebiblioteket.admin.dao.ResourceDao;
import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.daoobjects.OrganizationName;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpRange;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.PositionList;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.listobjects.OrganizationListItem;
import no.helsebiblioteket.admin.requestresult.EmptyResult;
import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.OrganizationService;

public class OrganizationServiceImpl implements OrganizationService {
	private static final long serialVersionUID = 1L;
	private OrganizationDao organizationDao;
	private OrganizationListDao organizationListDao;
	private OrganizationTypeDao organizationTypeDao;
	private OrganizationNameDao organizationNameDao;
	private PositionDao positionDao;
	private PersonDao personDao;
	private AccessDao accessDao;
	private ContactInformationDao contactInformationDao;
	private IpRangeDao ipRangeDao;
	private ResourceDao resourceDao;
	private SupplierSourceDao supplierSourceDao;

	// service methods

	public ListResult<OrganizationType> getOrganizationTypeListAll(String DUMMY) {
		List<OrganizationType> all = this.organizationTypeDao.getOrganizationTypeListAll();
		OrganizationType[] list = new OrganizationType[all.size()];
		int i=0;
		for (OrganizationType organizationType : all) {
			list[i++]=organizationType;
		}
		return new ListResult<OrganizationType>(list);
	}
	public SingleResult<OrganizationType> getOrganizationTypeByKey(String key){
		OrganizationType organizationType = this.organizationTypeDao.getOrganizationTypeByKey(key);
		if(organizationType == null){
			return new EmptyResult<OrganizationType>();
		} else {
			return new ValueResult<OrganizationType>(organizationType);
		}
	}
	public PageResult<Organization> getOrganizationListAll(PageRequest<Organization> request) {
		List<Organization> organizationList = null;
		organizationList = organizationListDao.getOrganizationListAll();
		List<Organization> organizationListPopulated = new ArrayList<Organization>();
		for (Organization organization : organizationList) {
			organizationListPopulated.add(populateOrganization(organization));
		}
		PageResult<Organization> result = new PageResult<Organization>();
		result.result = organizationListPopulated;
		result.from = 0;
		result.total = organizationListPopulated.size();
		return result;
	}
	public PageResult<Organization> findOrganizationsBySearchString( String searchString, PageRequest<Organization> request){
		List<Organization> allOrganizations = this.organizationListDao.getOrganizationListAll();
		
		// TODO: Do this search in the database!
		List<Organization> someOrganizations = new ArrayList<Organization>();
		for (Organization organization : allOrganizations) {
			if(organization.getNameEnglish().toLowerCase().contains(searchString.toLowerCase())){
				someOrganizations.add(organization);
			} else if(organization.getNameShortEnglish().toLowerCase().contains(searchString.toLowerCase())){
				someOrganizations.add(organization);
			} else if(organization.getNameNorwegian().toLowerCase().contains(searchString.toLowerCase())){
				someOrganizations.add(organization);
			} else if(organization.getNameShortNorwegian().toLowerCase().contains(searchString.toLowerCase())){
				someOrganizations.add(organization);
			}
		}
		List<Organization> organizationListPopulated = new ArrayList<Organization>();
		for (Organization organization : someOrganizations) {
			organizationListPopulated.add(populateOrganization(organization));
		}
		PageResult<Organization> result = new PageResult<Organization>();
		result.from = 0;
		result.result = organizationListPopulated;
		result.total = organizationListPopulated.size();
		return result;
	}
	public SingleResult<Organization> getOrganizationByListItem(OrganizationListItem organizationListItem) {
		Organization organization = null;
		organization = organizationDao.getOrganizationById(organizationListItem.getId());
		if(organization != null){
			organization = populateOrganization(organization);
			return new ValueResult<Organization>(organization);
		} else {
			return new EmptyResult<Organization>();
		}
	}
	public Boolean insertOrganization(Organization organization) {
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
		
		if (organization.getIpRangeList() != null) {
			for (IpRange ipRange : organization.getIpRangeList()) {
				ipRangeDao.insertIpRange(ipRange);
			}
		}
		return Boolean.TRUE;
	}
	public Boolean updateOrganization(Organization organization) {
		// TODO: Rewrite this!
		this.updateOrganization(organization, organizationDao.getOrganizationById(organization.getId()));
		return Boolean.TRUE;
	}


	
	private Organization populateOrganization(Organization organization) {
		// TODO: Rewrite this!
		
		//organization = (Organization) getSqlMapClientTemplate().queryForObject("getOrganizationById", organization.getId());
		if (organization.getParent() != null) {
			organization.setParent(organizationDao.getOrganizationById(organization.getParent().getId()));
		}
		if (organization.getContactInformation() != null) {
			organization.setContactInformation(contactInformationDao.getContactInformationByOrganization(organization));
		}
		if (organization.getContactPerson() != null) {
			if (organization.getContactPerson().getContactInformation() != null) {
				organization.getContactPerson().setContactInformation(contactInformationDao.getContactInformationByPerson(organization.getContactPerson()));
			}
			organization.setContactPerson(personDao.getPersonByOrganization(organization));
		}
		organization.setIpRangeList(ipRangeDao.getIpRangeListByOrganization(organization));
		organization.setAccessList(accessDao.getAccessListByOrganization(organization));
		List<OrganizationName> nameList = organizationNameDao.getOrganizationNameListByOrganization(organization);
		// FIXME: Set the names of the organization!
//		organization.setNameList();
		return organization;
	}

	
	
	
	

	
	private void saveOrganization(Organization organization) {
		// TODO: Remove!
//		saveOrganization(organization, organizationDao.getOrganizationById(organization.getId()));
	}
	private void saveOrganization(Organization changedOrganization, Organization originalOrganization) {
		// TODO: Remove!
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
	private PositionList getAllPositions(String dummy) {
		// TODO: Remove!
		PositionList list = new PositionList();
//		List<Position> res = this.positionDao.getAllPositions();
//		Position arr[] = new Position[res.size()];
//		int i=0;
//		for (Position position : res) {
//			arr[i++] = position;
//		}
//		list.setList(arr);
		return list;
	}
	
	private void updateOrganization(Organization changedOrganization, Organization originalOrganization) {		
		// TODO: Rewrite this!
		organizationDao.updateOrganization(changedOrganization);
		setForeignKeysForOrganization(changedOrganization);
		savePerson(changedOrganization.getContactPerson(), changedOrganization.getContactPerson());
		saveContactInformation(changedOrganization.getContactInformation(), originalOrganization.getContactInformation());
		saveIpRangeList(changedOrganization.getIpRangeList(), originalOrganization.getIpRangeList());
		
		// TODO: Rewrite saveOrganizationNameList
//		saveOrganizationNameList(changedOrganization.getNameList(), originalOrganization.getNameList());

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
				// FIXME: Insert or update!
//				ipRangeDao.saveIpRange(ipRange);
			}
		}
	}
	
	private void saveOrganizationNameList(List<OrganizationName> changedOrganizationNameList, List<OrganizationName> originalOrganizationNameList) {
		ModifiedListHelper<OrganizationName> listHelper = new ModifiedListHelper<OrganizationName>();
		List<OrganizationName> deleteList = listHelper.getDeleteList(changedOrganizationNameList, originalOrganizationNameList);;
		List<OrganizationName> insertAndUpdateList = listHelper.getInsertAndUpdateList(changedOrganizationNameList, originalOrganizationNameList);
		if (deleteList != null) {
			for (OrganizationName organizationName : deleteList) {
				// FIXME: Insert or update!
//				organizationDao.deleteOrganizationName(organizationName);
			}
		}
		if (insertAndUpdateList != null) {
			for (OrganizationName organizationName : insertAndUpdateList) {
				// FIXME: Insert or update!
//			organizationDao.saveOrganizationName(organizationName);
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
	
	private void insertOrganization2(Organization organization) {
		// TODO: Remove
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
		
		// FIXME: Insert name list!
//		if (organization.getNameList() != null) {
//			for (OrganizationName organizationName : organization.getNameList()) {		
//				organizationDao.insertOrganizationName(organizationName);
//			}
//		}
		if (organization.getIpRangeList() != null) {
			for (IpRange ipRange : organization.getIpRangeList()) {
				ipRangeDao.insertIpRange(ipRange);
			}
		}
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
			// FIXME: Insert name list!
//			if (organization.getNameList() != null) {
//				for (OrganizationName organizationName: organization.getNameList()) {
//					organizationName.setOrganizationId(organization.getId());
//				}
//			}
		}
	}
	
	
	
	
	
	
	



	// Set DAOs

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
}
