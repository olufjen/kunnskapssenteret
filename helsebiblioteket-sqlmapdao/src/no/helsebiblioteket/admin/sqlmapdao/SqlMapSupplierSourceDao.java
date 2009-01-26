package no.helsebiblioteket.admin.sqlmapdao;


import java.util.List;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.domain.Resource;
import no.helsebiblioteket.admin.domain.SupplierSource;


public class SqlMapSupplierSourceDao extends SqlMapClientDaoSupport implements SupplierSourceDao {
	// TODO: Go through all!
	public void insertSupplierSource(SupplierSource supplierSource){
		
		getSqlMapClientTemplate().insert("insertSupplierSource", supplierSource);

	}
	public void updateSupplierSource(SupplierSource supplierSource){
		
		getSqlMapClientTemplate().update("updateSupplierSource", supplierSource);

	}
	public void deleteSupplierSource(SupplierSource supplierSource){
		
		getSqlMapClientTemplate().delete("deleteSupplierSource", supplierSource);

	}
	public List<SupplierSource> getSupplierSourceListAll(){
		return null;
	}

	
	
	
	public Resource getSupplierSourceById(Integer supplierSourceId) {
		return (Resource) getSqlMapClientTemplate().queryForObject("getSupplierSourceById", supplierSourceId);
	}
	
	public List<SupplierSource> getSupplierSourceList() {
		return (List<SupplierSource>) getSqlMapClientTemplate().queryForList("getSupplierSourceList");
	}
	
	
}
