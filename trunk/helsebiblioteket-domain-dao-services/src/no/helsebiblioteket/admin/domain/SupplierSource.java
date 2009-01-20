package no.helsebiblioteket.admin.domain;

public class SupplierSource extends Resource {
	private Integer supplierSourceId = null;
	private String url = null;
	private String name = null;
	
	public SupplierSource() {
	}
	
	public SupplierSource(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	public SupplierSource(Integer id, String name, String url) {
		this.supplierSourceId = id;
		this.name = name;
		this.url = url;
	}
	
	public Integer getSupplierSourceId() {
		return this.supplierSourceId;
	}
	public void setSupplierSourceId(Integer id) {
		this.supplierSourceId = id;
	}
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
}