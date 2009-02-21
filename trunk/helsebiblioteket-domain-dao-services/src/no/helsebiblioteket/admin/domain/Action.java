package no.helsebiblioteket.admin.domain;

public class Action {
	private Integer id;
	private Resource resource;
	private AccessType accessType;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	public AccessType getAccessType() {
		return accessType;
	}
	public void setAccessType(AccessType accessType) {
		this.accessType = accessType;
	}
}
