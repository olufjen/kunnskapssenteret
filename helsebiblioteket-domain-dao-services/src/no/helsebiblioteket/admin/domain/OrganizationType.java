package no.helsebiblioteket.admin.domain;

public class OrganizationType {
	private Integer id;
	private String name;
	private String description;
	private OrganizationTypeKey key;

	public OrganizationType() {
	}
	
	public OrganizationType(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
}
