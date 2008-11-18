package no.helsebiblioteket.admin.domain;

public class Resource {
	private Integer id = null;
	private ResourceType resourceType = null;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ResourceType getResourceType() {
		return resourceType;
	}
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
}