package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;

public interface ResourceDao {
	// Edit not implemented
	public void insertSupplierSourceResource(SupplierSourceResource resource);
	public void updateSupplierSourceResource(SupplierSourceResource resource);
	public void deleteSupplierSourceResource(SupplierSourceResource resource);

	// Fetch
	public SupplierSourceResource getResourceById(Integer id);
	public List<SupplierSourceResource> getResourceByOrganization(SupplierOrganization supplierOrganization);
}
