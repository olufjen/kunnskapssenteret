package no.helsebiblioteket.admin.dao.join;

import no.helsebiblioteket.admin.domain.category.LanguageCategory;
import no.helsebiblioteket.admin.domain.category.OrganizationNameCategory;
import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;

public class OrgUnitNameJoin {
	private String organizationDescription;
	private Integer orgUnitId;
	private String organizationTypeName;
	private OrganizationTypeKey organizationTypeKey;
	private String organizationTypeDescription;
	private String organizationName;
	private LanguageCategory nameLanguage;
	private OrganizationNameCategory nameCategory;
	public String getOrganizationDescription() {
		return organizationDescription;
	}
	public void setOrganizationDescription(String organizationDescription) {
		this.organizationDescription = organizationDescription;
	}
	public Integer getOrgUnitId() {
		return orgUnitId;
	}
	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}
	public String getOrganizationTypeName() {
		return organizationTypeName;
	}
	public void setOrganizationTypeName(String organizationTypeName) {
		this.organizationTypeName = organizationTypeName;
	}
	public OrganizationTypeKey getOrganizationTypeKey() {
		return organizationTypeKey;
	}
	public void setOrganizationTypeKey(OrganizationTypeKey organizationTypeKey) {
		this.organizationTypeKey = organizationTypeKey;
	}
	public String getOrganizationTypeDescription() {
		return organizationTypeDescription;
	}
	public void setOrganizationTypeDescription(String organizationTypeDescription) {
		this.organizationTypeDescription = organizationTypeDescription;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public LanguageCategory getNameLanguage() {
		return nameLanguage;
	}
	public void setNameLanguage(LanguageCategory nameLanguage) {
		this.nameLanguage = nameLanguage;
	}
	public OrganizationNameCategory getNameCategory() {
		return nameCategory;
	}
	public void setNameCategory(OrganizationNameCategory nameCategory) {
		this.nameCategory = nameCategory;
	}
}
