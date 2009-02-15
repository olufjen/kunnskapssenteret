package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.System;

public class ValueResultSystem extends SingleResultSystem{
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
