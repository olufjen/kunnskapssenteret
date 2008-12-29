package no.helsebiblioteket.admin.domain;

public class Position {
	private Integer id;
	private String key;
	private String title;
	
	// foreign keys
	private Integer organizationTypeId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	public String getKey() { return key; }
	public void setKey(String key) { this.key = key; }
	public Integer getOrganizationTypeId() {
		return organizationTypeId;
	}
	public void setOrganizationTypeId(Integer organizationTypeId) {
		this.organizationTypeId = organizationTypeId;
	}
}
