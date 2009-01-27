package no.helsebiblioteket.admin.domain;

import java.util.Date;
import java.util.List;

import no.helsebiblioteket.admin.listobjects.OrganizationListItem;

public class Organization implements OrganizationListItem{
	private Integer id;
	private String description;
	private Date lastChanged;
	private String nameEnglishNormal;
	private String nameEnglishShort;
	private String nameNorwegianNormal;
	private String nameNorwegianShort;

	private List<IpRange> ipRangeList;
	private List<Access> accessList;
	private List<SupplierSource> supplierSourceList;

	private Organization parent;
	private OrganizationType type;
	private Person contactPerson;
	private ContactInformation contactInformation;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<IpRange> getIpRangeList() {
		return ipRangeList;
	}
	public void setIpRangeList(List<IpRange> ipRangeList) {
		this.ipRangeList = ipRangeList;
	}
	public Organization getParent() {
		return parent;
	}
	public void setParent(Organization parent) {
		this.parent = parent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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

	public List<Access> getAccessList() {
		return accessList;
	}

	public void setAccessList(List<Access> accessList) {
		this.accessList = accessList;
	}

	public Date getLastChanged() {
		return lastChanged;
	}

	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
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
	
	public List<SupplierSource> getSupplierSourceList() {
		return supplierSourceList;
	}

	public void setSupplierSourceList(List<SupplierSource> supplierSourceList) {
		this.supplierSourceList = supplierSourceList;
	}

	public int hashCode() {
        int result;
        result = (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        // TODO: Include names here!
        return result;
    }
	
	public boolean equals(Organization org) {
        if (this == org) return true;
        if (parent != null ? !parent.equals(org.parent) : org.parent != null) return false;
        if (type != null ? !type.equals(org.type) : org.type != null) return false;
        // TODO: Compare names here!
        return true;
    }
	public void setNameEnglishNormal(String nameEnglishNormal) {
		this.nameEnglishNormal = nameEnglishNormal;
	}
	public void setNameEnglishShort(String nameEnglishShort) {
		this.nameEnglishShort = nameEnglishShort;
	}
	public void setNameNorwegianNormal(String nameNorwegianNormal) {
		this.nameNorwegianNormal = nameNorwegianNormal;
	}
	public void setNameNorwegianShort(String nameNorwegianShort) {
		this.nameNorwegianShort = nameNorwegianShort;
	}
}
