package no.helsebiblioteket.admin.domain;

public class LoggedInOrganization {
	private Integer id;
	private String nameEnglishNormal;
	private String nameEnglishShort;
	private String nameNorwegianNormal;
	private String nameNorwegianShort;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNameEnglishNormal() {
		return nameEnglishNormal;
	}
	public void setNameEnglishNormal(String nameEnglishNormal) {
		this.nameEnglishNormal = nameEnglishNormal;
	}
	public String getNameEnglishShort() {
		return nameEnglishShort;
	}
	public void setNameEnglishShort(String nameEnglishShort) {
		this.nameEnglishShort = nameEnglishShort;
	}
	public String getNameNorwegianNormal() {
		return nameNorwegianNormal;
	}
	public void setNameNorwegianNormal(String nameNorwegianNormal) {
		this.nameNorwegianNormal = nameNorwegianNormal;
	}
	public String getNameNorwegianShort() {
		return nameNorwegianShort;
	}
	public void setNameNorwegianShort(String nameNorwegianShort) {
		this.nameNorwegianShort = nameNorwegianShort;
	}
}
