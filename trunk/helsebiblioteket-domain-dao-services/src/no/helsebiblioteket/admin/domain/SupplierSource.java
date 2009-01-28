package no.helsebiblioteket.admin.domain;

import java.util.Date;

import no.helsebiblioteket.admin.domain.base.Identifiable;

public class SupplierSource implements Identifiable{
	// Primary key
	private Integer supplierSourceId;
	
	// Local values
	private String supplierSourceName;
	// url from database table is set to url.stringValue
	private Url url;
	private Date lastChanged;

	// Constructors
	public SupplierSource() { }
	public SupplierSource(String name, Url url) {
		this.supplierSourceName = name;
		this.url = url;
	}
	public SupplierSource(Integer id, String name, Url url) {
		setSupplierSourceId(id);
		this.supplierSourceName = name;
		this.url = url;
	}

	// Helpers
	public Integer getId(){ return getSupplierSourceId(); }
	public void setId(Integer id){ this.setSupplierSourceId(id); }
	
	public Url getUrl() {
		return this.url;
	}
	public void setUrl(Url url) {
		this.url = url;
	}
	public String getSupplierSourceName() {
		return this.supplierSourceName;
	}
	public void setSupplierSourceName(String supplierSourceName) {
		this.supplierSourceName = supplierSourceName;
	}
	public Integer getSupplierSourceId() {
		return supplierSourceId;
	}

	public void setSupplierSourceId(Integer supplierSourceId) {
		this.supplierSourceId = supplierSourceId;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
}
