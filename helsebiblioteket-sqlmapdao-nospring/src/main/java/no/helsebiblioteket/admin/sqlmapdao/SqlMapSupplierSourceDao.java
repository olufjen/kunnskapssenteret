package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;
import no.helsebiblioteket.admin.sqlmapdao.ibatissupport.IbatisSqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.domain.SupplierSource;
import no.helsebiblioteket.admin.domain.Url;

public class SqlMapSupplierSourceDao extends IbatisSqlMapClientDaoSupport implements SupplierSourceDao {
	public void insertSupplierSource(SupplierSource supplierSource){
		getSqlMapClientTemplate().insert("insertSupplierSource", supplierSource);
		SupplierSource tmp = (SupplierSource) getSqlMapClientTemplate().queryForObject("getSupplierSourceById", supplierSource.getId());
		supplierSource.setLastChanged(tmp.getLastChanged());
	}
	public void updateSupplierSource(SupplierSource supplierSource){
		getSqlMapClientTemplate().update("updateSupplierSource", supplierSource);
		SupplierSource tmp = (SupplierSource) getSqlMapClientTemplate().queryForObject("getSupplierSourceById", supplierSource.getId());
		supplierSource.setLastChanged(tmp.getLastChanged());
	}
	public void deleteSupplierSource(SupplierSource supplierSource){
		getSqlMapClientTemplate().delete("deleteSupplierSource", supplierSource);
	}
	@SuppressWarnings("unchecked")
	public List<SupplierSource> getSupplierSourceListAll(){
		return (List<SupplierSource>) getSqlMapClientTemplate().queryForList("getSupplierSourceListAll");
	}
	public SupplierSource getSupplierSourceById(Integer id){
		return (SupplierSource) getSqlMapClientTemplate().queryForObject("getSupplierSourceById", id);
	}
	@SuppressWarnings("unchecked")
	public SupplierSource getSupplierSourceByDomain(Url url) {
		List<SupplierSource> supplierSourceList = (List<SupplierSource>) getSqlMapClientTemplate().queryForList("getSupplierSourceByDomain", url);
		SupplierSource supplierSource = (supplierSourceList != null && supplierSourceList.size() > 0) ? supplierSourceList.get(0) : null;
		return supplierSource;
		//return (SupplierSource) getSqlMapClientTemplate().queryForObject("getSupplierSourceByUrlStartsWith", url);
	}
}
