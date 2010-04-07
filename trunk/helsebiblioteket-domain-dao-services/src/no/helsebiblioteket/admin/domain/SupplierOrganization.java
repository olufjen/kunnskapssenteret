package no.helsebiblioteket.admin.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SupplierOrganization implements Serializable {
	// Values loaded by ResourceLine
	private SupplierSourceResource[] resourceList;
	
	// Organization
	private Organization organization = new Organization();

	// Helpers
	@Override
	public String toString() {
		return "Supplier: " + organization;
	}

	// GET/SET
	public SupplierSourceResource[] getResourceList() {
		return resourceList;
	}
	public void setResourceList(SupplierSourceResource[] resourceList) {
		this.resourceList = resourceList;
	}
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
