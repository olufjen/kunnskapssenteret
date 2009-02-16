package no.helsebiblioteket.admin.sqlmapdao;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.ResourceDao;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.SupplierSourceResource;
import no.helsebiblioteket.admin.domain.key.ResourceTypeKey;


public class SqlMapResourceDao extends SqlMapClientDaoSupport implements ResourceDao {
	public SupplierSourceResource getResourceById(Integer resourceId, ResourceTypeKey typeKey) {
		// Only supports supplier_source
		if(typeKey.getValue().equals(ResourceTypeKey.supplier_source.getValue())){
			return (SupplierSourceResource) getSqlMapClientTemplate().queryForObject("getSupplierSourceResourceById", resourceId);
		} else {
			// Ooops!
			return null;
		}
	}
	public void insertSupplierSourceResource(SupplierSourceResource resource) {
		getSqlMapClientTemplate().insert("insertSupplierSourceResource", resource);
	}
	public void updateSupplierSourceResource(SupplierSourceResource resource) {
		getSqlMapClientTemplate().update("updateSupplierSourceResource", resource);
	}
	public void deleteSupplierSourceResource(SupplierSourceResource resource) {
		getSqlMapClientTemplate().delete("deleteSupplierSourceResource", resource.getResource().getResourceId());
	}
}
