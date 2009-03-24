package no.helsebiblioteket.admin.domain;


public class OrganizationUser  {
	private Organization organization;
	private User user;
	
	// Helpers
	@Override
	public String toString() {
		return user + " - " + organization;
	}

	// GET/SET
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}