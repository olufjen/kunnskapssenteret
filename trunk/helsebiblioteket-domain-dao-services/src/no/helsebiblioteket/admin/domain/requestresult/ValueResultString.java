package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ValueResultString extends SingleResultString implements Serializable {
	private String value;
	public ValueResultString() { }
	public ValueResultString(String value){
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
