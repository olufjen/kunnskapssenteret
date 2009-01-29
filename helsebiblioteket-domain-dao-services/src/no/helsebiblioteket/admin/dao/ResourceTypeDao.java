package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.ResourceType;

public interface ResourceTypeDao {
	// Edit
	public void insertResourceType(ResourceType resourceType);
	public void updateResourceType(ResourceType resourceType);
	public void deleteResourceType(ResourceType resourceType);

	// Fetch
	public List<Resource> getResourceTypeListAll();
}
