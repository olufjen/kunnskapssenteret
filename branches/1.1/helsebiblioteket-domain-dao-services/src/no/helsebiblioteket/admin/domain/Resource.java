package no.helsebiblioteket.admin.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Resource implements Serializable {
	// Primary key
	private Integer id;
	
	// Local values
	private Date lastChanged;
	private boolean deleted = false;

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
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}