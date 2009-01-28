package no.helsebiblioteket.admin.domain;

import no.helsebiblioteket.admin.domain.key.PositionTypeKey;

public class Position {
	// Primary key
	private Integer positionTypeId;
	
	// Unique value
	private PositionTypeKey key;
	
	// Local values
	private String name;
	private String description;
	
	// References
	private OrganizationType organizationType;
	
	// GET/SET
	public PositionTypeKey getKey() { return key; }
	public void setKey(PositionTypeKey key) { this.key = key; }
	public Integer getPositionTypeId() {
		return positionTypeId;
	}
	public void setPositionTypeId(Integer id) {
		this.positionTypeId = id;
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
