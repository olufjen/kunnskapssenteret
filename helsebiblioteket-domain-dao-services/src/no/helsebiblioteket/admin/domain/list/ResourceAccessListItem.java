package no.helsebiblioteket.admin.domain.list;

import java.util.Date;

import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;

public class ResourceAccessListItem {
	private Integer id;
	private AccessTypeCategory category;
	private AccessTypeKey key;
	private Url url;
	private Date lastChanged;
	private String supplierSourceName;
	private Integer providedBy;
	private Integer resourceId;
	private Integer supplierSourceId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public AccessTypeCategory getCategory() {
		return category;
	}
	public void setCategory(AccessTypeCategory category) {
		this.category = category;
	}
	public AccessTypeKey getKey() {
		return key;
	}
	public void setKey(AccessTypeKey key) {
		this.key = key;
	}
	public Url getUrl() {
		return url;
	}
	public void setUrl(Url url) {
		this.url = url;
	}
	public Date getLastChanged() {
		return lastChanged;
	}
	public void setLastChanged(Date lastChanged) {
		this.lastChanged = lastChanged;
	}
	public String getSupplierSourceName() {
		return supplierSourceName;
	}
	public void setSupplierSourceName(String supplierSourceName) {
		this.supplierSourceName = supplierSourceName;
	}
	public Integer getProvidedBy() {
		return providedBy;
	}
	public void setProvidedBy(Integer providedBy) {
		this.providedBy = providedBy;
	}
	public Integer getResourceId() {
		return resourceId;
	}
	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}
	public Integer getSupplierSourceId() {
		return supplierSourceId;
	}
	public void setSupplierSourceId(Integer supplierSourceId) {
		this.supplierSourceId = supplierSourceId;
	}
}
