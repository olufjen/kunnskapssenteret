package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.Position;

@SuppressWarnings("serial")
public class ValueResultPosition extends SingleResultPosition{
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
