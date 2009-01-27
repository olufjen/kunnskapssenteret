package no.helsebiblioteket.admin.domain;

public class SupplierSource extends Resource implements Unique{
	private Url url = null;
	private String name = null;
	
	public SupplierSource() {
	}
	
	public Integer getId(){
		return getSupplierSourceId();
	}
	public void setId(Integer id){
		this.setSupplierSourceId(id);
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
