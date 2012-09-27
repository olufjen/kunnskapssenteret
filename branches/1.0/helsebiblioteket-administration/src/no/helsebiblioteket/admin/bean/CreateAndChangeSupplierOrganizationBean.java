package no.helsebiblioteket.admin.bean;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultResourceType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSupplierOrganization;
import no.helsebiblioteket.admin.validator.EmailValidator;

/**
 * 
 * @author Leif Torger Grøndahl, nokc.no
 * The class handles both to create new organizations of type "supplier" 
 * and editing the same type of organization. 
 */

public class CreateAndChangeSupplierOrganizationBean extends NewOrganizationBean {
	protected final Log logger = LogFactory.getLog(getClass());
	private SupplierOrganization supplierOrganization = null;

	private String sourceName = null;
	private String sourceDomain = null;
	private String sourceProxyDatabaseName = null;
	private String sourceUrl = null;
	private String sourceHost = null;

	private UIInput sourceNameUIInput = null;
	private UIInput sourceProxyDatabaseNameUIInput = null;
	private UIInput sourceDomainUIInput = null;
	private UIInput sourceUrlUIInput = null;
	private UIInput sourceHostUIInput = null;
	
	private HtmlDataTable supplierSourceListHtmlDataTable = null;
	

	public CreateAndChangeSupplierOrganizationBean() {
		
	}
	
	
	public String actionSaveOrganization() {
		logger.debug("Method 'actionSaveOrganization' invoked");
		if (!validateOrganizationNames()) {
			return "create_change_supplier_organization";
		}
		if(this.isNew){
			Person contactPerson;
			contactPerson = this.supplierOrganization.getOrganization().getContactPerson();
			OrganizationType organizationType = ((ValueResultOrganizationType)this.organizationService.getOrganizationTypeByKey(
					OrganizationTypeKey.health_enterprise)).getValue();
			contactPerson.setPosition(((ValueResultPosition)this.userService.getPositionByKey(PositionTypeKey.none, organizationType)).getValue());
		} else {
		}

		ResourceType resourceType = ((ValueResultResourceType)this.organizationBean.getAccessService().getResourceTypeByKey(ResourceTypeKey.supplier_source)).getValue();
		for (SupplierSourceResource resource : this.supplierOrganization.getResourceList()) {
			resource.getResource().setResourceType(resourceType);
			resource.getResource().setOfferedBy(this.supplierOrganization.getOrganization().getId());
		}

		if (this.isNew) {
			this.supplierOrganization = ((ValueResultSupplierOrganization)
					organizationService.insertSupplierOrganization(supplierOrganization)).getValue();
		} else {
			organizationService.updateOrganization(supplierOrganization.getOrganization());
		}
		
		SupplierSourceResource[] resourceList = new SupplierSourceResource[this.organizationBean.deltetedResources.size()];
		int i=0;
		for (SupplierSourceResource resource : this.organizationBean.deltetedResources) {
			resourceList[i++] = resource;
		}
		
		this.setSourceName("");
		this.setSourceDomain("");
		this.setSourceUrl("");
		this.setSourceHost("");
		this.setSourceProxyDatabaseName("");
		
		this.organizationService.deleteResources(resourceList);
		this.organizationService.addResources(this.supplierOrganization.getResourceList());
		
		this.organizationBean.setOrganization(this.organization);
		this.organizationBean.runSearch();
		return this.organizationBean.actionDetailsSingle();
	}
	
