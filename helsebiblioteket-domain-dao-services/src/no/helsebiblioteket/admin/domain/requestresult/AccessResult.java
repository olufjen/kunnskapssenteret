package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

public class AccessResult implements Serializable {
	public static final AccessResult logup = new AccessResult("logup");
	public static final AccessResult include = new AccessResult("include");
	public static final AccessResult exclude = new AccessResult("exclude");
	
	private String value = "";
	
	public AccessResult() { }
	public AccessResult(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean equals(Object o) {
		boolean isEqual = false;
		if (o instanceof AccessResult) {
			String accessResultValue = null;
			accessResultValue = ((AccessResult) o).getValue();
			isEqual = (null != accessResultValue && getValue() != null && accessResultValue.equals(getValue()));
		}
		return isEqual;
	}
}
