package no.helsebiblioteket.admin.dao;

import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;

public interface ResourceDao {
	// Edit not implemented
	public void insertSupplierSourceResource(SupplierSourceResource resource);
	public void updateSupplierSourceResource(SupplierSourceResource resource);
	public void deleteSupplierSourceResource(SupplierSourceResource resource);

	// Fetch
	public Resource getResourceById(Integer id, ResourceTypeKey typeKey);
}