	public void actionAddSupplierSource() {
		logger.debug("Method 'actionAddSupplierSource' invoked");
		setSourceName((getSourceNameUIInput().getSubmittedValue() != null) ? getSourceNameUIInput().getSubmittedValue().toString() : null);
		setSourceDomain((getSourceDomainUIInput().getSubmittedValue() != null) ? getSourceDomainUIInput().getSubmittedValue().toString() : null);
		setSourceProxyDatabaseName((getSourceProxyDatabaseNameUIInput().getSubmittedValue() != null) ? getSourceProxyDatabaseNameUIInput().getSubmittedValue().toString() : null);
		setSourceUrl((getSourceUrlUIInput().getSubmittedValue() != null) ? getSourceUrlUIInput().getSubmittedValue().toString() : null);
		setSourceHost((getSourceHostUIInput().getSubmittedValue() != null) ? getSourceHostUIInput().getSubmittedValue().toString() : null);
		if (! validateSupplierSource()) {
			return;
		}
		Url url = new Url();
		url.setDomain(getSourceDomain());
		SupplierSourceResource supplierSourceResource = new SupplierSourceResource();
		SupplierSource supplierSource = new SupplierSource(getSourceName(), url, getSourceProxyDatabaseName());
		supplierSourceResource.setSupplierSource(supplierSource);
		supplierSourceResource.setResource(new Resource());
		supplierSourceResource.getSupplierSource().getUrl().setStringValue(this.getSourceUrl());
		supplierSourceResource.getSupplierSource().setHost(this.getSourceHost());
//		supplierSourceResource.getResource().setId(-1);
		
		SupplierSourceResource[] newList = new SupplierSourceResource[this.supplierOrganization.getResourceList().length + 1];
		int i = 0;
		for (SupplierSourceResource resource : this.supplierOrganization.getResourceList()) {
			newList[i++] = resource;
		}
		newList[newList.length-1] = supplierSourceResource;
		this.supplierOrganization.setResourceList(newList);
		
		// TODO: Fase2: why are values not being reset?
		//this.setSourceName("");
		//this.setSourceUrl("");
		//this.setSourceProxyDatabaseName("");
		//getSourceNameUIInput().setValue("");
		//getSourceProxyDatabaseNameUIInput().setValue("");
		//getSourceUrlUIInput().setValue("");
	}
	
	public void actionDeleteSource() {
		logger.debug("Method 'actionDeleteSource' invoked");
		int index = this.supplierSourceListHtmlDataTable.getRowIndex();
		
		this.organizationBean.deltetedResources.add(this.supplierOrganization.getResourceList()[index]);
		
		SupplierSourceResource[] newList = new SupplierSourceResource[this.supplierOrganization.getResourceList().length - 1];
		int j=0; int i=0;
		for (SupplierSourceResource resource : this.supplierOrganization.getResourceList()) {
			if(j != index){ newList[i++] = resource; } j++;
		}
		this.supplierOrganization.setResourceList(newList);
		this.supplierSourceListHtmlDataTable = null;
	}

	public boolean isShowSourceList() {
		return (supplierOrganization.getResourceList().length > 0) ? true : false;
	}

	// Method is invoked by hidden init-field in JSP.
	public String getInit() {
		initOrganization();
		return "";
	}
	
	public String actionCancel(){
		setSourceName("");
		setSourceDomain("");
		setSourceProxyDatabaseName("");
		setSourceNameUIInput(null);
		setSourceDomainUIInput(null);
		setSourceProxyDatabaseNameUIInput(null);
		this.organizationBean.setOrganization(this.organization);
		return this.organizationBean.actionDetailsSingle();
	}

	private boolean validateSupplierSource() {
		String msg = "value_required";
		boolean validation = true;
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = ResourceBundle.getBundle("no.helsebiblioteket.admin.web.jsf.messageresources.main", Locale.getDefault());
		FacesMessage message = new FacesMessage(bundle.getString(msg));
		if (!hasValue(getSourceName())) {
			validation = false;
			sourceNameUIInput.setValid(false);
			facesContext.addMessage(sourceNameUIInput.getClientId(facesContext), message);
		}
		if (!hasValue(getSourceProxyDatabaseName())) {
			validation = false;
			sourceProxyDatabaseNameUIInput.setValid(false);
			facesContext.addMessage(sourceProxyDatabaseNameUIInput.getClientId(facesContext), message);
		}
		if (!hasValue(getSourceDomain())) {
			validation = false;
			sourceDomainUIInput.setValid(false);
			facesContext.addMessage(sourceDomainUIInput.getClientId(facesContext), message);
		}
		if (!hasValue(getSourceUrl())) {
			validation = false;
			sourceUrlUIInput.setValid(false);
			facesContext.addMessage(sourceUrlUIInput.getClientId(facesContext), message);
		}
		if (!hasValue(getSourceHost())) {
			validation = false;
			sourceHostUIInput.setValid(false);
			facesContext.addMessage(sourceHostUIInput.getClientId(facesContext), message);
		}
		return validation;
	}
	
