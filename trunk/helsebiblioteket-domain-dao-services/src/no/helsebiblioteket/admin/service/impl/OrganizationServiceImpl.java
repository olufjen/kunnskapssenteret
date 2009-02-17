package no.helsebiblioteket.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import no.helsebiblioteket.admin.ModifiedListHelper;
import no.helsebiblioteket.admin.dao.ContactInformationDao;
import no.helsebiblioteket.admin.dao.IpRangeDao;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.OrganizationListDao;
import no.helsebiblioteket.admin.dao.OrganizationNameDao;
import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressRange;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.IpAddressSingle;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.PositionList;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.category.LanguageCategory;
import no.helsebiblioteket.admin.domain.category.OrganizationNameCategory;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.line.IpAddressLine;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ListResultIpAddressSet;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.factory.ContactInformationFactory;
import no.helsebiblioteket.admin.factory.PersonFactory;
import no.helsebiblioteket.admin.requestresult.FirstPageRequest;
import no.helsebiblioteket.admin.requestresult.MorePageRequest;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.service.OrganizationService;

public class OrganizationServiceImpl implements OrganizationService {
	private static final long serialVersionUID = 1L;
	private OrganizationDao organizationDao;
	private OrganizationListDao organizationListDao;
	private OrganizationTypeDao organizationTypeDao;
	private OrganizationNameDao organizationNameDao;
	private PersonDao personDao;
	private ContactInformationDao contactInformationDao;
	private IpRangeDao ipRangeDao;
	private SupplierSourceDao supplierSourceDao;

