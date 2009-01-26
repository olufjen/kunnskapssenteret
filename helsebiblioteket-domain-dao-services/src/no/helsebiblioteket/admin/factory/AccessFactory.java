package no.helsebiblioteket.admin.factory;

import java.util.Date;

import no.helsebiblioteket.admin.domain.Access;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.Organization;

public class AccessFactory {
	public static AccessFactory factory = new AccessFactory();
	private AccessFactory(){}
	public Access createAccess(){
		Access access = new Access();
		AccessType accessType = new AccessType();
		// TODO: What are the Access type categories?
		accessType.setCategory("");
		accessType.setDescription("");
		accessType.setKey("");
		accessType.setName("");
		access.setType(accessType);
		access.setLastChanged(new Date());
		Organization organization = new Organization();
//		organization.setAccessList(accessList);
//		organization.setContactInformation(contactInformation)
//		organization.set
//		
//		access.setOrganization(organization);
//		access.setOrganizationAsResource(organizationAsResource);
//		access.setOrganizationId(organizationId);
//		access.setOrgTypeId(orgTypeId);
//		access.setProvidedBy(providedBy);
//		access.setSupplierSource(supplierSource);
//		access.setType(accessType);
//		access.setUserId(userId);
//		access.setUserRoleId(userRoleId);
//		access.setValidFrom(validFrom);
//		access.setValidTo(validTo);
		
		return access;
	}
}
