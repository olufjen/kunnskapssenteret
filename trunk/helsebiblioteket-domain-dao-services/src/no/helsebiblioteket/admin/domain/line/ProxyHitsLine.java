package no.helsebiblioteket.admin.domain.line;

import java.util.Date;

public class ProxyHitsLine {
	// Primary key
	private Integer resourceId;

	// Table values
	private Integer supplierSourceId;
	private Integer resourceTypeId;
	private Integer orgUnitId;
	private Date lastChanged;
	
	// GET/SET
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer id) {
		this.resourceId = id;
	}
	public Integer getSupplierSourceId() {
		return supplierSourceId;
	}
	public void setSupplierSourceId(Integer supplierSourceId) {
		this.supplierSourceId = supplierSourceId;
	}
	public Integer getResourceTypeId() {
		return resourceTypeId;
	}
	public void setResourceTypeId(Integer resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}
	public Integer getOrgUnitId() {
		return orgUnitId;
	}
	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
}
