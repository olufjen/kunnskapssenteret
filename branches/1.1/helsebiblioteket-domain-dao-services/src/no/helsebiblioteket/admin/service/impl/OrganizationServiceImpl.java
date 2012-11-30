package no.helsebiblioteket.admin.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import no.helsebiblioteket.admin.dao.AccessDao;
import no.helsebiblioteket.admin.dao.ContactInformationDao;
import no.helsebiblioteket.admin.dao.IpRangeDao;
import no.helsebiblioteket.admin.dao.OrganizationDao;
import no.helsebiblioteket.admin.dao.OrganizationListDao;
import no.helsebiblioteket.admin.dao.OrganizationNameDao;
import no.helsebiblioteket.admin.dao.OrganizationTypeDao;
import no.helsebiblioteket.admin.dao.PersonDao;
import no.helsebiblioteket.admin.dao.PositionDao;
import no.helsebiblioteket.admin.dao.ProfileDao;
import no.helsebiblioteket.admin.dao.ProxyExportDao;
import no.helsebiblioteket.admin.dao.ResourceDao;
import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.dao.UserDao;
import no.helsebiblioteket.admin.dao.keys.ResourceAccessForeignKeys;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressRange;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.IpAddressSingle;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.OrganizationUser;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.category.LanguageCategory;
import no.helsebiblioteket.admin.domain.category.OrganizationNameCategory;
import no.helsebiblioteket.admin.domain.export.ProxyResult;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.line.IpAddressLine;
import no.helsebiblioteket.admin.domain.line.ProxyHitLine;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.parameter.ProxyExportParameter;
import no.helsebiblioteket.admin.domain.parameter.ProxyHitParameter;
import no.helsebiblioteket.admin.domain.parameter.ProxyHitParameterList;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.EmptyResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ListResultIpAddressSet;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.ListResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ListResultProxyResult;
import no.helsebiblioteket.admin.domain.requestresult.ListResultSupplierSourceResource;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSupplierOrganization;
import no.helsebiblioteket.admin.factory.ContactInformationFactory;
import no.helsebiblioteket.admin.factory.PersonFactory;
import no.helsebiblioteket.admin.factory.ProfileFactory;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.validator.IpAddressValidator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OrganizationServiceImpl implements OrganizationService {
	private final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private OrganizationDao organizationDao;
	private OrganizationListDao organizationListDao;
	private OrganizationTypeDao organizationTypeDao;
	private OrganizationNameDao organizationNameDao;
	private PersonDao personDao;
	private ProfileDao profileDao;
	private ContactInformationDao contactInformationDao;
	private IpRangeDao ipRangeDao;
	private ResourceDao resourceDao;
//	private ResourceTypeDao resourceTypeDao;
	private PositionDao positionDao;
	private SupplierSourceDao supplierSourceDao;
	private AccessDao accessDao;
	private ProxyExportDao proxyExportDao;
	private UserDao userDao;
	
	/**
	 * Fetches all OrganizationType in the database. Delegates the task to
	 * OrganizationTypeDao. The variable DUMMY is never used.
	 */
	@Override
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
	@Override
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
	@Override
	public PageResultOrganizationListItem getOrganizationListAll(PageRequest request) {
		return this.getOrganizationListBySearchString(request, "");
	}
	/**
	 * Finds all the member organizations in the database. This method uses a page request
	 * and only fetches the next X objects from the last one fetched. This can be used
	 * for a paged view. Delegates the task of finding the organizations to
	 * OrganizationListDao.
	 * It only fetches the most important values needed in a list, like names, etc.
	 * These are the values in the OrganizationListItem object.
	 */
	@Override
	public PageResultOrganizationListItem getMemberOrganizationListAll(PageRequest request) {
		OrganizationTypeKey[] types = new OrganizationTypeKey[7];
		
		// TODO: Replace by this: Fetch all, run through and remove content_supplier
		types[0] = this.organizationTypeDao.getOrganizationTypeByKey(OrganizationTypeKey._health_enterprise).getKey();
		types[1] = this.organizationTypeDao.getOrganizationTypeByKey(OrganizationTypeKey._government).getKey();
		types[2] = this.organizationTypeDao.getOrganizationTypeByKey(OrganizationTypeKey._teaching).getKey();
		types[3] = this.organizationTypeDao.getOrganizationTypeByKey(OrganizationTypeKey._other).getKey();
		types[4] = this.organizationTypeDao.getOrganizationTypeByKey(OrganizationTypeKey._helsebiblioteket).getKey();
		types[5] = this.organizationTypeDao.getOrganizationTypeByKey(OrganizationTypeKey._primary_care).getKey();
		types[6] = this.organizationTypeDao.getOrganizationTypeByKey(OrganizationTypeKey._university).getKey();
		
		return getOrganizationListByTypes(request, types);
	}
	/**
	 * Finds all the supplier organizations in the database. This method uses a page request
	 * and only fetches the next X objects from the last one fetched. This can be used
	 * for a paged view. Delegates the task of finding the organizations to
	 * OrganizationListDao.
	 * It only fetches the most important values needed in a list, like names, etc.
	 * These are the values in the OrganizationListItem object.
	 */
	@Override
	public PageResultOrganizationListItem getSupplierOrganizationListAll(PageRequest request) {
		OrganizationTypeKey[] types = new OrganizationTypeKey[1];
		types[0] = this.organizationTypeDao.getOrganizationTypeByKey(OrganizationTypeKey._content_supplier).getKey();
		return getOrganizationListByTypes(request, types);
	}
	private PageResultOrganizationListItem getOrganizationListByTypes(PageRequest request, OrganizationTypeKey[] typesArray) {
		if(request.getMaxResult() > 200) {
			throw new NullPointerException("Max result must be 200 or less.");
		}

		List<OrganizationTypeKey> types = new ArrayList<OrganizationTypeKey>();
		for(OrganizationTypeKey key : typesArray){
			types.add(key);
		}
		
		List<OrganizationListItem> allOrganizations =
			this.organizationListDao.getOrganizationListByTypes(types,
					request.getSkip(),
					request.getMaxResult());
		Integer total = this.organizationListDao.getOrganizationNumberByTypes(types);

		PageResultOrganizationListItem result = new PageResultOrganizationListItem();
		result.setSkipped( request.getSkip() );
		result.setResult( translateList(allOrganizations) );
		result.setNumber( allOrganizations.size() );
		result.setTotal( total );
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
	@Override
	public PageResultOrganizationListItem getOrganizationListBySearchString(PageRequest request, String searchString){
		if(request.getMaxResult() > 40) {
			throw new NullPointerException("Max result must be 40 or less.");
		}
		
		if(isIpAddress(searchString)){
			searchString = normalizeIp(new IpAddress(searchString));
		}
		
		List<OrganizationListItem> allOrganizations = this.organizationListDao.getOrganizationListPagedSearchString(searchString,
				request.getSkip(), request.getMaxResult());
		Integer total = this.organizationListDao.getOrganizationNumberSearchString(searchString);
		
		PageResultOrganizationListItem result = new PageResultOrganizationListItem();
		result.setSkipped( request.getSkip() );
		result.setResult(translateList(allOrganizations));
		result.setNumber(allOrganizations.size());
		result.setTotal(total);
		return result;
	}
	private boolean isIpAddress(String searchString) {
		return IpAddressValidator.getInstance().isValidIPAddress(searchString);
	}
	private OrganizationListItem[] translateList(List<OrganizationListItem> organizationList) {
		OrganizationListItem[]items = new OrganizationListItem[organizationList.size()];
		int i=0;
		for (OrganizationListItem organizationListItem : organizationList) {
			items[i] = organizationListItem;
			String [] from = organizationListItem.getIpAddressesFrom();
			String [] to = organizationListItem.getIpAddressesTo();
			for(int j=0; j<from.length; j++){
				from[j] = unNormalizeIp(from[j]).getAddress();
				if(to[j] != null && (! to[j].equals(""))){
					to[j] = unNormalizeIp(to[j]).getAddress();
				} else {
					to[j] = "";
				}
			}			
			i++;
		}
		return items;
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
	@Override
	public SingleResultOrganization getOrganizationByListItem(OrganizationListItem organizationListItem) {
		Organization organization = organizationDao.getOrganizationById(organizationListItem.getId());
		if(organization == null){
			return new EmptyResultOrganization();
		}
		
		Integer typeid = organization.getType().getId();
		if(typeid == null){ throw new NullPointerException("Organization has no type"); }
		OrganizationType type = this.organizationTypeDao.getOrganizationTypeById(typeid);
		if(type==null){ throw new NullPointerException("Organization type is not valid"); }
		organization.setType(type);
		
		populateOrganizationNames(organization);
		populateOrganizationRest(organization);
		
		if(type.getKey().getValue().equals(OrganizationTypeKey._content_supplier.getValue())){
			SupplierOrganization supplierOrganization = new SupplierOrganization();
			supplierOrganization.setOrganization(organization);
			populateSupplierOrganization(supplierOrganization);
			return new ValueResultSupplierOrganization(supplierOrganization);
		} else {
			MemberOrganization memberOrganization = new MemberOrganization();
			memberOrganization.setOrganization(organization);
			populateMemberOrganization(memberOrganization);
			return new ValueResultMemberOrganization(memberOrganization);
		}
	}
	@Override
	public SingleResultOrganization getOrganizationByAdminUser(User user) {
		Organization organization = organizationDao.getOrganizationByAdminUserId(user.getId());
		if(organization == null){
			return new EmptyResultOrganization();
		} else {
			OrganizationListItem item = new OrganizationListItem();
			item.setId(organization.getId());
			return this.getOrganizationByListItem(item);
		}
	}
	private void populateMemberOrganization(MemberOrganization memberOrganization) {
		List<IpAddressLine> lineList = this.ipRangeDao.getIpRangeListByOrganization(memberOrganization);
		List<IpAddressRange> ipRangeList = new ArrayList<IpAddressRange>();
		List<IpAddressSingle> ipSingleList = new ArrayList<IpAddressSingle>();
		for (IpAddressLine ipAddressLine : lineList) {
			IpAddressSet ipAddressSet = translateIpAddressLine(ipAddressLine);
			if(ipAddressLine.getIpAddressTo().equals(ipAddressLine.getIpAddressFrom())){
				IpAddressSingle ipAddressSingle = new IpAddressSingle();
				ipAddressSingle.setIpAddressSingle(unNormalizeIp(ipAddressLine.getIpAddressFrom()));
				ipAddressSingle.setIpAddressSet(ipAddressSet);
				ipSingleList.add(ipAddressSingle);
			} else {
				IpAddressRange ipAddressRange = new IpAddressRange();
				ipAddressRange.setIpAddressFrom(unNormalizeIp(ipAddressLine.getIpAddressFrom()));
				ipAddressRange.setIpAddressTo(unNormalizeIp(ipAddressLine.getIpAddressTo()));
				ipAddressRange.setIpAddressSet(ipAddressSet);
				ipRangeList.add(ipAddressRange);
			}
		}
		IpAddressRange[] range = new IpAddressRange[ipRangeList.size()];
		int i = 0;
		for (IpAddressRange ipAddressRange : ipRangeList) {
			range[i] = ipAddressRange;
			i++;
		}
		memberOrganization.setIpAddressRangeList(range);
		IpAddressSingle[] single = new IpAddressSingle[ipSingleList.size()];
		i = 0;
		for (IpAddressSingle ipAddressSingle : ipSingleList) {
			single[i] = ipAddressSingle;
			i++;
		}
		memberOrganization.setIpAddressSingleList(single);
	}
	private void populateSupplierOrganization(SupplierOrganization supplierOrganization) {
		List<SupplierSourceResource> supplierSourceResourceList = this.resourceDao.getResourceByOrganization(supplierOrganization);
		SupplierSourceResource[] resources = new SupplierSourceResource[supplierSourceResourceList.size()];
		int i = 0;
		for (SupplierSourceResource resource : supplierSourceResourceList) {
			resources[i] = resource;
			i++;
		}
		supplierOrganization.setResourceList(resources);
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
	public SingleResultOrganization insertMemberOrganization(MemberOrganization memberOrganization) {
		// TODO Fase2: This should be removed, because it is only written to get
		//       around an error(?)in Axis2.
		if(memberOrganization.getIpAddressRangeList() == null){
			memberOrganization.setIpAddressRangeList(new IpAddressRange[0]);
		}
		if(memberOrganization.getIpAddressSingleList() == null){
			memberOrganization.setIpAddressSingleList(new IpAddressSingle[0]);
		}
		
		insertOrganization(memberOrganization.getOrganization());
		insertIpSetLists(memberOrganization);
		return new ValueResultMemberOrganization(memberOrganization);
	}
	@Override
	public SingleResultOrganization insertSupplierOrganization(SupplierOrganization supplierOrganization) {
		// TODO Fase2: This should be removed, because it is only written to get
		//       around an error(?)in Axis2.
		if(supplierOrganization.getResourceList() == null){
			supplierOrganization.setResourceList(new SupplierSourceResource[0]);
		}
		insertOrganization(supplierOrganization.getOrganization());
		insertSupplierSourceResourceList(supplierOrganization);
		return new ValueResultSupplierOrganization(supplierOrganization);
	}
	private Organization insertOrganization(Organization organization) {
		checkNull(organization);

		Person contactPerson = organization.getContactPerson();
		this.insertPerson(contactPerson);
		
		ContactInformation contactInformation = organization.getContactInformation();
		this.contactInformationDao.insertContactInformation(contactInformation);
		
		ContactInformation supportInformation = organization.getSupportInformation();
		if (supportInformation != null) {
			this.contactInformationDao.insertContactInformation(supportInformation);
		}

		OrganizationType type = this.organizationTypeDao.getOrganizationTypeByKey(organization.getType().getKey());
		if(type == null) throw new NullPointerException("Invalid type reference");

		this.organizationDao.insertOrganization(organization);

		List<OrganizationName> orgNameList = createNameList(organization);
		for (OrganizationName organizationName : orgNameList) {
			this.organizationNameDao.insertOrganizationName(organization, organizationName);
		}
		
		return organization;
		// No parent
//		Integer parentReference = organization.getParent();
//		if(parentReference != null){
//			Organization parent = this.organizationDao.getOrganizationById(parentReference);
//			if(parent == null) throw new NullPointerException("Invalid parent reference");
//		}
	}
	/**
	 * Updates an existing organization. If the organization does not
	 * exist an exception is thrown. It first checks for null on all
	 * values. The contact person and contact information must exist
	 * and it should have been created at load time. Otherwise: Huge
	 * error! :-). Changes to the ip list and name list are saved by
	 * methods saveIpRangeList and saveOrganizationNameList. See them.
	 */
	@Override
	public SingleResultOrganization updateOrganization(Organization organization) {
		checkNull(organization);
		
		OrganizationListItem organizationListItem = new OrganizationListItem();
		organizationListItem.setId(organization.getId());
		SingleResultOrganization result = this.getOrganizationByListItem(organizationListItem);
		Organization old;
		if(result instanceof EmptyResultOrganization){
			throw new NullPointerException("Tried to update non-existing organization");
		} else {
			if(result instanceof ValueResultSupplierOrganization){
				old = ((ValueResultSupplierOrganization)result).getValue().getOrganization();
			} else {
				old = ((ValueResultMemberOrganization)result).getValue().getOrganization();
			}
		}
		
		OrganizationType type = this.organizationTypeDao.getOrganizationTypeByKey(organization.getType().getKey());
		if(type == null) throw new NullPointerException("Invalid type reference");
		
		savePerson(organization.getContactPerson(), null);
		saveContactInformation(organization.getContactInformation(), null);
		saveContactInformation(organization.getSupportInformation(), null);

		if(organization.getContactPerson() != null &&
				organization.getContactPerson().getContactInformation() != null){
			saveContactInformation(organization.getContactPerson().getContactInformation(), null);
		}
		
		List<OrganizationName> nameList = this.organizationNameDao.getOrganizationNameListByOrganization(old);
		resetNameList(organization, nameList);
		updateOrganizationNameList(nameList);

		this.organizationDao.updateOrganization(organization);
		return new ValueResultOrganization(organization);
	}
	
	@Override
	public Boolean deleteOrganization(Organization organization) {
		OrganizationListItem organizationListItem = new OrganizationListItem();
		organizationListItem.setId(organization.getId());

		SingleResultOrganization result = this.getOrganizationByListItem(organizationListItem);
		Organization old;
		SupplierOrganization sup;
		MemberOrganization mem;
		if(result instanceof EmptyResultOrganization){
			throw new NullPointerException("Tried to delete non-existing organization");
		} else {
			if(result instanceof ValueResultSupplierOrganization){
				sup = ((ValueResultSupplierOrganization)result).getValue();
				old = sup.getOrganization();
				mem = null;
			} else {
				mem = ((ValueResultMemberOrganization)result).getValue();
				old = mem.getOrganization();
				sup = null;
			}
		}
		
		old.getAccessDomain();
		ContactInformation contactInformation = old.getContactInformation();
		Person person = old.getContactPerson();
		ContactInformation personContactInformation = person.getContactInformation();
		Profile profile = person.getProfile();
		ContactInformation supportInformation = old.getSupportInformation();

		// Do not delete organization users
		// Do not delete organization administrator
		// Set administration users org_id to NULL

		if(mem != null){
			IpAddressRange[] rangeList = mem.getIpAddressRangeList();
			IpAddressSingle[] singleList = mem.getIpAddressSingleList();
			IpAddressSet[] rangeSetsList = new IpAddressSet[rangeList.length];
			IpAddressSet[] singleSetsList = new IpAddressSet[singleList.length];
			int i=0;
			for (IpAddressRange range : rangeList) {
				rangeSetsList[i++] = range.getIpAddressSet();
			}
			i=0;
			for (IpAddressSingle single : singleList) {
				singleSetsList[i++] = single.getIpAddressSet();
			}
			this.deleteIpAddresses(rangeSetsList);
			this.deleteIpAddresses(singleSetsList);
			
		}
		if(sup != null){
			SupplierSourceResource[] resourceList = sup.getResourceList();
			for (SupplierSourceResource resource : resourceList) {
				List<ResourceAccessForeignKeys> accessList =
					this.accessDao.getAccessListByResource(resource.getResource());
				for (ResourceAccessForeignKeys access : accessList) {
					this.accessDao.deleteResourceAccessForeignKeys(access);
				}
				this.resourceDao.deleteSupplierSourceResource(resource);
				this.supplierSourceDao.deleteSupplierSource(resource.getSupplierSource());
			}
		}
		
		List<OrganizationName> orgNameList = this.organizationNameDao.
			getOrganizationNameListByOrganization(organization);
		for (OrganizationName organizationName : orgNameList) {
			this.organizationNameDao.deleteOrganizationName(organizationName);
		}
		
		List<OrganizationUser> adminUsers = this.userDao.getAdminUserByOrganizationId(organization.getId());
		for (OrganizationUser user : adminUsers) {
			user.getUser().getOrgAdminFor().setId(null);
			
			this.userDao.updateUser(user);
		}
		
		this.organizationDao.deleteOrganization(organization);
		this.contactInformationDao.deleteContactInformation(contactInformation);
		this.contactInformationDao.deleteContactInformation(supportInformation);
		this.personDao.deletePerson(person);
		this.profileDao.deleteProfile(profile);
		this.contactInformationDao.deleteContactInformation(personContactInformation);

		return Boolean.TRUE;
	}
	
	
	@Override
	public ListResultIpAddressSet addIpAddresses(Organization organization, IpAddressSingle[] ipAddressSingles) {
		IpAddressSet[] list = new IpAddressSet[ipAddressSingles.length];
		int i = 0;
		for (IpAddressSingle addressSingle : ipAddressSingles) {
			IpAddressLine line = translateIpAddressSingle(organization, addressSingle);
			line.setOrgUnitId(organization.getId());
			ipRangeDao.insertIpRange(line);
			list[i] = translateIpAddressLine(line);
			i++;
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
		line.setId(addressRange.getIpAddressSet().getId());
		line.setIpAddressFrom(normalizeIp(addressRange.getIpAddressFrom()));
		line.setIpAddressTo(normalizeIp(addressRange.getIpAddressTo()));
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
		line.setId(ipRange.getId());
		line.setLastChanged(ipRange.getLastChanged());
		return line;
	}
	/**
	 * Fetches all the organizations that have this IPAddress.
	 */
	@Override
	public ListResultOrganizationListItem getOrganizationListByIpAddress(IpAddress ipAddress) {
		ipAddress = new IpAddress(normalizeIp(ipAddress));
		List<OrganizationListItem> all = this.organizationListDao.getOrganizationListByIpAddress(ipAddress);
		OrganizationListItem[] list = new OrganizationListItem[all.size()];
		int i=0;
		for (OrganizationListItem memberOrganization : all) {
			list[i++]=memberOrganization;
		}
		return new ListResultOrganizationListItem(list);
	}
	
	/**
	 * Fetches all the organizations that have this access domain.
	 */
	@Override
	public ListResultOrganizationListItem getOrganizationListByAccessDomain(String accessDomain) {
		List<OrganizationListItem> all = this.organizationListDao.getOrganizationListByAccessDomain(accessDomain);
		OrganizationListItem[] list = new OrganizationListItem[all.size()];
		int i=0;
		for (OrganizationListItem memberOrganization : all) {
			list[i++]=memberOrganization;
		}
		return new ListResultOrganizationListItem(list);
	}
	
	/**
	 * Checks all values of the object for null.
	 * @param organization
	 */
	private void checkNull(Organization organization) {
		if(organization.getContactInformation()==null){ throw new NullPointerException("contactInformation==null"); }
		if(organization.getContactPerson()==null){ throw new NullPointerException("contactPerson==null"); }
		if(organization.getContactPerson().getContactInformation()==null){ throw new NullPointerException("contactPerson.contactInformation==null"); }
		if(organization.getContactPerson().getPosition()==null){ throw new NullPointerException("contactPerson.position==null"); }
		if(organization.getContactPerson().getProfile()==null){ throw new NullPointerException("contactPerson.profile==null"); }
		if(organization.getDescription()==null){ throw new NullPointerException("description==null"); }
//		if(organization.getLastChanged()==null){ throw new NullPointerException("lastChanged==null"); }
		if(organization.getNameEnglish()==null){ throw new NullPointerException("nameEnglishNormal==null"); }
		if(organization.getNameNorwegian()==null){ throw new NullPointerException("nameNorwegianNormal==null"); }
		if(organization.getNameShortEnglish()==null){ throw new NullPointerException("nameEnglishShort==null"); }
		if(organization.getNameShortNorwegian()==null){ throw new NullPointerException("nameNorwegianShort==null"); }
//		if(organization.getParent()==null){ throw new NullPointerException("parent==null"); }
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
	private void resetNameList(Organization organization, List<OrganizationName> names) {
		for (OrganizationName name : names) {
			if(name.getCategory() == OrganizationNameCategory.NORMAL &&
					name.getLanguageCode() == LanguageCategory.en){
				name.setName(organization.getNameEnglish());
			} else if(name.getCategory() == OrganizationNameCategory.SHORT &&
					name.getLanguageCode() == LanguageCategory.en){
				name.setName(organization.getNameShortEnglish());
			} else if(name.getCategory() == OrganizationNameCategory.NORMAL &&
					name.getLanguageCode() == LanguageCategory.no){
				name.setName(organization.getNameNorwegian());
			} else if(name.getCategory() == OrganizationNameCategory.SHORT &&
					name.getLanguageCode() == LanguageCategory.no){
				name.setName(organization.getNameShortNorwegian());
			}
		}
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
	 * In the longer term we should use the name list in the UI also.
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
		this.logger.info("Name " + language + "_" + category + " did not exist for organization " + organization.getId() + ". Inserting empty value for name. Just so you know. But this message is really a bit rendundant.");
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
	 * Also log when values are missing?
	 * 
	 * @param organization
	 */
	private void populateOrganizationRest(Organization organization) {
		if(organization.getContactPerson() == null ||
				organization.getContactPerson().getId() == null){
			this.logger.debug("Organization " + organization.getId() + "has no contact person");
			Person contactPerson = this.createPerson();
			organization.setContactPerson(contactPerson);
		}

		Person contactPerson = loadPerson(organization);
		
		organization.setContactPerson((contactPerson != null) ? contactPerson : new Person());

		if(organization.getContactInformation() == null ||
				organization.getContactInformation().getId() == null){
			ContactInformation contactInformation = createContactInformation();
			organization.setContactInformation(contactInformation);
		}

		ContactInformation contactInformation = this.contactInformationDao.getContactInformationByOrganization(organization);
		organization.setContactInformation((contactInformation != null) ? contactInformation : new ContactInformation());

		ContactInformation supportInformation = organization.getSupportInformation();
		if(supportInformation != null){
			supportInformation = this.contactInformationDao.getContactInformationById(supportInformation.getId());
		}
		organization.setSupportInformation((supportInformation != null) ? supportInformation : new ContactInformation());
	}
	private ContactInformation createContactInformation() {
		ContactInformation contactInformation = ContactInformationFactory.factory.completeContactInformation();
		this.contactInformationDao.insertContactInformation(contactInformation);
		return contactInformation;
	}
	private Person createPerson() {
		Position position = this.positionDao.getPositionByKey(PositionTypeKey.none);
		if(position == null){ throw new NullPointerException("Position none not found"); }
		
		Person contactPerson = PersonFactory.factory.completePerson(position);
		ContactInformation contactInformation = this.createContactInformation();
		contactPerson.setContactInformation(contactInformation);
		
		Profile profile = ProfileFactory.factory.completeProfile();
		this.profileDao.insertProfile(profile);
		
		this.personDao.insertPerson(contactPerson);
		return contactPerson;
	}
	private void insertPerson(Person person) {
		ContactInformation contactInformation = person.getContactInformation();
		Profile profile = person.getProfile();
		this.contactInformationDao.insertContactInformation(contactInformation);
		this.profileDao.insertProfile(profile);
		this.personDao.insertPerson(person);
	}
	private Person loadPerson(Organization organization) {
		Person person = this.personDao.getPersonByOrganization(organization);
		if (person != null) {
			person.setEmployer("");
			ContactInformation contactInformation = this.contactInformationDao.getContactInformationByPerson(person);
			person.setContactInformation((contactInformation != null) ? contactInformation : new ContactInformation());
			if(person.getPosition() != null && person.getPosition().getId() != null){
				person.setPosition(this.positionDao.getPositionById(person.getPosition().getId()));
			}
			Profile profile = this.profileDao.getProfileById(person.getProfile().getId());
			person.setProfile((profile != null) ? profile : new Profile());
		}
		return person;
	}
	/**
	 * Adds new ranges and single addresses.
	 * 
	 * @param changedIpRangeList
	 * @param originalIpRangeList
	 */
	private void insertIpSetLists(MemberOrganization memberOrganization) {
		for (IpAddressSingle single : memberOrganization.getIpAddressSingleList()) {
			ipRangeDao.insertIpRange(translateIpAddressSingle(memberOrganization.getOrganization(),
					single));
		}
		for (IpAddressRange range : memberOrganization.getIpAddressRangeList()) {
			ipRangeDao.insertIpRange(translateIpAddressRange(memberOrganization.getOrganization(),
					range));
		}
	}
	/**
	 * Adds new names and deletes old ones.
	 * 
	 * @param organization
	 * @param changedOrganizationNameList
	 */
	private void updateOrganizationNameList(List<OrganizationName> nameList){
		for (OrganizationName organizationName : nameList) {
			organizationNameDao.updateOrganizationName(organizationName);
		}
	}
	/**
	 * Adds new sources and deletes old ones
	 * 
	 * @param changedSupplierSourceList
	 * @param originalSupplierSourceList
	 */
	private void insertSupplierSourceResourceList(SupplierOrganization supplierOrganization) {
		for (SupplierSourceResource resource : supplierOrganization.getResourceList()) {
			resource.getResource().setOfferedBy(supplierOrganization.getOrganization().getId());
			this.supplierSourceDao.insertSupplierSource(resource.getSupplierSource());
			this.resourceDao.insertSupplierSourceResource(resource);
		}
	}
	/**
	 * Translates IpAddressSet(GUI) into IpAddressLine (DB)
	 * 
	 * @param ipAddressSet
	 * @return
	 */
	private IpAddressLine translateIpAddressSingle(Organization organization, IpAddressSingle ipAddressSingle){
		IpAddressLine ipAddressLine = new IpAddressLine();
		ipAddressLine.setId(ipAddressLine.getId());
		ipAddressLine.setOrgUnitId(organization.getId());
		ipAddressLine.setLastChanged(ipAddressSingle.getIpAddressSet().getLastChanged());
		ipAddressLine.setIpAddressFrom(normalizeIp(ipAddressSingle.getIpAddressSingle()));
		ipAddressLine.setIpAddressTo(normalizeIp(ipAddressSingle.getIpAddressSingle()));
		return ipAddressLine;
	}
	/**
	 * Translates an IP address into one with with three numbers for each part,
	 * prefixing with zeroes, like this: 192.168.101.1 => 192.168.101.001
	 * 
	 * @param address
	 * @return
	 */
	private String normalizeIp(IpAddress address) {
		StringBuffer result = new StringBuffer();
		StringTokenizer tokenizer = new StringTokenizer(address.getAddress(), ".");
		String part1 = tokenizer.nextToken();
		String part2 = tokenizer.nextToken();
		String part3 = tokenizer.nextToken();
		String part4 = tokenizer.nextToken();
		DecimalFormat format = new DecimalFormat("000");
		result.append(format.format(new Integer(part1)));
		result.append(".");
		result.append(format.format(new Integer(part2)));
		result.append(".");
		result.append(format.format(new Integer(part3)));
		result.append(".");
		result.append(format.format(new Integer(part4)));
		return result.toString();
	}
	/**
	 * Removes prefixed zeroes from IP addresses for each part, like
	 * this: 192.168.101.001 => 192.168.101.1
	 * 
	 * @param address
	 * @return
	 */
	private IpAddress unNormalizeIp(String addressText) {
		StringBuffer result = new StringBuffer();
		StringTokenizer tokenizer = new StringTokenizer(addressText, ".");
		if( ! tokenizer.hasMoreTokens()){ return new IpAddress("0.0.0.0"); }
		String part1 = tokenizer.nextToken();
		if( ! tokenizer.hasMoreTokens()){ return new IpAddress("0.0.0.0"); }
		String part2 = tokenizer.nextToken();
		if( ! tokenizer.hasMoreTokens()){ return new IpAddress("0.0.0.0"); }
		String part3 = tokenizer.nextToken();
		if( ! tokenizer.hasMoreTokens()){ return new IpAddress("0.0.0.0"); }
		String part4 = tokenizer.nextToken();
		result.append(new Integer(part1));
		result.append(".");
		result.append(new Integer(part2));
		result.append(".");
		result.append(new Integer(part3));
		result.append(".");
		result.append(new Integer(part4));
		return new IpAddress(result.toString());
	}
	/**
	 * Translates IpAddressLine (DB) into IpAddressSet(GUI)
	 * 
	 * @param ipAddressSet
	 * @return
	 */
	private IpAddressSet translateIpAddressLine(IpAddressLine ipAddressLine) {
		IpAddressSet ipAddressSet = new IpAddressSet();
		ipAddressSet.setId(ipAddressLine.getId());
		ipAddressSet.setLastChanged(ipAddressLine.getLastChanged());
		return ipAddressSet;
	}
	private void savePerson(Person person, Person originalPerson) {
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
	@Override
	public ListResultSupplierSourceResource addResources(SupplierSourceResource[] resources) {
		for (SupplierSourceResource supplierSourceResource : resources) {
			if(supplierSourceResource.getResource().getId() == null){
				this.supplierSourceDao.insertSupplierSource(supplierSourceResource.getSupplierSource());
				this.resourceDao.insertSupplierSourceResource(supplierSourceResource);
			}
		}
		return new ListResultSupplierSourceResource(resources);
	}
	@Override
	public Boolean deleteResources(SupplierSourceResource[] resources) {
		for (SupplierSourceResource supplierSourceResource : resources) {
			if(supplierSourceResource.getResource().getId() != null) {
				this.accessDao.deleteAccessByResourceId(supplierSourceResource.getResource().getId());
				this.resourceDao.deleteSupplierSourceResource(supplierSourceResource);
				this.supplierSourceDao.deleteSupplierSource(supplierSourceResource.getSupplierSource());
			}
		}
		return Boolean.TRUE;
	}

	@Override
	public ListResultProxyResult getProxyExportList(ProxyExportParameter parameter) {
		List<ProxyResult> all = this.proxyExportDao.getProxyExportList(parameter);
		ProxyResult[] list = new ProxyResult[all.size()];
		int i=0;
		for (ProxyResult proxyResult : all) {
			list[i++]=proxyResult;
		}
		return new ListResultProxyResult(list);
	}

	@Override
	public Boolean insertProxyHits(ProxyHitParameterList list) {
		Map<String, List<Integer>> memberOrgsFromIp = new HashMap<String, List<Integer>>();
		Map<String, Integer> supplierOrgsFromDomain = new HashMap<String, Integer>();
		Map<String, ProxyHitLine> hits = new HashMap<String, ProxyHitLine>();
		List<SupplierSourceResource> allResources = this.resourceDao.getResourceListAll();
		
		SimpleDateFormat periodFormat = new SimpleDateFormat("yyyyMMddhh");
		
		for (SupplierSourceResource supplierSourceResource : allResources) {
			String domain = supplierSourceResource.getSupplierSource().getUrl().getDomain();
			Integer orgId = supplierSourceResource.getResource().getOfferedBy();
			if(domain == null || orgId == null){
				this.logger.error("No domain or organization for inserting proxy hits");
				throw new NullPointerException("THROWING BIG TIME ERROR");
			} else {
				StringTokenizer tokenizer = new StringTokenizer(domain, "/");
				domain = tokenizer.nextToken();
				supplierOrgsFromDomain.put(domain, orgId);
			}
		}
		for (ProxyHitParameter parameter : list.getList()) {
			// Lookup member
			String normalIP = normalizeIp(new IpAddress(
					parameter.getFromIP()));
			if( ! memberOrgsFromIp.containsKey(normalIP)){
				OrganizationListItem[] memOrgs = this.getOrganizationListByIpAddress(new IpAddress(normalIP)).getList();
				List<Integer> orgids = new ArrayList<Integer>();
				for (OrganizationListItem item : memOrgs) {
					orgids.add(item.getId());
				}
				memberOrgsFromIp.put(normalIP, orgids);
			}
			Integer memberOrgId;
			boolean isMultiple;
			if(memberOrgsFromIp.get(normalIP).size() == 0){
				memberOrgId = null;
				isMultiple = false;
			} else if(memberOrgsFromIp.get(normalIP).size() == 1){
				memberOrgId = memberOrgsFromIp.get(normalIP).get(0);
				isMultiple = false;
			} else {
				memberOrgId = null;
				isMultiple = true;				
			}
			
			// Lookup supplier
			String domain = parameter.getToDomain();
			Integer supplierOrgId = supplierIdFromDomain(supplierOrgsFromDomain, domain);// supplierOrgsFromDomain.get(domain);
			if(supplierOrgId == null){
				this.logger.warn("Missing supplier for domain " + domain);
			}
			
			// Period OK
			String period = parameter.getPeriod();
			
			// insert hits
			String key = ""+memberOrgId+"*"+supplierOrgId+"*"+isMultiple+"*"+period;
			if(hits.containsKey(key)){
				ProxyHitLine line = hits.get(key);
				line.setCount(line.getCount().intValue()+parameter.getHits());
			} else {
				ProxyHitLine line = new ProxyHitLine();
				line.setMemberOrgUnitId(memberOrgId);
				line.setSupplierOrgUnitId(supplierOrgId);
				String year = period.substring(0, 4);
				line.setYear(year);
				String month = period.substring(4, 6);
				line.setMonth(month);
				String dayOfMonth = period.substring(6, 8);
				line.setDayOfMonth(dayOfMonth);
				String hour = period.substring(8, 10);
				line.setHour(hour);
				
				String dayOfWeek;
				try {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(periodFormat.parse(period));
					dayOfWeek = "" + (calendar.get(Calendar.DAY_OF_WEEK)+6)%7;
				} catch (ParseException e) {
					dayOfWeek = null;
					this.logger.error("Unable to parse date " + period);
				}
				line.setDayOfWeek(dayOfWeek);
				

				Integer count = parameter.getHits();
				line.setCount(count);
				Boolean multipleMembers = isMultiple;
				line.setMultipleMembers(multipleMembers);
				hits.put(key, line);
			}
		}
		
		for (String key : hits.keySet()) {
			//System.out.println(hits.get(key));
			this.proxyExportDao.insertHitsList(hits.get(key));
		}
		
		return Boolean.TRUE;
	}
	
	/*
	 * Find best match for supplier given a domain
	 */
	private Integer supplierIdFromDomain(Map<String, Integer> supplierOrgsDomains, String domain) {
		Integer supplierId = null;
		if (null != (supplierId = supplierOrgsDomains.get(domain))) {
			return supplierId;
		} else {
			String lookupDomainKey = domain;
			int failsafe = 0;
			// try lookup as long as there is one or more periods in domain key and as long as there are not too many periods in the key
			while ((lookupDomainKey.indexOf('.') != -1) && (lookupDomainKey.indexOf('.') < lookupDomainKey.lastIndexOf('.')) && (failsafe++ <= 10)) {
				lookupDomainKey = lookupDomainKey.substring(lookupDomainKey.indexOf('.')+1, lookupDomainKey.length());
				if (null != (supplierId = supplierOrgsDomains.get(lookupDomainKey))) {
					logger.debug("found supplier id by 'like search'. supplierid/domain: " + supplierId + "/" + lookupDomainKey);
					return supplierId;
				}
			}
		}
		// no match, nothing to return but null
		return null;
	}
	
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
	public void setProfileDao(ProfileDao profileDao) {
		this.profileDao = profileDao;
	}
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}
	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}
	public AccessDao getAccessDao() {
		return accessDao;
	}
	public void setAccessDao(AccessDao accessDao) {
		this.accessDao = accessDao;
	}
	public ProxyExportDao getProxyExportDao() {
		return proxyExportDao;
	}
	public void setProxyExportDao(ProxyExportDao proxyExportDao) {
		this.proxyExportDao = proxyExportDao;
	}
	public UserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}