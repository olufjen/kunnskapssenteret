package no.helsebiblioteket.admin.domain;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;

@SuppressWarnings("serial")
public class ResourceType implements Serializable {
	// Primary key
	private Integer id;

	// Unique value
	private ResourceTypeKey key;

	// Local values
	private String description;
	private String name;

	// Helpers
	@Override
	public String toString() {
		return "[" + id + ", " + key + ": " + description + "]";
	}

	
	// GET/SET
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ResourceTypeKey getKey() {
		return key;
	}
	public void setKey(ResourceTypeKey key) {
		this.key = key;
	}
}
