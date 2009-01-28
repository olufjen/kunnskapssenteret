package no.helsebiblioteket.admin.domain;

import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;

public class ResourceType {
	// Primary key
	private Integer resourceTypeId;

	// Unique value
	private ResourceTypeKey key;

	// Local values
	private String description;
	private String name;
	
	// GET/SET
	public Integer getId() {
		return resourceTypeId;
	}
	public void setId(Integer id) {
		this.resourceTypeId = id;
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
