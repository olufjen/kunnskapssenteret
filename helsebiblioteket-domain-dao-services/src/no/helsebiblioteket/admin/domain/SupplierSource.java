package no.helsebiblioteket.admin.domain;

import java.util.Date;

public class SupplierSource {
	// Primary key
	private Integer id;
	
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
		setId(id);
		this.supplierSourceName = name;
		this.url = url;
	}

	// Helpers
	@Override
	public String toString() {
		return "[" + id + ", " + supplierSourceName + "]";
	}

	// GET/SET
	public Integer getId(){ return id; }
	public void setId(Integer id){ this.id = id; }
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
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
}
