package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectMany;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

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
import no.helsebiblioteket.admin.domain.requestresult.PageResultUserListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultOrganizationUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSystem;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultUser;
import no.helsebiblioteket.admin.requestresult.PageRequest;
import no.helsebiblioteket.admin.service.OrganizationService;
import no.helsebiblioteket.admin.service.UserService;
import no.helsebiblioteket.admin.validator.PasswordValidator;
import no.helsebiblioteket.admin.web.jsf.MessageResourceReader;

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
	
	private String password;
	private String repeatPassword;

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
	private UIInput passwordRepeat;
	private UIInput passwordInput;
	
	private PageResultUserListItem lastPageResult;
	private String searchedString;
	private Role[] searchedRoles;
	private int SHOW_MAX = 40;
	
	
    protected final Log logger = LogFactory.getLog(getClass());

    private boolean isNew(){ return this.user.getId() == null; }
	// TODO: Now fetching main role with: user.roleList[0].name
    // TODO: Place in User class?
    private Role mainRole(){ return this.user.getRoleList()[0]; }
    private void mainRole(Role role){ this.user.setRoleList(new Role[1]); this.user.getRoleList()[0]=role; }
    
    public void initSelectedIsStudent(){
		if(this.availableIsStudent == null){
			this.availableIsStudent = new ArrayList<SelectItem>();
//    	if(this.mainRole().getKey().equals(roleKeyAdministrator) | 
//    			this.mainRole().getKey().getValue().equals(roleKeyEmployee) |
//    			this.mainRole().getKey().getValue().equals(roleKeyHealthWorker)){
//			this.availableIsStudent.add(new SelectItem("U", "None"));
//    		this.selectedIsStudent = "U";
//    		if(this.isStudentSelectOne != null) { this.isStudentSelectOne.setValue(this.selectedIsStudent); }
//    	} else if(this.mainRole().getKey().getValue().equals(roleKeyStudent)){
			this.availableIsStudent.add(new SelectItem("Y", "Student"));
			this.availableIsStudent.add(new SelectItem("N", "Employee"));
			this.selectedIsStudent = "N";
			if(this.isStudentSelectOne != null) { this.isStudentSelectOne.setValue(this.selectedIsStudent); }
//    	} else {
//    		// TODO: What then?
//    		this.availableIsStudent.add(new SelectItem("U", "None"));
//    		this.selectedIsStudent = "";
    	}
//		}

//		logger.info("this.user.getRole().getKey()=" + this.mainRole().getKey().getValue());
//		logger.info("this.selectedIsStudent=" + this.selectedIsStudent);
//		logger.info("this.isStudentSelectOne=" + this.isStudentSelectOne);
		

    }
    public void enableDisableFields(){
    	// TODO: Is this done correctly?
    	this.initSelectedIsStudent();
    	if(this.mainRole().getKey().getValue().equals(roleKeyAdministrator)){
        	this.showHprNumber = false;
        	this.user.getPerson().setHprNumber("");
        	this.showEmployerNumber = false;
    		this.user.getPerson().setStudentNumber("");
    		this.showIsStudent = false;
        	this.showEmployerText = false;
    		this.user.getPerson().setEmployer("");
        	this.showPositionText = false;
        	this.showPositionMenu = false;
    		this.showProfile = false;
    	} else if(this.mainRole().getKey().getValue().equals(roleKeyEmployee)){
    		this.showHprNumber = false;
    		this.user.getPerson().setHprNumber("");
        	this.showEmployerNumber = false;
    		this.user.getPerson().setStudentNumber("");
    		this.showIsStudent = false;
        	this.showEmployerText = true;
        	this.showPositionText = true;
        	this.showPositionMenu = false;
    		this.showProfile = true;
    	} else if(this.mainRole().getKey().getValue().equals(roleKeyHealthWorker)){
    		this.showHprNumber = true;
        	this.showEmployerNumber = false;
    		this.user.getPerson().setStudentNumber("");
    		this.showIsStudent = false;
        	this.showEmployerText = true;
        	this.showPositionText = false;
        	this.showPositionMenu = true;
    		this.showProfile = true;
    	} else if(this.mainRole().getKey().getValue().equals(roleKeyStudent)){
    		this.showHprNumber = false;
    		this.user.getPerson().setHprNumber("");
        	this.showEmployerNumber = true;
    		this.showIsStudent = true;
        	this.showEmployerText = true;
        	this.showPositionText = false;
        	this.showPositionMenu = false;
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
		this.user.getPerson().setPosition(new Position());
		this.user.getPerson().getPosition().setKey(PositionTypeKey.none);
		this.selectedUserRole = this.mainRole().getKey();
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
    	Object userObject = this.userService.getUserByUserListItem(item);
    	if (userObject instanceof ValueResultUser) {
    		this.user = ((ValueResultUser) userObject).getValue(); 
    	} else if (userObject instanceof ValueResultOrganizationUser) {
    		this.user = ((ValueResultOrganizationUser) userObject).getValue().getUser();
    	}
    	this.prepareEdit();
    	return "user_edit";
    }
    public String actionEditSingle(){
    	this.prepareEdit();
    	return "user_edit";
    }
    public void prepareEdit(){
    	this.password = "";
    	this.repeatPassword = "";
    	if(this.user.getPerson().getPosition() == null){
    		this.user.getPerson().setPosition(new Position());
    		this.user.getPerson().getPosition().setKey(PositionTypeKey.none);
    	}
		this.selectedUserRole = this.mainRole().getKey();
		this.enableDisableFields();
    }
    public String getUserRole(){
    	if(this.user != null && this.mainRole() != null) {
    		return this.mainRole().getName();
    	} else {
    		return "MISSING_USER_ROLE";
    	}
    }
    public String actionSave(){
    	if(this.password==null) this.password = "";
    	if(this.repeatPassword==null) this.repeatPassword="";
    	if( ( ! this.password.equals("") ) ||
    			( ! this.repeatPassword.equals(""))){
    		if( ! PasswordValidator.getInstance().isValidPassword(this.password)){
    			String  bundleMain = "no.helsebiblioteket.admin.web.jsf.messageresources.main";
    			String messageValue = MessageResourceReader.getMessageResourceString(bundleMain, "password_not_valid", "The password is not valid");
    			FacesContext.getCurrentInstance().addMessage(
    					this.passwordInput.getClientId(FacesContext.getCurrentInstance()),
    					new FacesMessage(FacesMessage.SEVERITY_INFO, messageValue, messageValue));
    			return null;
    		} else if( ! this.password.equals(this.repeatPassword)){
    			String  bundleMain = "no.helsebiblioteket.admin.web.jsf.messageresources.main";
    			String messageValue = MessageResourceReader.getMessageResourceString(bundleMain, "password_repeat_not_equal", "Passwords are not equal");
    			FacesContext.getCurrentInstance().addMessage(
    					this.passwordRepeat.getClientId(FacesContext.getCurrentInstance()),
    					new FacesMessage(FacesMessage.SEVERITY_INFO, messageValue, messageValue));
    			return null; 
    		}
    	}
    	
    	this.mainRole(this.allRolesMap.get(this.selectedUserRole.getValue()));
    	if(this.mainRole().getKey().getValue().equals(roleKeyAdministrator)){
        	this.user.getPerson().setHprNumber("");
    		this.user.getPerson().setStudentNumber("");
//    		this.user.getPerson().setIsStudent(false);
    		this.user.getPerson().setEmployer("");
//    		this.user.getPerson().setPosition(new Position());
//    		this.user.getPerson().setProfile(new Profile());
    	} else if(this.mainRole().getKey().getValue().equals(roleKeyEmployee)){
        	this.user.getPerson().setHprNumber("");
    		this.user.getPerson().setStudentNumber("");
//    		this.user.getPerson().setIsStudent(false);
    	} else if(this.mainRole().getKey().getValue().equals(roleKeyHealthWorker)){
    		this.user.getPerson().setStudentNumber("");
//    		this.user.getPerson().setIsStudent(false);
    		this.user.getPerson().setPosition(this.allPositionsMap.get(this.user.getPerson().getPosition().getKey().getValue()));
    		this.user.getPerson().setPositionText("");
    	} else if(this.mainRole().getKey().getValue().equals(roleKeyStudent)){
        	this.user.getPerson().setHprNumber("");
        	this.user.getPerson().setIsStudent(this.selectedIsStudent.equals("Y"));
//    		this.user.getPerson().setPosition(new Position());
    	}
    	if(this.isNew()){
    		this.userService.insertUser(this.user);
    	} else {
    		this.userService.updateUser(this.user);
    	}
    	UserListItem item = new UserListItem();
    	item.setId(this.user.getId());
    	
    	Object userObject = this.userService.getUserByUserListItem(item);
    	
    	if (userObject instanceof ValueResultUser) {
    		this.user = ((ValueResultUser) userObject).getValue(); 
    	} else if (userObject instanceof ValueResultOrganizationUser) {
    		this.user = ((ValueResultOrganizationUser) userObject).getValue().getUser();
    	}
    	
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
    public String getErrorMsg() { return ""; }
    public boolean getFailed() { return true; }
    public String details(){
		logger.info("USER: " + user.getPerson().getName());
		return "user_details";
    }
    public String actionDetails(){
		UserListItem item = (UserListItem) this.usersTable.getRowData();
    	SingleResultUser lookup = this.userService.getUserByUserListItem(item);
		if (lookup instanceof ValueResultUser) {
			this.user = ((ValueResultUser)lookup).getValue();
		} else if (lookup instanceof ValueResultOrganizationUser) {
			user = ((ValueResultOrganizationUser) lookup).getValue().getUser();
		}
		if(this.user.getPerson().getPosition() == null){
			this.user.getPerson().setPosition(new Position());
			this.user.getPerson().getPosition().setName(this.user.getPerson().getPositionText());
		}
		return details();
	}
	public Role[] getSelectedRolesRoleList(){
		if(this.allRolesMap == null) {getAllRoles();}
		Role[] selectedRoles = new Role[this.getSelectedRoles().size()];
		int i=0;
		for (String string : this.getSelectedRoles()) {
			selectedRoles[i++] = this.allRolesMap.get(string);
		}
		return selectedRoles;
	}
	public String actionSearch(){
		this.searchedString = this.searchinput;
		if(this.searchedString == null) this.searchedString = "";
		this.searchedRoles = this.getSelectedRolesRoleList();
		this.lastPageResult = this.userService.findUsersBySearchStringRoles(this.searchedString, this.searchedRoles,
				new PageRequest(0, 40));
		this.users = this.lastPageResult.getResult();
		
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
				String orgName = organization.getNameNorwegian();
				if(orgName.equals("")) orgName = organization.getNameEnglish();
				SelectItem option = new SelectItem(""+organization.getId(), orgName, "", false);
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
		this.selectedUserRole = this.mainRole().getKey();
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
//			Position dummy = new Position();
//			dummy.setKey(PositionTypeKey.none);
//			dummy.setName(ResourceBundle.getBundle(
//					"no.helsebiblioteket.admin.web.jsf.messageresources.main", Locale.getDefault()).getString(
//							"user_details_no_position"));
//			this.allPositions.add(dummy);
			
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
		if(this.users == null) { this.actionSearch(); }
		return this.users;
	}
	public String getSelectedIsStudent() {
		initSelectedIsStudent();
		return selectedIsStudent;
	}
	public void setUserService(UserService userService) { this.userService = userService; }
	public void setOrganizationService(OrganizationService organizationService) { 
		this.organizationService = organizationService; 
	}
	
	public String actionForward(){
		if(this.getShowMoreRight()){
			if(this.searchedString == null) { this.searchedString = ""; }
			if(this.searchedRoles == null) { this.searchedRoles = this.getSelectedRolesRoleList(); }
			if(this.lastPageResult == null){ this.actionSearch(); }
			PageRequest pageRequest = new PageRequest(this.lastPageResult.getSkipped() + SHOW_MAX,
					SHOW_MAX);
			this.lastPageResult = this.userService.findUsersBySearchStringRoles(this.searchedString,
					this.searchedRoles, pageRequest);
			this.users = this.lastPageResult.getResult();
			this.usersTable = null;
		}
		return "users_overview";
	}
	public String actionBackward(){
		if(this.getShowMoreLeft()){
			if(this.searchedString == null) { this.searchedString = ""; }
			if(this.searchedRoles == null) { this.searchedRoles = this.getSelectedRolesRoleList(); }
			if(this.lastPageResult == null){ this.actionSearch(); }
			PageRequest pageRequest = new PageRequest(this.lastPageResult.getSkipped() - SHOW_MAX,
					SHOW_MAX);
			this.lastPageResult = this.userService.findUsersBySearchStringRoles(this.searchedString,
					this.searchedRoles, pageRequest);
			this.users = this.lastPageResult.getResult();
			this.usersTable = null;
		}
		return "users_overview";
	}
	public boolean getShowMore() {
		if(this.lastPageResult == null){ this.actionSearch(); }
		if(this.lastPageResult.getTotal() > SHOW_MAX){
			return true;
		} else {
			return false;
		}
	}
	public boolean getShowMoreLeft() {
		if(this.lastPageResult == null){ this.actionSearch(); }
		if(this.lastPageResult.getSkipped() > 0){
			return true;
		} else {
			return false;
		}
	}
	public boolean getShowMoreRight() {
		if(this.lastPageResult == null){ this.actionSearch(); }
		if(this.lastPageResult.getTotal() >
			this.lastPageResult.getSkipped() + this.lastPageResult.getNumber()){
			return true;
		} else {
			return false;
		}
	}

	
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
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getRepeatPassword() { return repeatPassword; }
	public void setRepeatPassword(String repeatPassword) { this.repeatPassword = repeatPassword; }
	public UIInput getPasswordRepeat() { return passwordRepeat; }
	public void setPasswordRepeat(UIInput passwordRepeat) { this.passwordRepeat = passwordRepeat; }
	public UIInput getPasswordInput() { return passwordInput; }
	public void setPasswordInput(UIInput passwordInput) { this.passwordInput = passwordInput; }
}
