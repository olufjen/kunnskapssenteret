package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.Url;

public class ValueResultUrl extends SingleResultUrl {
	private Url value;
	public ValueResultUrl() { }
	public ValueResultUrl(Url value){
		this.value = value;
	}
	public Url getValue() {
		return value;
	}
	public void setValue(Url value) {
		this.value = value;
	}
}