	private boolean hasValue(String string) {
		return (string != null && !"".equals(string));
		
	}
	
	public void validateEmail(FacesContext facesContext, UIComponent component, Object newValue) throws ValidatorException {
		String email = (String)newValue;
		UIInput emailComponent = (UIInput)component;
		String msg = "";
		boolean valid = true;
		//if(email.length() == 0) { mes = "Email address is required."; valid = false; }
		if( ! EmailValidator.getInstance().isValidEmailAdress(email)) { msg = "email_not_valid"; valid = false; }
		if ( ! valid) {
			emailComponent.setValid(false);
			ResourceBundle bundle = ResourceBundle.getBundle("no.helsebiblioteket.admin.web.jsf.messageresources.main", Locale.getDefault() );
			FacesMessage message = new FacesMessage(bundle.getString(msg));
			facesContext.addMessage(component.getClientId(facesContext), message);
			throw new ValidatorException(message);
		}
	}
	
	private void initOrganization() {
		if ( ! this.organizationBean.getIsNew()) {
			this.supplierOrganization = this.organizationBean.getSupplierOrganization();
		} else {
			this.supplierOrganization = this.organizationBean.getSupplierOrganization();
		}
		this.setIsNew(this.organizationBean.getIsNew());
		this.setNotNew( ! this.organizationBean.getIsNew());
		this.organization = this.supplierOrganization.getOrganization();
		
		// TODO Fase2: remove
		if(this.supplierOrganization.getResourceList() == null){
			this.supplierOrganization.setResourceList(new SupplierSourceResource[0]);
		}
	}
	
	public UIInput getSourceNameUIInput() {
		return sourceNameUIInput;
	}
	public void setSourceNameUIInput(UIInput sourceNameUIInput) {
		this.sourceNameUIInput = sourceNameUIInput;
	}
	public void setSourceProxyDatabaseNameUIInput(UIInput sourceProxyDatabaseNameUIInput) {
		this.sourceProxyDatabaseNameUIInput = sourceProxyDatabaseNameUIInput;
	}
	public UIInput getSourceProxyDatabaseNameUIInput() {
		return sourceProxyDatabaseNameUIInput;
	}
	public UIInput getSourceDomainUIInput() {
		return sourceDomainUIInput;
	}
	public void setSourceDomainUIInput(UIInput sourceDomainUIInput) {
		this.sourceDomainUIInput = sourceDomainUIInput;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getSourceProxyDatabaseName() {
		return sourceProxyDatabaseName;
	}
	public void setSourceProxyDatabaseName(String sourceProxyDatabaseName) {
		this.sourceProxyDatabaseName = sourceProxyDatabaseName;
	}
	public String getSourceDomain() {
		return sourceDomain;
	}
	public void setSourceDomain(String sourceDomain) {
		this.sourceDomain = sourceDomain;
	}
	public SupplierOrganization getSupplierOrganization() {
		initOrganization();
		return this.supplierOrganization;
	}
	public HtmlDataTable getSupplierSourceListHtmlDataTable() {
		return this.supplierSourceListHtmlDataTable;
	}
	public void setSupplierSourceListHtmlDataTable(HtmlDataTable supplierSourceListHtmlDataTable) {
		this.supplierSourceListHtmlDataTable = supplierSourceListHtmlDataTable;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String getSourceHost() {
		return sourceHost;
	}
	public void setSourceHost(String sourceHost) {
		this.sourceHost = sourceHost;
	}
	public UIInput getSourceUrlUIInput() {
		return sourceUrlUIInput;
	}
	public void setSourceUrlUIInput(UIInput sourceUrlUIInput) {
		this.sourceUrlUIInput = sourceUrlUIInput;
	}
	public UIInput getSourceHostUIInput() {
		return sourceHostUIInput;
	}
	public void setSourceHostUIInput(UIInput sourceHostUIInput) {
		this.sourceHostUIInput = sourceHostUIInput;
	}
}