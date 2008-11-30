package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.UISelectMany;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.request.PageRequest;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;

public class UserBean {
	private UserService userService;
	private OrganizationService organizationService;
	private String searchinput;
	private List<SelectItem> availableRoles;
	private List<SelectItem> availablePositions;
	private List<SelectItem> availableEmployers;
	private List<String> selectedRoles;
	private List<String> selectedUserRoles;
	private List<Role> allRoles;
	private Map<String, Role> allRolesMap;
	// FIXME: Use caching?
	private List<User> users;
	private User user;
	private UISelectMany rolesManyCheckbox;
	private UISelectMany userRolesManyCheckbox;
	private HtmlDataTable usersTable;
    protected final Log logger = LogFactory.getLog(getClass());

    public boolean getCannotShowUser(){ return false; }
    public boolean getCanShowUser(){ return true; }
    public String actionEdit(){ this.user = (User) this.usersTable.getRowData(); return "user_edit"; }
    public String getUserRole(){
    	if(this.user != null && this.user.getRoleList().size()>0) {
    		return this.user.getRoleList().get(0).getRoleName();
    	} else {
    		return "MISSING_USER_ROLE";
    	}
    }
    public String actionSave(){ return "user_details"; }
    public String actionCancel(){ return "user_details"; }
    public String getErrorMsg() { return "ERRORS WILL BE PUT HERE!"; }
    public boolean getFailed() { return true; }
    public String actionDetails(){
//		FacesContext context = FacesContext.getCurrentInstance();
//		Map requestParams = context.getExternalContext().getRequestParameterMap();
//		logger.info("userId: " +
//				requestParams.get("userId"));
		this.user = (User) this.usersTable.getRowData();
		
		// FIXME: Remove testing.
		this.user.getPerson().setIsStudent(true);
		
		logger.info("USER: " + user.getPerson().getName());
		return "user_details";
	}
	public List<Role> getSelectedRolesRoleList(){
		List<Role> selectedRoles = new ArrayList<Role>();
		if(this.allRolesMap == null) {getAllRoles();}
		for (String string : this.getSelectedRoles()) {
			selectedRoles.add(this.allRolesMap.get(string));
		}
		return selectedRoles;
	}
	public String actionSearch(){
		logger.info("method 'search' invoked");
		for (String role : this.selectedRoles) {
			logger.info("SELECTED ROLE: " + role);
		}
		// FIXME: Handle paged result!
		this.users = this.userService.findUsersBySearchStringRoles(this.searchinput, this.getSelectedRolesRoleList(),
				new PageRequest<User>()).result;
		return "users_overview";
	}
	public List<SelectItem> getAvailableRoles() {
		if(this.availableRoles == null) {
			this.availableRoles = new ArrayList<SelectItem>();
			logger.info("STARTROLES");
			this.logger.info("this.userService=" + this.userService);
			for (Role role : this.getAllRoles()) {
				logger.info("role: " + role.getKey());
				SelectItem option = new SelectItem(role.getKey(), role.getRoleName(), "", false);
				this.availableRoles.add(option);
			}
			logger.info("DONEROLES");
			
		}
		return this.availableRoles;
	}
	public List<SelectItem> getAvailablePositions() {
		if(this.availablePositions == null) {
			this.availablePositions = new ArrayList<SelectItem>();
			for (Position position : this.userService.getAllUserPositions()) {
				SelectItem option = new SelectItem(position.getKey(), position.getTitle(), "", false);
				this.availablePositions.add(option);
			}
		}
		return this.availablePositions;
	}
	public List<SelectItem> getavailableEmployers() {
		if(this.availableEmployers == null) {
			this.availableEmployers = new ArrayList<SelectItem>();
			for (Organization organization : this.organizationService.getAllOrganizations()) {
				SelectItem option = new SelectItem(""+organization.getId(), organization.getName(), "", false);
				this.availableEmployers.add(option);
			}
		}
		return this.availableEmployers;
	}
	public List<String> getSelectedRoles() {
		// FIXME: All must be selected!
		if(this.selectedRoles == null){
			this.selectedRoles = new ArrayList<String>();
			for (Role role : this.getAllRoles()) {
				this.selectedRoles.add(role.getKey());
			}
		}
		return this.selectedRoles;
	}
	public List<String> getSelectedUserRoles() {
		List<String> selectedRoles = new ArrayList<String>();
		if(this.user != null){ 
			for (Role role : this.user.getRoleList()) {
				selectedRoles.add(role.getKey());
			}
		}
		return selectedRoles;
	}
	public List<Role> getAllRoles() {
		if(this.allRoles == null){
			this.allRoles = this.userService.getAllUserRoles();
			this.allRolesMap = new HashMap<String, Role>();
			for (Role role : this.allRoles) {
				this.allRolesMap.put(role.getKey(), role);
			}
		}
		return this.allRoles;
	}
	public List<User> getUsers() {
		// FIXME: Handle paged result!
		if(this.users == null) { this.users = userService.getAllUsers(new PageRequest<User>()).result; }
		return this.users;
	}
	public void setUserService(UserService userService) { this.userService = userService; }
	public void setOrganizationService(OrganizationService organizationService) { this.organizationService = organizationService; }
	public HtmlDataTable getUsersTable() { return usersTable; }
	public void setUsersTable(HtmlDataTable usersTable) { this.usersTable = usersTable; }
	public UISelectMany getRolesManyCheckbox() { return rolesManyCheckbox; }
	public void setRolesManyCheckbox(UISelectMany rolesManyCheckbox) { this.rolesManyCheckbox = rolesManyCheckbox; }
	public UISelectMany getUserRolesManyCheckbox() { return userRolesManyCheckbox; }
	public void setUserRolesManyCheckbox(UISelectMany userRolesManyCheckbox) { this.userRolesManyCheckbox = userRolesManyCheckbox; }
	public void setSelectedRoles(List<String> selectedRoles) { this.selectedRoles = selectedRoles; }
	public void setSelectedUserRoles(List<String> selectedUserRoles) { this.selectedUserRoles = selectedUserRoles; }
	public String getSearchinput() { return searchinput; }
	public void setSearchinput(String searchinput) { this.searchinput = searchinput; }
	public User getUser() { return user; }
	
}
