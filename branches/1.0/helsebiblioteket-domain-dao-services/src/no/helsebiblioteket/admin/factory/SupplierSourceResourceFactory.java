package no.helsebiblioteket.admin.factory;

import java.util.Date;

import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;

public class SupplierSourceResourceFactory {
	public static SupplierSourceResourceFactory factory = new SupplierSourceResourceFactory();
	private SupplierSourceResourceFactory(){}
	public SupplierSourceResource createSupplierSourceResource(){
		SupplierSourceResource resource = new SupplierSourceResource();
		resource.getResource().setLastChanged(new Date());
		return resource;
	}
	public SupplierSourceResource completeSupplierSourceResource(ResourceType resourceType, SupplierSource supplierSource, SupplierOrganization supplierOrganization){
		SupplierSourceResource resource = createSupplierSourceResource();
		resource.getResource().setResourceType(resourceType);
		resource.setSupplierSource(supplierSource);
		resource.getResource().setOfferedBy(supplierOrganization.getOrganization().getId());
		return resource;
	}
}
