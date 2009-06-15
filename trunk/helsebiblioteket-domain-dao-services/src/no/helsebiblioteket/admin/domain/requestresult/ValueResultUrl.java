package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.Url;

@SuppressWarnings("serial")
public class ValueResultUrl extends SingleResultUrl implements Serializable {
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
