package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.Organization;

public class ValueResultOrganization extends SingleResultOrganization{
	private Organization value;
	public ValueResultOrganization() { }
	public ValueResultOrganization(Organization value){
		this.value = value;
	}
	public Organization getValue() {
		return value;
	}
	public void setValue(Organization value) {
		this.value = value;
	}
}
