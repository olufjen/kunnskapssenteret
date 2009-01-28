package no.helsebiblioteket.admin.domain.line;

import no.helsebiblioteket.admin.domain.category.LanguageCategory;
import no.helsebiblioteket.admin.domain.category.OrganizationNameCategory;

public class OrgUnitNameLine {
	// Primary key
	private Integer orgUnitNameid;

	// Table values
	private Integer orgUnitId;
	private LanguageCategory languageCode;
	private String name;
	private OrganizationNameCategory orgUnitNameCategory;
	
	// Helpers
	// TODO: Remove?
	public boolean equals(OrgUnitNameLine orgName) {
        if (this == orgName) return true;
        if (name != null ? !name.equals(orgName.name) : orgName.name != null) return false;
        if (languageCode != null ? !languageCode.equals(orgName.languageCode) : orgName.languageCode != null) return false;
        if (orgUnitNameCategory != null ? !orgUnitNameCategory.equals(orgName.orgUnitNameCategory) : orgName.orgUnitNameCategory!= null) return false;
        return true;
    }
	public int hashCode() {
        int result;
        result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (languageCode != null ? languageCode.hashCode() : 0);
        result = 31 * result + (orgUnitNameCategory != null ? orgUnitNameCategory.hashCode() : 0);
        return result;
    }
	
	// GET/SET
	public Integer getOrgUnitNameid() {
		return orgUnitNameid;
	}
	public void setOrgUnitNameid(Integer orgUnitNameid) {
		this.orgUnitNameid = orgUnitNameid;
	}
	public LanguageCategory getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(LanguageCategory languageCode) {
		this.languageCode = languageCode;
	}
	public Integer getOrgUnitId() {
		return orgUnitId;
	}
	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public OrganizationNameCategory getOrgUnitNameCategory() {
		return orgUnitNameCategory;
	}
	public void setOrgUnitNameCategory(OrganizationNameCategory orgUnitNameCategory) {
		this.orgUnitNameCategory = orgUnitNameCategory;
	}
}
