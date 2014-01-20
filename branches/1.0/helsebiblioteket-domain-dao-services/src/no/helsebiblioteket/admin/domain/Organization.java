package no.helsebiblioteket.admin.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Organization implements Serializable {
	// Primary key
	private Integer id;
	
	// Local values
	private String description;
	private String accessDomain;
	private Date lastChanged;
	// These are set directly. Actually loaded from OrganizationName.
	private String nameEnglishNormal;
	private String nameEnglishShort;
	private String nameNorwegianNormal;
	private String nameNorwegianShort;

	// References
	private OrganizationType type;
	private Person contactPerson = new Person();
	private ContactInformation contactInformation;
	private ContactInformation supportInformation;

	// Helpers
	@Override
	public String toString() {
		return id + ", " + description + ", " + type;
	}
	public int hashCode() {
        int result;
        result = (type != null ? type.hashCode() : 0);
        return result;
    }
	public boolean equals(Organization org) {
        if (this == org) return true;
        if (type != null ? !type.equals(org.type) : org.type != null) return false;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getAccessDomain() {
		return accessDomain;
	}
	public void setAccessDomain(String accessDomain) {
		this.accessDomain = accessDomain;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
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
	public ContactInformation getSupportInformation() {
		return supportInformation;
	}
	public void setSupportInformation(ContactInformation supportInformation) {
		this.supportInformation = supportInformation;
	}
}