package no.helsebiblioteket.admin.domain.list;

import java.util.List;

import no.helsebiblioteket.admin.domain.key.UserRoleKey;

public class UserListItem {
	private String name;
	private String username;
	private List<String> roleNames;
	private List<UserRoleKey> roleKeys;
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
	public List<String> getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}
	public List<UserRoleKey> getRoleKeys() {
		return roleKeys;
	}
	public void setRoleKeys(List<UserRoleKey> roleKeys) {
		this.roleKeys = roleKeys;
	}
}
