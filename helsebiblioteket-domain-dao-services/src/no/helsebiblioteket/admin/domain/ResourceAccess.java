package no.helsebiblioteket.admin.domain;

public class ResourceAccess extends Access {
	// References
	private Resource resource;

	// GET/SET
	public Resource getResource() {
		return resource;
	}
	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
