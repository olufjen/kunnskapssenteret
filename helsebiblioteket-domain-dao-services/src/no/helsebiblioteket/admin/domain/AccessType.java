package no.helsebiblioteket.admin.domain;

import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;

public class AccessType {
	// Primary key
	private Integer accessTypeid;

	// Unique key
	private AccessTypeKey key;
	
	// Local values
	private String description;
	private String name;
	private AccessTypeCategory category;
	
	public Integer getAccessTypeId() {
		return accessTypeid;
	}
	public void setAccessTypeId(Integer id) {
		this.accessTypeid = id;
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
	public AccessTypeKey getKey() {
		return key;
	}
	public void setKey(AccessTypeKey key) {
		this.key = key;
	}
	public AccessTypeCategory getCategory() {
		return category;
	}
	public void setCategory(AccessTypeCategory category) {
		this.category = category;
	}
}
