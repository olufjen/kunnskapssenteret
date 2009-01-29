package no.helsebiblioteket.admin.factory;

import java.util.ArrayList;
import java.util.Date;

import no.helsebiblioteket.admin.domain.MemberOrganization;
import no.helsebiblioteket.admin.domain.Organization;
import no.helsebiblioteket.admin.domain.OrganizationType;
import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.Url;
import no.helsebiblioteket.admin.domain.Role;
import no.helsebiblioteket.admin.domain.User;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;

public class ResourceTypeFactory {
	public static ResourceTypeFactory factory = new ResourceTypeFactory();
	private ResourceTypeFactory(){}
	public ResourceType createResourceType(){
		ResourceType resourceType = new ResourceType();
		resourceType.setDescription("");
		resourceType.setKey(ResourceTypeKey.dummy);
		resourceType.setName("");
		return resourceType;
	}
	public ResourceType completeSupplierResourceType(){
		ResourceType resourceType = createResourceType();
		return resourceType;
	}
}
