package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressRange;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.IpAddressSingle;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.ResourceAccessListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultAccessType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultResourceType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSupplierOrganization;
import no.helsebiblioteket.admin.validator.IpAddressValidator;
import no.helsebiblioteket.admin.web.jsf.MessageResourceReader;

public class CreateAndChangeMemberOrganizationBean extends NewOrganizationBean {
	protected final Log logger = LogFactory.getLog(getClass());
	private MemberOrganization memberOrganization;
	private ResourceAccessListItem[] orgTypeAccessList;
	private ResourceAccessListItem[] orgAccessList;

	private String ipAddressSingle = null;
	private String ipAddressFrom = null;
	private String ipAddressTo = null;
	private UIInput ipAddressSingleUIInput = null;
	private UIInput ipAddressFromUIInput = null;
	private UIInput ipAddressToUIInput = null;
	private HtmlSelectOneMenu supplierSourceListValue;
	private HtmlSelectOneMenu accessTypeCategory = null;
	
	private String selectedOrganizationType = null;
	
	private List<IpAddressRange> ipRangeList = null;
	
	private HtmlDataTable ipRangeListHtmlDataTable = null;

	public CreateAndChangeMemberOrganizationBean() {
//		this.beanMode = BeanMode.change;
	}

	public String getSelectedOrganizationType() {
		return selectedOrganizationType;
	}

	public void setSelectedOrganizationType(String selectedOrganizationType) {
		this.selectedOrganizationType= selectedOrganizationType;
	}

	public String getIpAddressSingle() {
		return ipAddressSingle;
	}

	public void setIpAddressSingle(String ipAddressSingle) {
		this.ipAddressSingle = ipAddressSingle;
	}

	public UIInput getIpAddressSingleUIInput() {
		return ipAddressSingleUIInput;
	}

	public void setIpAddressSingleUIInput(UIInput ipAddressSingleUIInput) {
		this.ipAddressSingleUIInput = ipAddressSingleUIInput;
	}

	public String getIpAddressFrom() {
		return ipAddressFrom;
	}

	public void setIpAddressFrom(String ipAddressFrom) {
		this.ipAddressFrom = ipAddressFrom;
	}

	public String getIpAddressTo() {
		return ipAddressTo;
	}

	public void setIpAddressTo(String ipAddressTo) {
		this.ipAddressTo = ipAddressTo;
	}
	
	public UIInput getIpAddressFromUIInput() {
		return ipAddressFromUIInput;
	}

	public void setIpAddressFromUIInput(UIInput ipAddressFromUIInput) {
		this.ipAddressFromUIInput = ipAddressFromUIInput;
	}

	public UIInput getIpAddressToUIInput() {
		return ipAddressToUIInput;
	}

	public void setIpAddressToUIInput(UIInput ipAddressToUIInput) {
		this.ipAddressToUIInput = ipAddressToUIInput;
	}

	public List<IpAddressRange> getIpRangeList() {
		return this.ipRangeList;
	}

	public HtmlDataTable getIpRangeListHtmlDataTable() {
		return this.ipRangeListHtmlDataTable;
	}

	public void setIpRangeListHtmlDataTable(HtmlDataTable ipRangeListHtmlDataTable) {
		this.ipRangeListHtmlDataTable = ipRangeListHtmlDataTable;
	}

