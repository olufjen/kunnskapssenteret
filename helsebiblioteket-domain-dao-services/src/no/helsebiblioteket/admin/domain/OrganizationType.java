package no.helsebiblioteket.admin.domain;

import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;

public class OrganizationType {
	// Primary key
	private Integer orgTypeId;
	
	// Local values
	private String name;
	private String description;
	
	// References
	private OrganizationTypeKey key;

	// Helpers
	public boolean equals(OrganizationType orgType) {
        if (this == orgType) return true;
        if (key != null ? !key.equals(orgType.key) : orgType.key != null) return false;
        return true;
    }
	
	public int hashCode() {
        int result;
        result = (key != null ? key.hashCode() : 0);
        return result;
    }

	// Constructors
	public OrganizationType() { }
	public OrganizationType(Integer id) {
		this.orgTypeId = id;
	}
	
	public Integer getOrgTypeId() {
		return orgTypeId;
	}
	public void setOrgTypeId(Integer id) {
		this.orgTypeId = id;
	}
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
	public OrganizationTypeKey getKey() {
		return key;
	}
	public void setKey(OrganizationTypeKey key) {
		this.key = key;
	}
}
