package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.AccessType;

@SuppressWarnings("serial")
public class ValueResultAccessType extends SingleResultAccessType{
	private AccessType value;
	public ValueResultAccessType() { }
	public ValueResultAccessType(AccessType value){
		this.value = value;
	}
	public AccessType getValue() {
		return value;
	}
	public void setValue(AccessType value) {
		this.value = value;
	}
}
