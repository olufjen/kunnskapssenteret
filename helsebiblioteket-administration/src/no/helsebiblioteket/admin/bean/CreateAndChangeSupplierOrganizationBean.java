package no.helsebiblioteket.admin.bean;

import javax.faces.component.UIInput;
import org.apache.myfaces.component.html.ext.HtmlDataTable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;

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
	
//	private List<SupplierSource> supplierSourceList = null;
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
		
		ContactInformation contactInformationOrganization;
		Person contactPerson;
		ContactInformation contactInformationPerson;
		Profile contactPersonProfile;
		if(this.isNew){
			contactInformationOrganization = new ContactInformation();
			this.supplierOrganization.getOrganization().setContactInformation(contactInformationOrganization);

			contactPerson = new Person();
			OrganizationType organizationType = ((ValueResultOrganizationType)this.organizationService.getOrganizationTypeByKey(
					OrganizationTypeKey.health_enterprise)).getValue();
			contactPerson.setPosition(((ValueResultPosition)this.userService.getPositionByKey(PositionTypeKey.none, organizationType)).getValue());
			this.supplierOrganization.getOrganization().setContactPerson(contactPerson);
			
			contactInformationPerson = new ContactInformation();
			contactPerson.setContactInformation(contactInformationPerson);
			
			contactPersonProfile = new Profile();
			contactPerson.setProfile(contactPersonProfile);
		} else {
			contactInformationOrganization = this.supplierOrganization.getOrganization().getContactInformation();
			contactPerson = this.supplierOrganization.getOrganization().getContactPerson();
			contactInformationPerson = this.supplierOrganization.getOrganization().getContactPerson().getContactInformation();
			contactPersonProfile = this.supplierOrganization.getOrganization().getContactPerson().getProfile();
		}
		
		// FIXME: update resource list!

		
		if (this.isNew) {
			organizationService.insertSupplierOrganization(supplierOrganization);
		} else {
			organizationService.updateOrganization(supplierOrganization.getOrganization());
			supplierOrganization.getResourceList();
		}
		this.organizationBean.setOrganization(this.organization);
		return this.organizationBean.actionDetailsSingle();
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

	public boolean isShowSourceList() {
		// TODO: Do with services!
		return false;//(supplierOrganization.getSupplierSourceList() != null && supplierOrganization.getSupplierSourceList().size() > 0) ? true : false;
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
			this.supplierOrganization = this.organizationBean.getSupplierOrganization();
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
		this.setIsNew(this.organizationBean.getIsNew());
		this.setNotNew( ! this.organizationBean.getIsNew());
		this.organization = this.supplierOrganization.getOrganization();
	}
}
