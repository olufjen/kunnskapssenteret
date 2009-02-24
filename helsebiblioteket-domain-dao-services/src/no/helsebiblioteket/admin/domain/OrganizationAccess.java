package no.helsebiblioteket.admin.domain;

public class OrganizationAccess {
	// References
	private Organization organization;

	// Helpers
	@Override
	public String toString() {
		return super.toString() + " toOrg: " + organization;
	}

	// GET/SET
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
