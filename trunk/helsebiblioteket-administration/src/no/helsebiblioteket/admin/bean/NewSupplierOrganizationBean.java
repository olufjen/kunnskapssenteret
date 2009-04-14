package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIInput;
import org.apache.myfaces.component.html.ext.HtmlDataTable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationName;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
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
	
	private SupplierOrganization organization = null;
	
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

	public SupplierOrganization getSupplierOrganization() {
		if (this.organization == null) {
			this.organization = new SupplierOrganization();
		}
		// TODO: Should not be necessary
//		if (this.organization.getNameList() == null) {
//			this.organization.setNameList(new ArrayList<OrganizationName>());
//		}
		if (this.organization.getOrganization().getContactInformation() == null) {
			this.organization.getOrganization().setContactInformation(new ContactInformation());
		}
		if (this.organization.getOrganization().getContactPerson() == null) {
			this.organization.getOrganization().setContactPerson(new Person());
		}
		if (this.organization.getOrganization().getType() == null) {
			// TODO: FIX!
			SingleResultOrganizationType res = null;//organizationService.getOrganizationTypeByKey(OrganizationTypeKey.content_supplier);
			if(res instanceof ValueResultOrganizationType){
				this.organization.getOrganization().setType(((ValueResultOrganizationType)res).getValue());
			}
		}
//		if (this.organization.getSupplierSourceList() == null) {
//			this.organization.setSupplierSourceList(new ArrayList<SupplierSource>());
//		}
		return this.organization;
	}
	
	public void setOrganization(SupplierOrganization supplierOrganization) {
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
		organizationService.insertSupplierOrganization(organization);
	}
	
	public void actionAddSupplierSource() {
		logger.debug("Method 'actionAddSupplierSource' invoked");
		setSourceName((getSourceNameUIInput().getSubmittedValue() != null) ? getSourceNameUIInput().getSubmittedValue().toString() : null);
		setSourceUrl((getSourceUrlUIInput().getSubmittedValue() != null) ? getSourceUrlUIInput().getSubmittedValue().toString() : null);
		SupplierSource supplierSource = new SupplierSource(getSourceName(), new Url(getSourceUrl()));
		SupplierSourceResource resource = new SupplierSourceResource();
		resource.setSupplierSource(supplierSource);
		// TODO: Use services to add!
		// FIXME: Re-insert:
//		this.organization.getResourceList().add(resource);
	}
	
	public void actionDeleteSource() {
		logger.debug("Method 'actionDeleteSource' invoked");
		//Integer rowIndex = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ipRangeDeleteTableRowIndex"); 
		// TODO: Use services to remove!
		// FIXME: Re-insert
//		organization.getResourceList().remove((SupplierSource) this.supplierSourceListHtmlDataTable.getRowData());
	}

	public String actionNewSupplierOrganization() {
		return "new_supplier_organization";
	}

	public boolean isShowSourceList() {
		// FIXME: Re-insert:
		return false;
//		return (organization.getResourceList() != null && organization.getResourceList().size() > 0) ? true : false;
	}

	public void setSupplierOrganization(SupplierOrganization supplierOrganization) {
		this.organization = supplierOrganization;
	}
}
