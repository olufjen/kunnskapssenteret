package no.helsebiblioteket.admin.dao;

import java.util.List;
import no.helsebiblioteket.admin.domain.SupplierSource;

public interface SupplierSourceDao {
	// Edit
	public void insertSupplierSource(SupplierSource supplierSource);
	public void updateSupplierSource(SupplierSource supplierSource);
	public void deleteSupplierSource(SupplierSource supplierSource);

	// Fetch
	public List<SupplierSource> getSupplierSourceListAll();
}