	public String actionSaveOrganization() {
		logger.debug("Method 'actionSaveOrganization' invoked");
		ContactInformation contactInformationOrganization;
		Person contactPerson;
		Profile contactPersonProfile;
		ContactInformation contactInformationPerson;
		if(this.isNew){
//			this.memberOrganization = new MemberOrganization();
			contactInformationOrganization = new ContactInformation();
			this.memberOrganization.getOrganization().setContactInformation(contactInformationOrganization);
			contactPerson = new Person();
			OrganizationType organizationType = ((ValueResultOrganizationType)this.organizationService.getOrganizationTypeByKey(
					OrganizationTypeKey.health_enterprise)).getValue();
			contactPerson.setPosition(((ValueResultPosition)this.userService.getPositionByKey(PositionTypeKey.none, organizationType)).getValue());
			this.memberOrganization.getOrganization().setContactPerson(contactPerson);
			contactInformationPerson = new ContactInformation();
			contactPerson.setContactInformation(contactInformationPerson);
			contactPersonProfile = new Profile();
			contactPerson.setProfile(contactPersonProfile);
		} else {
			contactInformationOrganization = this.memberOrganization.getOrganization().getContactInformation();
			contactPerson = this.memberOrganization.getOrganization().getContactPerson();
			contactInformationPerson = this.memberOrganization.getOrganization().getContactPerson().getContactInformation();
			contactPersonProfile = contactPerson.getProfile();
		}
		contactInformationOrganization.setPostalAddress(this.getOrgAddress());
		contactInformationOrganization.setPostalCode(this.getOrgPostalCode());
		contactInformationOrganization.setPostalLocation(this.getOrgPostalLocation());
		contactInformationOrganization.setEmail("");
		contactInformationOrganization.setTelephoneNumber("");

		contactPerson.setFirstName(this.getContactPersonFirstName());
		contactPerson.setLastName(this.getContactPersonLastName());

		contactInformationPerson.setTelephoneNumber(this.getContactPersonTelephoneNumber());
		contactInformationPerson.setEmail(this.getContactPersonEmail());

		contactPersonProfile.setParticipateSurvey(false);
		contactPersonProfile.setReceiveNewsletter(false);
		
		this.memberOrganization.getOrganization().setType(
				((ValueResultOrganizationType)this.organizationService.getOrganizationTypeByKey(
						new OrganizationTypeKey(selectedOrganizationType))).getValue());

		if(this.isNew){
			this.organizationService.insertMemberOrganization(this.memberOrganization);
		} else {
			organizationService.updateOrganization(this.memberOrganization.getOrganization());
		}

		for (ResourceAccessListItem item : this.organizationBean.getAccessService().getAccessListByOrganization(this.organization).getList()) {
			this.organizationBean.getAccessService().deleteResourceAccess(item);
		}
		for (ResourceAccessListItem item : this.orgAccessList) {
			ResourceAccess resourceAccess = new ResourceAccess();
			Access access = new Access();
			AccessType accessType = ((ValueResultAccessType)this.organizationBean.getAccessService().getAccessTypeByTypeCategory(item.getKey(), item.getCategory())).getValue();
			access.setAccessType(accessType);
			OrganizationListItem organizationListItem = new OrganizationListItem();
			// TODO: Id of supplier
			organizationListItem.setId(item.getProvidedBy());
			SupplierOrganization supplier = ((ValueResultSupplierOrganization)this.organizationBean.getOrganizationService().getOrganizationByListItem(organizationListItem)).getValue();
			access.setProvidedBy(supplier);
			Calendar calendar = Calendar.getInstance();
			access.setValidFrom(calendar.getTime());
			calendar.add(Calendar.YEAR, 1);
			access.setValidTo(calendar.getTime());
			resourceAccess.setAccess(access);

			SupplierSourceResource supplierSourceResource = new SupplierSourceResource();
			Resource resource = new Resource();
			resource.setId(item.getResourceId());
			resource.setOfferedBy(supplier.getOrganization().getId());
			ResourceType resourceType = ((ValueResultResourceType)this.organizationBean.getAccessService().getResourceTypeByKey(ResourceTypeKey.supplier_source)).getValue();
			resource.setResourceType(resourceType);
						
			SupplierSource supplierSource = new SupplierSource();
			supplierSource.setId(item.getSupplierSourceId());
			supplierSource.setSupplierSourceName(item.getSupplierSourceName());
			supplierSource.setUrl(item.getUrl());
			supplierSourceResource.setResource(resource);
			supplierSourceResource.setSupplierSource(supplierSource);
			
			resourceAccess.setResource(supplierSourceResource);
			this.organizationBean.getAccessService().insertOrganizationResourceAccess(this.organization, resourceAccess);
		}
		
		IpAddressSet[] ipAddressSets = new IpAddressSet[this.memberOrganization.getIpAddressRangeList().length
		                                               + this.memberOrganization.getIpAddressSingleList().length];
		int i=0;
		for (IpAddressRange range : this.memberOrganization.getIpAddressRangeList()) {
			ipAddressSets[i++] = range.getIpAddressSet();
		}
		for (IpAddressSingle single : this.memberOrganization.getIpAddressSingleList()) {
			ipAddressSets[i++] = single.getIpAddressSet();
		}
		this.organizationService.deleteIpAddresses(ipAddressSets);
		
		int singles = 0;
		int ranges = 0;
		for (IpAddressRange range : this.ipRangeList) {
			if(range.getIpAddressTo().getAddress().equals("")) singles++; else ranges++;
		}
		IpAddressSingle[] ipAddressSingles = new IpAddressSingle[singles];
		IpAddressRange[] ipAddressRanges = new IpAddressRange[ranges]; 
		singles = 0;
		ranges = 0;
		
		for (IpAddressRange range : this.ipRangeList) {
			if(range.getIpAddressTo().getAddress().equals("")) {
				ipAddressSingles[singles] = new IpAddressSingle();
				ipAddressSingles[singles].setIpAddressSet(range.getIpAddressSet());
				ipAddressSingles[singles].setIpAddressSingle(range.getIpAddressFrom());
				singles++;
			} else {
				ipAddressRanges[ranges++] = range;
			}
		}
		
		this.organizationService.addIpAddresses(organization, ipAddressSingles);
		this.organizationService.addIpAddressRanges(organization, ipAddressRanges);
		
		this.organizationBean.setOrganization(this.organization);
		return this.organizationBean.actionDetailsSingle();
	}
	
