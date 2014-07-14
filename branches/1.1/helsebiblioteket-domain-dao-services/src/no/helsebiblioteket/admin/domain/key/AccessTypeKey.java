package no.helsebiblioteket.admin.domain.key;

public class AccessTypeKey {
	public static final AccessTypeKey general = new AccessTypeKey("general");
	public static final AccessTypeKey proxy_include = new AccessTypeKey("proxy_include");
	public static final AccessTypeKey proxy_exclude = new AccessTypeKey("proxy_exclude");
	public static final AccessTypeKey proxy_include_all = new AccessTypeKey("proxy_include_all");
	public static final AccessTypeKey proxy_exclude_all = new AccessTypeKey("proxy_exclude_all");

	private String value;
	public AccessTypeKey() { }
	public AccessTypeKey(String value) { this.value = value; }
	public String getValue() { return value; }
	public void setValue(String value) { this.value = value; }
	
	public boolean equals(Object object) {
		boolean ret = false;
		if (object instanceof AccessTypeKey && object != null) {
			return (((AccessTypeKey) object).getValue().equals(this.getValue()));
		}
		return ret;
	}
}