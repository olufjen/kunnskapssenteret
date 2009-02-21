package no.helsebiblioteket.admin.domain;

import no.helsebiblioteket.admin.domain.key.SystemKey;

public class System {
	// Primary key
	private Integer id;

	// Unique value
	private SystemKey key;

	// Local values
	private String name;
	private String description;
	
	// Helpers
	@Override
	public String toString() {
		return "[" + id + ", " + key + ", " + name + "]";
	}
	
	// GET/SET
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public SystemKey getKey() {
		return key;
	}
	public void setKey(SystemKey key) {
		this.key = key;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
