package no.helsebiblioteket.admin.domain;

import java.util.Date;
import java.util.Locale;

public class OrganizationName {
	private Integer id = null;
	private String name = null;
	private String languageCode = null;
	private OrganizationNameCategory category = null;
	private Date lastChanged = null;
	
	// foreign keys
	private Integer organizationId;
	
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
	public String getLanguageCode() {
		return this.languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public OrganizationNameCategory getCategory() {
		return category;
	}
	public void setCategory(OrganizationNameCategory category) {
		this.category = category;
	}
	
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public boolean equals(OrganizationName orgName) {
        if (this == orgName) return true;
        if (name != null ? !name.equals(orgName.name) : orgName.name != null) return false;
        if (languageCode != null ? !languageCode.equals(orgName.languageCode) : orgName.languageCode != null) return false;
        if (category != null ? !category.equals(orgName.category) : orgName.category!= null) return false;
        return true;
    }
	
	public int hashCode() {
        int result;
        result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (languageCode != null ? languageCode.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
	public Object getLocale() {
		// TODO Auto-generated method stub
		return null;
	}
}
