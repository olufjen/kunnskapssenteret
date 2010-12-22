package no.helsebiblioteket.admin.domain.category;


public class AccessTypeCategory {
	public static final AccessTypeCategory GRANT = new AccessTypeCategory("GRANT");
	public static final AccessTypeCategory DENY = new AccessTypeCategory("DENY");

	private String value;
	public AccessTypeCategory() { }
	public AccessTypeCategory(String value) { this.value = value; }
	public String getValue() { return value; }
	public void setValue(String value) { this.value = value; }
	
	public boolean equals(Object object) {
		boolean ret = false;
		if (object instanceof AccessTypeCategory && object != null) {
			return (((AccessTypeCategory) object).getValue().equals(this.getValue()));
		}
		return ret;
	}
}
