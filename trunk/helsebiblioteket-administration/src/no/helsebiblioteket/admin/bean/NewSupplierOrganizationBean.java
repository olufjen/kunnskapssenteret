package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIInput;
import org.apache.myfaces.component.html.ext.HtmlDataTable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.service.OrganizationService;


public class NewSupplierOrganizationBean {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private Person loggedInUser = null;
	private OrganizationService organizationService = null;
	
	private Organization organization = null;
	private String organizationName = null;
	
	private String sourceName = null;
	private String sourceUrl = null;
	private UIInput sourceNameUIInput = null;
	private UIInput sourceUrlUIInput = null;
	
	private OrganizationType organizationType = null;
	private String selectedOrganizationTypeId = null;
	
	private List<SupplierSource> supplierSourceList = null;
	
	private HtmlDataTable supplierSourceListHtmlDataTable = null;
	

	public NewSupplierOrganizationBean() {
		
	}
	
	public UIInput getSourceNameUIInput() {
		return sourceNameUIInput;
	}

	public void setSourceNameUIInput(UIInput sourceNameUIInput) {
		this.sourceNameUIInput = sourceNameUIInput;
	}

	public UIInput getSourceUrlUIInput() {
		return sourceUrlUIInput;
	}

	public void setSourceUrlUIInput(UIInput sourceUrlUIInput) {
		this.sourceUrlUIInput = sourceUrlUIInput;
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

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
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
	
	public List<SupplierSource> getSupplierSourceList() {
		return this.supplierSourceList;
	}

	public HtmlDataTable getSupplierSourceListHtmlDataTable() {
		return this.supplierSourceListHtmlDataTable;
	}

	public void setSupplierSourceListHtmlDataTable(HtmlDataTable ipRangeListHtmlDataTable) {
		this.supplierSourceListHtmlDataTable = ipRangeListHtmlDataTable;
	}

	public void actionSaveOrganization() {
		logger.debug("Method 'actionSaveOrganization' invoked");
	}
	
	public void actionAddSupplierSource() {
		logger.debug("Method 'actionAddSupplierSource' invoked");
		setSourceName((getSourceNameUIInput().getSubmittedValue() != null) ? getSourceNameUIInput().getSubmittedValue().toString() : null);
		setSourceUrl((getSourceUrlUIInput().getSubmittedValue() != null) ? getSourceUrlUIInput().getSubmittedValue().toString() : null);
		if (this.supplierSourceList == null) {
			this.supplierSourceList = new ArrayList<SupplierSource>();
		}
		this.supplierSourceList.add(new SupplierSource(getSourceName(), getSourceUrl()));
	}
	
	public void actionDeleteSource() {
		logger.debug("Method 'actionDeleteIpRange' invoked");
		//Integer rowIndex = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ipRangeDeleteTableRowIndex"); 
		this.supplierSourceList.remove((SupplierSource) this.supplierSourceListHtmlDataTable.getRowData());
	}

	public String actionNewSupplierOrganization() {
		return "new_supplier_organization";
	}

	public boolean isShowSourceList() {
		return (supplierSourceList != null && supplierSourceList.size() > 0) ? true : false;
	}	
	
	public List<SupplierOrganization> getSuppliersWithSourcesList() {
		return organizationService.getSupplierList();
	}
}