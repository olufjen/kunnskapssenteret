package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.User;

@SuppressWarnings("serial")
public class ValueResultUser extends SingleResultUser implements Serializable {
	private User value;
	public ValueResultUser() { }
	public ValueResultUser(User value){
		this.value = value;
	}
	public User getValue() {
		return value;
	}
	public void setValue(User value) {
		this.value = value;
	}
}
