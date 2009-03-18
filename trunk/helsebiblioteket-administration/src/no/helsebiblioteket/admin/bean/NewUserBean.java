package no.helsebiblioteket.admin.bean;

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
import no.helsebiblioteket.admin.domain.requestresult.ValueResultPosition;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NewUserBean {
    protected final Log logger = LogFactory.getLog(getClass());
    protected String firstname;
    protected String lastname;
    protected String emailaddress;
    protected String username;
    protected String password;
    
    protected UserBean userBean;
	protected UserService userService;
	protected OrganizationService organizationService;

    public String actionNewEndUser() {
		logger.info("method 'newEndUser' invoked");
		User user = new User();
		Role role = new Role();
		role.setKey(UserRoleKey.none);
		user.setRoleList(new Role[1]);
		user.getRoleList()[0] = role;
		user.setPerson(new Person());
		this.userBean.setUser(user);
		
		//return "create-enduser";
		// TODO: Ok to reuse beans etc?
		return "create-enduser";
	}
    public String actionNewAdministrator() {
		logger.info("method 'newEndUser' invoked");
		return "create-administrator";
	}

    public String actionCancelNewUser(){
    	return "users_overview";
    }
    public String actionSaveNewUser(User user) {
    	logger.info("method 'saveNewUser' invoked in new User Bean");

    	Person person = new Person();
    	person.setFirstName(this.firstname);
    	person.setLastName(this.lastname);
    	OrganizationType organizationType = ((ValueResultOrganizationType)this.organizationService.getOrganizationTypeByKey(
    			OrganizationTypeKey.health_enterprise)).getValue();
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
    	this.username = "";
    	this.password = "";
		return this.userBean.details();
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
}
