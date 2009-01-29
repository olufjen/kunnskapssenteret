package no.helsebiblioteket.admin.domain.line;

import no.helsebiblioteket.admin.domain.category.LanguageCategory;
import no.helsebiblioteket.admin.domain.category.OrganizationNameCategory;

public class OrgUnitNameLine {
	private Integer orgUnitNameid;
	private Integer orgUnitId;
	private LanguageCategory languageCode;
	private String name;
	private OrganizationNameCategory orgUnitNameCategory;

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