	public void actionAddSingleIp() {
		logger.debug("Method 'actionAddSingleIp' invoked");
		setIpAddressSingle((getIpAddressSingleUIInput().getSubmittedValue() != null) ? getIpAddressSingleUIInput().getSubmittedValue().toString() : null);
		if (this.ipRangeList == null) {
			this.ipRangeList = new ArrayList<IpAddressRange>();
		}
		if(IpAddressValidator.getInstance().isValidIPAddress(ipAddressSingle)) {
			this.ipRangeList.add(new IpAddressRange(new IpAddress(getIpAddressSingle()), new IpAddress("")));
		}
		else{
			 String  bundleMain = "no.helsebiblioteket.admin.web.jsf.messageresources.main";
			 String messageValue = MessageResourceReader.getMessageResourceString(bundleMain, "ip_address_valid", "Ip address in not valid.");
			 FacesContext.getCurrentInstance().addMessage("main:create-and-change-member-organization:ipAddressSingle" , new FacesMessage(FacesMessage.SEVERITY_ERROR,messageValue,null));
			 
		}
	}
	public void actionAddIpRange() {
		logger.debug("Method 'actionAddIpRange' invoked");
		
		setIpAddressFrom((getIpAddressFromUIInput().getSubmittedValue() != null) ? getIpAddressFromUIInput().getSubmittedValue().toString() : null);
		setIpAddressTo((getIpAddressToUIInput().getSubmittedValue() != null) ? getIpAddressToUIInput().getSubmittedValue().toString() : null);
		if (this.ipRangeList == null) {
			this.ipRangeList = new ArrayList<IpAddressRange>();
		}
		if(!IpAddressValidator.getInstance().isValidIPAddress(ipAddressFrom)){
			 String  bundleMain = "no.helsebiblioteket.admin.web.jsf.messageresources.main";
			 String messageValue = MessageResourceReader.getMessageResourceString(bundleMain, "ip_address_valid", "Ip address in not valid.");
			 FacesContext.getCurrentInstance().addMessage("main:create-and-change-member-organization:ipAddressFrom" , new FacesMessage(FacesMessage.SEVERITY_ERROR,messageValue,null));
		}else if(!IpAddressValidator.getInstance().isValidIPAddress(ipAddressTo)){
			 String  bundleMain = "no.helsebiblioteket.admin.web.jsf.messageresources.main";
			 String messageValue = MessageResourceReader.getMessageResourceString(bundleMain, "ip_address_valid", "Ip address in not valid.");
			 FacesContext.getCurrentInstance().addMessage("main:create-and-change-member-organization:ipAddressTo" , new FacesMessage(FacesMessage.SEVERITY_ERROR,messageValue,null));
		}else
			this.ipRangeList.add(new IpAddressRange(new IpAddress(getIpAddressFrom()), new IpAddress(getIpAddressTo())));
		
		
	}
	
	public void actionAddSource(){
		String selectedSourceValue = this.getSupplierSourceListValue().getSubmittedValue().toString();
		String selectedAccessTypeCategoryValue = this.getAccessTypeCategory().getSubmittedValue().toString();

		SupplierSourceResource addedResource = null;
		SupplierSourceResource[] resources = this.organizationBean.getSupplierSourceResources();
		for (SupplierSourceResource supplierSourceResource : resources) {
			if(selectedSourceValue.equals(""+supplierSourceResource.getResource().getId())){
				addedResource = supplierSourceResource;
			}
		}
		ResourceAccessListItem[] newList = new ResourceAccessListItem[this.orgAccessList.length + 1];
		for(int i=0; i<this.orgAccessList.length;i++){
			newList[i] = this.orgAccessList[i];
		}
		newList[newList.length-1] = new ResourceAccessListItem();
		newList[newList.length-1].setSupplierSourceName(addedResource.getSupplierSource().getSupplierSourceName());
		newList[newList.length-1].setCategory(new AccessTypeCategory(selectedAccessTypeCategoryValue));
		newList[newList.length-1].setId(addedResource.getResource().getId());
		newList[newList.length-1].setKey(AccessTypeKey.general);
		newList[newList.length-1].setUrl(addedResource.getSupplierSource().getUrl());
		newList[newList.length-1].setProvidedBy(addedResource.getResource().getOfferedBy());
		newList[newList.length-1].setResourceId(addedResource.getResource().getId());
		newList[newList.length-1].setSupplierSourceId(addedResource.getSupplierSource().getId());
		
		this.orgAccessList = newList;
		this.organizationBean.setOrgAccessList(newList);
	}

