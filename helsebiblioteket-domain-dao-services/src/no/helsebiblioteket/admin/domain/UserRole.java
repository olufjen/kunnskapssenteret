package no.helsebiblioteket.admin.domain;

import no.helsebiblioteket.admin.domain.key.UserRoleKey;

public class UserRole {
	// Primary key
	private Integer userRoleId;

	// Unique value
	// Key in combination with system.key
	private UserRoleKey key;
	
	// Local values
	private String name;
	private String description;
	
	// References
	private System system;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(Integer roleId) {
		this.userRoleId = roleId;
	}
	public UserRoleKey getKey() {
		return key;
	}
	public void setKey(UserRoleKey key) {
		this.key = key;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public System getSystem() {
		return system;
	}
	public void setSystem(System system) {
		this.system = system;
	}
}
