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
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.admin.requestresult.PageRequest;
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
	private UserRoleKey selectedUserRole;
	private String selectedIsStudent;

	private List<Role> allRoles;
	private Map<String, Role> allRolesMap;
	private List<Position> allPositions;
	private Map<String, Position> allPositionsMap;
	// FIXME: Use caching?
	private UserListItem[] users;
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
    private Role mainRole(){ return this.user.getRoleList()[0]; }
    private void mainRole(Role role){ this.user.setRoleList(new Role[1]); this.user.getRoleList()[0]=role; }
    
    public void initSelectedIsStudent(){
//		if(this.availableIsStudent == null){
		this.availableIsStudent = new ArrayList<SelectItem>();
    	if(this.mainRole().getKey().equals(roleKeyAdministrator) | 
    			this.mainRole().getKey().getValue().equals(roleKeyEmployee) |
    			this.mainRole().getKey().getValue().equals(roleKeyHealthWorker)){
			this.availableIsStudent.add(new SelectItem("U", "None"));
    		this.selectedIsStudent = "U";
    		if(this.isStudentSelectOne != null) { this.isStudentSelectOne.setValue(this.selectedIsStudent); }
    	} else if(this.mainRole().getKey().getValue().equals(roleKeyStudent)){
			this.availableIsStudent.add(new SelectItem("Y", "Student"));
			this.availableIsStudent.add(new SelectItem("N", "Employee"));
			this.selectedIsStudent = "N";
			if(this.isStudentSelectOne != null) { this.isStudentSelectOne.setValue(this.selectedIsStudent); }
    	} else {
    		// TODO: What then?
    		this.availableIsStudent.add(new SelectItem("U", "None"));
    		this.selectedIsStudent = "";
    	}
//		}

		logger.info("this.user.getRole().getKey()=" + this.mainRole().getKey().getValue());
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
//    	if("Y".equals(event.getNewValue())){ this.user.getPerson().setIsStudent(true); }
//    	if("N".equals(event.getNewValue())){ this.user.getPerson().setIsStudent(false); }
    	this.enableDisableFields();
    }
    public boolean getCannotShowUser(){ return false; }
    public boolean getCanShowUser(){ return true; }
    public String actionEdit(){
    	UserListItem item  = (UserListItem) this.usersTable.getRowData();
    	this.user = ((ValueResultUser)this.userService.getUserByUserListItem(item)).getValue();
    	this.enableDisableFields();
    	return "user_edit";
    }
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
//    		this.user.getPerson().setIsStudent(false);
    		this.user.getPerson().setEmployer("");
    		this.user.getPerson().setPosition(new Position());
    		this.user.getPerson().setProfile(new Profile());
    	} else if(this.mainRole().getKey().equals(roleKeyEmployee)){
        	this.user.getPerson().setHprNumber(null);
    		this.user.getPerson().setStudentNumber(null);
//    		this.user.getPerson().setIsStudent(false);
    	} else if(this.mainRole().getKey().equals(roleKeyHealthWorker)){
    		this.user.getPerson().setStudentNumber(null);
//    		this.user.getPerson().setIsStudent(false);
    		this.user.getPerson().setPosition(this.allPositionsMap.get(this.user.getPerson().getPosition().getKey()));
    	} else if(this.mainRole().getKey().equals(roleKeyStudent)){
        	this.user.getPerson().setHprNumber(null);
//        	this.user.getPerson().setIsStudent(this.selectedIsStudent.equals("Y"));
    		this.user.getPerson().setPosition(new Position());
    	}
    	if(this.isNew()){
    		this.userService.insertUser(this.user);
    	} else {
    		this.userService.updateUser(this.user);
    	}
    	UserListItem item = new UserListItem();
    	item.setId(this.user.getId());
    	this.user = ((ValueResultUser)this.userService.getUserByUserListItem(item)).getValue();
    	return "user_details";
    }
    public String actionCancel(){
    	SingleResultUser result = this.userService.findUserByUsername(this.user.getUsername());
    	if(result instanceof ValueResultUser){
        	this.user = ((ValueResultUser)result).getValue();
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
		UserListItem item = (UserListItem) this.usersTable.getRowData();
    	this.user = ((ValueResultUser)this.userService.getUserByUserListItem(item)).getValue();
//		this.user.getPerson().setIsStudent(true);
		return details();
	}
	public Role[] getSelectedRolesRoleList(){
		if(this.allRolesMap == null) {getAllRoles();}
		Role[] selectedRoles = new Role[this.getSelectedRoles().size()];
		int i=0;
		for (String string : this.getSelectedRoles()) {
			selectedRoles[i] = this.allRolesMap.get(string);
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
				new PageRequest(0, 40)).getResult();
		
		// TODO: Adding dummy roles?
		for (UserListItem item : this.users) {
			if(item.getRoleNames().length == 0){
				item.setRoleNames(new String[1]);
				item.getRoleNames()[0] = "";
			}
		}
		
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
				SelectItem option = new SelectItem(role.getKey().getValue(), role.getName(), "", false);
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
				SelectItem option = new SelectItem(position.getKey().getValue(), position.getName(), "", false);
				this.availablePositions.add(option);
			}
		}
		return this.availablePositions;
	}
	public List<SelectItem> getavailableEmployers() {
		if(this.availableEmployers == null) {
			this.availableEmployers = new ArrayList<SelectItem>();
			MemberOrganization dummy = new MemberOrganization();
			dummy.getOrganization().setId(-999);
			String name = ResourceBundle.getBundle(
					"no.helsebiblioteket.admin.web.jsf.messageresources.main", Locale.getDefault()).getString(
					"user_details_no_employer");
			// TODO: How to find right name here?
			dummy.getOrganization().setNameEnglish(name);
			dummy.getOrganization().setNameShortEnglish(name);
			dummy.getOrganization().setNameNorwegian(name);
			dummy.getOrganization().setNameShortNorwegian(name);
			SelectItem dummyOption = new SelectItem(""+dummy.getOrganization().getId(), dummy.getOrganization().getNameEnglish(), "", false);
			this.availableEmployers.add(dummyOption);
			PageRequest request = new PageRequest(0, 40);
			PageResultOrganizationListItem orgs = this.organizationService.getOrganizationListAll(request);
			for (OrganizationListItem organization : orgs.getResult()) {
				// TODO: How to find right name here?
				SelectItem option = new SelectItem(""+organization.getId(), organization.getNameEnglish(), "", false);
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
				this.selectedRoles.add(role.getKey().getValue());
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
					((ValueResultSystem)
							this.userService.getSystemByKey(SystemKey.helsebiblioteket_admin)).getValue()).getList();
			this.allRoles = new ArrayList<Role>();
			this.allRolesMap = new HashMap<String, Role>();
			for (Role role : roles) {
				this.allRoles.add(role);
				this.allRolesMap.put(role.getKey().getValue(), role);
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
			this.allPositionsMap = new HashMap<String, Position>();
			for (Position position : this.allPositions) {
				this.allPositionsMap.put(position.getKey().getValue(), position);
			}
		}
		return this.allPositions;
	}
	public UserListItem[] getUsers() {
		// FIXME: Handle paged result!
		if(this.users == null) { this.users = userService.getUserListAll(new PageRequest(0, 40)).getResult(); }

		// TODO: Set dummy roles?
		for (UserListItem item : this.users) {
			if(item.getRoleNames().length == 0){
				item.setRoleNames(new String[1]);
				item.getRoleNames()[0] = "";
			}
		}

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
