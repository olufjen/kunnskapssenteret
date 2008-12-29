package no.helsebiblioteket.admin.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Organization {
	private Integer id;
	private Organization parent = null;
	private String description = "";
	private OrganizationType type = new OrganizationType();
	private List<IpRange> ipRangeList = new ArrayList<IpRange>();
	private Person contactPerson = new Person();
	private ContactInformation contactInformation = new ContactInformation();
	private List<Access> accessList = new ArrayList<Access>();
	private Date lastChanged = null;
	private List<OrganizationName> nameList = new ArrayList<OrganizationName>();
	
	public Organization() {
	}
	
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

	public List<OrganizationName> getNameList() {
		return nameList;
	}

	public void setNameList(List<OrganizationName> nameList) {
		this.nameList = nameList;
	}
	
	/**
	 * @deprecated
	 */
	public String getName() {
		return "Deprecated";
	}
	
	/**
	 * @deprecated
	 */
	public void setName(String name) {
		// deprecated
	}
	
	/*
	 * Creates a map of "organization names" for a given category.
	 * Examples: a map of short names indexed on locale or a map of normal names indexed on locale.
	 */
	public Map<String, OrganizationName> getNameMap(OrganizationNameCategory orgNameCat) {
		Map<String, OrganizationName> orgNameMap = new HashMap<String, OrganizationName>();
		for (OrganizationName orgName: nameList) {
			if (orgName.getCategory().equals(orgNameCat)) {
				orgNameMap.put(orgName.getLocale().toString(), orgName);
			}
		}
		return orgNameMap;
	}
	
	public int hashCode() {
        int result;
        result = (parent != null ? parent.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (nameList != null ? nameList.hashCode() : 0);
        return result;
    }
	
	public boolean equals(Organization org) {
        if (this == org) return true;
        if (parent != null ? !parent.equals(org.parent) : org.parent != null) return false;
        if (type != null ? !type.equals(org.type) : org.type != null) return false;
        if (nameList != null ? !nameList.equals(org.nameList) : org.nameList != null) return false;
        return true;
    }
}