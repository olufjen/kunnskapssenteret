package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.Organization;

@SuppressWarnings("serial")
public class ValueResultOrganization extends SingleResultOrganization {
	// TODO Fase2: Remove class.
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
