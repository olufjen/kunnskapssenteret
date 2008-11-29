package no.helsebiblioteket.admin.bean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlSelectManyCheckbox;
import javax.faces.model.SelectItem;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.service.UserService;

public class UserBean {
	private UserService userService;
	private String searchinput;
	private List<SelectItem> availableRoles;
	private List<SelectItem> selectedRoles;
	// FIXME: Use caching?
	private List<User> users;
	private User user;
	private HtmlSelectManyCheckbox rolesManyCheckbox;
	private HtmlDataTable usersTable;
    protected final Log logger = LogFactory.getLog(getClass());
	public String actionDetails(){
//		FacesContext context = FacesContext.getCurrentInstance();
//		Map requestParams = context.getExternalContext().getRequestParameterMap();
//		logger.info("userId: " +
//				requestParams.get("userId"));
		this.user = (User)this.usersTable.getRowData();
		logger.info("USER: " + user.getPerson().getName());
//		this.supplierSourceList.remove((SupplierSource) this.supplierSourceListHtmlDataTable.getRowData());
		return "user_details";
	}
	public String actionChange(){ return "user_change"; }
	public String actionSearch(){
		logger.info("method 'search' invoked");
		List<Role> roles = Arrays.asList((Role[])this.rolesManyCheckbox.getSelectedValues());
		this.users = this.userService.findUsersBySearchStringRoles(this.searchinput, roles);
		return "users_overview";
	}
	public String getSearchinput() { return searchinput; }
	public void setSearchinput(String searchinput) { this.searchinput = searchinput; }
	public List<SelectItem> getAvailableRoles() {
		if(this.availableRoles == null) { this.availableRoles = getAllRolesAsItems(); }
		return this.availableRoles;
	}
	public void setSelectedRoles(List<SelectItem> selectedRoles) { this.selectedRoles = selectedRoles; }
	public List<SelectItem> getSelectedRoles() {
		if(this.selectedRoles == null){ this.selectedRoles = getAllRolesAsItems(); }
		return this.selectedRoles;
	}
	public List<SelectItem> getAllRolesAsItems() {
		List<Role> roles = this.userService.getAllUserRoles();
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (Role role : roles) {
			SelectItem option = new SelectItem(role, "This bean is for selectItems tag");
			option.setDisabled(false);
			items.add(option);
		}
		return items;
	}
	public List<User> getUsers() {
		if(this.users == null) { this.users = userService.getAllUsers(); }
		return this.users;
	}
	public void setUserService(UserService userService) { this.userService = userService; }
	public HtmlDataTable getUsersTable() { return usersTable; }
	public void setUsersTable(HtmlDataTable usersTable) { this.usersTable = usersTable; }
}
