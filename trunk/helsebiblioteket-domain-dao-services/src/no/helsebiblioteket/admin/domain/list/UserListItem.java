package no.helsebiblioteket.admin.domain.list;

import no.helsebiblioteket.admin.domain.key.UserRoleKey;

public class UserListItem {
	private Integer id;
	private String name;
	private String username;
	private String[] roleNames;
	private UserRoleKey[] roleKeys;
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
}
