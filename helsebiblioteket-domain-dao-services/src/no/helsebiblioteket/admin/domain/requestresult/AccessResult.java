package no.helsebiblioteket.admin.domain.requestresult;

public class AccessResult {
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
}
