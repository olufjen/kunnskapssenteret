package no.helsebiblioteket.admin.domain.key;

public class SystemKey {
	public static final SystemKey helsebiblioteket_admin = new SystemKey("helsebiblioteket_admin");

	private String value;
	public SystemKey() { }
	public SystemKey(String value) { this.value = value; }
	public String getValue() { return value; }
	public void setValue(String value) { this.value = value; }
}
