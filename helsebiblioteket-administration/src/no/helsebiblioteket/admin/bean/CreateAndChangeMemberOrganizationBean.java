package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressRange;
import no.helsebiblioteket.admin.domain.IpAddressSet;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.UnknownOrganization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganization;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.OrganizationService;

public class CreateAndChangeMemberOrganizationBean extends NewOrganizationBean {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private OrganizationService organizationService = null;
	
	private MemberOrganization memberOrganization;

	private String ipAddressSingle = null;
	private String ipAddressFrom = null;
	private String ipAddressTo = null;
	private UIInput ipAddressSingleUIInput = null;
	private UIInput ipAddressFromUIInput = null;
	private UIInput ipAddressToUIInput = null;
	private OrganizationBean organizationBean = null;
	
	private String selectedOrganizationTypeId = null;
	
	private List<IpAddressRange> ipRangeList = null;
	
	private List<String> selectedSourceList = null;
	private HtmlDataTable ipRangeListHtmlDataTable = null;
	
	private BeanMode beanMode;
	
	public CreateAndChangeMemberOrganizationBean() {
		this.beanMode = BeanMode.change;
	}

	// Method is invoked by hidden init-field in JSP.
	public String getInit() {
		initOrganization();
		return null;
	}
	
	public void setOrganizationBean(OrganizationBean organizationBean) {
		this.organizationBean = organizationBean;
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

	public MemberOrganization getOrganization() {
		return this.memberOrganization;
	}
	
	public void setOrganization(MemberOrganization organization) {
		this.memberOrganization = organization;
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

	public void actionSaveOrganization() {
		logger.debug("Method 'actionSaveOrganization' invoked");
		// TODO: use same bindings as used in NewSupplierOrganizationBean
		// Requires all org props to be initiates first, see getSupplierOrganization()
		this.memberOrganization = new MemberOrganization();
//		private String organizationName;
		//this.organization.setName(this.getOrganizationName());
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
		this.memberOrganization.getOrganization().setContactInformation(contactInformationOrganization);
		this.memberOrganization.getOrganization().setContactPerson(contactPerson);
		
		memberOrganization.getOrganization().setType(new OrganizationType(Integer.valueOf(selectedOrganizationTypeId)));
		if (this.memberOrganization.getOrganization().getId() == null) {
			this.organizationService.insertOrganization(this.memberOrganization.getOrganization());
			// FIXME: Insert new IP-addresses!
			this.memberOrganization.getIpAddressRangeList();
			this.memberOrganization.getIpAddressSingleList();
			
		} else {
			// FIXME: Insert new and delete IP-addresses!
			organizationService.updateOrganization(this.memberOrganization.getOrganization());
			this.memberOrganization.getIpAddressRangeList();
			this.memberOrganization.getIpAddressSingleList();
		}
	}
	
	public void actionAddSingleIp() {
		logger.debug("Method 'actionAddSingleIp' invoked");
		setIpAddressSingle((getIpAddressSingleUIInput().getSubmittedValue() != null) ? getIpAddressSingleUIInput().getSubmittedValue().toString() : null);
		if (this.ipRangeList == null) {
			this.ipRangeList = new ArrayList<IpAddressRange>();
		}
		this.ipRangeList.add(new IpAddressRange(new IpAddress(getIpAddressSingle()), null));
	}
	
	public void actionAddIpRange() {
		logger.debug("Method 'actionAddIpRange' invoked");
		setIpAddressFrom((getIpAddressFromUIInput().getSubmittedValue() != null) ? getIpAddressFromUIInput().getSubmittedValue().toString() : null);
		setIpAddressTo((getIpAddressToUIInput().getSubmittedValue() != null) ? getIpAddressToUIInput().getSubmittedValue().toString() : null);
		if (this.ipRangeList == null) {
			this.ipRangeList = new ArrayList<IpAddressRange>();
		}
		this.ipRangeList.add(new IpAddressRange(new IpAddress(getIpAddressFrom()), new IpAddress(getIpAddressTo())));
	}
	
	public void actionDeleteIpRange() {
		logger.debug("Method 'actionDeleteIpRange' invoked");
		//Integer rowIndex = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ipRangeDeleteTableRowIndex"); 
		this.ipRangeList.remove((IpAddressRange) this.ipRangeListHtmlDataTable.getRowData());
	}
	
	public String actionNewMemberOrganization() {
		beanMode = BeanMode.create;
		return "create_change_member_organization";
	}
	
	public String actionEditOrganization() {
		Integer orgId = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("organizationId");
		OrganizationListItem organizationLookup = new OrganizationListItem();
		organizationLookup.setId(orgId);
		
		SingleResultOrganization result = organizationService.getOrganizationByListItem(organizationLookup);
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
	
	private void initOrganization() {
		if (this.organizationBean != null && this.organizationBean.getOrganization() != null && this.organizationBean.getOrganization().getId() != null) {
			// TODO: Re-insert:
			this.memberOrganization = null;//(MemberOrganization) organizationBean.getOrganization();
			this.organizationBean.setOrganization(null);
			return;
		}
		this.memberOrganization = new MemberOrganization();
		this.memberOrganization.getOrganization().setContactInformation(new ContactInformation());
		this.memberOrganization.getOrganization().setContactPerson(new Person());
		this.memberOrganization.getOrganization().getContactPerson().setContactInformation(new ContactInformation());
		
		// TODO: Re-insert
//		this.memberOrganization.setIpAddressSetList(new ArrayList<IpAddressSet>());
		//this.organization.setNameList(new ArrayList<OrganizationName>());
	}
}