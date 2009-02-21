package no.helsebiblioteket.admin.domain;

public class SupplierOrganization {
	// Values loaded by ResourceLine
	private Resource[] resourceList;
	
	// Organization
	private Organization organization = new Organization();

	// Helpers
	@Override
	public String toString() {
		return "Supplier: " + organization;
	}

	// GET/SET
	public Resource[] getResourceList() {
		return resourceList;
	}
	public void setResourceList(Resource[] resourceList) {
		this.resourceList = resourceList;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
