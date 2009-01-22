package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.component.UIData;
import javax.faces.component.UISelectMany;
import javax.faces.component.UISelectOne;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Profile;
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
	private List<SelectItem> availableIsStudent;
	private List<String> selectedRoles;
	private String selectedUserRole;
	private String selectedIsStudent;

	private List<Role> allRoles;
	private Map<String, Role> allRolesMap;
	private List<Position> allPositions;
	private Map<String, Position> allPositionsMap;
	// FIXME: Use caching?
	private List<User> users;
	private User user;
	
	private boolean showHprNumber;
	private boolean showEmployerNumber;
	private boolean showIsStudent;
	private boolean showEmployerText;
	private boolean showPositionText;
	private boolean showPositionMenu;
	private boolean showProfile;

	private UISelectMany rolesManyCheckbox;
	private UISelectOne userRolesSelectOne;
	private UISelectOne isStudentSelectOne;
	private UIData usersTable;
    protected final Log logger = LogFactory.getLog(getClass());

    private boolean isNew(){ return this.user.getId() == null; }
    // TODO: Place in User class?
    private Role mainRole(){ return this.user.getRoleList().get(0); }
    private void mainRole(Role role){ this.user.setRoleList(new ArrayList<Role>()); this.user.getRoleList().add(role); }
    
    public void initSelectedIsStudent(){
//		if(this.availableIsStudent == null){
		this.availableIsStudent = new ArrayList<SelectItem>();
    	if(this.mainRole().getKey().equals("ADM") | 
    			this.mainRole().getKey().equals("ANST") |
    			this.mainRole().getKey().equals("HPNR")){
			this.availableIsStudent.add(new SelectItem("U", "None"));
    		this.selectedIsStudent = "U";
    		if(this.isStudentSelectOne != null) { this.isStudentSelectOne.setValue(this.selectedIsStudent); }
    	} else if(this.mainRole().getKey().equals("STUD")){
			this.availableIsStudent.add(new SelectItem("Y", "Student"));
			this.availableIsStudent.add(new SelectItem("N", "Employee"));
			this.selectedIsStudent = this.user.getPerson().getIsStudent() ? "Y" : "N";
			if(this.isStudentSelectOne != null) { this.isStudentSelectOne.setValue(this.selectedIsStudent); }
    	}
//		}

		logger.info("this.user.getRole().getKey()=" + this.mainRole().getKey());
		logger.info("this.selectedIsStudent=" + this.selectedIsStudent);
		logger.info("this.isStudentSelectOne=" + this.isStudentSelectOne);
		

    }
    public void enableDisableFields(){
    	// TODO: Is this done correctly?
    	this.initSelectedIsStudent();
    	if(this.mainRole().getKey().equals("ADM")){
        	this.showHprNumber = false;
        	this.user.getPerson().setHprNumber(null);
        	this.showEmployerNumber = false;
    		this.user.getPerson().setStudentNumber(null);
    		this.showIsStudent = false;
        	this.showEmployerText = false;
    		this.user.getPerson().setEmployer("");
        	this.showPositionText = false;
        	this.showPositionMenu = false;
    		this.user.getPerson().setPosition(new Position());
    		this.showProfile = false;
    		this.user.getPerson().setProfile(new Profile());
    	} else if(this.mainRole().getKey().equals("ANST")){
    		this.showHprNumber = false;
    		this.user.getPerson().setHprNumber(null);
        	this.showEmployerNumber = false;
    		this.user.getPerson().setStudentNumber(null);
    		this.showIsStudent = false;
        	this.showEmployerText = true;
        	this.showPositionText = true;
        	this.showPositionMenu = false;
    		this.user.getPerson().setPosition(new Position());
    		this.showProfile = true;
    	} else if(this.mainRole().getKey().equals("HPNR")){
    		this.showHprNumber = true;
        	this.showEmployerNumber = false;
    		this.user.getPerson().setStudentNumber(null);
    		this.showIsStudent = false;
        	this.showEmployerText = true;
        	this.showPositionText = false;
        	this.showPositionMenu = true;
    		this.showProfile = true;
    	} else if(this.mainRole().getKey().equals("STUD")){
    		this.showHprNumber = false;
    		this.user.getPerson().setHprNumber(null);
        	this.showEmployerNumber = true;
    		this.showIsStudent = true;
        	this.showEmployerText = true;
        	this.showPositionText = false;
        	this.showPositionMenu = false;
    		this.user.getPerson().setPosition(new Position());
    		this.showProfile = true;
    	}
    }
    public void roleChanged(ValueChangeEvent event){
    	// FIXME: Really use submit and this action for enable and disable fields?
    	logger.info("OLD: " + event.getOldValue());
    	logger.info("NEW: " + event.getNewValue());
    	if(this.allRolesMap.containsKey(event.getNewValue())){
    		this.mainRole(this.allRolesMap.get(event.getNewValue()));
    	}
    	this.enableDisableFields();
    }
    public void studentChanged(ValueChangeEvent event){
    	if("Y".equals(event.getNewValue())){ this.user.getPerson().setIsStudent(true); }
    	if("N".equals(event.getNewValue())){ this.user.getPerson().setIsStudent(false); }
    	this.enableDisableFields();
    }
    public boolean getCannotShowUser(){ return false; }
    public boolean getCanShowUser(){ return true; }
    public String actionEdit(){ this.user = (User) this.usersTable.getRowData(); this.enableDisableFields(); return "user_edit"; }
    public String actionEditSingle(){ return "user_edit"; }
    public String getUserRole(){
    	if(this.user != null && this.mainRole() != null) {
    		return this.mainRole().getName();
    	} else {
    		return "MISSING_USER_ROLE";
    	}
    }
    public String actionSave(){
    	this.mainRole(this.allRolesMap.get(this.selectedUserRole));
    	if(this.mainRole().getKey().equals("ADM")){
        	this.user.getPerson().setHprNumber(null);
    		this.user.getPerson().setStudentNumber(null);
    		this.user.getPerson().setIsStudent(false);
    		this.user.getPerson().setEmployer("");
    		this.user.getPerson().setPosition(new Position());
    		this.user.getPerson().setProfile(new Profile());
    	} else if(this.mainRole().getKey().equals("ANST")){
        	this.user.getPerson().setHprNumber(null);
    		this.user.getPerson().setStudentNumber(null);
    		this.user.getPerson().setIsStudent(false);
    	} else if(this.mainRole().getKey().equals("HPNR")){
    		this.user.getPerson().setStudentNumber(null);
    		this.user.getPerson().setIsStudent(false);
    		this.user.getPerson().setPosition(this.allPositionsMap.get(this.user.getPerson().getPosition().getKey()));
    	} else if(this.mainRole().getKey().equals("STUD")){
        	this.user.getPerson().setHprNumber(null);
        	this.user.getPerson().setIsStudent(this.selectedIsStudent.equals("Y"));
    		this.user.getPerson().setPosition(new Position());
    	}
    	if(this.isNew()){
    		this.userService.createUser(this.user);
    	} else {
    		this.userService.saveUser(this.user);
    	}
    	return "user_details";
    }
    public String actionCancel(){
    	this.user = this.userService.findUserByUsername(this.user);
    	return "user_details";
    }
    public String getErrorMsg() { return "ERRORS WILL BE PUT HERE!"; }
    public boolean getFailed() { return true; }
    public String details(){
		logger.info("USER: " + user.getPerson().getName());
		return "user_details";
    }
    public String actionDetails(){
//		FacesContext context = FacesContext.getCurrentInstance();
//		Map requestParams = context.getExternalContext().getRequestParameterMap();
//		logger.info("userId: " +
//				requestParams.get("userId"));
		this.user = (User) this.usersTable.getRowData();
		
		// FIXME: Remove testing.
		this.user.getPerson().setIsStudent(true);
		return details();
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
	public List<SelectItem> getAvailableIsStudent() {
		this.initSelectedIsStudent();
		return availableIsStudent;
	}

	public List<SelectItem> getAvailableRoles() {
		if(this.availableRoles == null) {
			this.availableRoles = new ArrayList<SelectItem>();
			logger.info("STARTROLES");
			this.logger.info("this.userService=" + this.userService);
			for (Role role : this.getAllRoles()) {
				logger.info("role: " + role.getKey());
				SelectItem option = new SelectItem(role.getKey(), role.getName(), "", false);
				this.availableRoles.add(option);
			}
			logger.info("DONEROLES");
			
		}
		return this.availableRoles;
	}
	public List<SelectItem> getAvailablePositions() {
		if(this.availablePositions == null) {
			this.availablePositions = new ArrayList<SelectItem>();
			for (Position position : getAllPositions()) {
				SelectItem option = new SelectItem(position.getKey(), position.getName(), "", false);
				this.availablePositions.add(option);
			}
		}
		return this.availablePositions;
	}
	public List<SelectItem> getavailableEmployers() {
		if(this.availableEmployers == null) {
			this.availableEmployers = new ArrayList<SelectItem>();
			Organization dummy = new Organization();
			dummy.setId(-999);
			dummy.setName(ResourceBundle.getBundle(
					"no.helsebiblioteket.admin.web.jsf.messageresources.main", Locale.getDefault()).getString(
							"user_details_no_employer"));
			SelectItem dummyOption = new SelectItem(""+dummy.getId(), dummy.getName(), "", false);
			this.availableEmployers.add(dummyOption);
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
	public String getSelectedUserRole() {
		if(this.selectedUserRole == null){
//			this.selectedUserRole = new ArrayList<String>();
			if(this.user != null){ 
//				for (Role role : this.user.getRoleList()) {
				this.selectedUserRole = this.mainRole().getKey();
//				}
			}
		}
		return this.selectedUserRole;
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
	public List<Position> getAllPositions() {
		if(this.allPositions == null){
			this.allPositions = new ArrayList<Position>();

			// TODO: Right way to add DUMMY?
			Position dummy = new Position();
			dummy.setKey("-999");
			dummy.setName(ResourceBundle.getBundle(
					"no.helsebiblioteket.admin.web.jsf.messageresources.main", Locale.getDefault()).getString(
							"user_details_no_position"));
			this.allPositions.add(dummy);
			
			this.allPositions.addAll(this.userService.getAllUserPositions());
			this.allPositionsMap = new HashMap<String, Position>();
			for (Position position : this.allPositions) {
				this.allPositionsMap.put(position.getKey(), position);
			}
		}
		return this.allPositions;
	}
	public List<User> getUsers() {
		// FIXME: Handle paged result!
		if(this.users == null) { this.users = userService.getAllUsers(new PageRequest<User>()).result; }
		return this.users;
	}
	public String getSelectedIsStudent() {
		initSelectedIsStudent();
		return selectedIsStudent;
	}
	public void setUserService(UserService userService) { this.userService = userService; }
	public void setOrganizationService(OrganizationService organizationService) { this.organizationService = organizationService; }
	public UIData getUsersTable() { return usersTable; }
	public void setUsersTable(UIData usersTable) { this.usersTable = usersTable; }
	public UISelectMany getRolesManyCheckbox() { return rolesManyCheckbox; }
	public void setRolesManyCheckbox(UISelectMany rolesManyCheckbox) { this.rolesManyCheckbox = rolesManyCheckbox; }
	public UISelectOne getUserRolesSelectOne() { return userRolesSelectOne; }
	public void setUserRolesSelectOne(UISelectOne userRolesSelectOne) { this.userRolesSelectOne = userRolesSelectOne; }
	public UISelectOne getIsStudentSelectOne() { return isStudentSelectOne; }
	public void setIsStudentSelectOne(UISelectOne isStudentSelectOne) { this.isStudentSelectOne = isStudentSelectOne; }
	public void setSelectedRoles(List<String> selectedRoles) { this.selectedRoles = selectedRoles; }
	public void setSelectedUserRole(String selectedUserRole) { this.selectedUserRole = selectedUserRole; }
	public String getSearchinput() { return searchinput; }
	public void setSearchinput(String searchinput) { this.searchinput = searchinput; }
	public User getUser() { return user; }
	public boolean isShowPositionText() { return showPositionText; }
	public boolean isShowPositionMenu() { return showPositionMenu; }
	public boolean isShowIsStudent() { return showIsStudent; }
	public boolean isShowEmployerText() { return showEmployerText; }
	public void setSelectedIsStudent(String selectedIsStudent) { this.selectedIsStudent = selectedIsStudent; }
	public boolean isShowHprNumber() { return showHprNumber; }
	public boolean isShowEmployerNumber() { return showEmployerNumber; }
	public boolean isShowProfile() { return showProfile; }
	public void setUser(User user) { this.user = user; }
}
