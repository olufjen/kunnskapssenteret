package no.helsebiblioteket.admin.domain;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;

public class AccessType implements Serializable {
	// Primary key
	private Integer id;

	// Unique key is key & category in combination
	private AccessTypeKey key;
	private AccessTypeCategory category;
	
	// Local values
	private String description;
	private String name;
	
	// Helpers
	@Override
	public String toString() {
		return id + ": " + key + ", " + category;
	}
	
	// GET/SET
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
