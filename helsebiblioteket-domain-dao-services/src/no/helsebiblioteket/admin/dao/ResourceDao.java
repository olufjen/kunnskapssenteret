package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.SupplierSource;

public interface ResourceDao {
	public Resource getResourceById(Integer resourceId);
	
	public List<Resource> getResourceList();
	
	public void insertResource(Resource resource);
	
	public void updateResource(Resource resource);
	
	public void deleteResource(Resource resource);
}
