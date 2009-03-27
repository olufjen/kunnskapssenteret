package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.IpAddress;
import no.helsebiblioteket.admin.domain.IpAddressRange;
import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
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

		// FIXME: Insert new and delete IP-addresses!
		this.memberOrganization.getIpAddressRangeList();
		this.memberOrganization.getIpAddressSingleList();

		if(this.isNew){
			this.organizationService.insertMemberOrganization(this.memberOrganization);
		} else {
			organizationService.updateOrganization(this.memberOrganization.getOrganization());
		}
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
			this.ipRangeList.add(new IpAddressRange(new IpAddress(getIpAddressSingle()), null));
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
	
	public List<Organization> getSuppliersWithSourcesList() {
		// TODO fetch supplier organization list based on type
		return new ArrayList<Organization>();
	}
	
	public List<String> getSelectedSourceList() {
		List<String> list = new ArrayList<String>();
		return list;
	}
	
	public void setSelectedSourceList(List<String> list) { 
//		this.selectedSourceList = list;
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
		}
		this.setIsNew(this.organizationBean.getIsNew());
		this.setNotNew( ! this.organizationBean.getIsNew());
		this.organization = this.memberOrganization.getOrganization();
	}
}