	public void actionDeleteIpRange() {
		logger.debug("Method 'actionDeleteIpRange' invoked");
		//Integer rowIndex = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ipRangeDeleteTableRowIndex"); 
		this.ipRangeList.remove((IpAddressRange) this.ipRangeListHtmlDataTable.getRowData());
	}
	
	public String actionEditOrganization() {
		Integer orgId = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("organizationId");
		OrganizationListItem organizationLookup = new OrganizationListItem();
		organizationLookup.setId(orgId);
		
		SingleResultOrganization result = organizationService.getOrganizationByListItem(organizationLookup);
    	// TODO: Do not use instanceof ValueResultOrganization.
		if(result instanceof ValueResultOrganization){
    		// FIXME: re-insert:
        	this.memberOrganization = null;// (MemberOrganization) ((ValueResultOrganization)result).getValue();
    	} else {
    		this.memberOrganization = null;
    	}
		return "create_and_change_member_organization";
	}
	
	public void addIpRangeActionListener(ActionEvent actionEvent) {
		logger.debug("Method 'addIpRangeActionListener' invoked");
		logger.debug("vce.getComponent().getId(): " + actionEvent.getComponent().getId());
	}
	
	public boolean isShowIpRangeList() {
		return (ipRangeList != null && ipRangeList.size() > 0) ? true : false;
	}
	
	public List<SelectItem> getSupplierSourceList() {
		SupplierSourceResource[] resources = this.organizationBean.getSupplierSourceResources();
		List<SelectItem> list = new ArrayList<SelectItem>();
		for (SupplierSourceResource resource : resources) {
			SelectItem item = new SelectItem("" + resource.getResource().getId(), "" +
					resource.getSupplierSource().getSupplierSourceName() + " " +
					resource.getSupplierSource().getUrl().getStringValue());
			list.add(item);
		}
		return list;
	}
	
	public List<SelectItem> getAccessTypeCategoryList(){
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(AccessTypeCategory.GRANT.getValue(), "Grant"));
		list.add(new SelectItem(AccessTypeCategory.DENY.getValue(), "Deny"));
		return list;
	}
	
	public HtmlSelectOneMenu getSupplierSourceListValue(){
		return supplierSourceListValue;
	}

	public void setSupplierSourceListValue(HtmlSelectOneMenu value){
		this.supplierSourceListValue = value;
	}

	public List<String> getSelectedSourceList() {
		List<String> list = new ArrayList<String>();
		return list;
	}

	public ResourceAccessListItem[] getOrgTypeAccessList() {
		return orgTypeAccessList;
	}

	public ResourceAccessListItem[] getOrgAccessList() {
		return orgAccessList;
	}

	// Method is invoked by hidden init-field in JSP.
	public String getInit() {
		initOrganization();
		return "";
	}

	public String actionCancel(){
		this.organizationBean.setOrganization(this.organization);
		return this.organizationBean.actionDetailsSingle();
	}
	
	private void initOrganization() {
		if ( ! this.organizationBean.getIsNew()) {
			this.memberOrganization = organizationBean.getMemberOrganization();
		} else {
			this.memberOrganization = new MemberOrganization();
			this.memberOrganization.getOrganization().setContactInformation(new ContactInformation());
			this.memberOrganization.getOrganization().setContactPerson(new Person());
			this.memberOrganization.getOrganization().getContactPerson().setContactInformation(new ContactInformation());
			this.memberOrganization.getOrganization().setNameEnglish("");
			this.memberOrganization.getOrganization().setNameNorwegian("");
			this.memberOrganization.getOrganization().setNameShortEnglish("");
			this.memberOrganization.getOrganization().setNameShortNorwegian("");
			this.memberOrganization.setIpAddressRangeList(new IpAddressRange[0]);
			this.ipAddressFrom = "";
			this.ipAddressTo="";
			this.ipAddressSingle="";
				
		}
		this.setIsNew(this.organizationBean.getIsNew());
		this.setNotNew( ! this.organizationBean.getIsNew());
		this.organization = this.memberOrganization.getOrganization();
		
		this.orgTypeAccessList = this.organizationBean.getOrgTypeAccessList();
		this.orgAccessList = this.organizationBean.getOrgAccessList();
		
	}
	public HtmlSelectOneMenu getAccessTypeCategory() {
		return accessTypeCategory;
	}

	public void setAccessTypeCategory(HtmlSelectOneMenu accessTypeCategory) {
		this.accessTypeCategory = accessTypeCategory;
	}
}
