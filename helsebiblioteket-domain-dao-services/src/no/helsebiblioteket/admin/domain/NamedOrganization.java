package no.helsebiblioteket.admin.domain;

public class NamedOrganization extends Organization {
//	private String nameEnglish;
//	private String nameShortEnglish;
//	private String nameNorwegian;
//	private String nameShortNorwegian;
	
	// TODO: Do it this way with getters and setters?
	// TODO: And are the codes right?
	public String getNameEnglish() {
		return findName(this, "en", OrganizationNameCategory.NORMAL);
	}
	public void setNameEnglish(String nameEnglish) {
		insertName(this, "en", OrganizationNameCategory.NORMAL, nameEnglish);
	}
	public String getNameShortEnglish() {
		return findName(this, "en", OrganizationNameCategory.SHORT);
	}
	public void setNameShortEnglish(String nameShortEnglish) {
		insertName(this, "en", OrganizationNameCategory.SHORT, nameShortEnglish);
	}
	public String getNameNorwegian() {
		return findName(this, "no", OrganizationNameCategory.NORMAL);
	}
	public void setNameNorwegian(String nameNorwegian) {
		insertName(this, "no", OrganizationNameCategory.NORMAL, nameNorwegian);
	}
	public String getNameShortNorwegian() {
		return findName(this, "no", OrganizationNameCategory.SHORT);
	}
	public void setNameShortNorwegian(String nameShortNorwegian) {
		insertName(this, "no", OrganizationNameCategory.SHORT, nameShortNorwegian);
	}
}
