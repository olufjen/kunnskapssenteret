package no.helsebiblioteket.admin.domain.list;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.key.UserRoleKey;

@SuppressWarnings("serial")
public class UserListItem implements Serializable {
	private Integer id;
	private String name;
	private String username;
	private String[] roleNames;
	private String organizationName;
	private UserRoleKey[] roleKeys;
	private String[] roleKeyValuesAsStrings;
	
	@Override
	public String toString() {
		return "[" + id + ": " + username + ", " + name + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String[] getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String[] roleNames) {
		this.roleNames = roleNames;
	}
	public UserRoleKey[] getRoleKeys() {
		return roleKeys;
	}
	public void setRoleKeys(UserRoleKey[] roleKeys) {
		this.roleKeys = roleKeys;
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
	public String[] getRoleKeyValuesAsStrings() {
		return roleKeyValuesAsStrings;
	}
	public void setRoleKeyValuesAsStrings(String[] roleKeyValuesAsStrings) {
		this.roleKeyValuesAsStrings = roleKeyValuesAsStrings;
	}
}
