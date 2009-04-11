package no.helsebiblioteket.admin.domain;

import java.util.Date;

public class Resource {
	// Primary key
	private Integer id;
	
	// Local values
	private Date lastChanged;
	
	// References
	private ResourceType resourceType;
	private Integer offeredBy;

	// Helpers
	@Override
	public String toString() {
		return "[" + id + ", " + resourceType + "]";
	}

	// GET/SET
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
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
	public Integer getOfferedBy() {
		return offeredBy;
	}
	public void setOfferedBy(Integer supplierOrganization) {
		this.offeredBy = supplierOrganization;
	}
}
