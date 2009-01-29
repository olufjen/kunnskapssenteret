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

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.requestresult.EmptyResult;
import no.helsebiblioteket.admin.requestresult.FirstPageRequest;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.requestresult.PageResult;
import no.helsebiblioteket.admin.requestresult.SingleResult;
import no.helsebiblioteket.admin.requestresult.ValueResult;
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
	private List<UserRoleKey> selectedRoles;
	private UserRoleKey selectedUserRole;
	private String selectedIsStudent;

	private List<Role> allRoles;
	private Map<UserRoleKey, Role> allRolesMap;
	private List<Position> allPositions;
	private Map<PositionTypeKey, Position> allPositionsMap;
	// FIXME: Use caching?
	private List<UserListItem> users;
	private User user;
	
	private boolean showHprNumber;
	private boolean showEmployerNumber;
	private boolean showIsStudent;
	private boolean showEmployerText;
	private boolean showPositionText;
	private boolean showPositionMenu;
	private boolean showProfile;
	
	// TODO: Move to domain model!
	private String roleKeyStudent = "student";
	private String roleKeyEmployee = "health_personnel_other";
	private String roleKeyHealthWorker = "health_personnel";
	private String roleKeyAdministrator = "administrator";

	private UISelectMany rolesManyCheckbox;
	private UISelectOne userRolesSelectOne;
	private UISelectOne isStudentSelectOne;
	private UIData usersTable;
    protected final Log logger = LogFactory.getLog(getClass());

    private boolean isNew(){ return this.user.getId() == null; }
	// TODO: Now fetching main role with: user.roleList[0].name
    // TODO: Place in User class?
    private Role mainRole(){ return this.user.getRoleList().get(0); }
    private void mainRole(Role role){ this.user.setRoleList(new ArrayList<Role>()); this.user.getRoleList().add(role); }
    
    public void initSelectedIsStudent(){
//		if(this.availableIsStudent == null){
		this.availableIsStudent = new ArrayList<SelectItem>();
    	if(this.mainRole().getKey().equals(roleKeyAdministrator) | 
    			this.mainRole().getKey().equals(roleKeyEmployee) |
    			this.mainRole().getKey().equals(roleKeyHealthWorker)){
			this.availableIsStudent.add(new SelectItem("U", "None"));
    		this.selectedIsStudent = "U";
    		if(this.isStudentSelectOne != null) { this.isStudentSelectOne.setValue(this.selectedIsStudent); }
    	} else if(this.mainRole().getKey().equals(roleKeyStudent)){
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
    	if(this.mainRole().getKey().equals(roleKeyAdministrator)){
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
    	} else if(this.mainRole().getKey().equals(roleKeyEmployee)){
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
    	} else if(this.mainRole().getKey().equals(roleKeyHealthWorker)){
    		this.showHprNumber = true;
        	this.showEmployerNumber = false;
    		this.user.getPerson().setStudentNumber(null);
    		this.showIsStudent = false;
        	this.showEmployerText = true;
        	this.showPositionText = false;
        	this.showPositionMenu = true;
    		this.showProfile = true;
    	} else if(this.mainRole().getKey().equals(roleKeyStudent)){
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
    	if(this.mainRole().getKey().equals(roleKeyAdministrator)){
        	this.user.getPerson().setHprNumber(null);
    		this.user.getPerson().setStudentNumber(null);
    		this.user.getPerson().setIsStudent(false);
    		this.user.getPerson().setEmployer("");
    		this.user.getPerson().setPosition(new Position());
    		this.user.getPerson().setProfile(new Profile());
    	} else if(this.mainRole().getKey().equals(roleKeyEmployee)){
        	this.user.getPerson().setHprNumber(null);
    		this.user.getPerson().setStudentNumber(null);
    		this.user.getPerson().setIsStudent(false);
    	} else if(this.mainRole().getKey().equals(roleKeyHealthWorker)){
    		this.user.getPerson().setStudentNumber(null);
    		this.user.getPerson().setIsStudent(false);
    		this.user.getPerson().setPosition(this.allPositionsMap.get(this.user.getPerson().getPosition().getKey()));
    	} else if(this.mainRole().getKey().equals(roleKeyStudent)){
        	this.user.getPerson().setHprNumber(null);
        	this.user.getPerson().setIsStudent(this.selectedIsStudent.equals("Y"));
    		this.user.getPerson().setPosition(new Position());
    	}
    	if(this.isNew()){
    		this.userService.insertUser(this.user);
    	} else {
    		this.userService.updateUser(this.user);
    	}
    	return "user_details";
    }
    public String actionCancel(){
    	SingleResult<User> result = this.userService.findUserByUsername(this.user.getUsername());
    	if(result instanceof ValueResult){
        	this.user = ((ValueResult<User>)result).getValue();
    	} else {
    		this.user = null;
    	}
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
		for (UserRoleKey string : this.getSelectedRoles()) {
			selectedRoles.add(this.allRolesMap.get(string));
		}
		return selectedRoles;
	}
	public String actionSearch(){
		logger.info("method 'search' invoked");
		for (UserRoleKey role : this.selectedRoles) {
			logger.info("SELECTED ROLE: " + role);
		}
		// FIXME: Handle paged result!
		this.users = this.userService.findUsersBySearchStringRoles(this.searchinput, this.getSelectedRolesRoleList(),
				new FirstPageRequest<UserListItem>(Integer.MAX_VALUE)).result;
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
			MemberOrganization dummy = new MemberOrganization();
			dummy.setId(-999);
			String name = ResourceBundle.getBundle(
					"no.helsebiblioteket.admin.web.jsf.messageresources.main", Locale.getDefault()).getString(
					"user_details_no_employer");
			// TODO: How to find right name here?
			dummy.setNameEnglish(name);
			dummy.setNameShortEnglish(name);
			dummy.setNameNorwegian(name);
			dummy.setNameShortNorwegian(name);
			SelectItem dummyOption = new SelectItem(""+dummy.getId(), dummy.getNameEnglish(), "", false);
			this.availableEmployers.add(dummyOption);
			PageRequest<OrganizationListItem> request = new FirstPageRequest<OrganizationListItem>(Integer.MAX_VALUE);
			PageResult<OrganizationListItem> orgs = this.organizationService.getOrganizationListAll(request);
			for (OrganizationListItem organization : orgs.result) {
				// TODO: How to find right name here?
				SelectItem option = new SelectItem(""+organization.getId(), organization.getNameEnglish(), "", false);
				this.availableEmployers.add(option);
			}
		}
		return this.availableEmployers;
	}
	public List<UserRoleKey> getSelectedRoles() {
		// FIXME: All must be selected!
		if(this.selectedRoles == null){
			this.selectedRoles = new ArrayList<UserRoleKey>();
			for (Role role : this.getAllRoles()) {
				this.selectedRoles.add(role.getKey());
			}
		}
		return this.selectedRoles;
	}
	public UserRoleKey getSelectedUserRole() {
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
			Role[] roles = this.userService.getRoleListBySystem(
					this.userService.getSystemByKey(SystemKey.helsebiblioteket_admin)).getList();
			this.allRoles = new ArrayList<Role>();
			this.allRolesMap = new HashMap<UserRoleKey, Role>();
			for (Role role : roles) {
				this.allRoles.add(role);
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
			dummy.setKey(PositionTypeKey.none);
			dummy.setName(ResourceBundle.getBundle(
					"no.helsebiblioteket.admin.web.jsf.messageresources.main", Locale.getDefault()).getString(
							"user_details_no_position"));
			this.allPositions.add(dummy);
			
			Position[] positions = this.userService.getPositionListAll("").getList();
			for (Position loadedPos : positions) {
				this.allPositions.add(loadedPos);
			}
			this.allPositionsMap = new HashMap<PositionTypeKey, Position>();
			for (Position position : this.allPositions) {
				this.allPositionsMap.put(position.getKey(), position);
			}
		}
		return this.allPositions;
	}
	public List<UserListItem> getUsers() {
		// FIXME: Handle paged result!
		if(this.users == null) { this.users = userService.getUserListAll(new FirstPageRequest<no.helsebiblioteket.admin.domain.list.UserListItem>(Integer.MAX_VALUE)).result; }
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
	public void setSelectedRoles(List<UserRoleKey> selectedRoles) { this.selectedRoles = selectedRoles; }
	public void setSelectedUserRole(UserRoleKey selectedUserRole) { this.selectedUserRole = selectedUserRole; }
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
