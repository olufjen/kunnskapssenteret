package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIInput;
import org.apache.myfaces.component.html.ext.HtmlDataTable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.service.OrganizationService;

/**
 * 
 * @author Leif Torger Grøndahl, nokc.no
 * The class handles both creating of new organization if type "supplier" 
 * and editing the same type of organization. 
 */

public class NewSupplierOrganizationBean extends NewOrganizationBean {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private OrganizationService organizationService = null;
	
	private SupplierOrganization supplierOrganization = null;
	
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
		if (this.supplierOrganization == null) {
			this.supplierOrganization = new SupplierOrganization();
		}
		if (this.supplierOrganization.getNameList() == null) {
			this.supplierOrganization.setNameList(new ArrayList<OrganizationName>());
		}
		if (this.supplierOrganization.getContactInformation() == null) {
			this.supplierOrganization.setContactInformation(new ContactInformation());
		}
		if (this.supplierOrganization.getContactPerson() == null) {
			this.supplierOrganization.setContactPerson(new Person());
		}
		if (this.supplierOrganization.getType() == null) {
			this.supplierOrganization.setType(organizationService.getOrganizationTypeByKey(OrganizationTypeKey.content_supplier.toString()));
		}
		if (this.supplierOrganization.getSourceList() == null) {
			this.supplierOrganization.setSourceList(new ArrayList<SupplierSource>());
		}
		return this.supplierOrganization;
	}
	
	public void setOrganization(SupplierOrganization supplierOrganization) {
		this.supplierOrganization = supplierOrganization;
	}

	public HtmlDataTable getSupplierSourceListHtmlDataTable() {
		return this.supplierSourceListHtmlDataTable;
	}
	
	public void setSupplierSourceListHtmlDataTable(HtmlDataTable supplierSourceListHtmlDataTable) {
		this.supplierSourceListHtmlDataTable = supplierSourceListHtmlDataTable;
	}

	public void actionSaveOrganization() {
		logger.debug("Method 'actionSaveOrganization' invoked");
		organizationService.saveOrganization(supplierOrganization);
	}
	
	public void actionAddSupplierSource() {
		logger.debug("Method 'actionAddSupplierSource' invoked");
		setSourceName((getSourceNameUIInput().getSubmittedValue() != null) ? getSourceNameUIInput().getSubmittedValue().toString() : null);
		setSourceUrl((getSourceUrlUIInput().getSubmittedValue() != null) ? getSourceUrlUIInput().getSubmittedValue().toString() : null);
		this.supplierOrganization.getSourceList().add(new SupplierSource(getSourceName(), getSourceUrl()));
	}
	
	public void actionDeleteSource() {
		logger.debug("Method 'actionDeleteSource' invoked");
		//Integer rowIndex = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ipRangeDeleteTableRowIndex"); 
		supplierOrganization.getSourceList().remove((SupplierSource) this.supplierSourceListHtmlDataTable.getRowData());
	}

	public String actionNewSupplierOrganization() {
		return "new_supplier_organization";
	}

	public boolean isShowSourceList() {
		return (supplierOrganization.getSourceList() != null && supplierOrganization.getSourceList().size() > 0) ? true : false;
	}

	public void setSupplierOrganization(SupplierOrganization supplierOrganization) {
		this.supplierOrganization = supplierOrganization;
	}
}