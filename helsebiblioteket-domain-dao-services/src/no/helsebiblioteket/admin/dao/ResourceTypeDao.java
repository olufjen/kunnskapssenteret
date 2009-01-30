package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.ResourceType;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;

public interface ResourceTypeDao {
	// Edit not in use
//	public void insertResourceType(ResourceType resourceType);
//	public void updateResourceType(ResourceType resourceType);
//	public void deleteResourceType(ResourceType resourceType);

	// Fetch
	public List<ResourceType> getResourceTypeListAll();
	public ResourceType getResourceTypeByKey(ResourceTypeKey resourceTypeKey);
}
