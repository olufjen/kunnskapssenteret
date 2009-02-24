package no.helsebiblioteket.admin.sqlmapdao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.ResourceDao;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;


public class SqlMapResourceDao extends SqlMapClientDaoSupport implements ResourceDao {
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
}
