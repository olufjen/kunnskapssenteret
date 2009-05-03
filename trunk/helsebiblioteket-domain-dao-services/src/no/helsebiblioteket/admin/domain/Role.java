package no.helsebiblioteket.admin.domain;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.key.UserRoleKey;

public class Role implements Serializable {
	// Primary key
	private Integer id;

	// Unique value
	// Key in combination with system.key
	private UserRoleKey key;
	
	// Local values
	private String name;
	private String description;
	
	// References
	private System system;

	// Helpers
	@Override
	public String toString() {
		return "[" + id + ", " + key + ", " + name + ", " + system + "]";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
