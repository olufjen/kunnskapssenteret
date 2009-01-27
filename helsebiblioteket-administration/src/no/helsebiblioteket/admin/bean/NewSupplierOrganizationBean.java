package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIInput;
import org.apache.myfaces.component.html.ext.HtmlDataTable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.daoobjects.OrganizationName;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Supplier;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.OrganizationService;

/**
 * 
 * @author Leif Torger Grøndahl, nokc.no
 * The class handles both to create new organizations of type "supplier" 
 * and editing the same type of organization. 
 */

public class NewSupplierOrganizationBean extends NewOrganizationBean {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private OrganizationService organizationService = null;
	
	private Supplier organization = null;
	
	private String sourceName = null;
	private String sourceUrl = null;
	private UIInput sourceNameUIInput = null;
	private UIInput sourceUrlUIInput = null;
	
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
	
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}

	public Organization getSupplierOrganization() {
		if (this.organization == null) {
			this.organization = new Supplier();
		}
		// TODO: Should not be nessecary
//		if (this.organization.getNameList() == null) {
//			this.organization.setNameList(new ArrayList<OrganizationName>());
//		}
		if (this.organization.getContactInformation() == null) {
			this.organization.setContactInformation(new ContactInformation());
		}
		if (this.organization.getContactPerson() == null) {
			this.organization.setContactPerson(new Person());
		}
		if (this.organization.getType() == null) {
			SingleResult<OrganizationType> res = organizationService.getOrganizationTypeByKey(OrganizationTypeKey.content_supplier);
			if(res instanceof ValueResult){
				this.organization.setType(((ValueResult<OrganizationType>)res).getValue());
			}
		}
//		if (this.organization.getSupplierSourceList() == null) {
//			this.organization.setSupplierSourceList(new ArrayList<SupplierSource>());
//		}
		return this.organization;
	}
	
	public void setOrganization(Supplier supplierOrganization) {
		this.organization = supplierOrganization;
	}

	public HtmlDataTable getSupplierSourceListHtmlDataTable() {
		return this.supplierSourceListHtmlDataTable;
	}
	
	public void setSupplierSourceListHtmlDataTable(HtmlDataTable supplierSourceListHtmlDataTable) {
		this.supplierSourceListHtmlDataTable = supplierSourceListHtmlDataTable;
	}

	public void actionSaveOrganization() {
		logger.debug("Method 'actionSaveOrganization' invoked");
		organizationService.insertOrganization(organization);
	}
	
	public void actionAddSupplierSource() {
		logger.debug("Method 'actionAddSupplierSource' invoked");
		setSourceName((getSourceNameUIInput().getSubmittedValue() != null) ? getSourceNameUIInput().getSubmittedValue().toString() : null);
		setSourceUrl((getSourceUrlUIInput().getSubmittedValue() != null) ? getSourceUrlUIInput().getSubmittedValue().toString() : null);
		this.organization.getSupplierSourceList().add(new SupplierSource(getSourceName(), new Url(getSourceUrl())));
	}
	
	public void actionDeleteSource() {
		logger.debug("Method 'actionDeleteSource' invoked");
		//Integer rowIndex = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ipRangeDeleteTableRowIndex"); 
		organization.getSupplierSourceList().remove((SupplierSource) this.supplierSourceListHtmlDataTable.getRowData());
	}

	public String actionNewSupplierOrganization() {
		return "new_supplier_organization";
	}

	public boolean isShowSourceList() {
		return (organization.getSupplierSourceList() != null && organization.getSupplierSourceList().size() > 0) ? true : false;
	}

	public void setSupplierOrganization(Supplier supplierOrganization) {
		this.organization = supplierOrganization;
	}
}
