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

public class SupplierSourceResourceFactory {
	public static SupplierSourceResourceFactory factory = new SupplierSourceResourceFactory();
	private SupplierSourceResourceFactory(){}
	public SupplierSourceResource createSupplierSourceResource(){
		SupplierSourceResource resource = new SupplierSourceResource();
		resource.setLastChanged(new Date());
		return resource;
	}
	public SupplierSourceResource completeSupplierSourceResource(ResourceType resourceType, SupplierSource supplierSource){
		SupplierSourceResource resource = createSupplierSourceResource();
		resource.setResourceType(resourceType);
		resource.setSupplierSource(supplierSource);
		return resource;
	}
}
