package no.helsebiblioteket.admin.domain;

import java.util.Date;
import java.util.List;

public class Resource {
	private Integer resourceId;
	private ResourceType resourceType;
	private Date lastChanged;
	private Integer supplierSourceId;
	private List<SupplierSource> supplierSourceList;

	// foreign keys
	private Integer organizationId;
	
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
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	public Integer getSupplierSourceId() {
		return supplierSourceId;
	}
	public void setSupplierSourceId(Integer supplierSourceId) {
		this.supplierSourceId = supplierSourceId;
	}
}
