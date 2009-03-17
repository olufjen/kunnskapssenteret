package no.helsebiblioteket.admin.domain.line;

import no.helsebiblioteket.admin.domain.key.UserRoleKey;

public class UserListLine {
	private Integer id;
	private String firstName;
	private String lastName;
	private String username;
	private String roleName;
	private String organizationName;
	private UserRoleKey roleKey;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public UserRoleKey getRoleKey() {
		return roleKey;
	}
	public void setRoleKey(UserRoleKey roleKey) {
		this.roleKey = roleKey;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
}
