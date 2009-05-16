package no.helsebiblioteket.admin.bean;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;

public class NewOrganizationBean {
	
	// TODO Fase2: Replace these with something else?
	private String organizationName;
	private String orgAddress;
	private String orgPostalCode;
	private String orgPostalLocation;
	private String contactPersonFirstName;
	private String contactPersonLastName;
	private String contactPersonTelephoneNumber;
	private String contactPersonEmail;
	private Boolean notNew;

	protected UIInput organizationNameNorwegianUIInput = null;
	protected UIInput organizationNameNorwegianShortUIInput = null;
	protected UIInput organizationNameEnglishUIInput = null;
	protected UIInput organizationNameEnglishShortUIInput = null;
	
	protected Boolean isNew;
	protected Organization organization;
	protected UserService userService;
	protected OrganizationService organizationService;
	protected OrganizationBean organizationBean;
	
	public Boolean getIsNew() {
		return isNew;
	}
	public void setIsNew(Boolean isNew) {
		this.isNew = isNew;
	}
	public Boolean getNotNew() {
		return notNew;
	}
	public void setNotNew(Boolean notNew) {
		this.notNew = notNew;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getOrgAddress() {
		return orgAddress;
	}
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	public String getOrgPostalCode() {
		return orgPostalCode;
	}
	public void setOrgPostalCode(String orgPostalCode) {
		this.orgPostalCode = orgPostalCode;
	}
	public String getOrgPostalLocation() {
		return orgPostalLocation;
	}
	public void setOrgPostalLocation(String orgPostalLocation) {
		this.orgPostalLocation = orgPostalLocation;
	}
	public String getContactPersonFirstName() {
		return contactPersonFirstName;
	}
	public void setContactPersonFirstName(String contactPersonFirstName) {
		this.contactPersonFirstName = contactPersonFirstName;
	}
	public String getContactPersonLastName() {
		return contactPersonLastName;
	}
	public void setContactPersonLastName(String contactPersonLastName) {
		this.contactPersonLastName = contactPersonLastName;
	}
	public String getContactPersonTelephoneNumber() {
		return contactPersonTelephoneNumber;
	}
	public void setContactPersonTelephoneNumber(String contactPersonTelephoneNumber) {
		this.contactPersonTelephoneNumber = contactPersonTelephoneNumber;
	}
	public String getContactPersonEmail() {
		return contactPersonEmail;
	}
	public void setContactPersonEmail(String contactPersonEmail) {
		this.contactPersonEmail = contactPersonEmail;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
	public void setOrganizationBean(OrganizationBean organizationBean) {
		this.organizationBean = organizationBean;
	}
	
	public void setOrganizationNameNorwegianUIInput(
			UIInput organizationNameNorwegianUIInput) {
		this.organizationNameNorwegianUIInput = organizationNameNorwegianUIInput;
	}

	public void setOrganizationNameNorwegianShortUIInput(
			UIInput organizationNameNorwegianShortUIInput) {
		this.organizationNameNorwegianShortUIInput = organizationNameNorwegianShortUIInput;
	}

	public void setOrganizationNameEnglishUIInput(
			UIInput organizationNameEnglishUIInput) {
		this.organizationNameEnglishUIInput = organizationNameEnglishUIInput;
	}

	public void setOrganizationNameEnglishShortUIInput(
			UIInput organizationNameEnglishShortUIInput) {
		this.organizationNameEnglishShortUIInput = organizationNameEnglishShortUIInput;
	}
	
	public UIInput getOrganizationNameNorwegianUIInput() {
		return organizationNameNorwegianUIInput;
	}

	public UIInput getOrganizationNameNorwegianShortUIInput() {
		return organizationNameNorwegianShortUIInput;
	}

	public UIInput getOrganizationNameEnglishUIInput() {
		return organizationNameEnglishUIInput;
	}

	public UIInput getOrganizationNameEnglishShortUIInput() {
		return organizationNameEnglishShortUIInput;
	}
	
	public boolean validateOrganizationNames() {
		String msg = "one_or_more_organization_names";
		boolean validation = true;
		if (organization != null && 
				(!hasValue(organization.getNameEnglish()) &&
					!hasValue(organization.getNameNorwegian()) &&
					!hasValue(organization.getNameShortEnglish()) &&
					!hasValue(organization.getNameShortNorwegian()))) {
			validation = false;
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ResourceBundle bundle = ResourceBundle.getBundle("no.helsebiblioteket.admin.web.jsf.messageresources.main", Locale.getDefault());
			FacesMessage message = new FacesMessage(bundle.getString(msg));	
			organizationNameEnglishShortUIInput.setValid(false);
			facesContext.addMessage(organizationNameEnglishShortUIInput.getClientId(facesContext), message);
			organizationNameEnglishUIInput.setValid(false);
			facesContext.addMessage(organizationNameEnglishUIInput.getClientId(facesContext), message);
			organizationNameNorwegianShortUIInput.setValid(false);
			facesContext.addMessage(organizationNameNorwegianShortUIInput.getClientId(facesContext), message);
			organizationNameNorwegianUIInput.setValid(false);
			facesContext.addMessage(organizationNameNorwegianUIInput.getClientId(facesContext), message);
		}
		return validation;
	}
	
	private boolean hasValue(String string) {
		return (string != null && !"".equals(string));
		
	}
}