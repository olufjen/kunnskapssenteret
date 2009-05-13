package no.helsebiblioteket.admin.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Url implements Serializable {
	// setStringValue is used by database!
	private String stringValue;
	public Url() { super(); }

	public Url(String value) {
		this();
		this.stringValue = value;
	}
	
	// Helpers
	@Override
	public String toString() {
		return "[" + stringValue + "]";
	}

	public String getStringValue() {
		return stringValue;
	}
	public void setStringValue(String value) {
		this.stringValue = value;
	}
}
