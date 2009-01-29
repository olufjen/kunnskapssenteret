package no.helsebiblioteket.admin.factory;

import java.util.Date;

import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.ResourceAccess;
import no.helsebiblioteket.admin.domain.SupplierSource;

public class ResourceAccessFactory {
	public static ResourceAccessFactory factory = new ResourceAccessFactory();
	private ResourceAccessFactory(){}
	public ResourceAccess createResourceAccess(){
		ResourceAccess access = new ResourceAccess();
		access.setLastChanged(new Date());
		access.setValidFrom(new Date());
		access.setValidTo(new Date());
		return access;
	}
	public ResourceAccess completeResourceAccess(Resource resource, AccessType accessType, Organization providedBy){
		ResourceAccess access = createResourceAccess();
		access.setAccessType(accessType);
		access.setProvidedBy(providedBy);
		access.setResource(resource);
		return access;
	}
}
