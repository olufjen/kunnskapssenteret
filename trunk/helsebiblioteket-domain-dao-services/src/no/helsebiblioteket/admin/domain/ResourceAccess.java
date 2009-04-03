package no.helsebiblioteket.admin.domain;

public class ResourceAccess {
	// References
	private SupplierSourceResource resource;
	private Access access = new Access();

	// Helpers
	@Override
	public String toString() {
		return "{ " + resource + "; " + access + "}";
	}

	// GET/SET
	public SupplierSourceResource getResource() {
		return resource;
	}
	public void setResource(SupplierSourceResource resource) {
		this.resource = resource;
	}
	public Access getAccess() {
		return access;
	}
	public void setAccess(Access access) {
		this.access = access;
	}
}