package no.helsebiblioteket.admin.domain;

import java.util.Date;

public class Access {
	private Integer id = null;
	private SupplierSource supplierSource = null;
	private AccessType accessType = null;
	private Date validFrom = null;
	private Date validTo = null;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SupplierSource getSupplierSource() {
		return supplierSource;
	}
	public void setSupplierSource(SupplierSource supplierSource) {
		this.supplierSource = supplierSource;
	}
	public AccessType getAccessType() {
		return accessType;
	}
	public void setAccessType(AccessType accessType) {
		this.accessType = accessType;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
}
