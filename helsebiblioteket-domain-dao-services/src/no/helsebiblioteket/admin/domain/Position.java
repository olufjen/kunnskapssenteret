package no.helsebiblioteket.admin.domain;

public class Position {
	private int id;
	private String key;
	private String name;
	private String description;
	
	// foreign keys
//	private Integer organizationTypeId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKey() { return key; }
	public void setKey(String key) { this.key = key; }
//	public Integer getOrganizationTypeId() {
//		return organizationTypeId;
//	}
//	public void setOrganizationTypeId(Integer organizationTypeId) {
//		this.organizationTypeId = organizationTypeId;
//	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
