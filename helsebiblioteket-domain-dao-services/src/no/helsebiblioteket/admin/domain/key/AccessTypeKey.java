package no.helsebiblioteket.admin.domain.key;

public class AccessTypeKey {
	// TODO: Insert something here!
	public static final AccessTypeKey dummy = new AccessTypeKey("dummy");

	
	public static final AccessTypeKey general = new AccessTypeKey("general");

	private String value;
	public AccessTypeKey() { }
	public AccessTypeKey(String value) { this.value = value; }
	public String getValue() { return value; }
	public void setValue(String value) { this.value = value; }
}
