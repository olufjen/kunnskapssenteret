package no.helsebiblioteket.admin.domain.key;

public class ResourceTypeKey {
	// TODO: Insert something here?

	public static final ResourceTypeKey supplier_source = new ResourceTypeKey("supplier_source");

	private String value;
	public ResourceTypeKey() { }
	public ResourceTypeKey(String value) { this.value = value; }
	public String getValue() { return value; }
	public void setValue(String value) {  this.value = value; }
}
