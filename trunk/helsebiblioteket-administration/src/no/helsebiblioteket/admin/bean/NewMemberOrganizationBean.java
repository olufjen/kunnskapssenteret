package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.daoobjects.OrganizationName;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpRange;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.requestresult.EmptyResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.OrganizationService;

public class NewMemberOrganizationBean extends NewOrganizationBean {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private OrganizationService organizationService = null;
	
	private Organization memberOrganization;

	private String ipAddressSingle = null;
	private String ipAddressFrom = null;
	private String ipAddressTo = null;
	private UIInput ipAddressSingleUIInput = null;
	private UIInput ipAddressFromUIInput = null;
	private UIInput ipAddressToUIInput = null;
	
	private String selectedOrganizationTypeId = null;
	
	private List<IpRange> ipRangeList = null;
	
	
	
	private List<String> selectedSourceList = null;
	private HtmlDataTable ipRangeListHtmlDataTable = null;
	

	
	
	
	public NewMemberOrganizationBean() {
	}
	
	
	
	
	
	
	
	
	public String getSelectedOrganizationTypeId() {
		return selectedOrganizationTypeId;
	}

	public void setSelectedOrganizationTypeId(String selectedOrganizationTypeId) {
		this.selectedOrganizationTypeId = selectedOrganizationTypeId;
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
	
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public Organization getMemberOrganization() {
		if (this.memberOrganization == null) {
			this.memberOrganization = new Organization();
		}
		if (this.memberOrganization.getContactInformation() == null) {
			this.memberOrganization.setContactInformation(new ContactInformation());
		}
		if (this.memberOrganization.getContactPerson() == null) {
			this.memberOrganization.setContactPerson(new Person());
		}
		if (this.memberOrganization.getContactPerson().getContactInformation() == null) {
			this.memberOrganization.getContactPerson().setContactInformation(new ContactInformation());
		}
		if (this.memberOrganization.getIpRangeList() == null) {
			this.memberOrganization.setIpRangeList(new ArrayList<IpRange>());
		}
		// TODO: Not use!
//		if(this.memberOrganization.getNameList() == null){
//			this.memberOrganization.setNameList(new ArrayList<OrganizationName>());
//		}
		return this.memberOrganization;
	}
	
	public void setOrganization(Organization memberOrganization) {
		this.memberOrganization = memberOrganization;
	}
	
	public List<IpRange> getIpRangeList() {
		return this.ipRangeList;
	}

	public HtmlDataTable getIpRangeListHtmlDataTable() {
		return this.ipRangeListHtmlDataTable;
	}

	public void setIpRangeListHtmlDataTable(HtmlDataTable ipRangeListHtmlDataTable) {
		this.ipRangeListHtmlDataTable = ipRangeListHtmlDataTable;
	}

	public void actionSaveOrganization() {
		logger.debug("Method 'actionSaveOrganization' invoked");
		// TODO: use same bindings as used in NewSupplierOrganizationBean
		// Requires all org props to be initiates first, see getSupplierOrganization()
		this.memberOrganization = new Organization();
//		private String organizationName;
//		this.memberOrganization.setName(this.getOrganizationName());
		ContactInformation contactInformationOrganization = new ContactInformation();
//		private String orgAddress;
		contactInformationOrganization.setPostalAddress(this.getOrgAddress());
//		private String orgPostalCode;
		contactInformationOrganization.setPostalCode(this.getOrgPostalCode());
//		private String orgPostalLocation;
		contactInformationOrganization.setPostalLocation(this.getOrgPostalLocation());
		ContactInformation contactInformationPerson = new ContactInformation();
		Person contactPerson = new Person();
//		private String contactPersonFirstName;
		contactPerson.setFirstName(this.getContactPersonFirstName());
//		private String contactPersonLastName;
		contactPerson.setLastName(this.getContactPersonLastName());
//		private String contactPersonTelephoneNumber;
		contactInformationPerson.setTelephoneNumber(this.getContactPersonTelephoneNumber());
//		private String contactPersonEmail;
		contactInformationPerson.setEmail(this.getContactPersonEmail());
		
		contactPerson.setContactInformation(contactInformationPerson);
		this.memberOrganization.setContactInformation(contactInformationOrganization);
		this.memberOrganization.setContactPerson(contactPerson);
		
		memberOrganization.setType(new OrganizationType(Integer.valueOf(selectedOrganizationTypeId)));
		this.organizationService.insertOrganization(this.memberOrganization);
	}
	
	public void actionAddSingleIp() {
		logger.debug("Method 'actionAddSingleIp' invoked");
		setIpAddressSingle((getIpAddressSingleUIInput().getSubmittedValue() != null) ? getIpAddressSingleUIInput().getSubmittedValue().toString() : null);
		if (this.ipRangeList == null) {
			this.ipRangeList = new ArrayList<IpRange>();
		}
		this.ipRangeList.add(new IpRange(new IpAddress(getIpAddressSingle()), null));
	}
	
	public void actionAddIpRange() {
		logger.debug("Method 'actionAddIpRange' invoked");
		setIpAddressFrom((getIpAddressFromUIInput().getSubmittedValue() != null) ? getIpAddressFromUIInput().getSubmittedValue().toString() : null);
		setIpAddressTo((getIpAddressToUIInput().getSubmittedValue() != null) ? getIpAddressToUIInput().getSubmittedValue().toString() : null);
		if (this.ipRangeList == null) {
			this.ipRangeList = new ArrayList<IpRange>();
		}
		this.ipRangeList.add(new IpRange(new IpAddress(getIpAddressFrom()), new IpAddress(getIpAddressTo())));
	}
	
	public void actionDeleteIpRange() {
		logger.debug("Method 'actionDeleteIpRange' invoked");
		//Integer rowIndex = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ipRangeDeleteTableRowIndex"); 
		this.ipRangeList.remove((IpRange) this.ipRangeListHtmlDataTable.getRowData());
	}
	
	public String actionNewMemberOrganization() {
		return "new_member_organization";
	}
	
	public String actionEditOrganization() {
		Integer orgId = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("organizationId");
		Organization organization = new Organization();
		organization.setId(orgId);
		SingleResult<Organization> result = organizationService.getOrganizationByListItem(organization);
		if(result instanceof ValueResult){
			this.memberOrganization = ((ValueResult<Organization>)result).getValue();
			//setContactPersonEmail(organization.getContactPerson())
		}
		return "new_member_organization";
	}
	
	public void addIpRangeActionListener(ActionEvent actionEvent) {
		logger.debug("Method 'addIpRangeActionListener' invoked");
		logger.debug("vce.getComponent().getId(): " + actionEvent.getComponent().getId());
	}
	
	public boolean isShowIpRangeList() {
		return (ipRangeList != null && ipRangeList.size() > 0) ? true : false;
	}
	
	public List<Organization> getSuppliersWithSourcesList() {
		// TODO fetch supplier organization list based on type
		return new ArrayList<Organization>();
	}
	
	public List<String> getSelectedSourceList() {
		List<String> list = new ArrayList<String>();
		return list;
	}
	
	public void setSelectedSourceList(List<String> list) { 
		this.selectedSourceList = list;
	}
}
