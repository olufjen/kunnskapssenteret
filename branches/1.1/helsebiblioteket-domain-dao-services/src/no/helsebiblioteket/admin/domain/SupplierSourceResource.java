package no.helsebiblioteket.admin.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SupplierSourceResource implements Serializable  {
	// References
	private SupplierSource supplierSource;
	private Resource resource = new Resource();

	// Helpers
	@Override
	public String toString() {
		return "[" + supplierSource + ", " + resource + "]";
	}

	// GET/SET
	public SupplierSource getSupplierSource() {
		return supplierSource;
	}
	public void setSupplierSource(SupplierSource supplierSource) {
		this.supplierSource = supplierSource;
	}
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
