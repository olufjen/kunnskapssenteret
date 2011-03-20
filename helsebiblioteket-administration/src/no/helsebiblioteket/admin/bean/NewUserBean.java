package no.helsebiblioteket.admin.bean;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationType;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.validator.EmailValidator;
import no.helsebiblioteket.admin.validator.PasswordValidator;
import no.helsebiblioteket.admin.validator.UsernameValidator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NewUserBean {
    protected final Log logger = LogFactory.getLog(getClass());
    protected String firstname;
    protected String lastname;
    protected String username;

    protected String emailaddress;
    protected String retypeemailaddress;
    protected String password;
    protected String retypePassword;
	
    protected User user;
    protected Role role;

	protected UserBean userBean;
	protected UserService userService;
	protected OrganizationService organizationService;

	private UIInput passwordRepeat;
	private UIInput passwordInput;

    public String actionNewEndUser() {
		logger.debug("method 'newEndUser' invoked");
		this.user = new User();
		this.user.setPerson(new Person());
		this.user.getPerson().getPosition().setKey(PositionTypeKey.none);
		this.role = new Role();
		role.setKey(UserRoleKey.none);
		this.user.setPerson(new Person());
		this.emailaddress = "";
		this.retypeemailaddress = "";
		this.password = "";
		this.retypePassword = "";
		return "create-enduser";
	}
    public String actionNewAdministrator() {
		logger.debug("method 'newEndUser' invoked");
		this.emailaddress = "";
		this.firstname = "";
		this.lastname = "";
		this.password = "";
		this.retypeemailaddress = "";
		this.retypePassword = "";
		this.username = "";
		return "create-administrator";
	}

    public String actionCancelNewUser(){
    	return "users_overview";
    }
    public String actionSaveNewEndUser() {
    	if(this.user.getPerson().getPosition() != null){
        	OrganizationType organizationType = ((ValueResultOrganizationType)this.organizationService.getOrganizationTypeByKey(
        			OrganizationTypeKey._health_enterprise)).getValue();
        	Position position = ((ValueResultPosition)this.userService.getPositionByKey(PositionTypeKey.none,
        			organizationType)).getValue();
        	this.user.getPerson().setPosition(position);
    	}
    	this.user.setPassword(this.password);
    	this.user.getPerson().getContactInformation().setEmail(this.emailaddress);
    	this.userService.insertUser(user);
    	Object userObject = this.userService.findUserByUsername(this.user.getUsername());
    	if (userObject instanceof ValueResultOrganizationUser) {
    		this.user = ((ValueResultOrganizationUser) userObject).getValue().getUser();
    	} else if (userObject instanceof ValueResultUser) {
    		this.user = ((ValueResultUser) userObject).getValue();
    	}
    	this.userBean.setUser(user);
    	return this.userBean.details();
    }
    public String actionSaveNewUser(User user) {
    	logger.debug("method 'saveNewUser' invoked in new User Bean");

    	Person person = new Person();
    	person.setFirstName(this.firstname);
    	person.setLastName(this.lastname);
    	OrganizationType organizationType = ((ValueResultOrganizationType)this.organizationService.getOrganizationTypeByKey(
    			OrganizationTypeKey._health_enterprise)).getValue();
    	Position position = ((ValueResultPosition)this.userService.getPositionByKey(PositionTypeKey.none,
    			organizationType)).getValue();
    	Profile profile = new Profile();
    	person.setPosition(position);
    	person.setProfile(profile);
    	ContactInformation contactInformation = new ContactInformation();
    	contactInformation.setEmail(this.emailaddress);
    	person.setContactInformation(contactInformation);
    	user.setPerson(person);
    	user.setUsername(this.username);
    	user.setPassword(this.password);

    	this.userService.insertUser(user);
    	
    	user = ((ValueResultUser)this.userService.findUserByUsername(user.getUsername())).getValue();
    	
    	this.userBean.setUser(user);
    	
    	this.firstname = "";
    	this.lastname = "";
    	this.emailaddress = "";
    	this.retypeemailaddress="";
    	this.username = "";
    	this.password = "";
		return this.userBean.details();
	}

    public void retypeValidate(FacesContext facesContext, UIComponent component, Object newValue) throws ValidatorException {
		String retypeemail = (String)newValue;
		UIInput emailComponent = (UIInput)component;
		String msg = "email_not_match";
		if (!retypeemail.equals(this.emailaddress) ) {
			emailComponent.setValid(false);
			ResourceBundle bundle = ResourceBundle.getBundle("no.helsebiblioteket.admin.web.jsf.messageresources.main", facesContext.getViewRoot().getLocale());
			FacesMessage message = new FacesMessage(bundle.getString(msg));
			facesContext.addMessage(component.getClientId(facesContext), message);
			throw new ValidatorException(message);
		}
	}
	
    public void validateEmail(FacesContext facesContext, UIComponent component, Object newValue) throws ValidatorException {
		String email = (String)newValue;
		this.setEmailaddress(email) ;
		UIInput emailComponent = (UIInput)component;
		this.logger.debug("email: " + email);
		String msg = "";
		boolean valid = true;
		if( ! EmailValidator.getInstance().isValidEmailAdress(email)) { msg = "email_not_valid"; valid = false; }
		if ( ! valid) {
			emailComponent.setValid(false);
			// TODO Fase2: Set with Spring
			ResourceBundle bundle = ResourceBundle.getBundle("no.helsebiblioteket.admin.web.jsf.messageresources.main", facesContext.getViewRoot().getLocale());
			FacesMessage message = new FacesMessage(bundle.getString(msg));
			facesContext.addMessage(component.getClientId(facesContext), message);
			throw new ValidatorException(message);
		}
	}
	
    public void validatePassword(FacesContext facesContext, UIComponent component, Object newValue) throws ValidatorException {
		String password = (String)newValue;
		this.setPassword(password);
		UIInput passwordComponent = (UIInput)component;
		if( ! PasswordValidator.getInstance().isValidPassword(password)) {
			ResourceBundle bundle = ResourceBundle.getBundle("no.helsebiblioteket.admin.web.jsf.messageresources.main", facesContext.getViewRoot().getLocale());
			passwordComponent.setValid(false);
			FacesMessage message = new FacesMessage(bundle.getString("password_not_valid"));
			facesContext.addMessage(component.getClientId(facesContext), message);
			throw new ValidatorException(message);
		}
	}
    public void validatePasswordRepeat(FacesContext facesContext, UIComponent component, Object newValue) throws ValidatorException {
		String retypePassword = (String)newValue;
		UIInput passwordComponent = (UIInput)component;
		if ( ! retypePassword.equals(this.password) ) {
			passwordComponent.setValid(false);
			ResourceBundle bundle = ResourceBundle.getBundle("no.helsebiblioteket.admin.web.jsf.messageresources.main", facesContext.getViewRoot().getLocale());
			FacesMessage message = new FacesMessage(bundle.getString("password_repeat_not_equal"));
			facesContext.addMessage(component.getClientId(facesContext), message);
			throw new ValidatorException(message);
		}
	}
    
    public void validateUsername(FacesContext facesContext, UIComponent component, Object newValue) throws ValidatorException {
    	String username = (String) newValue;
		UIInput input = (UIInput) component;
		List<UsernameValidator.ErrorCodes> usernameErrors = UsernameValidator.getInstance().validateAndGetErrorCodes(username, null);
		if (usernameErrors.size() > 0){
			input.setValid(false);
			ResourceBundle bundle = ResourceBundle.getBundle("no.helsebiblioteket.admin.web.jsf.messageresources.main", facesContext.getViewRoot().getLocale());
			FacesMessage message = new FacesMessage(bundle.getString("username_not_valid"));
			facesContext.addMessage(component.getClientId(facesContext), message);
			throw new ValidatorException(message);
		} else if(this.userService.usernameTaken(username, null)){
			input.setValid(false);
			ResourceBundle bundle = ResourceBundle.getBundle("no.helsebiblioteket.admin.web.jsf.messageresources.main", facesContext.getViewRoot().getLocale());
			FacesMessage message = new FacesMessage(bundle.getString("user_exists"));
			facesContext.addMessage(component.getClientId(facesContext), message);
			throw new ValidatorException(message);
		}
    }

    public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public String getRetypeemailaddress() {
		return retypeemailaddress;
	}
	public void setRetypeemailaddress(String retypeemailaddress) {
		this.retypeemailaddress = retypeemailaddress;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
    public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
    public String getRetypePassword() {
		return retypePassword;
	}
	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public UIInput getPasswordRepeat() {
		return passwordRepeat;
	}
	public void setPasswordRepeat(UIInput passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}
	public UIInput getPasswordInput() {
		return passwordInput;
	}
	public void setPasswordInput(UIInput passwordInput) {
		this.passwordInput = passwordInput;
	}
}
