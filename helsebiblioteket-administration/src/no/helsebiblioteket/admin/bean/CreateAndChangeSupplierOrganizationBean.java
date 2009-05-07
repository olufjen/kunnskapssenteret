package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;
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
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganization;
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
	private String sourceUrl = null;
	private UIInput sourceNameUIInput = null;
	private UIInput sourceUrlUIInput = null;
	
	private HtmlDataTable supplierSourceListHtmlDataTable = null;
	

	public CreateAndChangeSupplierOrganizationBean() {
		
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
	
	public String actionSaveOrganization() {
		logger.debug("Method 'actionSaveOrganization' invoked");
		ContactInformation contactInformationOrganization ;
		ContactInformation contactInformationPerson;
		Profile contactPersonProfile;
		if(this.isNew){
			Person contactPerson;
			contactPerson = this.supplierOrganization.getOrganization().getContactPerson();
			OrganizationType organizationType = ((ValueResultOrganizationType)this.organizationService.getOrganizationTypeByKey(
					OrganizationTypeKey.health_enterprise)).getValue();
			contactPerson.setPosition(((ValueResultPosition)this.userService.getPositionByKey(PositionTypeKey.none, organizationType)).getValue());
		} else {
			contactInformationOrganization = this.supplierOrganization.getOrganization().getContactInformation();
			contactInformationPerson = this.supplierOrganization.getOrganization().getContactPerson().getContactInformation();
			contactPersonProfile = this.supplierOrganization.getOrganization().getContactPerson().getProfile();
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
		
		this.sourceName = "";
		this.sourceUrl = "";
		
		this.organizationService.deleteResources(resourceList);
		this.organizationService.addResources(this.supplierOrganization.getResourceList());
		
		this.organizationBean.setOrganization(this.organization);
		this.organizationBean.runSearch();
		return this.organizationBean.actionDetailsSingle();
	}
	
	public void actionAddSupplierSource() {
		logger.debug("Method 'actionAddSupplierSource' invoked");
		setSourceName((getSourceNameUIInput().getSubmittedValue() != null) ? getSourceNameUIInput().getSubmittedValue().toString() : null);
		setSourceUrl((getSourceUrlUIInput().getSubmittedValue() != null) ? getSourceUrlUIInput().getSubmittedValue().toString() : null);
		SupplierSourceResource supplierSourceResource = new SupplierSourceResource();
		supplierSourceResource.setSupplierSource(new SupplierSource(getSourceName(), new Url(getSourceUrl())));
		supplierSourceResource.setResource(new Resource());
//		supplierSourceResource.getResource().setId(-1);
		
		SupplierSourceResource[] newList = new SupplierSourceResource[this.supplierOrganization.getResourceList().length + 1];
		int i = 0;
		for (SupplierSourceResource resource : this.supplierOrganization.getResourceList()) {
			newList[i++] = resource;
		}
		newList[newList.length-1] = supplierSourceResource;

		this.supplierOrganization.setResourceList(newList);
		
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
		this.sourceName = "";
		this.sourceUrl = "";
		this.sourceNameUIInput = null;
		this.sourceUrlUIInput = null;
		this.organizationBean.setOrganization(this.organization);
		return this.organizationBean.actionDetailsSingle();
	}

	public void validateEmail(FacesContext facesContext, UIComponent component, Object newValue) throws ValidatorException {

		String email = (String)newValue;
		UIInput emailComponent = (UIInput)component;
		this.logger.debug("email: " + email);
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
		
		// TODO: remove:
		if(this.supplierOrganization.getResourceList() == null){
			this.supplierOrganization.setResourceList(new SupplierSourceResource[0]);
		}
	}
}
