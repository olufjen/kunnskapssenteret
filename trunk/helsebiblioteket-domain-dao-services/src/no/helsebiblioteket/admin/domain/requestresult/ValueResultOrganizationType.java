package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.OrganizationType;

@SuppressWarnings("serial")
public class ValueResultOrganizationType extends SingleResultOrganizationType{
	private OrganizationType value;
	public ValueResultOrganizationType() { }
	public ValueResultOrganizationType(OrganizationType value){
		this.value = value;
	}
	public OrganizationType getValue() {
		return value;
	}
	public void setValue(OrganizationType value) {
		this.value = value;
	}
}
