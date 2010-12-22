package no.helsebiblioteket.admin.factory;

import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;

public class ResourceTypeFactory {
	public static ResourceTypeFactory factory = new ResourceTypeFactory();
	private ResourceTypeFactory(){}
	public ResourceType createResourceType(){
		ResourceType resourceType = new ResourceType();
		resourceType.setDescription("");
		resourceType.setKey(ResourceTypeKey.supplier_source);
		resourceType.setName("");
		return resourceType;
	}
	public ResourceType completeSupplierResourceType(){
		ResourceType resourceType = createResourceType();
		return resourceType;
	}
}
