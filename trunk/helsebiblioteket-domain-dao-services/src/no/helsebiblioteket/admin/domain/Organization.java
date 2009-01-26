package no.helsebiblioteket.admin.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Organization {
	private Integer id;
	private Organization parent = null;
	private String description = null;
	private OrganizationType type = null;
	private List<IpRange> ipRangeList = null;
	private Person contactPerson = null;
	private ContactInformation contactInformation = null;
	private List<Access> accessList = null;
	private Date lastChanged = null;
	private List<OrganizationName> nameList = null;
	private List<SupplierSource> supplierSourceList = null;
	
	public static String findName(Organization organization, String language, OrganizationNameCategory category){
		if (organization.nameList != null) {
			for (OrganizationName name : organization.nameList) {
				if(name.getLanguageCode().equals(language) &&
						name.getCategory().equals(category)){
					return name.getName();
				}
			}
		}
		// TODO: How to handle error here?
		return "";
	}

	public static void insertName(Organization organization, String language, OrganizationNameCategory category, String name){
		boolean set = false;
		organization.setNameList(organization.getNameList() == null ? new ArrayList<OrganizationName>() : organization.getNameList());
		for (OrganizationName organizationName : organization.nameList) {
			if(organizationName.getLanguageCode().equals(language) &&
					organizationName.getCategory().equals(category)){
				organizationName.setName(name);
				set = true;
			}
		}
		// TODO: This should probably _never_ happen?
		// LTG - this occurs when a new organizationname is created (i.a: when a new organization is created)
		if( ! set ) {
			OrganizationName newOrganizationName = new OrganizationName();
			newOrganizationName.setLanguageCode(language);
			newOrganizationName.setCategory(category);
			newOrganizationName.setName(name);
			organization.nameList.add(newOrganizationName);
		}
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
	
	public String getNameEnglish() {
		// TODO: isn't this enough?
		return findName(this, "en", OrganizationNameCategory.NORMAL);
		
//		String name = null;
//		if (nameList != null) {
//			for (OrganizationName orgName : nameList) {
//				if ("en".equals(orgName.getLanguageCode()) && OrganizationNameCategory.NORMAL.toString().equals(orgName.getCategory())) {
//					name = orgName.getName();
//					break;
//				}
//			}
//		}
//		return name;
	}
	
	public String getNameNorwegian() {
		// TODO: isn't this enough?
		return findName(this, "no", OrganizationNameCategory.NORMAL);
//		String name = null;
//		if (nameList != null) {
//			for (OrganizationName orgName : nameList) {
//				if ("no".equals(orgName.getLanguageCode()) && OrganizationNameCategory.NORMAL.equals(orgName.getCategory())) {
//					name = orgName.getName();
//					break;
//				}
//			}
//		}
//		return name;
	}
	
	public String getNameShortEnglish() {
		String name = null;
		if (nameList != null) {
			for (OrganizationName orgName : nameList) {
				if ("en".equals(orgName.getLanguageCode()) && OrganizationNameCategory.SHORT.toString().equals(orgName.getCategory())) {
					name = orgName.getName();
					break;
				}
			}
		}
		return name;
	}
	
	public String getNameShortNorwegian() {
		String name = null;
		if (nameList != null) {
			for (OrganizationName orgName : nameList) {
				if ("no".equals(orgName.getLanguageCode()) && OrganizationNameCategory.SHORT.toString().equals(orgName.getCategory())) {
					name = orgName.getName();
					break;
				}
			}
		}
		return name;
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
