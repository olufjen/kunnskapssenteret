package no.helsebiblioteket.admin.domain;

public class SupplierSource extends Resource {
	private Integer id = null;
	private String url = null;
	private String name = null;
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
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