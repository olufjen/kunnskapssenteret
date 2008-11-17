package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectItem;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;

public class NewOrganizationBean {
	protected final Log logger = LogFactory.getLog(getClass());
	
	private Organization organization = null;
	private Person loggedInUser = null;
	
	public NewOrganizationBean() {
		this.organization = new Organization();
		this.organization.setType(new OrganizationType());
	}
	
	public void setLoggedInUser(Person person) {
		this.loggedInUser = person;
	}
	
	public Organization getOrganization() {
		return this.organization;
	}
	
	public void save() {
		logger.debug("Method 'save' invoked");
	}
}