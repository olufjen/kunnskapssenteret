package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UISelectOne;
import javax.faces.model.SelectItem;

import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.System;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSystem;
import no.helsebiblioteket.admin.requestresult.ValueResult;
import no.helsebiblioteket.admin.service.UserService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NewEndUserBean extends NewUserBean {
    protected final Log logger = LogFactory.getLog(getClass());
	private UserService userService;

	private String firstname;
    private String lastname;
    private String studentHprNo;
    private String employer;
    private boolean newsletter;
    private boolean questionnaire;
    private String emailaddress;
    private String username;
    private String password;

    private String selectedUserRole;
	private UISelectOne userRolesSelectOne;
	private List<SelectItem> availableRoles;
	private List<Role> allRoles;
	private Map<UserRoleKey, Role> allRolesMap;

	public List<SelectItem> getAvailableRoles() {
		if(this.availableRoles == null) {
			this.availableRoles = new ArrayList<SelectItem>();
			for (Role role : this.getAllRoles()) {
				SelectItem option = new SelectItem(role.getKey(), role.getName(), "", false);
				this.availableRoles.add(option);
			}
		}
		return this.availableRoles;
	}
	public List<Role> getAllRoles() {
		if(this.allRoles == null){
			Role[] roles = this.userService.getRoleListBySystem(
					((ValueResultSystem)
							this.userService.getSystemByKey(SystemKey.helsebiblioteket_admin)).getValue()).getList();
			this.allRoles = new ArrayList<Role>();
			this.allRolesMap = new HashMap<UserRoleKey, Role>();
			for (Role role : roles) {
				this.allRoles.add(role);
				this.allRolesMap.put(role.getKey(), role);
			}
		}
		return this.allRoles;
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
	public String getStudentHprNo() {
		return studentHprNo;
	}
	public void setStudentHprNo(String studentHprNo) {
		this.studentHprNo = studentHprNo;
	}
	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public boolean isNewsletter() {
		return newsletter;
	}
	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}
	public boolean isQuestionnaire() {
		return questionnaire;
	}
	public void setQuestionnaire(boolean questionnaire) {
		this.questionnaire = questionnaire;
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
	public String getSelectedUserRole() {
		return selectedUserRole;
	}
	public void setSelectedUserRole(String selectedUserRole) {
		this.selectedUserRole = selectedUserRole;
	}
	public UISelectOne getUserRolesSelectOne() {
		return userRolesSelectOne;
	}
	public void setUserRolesSelectOne(UISelectOne userRolesSelectOne) {
		this.userRolesSelectOne = userRolesSelectOne;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}