package no.helsebiblioteket.admin.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SupplierOrganization implements Serializable {
	// Values loaded by ResourceLine
	private SupplierSourceResource[] resourceList;
	
	// Organization
	private Organization organization = new Organization();

	private String supportEmail;
	private String supportTelephone;
	
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
	public String getSupportEmail() {
		return supportEmail;
	}
	public void setSupportEmail(String supportEmail) {
		this.supportEmail = supportEmail;
	}
	public String getSupportTelephone() {
		return supportTelephone;
	}
	public void setSupportTelephone(String supportTelephone) {
		this.supportTelephone = supportTelephone;
	}
}
