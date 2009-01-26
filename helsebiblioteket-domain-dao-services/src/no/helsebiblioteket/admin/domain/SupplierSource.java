package no.helsebiblioteket.admin.domain;

public class SupplierSource extends Resource {
	// id moved to parent
	private String url = null;
	private String name = null;
	
	public SupplierSource() {
	}
	
	public SupplierSource(String name, String url) {
		this.name = name;
		this.url = url;
	}
	
	public SupplierSource(Integer id, String name, String url) {
		setSupplierSourceId(id);
		this.name = name;
		this.url = url;
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