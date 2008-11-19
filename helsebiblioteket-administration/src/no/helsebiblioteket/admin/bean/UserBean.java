package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import no.helsebiblioteket.domain.Organization;
import no.helsebiblioteket.domain.Role;
import no.helsebiblioteket.domain.User;
import no.helsebiblioteket.admin.service.AdminService;

public class UserBean {
	private AdminService adminService;
	
	private String searchinput;
	private List<SelectItem> availableRoles;
	private List<SelectItem> selectedRoles;
	private List<User> users;
	
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	public List<User> getUserList() {
		return adminService.getUserList();
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
		list.add(option);
		option = new SelectItem("ch2", "choice3", "This bean is for selectItems tag", true);
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
			roleList.add(new Role());
			user.setRoleList(roleList);
			Organization organizaion = new Organization();
			organizaion.setName("Ullevål Sykehus");
			user.setOrganization(organizaion);
			myUsers.add(user);
		}
		{
			User user = new User();
			user.setUsername("leiftorger");
			List<Role> roleList  = new ArrayList<Role>();
			roleList.add(new Role());
			roleList.add(new Role());
			user.setRoleList(roleList);
			Organization organization = new Organization();
			organization.setName("Rikshospitalet");
			user.setOrganization(organization);
			myUsers.add(user);
		}


		this.users = myUsers;
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}