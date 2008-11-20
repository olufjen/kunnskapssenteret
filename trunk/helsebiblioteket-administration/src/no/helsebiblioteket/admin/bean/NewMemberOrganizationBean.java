package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlDataTable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Contract;
import no.helsebiblioteket.admin.domain.IpRange;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.service.OrganizationService;

public class NewMemberOrganizationBean {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private Person loggedInUser = null;
	private OrganizationService organizationService = null;
	
	private Organization organization = null;
	private String organizationName = null;
	private String ipAddressSingle = null;
	private String ipAddressFrom = null;
	private String ipAddressTo = null;
	private UIInput ipAddressSingleUIInput = null;
	private UIInput ipAddressFromUIInput = null;
	private UIInput ipAddressToUIInput = null;
	private OrganizationType organizationType = null;
	private String selectedOrganizationTypeId = null;
	private List<IpRange> ipRangeList = null;
	private List<Contract> contractsForOrganizationType = null;
	
	private HtmlDataTable ipRangeListHtmlDataTable = null;
	
	private List<Contract> choosenContracts = null;

	public NewMemberOrganizationBean() {
		
	}
	
	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
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

	public void setLoggedInUser(Person person) {
		this.loggedInUser = person;
	}
	
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public Organization getOrganization() {
		return this.organization;
	}
	
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	public OrganizationType getOrganizationType() {
		return this.organizationType;
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
	}
	
	public void actionAddSingleIp() {
		logger.debug("Method 'actionAddSingleIp' invoked");
		setIpAddressSingle((getIpAddressSingleUIInput().getSubmittedValue() != null) ? getIpAddressSingleUIInput().getSubmittedValue().toString() : null);
		if (this.ipRangeList == null) {
			this.ipRangeList = new ArrayList<IpRange>();
		}
		this.ipRangeList.add(new IpRange(getIpAddressSingle(), null));
	}
	
	public void actionAddIpRange() {
		logger.debug("Method 'actionAddIpRange' invoked");
		setIpAddressFrom((getIpAddressFromUIInput().getSubmittedValue() != null) ? getIpAddressFromUIInput().getSubmittedValue().toString() : null);
		setIpAddressTo((getIpAddressToUIInput().getSubmittedValue() != null) ? getIpAddressToUIInput().getSubmittedValue().toString() : null);
		if (this.ipRangeList == null) {
			this.ipRangeList = new ArrayList<IpRange>();
		}
		this.ipRangeList.add(new IpRange(getIpAddressFrom(), getIpAddressTo()));
	}
	
	public void actionDeleteIpRange() {
		logger.debug("Method 'actionDeleteIpRange' invoked");
		//Integer rowIndex = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ipRangeDeleteTableRowIndex"); 
		this.ipRangeList.remove((IpRange) this.ipRangeListHtmlDataTable.getRowData());
	}
	
	public String actionNewMemberOrganization() {
		return "new_member_organization";
	}
	
	public void addIpRangeActionListener(ActionEvent actionEvent) {
		logger.debug("Method 'addIpRangeActionListener' invoked");
		logger.debug("vce.getComponent().getId(): " + actionEvent.getComponent().getId());
	}
	
	public boolean isShowIpRangeList() {
		return (ipRangeList != null && ipRangeList.size() > 0) ? true : false;
	}
	
	
	public List<SupplierOrganization> getSuppliersWithSourcesList() {
		return organizationService.getSupplierList();
	}
}