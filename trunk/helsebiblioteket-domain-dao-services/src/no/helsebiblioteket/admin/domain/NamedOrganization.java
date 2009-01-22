package no.helsebiblioteket.admin.domain;

public class NamedOrganization extends Organization {
//	private String nameEnglish;
//	private String nameShortEnglish;
//	private String nameNorwegian;
//	private String nameShortNorwegian;
	
	// TODO: Do it this way with getters and setters?
	// TODO: And are the codes right?
	public String getNameEnglish() {
		return this.findName("en", OrganizationNameCategory.NORMAL);
	}
	public void setNameEnglish(String nameEnglish) {
		this.insertName("en", OrganizationNameCategory.NORMAL, nameEnglish);
	}
	public String getNameShortEnglish() {
		return this.findName("en", OrganizationNameCategory.SHORT);
	}
	public void setNameShortEnglish(String nameShortEnglish) {
		this.insertName("en", OrganizationNameCategory.SHORT, nameShortEnglish);
	}
	public String getNameNorwegian() {
		return this.findName("no", OrganizationNameCategory.NORMAL);
	}
	public void setNameNorwegian(String nameNorwegian) {
		this.insertName("no", OrganizationNameCategory.NORMAL, nameNorwegian);
	}
	public String getNameShortNorwegian() {
		return this.findName("no", OrganizationNameCategory.SHORT);
	}
	public void setNameShortNorwegian(String nameShortNorwegian) {
		this.insertName("no", OrganizationNameCategory.SHORT, nameShortNorwegian);
	}
}
