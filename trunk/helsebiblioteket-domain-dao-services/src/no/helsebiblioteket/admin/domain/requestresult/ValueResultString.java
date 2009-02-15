package no.helsebiblioteket.admin.domain.requestresult;

public class ValueResultString extends SingleResultString{
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
