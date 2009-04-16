package no.helsebiblioteket.admin.domain.list;

import no.helsebiblioteket.admin.domain.key.OrganizationTypeKey;

public class OrganizationListItem {
	private Integer id;
	private String nameEnglish;
	private String nameShortEnglish;
	private String nameNorwegian;
	private String nameShortNorwegian;
	private String typeText;
	private String typeKey;
	private OrganizationTypeKey key;
	private String[] ipAddressesFrom;
	private String[] ipAddressesTo;

	public String toString() {
		return "[" + id + "] " + key + ", nameEnglish=" + nameEnglish;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNameEnglish() {
		return nameEnglish;
	}
	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}
	public String getNameShortEnglish() {
		return nameShortEnglish;
	}
	public void setNameShortEnglish(String nameShortEnglish) {
		this.nameShortEnglish = nameShortEnglish;
	}
	public String getNameNorwegian() {
		return nameNorwegian;
	}
	public void setNameNorwegian(String nameNorwegian) {
		this.nameNorwegian = nameNorwegian;
	}
	public String getNameShortNorwegian() {
		return nameShortNorwegian;
	}
	public void setNameShortNorwegian(String nameShortNorwegian) {
		this.nameShortNorwegian = nameShortNorwegian;
	}
	public OrganizationTypeKey getKey() {
		return key;
	}
	public void setKey(OrganizationTypeKey key) {
		this.key = key;
	}
	public String getTypeText() {
		return typeText;
	}
	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}
	public String[] getIpAddressesFrom() {
		return ipAddressesFrom;
	}
	public void setIpAddressesFrom(String[] ipAddressesFrom) {
		this.ipAddressesFrom = ipAddressesFrom;
	}
	public String[] getIpAddressesTo() {
		return ipAddressesTo;
	}
	public void setIpAddressesTo(String[] ipAddressesTo) {
		this.ipAddressesTo = ipAddressesTo;
	}
	public String getTypeKey() {
		return typeKey;
	}
	public void setTypeKey(String typeKey) {
		this.typeKey = typeKey;
	}
}
