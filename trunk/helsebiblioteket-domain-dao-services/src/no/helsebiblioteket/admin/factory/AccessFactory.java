package no.helsebiblioteket.admin.factory;

import java.util.Date;

import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.SupplierSource;

public class AccessFactory {
	public static AccessFactory factory = new AccessFactory();
	private AccessFactory(){}
	public Access createAccess(){
		Access access = new Access();
		access.setLastChanged(new Date());
		access.setValidFrom(new Date());
		access.setValidTo(new Date());

//		access.setOrganization(organization);
//		access.setOrganizationAsResource(organizationAsResource);
//		access.setProvidedBy(providedBy);
//		access.setSupplierSource(supplierSource);
//		access.setType(accessType);
		
//		accessType.setCategory("");
//		accessType.setDescription("");
//		accessType.setKey("");
//		accessType.setName("");
		return access;
	}
}
