package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;

import no.helsebiblioteket.admin.domain.ContactInformation;
import no.helsebiblioteket.admin.domain.Person;
import no.helsebiblioteket.admin.domain.UserRole;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.service.UserService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NewUserBean {
    protected final Log logger = LogFactory.getLog(getClass());
    private String firstname;
    private String lastname;
    private String emailaddress;
    private String username;
    private String password;
    private UserBean userBean;
    private UserService userService;
    public String actionNewEndUser() {
		logger.info("method 'newEndUser' invoked");
		User user = new User();
		UserRole role = new UserRole();
		role.setKey(UserRoleKey.none);
		user.setRoleList(new ArrayList<UserRole>());
		user.getRoleList().add(role);
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
    public String actionSaveNewUser() {
    	logger.info("method 'saveNewUser' invoked in new User Bean");
    	User user = new User();
    	Person person = new Person();
    	person.setFirstName(this.firstname);
    	person.setLastName(this.lastname);
    	ContactInformation contactInformation = new ContactInformation();
    	contactInformation.setEmail(this.emailaddress);
    	person.setContactInformation(contactInformation);
    	user.setPerson(person);
    	user.setUsername(this.username);
    	user.setPassword(this.password);
    	// TODO: Set admin role differently?
//    	Role role = new Role();
//    	role.setKey("ADM");
//    	user.getRoleList().add((this.userService.getRoleByKey(role)));
    	this.userService.insertUser(user);
    	
    	// FIXME: Reload
//    	user = this.userService.findUserByUsername(user);
    	
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
}
