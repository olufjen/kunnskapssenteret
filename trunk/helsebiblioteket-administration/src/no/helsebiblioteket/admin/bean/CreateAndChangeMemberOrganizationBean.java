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
import no.helsebiblioteket.admin.domain.requestresult.ValueResultAccessType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultResourceType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSupplierOrganization;
import no.helsebiblioteket.admin.validator.IpAddressValidator;
import no.helsebiblioteket.admin.web.jsf.MessageResourceReader;

public class CreateAndChangeMemberOrganizationBean extends NewOrganizationBean {
	protected final Log logger = LogFactory.getLog(getClass());
	private MemberOrganization memberOrganization;

	private String ipAddressSingle = null;
	private String ipAddressFrom = null;
	private String ipAddressTo = null;
	private UIInput ipAddressSingleUIInput = null;
	private UIInput ipAddressFromUIInput = null;
	private UIInput ipAddressToUIInput = null;
	private HtmlSelectOneMenu supplierSourceListValue;
	private HtmlSelectOneMenu accessTypeCategoryKey;
	
	private String selectedOrganizationType = null;
	private List<IpAddressRange> ipRangeList = null;
	private HtmlDataTable ipRangeListHtmlDataTable = null;
	private HtmlDataTable orgAccessTable;
	
	public String actionSaveOrganization() {
		logger.debug("Method 'actionSaveOrganization' invoked");
		ContactInformation contactInformationOrganization;
		Profile contactPersonProfile;
		if(this.isNew){
//			this.memberOrganization = new MemberOrganization();
			contactInformationOrganization = new ContactInformation();
			this.memberOrganization.getOrganization().setContactInformation(contactInformationOrganization);
			OrganizationType organizationType = ((ValueResultOrganizationType)this.organizationService.getOrganizationTypeByKey(
					OrganizationTypeKey.health_enterprise)).getValue();
			Person contactPerson = this.memberOrganization.getOrganization().getContactPerson();
			contactPerson.setPosition(((ValueResultPosition)this.userService.getPositionByKey(PositionTypeKey.none, organizationType)).getValue());
			contactPersonProfile = this.memberOrganization.getOrganization().getContactPerson().getProfile();
		} else {
			contactInformationOrganization = this.memberOrganization.getOrganization().getContactInformation();
			contactPersonProfile = this.memberOrganization.getOrganization().getContactPerson().getProfile();
		}
		contactInformationOrganization.setEmail("");
		contactInformationOrganization.setTelephoneNumber("");
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

		for (ResourceAccessListItem item : this.organizationBean.deltetedAccesses) {
			if(item.getId() == null) {continue;}
			this.organizationBean.getAccessService().deleteResourceAccess(item);
		}
		for (ResourceAccessListItem item : this.organizationBean.getOrgAccessList()) {
			if(item.getId() != null) {continue;}
			ResourceAccess resourceAccess = new ResourceAccess();
			Access access = new Access();
			AccessType accessType = ((ValueResultAccessType)this.organizationBean.getAccessService().getAccessTypeByTypeCategory(item.getKey(), item.getCategory())).getValue();
			access.setAccessType(accessType);
			OrganizationListItem organizationListItem = new OrganizationListItem();
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
		for (IpAddressRange range : this.organizationBean.getIpRangeList()) {
			if(range.getIpAddressTo().getAddress().equals("")) singles++; else ranges++;
		}
		IpAddressSingle[] ipAddressSingles = new IpAddressSingle[singles];
		IpAddressRange[] ipAddressRanges = new IpAddressRange[ranges]; 
		singles = 0;
		ranges = 0;
		
		for (IpAddressRange range : this.organizationBean.getIpRangeList()) {
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
		
		this.ipAddressFrom = "";
		this.ipAddressTo = "";
		this.ipAddressSingle = "";
		
		this.ipAddressFromUIInput = null;
		this.ipAddressToUIInput = null;
		this.ipAddressSingleUIInput = null;
		
		this.supplierSourceListValue = null;
		this.accessTypeCategoryKey = null;
		
		this.selectedOrganizationType = null;
		this.ipRangeList = null;
		this.ipRangeListHtmlDataTable = null;
		this.orgAccessTable = null;

		this.organizationBean.setOrganization(this.organization);
		this.organizationBean.runSearch();
		return this.organizationBean.actionDetailsSingle();
	}
	
	public void actionAddSingleIp() {
		logger.debug("Method 'actionAddSingleIp' invoked");
		setIpAddressSingle((getIpAddressSingleUIInput().getSubmittedValue() != null) ? getIpAddressSingleUIInput().getSubmittedValue().toString() : null);
		if(IpAddressValidator.getInstance().isValidIPAddress(ipAddressSingle)) {
			this.organizationBean.getIpRangeList().add(new IpAddressRange(new IpAddress(getIpAddressSingle()), new IpAddress("")));
		} else{
			 String  bundleMain = "no.helsebiblioteket.admin.web.jsf.messageresources.main";
			 String messageValue = MessageResourceReader.getMessageResourceString(bundleMain, "ip_address_valid", "Ip address in not valid.");
			 FacesContext.getCurrentInstance().addMessage("main:create-and-change-member-organization:ipAddressSingle" , new FacesMessage(FacesMessage.SEVERITY_ERROR,messageValue,null));
		}
//		this.ipAddressSingle = "";
//		this.ipAddressSingleUIInput = null;
	}
	public void actionAddIpRange() {
		logger.debug("Method 'actionAddIpRange' invoked");
		setIpAddressFrom((getIpAddressFromUIInput().getSubmittedValue() != null) ? getIpAddressFromUIInput().getSubmittedValue().toString() : null);
		setIpAddressTo((getIpAddressToUIInput().getSubmittedValue() != null) ? getIpAddressToUIInput().getSubmittedValue().toString() : null);
		if(!IpAddressValidator.getInstance().isValidIPAddress(ipAddressFrom)){
			 String  bundleMain = "no.helsebiblioteket.admin.web.jsf.messageresources.main";
			 String messageValue = MessageResourceReader.getMessageResourceString(bundleMain, "ip_address_valid", "Ip address in not valid.");
			 FacesContext.getCurrentInstance().addMessage("main:create-and-change-member-organization:ipAddressFrom" , new FacesMessage(FacesMessage.SEVERITY_ERROR,messageValue,null));
		}else if(!IpAddressValidator.getInstance().isValidIPAddress(ipAddressTo)){
			 String  bundleMain = "no.helsebiblioteket.admin.web.jsf.messageresources.main";
			 String messageValue = MessageResourceReader.getMessageResourceString(bundleMain, "ip_address_valid", "Ip address in not valid.");
			 FacesContext.getCurrentInstance().addMessage("main:create-and-change-member-organization:ipAddressTo" , new FacesMessage(FacesMessage.SEVERITY_ERROR,messageValue,null));
		}else {
			this.organizationBean.getIpRangeList().add(new IpAddressRange(new IpAddress(getIpAddressFrom()), new IpAddress(getIpAddressTo())));
		}
//		this.ipAddressFrom = "";
//		this.ipAddressTo = "";
//		this.ipAddressFromUIInput = null;
//		this.ipAddressToUIInput = null;
	}
	public void actionDeleteAccess(){
		int index = this.orgAccessTable.getRowIndex();
		this.organizationBean.deltetedAccesses.add(this.organizationBean.orgAccessList[index]);
		ResourceAccessListItem[] newList = new ResourceAccessListItem[this.organizationBean.orgAccessList.length - 1];
		int j=0; int i=0;
		for (ResourceAccessListItem access : this.organizationBean.orgAccessList) {
			if(j != index){ newList[i++] = access; } j++;
		}
		this.organizationBean.orgAccessList = newList;
	}
	public void actionAddSource(){
		String selectedSourceValue = this.getSupplierSourceListValue().getSubmittedValue().toString();

		String selectedAccessTypeCategoryKeyValue = this.accessTypeCategoryKey.getSubmittedValue().toString();
		String selectedAccessTypeCategoryKeyValueArray[] = selectedAccessTypeCategoryKeyValue.split(System.getProperty("path.separator"));
		String selectedAccessTypeCategoryValue = selectedAccessTypeCategoryKeyValueArray[0];
		String selectedAccessTypeKeyValue = selectedAccessTypeCategoryKeyValueArray[1];
		
		SupplierSourceResource addedResource = null;
		SupplierSourceResource[] resources = this.organizationBean.getSupplierSourceResources();
		for (SupplierSourceResource supplierSourceResource : resources) {
			if(selectedSourceValue.equals(""+supplierSourceResource.getResource().getId())){
				addedResource = supplierSourceResource;
			}
		}
		ResourceAccessListItem[] newList = new ResourceAccessListItem[this.organizationBean.orgAccessList.length + 1];
		for(int i=0; i<this.organizationBean.orgAccessList.length;i++){
			newList[i] = this.organizationBean.orgAccessList[i];
		}
		newList[newList.length-1] = new ResourceAccessListItem();
		newList[newList.length-1].setSupplierSourceName(addedResource.getSupplierSource().getSupplierSourceName());
		newList[newList.length-1].setCategory(new AccessTypeCategory(selectedAccessTypeCategoryValue));
		newList[newList.length-1].setKey(new AccessTypeKey(selectedAccessTypeKeyValue));
		newList[newList.length-1].setUrl(addedResource.getSupplierSource().getUrl());
		newList[newList.length-1].setProvidedBy(addedResource.getResource().getOfferedBy());
		newList[newList.length-1].setResourceId(addedResource.getResource().getId());
		newList[newList.length-1].setSupplierSourceId(addedResource.getSupplierSource().getId());
		
		this.organizationBean.orgAccessList = newList;
		this.organizationBean.setOrgAccessList(newList);
	}

	public void actionDeleteIpRange() {
		logger.debug("Method 'actionDeleteIpRange' invoked");
		this.ipRangeList.remove((IpAddressRange) this.ipRangeListHtmlDataTable.getRowData());
	}
	
	public void addIpRangeActionListener(ActionEvent actionEvent) {
		logger.debug("Method 'addIpRangeActionListener' invoked");
		logger.debug("vce.getComponent().getId(): " + actionEvent.getComponent().getId());
	}
	
	public boolean isShowIpRangeList() {
		return (ipRangeList.size() > 0) ? true : false;
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

	
	public HtmlDataTable getOrgAccessTable() {
		return orgAccessTable;
	}

	public void setOrgAccessTable(HtmlDataTable orgAccessTable) {
		this.orgAccessTable = orgAccessTable;
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
		return this.organizationBean.getOrgTypeAccessList();
	}

	public ResourceAccessListItem[] getOrgAccessList() {
		return this.organizationBean.getOrgAccessList();
	}

	public HtmlSelectOneMenu getAccessTypeCategoryKey() {
		return accessTypeCategoryKey;
	}

	public void setAccessTypeCategoryKey(HtmlSelectOneMenu accessTypeCategoryKey) {
		this.accessTypeCategoryKey = accessTypeCategoryKey;
	}

	// Method is invoked by hidden init-field in JSP.
	public String getInit() {
		initOrganization();
		return "";
	}

	public String actionCancel(){
		this.ipAddressFrom = "";
		this.ipAddressTo = "";
		this.ipAddressSingle = "";
		
		this.ipAddressFromUIInput = null;
		this.ipAddressToUIInput = null;
		this.ipAddressSingleUIInput = null;
		
		this.supplierSourceListValue = null;
		this.accessTypeCategoryKey = null;
		
		this.selectedOrganizationType = null;
		this.ipRangeList = null;
		this.ipRangeListHtmlDataTable = null;
		this.orgAccessTable = null;

		
//		this.organizationBean.setOrganization(this.organization);
		return this.organizationBean.actionDetailsSingle();
	}
	
	private void initOrganization() {
		if ( ! this.organizationBean.getIsNew()) {
			this.memberOrganization = organizationBean.getMemberOrganization();
		} else {
			this.memberOrganization = organizationBean.getMemberOrganization();
		}
		this.organization = this.memberOrganization.getOrganization();

		this.setIsNew(this.organizationBean.getIsNew());
		this.setNotNew( ! this.organizationBean.getIsNew());
		this.ipRangeList = this.organizationBean.getIpRangeList();
		
		this.ipAddressFrom = "";
		this.ipAddressTo="";
		this.ipAddressSingle="";
		
		if(this.organization.getType() != null &&
				this.organization.getType().getKey() != null){
			this.selectedOrganizationType = this.organization.getType().getKey().getValue();
		} else {
			this.selectedOrganizationType = null;
		}
	}
}
