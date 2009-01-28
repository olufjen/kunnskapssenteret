package no.helsebiblioteket.admin.domain;

import java.util.Date;

public abstract class Organization {
	// Primary key
	private Integer orgUnitId;
	
	// Local values
	private String description;
	private Date lastChanged;
	// These are set directly. Actually loaded from OrganizationName.
	private String nameEnglishNormal;
	private String nameEnglishShort;
	private String nameNorwegianNormal;
	private String nameNorwegianShort;

	// References
	private OrganizationType type;
	private Person contactPerson;
	private ContactInformation contactInformation;

	// Helpers
	public int hashCode() {
        int result;
        // TODO: What was this important for? Use Id
//        result = (parent != null ? parent.hashCode() : 0);
//        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = (type != null ? type.hashCode() : 0);
        // TODO: Include names here!
        return result;
    }
	public boolean equals(Organization org) {
        if (this == org) return true;
        // TODO: What was this important for? Use Id
//        if (parent != null ? !parent.equals(org.parent) : org.parent != null) return false;
        if (type != null ? !type.equals(org.type) : org.type != null) return false;
        // TODO: Compare names here!
        return true;
    }

	// If we want to use parent, this can be inserted
	// Should instead be an organization. Watch out for
	// loops and NullPointers!
//	private Integer parent;
//	public Integer getParent() {
//		return parent;
//	}
//	public void setParent(Integer parent) {
//		this.parent = parent;
//	}

	// GET/SET
	public Integer getOrgUnitId() {
		return orgUnitId;
	}
	public String getNameEnglish() {
		return this.nameEnglishNormal;
	}
	
	public String getNameNorwegian() {
		return this.nameNorwegianNormal;
	}
	public String getNameShortEnglish() {
		return nameEnglishShort;
	}
	
	public String getNameShortNorwegian() {
		return nameNorwegianShort;
	}
	
	public void setNameEnglish(String nameEnglishNormal) {
		this.nameEnglishNormal = nameEnglishNormal;
	}
	public void setNameShortEnglish(String nameEnglishShort) {
		this.nameEnglishShort = nameEnglishShort;
	}
	public void setNameNorwegian(String nameNorwegianNormal) {
		this.nameNorwegianNormal = nameNorwegianNormal;
	}
	public void setNameShortNorwegian(String nameNorwegianShort) {
		this.nameNorwegianShort = nameNorwegianShort;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
	public void setOrgUnitId(Integer id) {
		this.orgUnitId = id;
	}
	public OrganizationType getType() {
		return type;
	}
	public void setType(OrganizationType type) {
		this.type = type;
	}
	public Person getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(Person contactPerson) {
		this.contactPerson = contactPerson;
	}
	public ContactInformation getContactInformation() {
		return contactInformation;
	}
	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}
}
