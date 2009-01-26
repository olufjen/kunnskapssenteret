package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.Resource;

public interface ResourceDao {
	// Edit
	public void insertResource(Resource resource);
	public void updateResource(Resource resource);
	public void deleteResource(Resource resource);

	// Fetch
	public List<Resource> getResourceListAll();
}
