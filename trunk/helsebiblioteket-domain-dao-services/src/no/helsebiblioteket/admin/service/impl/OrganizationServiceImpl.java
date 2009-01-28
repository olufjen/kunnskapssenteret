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
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.PositionList;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.category.LanguageCategory;
import no.helsebiblioteket.admin.domain.category.OrganizationNameCategory;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.factory.ContactInformationFactory;
import no.helsebiblioteket.admin.factory.PersonFactory;
import no.helsebiblioteket.admin.requestresult.EmptyResult;
import no.helsebiblioteket.admin.requestresult.FirstPageRequest;
import no.helsebiblioteket.admin.requestresult.ListResult;
import no.helsebiblioteket.admin.requestresult.MorePageRequest;
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
	private PersonDao personDao;
	private AccessDao accessDao;
	private ContactInformationDao contactInformationDao;
	private IpRangeDao ipRangeDao;
	private ResourceDao resourceDao;
	private SupplierSourceDao supplierSourceDao;

	/**
	 * Fetches all OrganizationType in the database. Delegates the task to
	 * OrganizationTypeDao. The variable DUMMY is never used.
	 */
	public ListResult<OrganizationType> getOrganizationTypeListAll(String DUMMY) {
		List<OrganizationType> all = this.organizationTypeDao.getOrganizationTypeListAll();
		OrganizationType[] list = new OrganizationType[all.size()];
		int i=0;
		for (OrganizationType organizationType : all) {
			list[i++]=organizationType;
		}
		return new ListResult<OrganizationType>(list);
	}
	/**
	 * Fetches the OrganizationType with the given key.
	 * If none is found EmptyResult is returned.
	 * Delegates the task to OrganizationTypeDao.
	 * OrganizationTypeDao.getOrganizationTypeByKey(..) returns
	 * null if no OrganizationType is found.
	 */
	public SingleResult<OrganizationType> getOrganizationTypeByKey(OrganizationTypeKey key){
		OrganizationType organizationType = this.organizationTypeDao.getOrganizationTypeByKey(key);
		if(organizationType == null){
			return new EmptyResult<OrganizationType>();
		} else {
			return new ValueResult<OrganizationType>(organizationType);
		}
	}
	/**
	 * Finds all the organizations in the database. This method uses a page request
	 * and only fetches the next X objects from the last one fetched. This can be used
	 * for a paged view. Delegates the task to OrganizationListDao.
	 * It only fetches the most important values needed in a list, like names, etc.
	 * These are the values in the OrganizationListItem object.
	 */
	public PageResult<OrganizationListItem> getOrganizationListAll(PageRequest<OrganizationListItem> request) {
		// TODO: Do we need more values in OrganizationListItem?
		// TODO: Should we use Id for request.from?
		int from;
		if(request instanceof FirstPageRequest){
			from = 0;
		} else {
			from = ((MorePageRequest<OrganizationListItem>)request).last + 1;
		}
		List<OrganizationListItem> organizationList = organizationListDao.getOrganizationListPaged(from, request.maxResult);
		PageResult<OrganizationListItem> result = new PageResult<OrganizationListItem>();
		result.result = organizationList;
		result.from = from;
		result.total = organizationList.size();
		return result;
	}
	/**
	 * Finds all the organizations in the database. This method uses a page request
	 * and only fetches the next X objects from the last one fetched. This can be used
	 * for a paged view. Delegates the task of finding the organizations to
	 * OrganizationListDao. The search string is used to match against the name in
	 * any language. This may result in something the users perceive as 'strange
	 * behavior': The names of organizations in the list do not contain the search
	 * string.
	 * It only fetches the most important values needed in a list, like names, etc.
	 * These are the values in the OrganizationListItem object.
	 */
	public PageResult<OrganizationListItem> findOrganizationsBySearchString( String searchString, PageRequest<OrganizationListItem> request){
		// TODO: Do we need more values in OrganizationListItem?
		// TODO: Should we use Id for request.from?
		// TODO: Search for the search string in all names or do this by locale?
		int from;
		if(request instanceof FirstPageRequest){
			from = 0;
		} else {
			from = ((MorePageRequest<OrganizationListItem>)request).last + 1;
		}
		List<OrganizationListItem> allOrganizations = this.organizationListDao.getOrganizationListPagedSearchString(searchString, from, request.maxResult);
		PageResult<OrganizationListItem> result = new PageResult<OrganizationListItem>();
		result.from = from;
		result.result = allOrganizations;
		result.total = allOrganizations.size();
		return result;
	}
	/**
	 * Finds an Organization from an OrganizationListItem. This is used to
	 * load all the data for an organization when a detailed view is needed
	 * and for editing the organization. All the properties of Organization
	 * and all the objects it refers to are initialized, recursively. There
	 * are _no_ null pointers in the result.
	 * OrganizationDao fetches the basic organization object. The lists
	 * and the names and missing objects are initialized by the populate
	 * methods. See comments there.
	 */
	public SingleResult<Organization> getOrganizationByListItem(OrganizationListItem organizationListItem) {
		// TODO: Log when some properties are missing in an organization?
		//       Useful to locate errors and to see if what values have been set in import, etc.
		Organization organization = organizationDao.getOrganizationByListItem(organizationListItem);
		if(organization != null){
			populateOrganizationNames(organization);
			// TODO: Supplier and member!
//			populateOrganizationRest(organization);
			return new ValueResult<Organization>(organization);
		} else {
			return new EmptyResult<Organization>();
		}
	}
	/**
	 * Inserts a new organization. Checks all values for null first.
	 * Saves one and one of the sub-objects of organization and
	 * the lists. After each sub object is saved, the organization is
	 * updated with the saved objects that have the new foreign keys.
	 * OrganizationType is not inserted. Error if not exists.
	 * Error is the organization has parent reference and the
	 * parent does not exist.
	 * Do not use this for suppliers. Throws error.
	 * 
	 */
	public Boolean insertOrganization(Organization organization2) {
		
		// TODO: Write for supplier also:
		MemberOrganization organization = (MemberOrganization)organization2;
		checkNull(organization);
		
		// No parent
//		Integer parentReference = organization.getParent();
//		if(parentReference != null){
//			Organization parent = this.organizationDao.getOrganizationById(parentReference);
//			if(parent == null) throw new NullPointerException("Invalid parent reference");
//		}
		
		OrganizationType type = this.organizationTypeDao.getOrganizationTypeByKey(organization.getType().getKey());
		if(type == null) throw new NullPointerException("Invalid type reference");
		
		Person contactPerson = organization.getContactPerson();
		Person insertedPerson = this.personDao.insertPerson(contactPerson);
		organization.setContactPerson(insertedPerson);
		
		ContactInformation contactInformation = organization.getContactInformation();
		ContactInformation insertedContactInformation = this.contactInformationDao.insertContactInformation(contactInformation);
		organization.setContactInformation(insertedContactInformation);
		
		for (IpAddressSet ipRange : organization.getIpAddressSetList()) {
			// TODO: Remove this. Better solution is on its way!
			this.setForeignKeysForOrganization(organization);
			this.ipRangeDao.insertIpRange(ipRange);
		}
		List<OrganizationName> orgNameList = createNameList(organization);
		for (OrganizationName organizationName : orgNameList) {
			this.organizationNameDao.insertOrganizationName(organization, organizationName);
		}
		// TODO: Only for supplier!
//		for (SupplierSource supplierSource : organization.getSupplierSourceList()) {
//			this.supplierSourceDao.insertSupplierSource(supplierSource);
//		}
		this.organizationDao.insertOrganization(organization);
		return Boolean.TRUE;
	}
	/**
	 * Updates an existing organization. If the organization does not
	 * exist an exception is thrown. It first checks for null on all
	 * values. The contact person and contact information must exist
	 * and it should have been created at load time. Otherwise: Huge
	 * error! :-). Changes to the ip list and name list are saved by
	 * methods saveIpRangeList and saveOrganizationNameList. See them.
	 * 
	 * TODO: The lists should not be updated here. There should be
	 * addName and addRange methods at the service level.
	 */
	public Boolean updateOrganization(Organization organization2) {
		// TODO: Write for supplier also:
		MemberOrganization organization = (MemberOrganization)organization2;
		checkNull(organization);
		
		
		OrganizationListItem organizationListItem = new OrganizationListItem();
		organizationListItem.setId(organization.getOrgUnitId());
		SingleResult<Organization> result = this.getOrganizationByListItem(organizationListItem);
		if(result instanceof EmptyResult){
			throw new NullPointerException("Tried to update non-existing organization");
		}
		
		// TODO: Also for supplier!
		MemberOrganization old = (MemberOrganization)((ValueResult<Organization>)result).getValue();
		OrganizationType type = this.organizationTypeDao.getOrganizationTypeByKey(organization.getType().getKey());
		if(type == null) throw new NullPointerException("Invalid type reference");
		this.personDao.updatePerson(organization.getContactPerson());
		this.contactInformationDao.updateContactInformation(organization.getContactInformation());

		saveIpRangeList(organization.getIpAddressSetList(), old.getIpAddressSetList());
		List<OrganizationName> newOrgNameList = createNameList(organization);
		List<OrganizationName> oldOrgNameList = createNameList(old);
		saveOrganizationNameList(organization, newOrgNameList, oldOrgNameList);

		// TODO: Write for supplier:
//		List<SupplierSource> newSupplierSourceList = organization.getSupplierSourceList();
//		List<SupplierSource> oldSupplierSourceList = old.getSupplierSourceList();
//		saveSupplierSourceList(newSupplierSourceList, oldSupplierSourceList);
		
		this.organizationDao.updateOrganization(organization);
		return Boolean.TRUE;
	}
	/**
	 * TODO: Will be removed!
	 */
	private void setForeignKeysForOrganization(MemberOrganization organization) {
		if (organization != null) {
			if (organization.getIpAddressSetList() != null) {
				for (IpAddressSet ipRange : organization.getIpAddressSetList()) {
					// TODO: User line object!
//					ipRange.setOrganizationId(organization.getOrgUnitId());
				}
			}
		}
	}

	/**
	 * Checks all values of the object for null.
	 * @param organization
	 */
	private void checkNull(MemberOrganization organization) {
//		if(organization.getAccessList() == null){ throw new NullPointerException("accessList==null"); }
		if(organization.getContactInformation()==null){ throw new NullPointerException("contactInformation==null"); }
		if(organization.getContactPerson()==null){ throw new NullPointerException("contactPerson==null"); }
		if(organization.getDescription()==null){ throw new NullPointerException("description==null"); }
		if(organization.getIpAddressSetList()==null){ throw new NullPointerException("ipRangeList==null"); }
		if(organization.getLastChanged()==null){ throw new NullPointerException("lastChanged==null"); }
		if(organization.getNameEnglish()==null){ throw new NullPointerException("nameEnglishNormal==null"); }
		if(organization.getNameNorwegian()==null){ throw new NullPointerException("nameNorwegianNormal==null"); }
		if(organization.getNameShortEnglish()==null){ throw new NullPointerException("nameEnglishShort==null"); }
		if(organization.getNameShortNorwegian()==null){ throw new NullPointerException("nameNorwegianShort==null"); }
//		if(organization.getParent()==null){ throw new NullPointerException("parent==null"); }
//		if(organization.getSupplierSourceList()==null){ throw new NullPointerException("supplierSourceList==null"); }
		if(organization.getType()==null){ throw new NullPointerException("type==null"); }
	}
	/**
	 * Creates the list with the four language and categories of names.
	 * EN-SHORT, EN-NORMAL, NO-SHORT, NO-NORMAL.
	 * 
	 * @param organization
	 * @return
	 */
	private List<OrganizationName> createNameList(Organization organization) {
		List<OrganizationName> list = new ArrayList<OrganizationName>();
		list.add(createName(LanguageCategory.en, OrganizationNameCategory.NORMAL, organization.getNameEnglish()));
		list.add(createName(LanguageCategory.en, OrganizationNameCategory.SHORT, organization.getNameShortEnglish()));
		list.add(createName(LanguageCategory.no, OrganizationNameCategory.NORMAL, organization.getNameNorwegian()));
		list.add(createName(LanguageCategory.no, OrganizationNameCategory.SHORT, organization.getNameShortNorwegian()));
		return list;
	}
	/**
	 * Simply creates a OrganizationName object. 
	 * 
	 * @param language
	 * @param category
	 * @param name
	 * @return
	 */
	private OrganizationName createName(LanguageCategory language, OrganizationNameCategory category, String name) {
		OrganizationName organizationName = new OrganizationName();
		organizationName.setCategory(category);
		organizationName.setLanguageCode(language);
		organizationName.setLastChanged(new Date());
		organizationName.setName(name);
//		organizationName.setOrganizationId(organizationId);
		return organizationName;
	}
	/**
	 * Loads organization names from the database. If they do not exist there,
	 * findCreateName will insert them into the database.
	 * 
	 * @param organization
	 */
	private void populateOrganizationNames(Organization organization) {
		List<OrganizationName> nameList = organizationNameDao.getOrganizationNameListByOrganization(organization);
		organization.setNameEnglish(findCreateName(organization, nameList, LanguageCategory.en, OrganizationNameCategory.NORMAL).getName());
		organization.setNameShortEnglish(findCreateName(organization, nameList, LanguageCategory.en, OrganizationNameCategory.SHORT).getName());
		organization.setNameNorwegian(findCreateName(organization, nameList, LanguageCategory.no, OrganizationNameCategory.NORMAL).getName());
		organization.setNameShortNorwegian(findCreateName(organization, nameList, LanguageCategory.no, OrganizationNameCategory.SHORT).getName());
	}
	/**
	 * This checks if a name for a certain language and category is
	 * in the list. If it is not, it is inserted. It is also inserted
	 * into the database.
	 * 
	 * TODO: In the longer term we should use the name list in the UI also.
	 * 
	 * @param organization
	 * @param nameList
	 * @param language
	 * @param category
	 * @return
	 */
	private OrganizationName findCreateName(Organization organization, List<OrganizationName> nameList, LanguageCategory language, OrganizationNameCategory category) {
		for (OrganizationName organizationName : nameList) {
			if(organizationName.getCategory().equals(language) &&
					organizationName.getLanguageCode().equals(language)){
				return organizationName;
			}
		}
		OrganizationName created = createName(language, category, "");
		this.organizationNameDao.insertOrganizationName(organization, created);
		return created;
	}
	/**
	 * This method loads the type, contact person, contact information
	 * and ip-range list from the database and sets the properties
	 * on the object. If the values (other than type) do not exist in
	 * the database, they are inserted. If the type is missing or is
	 * invalid an exception it thrown.
	 * 
	 * TODO: Also log when values are missing?
	 * 
	 * @param organization
	 */
	private void populateOrganizationRest(MemberOrganization organization) {
		OrganizationTypeKey typeKey = organization.getType().getKey();
		// TODO: Set default type and log the incident?
		if(typeKey == null){ throw new NullPointerException("Organization has no type"); }
		OrganizationType type = this.organizationTypeDao.getOrganizationTypeByKey(organization.getType().getKey());
		if(type==null){ throw new NullPointerException("Organization type is not valid"); }
		organization.setType(type);

		if(organization.getContactPerson() == null ||
				organization.getContactPerson().getPersonId() == null){throw new NullPointerException("Organization has no contact person");}
		Person contactPerson = this.personDao.getPersonByOrganization(organization);
		if(contactPerson==null){
			// TODO: Create an empty contact person and log the incident?
			contactPerson = PersonFactory.factory.createPerson();
//			throw new NullPointerException("Contact person not found");
		}
		organization.setContactPerson(contactPerson);

		if(organization.getContactInformation() == null ||
				organization.getContactInformation().getContactInformationId() == null){ throw new NullPointerException("Organization has no contact information");}
		ContactInformation contactInformation = this.contactInformationDao.getContactInformationByOrganization(organization);
		if(contactInformation==null){
			// TODO: Create empty contact information and log the incident?
			contactInformation = ContactInformationFactory.factory.createContactInformation();
//			throw new NullPointerException("Contact information not found");
		}
		organization.setContactInformation(contactInformation);

		List<IpAddressSet> ipRangeList = this.ipRangeDao.getIpRangeListByOrganization(organization);
		organization.setIpAddressSetList(ipRangeList);

		// TODO: Write for supplier:
//		List<SupplierSource> supplierSourceList = this.supplierSourceDao.getSupplierSourceListByOrganization(organization);
//		organization.setSupplierSourceList(supplierSourceList);
	}
	/**
	 * Adds new ranges and deletes old ones.
	 * 
	 * @param changedIpRangeList
	 * @param originalIpRangeList
	 */
	private void saveIpRangeList(List<IpAddressSet> changedIpRangeList, List<IpAddressSet> originalIpRangeList) {
		// TODO: Rewrite this!
		ModifiedListHelper<IpAddressSet> listHelper = new ModifiedListHelper<IpAddressSet>();
		List<IpAddressSet> deleteList = listHelper.getDeleteList(changedIpRangeList, originalIpRangeList);
		List<IpAddressSet> insertList = listHelper.getInsertList(changedIpRangeList, originalIpRangeList);
		List<IpAddressSet> updateList = listHelper.getUpdateList(changedIpRangeList, originalIpRangeList);
		for (IpAddressSet ipRange : deleteList) {
			ipRangeDao.deleteIpRange(ipRange);
		}
		for (IpAddressSet ipRange : insertList) {
			ipRangeDao.insertIpRange(ipRange);
		}
		for (IpAddressSet ipRange : updateList) {
			ipRangeDao.updateIpRange(ipRange);			
		}
	}
	/**
	 * Adds new names and deletes old ones.
	 * 
	 * @param organization
	 * @param changedOrganizationNameList
	 * @param originalOrganizationNameList
	 */
	private void saveOrganizationNameList(Organization organization, List<OrganizationName> changedOrganizationNameList, List<OrganizationName> originalOrganizationNameList) {
		ModifiedListHelper<OrganizationName> listHelper = new ModifiedListHelper<OrganizationName>();
		List<OrganizationName> deleteList = listHelper.getDeleteList(changedOrganizationNameList, originalOrganizationNameList);;
		List<OrganizationName> insertList = listHelper.getInsertList(changedOrganizationNameList, originalOrganizationNameList);
		List<OrganizationName> updateList = listHelper.getUpdateList(changedOrganizationNameList, originalOrganizationNameList);
		for (OrganizationName organizationName : deleteList) {
			organizationNameDao.deleteOrganizationName(organization, organizationName);
		}
		for (OrganizationName organizationName : insertList) {
			organizationNameDao.insertOrganizationName(organization, organizationName);
		}
		for (OrganizationName organizationName : updateList) {
			organizationNameDao.updateOrganizationName(organization, organizationName);
		}
	}
	/**
	 * Adds new sources and deletes old ones
	 * 
	 * @param changedSupplierSourceList
	 * @param originalSupplierSourceList
	 */
	private void saveSupplierSourceList(List<SupplierSource> changedSupplierSourceList, List<SupplierSource> originalSupplierSourceList) {
		ModifiedListHelper<SupplierSource> listHelper = new ModifiedListHelper<SupplierSource>();
		List<SupplierSource> deleteList = listHelper.getDeleteList(changedSupplierSourceList, originalSupplierSourceList);
		List<SupplierSource> insertList = listHelper.getInsertList(changedSupplierSourceList, originalSupplierSourceList);
		List<SupplierSource> updateList = listHelper.getUpdateList(changedSupplierSourceList, originalSupplierSourceList);
		for (SupplierSource supplierSource : deleteList) {
			supplierSourceDao.deleteSupplierSource(supplierSource);
			// TODO: Rewrite:
//			resourceDao.deleteResource(supplierSource);
		}
		for (SupplierSource supplierSource : insertList) {
			supplierSourceDao.insertSupplierSource(supplierSource);
			// TODO: Rewrite:
//			resourceDao.insertResource(supplierSource);
		}
		for (SupplierSource supplierSource : updateList) {
			supplierSourceDao.updateSupplierSource(supplierSource);
			// TODO: Rewrite:
//			resourceDao.updateResource(supplierSource);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void saveAccessList(List<Access> changedAccessList, List<Access> originalAccessList) {
		// TODO: Rewrite this!
		ModifiedListHelper<Access> listHelper = new ModifiedListHelper<Access>();
		List<Access> deleteList = listHelper.getDeleteList(changedAccessList, originalAccessList);
		List<Access> insertAndUpdateList = null;//listHelper.getInsertAndUpdateList(changedAccessList, originalAccessList);
		if (deleteList != null) {
			for (Access access : deleteList) {
				// TODO: Watch this!
//				accessDao.deleteAccess(access);
			}
		}
		if (insertAndUpdateList != null) {
			for (Access access : insertAndUpdateList) {
				saveAccess(access);
			}
		}
	}

	private void saveOrganization(Organization organization) {
		// TODO: Remove!
//		saveOrganization(organization, organizationDao.getOrganizationById(organization.getId()));
	}
	private void saveOrganization(Organization changedOrganization, Organization originalOrganization) {
		// TODO: Remove!
		if (changedOrganization.getOrgUnitId() != null) {
			// TODO: Handle optimistic locking.
			//if (originalOrganization == null || (originalOrganization != null && !originalOrganization.getLastChanged().equals(changedOrganization.getLastChanged()))) {
			//	throw new OptimisticLockingFailureException("Organization has been changed by another caller since last time it was loaded from datastore");
			//}
			// TODO: Not use.
//			updateOrganization(changedOrganization, originalOrganization);
		} else {
//			insertOrganization(changedOrganization);
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
	
	private void updateOrganization(MemberOrganization changedOrganization, MemberOrganization originalOrganization) {		
		// TODO: Rewrite this!
		organizationDao.updateOrganization(changedOrganization);
		setForeignKeysForOrganization(changedOrganization);
//		savePerson(changedOrganization.getContactPerson(), changedOrganization.getContactPerson());
//		saveContactInformation(changedOrganization.getContactInformation(), originalOrganization.getContactInformation());
		saveIpRangeList(changedOrganization.getIpAddressSetList(), originalOrganization.getIpAddressSetList());
		
		// TODO: Rewrite saveOrganizationNameList
//		saveOrganizationNameList(changedOrganization.getNameList(), originalOrganization.getNameList());

//		saveAccessList(changedOrganization.getAccessList(), originalOrganization.getAccessList());
//		saveSupplierSourceList(changedOrganization.getSupplierSourceList(), originalOrganization.getSupplierSourceList());
	}
	
	private void savePerson(Person person, Person originalPerson) {
//		saveContactInformation(person.getContactInformation(), originalPerson.getContactInformation());
		
		if (person.getPersonId() != null) {
			personDao.updatePerson(person);
		} else {
			personDao.insertPerson(person);
		}
	}
	
	private void saveContactInformation(ContactInformation changedContactInformation, ContactInformation originalContactInformation) {
		if (originalContactInformation != null && changedContactInformation == null) {
			contactInformationDao.deleteContactInformation(originalContactInformation);
		} else {
			if (changedContactInformation.getContactInformationId() == null) {
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
//		setForeignKeysForOrganization(organization);
		
		// FIXME: Insert name list!
//		if (organization.getNameList() != null) {
//			for (OrganizationName organizationName : organization.getNameList()) {		
//				organizationDao.insertOrganizationName(organizationName);
//			}
//		}
//		if (organization.getIpAddressSetList() != null) {
//			for (IpAddressSet ipRange : organization.getIpAddressSetList()) {
//				ipRangeDao.insertIpRange(ipRange);
//			}
//		}
	}
	
	
	
	private void saveSupplierSource(SupplierSource supplierSource) {
		if (supplierSource.getSupplierSourceId() == null) {
			supplierSourceDao.insertSupplierSource(supplierSource);
//			resourceDao.insertResource(supplierSource);
		} else {
			supplierSourceDao.updateSupplierSource(supplierSource);
//			resourceDao.updateResource(supplierSource);
		}
	}
	
	
	// helper methods
	
	
	
	
	
	
	



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
	public OrganizationDao getOrganizationDao() {
		return organizationDao;
	}
	public void setOrganizationListDao(OrganizationListDao organizationListDao) {
		this.organizationListDao = organizationListDao;
	}
	public void setOrganizationNameDao(OrganizationNameDao organizationNameDao) {
		this.organizationNameDao = organizationNameDao;
	}
}
