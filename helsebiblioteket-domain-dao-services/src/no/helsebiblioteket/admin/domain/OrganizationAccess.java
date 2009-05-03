package no.helsebiblioteket.admin.domain;

import java.io.Serializable;

public class OrganizationAccess implements Serializable {
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
