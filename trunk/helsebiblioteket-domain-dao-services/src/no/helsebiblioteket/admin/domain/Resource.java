package no.helsebiblioteket.admin.domain;

import java.util.Date;

public class Resource {
	// Primary key
	private Integer resourceId;
	
	// Local values
	private Date lastChanged;
	
	// References
	private ResourceType resourceType;

	// GET/SET
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer id) {
		this.resourceId = id;
	}
	public ResourceType getResourceType() {
		return resourceType;
	}
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
}
