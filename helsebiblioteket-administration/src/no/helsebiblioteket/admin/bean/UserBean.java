package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.UserService;

public class UserBean {
	private UserService userService;
	private String searchinput;
	private List<SelectItem> availableRoles;
	private List<SelectItem> selectedRoles;
	private List<User> users;
	private String userId = "X";
    protected final Log logger = LogFactory.getLog(getClass());

	public String actionDetails(){
		return "users_overview";
	}
	public String actionChange(){
		return "users_overview";
	}

	public String actionSearch(){
		
		this.userId = "Y";

		logger.info("method 'search' invoked");

//		FacesContext context = FacesContext.getCurrentInstance();
//		Map requestParams = context.getExternalContext().getRequestParameterMap();
//		logger.info("userId: " +
//				requestParams.get("userId"));
		
		return "users_overview";

	}
	public void setAdminService(UserService userService) {
		this.userService = userService;
	}
	
	public List<User> getUserList() {
		return userService.getUserList();
	}

	public String getSearchinput() {
		return searchinput;
	}

	public void setSearchinput(String searchinput) {
		this.searchinput = searchinput;
	}

	public List<SelectItem> getAvailableRoles() {
    	List<SelectItem> list = new ArrayList<SelectItem>();
		SelectItem option = new SelectItem("ch1", "choice1", "This bean is for selectItems tag", true);
		option.setDisabled(false);
		list.add(option);
		option = new SelectItem("ch2", "choice3", "This bean is for selectItems tag", true);
		option.setDisabled(false);
		list.add(option);
		return list;
	}

	public void setAvailableRoles(List<SelectItem> availableRoles) {
		this.availableRoles = availableRoles;
	}

	public List<SelectItem> getSelectedRoles() {
		return selectedRoles;
	}

	public void setSelectedRoles(List<SelectItem> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public List<User> getUsers() {
		List<User> myUsers = new ArrayList<User>();
		{
			User user = new User();
			user.setUsername("fredriks");
			List<Role> roleList  = new ArrayList<Role>();
			Role role = new Role();
			role.setRoleName("User");
			roleList.add(role);
			user.setRoleList(roleList);
			Organization organizaion = new Organization();
			organizaion.setName("Ullevål Sykehus");
			user.setOrganization(organizaion);
			user.setId(123);
			myUsers.add(user);
		}
		{
			User user = new User();
			user.setUsername("leiftorger");
			List<Role> roleList  = new ArrayList<Role>();
			Role role = new Role();
			role.setRoleName("User");
			roleList.add(role);
			Role role2 = new Role();
			role2.setRoleName("Admin");
			roleList.add(role2);
			user.setRoleList(roleList);
			Organization organization = new Organization();
			organization.setName("Rikshospitalet");
			user.setOrganization(organization);
			user.setId(321);
			myUsers.add(user);
		}


		this.users = myUsers;
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
