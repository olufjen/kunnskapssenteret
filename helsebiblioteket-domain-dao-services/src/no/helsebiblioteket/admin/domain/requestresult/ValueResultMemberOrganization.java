package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.MemberOrganization;

@SuppressWarnings("serial")
public class ValueResultMemberOrganization extends SingleResultMemberOrganization{
	private MemberOrganization value;
	public ValueResultMemberOrganization() { }
	public ValueResultMemberOrganization(MemberOrganization value){
		this.value = value;
	}
	public MemberOrganization getValue() {
		return value;
	}
	public void setValue(MemberOrganization value) {
		this.value = value;
	}
}
