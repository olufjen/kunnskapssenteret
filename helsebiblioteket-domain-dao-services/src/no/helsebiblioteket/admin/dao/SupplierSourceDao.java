package no.helsebiblioteket.admin.dao;

import java.util.List;

import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.SupplierSource;

public interface SupplierSourceDao {
	public Resource getSupplierSourceById(Integer supplierSourceId);
	
	public List<SupplierSource> getSupplierSourceList();
	
	public void insertSupplierSource(SupplierSource supplierSource);
	
	public void updateSupplierSource(SupplierSource supplierSource);
	
	public void deleteSupplierSource(SupplierSource supplierSource);
}
