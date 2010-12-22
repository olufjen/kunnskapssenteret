package no.helsebiblioteket.admin.bean;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectBoolean;
import javax.faces.component.UISelectMany;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.key.UserRoleKey;
import no.helsebiblioteket.admin.domain.list.OrganizationListItem;
import no.helsebiblioteket.admin.domain.list.UserListItem;
import no.helsebiblioteket.admin.domain.requestresult.PageResultOrganizationListItem;
import no.helsebiblioteket.admin.domain.requestresult.PageResultUserListItem;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultOrganization;
import no.helsebiblioteket.admin.domain.requestresult.SingleResultUser;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultMemberOrganization;
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
	private String selectedUserRole;
	private String selectedIsStudent;
	private boolean isOrgAdmin;
	private boolean showSelectMemberOrg;
	private String password;
	private String repeatPassword;

	private List<Role> allRoles;
	private Map<String, Role> allRolesMap;
	private List<Position> allPositions;
	private Map<String, Position> allPositionsMap;
	private UserListItem[] users;
	private User user;
	private Integer memberOrgId;
	private OrganizationListItem[] memberOrganizations;
	private String adminOrgName;
	private boolean showHprNumber;
	private boolean showDateOfBirth;
	private boolean showEmployerNumber;
	private boolean showIsStudent;
	private boolean showEmployerText;
	private boolean showPositionText;
	private boolean showPositionMenu;
	private boolean showProfile;
	
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
	private UIInput usernameInput;
	private UISelectBoolean isOrgAdminSelectBooleanCheckbox;
	
	private PageResultUserListItem lastPageResult;
	private String searchedString;
	private Role[] searchedRoles;
	private int SHOW_MAX = 40;
	
    protected final Log logger = LogFactory.getLog(getClass());
    private static final String bundleMain = "no.helsebiblioteket.admin.web.jsf.messageresources.main";

    private static final Map<String, String> studentPosMap = new HashMap<String, String>();
    private static final List<String> studentPosList = new ArrayList<String>();
    
    static {
    	studentPosMap.put("pos_student", posBundle("pos_student")); 
    	studentPosMap.put("pos_teacher", posBundle("pos_teacher"));
    	studentPosMap.put("pos_administration", posBundle("pos_administration"));
    	studentPosMap.put("pos_researcher", posBundle("pos_researcher"));
    	studentPosMap.put("pos_other", posBundle("pos_other"));
    	
    	studentPosList.add("pos_student");
    	studentPosList.add("pos_teacher");
    	studentPosList.add("pos_administration");
    	studentPosList.add("pos_researcher");
    	studentPosList.add("pos_other");
    }
    
    private static String posBundle(String key){
    	return MessageResourceReader.getMessageResourceString(bundleMain, key, key);	
    }
    

    private boolean isNew(){ return this.user.getId() == null; }

    private Role mainRole(){
    	Role[] roleList = this.user.getRoleList();
    	if(roleList.length == 1){
        	return roleList[0];
    	} else {
    		if( ! roleList[0].getKey().getValue().equals(UserRoleKey.organization_administrator.getValue())){
        		return roleList[0];
    		} else {
        		return roleList[1];
    		}
    	}
    }
    private void mainRole(Role role){
    	Role[] roleList = this.user.getRoleList();
    	if(roleList == null){ roleList = new Role[1]; }
    	if(roleList.length == 1){
        	roleList[0] = role;
    	} else {
    		if( ! roleList[0].getKey().getValue().equals(UserRoleKey.organization_administrator.getValue())){
        		roleList[0] = role;
    		} else {
        		roleList[1] = role;
    		}
    	}
    	this.user.setRoleList(roleList);
    }
    public void initSelectedIsStudent(){
		if(this.availableIsStudent == null){
			this.availableIsStudent = new ArrayList<SelectItem>();
			
			for (String key : studentPosList) {
				this.availableIsStudent.add(new SelectItem(key, studentPosMap.get(key)));
			}
			
    	}
		this.selectedIsStudent = "";
		if(this.mainRole().getKey().getValue().equals(roleKeyStudent)){
			if(this.user != null && this.user.getPerson() != null){
				if( this.user.getPerson().getPositionText() != null){
					this.selectedIsStudent = this.user.getPerson().getPositionText();
				}
				this.user.getPerson().setPositionText("");
			}
		}
		if(this.isStudentSelectOne != null) { this.isStudentSelectOne.setValue(this.selectedIsStudent); }
    }
    
    public void enableDisableFields(){
    	this.initSelectedIsStudent();
    	if(this.mainRole().getKey().getValue().equals(roleKeyAdministrator)){
        	this.showHprNumber = false;
        	this.user.getPerson().setHprNumber("");
        	this.showDateOfBirth = false;        	
        	this.user.getPerson().setDateOfBirth("");
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
    		this.showDateOfBirth = true;
        	this.showEmployerNumber = false;
    		this.user.getPerson().setStudentNumber("");
    		this.showIsStudent = false;
        	this.showEmployerText = true;
        	this.showPositionText = true;
        	this.showPositionMenu = false;
    		this.showProfile = true;
    	} else if(this.mainRole().getKey().getValue().equals(roleKeyHealthWorker)){
    		this.showHprNumber = true;
    		this.showDateOfBirth = false;
        	this.showEmployerNumber = false;
    		this.user.getPerson().setStudentNumber("");
    		this.user.getPerson().setDateOfBirth("");
    		this.showIsStudent = false;
        	this.showEmployerText = true;
        	this.showPositionText = false;
        	this.showPositionMenu = true;
    		this.showProfile = true;
    	} else if(this.mainRole().getKey().getValue().equals(roleKeyStudent)){
    		this.showHprNumber = false;
    		this.user.getPerson().setHprNumber("");
    		this.showDateOfBirth = false;
    		this.user.getPerson().setDateOfBirth("");
        	this.showEmployerNumber = true;
    		this.showIsStudent = true;
        	this.showEmployerText = true;
        	this.showPositionText = false;
        	this.showPositionMenu = false;
    		this.showProfile = true;
    	}
    	this.showSelectMemberOrg = this.isOrgAdmin;
    }
    public void isOrgAdminChanged(ValueChangeEvent event){
    	Boolean newVal = (Boolean) event.getNewValue();
    	this.isOrgAdmin = newVal;
    	Role[] roleList = this.user.getRoleList();
    	if(roleList == null){ roleList = new Role[1]; }
    	if(this.isOrgAdmin){
        	if(roleList.length == 1 && ( ! roleList[0].getKey().getValue().equals(UserRoleKey.organization_administrator.getValue()))){
        		Role old = roleList[0];
            	roleList = new Role[2];
            	roleList[0] = old;
            	roleList[1] = this.allRolesMap.get(UserRoleKey.organization_administrator.getValue());
        	}
    	} else {
    		this.memberOrgId = -1;
        	if(roleList.length == 2){
        		Role old;
        		if(roleList[0].getKey().getValue() == UserRoleKey.organization_administrator.getValue()){
            		old = roleList[1];
        		} else {
            		old = roleList[0];
        		}
            	roleList = new Role[1];
            	roleList[0] = old;
        	}
    	}
    	this.user.setRoleList(roleList);
    	this.enableDisableFields();
    }
    public void roleChanged(ValueChangeEvent event){
    	if(this.allRolesMap.containsKey(event.getNewValue())){
    		this.mainRole(this.allRolesMap.get(event.getNewValue()));
    	}
		this.user.getPerson().setPosition(new Position());
		this.user.getPerson().getPosition().setKey(PositionTypeKey.none);
		this.user.getPerson().setPositionText("");
		this.selectedUserRole = this.mainRole().getKey().getValue();
    	this.enableDisableFields();
    }
    public void studentChanged(ValueChangeEvent event){
//    	if("Y".equals(event.getNewValue())){ this.user.getPerson().setIsStudent(true); }
//    	if("N".equals(event.getNewValue())){ this.user.getPerson().setIsStudent(false); }
    	this.enableDisableFields();
    }
    public boolean getCannotShowUser(){ return false; }
    public boolean getCanShowUser(){ return true; }
    public String actionDelete() {
    	userService.deleteUser(this.user);
    	return actionSearch();
    }
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
//    	UserListItem item  = new UserListItem();
//    	item.setId(user.getId());
//    	Object userObject = this.userService.getUserByUserListItem(item);
//    	if (userObject instanceof ValueResultUser) {
//    		this.user = ((ValueResultUser) userObject).getValue(); 
//    	} else if (userObject instanceof ValueResultOrganizationUser) {
//    		this.user = ((ValueResultOrganizationUser) userObject).getValue().getUser();
//    	}
    	this.prepareEdit();
    	return "user_edit";
    }
    public void prepareEdit(){
    	this.password = "";
    	this.repeatPassword = "";
    	if(this.user.getOrgAdminFor() != null){
        	this.memberOrgId = this.user.getOrgAdminFor().getId();
    	} else {
    		this.memberOrgId = null;
    	}
    	if(this.user.getPerson().getPosition() == null || this.user.getPerson().getPosition().getKey() == null){
    		this.user.getPerson().setPosition(new Position());
    		this.user.getPerson().getPosition().setKey(PositionTypeKey.none);
    	}
		this.selectedUserRole = this.mainRole().getKey().getValue();
		if(this.userRolesSelectOne != null && this.selectedUserRole != null){
			this.userRolesSelectOne.setValue(this.selectedUserRole);
		}
		this.isOrgAdmin = (this.user.getOrgAdminFor() != null);
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
    		} else {
    			this.user.setPassword(this.password);
    		}
    	}
		if(userExists(this.user.getUsername() == null ? "" : this.user.getUsername())){
			String  bundleMain = "no.helsebiblioteket.admin.web.jsf.messageresources.main";
			String messageValue = MessageResourceReader.getMessageResourceString(bundleMain, "user_exists", "The username is taken");
			FacesContext.getCurrentInstance().addMessage(
					this.usernameInput.getClientId(FacesContext.getCurrentInstance()),
					new FacesMessage(FacesMessage.SEVERITY_INFO, messageValue, messageValue));
			return null;
		}
    	
    	this.mainRole(this.allRolesMap.get(this.selectedUserRole));
    	if(this.mainRole().getKey().getValue().equals(roleKeyAdministrator)){
        	this.user.getPerson().setHprNumber("");
        	this.user.getPerson().setDateOfBirth("");
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
    		this.user.getPerson().setDateOfBirth("");
//    		this.user.getPerson().setIsStudent(false);
    		this.user.getPerson().setPosition(this.allPositionsMap.get(this.user.getPerson().getPosition().getKey().getValue()));
    		this.user.getPerson().setPositionText("");
    	} else if(this.mainRole().getKey().getValue().equals(roleKeyStudent)){
    		this.user.getPerson().setDateOfBirth("");
        	this.user.getPerson().setHprNumber("");
        	this.user.getPerson().setPositionText(this.selectedIsStudent);
//    		this.user.getPerson().setPosition(new Position());
    	}
    	this.user.setOrgAdminFor(new Organization());
    	if(this.isOrgAdmin){
        	if(this.memberOrgId != null && this.memberOrgId.intValue()>=0){
            	this.user.getOrgAdminFor().setId(this.memberOrgId);
        	} else {
            	this.user.getOrgAdminFor().setId(null);
        	}
    	} else {
        	this.user.getOrgAdminFor().setId(null);
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
    	
    	return details();
    }
	private boolean userExists(String username) {
		if(isNew()){
			return this.userService.usernameTaken(username, null);
		} else {
			return this.userService.usernameTaken(username, this.user.getId());
		}
	}
    public String actionCancel(){
    	SingleResultUser result = this.userService.findUserByUsername(this.user.getUsername());
    	if(result instanceof ValueResultUser){
        	this.user = ((ValueResultUser)result).getValue();
    	} else {
    		this.user = null;
    	}
    	
    	// TODO: Reset role?
    	this.userRolesSelectOne = null;
    	
    	return details();
    }
    public String getErrorMsg() { return ""; }
    public boolean getFailed() { return true; }
    public String details(){
		logger.info("USER: " + user.getPerson().getName());
		this.adminOrgName = "Ingen";
		if(this.user.getOrgAdminFor() != null && this.user.getOrgAdminFor().getId() != null){
			SingleResultOrganization orgRes = this.organizationService.getOrganizationByAdminUser(this.user);
			if(orgRes instanceof ValueResultMemberOrganization){
				this.adminOrgName = OrganizationBean.organizationName(((ValueResultMemberOrganization)orgRes).getValue().getOrganization());
			}
		}
		if(this.user.getPerson().getPosition() == null){
			this.user.getPerson().setPosition(new Position());
			if(this.mainRole().getKey().getValue().equals(roleKeyStudent)){
				this.user.getPerson().getPosition().setName("");
				if (this.user.getPerson().getPositionText() != null){
					String posText = studentPosMap.get(this.user.getPerson().getPositionText());
					if(posText != null){
						this.user.getPerson().getPosition().setName(posText);
					}
				}
			} else {
				this.user.getPerson().getPosition().setName(this.user.getPerson().getPositionText());
			}
		}
		return "user_details";
    }
    public String actionDetails(){
		UserListItem item = (UserListItem) this.usersTable.getRowData();
    	SingleResultUser lookup = this.userService.getUserByUserListItem(item);
		if (lookup instanceof ValueResultUser) {
			this.user = ((ValueResultUser)lookup).getValue();
		} else if (lookup instanceof ValueResultOrganizationUser) {
			this.user = ((ValueResultOrganizationUser) lookup).getValue().getUser();
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
		
		// TODO Fase2: Adding dummy roles?
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
			if(logger.isDebugEnabled()){
				logger.debug("STARTROLES");
				logger.debug("this.userService=" + this.userService);
			}
			for (Role role : this.getAllRoles()) {
				if( ! role.getKey().getValue().equals(UserRoleKey.organization_administrator.getValue())){
//					org_admin
					logger.debug("role: " + role.getKey());
					SelectItem option = new SelectItem(role.getKey().getValue(), role.getName(), "", false);
					this.availableRoles.add(option);
				}
			}
			if(logger.isDebugEnabled()){
				logger.debug("DONEROLES");
			}
			
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
			// TODO Fase2: How to find right name here?
			dummy.getOrganization().setNameEnglish(name);
			dummy.getOrganization().setNameShortEnglish(name);
			dummy.getOrganization().setNameNorwegian(name);
			dummy.getOrganization().setNameShortNorwegian(name);
			SelectItem dummyOption = new SelectItem(""+dummy.getOrganization().getId(), dummy.getOrganization().getNameEnglish(), "", false);
			this.availableEmployers.add(dummyOption);
			PageRequest request = new PageRequest(0, 40);
			PageResultOrganizationListItem orgs = this.organizationService.getOrganizationListAll(request);
			for (OrganizationListItem organization : orgs.getResult()) {
				// TODO Fase2: How to find right name here?
				String orgName = organization.getNameNorwegian();
				if(orgName.equals("")) orgName = organization.getNameEnglish();
				SelectItem option = new SelectItem(""+organization.getId(), orgName, "", false);
				this.availableEmployers.add(option);
			}
		}
		return this.availableEmployers;
	}
	public List<String> getSelectedRoles() {
		if(this.selectedRoles == null){
			this.selectedRoles = new ArrayList<String>();
			for (Role role : this.getAllRoles()) {
				this.selectedRoles.add(role.getKey().getValue());
			}
		}
		return this.selectedRoles;
	}
	public String getSelectedUserRole() {
		this.selectedUserRole = this.mainRole().getKey().getValue();
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

	public List<SelectItem> getMembers() {
		List<SelectItem> list = new ArrayList<SelectItem>();

		// TODO: Remove
		this.memberOrganizations = null;
		
		if(this.memberOrganizations == null){
			SelectItem allItem = new SelectItem(new Integer(-1), "Ingen");
			list.add(allItem);
			PageResultOrganizationListItem orgs = this.organizationService.getMemberOrganizationListAll(new PageRequest(0, 200));
			this.memberOrganizations = orgs.getResult();
			for(OrganizationListItem org : this.memberOrganizations){
				SelectItem orgItem = new SelectItem(org.getId(),
						AdminBean.subStringMax(OrganizationBean.organizationName(org), 40));
				list.add(orgItem);
			}
		}
		return list;
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
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getRepeatPassword() { return repeatPassword; }
	public void setRepeatPassword(String repeatPassword) { this.repeatPassword = repeatPassword; }
	public UIInput getPasswordRepeat() { return passwordRepeat; }
	public void setPasswordRepeat(UIInput passwordRepeat) { this.passwordRepeat = passwordRepeat; }
	public UIInput getPasswordInput() { return passwordInput; }
	public void setPasswordInput(UIInput passwordInput) { this.passwordInput = passwordInput; }
	public boolean isShowDateOfBirth() { return showDateOfBirth; }
	public void setShowDateOfBirth(boolean showDateOfBirth) { this.showDateOfBirth = showDateOfBirth; }
	public Integer getMemberOrgId() { return memberOrgId; }
	public void setMemberOrgId(Integer memberOrgId) { this.memberOrgId = memberOrgId; }
	public OrganizationListItem[] getMemberOrganizations() { return memberOrganizations; }
	public void setMemberOrganizations(OrganizationListItem[] memberOrganizations) { this.memberOrganizations = memberOrganizations; }
	public String getAdminOrgName() { return adminOrgName; }
	public void setAdminOrgName(String adminOrgName) { this.adminOrgName = adminOrgName; }
	public boolean getIsOrgAdmin() { return isOrgAdmin; }
	public void setIsOrgAdmin(boolean isOrgAdmin) { this.isOrgAdmin = isOrgAdmin; }
	public boolean isShowSelectMemberOrg() { return showSelectMemberOrg; }
	public void setShowSelectMemberOrg(boolean showSelectMemberOrg) { this.showSelectMemberOrg = showSelectMemberOrg; }
	public UISelectBoolean getIsOrgAdminSelectBooleanCheckbox() { return isOrgAdminSelectBooleanCheckbox; }
	public void setIsOrgAdminSelectBooleanCheckbox(UISelectBoolean isOrgAdminSelectBooleanCheckbox) { this.isOrgAdminSelectBooleanCheckbox = isOrgAdminSelectBooleanCheckbox; }
	public UIInput getUsernameInput() { return usernameInput; }
	public void setUsernameInput(UIInput usernameInput) { this.usernameInput = usernameInput; }
}
