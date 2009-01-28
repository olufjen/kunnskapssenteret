package no.helsebiblioteket.admin.domain;

public class Url {
	// setStringValue is used by database!
	private String stringValue;
	public Url() { super(); }

	public Url(String value) {
		this();
		this.stringValue = value;
	}
	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String value) {
		this.stringValue = value;
	}
}
