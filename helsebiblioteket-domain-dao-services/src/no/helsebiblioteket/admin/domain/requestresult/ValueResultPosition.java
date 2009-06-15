package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.Position;

@SuppressWarnings("serial")
public class ValueResultPosition extends SingleResultPosition implements Serializable {
	private Position value;
	public ValueResultPosition() { }
	public ValueResultPosition(Position value){
		this.value = value;
	}
	public Position getValue() {
		return value;
	}
	public void setValue(Position value) {
		this.value = value;
	}
}
