package no.helsebiblioteket.admin.sqlmapdao;

import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.SupplierSourceDao;
import no.helsebiblioteket.admin.domain.SupplierSource;

public class SqlMapSupplierSourceDao extends SqlMapClientDaoSupport implements SupplierSourceDao {
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
		return (List<SupplierSource>) getSqlMapClientTemplate().queryForList("getSupplierSourceListAll");
	}
	public SupplierSource getSupplierSourceById(Integer id){
		return (SupplierSource) getSqlMapClientTemplate().queryForObject("getSupplierSourceById", id);
	}
}
