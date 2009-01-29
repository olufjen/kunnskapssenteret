package no.helsebiblioteket.admin.domain;

public class OrganizationAccess extends Access {
	// References
	private Organization organization;

	// GET/SET
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
