package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.System;

@SuppressWarnings("serial")
public class ValueResultSystem extends SingleResultSystem implements Serializable {
	private System value;
	public ValueResultSystem() { }
	public ValueResultSystem(System value){
		this.value = value;
	}
	public System getValue() {
		return value;
	}
	public void setValue(System value) {
		this.value = value;
	}
}
