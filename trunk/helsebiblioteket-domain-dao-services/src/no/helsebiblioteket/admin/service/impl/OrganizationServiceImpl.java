package no.helsebiblioteket.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import no.helsebiblioteket.admin.dao.ResourceDao;
import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.daoobjects.LanguageCategory;
import no.helsebiblioteket.admin.daoobjects.OrganizationName;
import no.helsebiblioteket.admin.daoobjects.OrganizationNameCategory;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpRange;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
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
import no.helsebiblioteket.admin.service.PersonService;

public class OrganizationServiceImpl implements OrganizationService {
	private static final long serialVersionUID = 1L;
	private PersonService personService;
	private OrganizationDao organizationDao;
	private OrganizationListDao organizationListDao;
	private OrganizationTypeDao organizationTypeDao;
	private OrganizationNameDao organizationNameDao;
	private PersonDao personDao;
	private AccessDao accessDao;
	private ContactInformationDao contactInformationDao;
	private IpRangeDao ipRangeDao;
	private ResourceDao resourceDao;
	private SupplierSourceDao supplierSourceDao;

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
		List<Organization> organizationList = organizationListDao.getOrganizationListAll();
		for (Organization organization : organizationList) {
			populateOrganizationNames(organization);
			populateOrganizationRest(organization);
		}
		PageResult<Organization> result = new PageResult<Organization>();
		result.result = organizationList;
		result.from = 0;
		result.total = organizationList.size();
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
		for (Organization organization : someOrganizations) {
			populateOrganizationNames(organization);
			populateOrganizationRest(organization);
		}
		PageResult<Organization> result = new PageResult<Organization>();
		result.from = 0;
		result.result = someOrganizations;
		result.total = someOrganizations.size();
		return result;
	}
	public SingleResult<Organization> getOrganizationByListItem(OrganizationListItem organizationListItem) {
		Organization organization = null;
		organization = organizationDao.getOrganizationById(organizationListItem.getId());
		if(organization != null){
			populateOrganizationNames(organization);
			populateOrganizationRest(organization);
			return new ValueResult<Organization>(organization);
		} else {
			return new EmptyResult<Organization>();
		}
	}
	public Boolean insertOrganization(Organization organization) {

		checkNull(organization);
		
		for (Access access : organization.getAccessList()) {
			this.accessDao.insertAccess(access);
		}
		this.contactInformationDao.insertContactInformation(organization.getContactInformation());
		this.personService.insertPerson(organization.getContactPerson());
		for (IpRange ipRange : organization.getIpRangeList()) {
			this.ipRangeDao.insertIpRange(ipRange);
		}
		List<OrganizationName> orgNameList = createNameList(organization);
		for (OrganizationName organizationName : orgNameList) {
			this.organizationNameDao.insertOrganizationName(organizationName);
		}
		for (SupplierSource supplierSource : organization.getSupplierSourceList()) {
			this.supplierSourceDao.insertSupplierSource(supplierSource);
		}
		// TODO: Set foreign keys!
		this.organizationDao.insertOrganization(organization);
		return Boolean.TRUE;
	}
	public Boolean updateOrganization(Organization organization) {
		checkNull(organization);
		Organization old = this.organizationDao.getOrganizationById(organization.getId());

		saveAccessList(organization.getAccessList(), old.getAccessList());
		this.contactInformationDao.updateContactInformation(organization.getContactInformation());
		this.personService.updatePerson(organization.getContactPerson());
		saveIpRangeList(organization.getIpRangeList(), old.getIpRangeList());
		List<OrganizationName> newOrgNameList = createNameList(organization);
		List<OrganizationName> oldOrgNameList = createNameList(old);
		saveOrganizationNameList(newOrgNameList, oldOrgNameList);
		saveSupplierSourceList(organization.getSupplierSourceList(), old.getSupplierSourceList());
		// TODO: Set foreign keys!
		this.organizationDao.updateOrganization(organization);
//		this.updateOrganization(organization, organizationDao.getOrganizationById(organization.getId()));
		return Boolean.TRUE;
	}
	private void checkNull(Organization organization) {
		if(organization.getAccessList() == null){ throw new NullPointerException("accessList==null"); }
		if(organization.getContactInformation()==null){ throw new NullPointerException("contactInformation==null"); }
		if(organization.getContactPerson()==null){ throw new NullPointerException("contactPerson==null"); }
		if(organization.getDescription()==null){ throw new NullPointerException("description==null"); }
		if(organization.getIpRangeList()==null){ throw new NullPointerException("ipRangeList==null"); }
		if(organization.getLastChanged()==null){ throw new NullPointerException("lastChanged==null"); }
		if(organization.getNameEnglish()==null){ throw new NullPointerException("nameEnglishNormal==null"); }
		if(organization.getNameNorwegian()==null){ throw new NullPointerException("nameNorwegianNormal==null"); }
		if(organization.getNameShortEnglish()==null){ throw new NullPointerException("nameEnglishShort==null"); }
		if(organization.getNameShortNorwegian()==null){ throw new NullPointerException("nameNorwegianShort==null"); }
		// TODO: What to do with no parent?
		if(organization.getParent()==null){ throw new NullPointerException("parent==null"); }
		if(organization.getSupplierSourceList()==null){ throw new NullPointerException("supplierSourceList==null"); }
		if(organization.getType()==null){ throw new NullPointerException("type==null"); }
	}
	private String findName(List<OrganizationName> nameList, LanguageCategory language, OrganizationNameCategory category) {
		// TODO: In the longer term we should use the name list in the UI also!
		for (OrganizationName organizationName : nameList) {
			if(organizationName.getCategory().equals(language) &&
					organizationName.getLanguageCode().equals(language)){
				return organizationName.getName();
			}
		}
		return "";
	}
	private List<OrganizationName> createNameList(Organization organization) {
		List<OrganizationName> list = new ArrayList<OrganizationName>();
		{
			OrganizationName name = new OrganizationName();
			name.setName(organization.getNameEnglish());
			name.setCategory(OrganizationNameCategory.NORMAL);
			name.setLanguageCode(LanguageCategory.en);
			name.setLastChanged(new Date());
			list.add(name);
		}
		{
			OrganizationName name = new OrganizationName();
			name.setName(organization.getNameNorwegian());
			name.setCategory(OrganizationNameCategory.NORMAL);
			name.setLanguageCode(LanguageCategory.no);
			name.setLastChanged(new Date());
			list.add(name);
		}
		{
			OrganizationName name = new OrganizationName();
			name.setName(organization.getNameShortEnglish());
			name.setCategory(OrganizationNameCategory.SHORT);
			name.setLanguageCode(LanguageCategory.en);
			name.setLastChanged(new Date());
			list.add(name);
		}
		{
			OrganizationName name = new OrganizationName();
			name.setName(organization.getNameShortNorwegian());
			name.setCategory(OrganizationNameCategory.SHORT);
			name.setLanguageCode(LanguageCategory.no);
			name.setLastChanged(new Date());
			list.add(name);
		}
		return list;
	}
	private void populateOrganizationNames(Organization organization) {
		List<OrganizationName> nameList = organizationNameDao.getOrganizationNameListByOrganization(organization);
		organization.setNameEnglishNormal(findName(nameList, LanguageCategory.en, OrganizationNameCategory.NORMAL));
		organization.setNameEnglishShort(findName(nameList, LanguageCategory.en, OrganizationNameCategory.SHORT));
		organization.setNameNorwegianNormal(findName(nameList, LanguageCategory.no, OrganizationNameCategory.NORMAL));
		organization.setNameNorwegianShort(findName(nameList, LanguageCategory.no, OrganizationNameCategory.SHORT));
	}
	private void populateOrganizationRest(Organization organization) {
		List<IpRange> ipRangeList = this.ipRangeDao.getIpRangeListByOrganization(organization);
		organization.setIpRangeList(ipRangeList);
		List<Access> accessList = this.accessDao.getAccessListByOrganization(organization);
		organization.setAccessList(accessList);
		// TODO: How to deal with supplierSourceList?
		List<SupplierSource> supplierSourceList = new ArrayList<SupplierSource>();
		organization.setSupplierSourceList(supplierSourceList);
		// TODO: How to deal with those that have no parent?
		// TODO: And infinite loops!
		Organization parent = this.organizationDao.getOrganizationById(organization.getId());
		organization.setParent(parent);
		
		OrganizationType type = this.organizationTypeDao.getOrganizationTypeByKey(organization.getType().getKey());
		organization.setType(type);
		
		// TODO: How to deal with those that have no contact person?
		Person contactPerson = this.personDao.getPersonByOrganization(organization);
		organization.setContactPerson(contactPerson);
		
		ContactInformation contactInformation = this.contactInformationDao.getContactInformationByOrganization(organization);
		organization.setContactInformation(contactInformation);
	}
	private void saveAccessList(List<Access> changedAccessList, List<Access> originalAccessList) {
		// TODO: Rewrite this!
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
	private void saveIpRangeList(List<IpRange> changedIpRangeList, List<IpRange> originalIpRangeList) {
		// TODO: Rewrite this!
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
		// TODO: Rewrite this!
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
	private void saveSupplierSourceList(List<SupplierSource> changedSupplierSourceList, List<SupplierSource> originalSupplierSourceList) {
		// TODO: Rewrite this!
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
