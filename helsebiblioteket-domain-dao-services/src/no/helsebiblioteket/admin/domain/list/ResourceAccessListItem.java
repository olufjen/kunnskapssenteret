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
}
