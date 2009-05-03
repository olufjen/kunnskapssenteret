package no.helsebiblioteket.admin.domain;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.key.PositionTypeKey;

// TODO: Should this be named position type?
public class Position implements Serializable {
	// Primary key
	private Integer id;
	
	// Unique value
	private PositionTypeKey key = null;
	
	// Local values
	private String name;
	private String description;
	
	// References
	private OrganizationType organizationType;

	// Helpers
	@Override
	public String toString() {
		return "[" + id + ", " + key + "]";
	}

	// GET/SET
	public PositionTypeKey getKey() { return key; }
	public void setKey(PositionTypeKey key) { this.key = key; }
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
	public OrganizationType getOrganizationType() {
		return organizationType;
	}
	public void setOrganizationType(OrganizationType organizationType) {
		this.organizationType = organizationType;
	}
}
