package no.helsebiblioteket.admin.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class SupplierSource implements Serializable {
	// Primary key
	private Integer id;
	
	// Local values
	private String supplierSourceName;
	// url from database table is set to url.stringValue
	private Url url;
	private String proxyDatabaseName;
	private String host;
	private Date lastChanged;
	private boolean deleted = false;

	// Constructors
	public SupplierSource() { }
	public SupplierSource(String name, Url url, String proxyDatabaseName) {
		this.supplierSourceName = name;
		this.url = url;
		this.proxyDatabaseName = proxyDatabaseName;
	}
	public SupplierSource(Integer id, String name, Url url, String proxyDatabaseName) {
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
	public void setProxyDatabaseName(String proxyDatabaseName) {
		this.proxyDatabaseName = proxyDatabaseName;
	}
	public String getProxyDatabaseName() {
		return this.proxyDatabaseName;
	}
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
}