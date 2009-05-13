package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import no.helsebiblioteket.admin.domain.Position;
import no.helsebiblioteket.admin.domain.Profile;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.key.PositionTypeKey;
import no.helsebiblioteket.admin.domain.key.SystemKey;
import no.helsebiblioteket.admin.domain.requestresult.ValueResultSystem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NewEndUserBean extends NewUserBean {
    protected final Log logger = LogFactory.getLog(getClass());

	private String roleKeyStudent = "student";
	private String roleKeyEmployee = "health_personnel_other";
	private String roleKeyHealthWorker = "health_personnel";
	private String roleKeyAdministrator = "administrator";

	private List<SelectItem> availableRoles;
	private List<Role> allRoles;
	private Map<String, Role> allRolesMap;
	List<SelectItem> availableIsStudent;

	private boolean showStudentNo;
	private boolean showHpr;
	private boolean showEmployer;
	private boolean showPositionText;
	private boolean showPositionSelect;
	private boolean showIsStudent;
	
    public String actionNewEndUser() {
    	String tmp = super.actionNewEndUser();
    	this.enableDisableFields();
    	return tmp;
    }
    public String actionSaveNewUser() {
		logger.info("method 'saveNewUser' invoked in new end user Bean");
		return null;
	}
    public String actionSaveNewEndUser() {
    	if(this.role == null){
    		//
    	}
    	if(this.role.getKey().getValue().equals(roleKeyAdministrator)){
    	} else if(this.role.getKey().getValue().equals(roleKeyEmployee)){
    	} else if(this.role.getKey().getValue().equals(roleKeyStudent)){
    	} else if(this.role.getKey().getValue().equals(roleKeyHealthWorker)){
    		
    	} else {
    		// Unknown user role
    	}
		Role[] list = new Role[1];
		list[0] = this.allRolesMap.get(this.role.getKey().getValue());
		user.setRoleList(list);
		return super.actionSaveNewEndUser();
    }
    public void roleChanged(ValueChangeEvent event){
    	if(this.allRolesMap.containsKey((String)event.getNewValue())){
    		this.role = this.allRolesMap.get((String)event.getNewValue());
    	}
    	this.enableDisableFields();
    }
    public void enableDisableFields(){
    	if(this.role.getKey().getValue().equals(roleKeyAdministrator)){
        	this.showStudentNo = false;
        	this.showHpr = false;
        	this.showEmployer = false;
        	this.showPositionText = false;
        	this.showPositionSelect = false;
    		this.showIsStudent = false;
    		this.user.getPerson().setHprNumber(null);
    		this.user.getPerson().setStudentNumber(null);
    		this.user.getPerson().setIsStudent(null);
    		this.user.getPerson().setPosition(new Position());
    		this.user.getPerson().getPosition().setKey(PositionTypeKey.none);
    		this.user.getPerson().setProfile(new Profile());
    	} else if(this.role.getKey().getValue().equals(roleKeyEmployee)){
        	this.showStudentNo = false;
        	this.showHpr = false;
        	this.showEmployer = true;
        	this.showPositionText = true;
        	this.showPositionSelect = false;
    		this.showIsStudent = false;
    		this.user.getPerson().setHprNumber(null);
    		this.user.getPerson().setStudentNumber(null);
    		this.user.getPerson().setIsStudent(null);
    		this.user.getPerson().setPosition(new Position());
    		this.user.getPerson().getPosition().setKey(PositionTypeKey.none);
    	} else if(this.role.getKey().getValue().equals(roleKeyHealthWorker)){
        	this.showStudentNo = false;
        	this.showHpr = true;
        	this.showEmployer = false;
        	this.showPositionText = false;
        	this.showPositionSelect = true;
    		this.showIsStudent = false;
    		this.user.getPerson().setStudentNumber(null);
    		this.user.getPerson().setIsStudent(null);
    		this.user.getPerson().getPosition().setKey(PositionTypeKey.none);
    	} else if(this.role.getKey().getValue().equals(roleKeyStudent)){
        	this.showStudentNo = true;
        	this.showHpr = false;
        	this.showEmployer = true;
        	this.showPositionText = true;
        	this.showPositionSelect = false;
    		this.showIsStudent = true;
    		this.user.getPerson().setHprNumber(null);
        	this.user.getPerson().setPosition(new Position());
    		this.user.getPerson().getPosition().setKey(PositionTypeKey.none);
    	} else {
        	this.showStudentNo = false;
        	this.showHpr = false;
        	this.showEmployer = false;
        	this.showPositionText = false;
        	this.showPositionSelect = false;
    		this.showIsStudent = false;
    		this.user.getPerson().setHprNumber(null);
        	this.user.getPerson().setPosition(new Position());
    		this.user.getPerson().getPosition().setKey(PositionTypeKey.none);
    	}
    }
	public List<SelectItem> getAvailableRoles() {
		if(this.availableRoles == null) {
			this.availableRoles = new ArrayList<SelectItem>();
			for (Role role : this.getAllRoles()) {
				SelectItem option = new SelectItem(role.getKey().getValue(),
						role.getName(), "", false);
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
			this.allRolesMap = new HashMap<String, Role>();
			for (Role role : roles) {
				this.allRoles.add(role);
				this.allRolesMap.put(role.getKey().getValue(), role);
			}
		}
		return this.allRoles;
	}
	public List<SelectItem> getAvailableIsStudent() {
		if(this.availableIsStudent == null){
			this.availableIsStudent = new ArrayList<SelectItem>();
			this.availableIsStudent.add(new SelectItem(Boolean.TRUE, "Student"));
			this.availableIsStudent.add(new SelectItem(Boolean.FALSE, "Employee"));
		}
		return this.availableIsStudent;
    }
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public boolean isShowStudentNo() {
		return showStudentNo;
	}
	public void setShowStudentNo(boolean showStudentNo) {
		this.showStudentNo = showStudentNo;
	}
	public boolean isShowHpr() {
		return showHpr;
	}
	public void setShowHpr(boolean showHpr) {
		this.showHpr = showHpr;
	}
	public boolean isShowEmployer() {
		return showEmployer;
	}
	public void setShowEmployer(boolean showEmployer) {
		this.showEmployer = showEmployer;
	}
	public boolean isShowPositionText() {
		return showPositionText;
	}
	public void setShowPositionText(boolean showPositionText) {
		this.showPositionText = showPositionText;
	}
	public boolean isShowPositionSelect() {
		return showPositionSelect;
	}
	public void setShowPositionSelect(boolean showPositionSelect) {
		this.showPositionSelect = showPositionSelect;
	}
	public boolean isShowIsStudent() {
		return showIsStudent;
	}
	public void setShowIsStudent(boolean showIsStudent) {
		this.showIsStudent = showIsStudent;
	}
}
