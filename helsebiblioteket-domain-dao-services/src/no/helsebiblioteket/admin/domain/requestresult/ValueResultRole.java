package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.Role;

@SuppressWarnings("serial")
public class ValueResultRole extends SingleResultRole implements Serializable {
	private Role value;
	public ValueResultRole() { }
	public ValueResultRole(Role value){
		this.value = value;
	}
	public Role getValue() {
		return value;
	}
	public void setValue(Role value) {
		this.value = value;
	}
}
