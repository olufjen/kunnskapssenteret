package no.helsebiblioteket.admin.domain;

import java.util.Date;

import no.helsebiblioteket.admin.domain.base.Identifiable;
import no.helsebiblioteket.admin.domain.category.LanguageCategory;
import no.helsebiblioteket.admin.domain.category.OrganizationNameCategory;

public class OrganizationName implements Identifiable{
	// Primary key
	private Integer orgUnitNameId = null;
	
	// Local values
	private String name = null;
	private LanguageCategory languageCode = null;
	private OrganizationNameCategory category = null;
	private Date lastChanged = null;
	
	// Helpers
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

	public void setId(Integer orgUnitNameId) {
		this.orgUnitNameId = orgUnitNameId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LanguageCategory getLanguageCode() {
		return this.languageCode;
	}
	public void setLanguageCode(LanguageCategory languageCode) {
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

	public Integer getId() {
		return orgUnitNameId;
	}
}
