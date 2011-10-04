package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;

import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.ResourceDao;
import no.helsebiblioteket.admin.domain.SupplierOrganization;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;


public class SqlMapResourceDao extends IbatisSqlMapClientDaoSupport implements ResourceDao {
	public void insertSupplierSourceResource(SupplierSourceResource resource) {
		getSqlMapClientTemplate().insert("insertSupplierSourceResource", resource);
		SupplierSourceResource tmp = (SupplierSourceResource) getSqlMapClientTemplate().queryForObject("getSupplierSourceResourceById", resource.getResource().getId());
		resource.getResource().setLastChanged(tmp.getResource().getLastChanged());
	}
	public void updateSupplierSourceResource(SupplierSourceResource resource) {
		getSqlMapClientTemplate().update("updateSupplierSourceResource", resource);
		SupplierSourceResource tmp = (SupplierSourceResource) getSqlMapClientTemplate().queryForObject("getSupplierSourceResourceById", resource.getResource().getId());
		resource.getResource().setLastChanged(tmp.getResource().getLastChanged());
	}
	public void deleteSupplierSourceResource(SupplierSourceResource resource) {
		getSqlMapClientTemplate().delete("deleteSupplierSourceResource", resource);
	}
	public SupplierSourceResource getResourceById(Integer resourceId) {
		// Only supports supplier_source
		return (SupplierSourceResource) getSqlMapClientTemplate().queryForObject("getSupplierSourceResourceById", resourceId);
	}
	@SuppressWarnings("unchecked")
	public List<SupplierSourceResource> getResourceByOrganization(SupplierOrganization supplierOrganization) {
		return getSqlMapClientTemplate().queryForList("getSupplierSourceResourceByOrganizationId", supplierOrganization.getOrganization().getId());
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<SupplierSourceResource> getResourceListAll() {
		return getSqlMapClientTemplate().queryForList("getSupplierSourceResourcesAll");
	}
}