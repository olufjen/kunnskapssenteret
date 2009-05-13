package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.OrganizationUser;

@SuppressWarnings("serial")
public class ValueResultOrganizationUser extends SingleResultUser{
	private OrganizationUser value;
	public ValueResultOrganizationUser() { }
	public ValueResultOrganizationUser(OrganizationUser value){
		this.value = value;
	}
	public OrganizationUser getValue() {
		return value;
	}
	public void setValue(OrganizationUser value) {
		this.value = value;
	}
}
