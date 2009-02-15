package no.helsebiblioteket.admin.sqlmapdao;


import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import no.helsebiblioteket.admin.dao.AccessTypeDao;
import no.helsebiblioteket.admin.dao.keys.AccessTypeCompositeKey;
import no.helsebiblioteket.admin.domain.AccessType;
import no.helsebiblioteket.admin.domain.category.AccessTypeCategory;
import no.helsebiblioteket.admin.domain.key.AccessTypeKey;


public class SqlMapAccessTypeDao extends SqlMapClientDaoSupport implements AccessTypeDao {
	public AccessType getAccessTypeByKey(AccessTypeKey key, AccessTypeCategory category) {
		AccessTypeCompositeKey compositeKey = new AccessTypeCompositeKey();
		compositeKey.setCategory(category.toString());
		compositeKey.setKey(key.getValue());
		return (AccessType) getSqlMapClientTemplate().queryForObject("getAccessTypeByKey", compositeKey);
	}
	@SuppressWarnings("unchecked")
	public List<AccessType> getAccessTypeListAll() {
		return getSqlMapClientTemplate().queryForList("getAccessTypeListAll");
	}
}
