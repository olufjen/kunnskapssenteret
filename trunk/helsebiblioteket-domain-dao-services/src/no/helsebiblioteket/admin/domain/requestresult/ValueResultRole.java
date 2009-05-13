package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.Role;

@SuppressWarnings("serial")
public class ValueResultRole extends SingleResultRole{
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
