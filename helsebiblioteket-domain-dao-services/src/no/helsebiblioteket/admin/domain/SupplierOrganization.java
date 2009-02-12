package no.helsebiblioteket.admin.domain;

import java.util.List;

public class SupplierOrganization extends Organization{
	// Values loaded by ResourceLine
	private List<Resource> resourceList;

	// GET/SET
	public List<Resource> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<Resource> resourceList) {
		this.resourceList = resourceList;
	}
}