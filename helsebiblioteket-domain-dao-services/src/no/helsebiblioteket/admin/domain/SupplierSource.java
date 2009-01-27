package no.helsebiblioteket.admin.domain;

public class SupplierSource extends Resource {
	private Url url = null;
	private String name = null;
	
	public SupplierSource() {
	}
	
	public SupplierSource(String name, Url url) {
		this.name = name;
		this.url = url;
	}
	
	public SupplierSource(Integer id, String name, Url url) {
		setSupplierSourceId(id);
		this.name = name;
		this.url = url;
	}
	
	public Url getUrl() {
		return this.url;
	}
	public void setUrl(Url url) {
		this.url = url;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
