package no.helsebiblioteket.admin.bean;

import javax.faces.component.UIInput;
import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Person;
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

public class CreateAndChangeSupplierOrganizationBean extends NewOrganizationBean {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private OrganizationService organizationService = null;
	
	private SupplierOrganization supplierOrganization = null;
	
	private String sourceName = null;
	private String sourceUrl = null;
	private UIInput sourceNameUIInput = null;
	private UIInput sourceUrlUIInput = null;
	
//	private List<SupplierSource> supplierSourceList = null;
	private HtmlDataTable supplierSourceListHtmlDataTable = null;
	
	private OrganizationBean organizationBean = null;

	public CreateAndChangeSupplierOrganizationBean() {
	}
	
	// Method is invoked by hidden init-field in JSP.
	public String getInit() {
		initOrganization();
		return "";
	}

	public void setOrganizationBean(OrganizationBean organizationBean) {
		this.organizationBean = organizationBean;
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
		initOrganization();
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

	public String actionSaveOrganization() {
		logger.debug("Method 'actionSaveOrganization' invoked");
		if (supplierOrganization.getOrganization().getId() == null) {
			organizationService.insertSupplierOrganization(supplierOrganization);
		} else {
			organizationService.updateOrganization(supplierOrganization.getOrganization());
			// FIXME: update resource list!
			supplierOrganization.getResourceList();
		}
		return "organizations_overview";
	}
	
	public void actionAddSupplierSource() {
		logger.debug("Method 'actionAddSupplierSource' invoked");
		setSourceName((getSourceNameUIInput().getSubmittedValue() != null) ? getSourceNameUIInput().getSubmittedValue().toString() : null);
		setSourceUrl((getSourceUrlUIInput().getSubmittedValue() != null) ? getSourceUrlUIInput().getSubmittedValue().toString() : null);
		SupplierSourceResource supplierSourceResource = new SupplierSourceResource();
		supplierSourceResource.setSupplierSource(new SupplierSource(getSourceName(), new Url(getSourceUrl())));
		// TODO: Do with services!
		// FIXME: Re-insert
//		this.supplierOrganization.getResourceList().add(supplierSourceResource);
	}
	
	public void actionDeleteSource() {
		logger.debug("Method 'actionDeleteSource' invoked");
		//Integer rowIndex = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("ipRangeDeleteTableRowIndex"); 
		// TODO: Do with services!
		// FIXME: Re-insert
//		supplierOrganization.getResourceList().remove((SupplierSourceResource) this.supplierSourceListHtmlDataTable.getRowData());
	}

	public String actionNewSupplierOrganization() {
		return "create_change_supplier_organization";
	}

	public boolean isShowSourceList() {
		// TODO: Do with services!
		return false;//(supplierOrganization.getSupplierSourceList() != null && supplierOrganization.getSupplierSourceList().size() > 0) ? true : false;
	}
	
	private void initOrganization() {
		if (this.organizationBean != null && this.organizationBean.getOrganization() != null && this.organizationBean.getOrganization().getId() != null) {
			this.supplierOrganization = organizationBean.getSupplierOrganization();
		} else {
			this.supplierOrganization = new SupplierOrganization();
			this.supplierOrganization.getOrganization().setContactInformation(new ContactInformation());
			this.supplierOrganization.getOrganization().setContactPerson(new Person());
			this.supplierOrganization.setResourceList(new SupplierSourceResource[0]);
			SingleResultOrganizationType result = organizationService.getOrganizationTypeByKey(OrganizationTypeKey.content_supplier);
	    	if(result instanceof ValueResultOrganizationType){
				this.supplierOrganization.getOrganization().setType(((ValueResultOrganizationType) result).getValue());
			}
		}
		this.organization = this.supplierOrganization.getOrganization();
	}
}