	/**
	 * Fetches all OrganizationType in the database. Delegates the task to
	 * OrganizationTypeDao. The variable DUMMY is never used.
	 */
	public ListResultOrganizationType getOrganizationTypeListAll(String DUMMY) {
		List<OrganizationType> all = this.organizationTypeDao.getOrganizationTypeListAll();
		OrganizationType[] list = new OrganizationType[all.size()];
		int i=0;
		for (OrganizationType organizationType : all) {
			list[i++]=organizationType;
		}
		return new ListResultOrganizationType(list);
	}
	/**
	 * Fetches the OrganizationType with the given key.
	 * If none is found EmptyResult is returned.
	 * Delegates the task to OrganizationTypeDao.
	 * OrganizationTypeDao.getOrganizationTypeByKey(..) returns
	 * null if no OrganizationType is found.
	 */
	public SingleResultOrganizationType getOrganizationTypeByKey(OrganizationTypeKey key){
		OrganizationType organizationType = this.organizationTypeDao.getOrganizationTypeByKey(key);
		if(organizationType == null){
			return new EmptyResultOrganizationType();
		} else {
			return new ValueResultOrganizationType(organizationType);
		}
	}
	/**
	 * Finds all the organizations in the database. This method uses a page request
	 * and only fetches the next X objects from the last one fetched. This can be used
	 * for a paged view. Delegates the task to OrganizationListDao.
	 * It only fetches the most important values needed in a list, like names, etc.
	 * These are the values in the OrganizationListItem object.
	 */
	public PageResultOrganizationListItem getOrganizationListAll(PageRequest request) {
		// TODO: Do we need more values in OrganizationListItem?
		// TODO: Should we use Id for request.from?
		List<OrganizationListItem> organizationList = organizationListDao.getOrganizationListPaged(request.skip, request.maxResult);
		PageResultOrganizationListItem result = new PageResultOrganizationListItem();
		result.setResult(translateList(organizationList));
		result.setSkipped(request.skip);
		result.setTotal(organizationList.size());
		return result;
	}
	private OrganizationListItem[] translateList(List<OrganizationListItem> organizationList) {
		OrganizationListItem[]items = new OrganizationListItem[organizationList.size()];
		int i=0;
		for (OrganizationListItem organizationListItem : organizationList) {
			items[i] = organizationListItem;
			i++;
		}
		return items;
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
	public PageResultOrganizationListItem findOrganizationsBySearchString( String searchString, PageRequest request){
		// TODO: Do we need more values in OrganizationListItem?
		// TODO: Should we use Id for request.from?
		// TODO: Search for the search string in all names or do this by locale?
		List<OrganizationListItem> allOrganizations = this.organizationListDao.getOrganizationListPagedSearchString(searchString, request.skip, request.maxResult);
		PageResultOrganizationListItem result = new PageResultOrganizationListItem();
		result.setSkipped( request.skip );
		result.setResult((OrganizationListItem[]) allOrganizations.toArray());
		result.setTotal(allOrganizations.size());
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
	public SingleResultOrganization getOrganizationByListItem(OrganizationListItem organizationListItem) {
		// TODO: Log when some properties are missing in an organization?
		//       Useful to locate errors and to see if what values have been set in import, etc.
		// TODO: Log when organization has no type
		// TODO: Deal with Supp/Member
		MemberOrganization organization = new MemberOrganization();
		organization.setOrganization(organizationDao.getOrganizationById(organizationListItem.getId()));
		if(organization != null){
			// FIXME: Re-insert:
			populateOrganizationNames(organization.getOrganization());
			// TODO: Supplier and member!
//			populateOrganizationRest(organization);
			// FIXME: Re-insert:
			return new ValueResultMemberOrganization(organization);
		} else {
			return new EmptyResultOrganization();
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
	@Override
	public SingleResultOrganization insertOrganization(Organization organization) {
		checkNull(organization);

		Person contactPerson = organization.getContactPerson();
		Person insertedPerson = this.personDao.insertPerson(contactPerson);
		organization.setContactPerson(insertedPerson);
		
		ContactInformation contactInformation = organization.getContactInformation();
		ContactInformation insertedContactInformation = this.contactInformationDao.insertContactInformation(contactInformation);
		organization.setContactInformation(insertedContactInformation);

		OrganizationType type = this.organizationTypeDao.getOrganizationTypeByKey(organization.getType().getKey());
		if(type == null) throw new NullPointerException("Invalid type reference");

		Organization insertedOrganization = this.organizationDao.insertOrganization(organization);

		List<OrganizationName> orgNameList = createNameList(organization);
		for (OrganizationName organizationName : orgNameList) {
			this.organizationNameDao.insertOrganizationName(insertedOrganization, organizationName);
		}
		
		// No parent
//		Integer parentReference = organization.getParent();
//		if(parentReference != null){
//			Organization parent = this.organizationDao.getOrganizationById(parentReference);
//			if(parent == null) throw new NullPointerException("Invalid parent reference");
//		}
		return new ValueResultOrganization(organization);
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
	@Override
	public SingleResultOrganization updateOrganization(Organization organization) {
		// TODO: Write for supplier also:
		checkNull(organization);
		
		
		OrganizationListItem organizationListItem = new OrganizationListItem();
		organizationListItem.setId(organization.getId());
		SingleResultOrganization result = this.getOrganizationByListItem(organizationListItem);
		if(result instanceof EmptyResultOrganization){
			throw new NullPointerException("Tried to update non-existing organization");
		}
		
		// TODO: Also for supplier!
		// FIXME: Re-insert:
		MemberOrganization old = null;//(MemberOrganization)((ValueResultOrganization)result).getValue();
		OrganizationType type = this.organizationTypeDao.getOrganizationTypeByKey(organization.getType().getKey());
		if(type == null) throw new NullPointerException("Invalid type reference");
		this.personDao.updatePerson(organization.getContactPerson());
		this.contactInformationDao.updateContactInformation(organization.getContactInformation());

		// TODO: Re-insert
//		saveIpRangeList(organization.getIpAddressSetList(), old.getIpAddressSetList());
		
		
		
		// FIXME: Re-insert:
//		List<OrganizationName> newOrgNameList = createNameList(organization);
//		List<OrganizationName> oldOrgNameList = createNameList(old);
		// FIXME: Re-insert:
//		saveOrganizationNameList(organization, newOrgNameList, oldOrgNameList);

		// TODO: Write for supplier:
//		List<SupplierSource> newSupplierSourceList = organization.getSupplierSourceList();
//		List<SupplierSource> oldSupplierSourceList = old.getSupplierSourceList();
//		saveSupplierSourceList(newSupplierSourceList, oldSupplierSourceList);
		
		// FIXME: Re-insert:
//		this.organizationDao.updateOrganization(organization);
		return new ValueResultOrganization(organization);
	}
	@Override
	public ListResultIpAddressSet addIpAddresses(Organization organization, IpAddressSingle[] ipAddressSingles) {
		IpAddressSet[] list = new IpAddressSet[ipAddressSingles.length];
		for (IpAddressSingle addressSingle : ipAddressSingles) {
			IpAddressLine line = translateIpAddressSingle(addressSingle);
			line.setOrgUnitId(organization.getId());
			ipRangeDao.insertIpRange(line);
		}
		return new ListResultIpAddressSet(list);
	}
	@Override
	public ListResultIpAddressSet addIpAddressRanges(Organization organization, IpAddressRange[] ipAddressRanges) {
		IpAddressSet[] list = new IpAddressSet[ipAddressRanges.length];
		int i = 0;
		for (IpAddressRange addressRange : ipAddressRanges) {
			IpAddressLine line = translateIpAddressRange(organization, addressRange);
			ipRangeDao.insertIpRange(line);
			list[i] = translateIpAddressLine(line);
			i++;
		}
		return new ListResultIpAddressSet(list);
	}
	private IpAddressLine translateIpAddressRange(Organization organization, IpAddressRange addressRange) {
		IpAddressLine line = new IpAddressLine();
		line.setIpAddressId(addressRange.getIpAddressSet().getIpAddressId());
		line.setIpAddressFrom(addressRange.getIpAddressFrom().getAddress());
		line.setIpAddressTo(addressRange.getIpAddressTo().getAddress());
		line.setLastChanged(addressRange.getIpAddressSet().getLastChanged());
		line.setOrgUnitId(organization.getId());
		return line;
	}
	@Override
	public Boolean deleteIpAddresses(IpAddressSet[] ipAddressSets) {
		for (IpAddressSet ipRange : ipAddressSets) {
			IpAddressLine line = translateIpAddressSet(ipRange);
			ipRangeDao.deleteIpRange(line);
		}
		return true;
	}
	private IpAddressLine translateIpAddressSet(IpAddressSet ipRange) {
		IpAddressLine line = new IpAddressLine();
		line.setIpAddressId(ipRange.getIpAddressId());
		return line;
	}
	/**
	 * Fetches all the organizations that have this IPAddress.
	 */
	public ListResultOrganizationListItem getOrganizationListByIpAddress(IpAddress ipAddress) {
		List<OrganizationListItem> all = this.organizationListDao.getOrganizationListByIpAddress(ipAddress);
		OrganizationListItem[] list = new OrganizationListItem[all.size()];
		int i=0;
		for (OrganizationListItem memberOrganization : all) {
			list[i++]=memberOrganization;
		}
		return new ListResultOrganizationListItem(list);
	}
	/**
	 * TODO: Will be removed!
	 */
	private void setForeignKeysForOrganization(MemberOrganization organization) {
		if (organization != null) {
			if (organization.getIpAddressRangeList() != null) {
//				for (IpAddressSet ipRange : organization.getIpAddressRangeList()) {
//					// TODO: User line object!
////					ipRange.setOrganizationId(organization.getOrgUnitId());
//				}
			}
		}
	}

	/**
	 * Checks all values of the object for null.
	 * @param organization
	 */
	private void checkNull(Organization organization) {
//		if(organization.getAccessList() == null){ throw new NullPointerException("accessList==null"); }
		if(organization.getContactInformation()==null){ throw new NullPointerException("contactInformation==null"); }
		if(organization.getContactPerson()==null){ throw new NullPointerException("contactPerson==null"); }
		if(organization.getDescription()==null){ throw new NullPointerException("description==null"); }
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
		// FIXME: Reinsert this:
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
			if((organizationName.getCategory() == category) &&
					organizationName.getLanguageCode() == language){
				return organizationName;
			}
		}
		OrganizationName created = createName(language, category, "");
		// FIXME: Re-insert this:
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
		OrganizationTypeKey typeKey = organization.getOrganization().getType().getKey();
		// TODO: Set default type and log the incident?
		if(typeKey == null){ throw new NullPointerException("Organization has no type"); }
		OrganizationType type = this.organizationTypeDao.getOrganizationTypeByKey(organization.getOrganization().getType().getKey());
		if(type==null){ throw new NullPointerException("Organization type is not valid"); }
		organization.getOrganization().setType(type);

		if(organization.getOrganization().getContactPerson() == null ||
				organization.getOrganization().getContactPerson().getId() == null){throw new NullPointerException("Organization has no contact person");}
		// FIXME: Re-insert:
		Person contactPerson = null;//this.personDao.getPersonByOrganization(organization);
		if(contactPerson==null){
			// TODO: Create an empty contact person and log the incident?
			contactPerson = PersonFactory.factory.createPerson();
//			throw new NullPointerException("Contact person not found");
		}
		organization.getOrganization().setContactPerson(contactPerson);

		if(organization.getOrganization().getContactInformation() == null ||
				organization.getOrganization().getContactInformation().getId() == null){ throw new NullPointerException("Organization has no contact information");}
		// FIXME: Re-insert:
		ContactInformation contactInformation = null;//this.contactInformationDao.getContactInformationByOrganization(organization);
		if(contactInformation==null){
			// TODO: Create empty contact information and log the incident?
			contactInformation = ContactInformationFactory.factory.createContactInformation();
//			throw new NullPointerException("Contact information not found");
		}
		organization.getOrganization().setContactInformation(contactInformation);
		
		
		List<IpAddressLine> lineList = this.ipRangeDao.getIpRangeListByOrganization(organization);
		List<IpAddressSet> ipRangeList = new ArrayList<IpAddressSet>();
		for (IpAddressLine ipAddressLine : lineList) {
			ipRangeList.add(translateIpAddressLine(ipAddressLine));
		}
			
		// TODO: Re-insert:
//		organization.setIpAddressSetList(ipRangeList);

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
//		ModifiedListHelper<IpAddressSet> listHelper = new ModifiedListHelper<IpAddressSet>();
//		List<IpAddressSet> deleteList = listHelper.getDeleteList(changedIpRangeList, originalIpRangeList);
//		List<IpAddressSet> insertList = listHelper.getInsertList(changedIpRangeList, originalIpRangeList);
//		List<IpAddressSet> updateList = listHelper.getUpdateList(changedIpRangeList, originalIpRangeList);
//		for (IpAddressSet ipRange : deleteList) {
//			ipRangeDao.deleteIpRange(translateIpAddressSet(ipRange));
//		}
//		for (IpAddressSet ipRange : insertList) {
//			ipRangeDao.insertIpRange(translateIpAddressSet(ipRange));
//		}
//		for (IpAddressSet ipRange : updateList) {
//			ipRangeDao.updateIpRange(translateIpAddressSet(ipRange));
//		}
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
			organizationNameDao.deleteOrganizationName(organizationName);
		}
		// FIXME: Re-insert this
//		for (OrganizationName organizationName : insertList) {
//			organizationNameDao.insertOrganizationName(organization, organizationName);
//		}
		for (OrganizationName organizationName : updateList) {
			organizationNameDao.updateOrganizationName(organizationName);
		}
	}
	/**
	 * Adds new sources and deletes old ones
	 * 
	 * @param changedSupplierSourceList
	 * @param originalSupplierSourceList
	 */
	private void saveSupplierSourceList(List<SupplierSource> changedSupplierSourceList, List<SupplierSource> originalSupplierSourceList) {
//		ModifiedListHelper<SupplierSource> listHelper = new ModifiedListHelper<SupplierSource>();
//		List<SupplierSource> deleteList = listHelper.getDeleteList(changedSupplierSourceList, originalSupplierSourceList);
//		List<SupplierSource> insertList = listHelper.getInsertList(changedSupplierSourceList, originalSupplierSourceList);
//		List<SupplierSource> updateList = listHelper.getUpdateList(changedSupplierSourceList, originalSupplierSourceList);
//		for (SupplierSource supplierSource : deleteList) {
//			supplierSourceDao.deleteSupplierSource(supplierSource);
//			// TODO: Rewrite:
////			resourceDao.deleteResource(supplierSource);
//		}
//		for (SupplierSource supplierSource : insertList) {
//			supplierSourceDao.insertSupplierSource(supplierSource);
//			// TODO: Rewrite:
////			resourceDao.insertResource(supplierSource);
//		}
//		for (SupplierSource supplierSource : updateList) {
//			supplierSourceDao.updateSupplierSource(supplierSource);
//			// TODO: Rewrite:
////			resourceDao.updateResource(supplierSource);
//		}
	}
	/**
	 * Translates IpAddressSet(GUI) into IpAddressLine (DB)
	 * 
	 * @param ipAddressSet
	 * @return
	 */
	private IpAddressLine translateIpAddressSingle(IpAddressSingle ipAddressSingle){
		IpAddressLine ipAddressLine = new IpAddressLine();
		ipAddressLine.setIpAddressId(ipAddressLine.getIpAddressId());
		ipAddressLine.setOrgUnitId(ipAddressLine.getOrgUnitId());
		ipAddressLine.setLastChanged(ipAddressLine.getLastChanged());
//		if(ipAddressSet instanceof IpAddressRange){
//			IpAddressRange ipAddressRange = (IpAddressRange) ipAddressSet;
//			ipAddressLine.setIpAddressFrom(ipAddressRange.getIpAddressFrom().getAddress());
//			ipAddressLine.setIpAddressTo(ipAddressRange.getIpAddressTo().getAddress());
//		} else {
//		IpAddressSingle ipAddressSingle = (IpAddressSingle) ipAddressSet;
		ipAddressLine.setIpAddressFrom(ipAddressSingle.getIpAddressSingle().getAddress());
//		}
		return ipAddressLine;
	}
	/**
	 * Translates IpAddressLine (DB) into IpAddressSet(GUI)
	 * 
	 * @param ipAddressSet
	 * @return
	 */
	private IpAddressSet translateIpAddressLine(IpAddressLine ipAddressLine) {
		IpAddressSet ipAddressSet = new IpAddressSet();
		if(ipAddressLine.getIpAddressTo()==null){
			IpAddressSingle ipAddressSingle = new IpAddressSingle();
			ipAddressSingle.setIpAddressSingle(new IpAddress(ipAddressLine.getIpAddressFrom()));
			// FIXME: Handle this
//			ipAddressSet = ipAddressSingle;
		} else {
			IpAddressRange ipAddressRange = new IpAddressRange();
			ipAddressRange.setIpAddressFrom(new IpAddress(ipAddressLine.getIpAddressFrom()));
			ipAddressRange.setIpAddressTo(new IpAddress(ipAddressLine.getIpAddressTo()));
			// FIXME: Handle this
//			ipAddressSet = ipAddressRange;
		}

		ipAddressSet.setIpAddressId(ipAddressLine.getIpAddressId());
		ipAddressSet.setLastChanged(ipAddressLine.getLastChanged());
		return ipAddressSet;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void saveAccessList(List<Access> changedAccessList, List<Access> originalAccessList) {
		// TODO: Rewrite this!
//		ModifiedListHelper<Access> listHelper = new ModifiedListHelper<Access>();
//		List<Access> deleteList = listHelper.getDeleteList(changedAccessList, originalAccessList);
//		List<Access> insertAndUpdateList = null;//listHelper.getInsertAndUpdateList(changedAccessList, originalAccessList);
//		if (deleteList != null) {
//			for (Access access : deleteList) {
//				// TODO: Watch this!
////				accessDao.deleteAccess(access);
//			}
//		}
//		if (insertAndUpdateList != null) {
//			for (Access access : insertAndUpdateList) {
//				saveAccess(access);
//			}
//		}
	}

	private void saveOrganization(Organization organization) {
		// TODO: Remove!
//		saveOrganization(organization, organizationDao.getOrganizationById(organization.getId()));
	}
	private void saveOrganization(Organization changedOrganization, Organization originalOrganization) {
		// TODO: Remove!
//		if (changedOrganization.getOrgUnitId() != null) {
			// TODO: Handle optimistic locking.
			//if (originalOrganization == null || (originalOrganization != null && !originalOrganization.getLastChanged().equals(changedOrganization.getLastChanged()))) {
			//	throw new OptimisticLockingFailureException("Organization has been changed by another caller since last time it was loaded from datastore");
			//}
			// TODO: Not use.
//			updateOrganization(changedOrganization, originalOrganization);
//		} else {
//			insertOrganization(changedOrganization);
//		}
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
		// FIXME: Re-insert:
//		organizationDao.updateOrganization(changedOrganization);
		setForeignKeysForOrganization(changedOrganization);
//		savePerson(changedOrganization.getContactPerson(), changedOrganization.getContactPerson());
//		saveContactInformation(changedOrganization.getContactInformation(), originalOrganization.getContactInformation());
		
		//TODO: Re-insert
//		saveIpRangeList(changedOrganization.getIpAddressSetList(), originalOrganization.getIpAddressSetList());
		
		// TODO: Rewrite saveOrganizationNameList
//		saveOrganizationNameList(changedOrganization.getNameList(), originalOrganization.getNameList());

//		saveAccessList(changedOrganization.getAccessList(), originalOrganization.getAccessList());
//		saveSupplierSourceList(changedOrganization.getSupplierSourceList(), originalOrganization.getSupplierSourceList());
	}
	
	private void savePerson(Person person, Person originalPerson) {
//		saveContactInformation(person.getContactInformation(), originalPerson.getContactInformation());
		
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
		
		// FIXME: Re-insert this!
//		organizationDao.insertOrganization(organization);
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
		if (supplierSource.getId() == null) {
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
	public void setContactInformationDao(ContactInformationDao contactInformationDao) {
		this.contactInformationDao = contactInformationDao;
	}
	public void setIpRangeDao(IpRangeDao ipRangeDao) {
		this.ipRangeDao = ipRangeDao;
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
